package hardware.simulators;

import hardware.channels.ProductChannel;
import hardware.exceptions.NoSuchHardwareException;
import hardware.exceptions.SimulationException;
import hardware.funds.CardSlot;
import hardware.racks.ProductRack;
import hardware.ui.DeliveryChute;
import hardware.ui.Display;
import hardware.ui.IndicatorLight;
import hardware.ui.PushButton;

import java.net.Socket;

/**
 * @deprecated This machine is not ready yet
 * Configuration 3 of the Vending Machine
 */
public class VMRUS_SFF_P_PI extends AbstractVendingMachine {
	private DeliveryChute deliveryChute;
	private CardSlot cardSlot;
	private ProductRack[] productRacks;
	private Display display;
	private PushButton[] selectionButtons;
	private PushButton returnButton;
	private IndicatorLight outOfOrderLight;
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
	public VMRUS_SFF_P_PI(int[] popCosts, String[] popNames) {

		int numOfProducts = 6;
		
		if (popCosts == null || popNames == null)
			throw new SimulationException("Arguments may not be null");

		if (popCosts.length != numOfProducts)
			throw new SimulationException("Pop costs must have length of "
					+ numOfProducts);

		if (popNames.length != numOfProducts)
			throw new SimulationException("Pop names must have length of "
					+ numOfProducts);

		cardSlot = new CardSlot();
		deliveryChute = new DeliveryChute(deliveryChuteCapacity);
		
		productRacks = new ProductRack[numOfProducts];
		for (int i = 0; i < numOfProducts; i++) {
			productRacks[i] = new ProductRack(popRackCapacity);
			productRacks[i].connect(new ProductChannel(deliveryChute));
		}

		selectionButtons = new PushButton[numOfProducts];
		for (int i = 0; i < numOfProducts; i++)
			selectionButtons[i] = new PushButton();
		returnButton = new PushButton();

		outOfOrderLight = new IndicatorLight();
		outOfProductLights = new IndicatorLight[numOfProducts];
		for (int i = 0; i < numOfProducts; i++)
			outOfProductLights[i] = new IndicatorLight();

		display = new Display();
		socket = new Socket(); // to be changed to VMSocket
		// NEEDED: instantiate configuration panel

	}

	@Override
	public CardSlot getCardSlot() throws NoSuchHardwareException {
		return cardSlot;
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
	public void enableSafety() {
		super.enableSafety();
		cardSlot.disable();
		deliveryChute.disable();

		for (int i = 0; i < productRacks.length; i++)
			productRacks[i].disable();

		outOfOrderLight.activate();
	}

	@Override
	public void disableSafety() {
		super.disableSafety();
		cardSlot.enable();
		deliveryChute.enable();

		for (int i = 0; i < productRacks.length; i++)
			productRacks[i].enable();

		outOfOrderLight.deactivate();
	}

}