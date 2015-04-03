package hardware.simulators;

import hardware.channels.CoinChannel;
import hardware.channels.ProductChannel;
import hardware.exceptions.NoSuchHardwareException;
import hardware.exceptions.SimulationException;
import hardware.funds.CoinReceptacle;
import hardware.funds.CoinSlot;
import hardware.racks.CoinRack;
import hardware.racks.ProductRack;
import hardware.ui.DeliveryChute;
import hardware.ui.Display;
import hardware.ui.IndicatorLight;
import hardware.ui.PushButton;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @deprecated This machine is not ready yet
 * Configuration 2 of the Vending Machine
 */
public class VMRUS_SFF_P_CI extends AbstractVendingMachine {
	private CoinSlot coinSlot;
	private CoinReceptacle coinReceptacle, coinStorageBin;
	private DeliveryChute deliveryChute;
	private CoinRack[] coinRacks;
	private Map<Integer, CoinChannel> coinRackChannels;
	private ProductRack[] productRacks;
	private Display display;
	private PushButton[] selectionButtons;
	private PushButton returnButton;
	private IndicatorLight exactChangeLight, outOfOrderLight;
	private IndicatorLight[] outOfProductLights;
	private Socket socket; // to be changed to VMSocket
	// still missing ConfigurationPanel

	protected static int deliveryChuteCapacity = 20;
	protected static int coinReceptacleCapacity = 50;
	protected static int storageBinCapacity = 1000;
	protected static int coinRackCapacity = 20;
	protected static int popRackCapacity = 15;
	protected static int displayCharacters = 30;

	// CONSTRUCTOR
	public VMRUS_SFF_P_CI(int[] coinValues, int[] popCosts, String[] popNames) {

		int numOfProducts = 6;
		// int[] coinValues = { 5, 10, 25, 100, 200 };

		if (coinValues == null || popCosts == null || popNames == null)
			throw new SimulationException("Arguments may not be null");

		if (popCosts.length != numOfProducts)
			throw new SimulationException("Pop costs must have length of "
					+ numOfProducts);

		if (popNames.length != numOfProducts)
			throw new SimulationException("Pop names must have length of "
					+ numOfProducts);

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
			productRacks[i] = new ProductRack(popRackCapacity);
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
		socket = new Socket(); // to be changed to VMSocket
		// NEEDED: instantiate configuration panel

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
	public DeliveryChute getDeliveryChute() {
		return deliveryChute;
	}

	@Override
	public Display getDisplay() {
		return display;
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
	public Socket getSocket() throws NoSuchHardwareException {
		return socket;
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