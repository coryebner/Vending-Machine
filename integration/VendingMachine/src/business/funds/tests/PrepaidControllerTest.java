package business.funds.tests;

import static org.junit.Assert.*;

import java.util.Locale;

import hardware.Hardware;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;
import hardware.funds.Card;
import hardware.funds.Card.CardType;
import hardware.funds.CardSlot;
import hardware.funds.CardSlotNotEmptyException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.funds.PrepaidController;
import business.funds.TransactionReturnCode;
import business.funds.Currency;



public class PrepaidControllerTest {
	int[] coinValues = {5, 10, 25, 100, 200};
	int[] popCosts = {100, 200, 150, 250, 175};
	String[] popNames = {"Coke", "Pepsi", "7up", "Sprite", "Crush"};

	CardSlot cs;

	Card prepaidCard;
	PrepaidController pc;
	Currency curr;
	Hardware hw;

	@Before
	public void setUp() throws Exception {
		curr = new Currency(Locale.CANADA);
		hw = new Hardware(coinValues, popCosts, popNames);
		pc = new PrepaidController(curr);
		cs = hw.getCardSlot();
		cs.register(pc);
		prepaidCard = new Card(Card.CardType.PREPAID, "7373737373", "Defualt Prepaid" , "", "00/0000", Locale.CANADA, 10000);
	}

	@After
	public void tearDown() throws Exception {
		curr = null;
		pc = null;
		cs = null;
		hw = null;
		prepaidCard = null;
	}

	/**
	 * Test getAvailableBalance() method
	 */
	@Test 
	public void getAvailableBalanceTest() {
		try {	
			cs.insertCard(prepaidCard);
			assertEquals("Expected 10000 balance", prepaidCard.getCardBalance(), pc.getAvailableBalance());

		} catch (CardSlotNotEmptyException e) {
			fail("Card slot was not empty when trying to insert new card.");
		} catch (DisabledException e) {
			fail("Hardware disabled.");
		}
	}

	/**
	 * Test isCardInserted() method
	 */
	@Test 
	public void isCardInsertedTest() {
		try {
			assertFalse("No card should be inserted.", pc.isCardInserted());

			cs.insertCard(prepaidCard);
			assertTrue("Card was inserted", pc.isCardInserted());

		} catch (CardSlotNotEmptyException e) {
			fail("Card slot was not empty when trying to insert new card.");
		} catch (DisabledException e) {
			fail("Hardware disabled.");
		}
	}

	/**
	 * Test disablePrepaidController() method
	 */
	@Test 
	public void disablePrepaidControllerTest() {
		assertFalse("Controller initialized as enabled.", pc.isDisabled());
		assertFalse("Controller initialized with empty cars slot.", pc.isCardInserted());

		pc.disablePrepaidController();

		assertTrue("Controller disabled.", pc.isDisabled());
		assertFalse("Controller disabled, no card should be inserted.", pc.isCardInserted());

	}

	/**
	 * Test cardInserted(CardSlot slot) method
	 */
	@Test 
	public void cardInsertedTest() {
		assertFalse("No card should be inserted.", pc.isCardInserted());

		try {
			hw.getCardSlot().insertCard(prepaidCard);
			assertTrue("Card was inserted into card slot.", pc.isCardInserted());

		} catch (CardSlotNotEmptyException e) {
			fail("Card slot was not empty when trying to insert new card.");
		} catch (DisabledException e) {
			fail("Hardware disabled.");
		}
	}

	/**
	 * Test cardEjected(CardSlot slot) method
	 */
	@Test 
	public void cardEjectedTest() {
		assertFalse("No card should be inserted.", pc.isCardInserted());

		try {
			hw.getCardSlot().insertCard(prepaidCard);
			assertTrue("Card was inserted into card slot.", pc.isCardInserted());

			hw.getCardSlot().ejectCard();
			assertFalse("The card was ejected.", pc.isCardInserted());

		} catch (CardSlotNotEmptyException e) {
			fail("Card slot was not empty when trying to insert new card.");
		} catch (DisabledException e) {
			fail("Hardware disabled.");
		} catch (EmptyException e) {
			fail("Card slot should not be empty.");
		}
	}

	/**
	 * Test ConductTransaction(int price) method
	 * Test against a disabled machine and insufficient funds
	 */
	@Test 
	public void conductTransactionTestError() {
		try {
			// Request funds above card balance
			cs.insertCard(prepaidCard);
			assertEquals("Insuffcient funds", TransactionReturnCode.INSUFFICIENTFUNDS, pc.ConductTransaction(99999));
			
			// Request funds against a disable prepaid controller
			pc.disablePrepaidController();
			assertEquals("Returns card error when prepaid controller disabled", TransactionReturnCode.DISABLED, pc.ConductTransaction(200));

		} catch (CardSlotNotEmptyException e) {
			fail("Card slot was not empty when trying to insert new card.");
		} catch (DisabledException e) {
			fail("Hardware disabled.");
		}

	}

	/**
	 * Test ConductTransaction(int price) method
	 * Test against successful and unsuccessful transaction
	 */
	@Test 
	public void conductTransactionTestSuccess() {
		try {
			// Test a successful transaction
			cs.insertCard(prepaidCard);
			assertEquals("Suffcient funds", TransactionReturnCode.SUCCESSFUL, pc.ConductTransaction(8000));
			assertEquals("Balance should be 2000", 2000, pc.getAvailableBalance());
			
			// Test when no prepaid card is inserted.
			cs.ejectCard();
			assertEquals("Returns unsuccessful when prepaid is not inserted", TransactionReturnCode.UNSUCCESSFUL, pc.ConductTransaction(200));

			// Test checks if transaction is possible with unknown card
			cs.insertCard(new Card(CardType.UNKNOWN, "12121212373", "Unknown" , "", "00/0000", Locale.US, 1000));
			assertEquals("Returns unsuccessful when card is not a prepaid card", TransactionReturnCode.UNSUCCESSFUL, pc.ConductTransaction(200));
			
			// Card controller should not accept any card except prepaid.
			assertFalse(pc.isCardInserted());

		} catch (CardSlotNotEmptyException e) {
			fail("Card slot was not empty when trying to insert new card.");
		} catch (DisabledException e) {
			fail("Hardware disabled.");
		} catch (EmptyException e) {
			fail("Should not be empty.");
		}
	}

	/**
	 * Test ConductTransaction(int price) method
	 * Test balance logic
	 */
	@Test 
	public void conductTransactionTestBalance1() {
		try {
			// Test if we can make transaction with full balance of card
			cs.insertCard(prepaidCard);
			assertEquals("Balance should be 10000", 10000, pc.getAvailableBalance());
			assertEquals("Suffcient funds", TransactionReturnCode.SUCCESSFUL, pc.ConductTransaction(10000));
			assertEquals("Balance should be zero", 0, pc.getAvailableBalance());

		} catch (CardSlotNotEmptyException e) {
			fail("Card slot was not empty when trying to insert new card.");
		} catch (DisabledException e) {
			fail("Hardware disabled.");
		} 
	}

	/**
	 * Test ConductTransaction(int price) method
	 * Test balance logic
	 */
	@Test 
	public void conductTransactionTestBalance2() {
		try {
			// exactExchange is equal to the product cost in foreign UK locale
			int exactExchange = (int) Math.ceil(curr.ExchangeFromToCurrency(curr.getVendingMachineLocale(), Locale.UK, 200));
			cs.insertCard(new Card(CardType.PREPAID, "12121212373", "Test prepaid" , "", "00/0000", Locale.UK, exactExchange));

			// getAvailableBalance converts the balance from card locale to machine locale
			assertEquals("Balance should be 200", 200, pc.getAvailableBalance());

			// Request funds from card
			assertEquals("Suffcient funds", TransactionReturnCode.SUCCESSFUL, pc.ConductTransaction(200));

			// The balance should be zero after the transaction.
			assertEquals("Balance should be zero", 0, pc.getAvailableBalance());

		} catch (CardSlotNotEmptyException e) {
			fail("Card slot was not empty when trying to insert new card.");
		} catch (DisabledException e) {
			fail("Hardware disabled.");
		} 
	}

	/**
	 * Test ConductTransaction(int price) method
	 * Test balance logic
	 */
	@Test 
	public void conductTransactionTestBalance3() {
		try {
			// Change default constructors to test currency exchange
			curr = new Currency(Locale.UK);
			pc = new PrepaidController(curr);
			cs.register(pc);

			// exactExchange is equal to the product cost in foreign UK locale
			int exactExchange = (int) Math.ceil(curr.ExchangeFromToCurrency(curr.getVendingMachineLocale(), Locale.US, 200));
			cs.insertCard(new Card(CardType.PREPAID, "12121212373", "Test prepaid" , "", "00/0000", Locale.US, exactExchange));

			// getAvailableBalance converts the balance from card locale to machine locale
			assertEquals("Balance should be 200", 200, pc.getAvailableBalance());

			// Request funds from card
			assertEquals("Suffcient funds", TransactionReturnCode.SUCCESSFUL, pc.ConductTransaction(200));

			// The balance should be zero after the transaction.
			assertEquals("Balance should be zero", 0, pc.getAvailableBalance());

		} catch (CardSlotNotEmptyException e) {
			fail("Card slot was not empty when trying to insert new card.");
		} catch (DisabledException e) {
			fail("Hardware disabled.");
		} 
	}
	
	/**
	 * Test ConductTransaction(int price) method
	 * Test balance logic
	 */
	@Test 
	public void conductTransactionTestBalance4() {
		try {
			// Change default constructors to test currency exchange
			curr = new Currency(Locale.UK);
			pc = new PrepaidController(curr);
			cs.register(pc);

			// Insert card with US locale
			cs.insertCard(new Card(CardType.PREPAID, "12121212373", "Test prepaid" , "", "00/0000", Locale.US, 200));

			// Request funds from card
			assertEquals("Insuffcient funds", TransactionReturnCode.INSUFFICIENTFUNDS, pc.ConductTransaction(200));


		} catch (CardSlotNotEmptyException e) {
			fail("Card slot was not empty when trying to insert new card.");
		} catch (DisabledException e) {
			fail("Hardware disabled.");
		} 
	}
}
