package hardware.simulators;

import hardware.channels.CoinChannel;
import hardware.channels.ProductChannel;
import hardware.exceptions.SimulationException;
import hardware.funds.*;
import hardware.racks.CoinRack;
import hardware.racks.ProductRack;
import hardware.ui.DeliveryChute;
import hardware.ui.Display;
import hardware.ui.IndicatorLight;
import hardware.ui.PushButton;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @deprecated This machine is not ready yet
 * Configuration 10 of the Vending Machine
 * Product: Candy
 * ProductRacks: 24
 * SelectionButtons: 24 (One per ProductRack)
 * CoinSlot: Y
 * BillSlot: Y
 * CardSlot: Y
 * PayPal: Y
 * TouchScreen: Y
 * VMSocket (Internet): Y
 * OutOfOrderLight: Y
 * ExactChangeLight: Y
 * NoInternetConnectionLight: Y
 * OutOfProductLights: 24
 * ReturnButton: Y
 * 
 * Still Missing: ConfigurationPanel
 * Still Missing: Banknote Hardware Connections
 */
public class VMRUS_TOC_C_MI extends AbstractVendingMachine{
	private CoinSlot coinSlot;
	private BanknoteSlot banknoteSlot;
	private BanknoteReceptacle banknoteReceptacle, banknoteStorageBin;
	private CoinReceptacle coinReceptacle, coinStorageBin;
	private CardSlot cardSlot;
	private DeliveryChute deliveryChute;
	private CoinRack[] coinRacks;
	private Map<Integer, CoinChannel> coinRackChannels;
	private ProductRack[] productRacks;
	private Display display;
    private PushButton[] selectionButtons;
    private PushButton returnButton;
	private IndicatorLight exactChangeLight, outOfOrderLight, noInternetConnectionLight;
	private IndicatorLight[] outOfProductLights;
	private VMSocket socket;
	// still missing ConfigurationPanel

	protected static int banknoteReceptacleCapacity = 20;
	protected static int deliveryChuteCapacity = 20;
	protected static int coinReceptacleCapacity = 50;
	protected static int storageBinCapacity = 1000;
	protected static int coinRackCapacity = 20;
	protected static int productRackCapacity = 15;
	protected static int displayCharacters = 30;

	// CONSTRUCTOR
	public VMRUS_TOC_C_MI(Locale locale, int[] coinValues, int[] banknoteValues) {

		this.locale = locale;
		
		int numOfProducts = 24;	
		
		if (locale == null || coinValues == null || banknoteValues == null)
			throw new SimulationException("Arguments may not be null");
		
		banknoteSlot = new BanknoteSlot(banknoteValues);
		banknoteReceptacle = new BanknoteReceptacle(banknoteReceptacleCapacity);
		banknoteStorageBin = new BanknoteReceptacle(storageBinCapacity);
		
		cardSlot = new CardSlot();
		
		coinSlot = new CoinSlot(coinValues);
		coinReceptacle = new CoinReceptacle(coinReceptacleCapacity);
		coinStorageBin = new CoinReceptacle(storageBinCapacity);
		
		deliveryChute = new DeliveryChute(deliveryChuteCapacity);
		coinRacks = new CoinRack[coinValues.length];
		coinRackChannels = new HashMap<Integer, CoinChannel>();
		for (int i = 0; i < coinValues.length; i++) {
			coinRacks[i] = new CoinRack(coinRackCapacity);
			coinRacks[i].connect(new CoinChannel(deliveryChute));
			coinRackChannels.put(new Integer(coinValues[i]), new CoinChannel(
					coinRacks[i]));
		}
		coinSlot.connect(new CoinChannel(coinReceptacle), new CoinChannel(
				deliveryChute));
		coinReceptacle.connect(coinRackChannels,
				new CoinChannel(deliveryChute), new CoinChannel(coinStorageBin));

		/* NEEDED: Banknote Hardware Connections
		banknoteSlot.connect(new BanknoteChannel(banknoteReceptacle new CoinChannel(coinStorageBin));
		banknoteReceptacle.connect(new CoinChannel(deliveryChute), new CoinChannel(coinStorageBin));
		*/
		
		productRacks = new ProductRack[numOfProducts];
		for (int i = 0; i < numOfProducts; i++) {
			productRacks[i] = new ProductRack(productRackCapacity);
			productRacks[i].connect(new ProductChannel(deliveryChute));
		}

		selectionButtons = new PushButton[numOfProducts];
		for (int i = 0; i < numOfProducts; i++)
			selectionButtons[i] = new PushButton();
		returnButton = new PushButton();

		noInternetConnectionLight = new IndicatorLight();	
		exactChangeLight = new IndicatorLight();
		outOfOrderLight = new IndicatorLight();
		outOfProductLights = new IndicatorLight[numOfProducts];
		for (int i = 0; i < numOfProducts; i++)
			outOfProductLights[i] = new IndicatorLight();

		display = new Display();
		socket = new VMSocket();
		// NEEDED: instantiate configuration panel

	}
	
	@Override
	public IndicatorLight getNoInternetConnectionLight(){
		return noInternetConnectionLight;
	}
	
	@Override
	public IndicatorLight getOutOfOrderLight() {
		return outOfOrderLight;
	}
	
	@Override
	public IndicatorLight getOutOfProductLight(int index) {
		return outOfProductLights[index];
	}

	@Override
	public IndicatorLight getExactChangeLight() {
		return exactChangeLight;
	}
	
	@Override
	public CoinRack getCoinRack(int index) {
		return coinRacks[index];
	}

	@Override
	public CoinReceptacle getCoinReceptacle() {
		return coinReceptacle;
	}

	@Override
	public CoinSlot getCoinSlot() {
		return coinSlot;
	}

	// NEEDED: configuration panel
	// @Override
	// public Object getConfigurationPanel() throws NoSuchHardwareException {
	// return configurationPanel;
	// }

	@Override
	public CardSlot getCardSlot() {
		return cardSlot;
	}

	@Override
	public DeliveryChute getDeliveryChute() {
		return deliveryChute;
	}

	@Override
	public Display getDisplay() {
		return display;
	}

	@Override
	public int getNumberOfCoinRacks() {
		return coinRacks.length;
	}
	
	@Override
	public int getNumberOfOutOfProductLights() {
		return outOfProductLights.length;
	}

	@Override
	public int getNumberOfProductRacks() {
		return productRacks.length;
	}

	@Override
	public int getNumberOfSelectionButtons() {
		return selectionButtons.length;
	}

	@Override
	public ProductRack getProductRack(int index) {
		return productRacks[index];
	}

	@Override
	public PushButton getReturnButton() {
		return returnButton;
	}

	@Override
	public PushButton getSelectionButton(int index) {
		return selectionButtons[index];
	}
	
	@Override
	public VMSocket getSocket() {
		return socket;
	}
	
	@Override
	public BanknoteReceptacle getBanknoteReceptacle() {
		return banknoteReceptacle;
	}
	
	@Override
	public BanknoteReceptacle getBanknoteStorageBin() {
		return banknoteStorageBin;
	}
	
	@Override
	public BanknoteSlot getBanknoteSlot(){
		return banknoteSlot;
	}
	
	@Override
	public CoinReceptacle getCoinStorageBin() {
		return coinStorageBin;
	}

	@Override
	public void enableSafety() {
		super.enableSafety();
		cardSlot.disable();
		coinSlot.disable();
		banknoteSlot.disable();
		deliveryChute.disable();

		for (int i = 0; i < productRacks.length; i++)
			productRacks[i].disable();

		for (int i = 0; i < coinRacks.length; i++)
			coinRacks[i].disable();

		outOfOrderLight.activate();
	}

	@Override
	public void disableSafety() {
		super.disableSafety();
		cardSlot.enable();
		coinSlot.enable();
		banknoteSlot.enable();
		deliveryChute.enable();

		for (int i = 0; i < productRacks.length; i++)
			productRacks[i].enable();

		for (int i = 0; i < coinRacks.length; i++)
			coinRacks[i].enable();

		outOfOrderLight.deactivate();
	}

}