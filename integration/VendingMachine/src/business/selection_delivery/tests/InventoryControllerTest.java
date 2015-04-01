package business.selection_delivery.tests;

import static org.junit.Assert.*;
import hardware.racks.ProductRack;

import org.junit.Before;
import org.junit.Test;

import business.selection_delivery.InventoryController;


public class InventoryControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInventroyManagerConstructor() {
		
		//**need to wait for hardware to change their popcan rack into something more abstract like productrack before doing anything else 
		ProductRack[] rack;
		rack[0] = new ProductRack();
		rack[1] = new ProductRack();
		rack[0] = new ProductRack(0);
		rack[1] = new ProductRack(0);


		int rackcount = 2;
		String [] names = {"Product1","Product2"}
		int [] costs = {1,2};
		
		InventoryManager manager = new InventoryManager(rack,rackcount,names,costs);
	
		int changelater;
		//Test to see if the cost is correct for first rack
		assertEquals(manager.getCost(0),1);
		//test to see if the cost is correct for second rack
		assertEquals(manager.getCost(1),2);

		
		//****need to update the amount of count after ProductRack can be constructed
		//Test to see if the amount of product in first rack is correct
		assertEquals(manager.getCount(0),changelater);
		//Test to see if the amount of product in second rack is correct
		assertEquals(manager.getCount(1),changelater);
		
		//check to see if the name for the first rack is properly assigned
		assertEquals(manager.getName(0),"Product1");
		//check to see if the name for the second rack is properly assigned
		assertEquals(manager.getName(1),"Product2");
		
		//Check to see if InventoryManager created productrackmanager[] in the right order by comparing the name of the racks
		assertEquals(manager.racks[0].getName(),"Product1");
		assertEquals(manager.racks[1].getName(),"Product2");


		

	}

	@Test
	public void testInventoryManager() {
		InventoryManager manager = new InventoryManager();

		if(manager == null)
			assertTrue(false);
		else
			assertTrue(true);
	}

	@Test
	public void testRefillAll() {

		//**need to wait for hardware to change their popcan rack into something more abstract like productrack before doing anything else 
		ProductRack[] rack;
		rack[0] = new ProductRack();
		rack[1] = new ProductRack();


		int rackcount = 2;
		String [] names = {"Product1","Product2"};
		int [] costs = {1,2};
		
		InventoryManager manager = new InventoryManager(rack,rackcount,names,costs);

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
		//**need to wait for hardware to change their popcan rack into something more abstract like productrack before doing anything else 
		ProductRack[] rack;
		rack[0] = new ProductRack();
		rack[1] = new ProductRack();


		int rackcount = 2;
		String [] names = {"Product1","Product2"};
		int [] costs = {1,2};
		
		InventoryManager manager = new InventoryManager(rack,rackcount,names,costs);

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

		ProductRack[] rack;
		rack[0] = new ProductRack();
		rack[1]= new ProductRack();
		
		int rackcount = 2;
		String [] names = {"Product1","Product2"};
		int [] cost = {1,2};
		
		InventoryManager manager = new InventoryManager(rack,rackcount,names,cost);
		
		//set up test ProductRack the same as rack 0
		ProductRack test = new ProductRack();
		
		assertEquals(test,manager.getRack(0));
		
		
	}

	@Test
	public void testGetRackCount() {
		ProductRack[] rack;
		rack[0] = new ProductRack();
		rack[1]= new ProductRack();
		
		int rackcount = 2;
		String [] names = {"Product1","Product2"};
		int [] cost = {1,2};
		
		InventoryManager manager = new InventoryManager(rack,rackcount,names,cost);
		
		assertEquals(2,manager.getRackCount());
	}

	@Test
	public void testGetCount() {
		ProductRack[] rack;
		rack[0] = new ProductRack();
		rack[1]= new ProductRack();
		
		int rackcount = 2;
		String [] names = {"Product1","Product2"};
		int [] cost = {1,2};
		
		InventoryManager manager = new InventoryManager(rack,rackcount,names,cost);
			
		//change changelater to value of rack0's count
		assertEquals(int changedlater, manager.getCount(0));
	}
	
	@Test
	public void testGetCount_Fail() {
		ProductRack[] rack;
		rack[0] = new ProductRack();
		rack[1]= new ProductRack();
		
		int rackcount = 2;
		String [] names = {"Product1","Product2"};
		int [] cost = {1,2};
		
		InventoryManager manager = new InventoryManager(rack,rackcount,names,cost);
			
		//change changelater to not a value of rack0's count
		assertEquals(int changedlater, manager.getCount(0));
	}
	
	
	@Test
	public void testGetCost() {
		@Test
		public void testGetCount() {
			ProductRack[] rack;
			rack[0] = new ProductRack();
			rack[1]= new ProductRack();
			
			int rackcount = 2;
			String [] names = {"Product1","Product2"};
			int [] cost = {1,2};
			
			InventoryManager manager = new InventoryManager(rack,rackcount,names,cost);
				
			
			assertEquals(1, manager.getCost(0));
			assertEquals(2, manager.getCost(1));

		}	}

	@Test
	public void testGetCapacity() {
		ProductRack[] rack;
		rack[0] = new ProductRack();
		rack[1]= new ProductRack();
		
		int rackcount = 2;
		String [] names = {"Product1","Product2"};
		int [] cost = {1,2};
		
		InventoryManager manager = new InventoryManager(rack,rackcount,names,cost);
			
		//change changelater to not a value of rack0's capacity
		assertEquals(int changedlater, manager.getCapacity(0));	
		//change changelater to not a value of rack01s capacity
		assertEquals(int changedlater, manager.getCapacity(1));	
		
	}

	@Test
	public void testGetName() {
		ProductRack[] rack;
		rack[0] = new ProductRack();
		rack[1]= new ProductRack();
		
		int rackcount = 2;
		String [] names = {"Product1","Product2"};
		int [] cost = {1,2};
		
		InventoryManager manager = new InventoryManager(rack,rackcount,names,cost);
			
		assertEquals("Product1", manager.getName(0));		
		assertEquals("Product2", manager.getName(1));		

	
	}

	@Test
	public void testIsFull() {
		
		
		ProductRack[] rack;
		rack[0] = new ProductRack();
		rack[1]= new ProductRack();
		
		int rackcount = 2;
		String [] names = {"Product1","Product2"};
		int [] cost = {1,2};
		
		InventoryManager manager = new InventoryManager(rack,rackcount,names,cost);
			
		//set rack0 to full
		//set rack1 to empty
		//****Enter code here****
		assertEquals(true,manager.isFull(0));
		assertEquals(false,manager.isFull(1));
		
	}

	@Test
	public void testIsEmpty() {
		ProductRack[] rack;
		rack[0] = new ProductRack();
		rack[1]= new ProductRack();
		
		int rackcount = 2;
		String [] names = {"Product1","Product2"};
		int [] cost = {1,2};
		
		InventoryManager manager = new InventoryManager(rack,rackcount,names,cost);
			
		//set rack0 to full or partially full
		//set rack1 to empty
		//****Enter code here****
		assertEquals(false,manager.isEmpty(0));
		assertEquals(true,manager.isEmpty(1));
			}

	@Test
	public void testChangePrice() {
		ProductRack[] rack;
		rack[0] = new ProductRack();
		rack[1]= new ProductRack();
		
		int rackcount = 2;
		String [] names = {"Product1","Product2"};
		int [] cost = {1,2};
		
		InventoryManager manager = new InventoryManager(rack,rackcount,names,cost);
			
		manager.ChangePrice(0, 5);
	
		assertEquals(5,manager.getCost(0));
		assertEquals(2, manager.getCost(1));
		
		}

	@Test
	public void testChangeName() {
		ProductRack[] rack;
		rack[0] = new ProductRack();
		rack[1]= new ProductRack();
		
		int rackcount = 2;
		String [] names = {"Product1","Product2"};
		int [] cost = {1,2};
		
		InventoryManager manager = new InventoryManager(rack,rackcount,names,cost);
			
		manager.changeName(0, "NewProduct1");
	
		assertEquals("NewProduct1",manager.getName(0));
		assertEquals("Product2", manager.getName(1));
			}

}
