package business.selection_delivery.tests;

import static org.junit.Assert.*;
import hardware.racks.ProductRack;

import org.junit.Before;
import org.junit.Test;

import SDK.logger.Logger;
import business.selection_delivery.InventoryController;


public class InventoryControllerTest {

	public InventoryController manager;
	public ProductRack[] rack = new ProductRack[2];
	public int rackcount;
	String [] names = new String[2];
	int [] costs = new int [2];
	int [] quantity = new int [2];
	int [] productID = new int[2];
	
	@Before
	public void setUp() throws Exception {
		
		rack[0] = new ProductRack(10);
		rack[1] = new ProductRack(10);
		
		rackcount = 2;
		names[0] = "Product1";
		names[1] = "Product2";
		costs[0] = 1;
		costs[1] = 2;
		quantity[0] = 4;
		quantity [1] = 3;
		productID[0] = 7331;
		productID[1] = 42;

		manager = new InventoryController(rack,rackcount,names,costs,quantity, null, new Logger(), 0);
	}

	@Test
	public void testInventroyManagerConstructor() {
		assertNotNull(manager);	
	}

	@Test
	public void testInventoryManager() {
		InventoryController defaultconstructormanager = new InventoryController();
		
		assertNotNull("Instantionation of InventoryController should not return a null object", defaultconstructormanager);
	}

	@Test
	public void testRefillAll() {
		
		
		manager.refillAll();
		
		//Test to see that after refill All, the amount of product in each rack is equal to their capacity(full)
		assertEquals(manager.getCapacity(0),manager.getCount(0));
		assertEquals(true, manager.isFull(0));
		assertEquals(manager.getCapacity(1),manager.getCount(1));
		assertEquals(true, manager.isFull(1));

	}

	@Test
	public void testRefillRack() {
	
		manager.refillRack(0);

		//Test to see that after refillRack for the first rack, the amount of product in each rack is equal to the capacity(full)
		assertEquals(manager.getCapacity(0),manager.getCount(0));
		}

	@Test
	public void testGetRack() {

		ProductRack test;
		test = manager.getRack(0);
		assertEquals(test,rack[0]);
		
	}

	@Test
	public void testGetRackCount() {
		
		assertEquals(2,manager.getRackCount());
	}

	@Test
	public void testGetCount() {
		assertEquals(quantity[0], manager.getCount(0));
		assertEquals(quantity[1], manager.getCount(1));
	}
	
	
	
	
	@Test
	public void testGetCost() {
		
		assertEquals(1, manager.getCost(0));
		assertEquals(2, manager.getCost(1));
	}	

	@Test
	public void testGetCapacity() {
		
		assertEquals(10, manager.getCapacity(0));	
		assertEquals(10, manager.getCapacity(1));	
	}

	@Test
	public void testGetName() {
		
		assertEquals("Product1", manager.getName(0));		
		assertEquals("Product2", manager.getName(1));		
	}
	
//	@Test
//	public void testGetProductID() {
//		
//		assertEquals(7331, manager.getProductID(0));
//		assertEquals(42, manager.getProductID(1));
//	}

	@Test
	public void testIsFull() {

		manager.refillRack(0);

		assertEquals(true,manager.isFull(0));
		assertEquals(false,manager.isFull(1));	
	}

	@Test
	public void testIsEmpty() {
		ProductRack [] testrack = new ProductRack[1];
		testrack[0]= new ProductRack(10);
		int[] quant = new int[1];
		int[] cost = new int[1];
		int[] id = new int[1];
		String[] nom = new String[1];
		
		quant[0] = 0;
		cost[0] = 0;
		id[0] = 0;
		
		InventoryController test = new InventoryController(testrack,1,nom,cost,quant,null, new Logger(), 0);
		
		assertEquals(true,test.isEmpty(0));
	}

	@Test
	public void testChangePrice() {
		
		manager.changePrice(0, 5);
	
		assertEquals(5,manager.getCost(0));
		assertEquals(2, manager.getCost(1));
	}

	@Test
	public void testChangeName() {
		
		manager.changeName(0, "NewProduct1");
	
		assertEquals("NewProduct1",manager.getName(0));
		assertEquals("Product2", manager.getName(1));		
	}
	
//	@Test
//	public void testChangeProductID() {
//		
//		manager.changeProductID(0, 1123581321);
//		
//		assertEquals(1123581321, manager.getProductID(0));
//		assertEquals(42, manager.getProductID(1));
//	}

}
