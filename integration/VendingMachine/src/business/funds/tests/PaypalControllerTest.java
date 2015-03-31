package business.funds.tests;

import static org.junit.Assert.*;

import java.util.Locale;

import hardware.Hardware;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;
import hardware.funds.Card;
import hardware.funds.CardSlot;
import hardware.funds.CardSlotNotEmptyException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.funds.PrepaidController;
import business.funds.TransactionReturnCode;
import business.funds.VMCurrencies;



public class PaypalControllerTest {
	CardSlot cs;
	Card prepaidCard;
	PrepaidController pc;
	VMCurrencies curr;
	Hardware hw;
	
	@Before
	public void setUp() throws Exception {
		curr = new VMCurrencies(Locale.CANADA);
		hw = new Hardware(null, null, null);
		pc = new PrepaidController(curr);
		cs = hw.getCardSlot();
		//prepaidCard = new Card(Card.CardType.PREPAID, "7373737373", "Defualt Prepaid" , Locale.CANADA, 10000);;
		prepaidCard = new Card(Card.CardType.PREPAID, "7373737373", "Defualt Prepaid" , "", 10000);;
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
			//TODO assertEquals("Expected 10000 balance", prepaidCard.getCardBalance(), pc.getAvailableBalance());
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
	 */
	@Test 
	public void conductTransactionTest() {
		try {
			cs.insertCard(prepaidCard);
			
			assertEquals("Insuffcient funds", TransactionReturnCode.UNSUCCESSFUL, pc.ConductTransaction(9999));
			pc.disablePrepaidController();
			assertEquals("Returns card error when prepaid controller disabled", TransactionReturnCode.CREDITCARDERROR, pc.ConductTransaction(200));
			
		} catch (CardSlotNotEmptyException e) {
			fail("Card slot was not empty when trying to insert new card.");
		} catch (DisabledException e) {
			fail("Hardware disabled.");
		}
		
	}

}
