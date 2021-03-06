package hardware.test.funds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Locale;

import hardware.exceptions.SimulationException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;
import hardware.funds.Card;
import hardware.funds.CardSlot;
import hardware.funds.CardSlotNotEmptyException;
import hardware.funds.Card.CardType;
import hardware.test.stub.CardSlotListenerStub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CardSlotTest {
    private CardSlot slot;
    private CardSlotListenerStub listener;
    private Card card;
    private final int MAX_AMOUNT = 100;

    @Before
    public void setup() {
	slot = new CardSlot();
	listener = new CardSlotListenerStub();
	card = new Card(Card.CardType.PREPAID, "123", "JoeyJoey", "1234", "06/2018", Locale.CANADA, MAX_AMOUNT);

	slot.register(listener);

	listener.init();
    }

    @After
    public void teardown() {
	slot.deregisterAll();
	slot = null;
	listener = null;
	card = null;
    }

    @Test
    public void testInsertCard() throws CardSlotNotEmptyException,
	    DisabledException {
	listener.expect("cardInserted");

	slot.insertCard(card);
	listener.assertProtocol();
    }

    @Test
    public void testInsertCardDisabled() throws CardSlotNotEmptyException,
	    DisabledException {
	slot.setDisabledWithoutEvents(true);

	try {
	    slot.insertCard(card);
	    fail();
	}
	catch(DisabledException e) {}
	listener.assertProtocol();
    }

    @Test
    public void testInsertCardNotEmpty() throws CardSlotNotEmptyException,
	    DisabledException {
	slot.loadWithoutEvents(card);

	try {
	    slot.insertCard(card);
	    fail();
	}
	catch(CardSlotNotEmptyException e) {}
	listener.assertProtocol();
    }

    @Test
    public void testReadVCardInformation() throws EmptyException {
	slot.loadWithoutEvents(card);

	Card loadedCard = slot.readCardData();
	assertTrue(loadedCard.getType() == CardType.PREPAID);
	assertFalse(loadedCard.getType() == CardType.UNKNOWN);
	assertEquals(loadedCard.getNumber(), "123");
	assertEquals(loadedCard.getName(), "JoeyJoey");
	assertTrue(loadedCard.checkPin("1234"));
	assertFalse(loadedCard.checkPin("123"));
	assertFalse(loadedCard.checkPin("12345"));
	
	assertFalse(loadedCard.requestFunds(MAX_AMOUNT + 1, null));

	listener.assertProtocol();
    }

    @Test
    public void testEjectCard() throws CardSlotNotEmptyException,
	    EmptyException, DisabledException {
	slot.loadWithoutEvents(card);

	listener.expect("cardEjected");
	slot.ejectCard();
	listener.assertProtocol();
    }

    @Test
    public void testEjectCardEmpty() throws CardSlotNotEmptyException,
	    DisabledException {
	try {
	    slot.ejectCard();
	    fail();
	}
	catch(EmptyException e) {}
	listener.assertProtocol();
    }

    @Test
    public void testEjectCardDisabled() throws EmptyException {
	slot.setDisabledWithoutEvents(true);

	try {
	    slot.ejectCard();
	    fail();
	}
	catch(DisabledException e) {}

	listener.assertProtocol();
    }

    @Test
    public void testReadCard() throws CardSlotNotEmptyException,
	    EmptyException, DisabledException {
	listener.expect("cardInserted");
	slot.insertCard(card);
	assertTrue(slot.readCardData() == card);
	listener.assertProtocol();
    }

    @Test
    public void testReadCardDisabled() throws CardSlotNotEmptyException,
	    EmptyException {
	slot.loadWithoutEvents(card);
	slot.setDisabledWithoutEvents(true);
	
	assertTrue(slot.readCardData() == card);
	
	listener.assertProtocol();
    }

    @Test
    public void testReadCardEmpty() throws CardSlotNotEmptyException {
	try {
	    slot.readCardData();
	    fail();
	}
	catch(EmptyException e) {}
	listener.assertProtocol();
    }

    @Test
    public void testOtherCardData() {
	// To get full coverage, generated CardType methods have to be called

	CardType[] types = Card.CardType.values();
	assertTrue(types.length == 4);

	assertTrue(Card.CardType.valueOf("PREPAID") == card.getType());
	try {
	    Card.CardType.valueOf("visa");
	    fail();
	}
	catch(Exception e) {}

	assertTrue(Card.CardType.valueOf(Card.CardType.class, "PREPAID") == card.getType());
    }
    
    @Test(expected = SimulationException.class)
    public void testBadSetup1() {
	card = new Card(null, "123", "JoeyJoey", "1234", "06/2018", Locale.CANADA, MAX_AMOUNT);
    }
    
    @Test(expected = SimulationException.class)
    public void testBadSetup2() {
	card = new Card(Card.CardType.PREPAID, null, "JoeyJoey", "1234", "06/2018", Locale.CANADA, MAX_AMOUNT);
    }
    
    @Test(expected = SimulationException.class)
    public void testBadSetup3() {
	card = new Card(Card.CardType.PREPAID, "123", null, "1234", "06/2018", Locale.CANADA, MAX_AMOUNT);
    }

    @Test(expected = SimulationException.class)
    public void testBadSetup4() {
	card = new Card(Card.CardType.PREPAID, "123", "JoeyJoey", "1234", "ab/2018", Locale.CANADA, MAX_AMOUNT);
    }
}
