package hardware.test.funds;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.SimulationException;
import hardware.funds.*;
import hardware.test.stub.*;

//UNFINISHED
public class BanknoteReceptacleTest {
	public Banknote banknote;
	public Banknote invalidBanknote;
	public BanknoteReceptacle receptacle;
	public Exception caught;
	public BanknoteReceptacleListenerStub listener;
	
	@Before
	public void setUp() throws Exception {
		banknote=new Banknote(5);
		invalidBanknote= new Banknote(3);
		receptacle=new BanknoteReceptacle(2);
		listener=new BanknoteReceptacleListenerStub();
		receptacle.register(listener);
		
	}

	@After
	public void tearDown() throws Exception {
		receptacle.deregisterAll();
		listener=null;
		banknote=null;
		invalidBanknote=null;
		receptacle=null;
	}

	@Test
	public void invalidCapacityTest() {
		try{
			receptacle=new BanknoteReceptacle(-1);
		}catch(Exception e){
			caught=e;
		}
		assertTrue("The exception should be of type SimulationException", caught.getClass()==SimulationException.class);	
	}
	@Test
	public void insertBanknoteTest(){
		listener.expect("banknoteAdded");
		try{
			receptacle.acceptBanknote(banknote);
		}catch(Exception e){
			caught=e;
		}
		assertNull(caught);
		listener.assertProtocol();
	}
	@Test
	public void insertInvalidBanknoteTest(){
		listener.expect("banknoteAdded");
		try{
			receptacle.acceptBanknote(invalidBanknote);
		}catch(Exception e){
			caught=e;
		}
		assertNull(caught);
		listener.assertProtocol();
	}
	@Test
	public void hasSpaceEmptyTest(){
		assertTrue(receptacle.hasSpace()==true);
	}
	@Test
	public void hasSpaceTest(){
		listener.expect("banknoteAdded");
		try{
			receptacle.acceptBanknote(banknote);
		}catch(Exception e){
			caught=e;
		}
		assertNull(caught);
		listener.assertProtocol();
		assertTrue(receptacle.hasSpace()==true);
	}
	@Test
	public void hasSpaceFullTest(){
		listener.expect("banknoteAdded");
		try{
			receptacle.acceptBanknote(new Banknote(5));
		}catch(Exception e){
			caught=e;
		}
		assertNull(caught);
		listener.assertProtocol();
		listener.expect("banknoteAdded");
		try{
			receptacle.acceptBanknote(new Banknote(5));
		}catch(Exception e){
			caught=e;
		}
		assertNull(caught);
		listener.assertProtocol();
		assertTrue(receptacle.hasSpace()==false);
	}
	
    @Test
    public void testBanknoteStoreBecomingFull() throws CapacityExceededException, DisabledException {
	receptacle.loadWithoutEvents(banknote);
	listener.expect("banknoteAdded", "banknoteFull");

	assertTrue(receptacle.hasSpace());
	receptacle.acceptBanknote(banknote);
	assertFalse(receptacle.hasSpace());

	listener.assertProtocol();
    }
    
    @Test
    public void testBanknoteReceptacleStoreFull() throws DisabledException {
	receptacle.loadWithoutEvents(banknote, banknote);

	try {
	    receptacle.acceptBanknote(banknote);
	    fail();
	}
	catch(CapacityExceededException e) {
	}
	listener.assertProtocol();
    }
    
	@Test
	public void isDisabledTest(){
		receptacle.disable();
		assertTrue(receptacle.isDisabled());
	}
	@Test
	public void isDisabledAcceptBanknoteTest(){
		receptacle.disable();
		assertTrue(receptacle.isDisabled());
		try{
			receptacle.acceptBanknote(banknote);
		}catch(Exception e){
			caught=e;
		}
		assertTrue(caught.getClass()==DisabledException.class);
	}

}
