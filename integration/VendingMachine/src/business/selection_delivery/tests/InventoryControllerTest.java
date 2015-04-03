package business.selection_delivery.tests;

import static org.junit.Assert.*;
import hardware.racks.ProductRack;

import org.junit.Before;
import org.junit.Test;

import business.selection_delivery.InventoryController;



public class InventoryControllerTest {

	public InventoryController manager;
	public ProductRack[] rack = new ProductRack[2];
	public int rackcount;
	String [] names = new String[2];
	int [] costs = new int [2];
	int [] quantity = new int [2];
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

		
		manager = new InventoryController(rack,rackcount,names,costs,quantity);
	}

	@Test
	public void testInventroyManagerConstructor() {
	
		assertNotNull(manager);	

	}

	@Test
	public void testInventoryManager() {
		InventoryController defaultconstructormanager = new InventoryController();

		if(defaultconstructormanager == null)
			assertTrue(false);
		else
			assertTrue(true);
	}

	@Test
	public void testRefillAll() {

		
		//Empty racks
		//*****Enter code (added later) ****
		//Check racks are emptied
		//*****Enter code (added later) ****
		
		
		
		manager.refillAll();
		
		//Test to see that after refill All, the amount of product in each rack is equal to their capcity(full)
		assertEquals(manager.getCapacity(0),manager.getCount(0));
		assertEquals(manager.getCapacity(1),manager.getCount(1));

		
	
	}

	@Test
	public void testRefillRack() {
		
		//To Empty Rack 0
		//**********Enter code (added later)
		// Test to see rack 0 is emptied
		//**********Enter code (added later)

		manager.refillRack(0);

		
		//Test to see that after refillRack for the first rack, the amount of product in each rack is equal to the capacity(full)
		assertEquals(manager.getCapacity(0),manager.getCount(0));

		
		}

	@Test
	public void testGetRack() {

	
		
		
	}

	@Test
	public void testGetRackCount() {
		
		
		assertEquals(2,manager.getRackCount());
	}

	@Test
	public void testGetCount() {
	
		//change changelater to value of rack0's count
		assertEquals(quantity[0], manager.getCount(0));
		assertEquals(quantity[1], manager.getCount(1));

	}
	
	@Test
	public void testGetCount_Fail() {
		
		//change changelater to not a value of rack0's count
		assertEquals(1, manager.getCount(0));
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

	@Test
	public void testIsFull() {
		
		
		
		//set rack0 to full
		manager.refillRack(0);
		//set rack1 to empty
		//****Enter code here****
		assertEquals(true,manager.isFull(0));
		assertEquals(false,manager.isFull(1));
		
	}

	@Test
	public void testIsEmpty() {
		
		//set rack0 to full or partially full
		manager.refillRack(0);

		//set rack1 to empty
		//****Enter code here****
		assertEquals(false,manager.isEmpty(0));
		assertEquals(true,manager.isEmpty(1));
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

}
