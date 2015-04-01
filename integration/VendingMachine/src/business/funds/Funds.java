package business.funds;

import hardware.racks.CoinRack;

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
 */
public class Funds {
    
    private boolean prepaidPresent;
    private boolean billsPresent;
    private boolean coinsPresent;
    private boolean payPalPresent;
    private boolean creditCardPresent;
    
    private PrepaidController prepaidController;
    private BanknoteController bankNoteController;
    private CoinsController coinsController;
    
    private VMCurrencies machineCurrencies;
    
    
    
    /*
     CoinRack[] coinRacks, int[] coinRackDenominations,
     int[] coinRackQuantities, int[] productPrices are needed for CoinsController constructor
     
     No arguments needed for BankNotController, PayPal controller. creditCard controller constructors
     
     Only Locale is needed for PrePaid controller constructor
     */
    
    //Public Constructor for Funds
    public Funds(Locale locale, CoinRack[] coinRacks, int[] coinRackDenominations, int[] coinRackQuantities, int[] productPrices, List<PaymentMethods> availablePaymentMethods){
        this.prepaidPresent = this.creditCardPresent = this.payPalPresent = this.coinsPresent = this.prepaidPresent = false;
        
        this.machineCurrencies = new VMCurrencies(locale);
        
        this.prepaidController = new PrepaidController(this.machineCurrencies);
        this.bankNoteController = new BanknoteController();
        this.coinsController =  new CoinsController(coinRacks, coinRackDenominations, coinRackQuantities, productPrices);
        
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
        if(availablePaymentMethods.contains(PaymentMethods.PAYPAL)){
            this.payPalPresent = true;
        }
        if(availablePaymentMethods.contains(PaymentMethods.CREDITCARD)){
            this.creditCardPresent = true;
        }
    }
    
    
    /** Description of ConductTransaction with all available payment methods
     * @param price 	The price in cents of the transaction attempted
     * @return 			The return code based on success of the transaction
     */
    public TransactionReturnCode ConductTransaction(int price){
        return null;
    }
    
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