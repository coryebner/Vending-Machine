package business.funds.tests;

import hardware.exceptions.DisabledException;
import hardware.funds.Banknote;
import hardware.funds.BanknoteReceptacle;
import hardware.funds.BanknoteSlot;
import hardware.funds.Card;
import hardware.funds.CardSlot;
import hardware.funds.CardSlotNotEmptyException;
import hardware.funds.Coin;
import hardware.funds.CoinReceptacle;
import hardware.funds.CoinSlot;
import hardware.racks.CoinRack;
import hardware.racks.ProductRack;
import hardware.simulators.VMRUS_COM_C_M;
import hardware.ui.PushButton;
import SDK.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.paypal.api.payments.BillingInfo;

import business.config.Configuration;
import business.config.ConfigurationListener;
import business.funds.BanknoteController;
import business.funds.CoinRackController;
import business.funds.CoinsController;
import business.funds.CreditCardController;
import business.funds.Currency;
import business.funds.FundsController;
import business.funds.PayPalController;
import business.funds.PaymentMethods;
import business.funds.PrepaidController;
import business.funds.TransactionReturnCode;
import business.selection_delivery.InventoryController;

public class FundsIntegrationTests {

	private Mockery context = new Mockery() {{
		setImposteriser(ClassImposteriser.INSTANCE);
	}};

	int[] popCosts;
	String[] popNames;
	
	Configuration config;

	FundsController fundsController;
	PrepaidController prepaidController;
	CoinsController coinsController;
	BanknoteController bankNoteController ;
	CreditCardController creditCardController;
	PayPalController payPalController;

	Locale locale;
	Currency currency;
	boolean bestEffortChange;
	
	CardSlot cardSlot;
	CoinSlot coinSlot;
	CoinReceptacle coinReceptacle;
	CoinReceptacle overflowCoinReceptacle;
	CoinRack[] coinRacks;
	CoinRack mockedRack;
	int[] coinRackDenominations;
	int[] coinRackQuantities;
	
	BanknoteSlot banknoteSlot;
	BanknoteReceptacle bnReceptacle;
	BanknoteReceptacle tempbnReceptacle;
	int[] banknoteDenominations;
	
	InventoryController inventoryController;
	List<PaymentMethods> availablePaymentMethods;
	Logger logger;
	
	VMRUS_COM_C_M hw;

	int id = 10;
	PushButton returnButton;
	
	@Before
	public void setUp() throws Exception {
		banknoteDenominations = new int[] {5,10,20};
		coinRackDenominations = new int[]{5, 10, 25, 100, 200};
		coinRackQuantities = new int[]{5, 5, 5, 5, 5};
		popCosts = new int []{100, 200, 150, 250, 175};
		popNames = new String[]{"Coke", "Pepsi", "7up", "Sprite", "Crush"};
		bestEffortChange = true;
		
		hw = new VMRUS_COM_C_M(Locale.CANADA, coinRackDenominations, banknoteDenominations);
		
		locale = hw.getLocale();
		currency = new Currency(locale);
		
		coinReceptacle = hw.getCoinReceptacle();
		coinSlot = hw.getCoinSlot();
		banknoteSlot = hw.getBanknoteSlot();
		coinRacks = new CoinRack[hw.getNumberOfCoinRacks()];
		for(int i=0; i < hw.getNumberOfCoinRacks(); i++) {
			coinRacks[i] = hw.getCoinRack(i);
		}
		config = new Configuration();
		bnReceptacle = hw.getBanknoteReceptacle();
		tempbnReceptacle = hw.getBanknoteReceptacle();
		
		overflowCoinReceptacle = new CoinReceptacle(10);
		
		//prepaidController = new PrepaidController(currency);
		cardSlot = hw.getCardSlot();
		//cardSlot.register(prepaidController);
		
		availablePaymentMethods = new ArrayList<PaymentMethods>();
		availablePaymentMethods.add(PaymentMethods.PREPAID);
		availablePaymentMethods.add(PaymentMethods.BILLS);
		availablePaymentMethods.add(PaymentMethods.COINS);
		availablePaymentMethods.add(PaymentMethods.CREDITCARD);
		availablePaymentMethods.add(PaymentMethods.PAYPAL);	
		
		inventoryController = new InventoryController();
		logger = new Logger();
		returnButton = new PushButton();
		ProductRack[] productRacks = new ProductRack[hw.getNumberOfProductRacks()];
		for(int i = 0; i < hw.getNumberOfProductRacks(); i++){
			productRacks[i] = hw.getProductRack(i);
		}
		fundsController = new FundsController(locale, availablePaymentMethods, bestEffortChange, coinRackDenominations, coinSlot, coinReceptacle, 0, overflowCoinReceptacle, null, coinRacks, coinRackQuantities, banknoteSlot, tempbnReceptacle, bnReceptacle, null, 0, null, inventoryController, logger);

		// PrpaidController Registration
		if(fundsController.isPrepaidPresent()){
			cardSlot.register(fundsController.getPrepaidController());
		}
		// COINS registration
		if(fundsController.isCoinsPresent()){
			//Exact Change Controller Registration
			//config.registerConfigListener(fundsController.getExactChangeController());
			for(ProductRack productRack: productRacks){
				productRack.register(fundsController.getExactChangeController());
			}
			for(CoinRack coinRack: coinRacks){
				coinRack.register(fundsController.getExactChangeController());
			}

			//Overflow Coins Registration
			/////////////********************* FOR OVERFLOW BIN ***************************////////
			overflowCoinReceptacle.register(fundsController.getCoinStorageBinTracker());

			//COIN CONTROLLER REGISTRATION //////////////////FOR COIN RECEPTACLE/////////////////
			coinReceptacle.register(fundsController.getCoinsController());
			returnButton.register(fundsController.getCoinsController());


			// COIN RACKS REGISTRATION
			CoinRackController[] coinRackControllers = fundsController.getCoinRackControllers();
			for(int i = 0; i < coinRackControllers.length; i++){
				coinRacks[i].register(coinRackControllers[i]);
			}
		}
		if(fundsController.isCreditCardPresent()){
			cardSlot.register(fundsController.getCreditCardController());

		}
		if(fundsController.isBillsPresent()){
			tempbnReceptacle.register(fundsController.getBankNoteController());
			returnButton.register(fundsController.getBankNoteController());
			bnReceptacle.register(fundsController.getBankNoteStorageBinTracker());
		}
		
		
		
		
		
		
		
		
	}

	@After
	public void tearDown() throws Exception {
		popCosts = null;
		popNames = null;
		fundsController = null;
		prepaidController = null;
		coinsController = null;
		bankNoteController = null;
		currency = null;
		creditCardController = null;
		payPalController = null;
		locale = null;
		bestEffortChange = false;;
		coinReceptacle = null;
		overflowCoinReceptacle = null;
		coinRacks = null;
		cardSlot = null;
		mockedRack = null;
		coinRackDenominations = null;
		coinRackQuantities = null;
		banknoteSlot = null;
		bnReceptacle = null;
		tempbnReceptacle = null;
		banknoteDenominations = null;
		inventoryController = null;
		availablePaymentMethods = null;
		coinSlot = null;
		logger = null;
		hw = null;
	}

	/**
	 * ONLY PREPAID TESTS
	 */

	@Test
	public void testTEMPORARYTEST_PREPAID() {
		try {
			cardSlot.insertCard(new Card(Card.CardType.PREPAID, "7373737373", "Defualt Prepaid" , "", "00/0000", Locale.CANADA, 10000));
		} catch (CardSlotNotEmptyException e) {
			fail();
		} catch (DisabledException e) {
			fail();
		}
		assertEquals(TransactionReturnCode.SUCCESSFUL, fundsController.ConductTransaction(0, 200));
	}
	
	@Test
	public void testPrepaidandCoinsTransactionSufficient(){
		try {
			cardSlot.insertCard(new Card(Card.CardType.PREPAID, "7373737373", "Defualt Prepaid" , "", "00/0000", Locale.CANADA, 50));
		} catch (CardSlotNotEmptyException | DisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			coinSlot.addCoin(new Coin(100));
			coinSlot.addCoin(new Coin(25));
		} catch (DisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(TransactionReturnCode.SUCCESSFUL, fundsController.ConductTransaction(0, 175));
	}
	
	@Test
	public void testPrepaidandCoinsTransactionInsufficient(){
		try {
			cardSlot.insertCard(new Card(Card.CardType.PREPAID, "7373737373", "Defualt Prepaid" , "", "00/0000", Locale.CANADA, 50));
		} catch (CardSlotNotEmptyException | DisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			coinSlot.addCoin(new Coin(100));
		} catch (DisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(TransactionReturnCode.INSUFFICIENTFUNDS, fundsController.ConductTransaction(0, 175));
	}
	
	@Test
	public void testPrepaidandBillsTransactionSufficient() throws Exception{
		try {
			cardSlot.insertCard(new Card(Card.CardType.PREPAID, "7373737373", "Defualt Prepaid" , "", "00/0000", Locale.CANADA, 50));
		} catch (CardSlotNotEmptyException | DisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			banknoteSlot.addBanknote(new Banknote(5));
		} catch (DisabledException e) {
			
		}
		assertEquals(TransactionReturnCode.SUCCESSFUL, fundsController.ConductTransaction(0, 175));
	}
	
}
