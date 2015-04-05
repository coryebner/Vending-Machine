package business.config.test;

import static org.junit.Assert.*;
import hardware.racks.ProductRack;
import hardware.ui.ConfigurationPanelTransmitter;

import org.junit.Before;
import org.junit.Test;

import business.config.ConfigPanelLogic;
import business.config.ConfigurationListener;
import business.selection_delivery.InventoryController;
import business.stub.FundsController;

public class ConfigurationTest {

	public InventoryController manager;
	public ConfigurationListener config;
	public ProductRack[] rack = new ProductRack[2];
	public int rackcount;
	String [] names = new String[2];
	int [] costs = new int [2];
	int [] quantity = new int [2];
	ConfigurationPanelTransmitter test;
	ConfigPanelLogic sub;

	@Before
	public void setUp() throws Exception {

		rack[0] = new ProductRack(10);
		rack[1] = new ProductRack(10);

		rackcount = 2;
		names[0] = "Product1";
		names[1] = "Product2";
		costs[0] = 100;
		costs[1] = 200;
		quantity[0] = 4;
		quantity [1] = 3;

		manager = new InventoryController(rack,rackcount,names,costs,quantity);
		test = new ConfigurationPanelTransmitter();
		sub = new ConfigPanelLogic();
		test.register(sub);
		sub.register(manager);
	}

	@Test
	public void changePriceTest() {
		
		test.enterCommand("newPrice");
		test.enterCommand("1");
		test.enterCommand("ENTER");
		test.enterCommand("1");
		test.enterCommand("2");
		test.enterCommand("5");
		test.enterCommand("ENTER");


		assertEquals("New cost: " + manager.getCost(1), 125, manager.getCost(1));

	}
	
	@Test
	public void changeNameTest() {

		test.enterCommand("newName");
		test.enterCommand("0");
		test.enterCommand("ENTER");
		test.enterCommand("C");
		test.enterCommand("o");
		test.enterCommand("l");
		test.enterCommand("a");
		test.enterCommand("ENTER");
		
		assertEquals("New name: " + manager.getName(0), "Cola", manager.getName(0));
		
	}
}
