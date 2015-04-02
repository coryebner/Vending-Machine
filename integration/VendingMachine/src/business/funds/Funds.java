package business.funds;

import hardware.racks.CoinRack;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import business.selection_delivery.InventoryController;

/**
 * Description of PrepaidController
 * 
 * @author James Pihooja
 * @author Nabil Muthanna
 *
 *         Class to interact with hardware to conduct a Transaction with all
 *         subcomponents 1. Prepaid 2. Bills 3. Coins - change provided 4.
 *         PayPal 5. Credit Card - Through PayPal
 */
public class Funds {
	private boolean prepaidPresent = false;
	private boolean billsPresent = false;
	private boolean coinsPresent = false;
	private boolean payPalPresent = false;
	private boolean creditCardPresent = false;
	private boolean conductTransactionIsCalled = false;
	
	boolean bestEffortChange = false;
	boolean noChangeDueToPrepaidExceed = false;

	private PrepaidController prepaidController;
	private BanknoteController bankNoteController;
	private CoinsController coinsController;
	private CreditCardController creditCardController;
	private PayPalController payPalController;
	private ExactChangeController exactChangeController;

	private VMCurrencies machineCurrencies;

	private HashMap<String, String> LOG;

	/**
	 * @param locale - the locale of the machine Locale.US, Locale.CANADA, Locale.UK only supported
	 * @param bestEffortChange - if the machine dispenses change or not when exact change is active
	 * @param coinRacks - CounRack[] of available coin racks
	 * @param coinRackDenominations - the denominations stored in each coin rack
	 * @param availablePaymentMethods - List<PaymentMethods> a list of enumerated payment types supported
	 * @param inventoryController - InventoryController reference (must have current product state for machine due to event handling)
	 * 
	 * No arguments needed for BankNotController, PayPal controller. creditCard
	 * controller constructors
	 */
	public Funds(Locale locale, boolean bestEffortChange, CoinRack[] coinRacks,
			int[] coinRackDenominations, int[] coinRackQuantities, 
			List<PaymentMethods> availablePaymentMethods, InventoryController inventoryController) {
		//this.prepaidPresent = this.billsPresent = this.coinsPresent = this.creditCardPresent = this.payPalPresent = this.conductTransactionIsCalled = false;

		this.machineCurrencies = new VMCurrencies(locale);

		this.LOG = new HashMap<String, String>();
		
		/* Setup the Exact Change Object */
		exactChangeController = new ExactChangeController(inventoryController);
		this.bestEffortChange = bestEffortChange;

		/* Set the payment methods for this machine */
		if (availablePaymentMethods.contains(PaymentMethods.PREPAID)) {
			this.prepaidPresent = true;
			this.prepaidController = new PrepaidController(this.machineCurrencies);
		}
		if (availablePaymentMethods.contains(PaymentMethods.BILLS)) {
			this.billsPresent = true;
			this.bankNoteController = new BanknoteController();
		}
		if (availablePaymentMethods.contains(PaymentMethods.COINS)) {
			this.coinsPresent = true;
			this.coinsController = new CoinsController(bestEffortChange, coinRacks,
					coinRackDenominations, coinRackQuantities, null);///////////////////////////////////////////Needs to be fixed
		}
		if (availablePaymentMethods.contains(PaymentMethods.CREDITCARD)) {
			this.creditCardPresent = true;
			this.creditCardController = new CreditCardController(
					this.payPalController);
		}
		if (availablePaymentMethods.contains(PaymentMethods.PAYPAL)) {
			this.payPalPresent = true;
			this.payPalController = new PayPalController();
		}
	}

	/**
	 * Description of ConductTransaction with all available payment methods
	 * 
	 * @param price
	 *            The price in cents of the transaction attempted
	 * @return The return code based on success of the transaction Assumption,
	 *         returnValue of ConductTransaction
	 */
	public TransactionReturnCode ConductTransaction(int price){
		TransactionReturnCode returnCode = TransactionReturnCode.INSUFFICIENTFUNDS;
		TransactionReturnCode returnCodeCC_PP = TransactionReturnCode.INSUFFICIENTFUNDS;
		
		noChangeDueToPrepaidExceed = false;
		
		int availableFunds = 0;
		availableFunds = getTotalBalanceInPreBilCoin();
		
		// Try to conductTransaction with funds we know are available
		if(availableFunds >= price){
			returnCode = conductTransactionFromAvailableFunds(price);
			giveChange(availableFunds - price);
		}
		else{
			// try with credit card and paypal
			int remainingBalance = price - availableFunds;
			returnCodeCC_PP = conductTransactionFromCC_PP(remainingBalance);
			if(returnCodeCC_PP == TransactionReturnCode.SUCCESSFUL){
				returnCode = conductTransactionFromAvailableFunds(availableFunds);
			}
			else{
				returnCode = returnCodeCC_PP;
			}
		}
		return returnCode;
		
	}
	
	
	private void giveChange(int amount){
		if(exactChangeController.isExactChangeActive() && !bestEffortChange){
			return; // we do not provide change
		}
		
		if(amount > 0 && coinsPresent && !noChangeDueToPrepaidExceed){
			coinsController.provideChange(amount);
		}
	}
	
	private TransactionReturnCode conductTransactionFromCC_PP(int price){
		TransactionReturnCode creditCardReturn = TransactionReturnCode.INSUFFICIENTFUNDS;
		TransactionReturnCode payPalReturn = TransactionReturnCode.INSUFFICIENTFUNDS;
		
		if(creditCardPresent){
			creditCardReturn = creditCardController.ConductTransaction(price);
		}
		if(creditCardReturn != TransactionReturnCode.SUCCESSFUL && payPalPresent){
			payPalReturn = payPalController.ConductTransaction(price);
			return payPalReturn;
		}
		return creditCardReturn;
		
	}
	
	private TransactionReturnCode conductTransactionFromAvailableFunds(int price){
		TransactionReturnCode returnCodePP = TransactionReturnCode.SUCCESSFUL;
		TransactionReturnCode returnCodeC = TransactionReturnCode.SUCCESSFUL;
		TransactionReturnCode returnCodeBN = TransactionReturnCode.SUCCESSFUL;
		
		int balance = price;
		int amount = 0;
		
		if(prepaidPresent && balance > 0){
			int ppFunds = prepaidController.getAvailableBalance();
			if(ppFunds > balance){
				noChangeDueToPrepaidExceed = true;// set to tell if change should be provided
			}
			amount = Math.min(ppFunds, balance);
			returnCodePP = prepaidController.ConductTransaction(amount);
			balance -= amount;
		}
		if(billsPresent && balance > 0){
			int billsFunds = bankNoteController.getAvailableBalance();
			amount = Math.min(billsFunds, balance);
			returnCodeC = bankNoteController.ConductTransaction(amount);
			balance -= amount;
		}
		if(coinsPresent && balance > 0){
			int coinsFunds = coinsController.getAvailableBalance();
			amount = Math.min(coinsFunds, balance);
			returnCodeC = coinsController.ConductTransaction(amount);
			balance -= amount;
		}
		
		if(returnCodePP == TransactionReturnCode.SUCCESSFUL &&
				returnCodeBN == TransactionReturnCode.SUCCESSFUL &&
				returnCodeC == TransactionReturnCode.SUCCESSFUL){
			return TransactionReturnCode.SUCCESSFUL;
		}
		else{
			return TransactionReturnCode.UNSUCCESSFUL;
		}
		
	}

	/**
	 * @return totalBalanceInPreBilCoin of(PrePaid and/or Bills and/or Coins)
	 */
	private int getTotalBalanceInPreBilCoin() {
		// TODO Auto-generated method stub
		int totalBalanceInPreBilCoin = 0;
		if (this.prepaidPresent) {
			totalBalanceInPreBilCoin += this.prepaidController
					.getAvailableBalance();
		}
		if (this.billsPresent) {
			totalBalanceInPreBilCoin += this.bankNoteController
					.getAvailableBalance();
		}
		if (this.coinsPresent) {
			totalBalanceInPreBilCoin += this.coinsController
					.getAvailableBalance();
		}

		return totalBalanceInPreBilCoin;
	}

	/**
	 * @return null if no attempt made to conduct transaction otherwise it
	 *         returns the PaymentMethodUsedAndPaymentStatus list;
	 */
	public HashMap<String, String> getPaymentMethodUsedAndPaymentStatus() {
		if (this.conductTransactionIsCalled) {
			return this.LOG;
		}
		return null;
	}

	// Getters

	/**
	 * Description of isPrepaidPresent for determining if there is prepaid on
	 * this machine
	 * 
	 * @return Indicates if the prepaid functionality is present
	 */
	public boolean isPrepaidPresent() {
		return prepaidPresent;
	}

	/**
	 * Description of isBillsPresent for determining if there are bills on this
	 * machine
	 * 
	 * @return Indicates if the bills functionality is present
	 */
	public boolean isBillsPresent() {
		return billsPresent;
	}

	/**
	 * Description of isCoinsPresent for determining if there are coins on this
	 * machine
	 * 
	 * @return Indicates if the coins functionality is present
	 */
	public boolean isCoinsPresent() {
		return coinsPresent;
	}

	/**
	 *
	 * @return
	 */
	public boolean isCreditCardPresent() {
		return creditCardPresent;
	}

	/**
	 * Description of isPayPalPresent for determining if there is PayPal on this
	 * machine
	 * 
	 * @return Indicates if the PayPal functionality is present
	 */
	public boolean isPayPalPresent() {
		return payPalPresent;
	}

	/**
	 * Description of isExactChangeActive if no change will be provided
	 * 
	 * @return The state of not providing change due to exact change status
	 */
	public boolean isExactChangeActive() {
		return exactChangeController.isExactChangeActive();
	}

	/**
	 * Description of getPrepaidController
	 * 
	 * @return The PrepaidController for registration
	 */
	public PrepaidController getPrepaidController() {
		return prepaidController;
	}

	/**
	 * Description of getBillsController
	 * 
	 * @return The BillsController for registration
	 */
	public BanknoteController getBankNoteController() {
		return bankNoteController;
	}

	/**
	 * Description of getCoinsController
	 * 
	 * @return The CoinsController for registration
	 */
	public CoinsController getCoinsController() {
		return coinsController;
	}

	/**
	 * Description of getCoinRackControllers
	 * 
	 * @return An array of CoinRackControllers for registration
	 */
	public CoinRackController[] getCoinRackControllers() {
		return null;
	}
	
	public void ONLY_FOR_TESTING_setControllerState(boolean present, Object object, Class<?> classType ){
		if(classType == PrepaidController.class){
			prepaidPresent = present;
			prepaidController = (PrepaidController) object;
			return;
		}
		if(classType == BanknoteController.class){
			billsPresent = present;
			bankNoteController = (BanknoteController) object;
			return;
		}
		if(classType == CoinsController.class){
			coinsPresent = present;
			coinsController = (CoinsController) object;
			return;
		}
		if(classType == CreditCardController.class){
			creditCardPresent = present;
			creditCardController = (CreditCardController) object;
			return;
		}
		if(classType == PayPalController.class){
			payPalPresent = present;
			payPalController = (PayPalController) object;
			return;
		}
	}
	
	
	
	/**
	public TransactionReturnCode ConductTransaction(int price) {
		boolean transactionSuccess = false;
		TransactionReturnCode returnCodeOne;
		TransactionReturnCode returnCodeTwo;

		// Payment Flow is as follow, PrePaid, Bills, Coins, Credit Card, PayPal
		this.conductTransactionIsCalled = true;
		int totalBalance = getTotalBalanceInPreBilCoin();

		// There is enough balance with (PrePaid and/or Bills and/or Coins)
		if (totalBalance >= price) {
			// conduct transaction accordingly
			conductPrePBillCoinTransaction(price);
			this.LOG.putIfAbsent("STATUS", "SUCCESS");
			return TransactionReturnCode.SUCCESSFUL;
		}
		// Try to conduct CreditCard and/or PayPal transaction with (price -
		// totalBalance)
		else {
			returnCodeOne = this.creditCardController.ConductTransaction(price
					- totalBalance);
			if (returnCodeOne == TransactionReturnCode.SUCCESSFUL) {
				transactionSuccess = true;
			}
			if (!transactionSuccess) {
				returnCodeTwo = this.payPalController.ConductTransaction(price
						- totalBalance);
				if (returnCodeTwo == TransactionReturnCode.SUCCESSFUL) {
					transactionSuccess = true;
				}
			}
		}

		if (transactionSuccess) {
			conductPrePBillCoinTransaction(totalBalance);
			this.LOG.putIfAbsent("STATUS", "SUCCESS");
			return TransactionReturnCode.SUCCESSFUL;
		} else {
			this.LOG.putIfAbsent("STATUS", "FAIL");
			return TransactionReturnCode.INSUFFICIENTFUNDS;
		}
	}
	
		private void conductPrePBillCoinTransaction(int price) {
		// TODO Auto-generated method stub
		int prepaidBalance = this.prepaidController.getAvailableBalance();
		int billBalance = this.bankNoteController.getAvailableBalance();

		if (prepaidBalance >= price) {
			this.prepaidController.ConductTransaction(price);
		} else if ((prepaidBalance + billBalance) >= price) {
			this.prepaidController.ConductTransaction(prepaidBalance);
			this.bankNoteController.ConductTransaction(price - prepaidBalance);
		} else {
			this.prepaidController.ConductTransaction(prepaidBalance);
			this.bankNoteController.ConductTransaction(price - prepaidBalance);
			this.coinsController.ConductTransaction(price - prepaidBalance
					- billBalance);
		}

	}
	*/
}