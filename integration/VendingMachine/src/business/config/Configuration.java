package business.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import SDK.logger.LogDate;
import SDK.logger.LogDate.LoggingType;
import SDK.logger.Logger;
import SDK.rifffish.Rifffish;
import business.funds.CoinRackController;
import business.funds.FundsController;
import business.funds.PaymentMethods;
import business.notifications.DisplayController;
import business.selection_delivery.ButtonSelectionController;
import business.selection_delivery.CodeSelectionController;
import business.selection_delivery.InventoryController;
import hardware.simulators.AbstractVendingMachine;
import hardware.simulators.VMRUS_COM_C_M;
import hardware.simulators.VMRUS_COM_C_MI;
import hardware.simulators.VMRUS_COM_P_M;
import hardware.simulators.VMRUS_COM_P_MI;
import hardware.simulators.VMRUS_SFF_P_C;
import hardware.simulators.VMRUS_SFF_P_CI;
import hardware.simulators.VMRUS_SFF_P_PI;
import hardware.simulators.VMRUS_TOC_CP;
import hardware.simulators.VMRUS_TOC_CP_I;
import hardware.simulators.VMRUS_TOC_C_MI;
import hardware.simulators.VMRUS_TOC_P_I;
import hardware.simulators.VMRUS_TOC_P_MI;
import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.simulators.*;
import hardware.exceptions.NoSuchHardwareException;
import hardware.racks.CoinRack;
import hardware.racks.ProductRack;
import hardware.ui.ConfigurationPanelTransmitter;
import hardware.ui.ConfigurationPanelTransmitterListener;
import hardware.ui.PushButton;
import hardware.ui.PushButtonCodeInterpreter;

public class Configuration {

	// Configuration data loaded from/written to the configuration file
	protected String type;
	protected String [] names;
	protected int [] quantities;
	protected int [] prices;

	protected int [] coinRackQuantities;
	protected int [] coinStorage;
	Map<Integer,Integer> coinStorageQuantities;
	protected int [] billStorageQuantities;
	protected int [] coinDenominations;
	protected int [] billDenominations;

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
	protected Logger logger;


	public Configuration()
	{

	}

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
		createConfigurationController(machine, configListeners);
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
			//machine = createSFFPC();
			machine = createSFFPC(machine);
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
		
		//coinStorageQuantities = funds.getCoinStorageBinTracker().getQuantities
		//if(locale.equals(Locale.CANADA){
		//		coinStorage[0]=coinStorageQuantities.get(5);
		//		coinStorage[1]=coinStorageQuantities.get(10);
		//		coinStorage[2]=coinStorageQuantities.get(25);
		//		coinStorage[3]=coinStorageQuantities.get(100);
		//		coinStorage[4]=coinStorageQuantities.get(200);
		//}
		//if(locale.equals(Locale.UK){
		//		coinStorage[0]=coinStorageQuantities.get(5);
		//		coinStorage[1]=coinStorageQuantities.get(10);
		//		coinStorage[2]=coinStorageQuantities.get(20);
		//		coinStorage[3]=coinStorageQuantities.get(50);
		//		coinStorage[4]=coinStorageQuantities.get(100);
		//		coinStorage[5]=coinStorageQuantities.get(200);
		//}
		//if(locale.equals(Locale.US){
		//		coinStorage[0]=coinStorageQuantities.get(5);
		//		coinStorage[1]=coinStorageQuantities.get(10);
		//		coinStorage[2]=coinStorageQuantities.get(25);
		//		coinStorage[3]=coinStorageQuantities.get(50);
		//		coinStorage[4]=coinStorageQuantities.get(100);
		//}
		//int CRackcount=funds.getCoinRackControllers().length;
		//for(int i=0;i<CRackcount;i++){
			//coinRackQuantities[i]=funds.getCoinRackControllers()[i].getQuantity();
		//}
		//billStorageQuantities[0]=funds.getBankNoteStorageBinTracker().getQuantity();

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
				coinStorage = readIntArray(line);
			}
			else if (line.startsWith("billstorage")) {
				billStorageQuantities = readIntArray(line);
			}
			else if (line.startsWith("logfrequency")){
				logFrequency = readString(line);
			}
			else if (line.startsWith("locale")){
				String l = readString(line);
				if (l.equals("CAD")) locale = Locale.CANADA;
				else if (l.equals("USD")) locale = Locale.US;
				else if (l.equals("EUR")) locale = Locale.UK;
				else throw new ConfigurationException("Invalid locale " + l + " - must be CAD, USD or EUR");
			}
		}
		
		if (type == null
			|| names == null
			|| prices == null
			|| quantities == null
			|| coinRackQuantities == null
			|| coinStorage == null
			|| billStorageQuantities == null
			|| locale == null)
		{
			throw new ConfigurationException("Missing line in config file!");
		}
		if (locale.equals(Locale.CANADA)){
			try{
			coinStorageQuantities.put(5,coinStorage[0]);
			coinStorageQuantities.put(10, coinStorage[1]);
			coinStorageQuantities.put(25, coinStorage[2]);
			coinStorageQuantities.put(100, coinStorage[3]);
			coinStorageQuantities.put(200, coinStorage[4]);
			coinDenominations = new int [] {5, 10, 25, 50, 100, 200};
			billDenominations = new int [] {500, 1000, 2000, 5000, 10000};
			}
			catch(Exception e){
				throw new ConfigurationException("Coin Storage Quantities Array is incorrect");
			}
		}
		else if(locale.equals(Locale.UK)){
			try{
			coinStorageQuantities.put(5,coinStorage[0]);
			coinStorageQuantities.put(10, coinStorage[1]);
			coinStorageQuantities.put(20, coinStorage[2]);
			coinStorageQuantities.put(50, coinStorage[3]);
			coinStorageQuantities.put(100, coinStorage[4]);
			coinStorageQuantities.put(200, coinStorage[5]);
			coinDenominations = new int [] {5, 10, 20, 50, 100, 200};
			billDenominations = new int [] {500, 1000, 2000, 5000, 10000};
			}
			catch(Exception e){
				throw new ConfigurationException("Coin Storage Quantities Array is incorrect");
			}
		}
		else if (locale.equals(Locale.US)){
			try{
			coinStorageQuantities.put(5,coinStorage[0]);
			coinStorageQuantities.put(10, coinStorage[1]);
			coinStorageQuantities.put(25, coinStorage[2]);
			coinStorageQuantities.put(50, coinStorage[3]);
			coinStorageQuantities.put(100, coinStorage[4]);
			coinDenominations = new int [] {5, 10, 25, 50, 100};
			billDenominations = new int [] {500, 1000, 2000, 5000, 10000};
			}
			catch(Exception e){
				throw new ConfigurationException("Coin Storage Quantities Array is incorrect");
			}
		}
	}

	protected void writeConfigFile(BufferedWriter output) throws IOException
	{
		String namestring,pricesstring,Qstring,CRQString,CSQString,BSQString,LFString,LocaleString;
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
		for(int i=0;i<coinStorage.length;i++){
			CSQString+=" "+Integer.toString(coinStorage[i]);
		}
		BSQString="billstorage";
		for(int i=0;i<billStorageQuantities.length;i++){
			BSQString+=" "+Integer.toString(billStorageQuantities[i]);
		}
		LFString="logfrequency "+logFrequency;
		LocaleString="locale "+locale.getCountry();
		output.write(type);
		output.newLine();
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
		output.write(BSQString);
		output.newLine();
		output.write(LFString);
		output.newLine();
		output.write(LocaleString);
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
	protected void createFundsController(AbstractVendingMachine m, boolean coin,
			boolean card,
			boolean paypal,
			boolean bill)
	{
		boolean bestEffortChange = false;
		// TODO Maria, working on this.
		// this.funds = new FundsController();
		//	public Funds(Locale locale, boolean bestEffortChange, CoinRack[] coinRacks,
		//int[] coinRackDenominations, int[] coinRackQuantities, 
		CoinRack[] cr;
		try {
			cr = new CoinRack[m.getNumberOfCoinRacks()];
			for(int i=0; i< m.getNumberOfCoinRacks(); i++){
				cr[i] = m.getCoinRack(i);
			}
			List<PaymentMethods> availablePaymentMethods = new ArrayList<PaymentMethods>();
			if(coin){
				availablePaymentMethods.add(PaymentMethods.COINS);
			}
			if(card){
				availablePaymentMethods.add(PaymentMethods.PREPAID);
			}
			if(paypal){
				availablePaymentMethods.add(PaymentMethods.PAYPAL);
			}
			if(bill){
				availablePaymentMethods.add(PaymentMethods.BILLS);
			}
			FundsController fundsNew =
					new FundsController(this.locale,
										availablePaymentMethods,
										
										bestEffortChange,
										coinDenominations,
										m.getCoinSlot(),
										
										m.getCoinReceptacle(),
										0,
										
										m.getCoinStorageBin(),
										coinStorageQuantities,
										
										cr,
										quantities,
										
										m.getBanknoteSlot(),
										m.getBanknoteReceptacle(),
										m.getBanknoteStorageBin(),
										
										new HashMap<Integer, Integer>(), // TODO: Save/restore this like we do coins
										0,
										
										m.getOutOfOrderLight(),
										inventoryController,
										logger;
			
			m.getCoinReceptacle().register(fundsNew.getCoinsController());
			m.getBanknoteReceptacle().register(fundsNew.getBankNoteController());
			// Register the coinracks
			CoinRackController[] crControllers = fundsNew.getCoinRackControllers();
			for(int i =0; i < m.getNumberOfCoinRacks(); i++){
				m.getCoinRack(i).register(crControllers[i]);
			}
			m.getCardSlot().register(fundsNew.getPrepaidController());
		} catch (NoSuchHardwareException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Create a ButtonController, and register it with all the buttons it needs
	 *  to be registered with.
	 */
	protected void createButtonSelectionController(AbstractVendingMachine m)
	{

		try {
			int numberOfButtons = m.getNumberOfSelectionButtons();
			PushButton [] pushButtons = new PushButton[numberOfButtons];
			for(int i = 0; i < numberOfButtons; i++){

				pushButtons[i] = m.getSelectionButton(i);
			}
			//Creation of controller
			this.buttonSelectionController = new ButtonSelectionController(this.inventoryController, this.funds, pushButtons, numberOfButtons);

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
	protected void createCodeController(AbstractVendingMachine m, int offset)
	{


		try {
			// Creating the code selection controller.
			this.codeSelectionController =
					new CodeSelectionController(inventoryController, this.funds, offset);

			//Registering the PushButtonCodeInterpreter with the codeSelectionController
			m.getPushButtonCodeInterpreter().register(codeSelectionController);
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
	protected void createInventoryController(AbstractVendingMachine m)
	{

		try {
			int numberOfRacks = m.getNumberOfProductRacks();
			// An array of ProductRack is created and used to build an InventoryController object
			ProductRack racks[] = new ProductRack[numberOfRacks];
			
			// TODO: This is not right... the ids part at least
			int [] ids = new int[racks.length];
			for(int i=0; i < m.getNumberOfProductRacks(); i++){
				racks[i] = m.getProductRack(i);
				ids[i] = i;
			}

			//Inventory controller creation with information known from machine.
			this.inventoryController = new InventoryController(racks, numberOfRacks, this.names, this.prices, this.quantities, ids);

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
	protected void createLogger(AbstractVendingMachine m, String frequency)
	{
		String r = "rsh_3wL4MyhWW4z3kfjoYfyN0gtt";
		if(frequency.equalsIgnoreCase("instant")){
			this.logger= new Logger(r,0);
			
		} else if(frequency.equalsIgnoreCase("batch")){
			this.logger= new Logger(r,100);
			
		}else if(frequency.equalsIgnoreCase("daily")){
			
			LogDate logdate = new LogDate(LoggingType.Daily,0,4,0);
			this.logger= new Logger(r,logdate);
			
		}else if(frequency.equalsIgnoreCase("offline")){
			this.logger= new Logger();
		}
		
	}

	/**
	 * Create a ConfigurationController and register it with the ConfigurationPanel,
	 *  as well as registering anything that needs to be listening to it.
	 * @param machine
	 */
	protected void createConfigurationController(AbstractVendingMachine m, ArrayList<ConfigurationListener> configListeners)
	{
		try {
			// Creating the ConfigPanelLogic object.
			if(machine==null||configListeners == null){
				return;
			}
			ConfigPanelLogic configPanelLogic = new ConfigPanelLogic(m.getDisplay());
			
			// Register configPanelLogic with this new listener just created
			Iterator<ConfigurationListener> current = configListeners.iterator();
			while(current.hasNext()){
				configPanelLogic.register(current.next());
			}
			
		} catch (NoSuchHardwareException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 *   - createInventoryController(machine)
	 *  in the correct order. Note that this does NOT include createLogger()
	 */
//	protected AbstractVendingMachine createSFFPC()
//	{
//		return new VendingMachine1(coinDenominations, prices, names);
//	}
	
	//These are all marked deprecated, and the first few have inconsistent constructors. This is intentional, of course, but I made the constructors as close to
	//finished as was reasonable. I had to do instantiate the machine object here, instead of waiting for createMachine() to resolve, because the controllers need
	//the machine to be instantiated to work properly.

	protected AbstractVendingMachine createSFFPC(AbstractVendingMachine m)
	{
		m = new VMRUS_SFF_P_C(locale, coinDenominations);

		controllerCreator(m);

		return m;
	}
	
	protected void controllerCreator(AbstractVendingMachine m)
	{
		//Create the inventory manager
		createInventoryController(m);
		
		//Create a funds controller for coins only
		createFundsController(m, true, false, false, false);

		//Create a selection button controller
		createButtonSelectionController(m);

		//Create the logger
		createLogger(m, logFrequency);
		
	}

	protected AbstractVendingMachine createSFFPCI()
	{
		machine = new VMRUS_SFF_P_CI(locale, coinDenominations);	
		
		//Create the inventory manager
		createInventoryController(machine);
		
		//Create a funds controller for coins only
		createFundsController(machine, true, false, false, false);
		
		//Create a selection button controller
		createButtonSelectionController(machine);
		
		//Create the logger
		createLogger(machine, logFrequency);
		
		//TODO: Displaycontroller(Basic), keyboardController(None), internetController(True)
		return machine;
	}

	protected AbstractVendingMachine createSFFPPI()
	{
		machine = new VMRUS_SFF_P_PI(locale);
		
		//Create the inventory manager
		createInventoryController(machine);
	
		//Create a funds controller for prepaid cards only
		createFundsController(machine, false, true, false, false);
		
		//Create a selection button controller
		createButtonSelectionController(machine);
		
		//Create the logger
		createLogger(machine, logFrequency);
		
		//TODO: Displaycontroller(Basic), keyboardController(None), internetController(True)
		return machine;
	}

	protected AbstractVendingMachine createCOMPMI()
	{
		machine = new VMRUS_COM_P_MI(locale, coinDenominations, billDenominations);	
		
		//Create the inventory manager
		createInventoryController(machine);	
	
		//Create a funds controller for all payment options
		createFundsController(machine, true, true, true, true);
		
		//Create a selection button controller
		createButtonSelectionController(machine);
		
		//Create the logger
		createLogger(machine, logFrequency);
		
		//TODO: Displaycontroller(Basic), keyboardController(Physical), internetController(True)
		return machine;
	}

	protected AbstractVendingMachine createCOMPM()
	{
		machine = new VMRUS_COM_P_M(locale, coinDenominations, billDenominations);		
		
		//Create the inventory manager
		createInventoryController(machine);
	
		//Create a funds controller for all payment options except Paypal
		createFundsController(machine, true, true, false, true);
		
		//Create a selection button controller
		createButtonSelectionController(machine);
		
		//Create the logger
		createLogger(machine, logFrequency);
	
		//TODO: Displaycontroller(Basic), keyboardController(None), internetController(False)
		return machine;
	}

	protected AbstractVendingMachine createCOMCMI()
	{
		machine = new VMRUS_COM_C_MI(locale, coinDenominations, billDenominations);		

		//Create the inventory manager
		createInventoryController(machine);
		
		//Create a funds controller for all payment options
		createFundsController(machine, true, true, true, true);
	
		//Create Code selection controller
		createCodeController(machine, 0);
		
		//Create the logger
		createLogger(machine, logFrequency);
		
		//TODO: Displaycontroller(basic), keyboardController(Physical), internetController(True)
		return machine;
	}

	protected AbstractVendingMachine createCOMCM()
	{
		machine = new VMRUS_COM_C_M(locale, coinDenominations, billDenominations);		

		//Create the inventory manager
		createInventoryController(machine);
		
		//Create a funds controller for all options except Paypal
		createFundsController(machine, true, true, false, true);

		//Create Code selection controller
		createCodeController(machine, 0);
		
		//Create the logger
		createLogger(machine, logFrequency);
		
		//TODO: Displaycontroller(basic), keyboardController(none), internetController(false)
		return machine;
	}
	
	protected AbstractVendingMachine createTOCPMI()
	{
		machine = new VMRUS_TOC_P_MI(locale, coinDenominations, billDenominations);		
		
		//Create the inventory manager
		createInventoryController(machine);
		
		//Create a funds controller for all payment options
		createFundsController(machine, true, true, true, true);
	
		//Create a selection button controller
		createButtonSelectionController(machine);
	
		//Create the logger
		createLogger(machine, logFrequency);
		
		//TODO: Displaycontroller(touchscreen), keyboardController(digital), internetController(True)
		return machine;
	}
	
	protected AbstractVendingMachine createTOCPI()
	{
		machine = new VMRUS_TOC_P_I(locale, coinDenominations, billDenominations);
		
		//Create the inventory manager
		createInventoryController(machine);
		
		//Create a funds controller for all payment options except Paypal
		createFundsController(machine, true, true, false, true);

		//Create a selection button controller
		createButtonSelectionController(machine);

		//Create the logger
		createLogger(machine, logFrequency);
	
		//TODO: Displaycontroller(touchscreen), keyboardController(digital), internetController(False)
		return machine;		
	}

	protected AbstractVendingMachine createTOCCMI()
	{
		machine = new VMRUS_TOC_C_MI(locale, coinDenominations, billDenominations);		
		
		//Create the inventory manager
		createInventoryController(machine);
	
		//Create a funds controller for all payment options
		createFundsController(machine, true, true, true, true);
	
		//Create Code selection controller
		createCodeController(machine, 0);
	
		//Create the logger
		createLogger(machine, logFrequency);
	
		//TODO: Displaycontroller(touchscreen), keyboardController(digital), internetController(True)
		return machine;
	}

	protected AbstractVendingMachine createTOCCp() throws NoSuchHardwareException
	{
		machine = new VMRUS_TOC_CP(locale, coinDenominations, billDenominations);		
		
		//Create the inventory manager
		createInventoryController(machine);	
		
		//Create a funds controller for all payment options except Paypal
		createFundsController(machine, true, true, false, true);
	
		//Create a selection button controller
		createButtonSelectionController(machine);

		//Create Code selection controller
		createCodeController(machine, machine.getNumberOfSelectionButtons());

		//Create the logger
		createLogger(machine, logFrequency);

		//TODO: Displaycontroller(touchscreen), keyboardController(digital), internetController(false)
		return machine;
	}

	protected AbstractVendingMachine createTOCCpI() throws NoSuchHardwareException
	{
		machine = new VMRUS_TOC_CP_I(locale, coinDenominations, billDenominations);

		//Create the inventory manager
		createInventoryController(machine);
		
		//Create a funds controller for all payment options
		createFundsController(machine, true, true, true, true);

		//Create a selection button controller
		createButtonSelectionController(machine);

		//Create Code selection controller
		createCodeController(machine, machine.getNumberOfSelectionButtons());

		//Create the logger
		createLogger(machine, logFrequency);

		//TODO: Displaycontroller(touchscreen), keyboardController(digital), internetController(true)
		return machine;		
	}

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
	
	protected String readString(String line) {
		return readStringArray(line)[0];
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
	public FundsController getFunds(){
		return funds;
	}
	public InventoryController getInventory(){
		return inventoryController;
	}
}
