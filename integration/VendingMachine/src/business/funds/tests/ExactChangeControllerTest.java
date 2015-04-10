package business.funds.tests;

import static org.junit.Assert.*;

import java.util.Vector;

import hardware.racks.CoinRack;
import hardware.racks.ProductRack;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SDK.logger.Logger;
import business.funds.CoinRackController;
import business.funds.ExactChangeController;
import business.selection_delivery.InventoryController;


public class ExactChangeControllerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testCoinReturnValues1() {
		int numProductRacks = 5;
	    String names[] = {"1", "2", "3", "4", "5"};
	    int costs[] = {1,1,1,1,100};
	    int quantity[] = {1,1,1,1,1};

	    ProductRack productRacks[] = new ProductRack[]{new ProductRack(10),new ProductRack(10),new ProductRack(10),new ProductRack(10),new ProductRack(10)};
		
		InventoryController ic = new InventoryController(productRacks, numProductRacks, names, costs, quantity, null, new Logger(), 0);
		
		
		int numRacks = 5;
	    CoinRackController crc[] = new CoinRackController[numRacks];
	    CoinRack cr[] = new CoinRack[numRacks];
	    int rackDenomination[] = {5,10,25,100,200};
	    int amount[] = {0,0,0,0,0};
	    for(int i = 0; i < numRacks; i++) {
	    	cr[i] = new CoinRack(10);
	    	crc[i] = new CoinRackController(cr[i], rackDenomination[i], amount[i]);	
	    }
	    
		ExactChangeController exactChangeController = new ExactChangeController(ic, crc);
		
		Vector<Integer> returnValues = exactChangeController.getReturnValues();
		Vector<Integer> expectedReturnValues = new Vector<Integer>();
		expectedReturnValues.add(199);
		expectedReturnValues.add(99);
		expectedReturnValues.add(24);
		expectedReturnValues.add(9);
		expectedReturnValues.add(4);
		expectedReturnValues.add(100);
		expectedReturnValues.add(5);
		
		assertEquals(expectedReturnValues.size(), returnValues.size());
		for(int i = 0; i < expectedReturnValues.size(); i++) {
			assertTrue(returnValues.contains(expectedReturnValues.get(i)));
		}
		assertTrue(exactChangeController.isExactChangeActive());
		
	}
	
	@Test
	public void testCoinReturnValues2() {
		int numProductRacks = 5;
	    String names[] = {"1", "2", "3", "4", "5"};
	    int costs[] = {50,50,50,50,50};
	    int quantity[] = {1,1,1,1,1};

	    ProductRack productRacks[];
		productRacks = new ProductRack[numProductRacks];
	    for(int i = 0; i < productRacks.length; i++) {
	    	productRacks[i] = new ProductRack(10);
	    }
		
		InventoryController ic = new InventoryController(productRacks, numProductRacks, names, costs, quantity, null, new Logger(), 0);
		
		
		int numRacks = 5;
	    CoinRackController crc[] = new CoinRackController[numRacks];
	    CoinRack cr[] = new CoinRack[numRacks];
	    int rackDenomination[] = {5,10,25,100,200};
	    int amount[] = {0,0,0,0,0};
	    for(int i = 0; i < numRacks; i++) {
	    	cr[i] = new CoinRack(10);
	    	crc[i] = new CoinRackController(cr[i], rackDenomination[i], amount[i]);	
	    }
	    
		ExactChangeController exactChangeController = new ExactChangeController(ic, crc);
		
		
		Vector<Integer> returnValues = exactChangeController.getReturnValues();
		Vector<Integer> expectedReturnValues = new Vector<Integer>();
		expectedReturnValues.add(150);
		expectedReturnValues.add(50);
		expectedReturnValues.add(5);
		
		assertEquals(expectedReturnValues.size(), returnValues.size());
		for(int i = 0; i < expectedReturnValues.size(); i++) {
			assertTrue(returnValues.contains(expectedReturnValues.get(i)));
		}
		assertTrue(exactChangeController.isExactChangeActive());
	
	}
	
	@Test
	public void testCoinReturnValues3() {
		int numProductRacks = 5;
	    String names[] = {"1", "2", "3", "4", "5"};
	    int costs[] = {100,50,175,200,250};
	    int quantity[] = {1,1,1,1,1};

	    ProductRack productRacks[];
		productRacks = new ProductRack[numProductRacks];
	    for(int i = 0; i < productRacks.length; i++) {
	    	productRacks[i] = new ProductRack(10);
	    }
		
		InventoryController ic = new InventoryController(productRacks, numProductRacks, names, costs, quantity, null, new Logger(), 0);
		
		
		int numRacks = 5;
	    CoinRackController crc[] = new CoinRackController[numRacks];
	    CoinRack cr[] = new CoinRack[numRacks];
	    int rackDenomination[] = {5,10,25,100,200};
	    int amount[] = {1,5,0,1,0};
	    for(int i = 0; i < numRacks; i++) {
	    	cr[i] = new CoinRack(10);
	    	crc[i] = new CoinRackController(cr[i], rackDenomination[i], amount[i]);	
	    }
	    
		ExactChangeController exactChangeController = new ExactChangeController(ic, crc);
		
		
		Vector<Integer> returnValues = exactChangeController.getReturnValues();
		Vector<Integer> expectedReturnValues = new Vector<Integer>();
		expectedReturnValues.add(150);
		expectedReturnValues.add(50);
		expectedReturnValues.add(100);
		expectedReturnValues.add(25);
		expectedReturnValues.add(5);

		
		assertEquals(expectedReturnValues.size(), returnValues.size());
		for(int i = 0; i < expectedReturnValues.size(); i++) {
			assertTrue(returnValues.contains(expectedReturnValues.get(i)));
		}
		assertTrue(!exactChangeController.isExactChangeActive());
	
	}
	
	@Test
	public void testCoinReturnValues4() {
		int numProductRacks = 5;
	    String names[] = {"1", "2", "3", "4", "5"};
	    int costs[] = {100,50,175,200,251};
	    int quantity[] = {1,1,1,1,1};

	    ProductRack productRacks[];
		productRacks = new ProductRack[numProductRacks];
	    for(int i = 0; i < productRacks.length; i++) {
	    	productRacks[i] = new ProductRack(10);
	    }
		
		InventoryController ic = new InventoryController(productRacks, numProductRacks, names, costs, quantity, null, new Logger(), 0);
		
		
		int numRacks = 1;
	    CoinRackController crc[] = new CoinRackController[numRacks];
	    CoinRack cr[] = new CoinRack[numRacks];
	    int rackDenomination[] = {5};
	    int amount[] = {1};
	    for(int i = 0; i < numRacks; i++) {
	    	cr[i] = new CoinRack(10);
	    	crc[i] = new CoinRackController(cr[i], rackDenomination[i], amount[i]);	
	    }
	    
		ExactChangeController exactChangeController = new ExactChangeController(ic, crc);
		
		
		Vector<Integer> returnValues = exactChangeController.getReturnValues();
		Vector<Integer> expectedReturnValues = new Vector<Integer>();
		expectedReturnValues.add(4);

		
		assertEquals(expectedReturnValues.size(), returnValues.size());
		for(int i = 0; i < expectedReturnValues.size(); i++) {
			assertTrue(returnValues.contains(expectedReturnValues.get(i)));
		}
		assertTrue(exactChangeController.isExactChangeActive());
	
	}
	
	
	@Test
	public void testCoinReturnValues5() {
		int numProductRacks = 5;
	    String names[] = {"1", "2", "3", "4", "5"};
	    int costs[] = {100,50,175,200,0};
	    int quantity[] = {1,1,1,1,1};

	    ProductRack productRacks[];
		productRacks = new ProductRack[numProductRacks];
	    for(int i = 0; i < productRacks.length; i++) {
	    	productRacks[i] = new ProductRack(10);
	    }
		
		InventoryController ic = new InventoryController(productRacks, numProductRacks, names, costs, quantity, null, new Logger(), 0);
		
		
		int numRacks = 5;
	    CoinRackController crc[] = new CoinRackController[numRacks];
	    CoinRack cr[] = new CoinRack[numRacks];
	    int rackDenomination[] = {5,10,25,100,200};
	    int amount[] = {1,5,0,1,0};
	    for(int i = 0; i < numRacks; i++) {
	    	cr[i] = new CoinRack(10);
	    	crc[i] = new CoinRackController(cr[i], rackDenomination[i], amount[i]);	
	    }
	    
		ExactChangeController exactChangeController = new ExactChangeController(ic, crc);
		
		
		Vector<Integer> returnValues = exactChangeController.getReturnValues();
		Vector<Integer> expectedReturnValues = new Vector<Integer>();
		expectedReturnValues.add(200);
		expectedReturnValues.add(150);
		expectedReturnValues.add(50);
		expectedReturnValues.add(100);
		expectedReturnValues.add(25);
		expectedReturnValues.add(10);
		expectedReturnValues.add(5);

		
		assertEquals(expectedReturnValues.size(), returnValues.size());
		for(int i = 0; i < expectedReturnValues.size(); i++) {
			assertTrue(returnValues.contains(expectedReturnValues.get(i)));
		}
		assertTrue(exactChangeController.isExactChangeActive());
	
	}
	
}
