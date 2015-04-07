package business.funds.tests;

import hardware.funds.BanknoteReceptacle;
import hardware.funds.CoinReceptacle;
import hardware.racks.CoinRack;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.lib.legacy.ClassImposteriser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.funds.BanknoteController;
import business.funds.CoinsController;
import business.funds.CreditCardController;
import business.funds.FundsController;
import business.funds.PayPalController;
import business.funds.PaymentMethods;
import business.funds.PrepaidController;
import business.funds.TransactionReturnCode;
import business.selection_delivery.InventoryController;

public class FundsControllerTest {
	
	
	private Mockery context = new Mockery() {{
	    setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	PrepaidController prepaidController;
	CoinsController coinsController;
	BanknoteController bankNoteController ;
	CreditCardController creditCardController;
	PayPalController payPalController;
	
	Locale locale;
	boolean bestEffortChange;
	CoinReceptacle coinReceptacle;
	CoinRack[] coinRacks;
	CoinRack mockedRack;
	int[] coinRackDenominations;
	int[] coinRackQuantities;
	BanknoteReceptacle bnReceptacle;
	int[] banknoteDenominations;
	InventoryController inventoryController;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		locale = null;
		bestEffortChange = false;
		coinReceptacle = null;
		coinRacks = null;
		mockedRack = null;
		coinRackDenominations = null;
		coinRackQuantities = null;
		bnReceptacle = null;
		banknoteDenominations= null;
		inventoryController = null;
	}


	@Test
	public void testFunds() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testFundsConstructorOnlyPrepaid() {
		List<PaymentMethods> availablePaymentMethods = new ArrayList<PaymentMethods>();
		availablePaymentMethods.add(PaymentMethods.PREPAID);
		FundsController funds = setupConstructorCall(availablePaymentMethods);
		
		assertNotNull(funds.getPrepaidController());
		assertNull(funds.getCoinsController());
		assertNull(funds.getCoinRackControllers());
		assertNull(funds.getCoinStorageBinTracker());
		assertNull(funds.getBankNoteController());
		assertNull(funds.getBankNoteStorageBinTracker());
		assertNull(funds.getCreditCardController());
		assertNull(funds.getPayPalController());
	}

	@Test
	public void testFundsConstructorPrepaidBills() {
		List<PaymentMethods> availablePaymentMethods = new ArrayList<PaymentMethods>();
		availablePaymentMethods.add(PaymentMethods.PREPAID);
		availablePaymentMethods.add(PaymentMethods.BILLS);
		FundsController funds = setupConstructorCall(availablePaymentMethods);
		
		assertNotNull(funds.getPrepaidController());
		assertNull(funds.getCoinsController());
		assertNull(funds.getCoinRackControllers());
		assertNull(funds.getCoinStorageBinTracker());
		assertNotNull(funds.getBankNoteController());
		assertNotNull(funds.getBankNoteStorageBinTracker());
		assertNull(funds.getCreditCardController());
		assertNull(funds.getPayPalController());
	}
	
	@Test
	public void testFundsConstructorPrepaidBillsCoins() {
		List<PaymentMethods> availablePaymentMethods = new ArrayList<PaymentMethods>();
		availablePaymentMethods.add(PaymentMethods.PREPAID);
		availablePaymentMethods.add(PaymentMethods.BILLS);
		availablePaymentMethods.add(PaymentMethods.COINS);

		FundsController funds = setupConstructorCall(availablePaymentMethods);
		
		assertNotNull(funds.getPrepaidController());
		assertNotNull(funds.getCoinsController());
		assertNotNull(funds.getCoinRackControllers());
		assertNotNull(funds.getCoinStorageBinTracker());
		assertNotNull(funds.getBankNoteController());
		assertNotNull(funds.getBankNoteStorageBinTracker());
		assertNull(funds.getCreditCardController());
		assertNull(funds.getPayPalController());
	}
	
	@Test
	public void testConductTransaction() {
		fail("Not yet implemented");
	}
	
	private FundsController setupConstructorCall(List<PaymentMethods> availablePaymentMethods){
		setupOnlyControllerMocks();
		locale = Locale.CANADA;
		bestEffortChange = false;
		coinReceptacle = context.mock(CoinReceptacle.class);
		coinRacks = new CoinRack[5];
		mockedRack = context.mock(CoinRack.class);
		for(int i = 0; i< 5; i++){
			coinRacks[i] = mockedRack;
		}
		coinRackDenominations = new int[]{5,10,25,100,200};
		coinRackQuantities = new int[]{5,5,5,5,5};
		bnReceptacle = context.mock(BanknoteReceptacle.class);
		banknoteDenominations = new int[]{5,10,20};
		inventoryController = context.mock(InventoryController.class);
		
		context.checking(new Expectations(){
			{
			allowing(mockedRack).length;
			will(returnValue(100));
			oneOf(prepaidController).ConductTransaction(100);
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		
		FundsController funds = new FundsController(locale, bestEffortChange, coinReceptacle, coinRacks,
				coinRackDenominations, coinRackQuantities, bnReceptacle, banknoteDenominations,
				availablePaymentMethods,
				inventoryController);
		return funds;
	}
	
	private void setupOnlyControllerMocks(){
		prepaidController = context.mock(PrepaidController.class);
		coinsController = context.mock(CoinsController.class);
		bankNoteController = context.mock(BanknoteController.class) ;
		creditCardController = context.mock(CreditCardController.class);
		payPalController = context.mock(PayPalController.class);
	}
	
	private FundsController setupControllers(boolean prepaid, boolean banknote, boolean coins,
			boolean creditCard, boolean payPal){
		InventoryController inventoryController = null;
		int [] nothing = null;
		CoinRack[] coinRack = null;
		List<PaymentMethods> availablePaymentMethods = new ArrayList<PaymentMethods>();
		FundsController funds = new FundsController(Locale.CANADA, false, null, coinRack, nothing, nothing, null, null, availablePaymentMethods,inventoryController);
		
		prepaidController = context.mock(PrepaidController.class);
		coinsController = context.mock(CoinsController.class);
		bankNoteController = context.mock(BanknoteController.class) ;
		creditCardController = context.mock(CreditCardController.class);
		payPalController = context.mock(PayPalController.class);
		
		if(prepaid){
			funds.ONLY_FOR_TESTING_setControllerState(prepaid, prepaidController, PrepaidController.class);
		}
		if(coins){
			funds.ONLY_FOR_TESTING_setControllerState(coins, coinsController, CoinsController.class);
		}
		if(banknote){
			funds.ONLY_FOR_TESTING_setControllerState(banknote, bankNoteController, BanknoteController.class);
		}
		if(creditCard){
			funds.ONLY_FOR_TESTING_setControllerState(creditCard, creditCardController, CreditCardController.class);
		}
		if(payPal){
			funds.ONLY_FOR_TESTING_setControllerState(payPal, payPalController, PayPalController.class);
		}
		
		return funds;
	}
	
	/**
	 * ONLY PREPAID TESTS
	 */
	
	@Test
	public void testConductTransactionNotEnoughPrepaid_OnlyPrepaid() {
		FundsController funds = setupControllers(true, false, false, false, false);
		context.checking(new Expectations(){
			{
			atLeast(1).of(prepaidController).getAvailableBalance();
			will(returnValue(25));
			}
		});
		TransactionReturnCode returnCode = funds.ConductTransaction(100);
		assertEquals(TransactionReturnCode.INSUFFICIENTFUNDS,returnCode);

	}
	
	@Test
	public void testConductTransactiontExactPrepaid_OnlyPrepaid() {
		FundsController funds = setupControllers(true, false, false, false, false);
		context.checking(new Expectations(){
			{
			atLeast(1).of(prepaidController).getAvailableBalance();
			will(returnValue(100));
			oneOf(prepaidController).ConductTransaction(100);
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		TransactionReturnCode returnCode = funds.ConductTransaction(100);
		assertEquals(TransactionReturnCode.SUCCESSFUL,returnCode);

	}
	
	@Test
	public void testConductTransactiontOverPrepaid_OnlyPrepaid() {
		FundsController funds = setupControllers(true, false, false, false, false);
		context.checking(new Expectations(){
			{
			atLeast(1).of(prepaidController).getAvailableBalance();
			will(returnValue(110));
			oneOf(prepaidController).ConductTransaction(100);
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		TransactionReturnCode returnCode = funds.ConductTransaction(100);
		assertEquals(TransactionReturnCode.SUCCESSFUL,returnCode);

	}
	
	/**
	 * ONLY Bills TESTS
	 */
	
	@Test
	public void testConductTransactionNotEnoughBills_OnlyBills() {
		FundsController funds = setupControllers(false, true, false, false, false);
		context.checking(new Expectations(){
			{
			atLeast(1).of(bankNoteController).getAvailableBalance();
			will(returnValue(500));
			}
		});
		TransactionReturnCode returnCode = funds.ConductTransaction(600);
		assertEquals(TransactionReturnCode.INSUFFICIENTFUNDS,returnCode);

	}
	
	@Test
	public void testConductTransactiontExactBills_OnlyBills() {
		FundsController funds = setupControllers(false, true, false, false, false);
		context.checking(new Expectations(){
			{
			atLeast(1).of(bankNoteController).getAvailableBalance();
			will(returnValue(500));
			oneOf(bankNoteController).ConductTransaction(500);
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		TransactionReturnCode returnCode = funds.ConductTransaction(500);
		assertEquals(TransactionReturnCode.SUCCESSFUL,returnCode);

	}
	
	@Test
	public void testConductTransactiontOverBankNote_OnlyBankNote() {
		FundsController funds = setupControllers(false, true, false, false, false);
		context.checking(new Expectations(){
			{
			atLeast(1).of(bankNoteController).getAvailableBalance();
			will(returnValue(500));
			oneOf(bankNoteController).ConductTransaction(100);
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		TransactionReturnCode returnCode = funds.ConductTransaction(100);
		assertEquals(TransactionReturnCode.SUCCESSFUL,returnCode);

	}
	
	
	/**
	 * ONLY Coins TESTS
	 */
	
	@Test
	public void testConductTransactionNotEnoughCoins_OnlyCoinss() {
		FundsController funds = setupControllers(false, false, true, false, false);
		context.checking(new Expectations(){
			{
			atLeast(1).of(coinsController).getAvailableBalance();
			will(returnValue(25));
			}
		});
		TransactionReturnCode returnCode = funds.ConductTransaction(100);
		assertEquals(TransactionReturnCode.INSUFFICIENTFUNDS,returnCode);

	}
	
	
	@Test
	public void testConductTransactiontExactCoins_OnlyCoins() {
		FundsController funds = setupControllers(false, false, true, false, false);
		context.checking(new Expectations(){
			{
			atLeast(1).of(coinsController).getAvailableBalance();
			will(returnValue(100));
			oneOf(coinsController).ConductTransaction(100);
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		TransactionReturnCode returnCode = funds.ConductTransaction(100);
		assertEquals(TransactionReturnCode.SUCCESSFUL,returnCode);

	}
	
	@Test
	public void testConductTransactiontOverCoins_OnlyCoins() {
		FundsController funds = setupControllers(false, false, true, false, false);
		context.checking(new Expectations(){
			{
			atLeast(1).of(coinsController).getAvailableBalance();
			will(returnValue(500));
			oneOf(coinsController).ConductTransaction(100);
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			oneOf(coinsController).provideChange(400);
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		TransactionReturnCode returnCode = funds.ConductTransaction(100);
		assertEquals(TransactionReturnCode.SUCCESSFUL,returnCode);

	}
	
	/**
	 * CreditCard Only TESTS
	 */
	
	@Test
	public void testConductTransactionNotEnoughCreditCard_OnlyCreditCard() {
		FundsController funds = setupControllers(false, false, false, true, false);
		context.checking(new Expectations(){
			{
			atLeast(1).of(creditCardController).ConductTransaction(100);
			will(returnValue(TransactionReturnCode.UNSUCCESSFUL));
			}
		});
		TransactionReturnCode returnCode = funds.ConductTransaction(100);
		assertEquals(TransactionReturnCode.UNSUCCESSFUL,returnCode);

	}
	
	@Test
	public void testConductTransactiontEnoughCreditCard_OnlyCreditCard() {
		FundsController funds = setupControllers(false, false, false, true, false);
		context.checking(new Expectations(){
			{
			atLeast(1).of(creditCardController).ConductTransaction(100);
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		TransactionReturnCode returnCode = funds.ConductTransaction(100);
		assertEquals(TransactionReturnCode.SUCCESSFUL,returnCode);
	}
	
	/**
	 * CreditCard Only TESTS
	 */
	
	@Test
	public void testConductTransactionNotEnoughPayPal_OnlyPayPal() {
		FundsController funds = setupControllers(false, false, false, false, true);
		context.checking(new Expectations(){
			{
			atLeast(1).of(payPalController).ConductTransaction(100);
			will(returnValue(TransactionReturnCode.UNSUCCESSFUL));
			}
		});
		TransactionReturnCode returnCode = funds.ConductTransaction(100);
		assertEquals(TransactionReturnCode.UNSUCCESSFUL,returnCode);

	}
	
	@Test
	public void testConductTransactioTimeOutPayPal_OnlyPayPal() {
		FundsController funds = setupControllers(false, false, false, false, true);
		context.checking(new Expectations(){
			{
			atLeast(1).of(payPalController).ConductTransaction(100);
			will(returnValue(TransactionReturnCode.TIMEOUT));
			}
		});
		TransactionReturnCode returnCode = funds.ConductTransaction(100);
		assertEquals(TransactionReturnCode.TIMEOUT,returnCode);

	}
	
	@Test
	public void testConductTransactiontEnoughPayPal_OnlyPayPal() {
		FundsController funds = setupControllers(false, false, false, false, true);
		context.checking(new Expectations(){
			{
			atLeast(1).of(payPalController).ConductTransaction(100);
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		TransactionReturnCode returnCode = funds.ConductTransaction(100);
		assertEquals(TransactionReturnCode.SUCCESSFUL,returnCode);
	}
	
	@Test
	public void testGetPaymentMethodUsedAndPaymentStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsPrepaidPresentTrue() {
		FundsController funds = setupControllers(true, false, false, false, false);
		assertTrue(funds.isPrepaidPresent());
	}

	@Test
	public void testIsPrepaidPresentFalse() {
		FundsController funds = setupControllers(false, false, false, false, false);
		assertFalse(funds.isPrepaidPresent());
	}
	
	@Test
	public void testIsBillsPresentTrue() {
		FundsController funds = setupControllers(false, true, false, false, false);
		assertTrue(funds.isBillsPresent());
	}
	
	@Test
	public void testIsBillsPresentFalse() {
		FundsController funds = setupControllers(false, false, false, false, false);
		assertFalse(funds.isBillsPresent());
	}

	@Test
	public void testIsCoinsPresentTrue() {
		FundsController funds = setupControllers(false, false, true, false, false);
		assertTrue(funds.isCoinsPresent());
	}
	
	@Test
	public void testIsCoinsPresentFalse() {
		FundsController funds = setupControllers(false, false, false, false, false);
		assertFalse(funds.isCoinsPresent());
	}

	@Test
	public void testIsCreditCardPresentTrue() {
		FundsController funds = setupControllers(false, false, false, true, false);
		assertTrue(funds.isCreditCardPresent());
	}
	
	@Test
	public void testIsCreditCardPresentFalse() {
		FundsController funds = setupControllers(false, false, false, false, false);
		assertFalse(funds.isCreditCardPresent());
	}

	@Test
	public void testIsPayPalPresentTrue() {
		FundsController funds = setupControllers(false, false, false, false, true);
		assertTrue(funds.isPayPalPresent());
	}
	
	@Test
	public void testIsPayPalPresentFalse() {
		FundsController funds = setupControllers(false, false, false, false, false);
		assertFalse(funds.isPayPalPresent());
	}

	@Test
	public void testIsExactChangeActive() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsFullOfChangeActive() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPrepaidController() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBankNoteController() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCoinsController() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCoinRackControllers() {
		fail("Not yet implemented");
	}

}
