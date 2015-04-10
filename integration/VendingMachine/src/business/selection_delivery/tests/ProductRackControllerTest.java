package business.selection_delivery.tests;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hardware.racks.*;
import SDK.logger.Logger;
import SDK.rifffish.Machine;
import SDK.rifffish.Rifffish;
import business.selection_delivery.ProductRackController;

public class ProductRackControllerTest {

	static ProductRackController rackManager;
	
	static int capacity;
	
	static ProductRack rack;
	static String name;
	static int cost;
	static int quantity;
	static int productID;
	
	/**
	 * This section of stuff is for resetting conditions between tests.
	 */
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{//Runs at beginning of class. Initialize constructs to null.
		rackManager = null;
		rack = null;
		name = null;
		cost = 0;
		quantity = 0;
		productID = -1;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{//Runs at end of class. Null the constructs.
		rackManager = null;
		rack = null;
		name = null;
		cost = 0;
		quantity = 0;
		productID = -1;
	}

	@Before
	public void setUp() throws Exception
	{//Runs before tests. Make fresh new rack and manager.
		capacity = 10;
		
		rack = new ProductRack(capacity);
		name = "Coke";
		cost = 100;
		quantity = 0;
		productID = 10;
		
		rackManager = new ProductRackController(rack, name, cost, quantity, null, new Logger(), 0);
		
	}

	@After
	public void tearDown() throws Exception
	{//Runs at end of tests. Null the constructs.
		rackManager = null;
		rack = null;
		name = null;
		cost = 0;
		quantity = 0;
		productID = -1;
	}

	/**
	 * End reset stuff section.
	 **/
	
	//---------------
	
	/**
	 * Tests with stuff that happens and does things
	 */
	
	@Test
	public void test_constructor_noQuantity()
	{
		assertNotNull(rackManager);
	}
	
	@Test
	public void test_constructor_Quantity()
	{
		quantity = 5;
		
		rackManager = new ProductRackController(rack, name, cost, quantity, null, new Logger(), 0);
		
		assertNotNull(rackManager);
	}
	
	@Test
	public void test_default_constructor()
	{	
		rackManager = new ProductRackController();
		
		assertNotNull(rackManager);
	}
	
	@Test
	public void test_constructor_null_racks()
	{	
		try{
			rackManager = new ProductRackController(null, name, cost, quantity, null, new Logger(), 0);
		} catch(InvalidParameterException e){
			assertTrue(true);
		}
		
		//assertTrue(false);
	}
	
	@Test
	public void test_refill()
	{
		rackManager.refill();
		
		boolean fullReturn = rackManager.isFull();
		assertEquals(true, fullReturn);
	}
	
	@Test
	public void test_refillQuantity()
	{
		int quant = 2;
		
		rackManager.refillQuantity(quant);
		
		int countReturn = rackManager.getCount();
		assertEquals(quant, countReturn);
	}
	
	@Test
	public void test_refillQuantity_overCapacity()
	{
		int quant = 100;
		
		rackManager.refillQuantity(quant);
		
		int countReturn = rackManager.getCount();
		assertEquals(rackManager.getCapacity(), countReturn);
	}
	
	/**
	 * Basic getter Tests
	 */
	
	@Test
	public void test_getRack()
	{
		ProductRack rackReturn = rackManager.getRack();
		assertEquals(rack, rackReturn);
	}
	
	@Test
	public void test_getCount()
	{
		int countReturn = rackManager.getCount();
		assertEquals(0, countReturn);
	}
	
	@Test
	public void test_getCost()
	{
		int costReturn = rackManager.getCost();
		assertEquals(cost, costReturn);
	}
	
	@Test
	public void test_getCapacity()
	{
		int capacityReturn = rackManager.getCapacity();
		assertEquals(capacity, capacityReturn);
	}
	
	@Test
	public void test_getName()
	{
		String nameReturn = rackManager.getName();
		assertEquals(name, nameReturn);
	}
	
//	@Test
//	public void test_getProductID()
//	{
//		int IDReturn = rackManager.getProductID();
//		assertEquals(productID, IDReturn);
//	}
	
	@Test
	public void test_isFull()
	{
		boolean fullReturn = rackManager.isFull();
		assertEquals(false, fullReturn);
	}

	@Test
	public void test_isEmpty()
	{
		boolean emptyReturn = rackManager.isEmpty();
		assertEquals(true, emptyReturn);
	}
	
	/**
	 * Basic Setter Tests. Relies on Getters to be working.
	 */	
	@Test
	public void test_changePrice()
	{
		int newCost = 200;
		rackManager.changePrice(newCost);
		
		int costReturn = rackManager.getCost();
		assertEquals(newCost, costReturn);
	}
	
	@Test
	public void test_changeName()
	{
		String newName = "Pepsi";
		rackManager.changeName(newName);
		
		String nameReturn = rackManager.getName();
		assertEquals(newName, nameReturn);
	}
	
//	@Test
//	public void test_changeProductID()
//	{
//		int newID = 20;
//		rackManager.changeProductID(newID);
//		
//		int IDReturn = rackManager.getProductID();
//		assertEquals(newID, IDReturn);
//	}
	
	@Test
	public void test_load_isFull()
	{
		rackManager.refill();
		
		boolean fullReturn = rackManager.isFull();
		assertEquals(true, fullReturn);
	}

	@Test
	public void test_load_isEmpty()
	{
		rackManager.refill();
		
		boolean emptyReturn = rackManager.isEmpty();
		assertEquals(false, emptyReturn);
	}
	
}