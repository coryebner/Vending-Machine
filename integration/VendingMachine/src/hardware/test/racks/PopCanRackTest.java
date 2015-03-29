package hardware.test.racks;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import hardware.channels.PopCanChannel;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;
import hardware.exceptions.SimulationException;
import hardware.products.PopCan;
import hardware.racks.PopCanRack;
import hardware.test.stub.AbstractPopCanAcceptorStub;
import hardware.test.stub.PopCanRackListenerStub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PopCanRackTest {
    private PopCanRack rack;
    private PopCanRackListenerStub listener;
    private PopCan pop;
    private AbstractPopCanAcceptorStub deliveryChute;

    @Before
    public void setup() {
	rack = new PopCanRack(2);
	listener = new PopCanRackListenerStub();
	rack.register(listener);

	deliveryChute = new AbstractPopCanAcceptorStub();
	rack.connect(new PopCanChannel(deliveryChute));

	pop = new PopCan();

	listener.init();
	deliveryChute.init();
    }

    @After
    public void teardown() {
	rack.deregisterAll();
	rack.connect(null);
	rack = null;
	listener = null;
	pop = null;
	deliveryChute = null;
    }

    @Test
    public void testRackAcceptPop() throws CapacityExceededException,
	    DisabledException {
	listener.expect("popAdded");

	rack.addPop(pop);
	listener.assertProtocol();
    }

    @Test
    public void testRackAcceptPopBecomingFull()
	    throws CapacityExceededException, DisabledException {
	rack.loadWithoutEvents(pop);
	listener.expect("popAdded", "popFull");

	rack.addPop(pop);
	listener.assertProtocol();
    }

    @Test(expected = CapacityExceededException.class)
    public void testRackAcceptPopWhenFull() throws CapacityExceededException,
	    DisabledException {
	rack.loadWithoutEvents(pop, pop);

	try {
	    rack.addPop(pop);
	}
	finally {
	    listener.assertProtocol();
	}
    }

    @Test
    public void testRackAcceptPopDisabled() throws CapacityExceededException,
	    DisabledException {
	rack.loadWithoutEvents(pop);
	rack.setDisabledWithoutEvents(true);

	try {
	    rack.addPop(pop);
	    fail();
	}
	catch(DisabledException e) {}

	listener.assertProtocol();
    }

    @Test
    public void testRackDispenseBecomingEmpty()
	    throws CapacityExceededException, EmptyException, DisabledException {
	rack.loadWithoutEvents(pop);

	deliveryChute.expect("acceptPop");
	listener.expect("popRemoved", "popEmpty");

	rack.dispensePop();
	listener.assertProtocol();
	deliveryChute.assertProtocol();
    }

    @Test
    public void testRackDispenseNotBecomingEmpty()
	    throws CapacityExceededException, EmptyException, DisabledException {
	rack.loadWithoutEvents(pop, pop);

	deliveryChute.expect("acceptPop");
	listener.expect("popRemoved");

	rack.dispensePop();
	listener.assertProtocol();
	deliveryChute.assertProtocol();
    }

    @Test
    public void testRackDispenseDisabled() throws DisabledException,
	    CapacityExceededException, EmptyException {
	rack.loadWithoutEvents(pop);

	listener.expect("disabled");

	assertFalse(rack.isDisabled());
	rack.disable();
	assertTrue(rack.isDisabled());

	listener.assertProtocol();

	try {
	    listener.init();
	    rack.dispensePop();
	    fail();
	}
	catch(DisabledException e) {}
    }

    @Test
    public void testRackDispenseEmpty() throws CapacityExceededException,
	    DisabledException {
	try {
	    rack.dispensePop();
	    fail();
	}
	catch(EmptyException e) {}
    }

    @Test(expected = SimulationException.class)
    public void testRackOverload() {
	assertTrue(rack.getCapacity() == 2);
	rack.loadWithoutEvents(pop, pop, pop);
    }

    @Test(expected = SimulationException.class)
    public void testBadRackSetup1() {
	rack = new PopCanRack(0);
    }

    @Test(expected = SimulationException.class)
    public void testBadRackSetup2() {
	rack = new PopCanRack(-1);
    }

    @Test
    public void testDispenseWithoutSink() throws DisabledException,
	    EmptyException, CapacityExceededException {
	rack.connect(null);
	rack.loadWithoutEvents(pop);
	listener.expect("popRemoved");

	try {
	    rack.dispensePop();
	    fail();
	}
	catch(SimulationException e) {}

	listener.assertProtocol();
    }
}
