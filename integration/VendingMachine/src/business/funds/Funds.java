package business.funds;

import hardware.racks.CoinRack;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;


/** Description of PrepaidController
 * @author James Pihooja
 * @author Nabil Muthanna
 *
 * Class to interact with hardware to conduct a Transaction with all subcomponents
 * 1. Prepaid
 * 2. Bills
 * 3. Coins - change provided
 * 4. PayPal
 * 5. Credit Card - Through PayPal
 */
public class Funds {
    
    private boolean prepaidPresent;
    private boolean billsPresent;
    private boolean coinsPresent;
    private boolean payPalPresent;
    private boolean creditCardPresent;
    private boolean conductTransactionIsCalled;
    
    private PrepaidController prepaidController;
    private BanknoteController bankNoteController;
    private CoinsController coinsController;
    private CreditCardController creditCardController;
    private PayPalController payPalController;
    
    
    private VMCurrencies machineCurrencies;
    
	private HashMap<String, String> LOG;    
    
    /*
     CoinRack[] coinRacks, int[] coinRackDenominations,
     int[] coinRackQuantities, int[] productPrices are needed for CoinsController constructor
     
     No arguments needed for BankNotController, PayPal controller. creditCard controller constructors
     
     Only Locale is needed for PrePaid controller constructor
     */
    
    //Public Constructor for Funds
    public Funds(Locale locale, CoinRack[] coinRacks, int[] coinRackDenominations, int[] coinRackQuantities, int[] productPrices, List<PaymentMethods> availablePaymentMethods){
        this.prepaidPresent = this.billsPresent = this.coinsPresent = this.creditCardPresent = this.payPalPresent = this.conductTransactionIsCalled = false;
        
        this.machineCurrencies = new VMCurrencies(locale);
        
        this.prepaidController = new PrepaidController(this.machineCurrencies);
        this.bankNoteController = new BanknoteController();
        this.coinsController =  new CoinsController(coinRacks, coinRackDenominations, coinRackQuantities, productPrices);
        this.payPalController = new PayPalController();
        this.creditCardController = new CreditCardController(this.payPalController);
        
        this.LOG = new HashMap<String, String>();
        
        /* Set the payment methods for this machine */
        if(availablePaymentMethods.contains(PaymentMethods.PREPAID)){
            this.prepaidPresent = true;
        }
        if(availablePaymentMethods.contains(PaymentMethods.BILLS)){
            this.billsPresent = true;
        }
        if(availablePaymentMethods.contains(PaymentMethods.COINS)){
            this.coinsPresent = true;
        }
        if(availablePaymentMethods.contains(PaymentMethods.CREDITCARD)){
            this.creditCardPresent = true;
        }
        if(availablePaymentMethods.contains(PaymentMethods.PAYPAL)){
            this.payPalPresent = true;
        }
    }
    
    
    /** Description of ConductTransaction with all available payment methods
     * @param price 	The price in cents of the transaction attempted
     * @return 			The return code based on success of the transaction
     * Assumption, returnValue of ConductTransaction
     */
    public TransactionReturnCode ConductTransaction(int price){
        boolean transactionSuccess = false;
        TransactionReturnCode returnCodeOne;
        TransactionReturnCode returnCodeTwo;
        
        //Payment Flow is as follow, PrePaid, Bills, Coins, Credit Card, PayPal
        this.conductTransactionIsCalled = true;
        int totalBalance = getTotalBalanceInPreBilCoin();
        
        //There is enough balance with (PrePaid and/or Bills and/or Coins)
        if(totalBalance >= price){
            //conduct transaction accordingly
            conductPrePBillCoinTransaction(price);
			this.LOG.putIfAbsent("STATUS", "SUCCESS");
            return TransactionReturnCode.SUCCESSFUL;
        }
        //Try to conduct CreditCard and/or PayPal transaction with (price - totalBalance)
        else{
            returnCodeOne = this.creditCardController.ConductTransaction(price - totalBalance);
            if(returnCodeOne == TransactionReturnCode.SUCCESSFUL){
                transactionSuccess = true;
            }
            if(!transactionSuccess){
                returnCodeTwo = this.payPalController.ConductTransaction(price - totalBalance);
                if(returnCodeTwo == TransactionReturnCode.SUCCESSFUL){
                    transactionSuccess = true;
                }
            }
        }
        
        if(transactionSuccess){
            conductPrePBillCoinTransaction(totalBalance);
			this.LOG.putIfAbsent("STATUS", "SUCCESS");
            return TransactionReturnCode.SUCCESSFUL;
        } else{
			this.LOG.putIfAbsent("STATUS", "FAIL");
            return TransactionReturnCode.INSUFFICIENTFUNDS;
        }
    }
    
    /**
     * No error Handling
     * @param price
     */
    private void conductPrePBillCoinTransaction(int price) {
        // TODO Auto-generated method stub
        int prepaidBalance = this.prepaidController.getAvailableBalance();
        int billBalance = this.bankNoteController.getAvailableBalance();
        
        if(prepaidBalance >= price){
            this.prepaidController.ConductTransaction(price);
        } else if((prepaidBalance + billBalance) >= price){
            this.prepaidController.ConductTransaction(prepaidBalance);
            this.bankNoteController.ConductTransaction(price - prepaidBalance);
        } else{
            this.prepaidController.ConductTransaction(prepaidBalance);
            this.bankNoteController.ConductTransaction(price - prepaidBalance);
            this.coinsController.ConductTransaction(price - prepaidBalance - billBalance);
        }
        
    }
    
    
    /**
     * @return totalBalanceInPreBilCoin of(PrePaid and/or Bills and/or Coins)
     */
    private int getTotalBalanceInPreBilCoin() {
        // TODO Auto-generated method stub
    	int totalBalanceInPreBilCoin = 0;
    	if(this.prepaidPresent){
    		totalBalanceInPreBilCoin += this.prepaidController.getAvailableBalance();
    	}
    	if(this.billsPresent){
    		totalBalanceInPreBilCoin += this.bankNoteController.getAvailableBalance();
    	}
    	if(this.coinsPresent){
    		totalBalanceInPreBilCoin += this.coinsController.getAvailableBalance();
    	}
    	
    	return totalBalanceInPreBilCoin;
    }
    
    
    /**
     * @return null if no attempt made to conduct transaction
     * otherwise it returns the PaymentMethodUsedAndPaymentStatus list;
     */
    public HashMap<String, String> getPaymentMethodUsedAndPaymentStatus(){
        if(this.conductTransactionIsCalled){
            return this.LOG;
        }
        return null;
    }
    
    
    
    
    //Getters
    
    /** Description of isPrepaidPresent for determining if there is prepaid on this machine
     * @return 			Indicates if the prepaid functionality is present
     */
    public boolean isPrepaidPresent() {
        return prepaidPresent;
    }
    
    /** Description of isBillsPresent for determining if there are bills on this machine
     * @return 			Indicates if the bills functionality is present
     */
    public boolean isBillsPresent() {
        return billsPresent;
    }
    
    /** Description of isCoinsPresent for determining if there are coins on this machine
     * @return 			Indicates if the coins functionality is present
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
    
    
    /** Description of isPayPalPresent for determining if there is PayPal on this machine
     * @return 			Indicates if the PayPal functionality is present
     */
    public boolean isPayPalPresent() {
        return payPalPresent;
    }
    
    /** Description of isExactChangeActive if no change will be provided
     * @return 			The state of not providing change due to exact change status
     */
    public boolean isExactChangeActive() {
        return false;
    }
    
    /** Description of isFullOfChange if at least one coin rack is full of coins
     * @return 			The state of at least one rack being full of coins
     */
    public boolean isFullOfChangeActive() {
        return false;
    }
    
    /** Description of getPrepaidController
     * @return 			The PrepaidController for registration
     */
    public PrepaidController getPrepaidController() {
        return prepaidController;
    }
    
    /** Description of getBillsController
     * @return 			The BillsController for registration
     */
    public BanknoteController getBankNoteController() {
        return bankNoteController;
    }
    
    /** Description of getCoinsController
     * @return 			The CoinsController for registration
     */
    public CoinsController getCoinsController() {
        return coinsController;
    }
    
    /** Description of getCoinRackControllers
     * @return 			An array of CoinRackControllers for registration
     */
    public CoinRackController[] getCoinRackControllers(){
        return null;
    }
}