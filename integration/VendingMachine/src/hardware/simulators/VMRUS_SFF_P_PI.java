package hardware.simulators;

import java.util.Locale;

import hardware.channels.ProductChannel;
import hardware.exceptions.NoSuchHardwareException;
import hardware.exceptions.SimulationException;
import hardware.funds.CardSlot;
import hardware.racks.ProductRack;
import hardware.ui.DeliveryChute;
import hardware.ui.Display;
import hardware.ui.IndicatorLight;
import hardware.ui.PushButton;

/**
 * [Ready for integration testing]
 * Configuration 3 of the Vending Machine
 * Product: Pop
 * ProductRacks: 6
 * SelectionButtons: 6 (One per ProductRack)
 * CoinSlot: N
 * BillSlot: N
 * CardSlot: Y
 * PayPal: N
 * TouchScreen: N
 * VMSocket (Internet): Y
 * OutOfOrderLight: Y
 * ExactChangeLight: N
 * NoInternetConnectionLight: N (might be added later)
 * OutOfProductLights: 6
 * ReturnButton: Y
 * 
 * Still Missing: ConfigurationPanel
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
	private VMSocket socket;
	// still missing ConfigurationPanel

	protected static int deliveryChuteCapacity = 20;
	protected static int productRackCapacity = 15;
	protected static int displayCharacters = 30;

	// CONSTRUCTOR
	public VMRUS_SFF_P_PI(Locale locale) {

		this.locale = locale;
		
		int numOfProducts = 6;
		
		if (locale == null)
			throw new SimulationException("Arguments may not be null");

		cardSlot = new CardSlot();
		deliveryChute = new DeliveryChute(deliveryChuteCapacity);
		
		productRacks = new ProductRack[numOfProducts];
		for (int i = 0; i < numOfProducts; i++) {
			productRacks[i] = new ProductRack(productRackCapacity);
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
		socket = new VMSocket();
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
	public VMSocket getSocket() throws NoSuchHardwareException {
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