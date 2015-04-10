package hardware.simulators;

import hardware.channels.CoinChannel;
import hardware.channels.ProductChannel;
import hardware.exceptions.SimulationException;
import hardware.funds.CoinReceptacle;
import hardware.funds.CoinSlot;
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
 * Configuration 2 of the Vending Machine:
 * <ul>
 * <li>Product: Pop</li>
 * <li>ProductRacks: 6</li>
 * <li>SelectionButtons: 6 (One per ProductRack)</li>
 * <li>CoinSlot: Y</li>
 * <li>BillSlot: N</li>
 * <li>CardSlot: N</li>
 * <li>PayPal: N</li>
 * <li>TouchScreen: N</li>
 * <li>Internet: Y</li>
 * <li>OutOfOrderLight: Y</li>
 * <li>ExactChangeLight: Y</li>
 * <li>NoInternetConnectionLight: N (might be added later)</li>
 * <li>OutOfProductLights: 6</li>
 * <li>ReturnButton: Y</li>
 * </ul>
 */
public class VMRUS_SFF_P_CI extends AbstractVendingMachine {
	private CoinSlot coinSlot;
	private CoinReceptacle coinReceptacle, coinStorageBin;
	private DeliveryChute deliveryChute;
	private CoinRack[] coinRacks;
	private Map<Integer, CoinChannel> coinRackChannels;
	private ProductRack[] productRacks;
	private Display display, configPanelDisplay;
	private PushButton[] selectionButtons;
	private PushButton returnButton;
	private IndicatorLight exactChangeLight, outOfOrderLight;
	private IndicatorLight[] outOfProductLights;
	private ConfigurationPanelTransmitter configurationPanelTransmitter;

	protected static int deliveryChuteCapacity = 20;
	protected static int coinReceptacleCapacity = 50;
	protected static int storageBinCapacity = 1000;
	protected static int coinRackCapacity = 20;
	protected static int productRackCapacity = 15;
	protected static int displayCharacters = 30;

	// CONSTRUCTOR
	public VMRUS_SFF_P_CI(Locale locale, int[] coinValues) {

		this.locale = locale;
		
		// Create new Rifffish object for logging
		rifffLogger = new Rifffish("rsh_Dv5iLASQA2FRlYzANQdelAtt");
		
		// Create new machine representation in Rifffish
		rifffMachine = this.getRifffishLogger().createMachine(new Machine("That Vending Machine", "vmrus_sff_p_ci", "In Service", Currency.getInstance(this.getLocale()).getCurrencyCode()));
		
		int numOfProducts = 6;

		if (locale == null || coinValues == null)
			throw new SimulationException("Arguments may not be null");

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

		productRacks = new ProductRack[numOfProducts];
		for (int i = 0; i < numOfProducts; i++) {
			productRacks[i] = new ProductRack(productRackCapacity);
			productRacks[i].connect(new ProductChannel(deliveryChute));
		}

		selectionButtons = new PushButton[numOfProducts];
		for (int i = 0; i < numOfProducts; i++)
			selectionButtons[i] = new PushButton();
		returnButton = new PushButton();

		exactChangeLight = new IndicatorLight();
		outOfOrderLight = new IndicatorLight();
		outOfProductLights = new IndicatorLight[numOfProducts];
		for (int i = 0; i < numOfProducts; i++)
			outOfProductLights[i] = new IndicatorLight();

		display = new Display();
		configurationPanelTransmitter = new ConfigurationPanelTransmitter();

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
	public IndicatorLight getExactChangeLight() {
		return exactChangeLight;
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
	public IndicatorLight getOutOfOrderLight() {
		return outOfOrderLight;
	}

	@Override
	public IndicatorLight getOutOfProductLight(int index) {
		return outOfProductLights[index];
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
	public CoinReceptacle getCoinStorageBin() {
		return coinStorageBin;
	}

	@Override
	public void enableSafety() {
		super.enableSafety();
		coinSlot.disable();
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
		coinSlot.enable();
		deliveryChute.enable();

		for (int i = 0; i < productRacks.length; i++)
			productRacks[i].enable();

		for (int i = 0; i < coinRacks.length; i++)
			coinRacks[i].enable();

		outOfOrderLight.deactivate();
	}

}