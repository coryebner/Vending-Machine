package gui.test;

import hardware.AbstractVendingMachine;
import hardware.channels.CoinChannel;
import hardware.channels.PopCanChannel;
import hardware.channels.ProductChannel;
import hardware.exceptions.NoSuchHardwareException;
import hardware.exceptions.SimulationException;
import hardware.funds.*;
import hardware.racks.CoinRack;
import hardware.racks.PopCanRack;
import hardware.racks.ProductRack;
import hardware.ui.DeliveryChute;
import hardware.ui.Display;
import hardware.ui.IndicatorLight;
import hardware.ui.PushButton;
import hardware.ui.PushButtonCodeInterpreter;

import java.util.HashMap;
import java.util.Map;

public class CandyVendingMachine extends AbstractVendingMachine{
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
    private PushButton[] characterButtons;
    private PushButtonCodeInterpreter interpreter;
	private PushButton returnButton;
	
	private IndicatorLight exactChangeLight, outOfOrderLight;
	//private IndicatorLight[] outOfProductLights;
	// still missing ConfigurationPanel

	protected static int banknoteReceptacleCapacity = 20;
	protected static int deliveryChuteCapacity = 20;
	protected static int coinReceptacleCapacity = 50;
	protected static int storageBinCapacity = 1000;
	protected static int coinRackCapacity = 20;
	protected static int productRackCapacity = 15;
	protected static int displayCharacters = 30;

	// CONSTRUCTOR
	public CandyVendingMachine(int[] coinValues, int[] banknoteValues) {

		int numOfProducts = 24;	

		banknoteSlot = new BanknoteSlot(banknoteValues);
		banknoteReceptacle = new BanknoteReceptacle(banknoteReceptacleCapacity);
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

		productRacks = new ProductRack[numOfProducts];
		for (int i = 0; i < numOfProducts; i++) {
			productRacks[i] = new ProductRack(productRackCapacity);
			productRacks[i].connect(new ProductChannel(deliveryChute));
			// NEEDED: set price for productRacks[i]
			// NEEDED: set name for productRacks[i]
		}

		returnButton = new PushButton();

		exactChangeLight = new IndicatorLight();
		outOfOrderLight = new IndicatorLight();
		/*
		outOfProductLights = new IndicatorLight[numOfProducts];
		for (int i = 0; i < numOfProducts; i++)
			outOfProductLights[i] = new IndicatorLight();
		*/

		display = new Display();
		// NEEDED: instantiate configuration panel

		characterButtons = new PushButton[16];
		for(int i = 0; i < 16; i++)
		    characterButtons[i] = new PushButton();
		
		Map<PushButton, Character> map = new HashMap<>();
		map.put(characterButtons[0], 'A');
		map.put(characterButtons[1], 'B');
		map.put(characterButtons[2], 'C');
		map.put(characterButtons[3], 'D');
		map.put(characterButtons[4], 'E');
		map.put(characterButtons[5], 'F');
		map.put(characterButtons[6], '0');
		map.put(characterButtons[7], '1');
		map.put(characterButtons[8], '2');
		map.put(characterButtons[9], '3');
		map.put(characterButtons[10], '4');
		map.put(characterButtons[11], '5');
		map.put(characterButtons[12], '6');
		map.put(characterButtons[13], '7');
		map.put(characterButtons[14], '8');
		map.put(characterButtons[15], '9');
		
		interpreter = new PushButtonCodeInterpreter(map);
	}
	
	@Override
	public PushButtonCodeInterpreter getPushButtonCodeInterpreter() {
		return interpreter;
	}
	
	/*
	@Override
	public IndicatorLight getOutOfProductLight(int index) throws NoSuchHardwareException {
		return outOfProductLights[index];
	}
	*/
	
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
