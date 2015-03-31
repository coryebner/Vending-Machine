package config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Configuration {
	// Stubs for compiling - to be removed once these classes are actually merged in
	static class AbstractVendingMachine {}
	static class InventoryManager {}
	static class FundsController {}
	
	// Configuration file
	private File configFile;
	
	private String type;
	private String [] names;
	private int [] quantities;
	private int [] prices;
	
	private int [] coinRackQuantities;
	private int [] coinStorageQuantities;
	private int [] billRackQuantities;
	private int [] billStorageQuantities;
	
	private String logFrequency;
	private Locale locale;
	
	// Vending machine we created
	private AbstractVendingMachine machine;
	
	// Controllers we require data from when saving
	// eg. FundsController needs to tell us how many coins in each rack
	private FundsController funds;
	private InventoryManager inventory;
	
	public Configuration(String filename)
	{
		configFile = new File(filename);
	}
	
	private AbstractVendingMachine createMachine()
		throws IOException
	{
		if (type.equals("VMRUS-SFF-P/C")) {
			return createSFFPC();
		}
		else if (type.equals("VMRUS-SFF-P/CI")) {
			return createSFFPCI();
		}
		else if (type.equals("VMRUS-SFF-P/PI")) {
			return createSFFPPI();
		}
		else if (type.equals("VMRUS-COM-P/MI")) {
			return createCOMPMI();
		}
		else if (type.equals("VMRUS-COM-P/M")) {
			return createCOMPM();
		}
		else if (type.equals("VMRUS-COM-C/MI")) {
			return createCOMCMI();
		}
		else if (type.equals("VMRUS-COM-C/M")) {
			return createCOMCM();
		}
		else if (type.equals("VMRUS-TOC-P/MI")) {
			return createTOCPMI();
		}
		else if (type.equals("VMRUS-TOC-P/I")) {
			return createTOCPI();
		}
		else if (type.equals("VMRUS-TOC-C/MI")) {
			return createTOCCMI();
		}
		else if (type.equals("VMRUS-TOC-C+")) {
			return createTOCCp();
		}
		else if (type.equals("VMRUS-TOC-C+/I")) {
			return createTOCCpI();
		}
		else {
			return null;
		}
	}
	
	private void readConfigFile(BufferedReader input) throws IOException
	{
		ArrayList<String> lines = readFileLines(input);
		
		type = lines.get(0);
		
		for (String line : lines) {
			if (line.startsWith("names")) {
				names = readStringArray(line);
			}
			else if (line.startsWith("prices")) {
				prices = readIntArray(line);
			}
			else if (line.startsWith("quantities")) {
				quantities = readIntArray(line);
			}
			else if (line.startsWith("coinracks")) {
				coinRackQuantities = readIntArray(line);
			}
			else if (line.startsWith("coinstorage")) {
				coinStorageQuantities = readIntArray(line);
			}
			else if (line.startsWith("billracks")) {
				billRackQuantities = readIntArray(line);
			}
			else if (line.startsWith("billstorage")) {
				billStorageQuantities = readIntArray(line);
			}
		}
		
		if (type == null
			|| names == null
			|| prices == null
			|| quantities == null
			|| coinRackQuantities == null
			|| coinStorageQuantities == null
			|| billRackQuantities == null
			|| billStorageQuantities == null)
		{
			// Throw something! Parsing problem
		}
	}
	
	private void writeConfigFile(BufferedWriter output)
	{
		// Do basically the opposite of what we do in readConfigFile()
	}

	public AbstractVendingMachine load() throws IOException
	{
		BufferedReader input = new BufferedReader(new FileReader(configFile));
		readConfigFile(input);
		machine = createMachine();
		return machine;
	}
	
	public void save() throws IOException
	{
		// Read all the data we need from machine, funds and inventory
		BufferedWriter output = new BufferedWriter(new FileWriter(configFile));
		writeConfigFile(output);
	}
	
	/**
	 * Create a FundsController with the specified parameters, and register
	 *  it with all the listeners it needs to be registered with.
	 *  
	 * @param machine				hardware to register listeners with
	 * @param coin					does this machine accept coin?
	 * @param card					does this machine accept card?
	 * @param paypal				does this machine accept paypal?
	 */
	private void createFundsController(AbstractVendingMachine machine,
									   boolean coin,
									   boolean card,
									   boolean paypal)
	{
		// funds = new FundsController(locale, coin, card, paypal);
		// machine.getCoinSlot().register(funds.getCoinController());
		// machine.getCardSlot().register...
	}
	
	/**
	 * Create a ButtonController, and register it with all the buttons it needs
	 *  to be registered with.
	 *  
	 * @param machine				hardware to register listeners with
	 */
	private void createButtonController(AbstractVendingMachine machine)
	{
		
	}
	
	/**
	 * Create a CodeController at the specified selection button offset, and register
	 *  it with the CodeInterpreter it needs to listen to.
	 *  
	 * @param machine				hardware to register listeners with
	 * @param offset				beginning index of 'code' selection buttons
	 * 								  (vs. normal push buttons)
	 */
	private void createCodeController(AbstractVendingMachine machine, int offset)
	{
		
	}
	
	/**
	 * Create an InventoryManager and register it with all the hardware it needs to
	 *  listen to.
	 *  	
	 * @param machine				hardware to register listeners with
	 */
	private void createInventoryManager(AbstractVendingMachine machine)
	{
		
	}
	
	/**
	 * Create a logger with the specified logging frequency
	 * 
	 * @param machine				hardware to register listeners with
	 * @param frequency				logging frequency
	 * 								  (one of either "instant", "batch" or "daily")
	 */
	private void createLogger(AbstractVendingMachine machine, String frequency)
	{
		
	}

	/**
	 * Specific hardware creation functions. Returns an AbstractVendingMachine
	 *  with concrete type specified in the method name.
	 *  
	 * These functions are where the registration of pieces happens. Each of these
	 *  functions are responsible for calling:
	 *   - createFundsController()
	 *   - createButtonController()
	 *   - createCodeController()
	 *   - createInventoryController()
	 *  in the correct order. Note that this does NOT include createLogger()
	 */
	private AbstractVendingMachine createSFFPC()
	{
		return new AbstractVendingMachine();
	}

	private AbstractVendingMachine createSFFPCI()
	{
		return new AbstractVendingMachine();	
	}

	private AbstractVendingMachine createSFFPPI()
	{
		return new AbstractVendingMachine();		
	}

	private AbstractVendingMachine createCOMPMI()
	{
		return new AbstractVendingMachine();		
	}

	private AbstractVendingMachine createCOMPM()
	{
		return new AbstractVendingMachine();		
	}

	private AbstractVendingMachine createCOMCMI()
	{
		return new AbstractVendingMachine();		
	}

	private AbstractVendingMachine createCOMCM()
	{
		return new AbstractVendingMachine();		
	}
	
	private AbstractVendingMachine createTOCPMI()
	{
		return new AbstractVendingMachine();		
	}
	
	private AbstractVendingMachine createTOCPI()
	{
		return new AbstractVendingMachine();		
	}

	private AbstractVendingMachine createTOCCMI()
	{
		return new AbstractVendingMachine();		
	}

	private AbstractVendingMachine createTOCCp()
	{
		return new AbstractVendingMachine();		
	}

	private AbstractVendingMachine createTOCCpI()
	{
		return new AbstractVendingMachine();		
	}
	
	/**
	 * Parsing functions - simple parsing of integer and string arrays
	 *  from file lines. Used by readConfigFile().
	 */
	private ArrayList<String> readFileLines(BufferedReader input)
		throws IOException
	{
		ArrayList<String> lines = new ArrayList<String>();
		
		String line;
		while ((line = input.readLine()) != null) {
			lines.add(line);
		}
		
		return lines;
	}
	
	private String [] stripFirst(String [] components)
	{
		String [] ret = new String[components.length - 1];

		for (int i = 0; i < ret.length; ++i) {
			ret[i] = components[i + 1];
		}
		
		return ret;
	}
	
	private String [] readStringArray(String line) {
		String [] components = line.split(" ");

		return stripFirst(components);
	}
	
	private int [] toIntArray(String [] strings) {
		int [] ret = new int[strings.length];
		
		for (int i = 0; i < ret.length; ++i) {
			ret[i] = Integer.valueOf(strings[i]);
		}
		
		return ret;
	}
	
	private int [] readIntArray(String line) {
		String [] components = line.split(" ");
		String [] strings = stripFirst(components);
		
		return toIntArray(strings);
	}
	
}
