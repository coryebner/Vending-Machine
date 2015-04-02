package business.funds.tests;

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
import business.funds.Funds;
import business.funds.PayPalController;
import business.funds.PaymentMethods;
import business.funds.PrepaidController;
import business.funds.TransactionReturnCode;
import business.selection_delivery.InventoryController;

public class FundsTest {
	
	
	private Mockery context = new Mockery() {{
	    setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	PrepaidController prepaidController;
	CoinsController coinsController;
	BanknoteController bankNoteController ;
	CreditCardController creditCardController;
	PayPalController payPalController;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testFunds() {
		fail("Not yet implemented");
	}

	@Test
	public void testConductTransaction() {
		fail("Not yet implemented");
	}
	
	private Funds setupControllers(boolean prepaid, boolean banknote, boolean coins,
			boolean creditCard, boolean payPal){
		InventoryController inventoryController = null;
		int [] nothing = null;
		CoinRack[] coinRack = null;
		List<PaymentMethods> availablePaymentMethods = new ArrayList<PaymentMethods>();
		Funds funds = new Funds(Locale.CANADA, false, coinRack, nothing, nothing, availablePaymentMethods,inventoryController);
		
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
		Funds funds = setupControllers(true, false, false, false, false);
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
		Funds funds = setupControllers(true, false, false, false, false);
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
		Funds funds = setupControllers(true, false, false, false, false);
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
		Funds funds = setupControllers(false, true, false, false, false);
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
		Funds funds = setupControllers(false, true, false, false, false);
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
		Funds funds = setupControllers(false, true, false, false, false);
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
		Funds funds = setupControllers(false, false, true, false, false);
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
		Funds funds = setupControllers(false, false, true, false, false);
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
		Funds funds = setupControllers(false, false, true, false, false);
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
		Funds funds = setupControllers(false, false, false, true, false);
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
		Funds funds = setupControllers(false, false, false, true, false);
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
		Funds funds = setupControllers(false, false, false, false, true);
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
		Funds funds = setupControllers(false, false, false, false, true);
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
		Funds funds = setupControllers(false, false, false, false, true);
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
	public void testIsPrepaidPresent() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsBillsPresent() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsCoinsPresent() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsCreditCardPresent() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsPayPalPresent() {
		fail("Not yet implemented");
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
