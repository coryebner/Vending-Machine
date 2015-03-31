package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import config.Configuration;
import config.ConfigurationException;

public class ConfigurationTest extends Configuration {
	public ConfigurationTest()
	{
		super("");
	}

	@Test
	public void testReadInitialConfigFile() throws Exception {
		BufferedReader input = new BufferedReader(new StringReader(
				"VMRUS-SFF-PC\n"
				+ "names Pepsi Coke 7-up Sprite\n"
				+ "prices 100 100 100 100\n"
				+ "quantities 0 0 0 0\n"
				+ "coinracks 0 0 0 0 0\n"
				+ "coinstorage 0 0 0 0 0\n"
				+ "billracks 0 0 0 0 0\n"
				+ "billstorage 0 0 0 0 0\n"));

		readConfigFile(input);
		
		String [] expectedNames = {"Pepsi", "Coke", "7-up", "Sprite"};
		int [] expectedPrices = {100, 100, 100, 100};
		int [] expectedQuantities = {0, 0, 0, 0};
		int [] expectedFunds = {0, 0, 0, 0, 0};
		
		assertArrayEquals("Correct names found", expectedNames, names);
		assertArrayEquals("Correct prices found", expectedPrices, prices);
		assertArrayEquals("Correct quantities found", expectedQuantities, quantities);
		assertArrayEquals("Correct coinracks found", expectedFunds, coinRackQuantities);
		assertArrayEquals("Correct coinstorage found", expectedFunds, coinStorageQuantities);
		assertArrayEquals("Correct billracks found", expectedFunds, billRackQuantities);
		assertArrayEquals("Correct billstorage found", expectedFunds, billStorageQuantities);
	}
	
	@Test(expected=ConfigurationException.class)
	public void testReadMissingLineConfigFile() throws Exception {
		BufferedReader input = new BufferedReader(new StringReader(
				"VMRUS-SFF-PC\n"
				+ "names Pepsi Coke 7-up Sprite\n"
				// + "prices 100 100 100 100\n"
				+ "quantities 0 0 0 0\n"
				+ "coinracks 0 0 0 0 0\n"
				+ "coinstorage 0 0 0 0 0\n"
				+ "billracks 0 0 0 0 0\n"
				+ "billstorage 0 0 0 0 0\n"));

		readConfigFile(input);
		fail("Exception should have been thrown by now");
	}
	
	@Test(expected=ConfigurationException.class)
	public void testReadBadTypeConfigFile() throws Exception {
		BufferedReader input = new BufferedReader(new StringReader(
				"NOT-A-VENDING-MACHINE\n"
				+ "names Pepsi Coke 7-up Sprite\n"
				+ "quantities 0 0 0 0\n"
				+ "coinracks 0 0 0 0 0\n"
				+ "coinstorage 0 0 0 0 0\n"
				+ "billracks 0 0 0 0 0\n"
				+ "billstorage 0 0 0 0 0\n"));

		readConfigFile(input);
		fail("Exception should have been thrown by now");
	}
	
	@Test(expected=ConfigurationException.class)
	public void testReadEmptyConfigFile() throws Exception {
		BufferedReader input = new BufferedReader(new StringReader(""));

		readConfigFile(input);
		fail("Exception should have been thrown by now");
	}

	@Test
	public void testReadSavedConfigFile() throws Exception {
		BufferedReader input = new BufferedReader(new StringReader(
				"VMRUS-SFF-PC\n"
				+ "names Pepsi Coke 7-up Sprite\n"
				+ "prices 125 150 200 100\n"
				+ "quantities 1 9 0 15\n"
				+ "coinracks 30 15 12 0 10\n"
				+ "coinstorage 90 23 12 14 55\n"
				+ "billracks 98 53 22 10 64\n"
				+ "billstorage 2 5 19 21 3\n"));

		readConfigFile(input);
		
		String [] expectedNames = {"Pepsi", "Coke", "7-up", "Sprite"};
		int [] expectedPrices = {125, 150, 200, 100};
		int [] expectedQuantities = {1, 9, 0, 15};
		int [] expectedCoinRacks = {30, 15, 12, 0, 10};
		int [] expectedCoinStorage = {90, 23, 12, 14, 55};
		int [] expectedBillRacks = {98, 53, 22, 10, 64};
		int [] expectedBillStorage = {2, 5, 19, 21, 3};
		
		assertArrayEquals("Correct names found", expectedNames, names);
		assertArrayEquals("Correct prices found", expectedPrices, prices);
		assertArrayEquals("Correct quantities found", expectedQuantities, quantities);
		assertArrayEquals("Correct coinracks found", expectedCoinRacks, coinRackQuantities);
		assertArrayEquals("Correct coinstorage found", expectedCoinStorage, coinStorageQuantities);
		assertArrayEquals("Correct billracks found", expectedBillRacks, billRackQuantities);
		assertArrayEquals("Correct billstorage found", expectedBillStorage, billStorageQuantities);
	}
}
