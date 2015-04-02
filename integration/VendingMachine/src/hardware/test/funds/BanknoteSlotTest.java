package hardware.test.funds;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hardware.exceptions.DisabledException;
import hardware.exceptions.SimulationException;
import hardware.funds.*;
import hardware.test.stub.*;
import hardware.acceptors.*;
import hardware.channels.BanknoteChannel;

import org.junit.*;

public class BanknoteSlotTest {
	public BanknoteSlot slot;
	public AbstractBanknoteAcceptorStub banknoteAcceptorStub;
	public BanknoteSlotListenerStub banknoteListener;
	public Banknote banknote;
	public Banknote invalidBanknote;
	Exception caught=null;
	
	@Before
	public void setup(){
		int[] billValues={5,10,20,50,100};
		banknoteListener=new BanknoteSlotListenerStub();
		slot=new BanknoteSlot(billValues);
		slot.register(banknoteListener);
		banknote=new Banknote(5);
		invalidBanknote=new Banknote(3);

	}
	@After
	public void teardown(){
		slot.deregisterAll();
		slot=null;
		banknoteAcceptorStub=null;
		banknoteListener=null;
		banknote=null;
		invalidBanknote=null;
		caught=null;

	}
	@Test
	public void validBanknoteInserted(){
		
		banknoteAcceptorStub=new AbstractBanknoteAcceptorStub(true);
		slot.connect(new BanknoteChannel(banknoteAcceptorStub));
		banknoteListener.expect("validBanknoteInserted");
		try{
			slot.addBanknote(banknote);
		}
		catch(Exception e){
			fail("Exception thown: "+e);
		}
		banknoteListener.assertProtocol();
	}
	@Test
	public void invalidBanknoteInserted(){
		banknoteAcceptorStub=new AbstractBanknoteAcceptorStub(true);
		slot.connect(new BanknoteChannel(banknoteAcceptorStub));
		banknoteListener.expect("banknoteRejected");
		try{
			slot.addBanknote(invalidBanknote);
		}
		catch(Exception e){
			fail("Exception thown: "+e);
		}
		banknoteListener.assertProtocol();
		assertTrue("The banknote should be in the slot", slot.removeBanknote()==invalidBanknote);
		
	}
	@Test
	public void validBanknoteInsertedReceptacleFull(){
		banknoteAcceptorStub=new AbstractBanknoteAcceptorStub(false);
		slot.connect(new BanknoteChannel(banknoteAcceptorStub));
		banknoteListener.expect("banknoteRejected");
		try{
			slot.addBanknote(banknote);
		}
		catch(Exception e){
			fail("Exception thown: "+e);
		}
		banknoteListener.assertProtocol();
		assertTrue("The banknote should be in the slot", slot.removeBanknote()==banknote);
	}
	@Test
	public void invalidBanknoteInsertedReceptacleFull(){
		banknoteAcceptorStub=new AbstractBanknoteAcceptorStub(false);
		slot.connect(new BanknoteChannel(banknoteAcceptorStub));
		banknoteListener.expect("banknoteRejected");
		try{
			slot.addBanknote(invalidBanknote);
		}
		catch(Exception e){
			fail("Exception thown: "+e);
		}
		banknoteListener.assertProtocol();
		assertTrue("The banknote should be in the slot", slot.removeBanknote()==invalidBanknote);
	}
	@Test
	public void validBanknoteInsertedChannelsFull(){
		banknoteAcceptorStub=new AbstractBanknoteAcceptorStub(true);
		slot.connect(new BanknoteChannel(banknoteAcceptorStub));
		banknoteListener.expect("banknoteRejected");
		try{
			slot.addBanknote(invalidBanknote);
		}
		catch(Exception e){
			fail("Exception thrown"+e);
		}
		banknoteListener.assertProtocol();
		try{
			slot.addBanknote(banknote);
		}
		catch(Exception e){
			caught=e;
		}
		assertNotNull(caught);
		assertTrue(caught.getClass()==SimulationException.class);
		
	}
	@Test
	public void invalidBanknoteInsertedChannelsFull(){
		banknoteAcceptorStub=new AbstractBanknoteAcceptorStub(true);
		slot.connect(new BanknoteChannel(banknoteAcceptorStub));
		banknoteListener.expect("banknoteRejected");
		try{
			slot.addBanknote(invalidBanknote);
		}
		catch(Exception e){
			fail("Exception thrown"+e);
		}
		banknoteListener.assertProtocol();
		try{
			slot.addBanknote(invalidBanknote);
		}
		catch(Exception e){
			caught=e;
		}
		assertNotNull(caught);
		assertTrue(caught.getClass()==SimulationException.class);
		
	}
	@Test
	public void isDisabledTest(){
		banknoteAcceptorStub=new AbstractBanknoteAcceptorStub(true);
		slot.connect(new BanknoteChannel(banknoteAcceptorStub));
		slot.disable();
		assertTrue(slot.isDisabled());
		
	}
	@Test
	public void isDisabledAcceptBillTest(){
		banknoteAcceptorStub=new AbstractBanknoteAcceptorStub(true);
		slot.connect(new BanknoteChannel(banknoteAcceptorStub));
		slot.disable();
		assertTrue(slot.isDisabled());
		try{
			slot.addBanknote(banknote);
		}catch(Exception e){
			caught=e;
		}
		assertTrue(caught.getClass()==DisabledException.class);
		
	}

}

