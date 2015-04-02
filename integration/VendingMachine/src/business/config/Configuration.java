package business.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import business.selection_delivery.ButtonSelectionController;
import business.selection_delivery.CodeSelectionController;
import business.selection_delivery.InventoryController;
import business.stub.DisplayController;
import business.stub.FundsController;
import hardware.AbstractVendingMachine;
import hardware.VendingMachine1;
import hardware.exceptions.NoSuchHardwareException;
import hardware.racks.ProductRack;
import hardware.ui.PushButton;
import hardware.ui.PushButtonCodeInterpreter;

public class Configuration {
	
	
	// Configuration data loaded from/written to the configuration file
	protected String type;
	protected String [] names;
	protected int [] quantities;
	protected int [] prices;
	
	protected int [] coinRackQuantities;
	protected int [] coinStorageQuantities;
	protected int [] billRackQuantities;
	protected int [] billStorageQuantities;
	
	protected String logFrequency;
	protected Locale locale;
	
	// Vending machine we created
	protected AbstractVendingMachine machine;
	
	// Controllers we require data from when saving
	// eg. FundsController needs to tell us how many coins in each rack
	
	protected FundsController funds;
	//protected InventoryManager inventory; 
	protected InventoryController inventoryController; // Maria: added as InventoryManager was commented.
	protected CodeSelectionController codeSelectionController; // Maria: Added CodeSelectionController object
	protected DisplayController displayController; // Maria: added for the displayController
	protected ButtonSelectionController buttonSelectionController;
	

	public Configuration()
	{}

	/**
	 * Loads a vending machine from a given configuration file.
	 * 
	 * @param filename					config file to read from
	 * @param configListeners			listeners to register with the ConfigurationPanel
	 * 									  (for the GUI pieces that need it)
	 * 
	 * @return							the created AbstractVendingMachine
	 * 
	 * @throws IOException				standard file reading exceptions
	 * @throws ConfigurationException	if the configuration file is incorrectly formed
	 */
	public AbstractVendingMachine load(String filename,
										  ArrayList<ConfigurationListener> configListeners)
		throws IOException, ConfigurationException
	{
		BufferedReader input = new BufferedReader(new FileReader(filename));
		readConfigFile(input);
		createMachine();
		createConfigurationController(configListeners);
		loadMachine();
		return machine;
	}

	/**
	 * Saves the vending machine previously created by load() to a given file.
	 * 
	 * Requires that a vending machine has been created previously with load().
	 * 
	 * @param filename					configuration file to save to
	 * 
	 * @throws IOException				standard file reading exceptions
	 * @throws ConfigurationException 	if a machine has not been created
	 */
	public void save(String filename) throws IOException, ConfigurationException
	{
		if (machine == null) {
			throw new ConfigurationException("Attempted to save a nonexistent machine!");
		}

		// Read all the data we need from machine, funds and inventory
		BufferedWriter output = new BufferedWriter(new FileWriter(filename));
		writeConfigFile(output);
	}
	
	/**
	 * Wrap the nasty type -> machine creation function if statement up in its own little
	 *  bubble where it can't hurt anyone.
	 *  			
	 * @throws ConfigurationException	if the type is not a recognized vending machine type
	 */
	protected void createMachine()
		throws ConfigurationException
	{
		if (type.equals("VMRUS-SFF-P/C")) {
			machine = createSFFPC();
		}
//		else if (type.equals("VMRUS-SFF-P/CI")) {
//			machine = createSFFPCI();
//		}
//		else if (type.equals("VMRUS-SFF-P/PI")) {
//			machine = createSFFPPI();
//		}
//		else if (type.equals("VMRUS-COM-P/MI")) {
//			machine = createCOMPMI();
//		}
//		else if (type.equals("VMRUS-COM-P/M")) {
//			machine = createCOMPM();
//		}
//		else if (type.equals("VMRUS-COM-C/MI")) {
//			machine = createCOMCMI();
//		}
//		else if (type.equals("VMRUS-COM-C/M")) {
//			machine = createCOMCM();
//		}
//		else if (type.equals("VMRUS-TOC-P/MI")) {
//			machine = createTOCPMI();
//		}
//		else if (type.equals("VMRUS-TOC-P/I")) {
//			machine = createTOCPI();
//		}
//		else if (type.equals("VMRUS-TOC-C/MI")) {
//			machine = createTOCCMI();
//		}
//		else if (type.equals("VMRUS-TOC-C+")) {
//			machine = createTOCCp();
//		}
//		else if (type.equals("VMRUS-TOC-C+/I")) {
//			machine = createTOCCpI();
//		}
		else {
			throw new ConfigurationException("Invalid machine type!");
		}
		
	/**
	 * This will take the values of all prices racks and quantities from the controllers
	 * and update the Configuration values
	 */
	}
	protected void updateValues(){
		int rackcount=inventoryController.getRackCount();
		for(int i=0;i<rackcount;i++){
				names[i]=inventoryController.getName(i);
			}
		for(int i=0;i<rackcount;i++){
				quantities[i]=inventoryController.getCount(i);
		}
		for(int i=0;i<rackcount;i++){
				prices[i]=inventoryController.getCost(i);
		}
		//TODO Anish: Working on this
		//Add funds controller stuff here
	}
	
	/**
	 * The main workhorse of parsing the configuration file into member variables
	 *  for vending machine creation functions to use.
	 *  
	 * @param input						stream to read config file from
	 * 
	 * @throws IOException				standard stream reading exceptions
	 * @throws ConfigurationException	if the configuration file is incorrectly formed
	 */
	protected void readConfigFile(BufferedReader input)
		throws IOException, ConfigurationException
	{
		ArrayList<String> lines = readFileLines(input);
		if (lines.size() < 8) {
			throw new ConfigurationException("Incomplete config file");
		}
		
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
			throw new ConfigurationException("Missing line in config file!");
		}
	}
	
	protected void writeConfigFile(BufferedWriter output) throws IOException
	{
		String namestring,pricesstring,Qstring,CRQString,CSQString,BRQString,BSQString;
		namestring="names";
		for(int i=0;i<names.length;i++){
			namestring+=" "+names[i];
		}
		pricesstring="prices";
		for(int i=0;i<prices.length;i++){
			pricesstring+=" "+Integer.toString(prices[i]);
		}
		Qstring="quantities";
		for(int i=0;i<quantities.length;i++){
			Qstring+=" "+Integer.toString(quantities[i]);
		}
		CRQString="coinracks";
		for(int i=0;i<coinRackQuantities.length;i++){
			CRQString+=" "+Integer.toString(coinRackQuantities[i]);
		}
		CSQString="coinstorage";
		for(int i=0;i<coinStorageQuantities.length;i++){
			CRQString+=" "+Integer.toString(coinStorageQuantities[i]);
		}
		BRQString="billracks";
		for(int i=0;i<billRackQuantities.length;i++){
			BRQString+=" "+Integer.toString(billRackQuantities[i]);
		}
		BSQString="billstorage";
		for(int i=0;i<billStorageQuantities.length;i++){
			BRQString+=" "+Integer.toString(billStorageQuantities[i]);
		}
		output.write(namestring);
		output.newLine();
		output.write(pricesstring);
		output.newLine();
		output.write(Qstring);
		output.newLine();
		output.write(CRQString);
		output.newLine();
		output.write(CSQString);
		output.newLine();
		output.write(BRQString);
		output.newLine();
		output.write(BSQString);
		//TODO Anish:Working on this
		//Do basically the opposite of what we do in readConfigFile()
	}
	
	protected void loadMachine()
	{
		// Load the machine with all the right amounts of coins, bills, products
		// making sure to use loadWithoutEvents() functions where you can
	}

	/**
	 * Create a FundsController with the specified parameters, and register
	 *  it with all the listeners it needs to be registered with. Note that
	 *  this will also have to save the FundsController as the 'funds' field
	 *  of this class so we have access to it when we want to save later.
	 *  
	 * @param coin					does this machine accept coin?
	 * @param card					does this machine accept card?
	 * @param paypal				does this machine accept paypal?
	 */
	protected void createFundsController(boolean coin,
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
	 */
	public void createButtonSelectionController()
	{
		// TODO Maria: Work in progress
		try {
		int numberOfButtons = this.machine.getNumberOfSelectionButtons();
		PushButton [] pushButtons = new PushButton[numberOfButtons];
		for(int i = 0; i < numberOfButtons; i++){
			
				pushButtons[i] = this.machine.getSelectionButton(i);
		}
		//Creation of controller
		this.buttonSelectionController = new ButtonSelectionController(this.inventoryController,this.displayController,this.funds,pushButtons,numberOfButtons);
		
		// Registering the buttons from hardware with the buttonSelectionController
		for(int i=0; i < numberOfButtons; i++){
			pushButtons[i].register(buttonSelectionController);
		}
			} catch (NoSuchHardwareException e) {
				e.printStackTrace();
			}
		
	}
	
	/**
	 * Create a CodeController at the specified selection button offset, and register
	 *  it with the CodeInterpreter it needs to listen to.
	 *  
	 * @param offset				beginning index of 'code' selection buttons
	 * 								  (vs. normal push buttons)
	 */
	protected void createCodeController(int offset)
	{
		
		// TODO Maria: Work in progress
		try {
			// Creating the codeselection controller.
			this.codeSelectionController = new CodeSelectionController(
					this.inventoryController, 
					this.displayController, 
					this.funds, 
					this.machine.getPushButtonCodeInterpreter(), 
					offset);
			
			//Registering the PushButtonCodeInterpreter with the codeSelectionController
			this.machine.getPushButtonCodeInterpreter().register(codeSelectionController);
		} catch (NoSuchHardwareException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Create an InventoryManager and register it with all the hardware it needs to
	 *  listen to. Note that this will also need to save said InventoryManager
	 *  as the 'inventory' field of this class, so we have access to it again
	 *  when we want to save.
	 */
	protected void createInventoryController()
	{
		// TODO Maria: Work in progress
		
		try {
			int numberOfRacks = this.machine.getNumberOfProductRacks();
			// An array of ProductRack is created and used to build an InventoryController object
			ProductRack racks[] = new ProductRack[numberOfRacks];
			for(int i=0; i < this.machine.getNumberOfProductRacks(); i++){
				racks[i] = new ProductRack(this.machine.getProductRack(i).getMaxCapacity());
			}
			
			//Inventory controller creation with information known from machine.
			this.inventoryController = new InventoryController(racks,numberOfRacks,this.names,this.prices,this.quantities);
			
		} catch (NoSuchHardwareException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Create a logger with the specified logging frequency
	 * 
	 * @param machine				hardware to register listeners with
	 * @param frequency				logging frequency
	 * 								  (one of either "instant", "batch" or "daily")
	 */
	protected void createLogger(String frequency)
	{
		
	}
	
	/**
	 * Create a ConfigurationController and register it with the ConfigurationPanel,
	 *  as well as registering anything that needs to be listening to it.
	 * @param machine
	 */
	protected void createConfigurationController(ArrayList<ConfigurationListener> configListeners)
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
	protected AbstractVendingMachine createSFFPC()
	{
		return new VendingMachine1(new int [] {5, 10, 25, 100, 200}, prices, names);
	}
//
//	protected AbstractVendingMachine createSFFPCI()
//	{
//		return new AbstractVendingMachine();	
//	}
//
//	protected AbstractVendingMachine createSFFPPI()
//	{
//		return new AbstractVendingMachine();		
//	}
//
//	protected AbstractVendingMachine createCOMPMI()
//	{
//		return new AbstractVendingMachine();		
//	}
//
//	protected AbstractVendingMachine createCOMPM()
//	{
//		return new AbstractVendingMachine();		
//	}
//
//	protected AbstractVendingMachine createCOMCMI()
//	{
//		return new AbstractVendingMachine();		
//	}
//
//	protected AbstractVendingMachine createCOMCM()
//	{
//		return new AbstractVendingMachine();		
//	}
//	
//	protected AbstractVendingMachine createTOCPMI()
//	{
//		return new AbstractVendingMachine();		
//	}
//	
//	protected AbstractVendingMachine createTOCPI()
//	{
//		return new AbstractVendingMachine();		
//	}
//
//	protected AbstractVendingMachine createTOCCMI()
//	{
//		return new AbstractVendingMachine();		
//	}
//
//	protected AbstractVendingMachine createTOCCp()
//	{
//		return new AbstractVendingMachine();		
//	}
//
//	protected AbstractVendingMachine createTOCCpI()
//	{
//		return new AbstractVendingMachine();		
//	}
	
	/**
	 * Parsing functions - simple parsing of integer and string arrays
	 *  from file lines. Used by readConfigFile().
	 */
	protected ArrayList<String> readFileLines(BufferedReader input)
		throws IOException
	{
		ArrayList<String> lines = new ArrayList<String>();
		
		String line;
		while ((line = input.readLine()) != null) {
			lines.add(line);
		}
		
		return lines;
	}
	
	protected String [] stripFirst(String [] components)
	{
		String [] ret = new String[components.length - 1];

		for (int i = 0; i < ret.length; ++i) {
			ret[i] = components[i + 1];
		}
		
		return ret;
	}
	
	protected String [] readStringArray(String line) {
		String [] components = line.split(" ");

		return stripFirst(components);
	}
	
	protected int [] toIntArray(String [] strings) {
		int [] ret = new int[strings.length];
		
		for (int i = 0; i < ret.length; ++i) {
			ret[i] = Integer.valueOf(strings[i]);
		}
		
		return ret;
	}
	
	protected int [] readIntArray(String line) {
		String [] components = line.split(" ");
		String [] strings = stripFirst(components);
		
		return toIntArray(strings);
	}
	
	// Getters and setters
	
	public ButtonSelectionController getButtonSelectionController() {
		return buttonSelectionController;
	}
	
	public void setButtonSelectionController(ButtonSelectionController controller){
		this.buttonSelectionController = controller;
	}
}
