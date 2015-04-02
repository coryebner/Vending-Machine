package business.selection_delivery.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hardware.racks.*;

import business.selection_delivery.ProductRackController;

public class ProductRackControllerTest {

	static ProductRackController rackManager;
	static ProductRack rack;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{//Runs at beginning of class. Initialize constructs to null.
		rackManager = null;
		rack = null;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{//Runs at end of class. Null the constructs.
		rackManager = null;
		rack = null;
	}

	@Before
	public void setUp() throws Exception
	{//Runs before tests. Make fresh new rack and manager.
		int capacity = 10;
		
		rack = new ProductRack(capacity);
		
		String n = "name";
		int cost = 100;
		int qty = 0;
		
		rackManager = new ProductRackController(rack,n,cost, qty);
	}

	@After
	public void tearDown() throws Exception
	{//Runs at end of tests. Null the constructs.
		rackManager = null;
		rack = null;
	}

	@Test
	public void test_Construction()
	{
		assertNotNull(rackManager);
	}
	
	@Test
	public void test2()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void test3()
	{
		fail("Not yet implemented");
	}
	
	public void test4()
	{
		fail("Not yet implemented");
	}
	
	public void test5()
	{
		fail("Not yet implemented");
	}
	
	public void test6()
	{
		fail("Not yet implemented");
	}
	
	public void test7()
	{
		fail("Not yet implemented");
	}
	
	public void test8()
	{
		fail("Not yet implemented");
	}

}
