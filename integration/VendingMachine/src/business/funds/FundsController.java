package business.funds;

import hardware.funds.BanknoteReceptacle;
import hardware.funds.BanknoteSlot;
import hardware.funds.CoinReceptacle;
import hardware.funds.CoinSlot;
import hardware.racks.CoinRack;
import hardware.ui.IndicatorLight;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import SDK.logger.Logger;
import SDK.rifffish.Rifffish.PaymentMethod;
import SDK.rifffish.Transaction;
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
public class FundsController {
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
	private CoinStorageBinController coinStorageBinTracker;
	private BanknoteStorageBinController banknoteStorageBinTracker;

	private Currency machineCurrencies;

	private HashMap<String, String> LOG;
	
	private Logger logger;
	
	private boolean cashUsed = false;
	private boolean cardUsed = false;
	private boolean paypalused = false;
	
	private int banknoteValueReturnedNotUsed = 0;
	private int coinValueReturnedNotUsed = 0;

	/**
	 * @param Locale locale
	 *            - the locale of the machine Locale.US, Locale.CANADA,
	 *            Locale.UK only supported
	 * @param List<PaymentMethods> availablePaymentMethods
	 *            - List<PaymentMethods> a list of enumerated payment types
	 *            supported
	 * @param boolean bestEffortChange
	 *            - if the machine dispenses change or not when exact change is
	 *            active
	 * @param int[] coinDenominations
	 *            - the denominations supported by the machine
	 *                        
	 * @param Coinslot coinslot
	 * 				- a reference to the coin slot of the machine
	 * @param CoinReceptacle tempCoinReceptacle
	 * 				- reference to the temporary coin receptacle (hooked up to the coin slot)
	 * @param int tempCoinRecepticleBalance
	 * 				- the current balance inside the temp coin receptacle (0 on fresh start)
	 * @param CoinReceptacle overflowCoinReceptacle
	 * 				- reference to the overflow coin receptacle (hooked up to the temp coin receptacle)
	 * @param Map<Integer, Integer> coinOverflowCoinRecepticleQuantities
	 * 				- a Map of the Value to the number of stored coins <Integer-value><Integer number> inside the overflow bin
	 * @param CoinRack[] coinRacks
	 *            - CoinRack[] of available coin racks
	 * @param int[] coinRackQuantities
	 * 			  - the number of coins currently in each rack
	 * @param BanknoteSlot banknoteSlot
	 * 				- reference to the bank note slot
	 * @param BanknoteReceptacle tempBanknoteReceptacle
	 * 				- reference to the inital temporary banknote receptacle (hooked up to the banknote slot)
	 * @param BanknoteReceptacle permBanknoteStore
	 * 				- reference to the permanent storage banknote receptacle (hooked up to the temp banknote receptacle)
	 * @param Map<Integer, Integer> tempBanknoteReceptacleQuantities
	 *				- a Map of the Value to the number of stored bills <Integer-value><Integer number> inside the temp banknote bin
	 * @param int permBanknoteReceptacleQuanity,	
	 * 				- integer of the number of banknotes permanently stored
	 * @param IndicatorLight outOfOrderLight
	 * @param InventoryController inventoryController
	 *            - InventoryController reference (must have current product
	 * 	           state for machine due to event handling)
	 * @param Rifffish logger	
	 * 				- reference to the logger
	 */
	public FundsController(
			Locale locale, 													
			List<PaymentMethods> availablePaymentMethods,				
			
			// Coin Parameters
			boolean bestEffortChange,									//false if not using coins
			int[] coinDenominations,									//empty if not using coins 		- denominations of coins supported by the machine 
			CoinSlot coinSlot,											//null if not using coins		- for full state
			
			// TempCoinReceptacle
			CoinReceptacle tempCoinReceptacle, 							//null if not using coins 		- for tracking and funds return
			int tempCoinRecepticleBalance,								//0 if not using coins			- needed for recovery (just the funds total)
			
			// Overflow Coin Recepacle
			CoinReceptacle overflowCoinReceptacle, 						//null if not using coins 		- for tracking
			Map<Integer, Integer> coinOverflowCoinRecepticleQuantities,	//null if not using coins		- needed for recovery
			
			// Coin Racks
			CoinRack[] coinRacks,										//null if not using coins		- needed to provide change
			int[] coinRackQuantities,									//empty array if not using coins- needed for recovery
			
			// Bills Parameters
			BanknoteSlot banknoteSlot,									//null if not using banknotes	- for full state
			BanknoteReceptacle tempBanknoteReceptacle,					//null if not using banknotes 	- for tracking and funds return
			BanknoteReceptacle permBanknoteStore,						//null if not using banknotes 	- for tracking
			Map<Integer, Integer> tempBanknoteReceptacleQuantities,		//demoninations to quantity map(initially 0) - for recovery and setup
			int permBanknoteReceptacleQuanity,							//0 if not using bills - initially 0, unless from recovery state
			
			
			IndicatorLight outOfOrderLight,								//NOT NULL - needed for full and out of order statuses
			InventoryController inventoryController,					//NOT NULL - needed for exact change calculations
			Logger logger												//Needed for logging purposes
			) {
		this.logger = logger;
		this.machineCurrencies = new Currency(locale);

		this.LOG = new HashMap<String, String>();

		this.bestEffortChange = bestEffortChange;

		/* Set the payment methods for this machine */
		if (availablePaymentMethods.contains(PaymentMethods.PREPAID)) {
			this.prepaidPresent = true;
			this.prepaidController = new PrepaidController(
					this.machineCurrencies);
		}
		if (availablePaymentMethods.contains(PaymentMethods.BILLS)) {
			this.billsPresent = true;
			this.banknoteStorageBinTracker = new BanknoteStorageBinController(
					permBanknoteReceptacleQuanity, outOfOrderLight);
			this.bankNoteController = new BanknoteController(tempBanknoteReceptacle,
					this.banknoteStorageBinTracker);
		}
		
		// Everything to do with coins
		if (availablePaymentMethods.contains(PaymentMethods.COINS)) {
			this.coinsPresent = true;
	
			// Permanent Storage Bin Controller - restore safe
			this.coinStorageBinTracker = new CoinStorageBinController(
					coinOverflowCoinRecepticleQuantities, outOfOrderLight);
			
			// Coin controller - restore safe
			this.coinsController = new CoinsController(tempCoinRecepticleBalance,tempCoinReceptacle,
					coinRacks, coinDenominations, coinRackQuantities,
					this.coinStorageBinTracker);
			
			// Can only set up exact change controller if there is a
			// coinsController.
			exactChangeController = new ExactChangeController(
					inventoryController,
					coinsController.getCoinRackControllers());
		}
		if (availablePaymentMethods.contains(PaymentMethods.PAYPAL)) {
			this.payPalPresent = true;
			this.payPalController = new PayPalController();
		}
		if (availablePaymentMethods.contains(PaymentMethods.CREDITCARD)) {
			this.creditCardPresent = true;
			if(this.payPalController == null){
				this.payPalController = new PayPalController();
			}
			this.creditCardController = new CreditCardController(
					this.payPalController);
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
	public TransactionReturnCode ConductTransaction(int productID, int price) {
		cardUsed = false;
		cashUsed = false;
		paypalused = false;
		TransactionReturnCode returnCode = TransactionReturnCode.INSUFFICIENTFUNDS;
		TransactionReturnCode returnCodeCC_PP = TransactionReturnCode.INSUFFICIENTFUNDS;

		banknoteValueReturnedNotUsed = 0;
		coinValueReturnedNotUsed = 0;
		
		noChangeDueToPrepaidExceed = false;

		int availableFunds = 0;
		availableFunds = getTotalBalanceInPreBilCoin();

		// Try to conductTransaction with funds we know are available
		if (availableFunds >= price) {
			returnCode = conductTransactionFromAvailableFunds(price);
			giveChange(availableFunds - price - banknoteValueReturnedNotUsed - coinValueReturnedNotUsed);
		} else {
			// try with credit card and paypal
			int remainingBalance = price - availableFunds;
			returnCodeCC_PP = conductTransactionFromCC_PP(remainingBalance);
			if (returnCodeCC_PP == TransactionReturnCode.SUCCESSFUL) {
				returnCode = conductTransactionFromAvailableFunds(availableFunds);
			} else {
				returnCode = returnCodeCC_PP;
			}
		}
		if(cashUsed && (!cardUsed && !paypalused)){
			
			logger.log(new Transaction(productID, PaymentMethod.COIN, true));
		}
		else if(cardUsed && (!cashUsed && !paypalused)){
			logger.log(new Transaction(productID, PaymentMethod.CREDIT_CARD, true));
		}
		else if(paypalused && (!cashUsed && !cardUsed)){
			logger.log(new Transaction(productID, PaymentMethod.PAYPAL, true));
		}
		else if(paypalused || cashUsed || cardUsed){
			logger.log(new Transaction(productID, PaymentMethod.COIN, true));
			//logger.log(new Transaction(availableFunds, PaymentMethod.MIXED, true));
		}
		return returnCode;

	}

	private void giveChange(int amount) {
		if (exactChangeController == null
				|| (exactChangeController.isExactChangeActive() && !bestEffortChange)) {
			return; // we do not provide change
		}

		if (amount > 0 && coinsPresent && !noChangeDueToPrepaidExceed) {
			coinsController.provideChange(amount);
		}
	}

	private TransactionReturnCode conductTransactionFromCC_PP(int price) {
		TransactionReturnCode creditCardReturn = TransactionReturnCode.INSUFFICIENTFUNDS;
		TransactionReturnCode payPalReturn = TransactionReturnCode.INSUFFICIENTFUNDS;

		if (creditCardPresent) {
			creditCardReturn = creditCardController.ConductTransaction(price);
			cardUsed =creditCardReturn.equals(TransactionReturnCode.SUCCESSFUL);
		}
		if (creditCardReturn != TransactionReturnCode.SUCCESSFUL
				&& payPalPresent) {
			payPalReturn = payPalController.ConductTransaction(price);
			paypalused = payPalReturn.equals(TransactionReturnCode.SUCCESSFUL);
			return payPalReturn;
		}
		return creditCardReturn;

	}

	private TransactionReturnCode conductTransactionFromAvailableFunds(int price) {
		TransactionReturnCode returnCodePP = TransactionReturnCode.SUCCESSFUL;
		TransactionReturnCode returnCodeC = TransactionReturnCode.SUCCESSFUL;
		TransactionReturnCode returnCodeBN = TransactionReturnCode.SUCCESSFUL;

		int balance = price;
		int amount = 0;

		if (prepaidPresent && balance > 0) {
			int ppFunds = prepaidController.getAvailableBalance();
			if (ppFunds > balance) {
				noChangeDueToPrepaidExceed = true;// set to tell if change
													// should be provided
			}
			if(ppFunds >0){
				amount = Math.min(ppFunds, balance);
				returnCodePP = prepaidController.ConductTransaction(amount);
				balance -= amount;
				cardUsed = true;
			}
		}

		if (coinsPresent && balance > 0) {
			int coinsFunds = coinsController.getAvailableBalance();
			if(coinsFunds >0){
				amount = Math.min(coinsFunds, balance);
				returnCodeC = coinsController.ConductTransaction(amount);
				balance -= amount;
				cashUsed = true;
			}
		}
		else if(coinsPresent && balance == 0){ // transaction has been covered by coins
			coinValueReturnedNotUsed = coinsController.getAvailableBalance();
			coinsController.pressed(null); // releases the coins
		}

		if (billsPresent && balance > 0) {
			int billsFunds = bankNoteController.getAvailableBalance();
			if(billsFunds >0){
				amount = Math.min(billsFunds, balance);
				returnCodeC = bankNoteController.ConductTransaction(amount);
				balance -= amount;
				cashUsed = true;
			}
		}
		else if(billsPresent && balance ==0){ // tran saction has been covered by prepaid and/or coins don't use bills
			banknoteValueReturnedNotUsed = bankNoteController.getAvailableBalance();
			bankNoteController.pressed(null); // releases unused bank notes
		}
		
		if (returnCodePP == TransactionReturnCode.SUCCESSFUL
				&& returnCodeBN == TransactionReturnCode.SUCCESSFUL
				&& returnCodeC == TransactionReturnCode.SUCCESSFUL) {
			return TransactionReturnCode.SUCCESSFUL;
		} else {
			return TransactionReturnCode.UNSUCCESSFUL;
		}

	}

	/**
	 * @return totalBalanceInPreBilCoin of(PrePaid and/or Bills and/or Coins)
	 */
	private int getTotalBalanceInPreBilCoin() {
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
		// Returns true if exactChange does not exist.
		return exactChangeController == null
				|| exactChangeController.isExactChangeActive();
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
		if(!coinsPresent){
			return null;
		}
		CoinRackController[] coinRackControllers = coinsController.getCoinRackControllers();
		return coinRackControllers;
	}

	public CoinStorageBinController getCoinStorageBinTracker() {
		return this.coinStorageBinTracker;
	}

	public BanknoteStorageBinController getBankNoteStorageBinTracker() {
		return this.banknoteStorageBinTracker;
	}
	
	public CreditCardController getCreditCardController(){
		return this.creditCardController;
	}
	public PayPalController getPayPalController(){
		return this.payPalController;
	}
	public ExactChangeController getExactChangeController(){
		return this.exactChangeController;
	}
	public void ONLY_FOR_TESTING_setControllerState(boolean present,
			Object object, Class<?> classType) {
		if (classType == PrepaidController.class) {
			prepaidPresent = present;
			prepaidController = (PrepaidController) object;
			return;
		}
		if (classType == BanknoteController.class) {
			billsPresent = present;
			bankNoteController = (BanknoteController) object;
			return;
		}
		if (classType == CoinsController.class) {
			coinsPresent = present;
			coinsController = (CoinsController) object;
			return;
		}
		if (classType == CreditCardController.class) {
			creditCardPresent = present;
			creditCardController = (CreditCardController) object;
			return;
		}
		if (classType == PayPalController.class) {
			payPalPresent = present;
			payPalController = (PayPalController) object;
			return;
		}
	}

}
