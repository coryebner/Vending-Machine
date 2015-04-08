package hardware.simulators;

import java.util.Currency;
import java.util.Locale;

import SDK.rifffish.Machine;
import SDK.rifffish.Rifffish;
import hardware.channels.ProductChannel;
import hardware.exceptions.NoSuchHardwareException;
import hardware.exceptions.SimulationException;
import hardware.funds.CardSlot;
import hardware.racks.ProductRack;
import hardware.ui.ConfigurationPanelTransmitter;
import hardware.ui.DeliveryChute;
import hardware.ui.Display;
import hardware.ui.IndicatorLight;
import hardware.ui.PushButton;

/**
 * Configuration 3 of the Vending Machine:
 * <ul>
 * <li>Product: Pop</li>
 * <li>ProductRacks: 6</li>
 * <li>SelectionButtons: 6 (One per ProductRack)</li>
 * <li>oinSlot: N</li>
 * <li>BillSlot: N</li>
 * <li>CardSlot: Y</li>
 * <li>PayPal: N</li>
 * <li>TouchScreen: N</li>
 * <li>VMSocket (Internet): Y</li>
 * <li>OutOfOrderLight: Y</li>
 * <li>ExactChangeLight: N</li>
 * <li>NoInternetConnectionLight: N (might be added later)</li>
 * <li>OutOfProductLights: 6</li>
 * <li>ReturnButton: Y</li>
 * </ul> 
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
	private ConfigurationPanelTransmitter configurationPanelTransmitter;

	protected static int deliveryChuteCapacity = 20;
	protected static int productRackCapacity = 15;
	protected static int displayCharacters = 30;

	// CONSTRUCTOR
	public VMRUS_SFF_P_PI(Locale locale) {

		this.locale = locale;
		
		// Create new Rifffish object for logging
		rifffLogger = new Rifffish("rsh_Dv5iLASQA2FRlYzANQdelAtt");
		
		// Create new machine representation in Rifffish
		rifffMachine = this.getRifffishLogger().createMachine(new Machine("That Vending Machine", "vmrus_sff_p_pi", "In Service", Currency.getInstance(this.getLocale()).getCurrencyCode()));
		
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
		configurationPanelTransmitter = new ConfigurationPanelTransmitter();

	}

	@Override
	public CardSlot getCardSlot() throws NoSuchHardwareException {
		return cardSlot;
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
