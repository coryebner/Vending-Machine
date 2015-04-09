package hardware.simulators;

import hardware.channels.BanknoteChannel;
import hardware.channels.CoinChannel;
import hardware.channels.ProductChannel;
import hardware.exceptions.NoSuchHardwareException;
import hardware.exceptions.SimulationException;
import hardware.funds.*;
import hardware.racks.CoinRack;
import hardware.racks.ProductRack;
import hardware.ui.ConfigurationPanelTransmitter;
import hardware.ui.DeliveryChute;
import hardware.ui.Display;
import hardware.ui.IndicatorLight;
import hardware.ui.PushButton;

import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import SDK.rifffish.Machine;
import SDK.rifffish.Rifffish;

/**
 * Configuration 4 of the Vending Machine:
 * <ul>
 * <li>Product: Pop</li>
 * <li>ProductRacks: 12</li>
 * <li>SelectionButtons: 12 (One per ProductRack)</li>
 * <li>CoinSlot: Y</li>
 * <li>BillSlot: Y</li>
 * <li>CardSlot: Y</li>
 * <li>PayPal: Y</li>
 * <li>TouchScreen: N</li>
 * <li>Internet: Y</li>
 * <li>OutOfOrderLight: Y</li>
 * <li>ExactChangeLight: Y</li>
 * <li>NoInternetConnectionLight: Y</li>
 * <li>OutOfProductLights: 12</li>
 * <li>ReturnButton: Y</li>
 * </ul>
 */
public class VMRUS_COM_P_MI extends AbstractVendingMachine{
	private CoinSlot coinSlot;
	private BanknoteSlot banknoteSlot;
	private BanknoteReceptacle banknoteReceptacle, banknoteStorageBin;
	private CoinReceptacle coinReceptacle, coinStorageBin;
	private CardSlot cardSlot;
	private DeliveryChute deliveryChute;
	private CoinRack[] coinRacks;
	private Map<Integer, CoinChannel> coinRackChannels;
	private ProductRack[] productRacks;
	private Display display, configPanelDisplay;
	private PushButton[] selectionButtons;
	private PushButton returnButton;	
	private IndicatorLight exactChangeLight, outOfOrderLight, noInternetConnectionLight;
	private IndicatorLight[] outOfProductLights;
	private ConfigurationPanelTransmitter configurationPanelTransmitter;

	protected static int banknoteReceptacleCapacity = 20;
	protected static int deliveryChuteCapacity = 20;
	protected static int coinReceptacleCapacity = 50;
	protected static int storageBinCapacity = 1000;
	protected static int coinRackCapacity = 20;
	protected static int productRackCapacity = 15;
	protected static int displayCharacters = 30;

	// CONSTRUCTOR
	public VMRUS_COM_P_MI(Locale locale, int[] coinValues, int[] banknoteValues) {

		this.locale = locale;
		
		// Create new Rifffish object for logging
		rifffLogger = new Rifffish("rsh_Dv5iLASQA2FRlYzANQdelAtt");
		
		// Create new machine representation in Rifffish
		rifffMachine = this.getRifffishLogger().createMachine(new Machine("That Vending Machine", "vmrus_com_p_mi", "In Service", Currency.getInstance(this.getLocale()).getCurrencyCode()));

		int numOfProducts = 12;	
		
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

		banknoteSlot.connect(new BanknoteChannel(banknoteReceptacle));
		banknoteReceptacle.connect(new BanknoteChannel(banknoteStorageBin), new BanknoteChannel(deliveryChute));
		
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
		configPanelDisplay = new Display();
		configurationPanelTransmitter = new ConfigurationPanelTransmitter();

	}

	@Override
	public IndicatorLight getNoInternetConnectionLight(){
		return noInternetConnectionLight;
	}
	
	@Override
	public IndicatorLight getOutOfProductLight(int index) {
		return outOfProductLights[index];
	}
	
	@Override
	public IndicatorLight getOutOfOrderLight() {
		return outOfOrderLight;
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

	@Override
	public ConfigurationPanelTransmitter getConfigurationPanelTransmitter() {
		return configurationPanelTransmitter;
	}

	@Override
	public CardSlot getCardSlot() {
		return cardSlot;
	}
	
	@Override
	public BanknoteReceptacle getBanknoteReceptacle() {
		return banknoteReceptacle;
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
	public Display getConfigPanelDisplay() {
		return configPanelDisplay;
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
