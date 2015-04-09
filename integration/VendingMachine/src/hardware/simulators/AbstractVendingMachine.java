package hardware.simulators;

import java.util.Locale;

import SDK.rifffish.Machine;
import SDK.rifffish.Rifffish;
import hardware.exceptions.NoSuchHardwareException;
import hardware.funds.BanknoteReceptacle;
import hardware.funds.BanknoteSlot;
import hardware.funds.CardSlot;
import hardware.funds.CoinReceptacle;
import hardware.funds.CoinSlot;
import hardware.racks.CoinRack;
import hardware.racks.ProductRack;
import hardware.ui.ConfigurationPanelTransmitter;
import hardware.ui.DeliveryChute;
import hardware.ui.Display;
import hardware.ui.IndicatorLight;
import hardware.ui.PushButton;
import hardware.ui.PushButtonCodeInterpreter;

public abstract class AbstractVendingMachine {

	protected boolean safetyOn = false;
	protected Locale locale;
	protected Rifffish rifffLogger;
	protected Machine rifffMachine;

	/**
	 * Returns the out of order light.
	 * 
	 * @return the out of order light.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public IndicatorLight getOutOfOrderLight() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the exact change light.
	 * 
	 * @return the exact change light.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public IndicatorLight getExactChangeLight() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the no Internet connection light.
	 * 
	 * @return the no Internet connection light.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public IndicatorLight getNoInternetConnectionLight()
			throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns an out of product light at the indicated index.
	 * 
	 * @param index
	 *            The index of the desired out of product light.
	 * @return the out of product light at the indicated index.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public IndicatorLight getOutOfProductLight(int index)
			throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns a selection button at the indicated index.
	 * 
	 * @param index
	 *            The index of the desired selection button.
	 * @return the selection button at the indicated index.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public PushButton getSelectionButton(int index)
			throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns a product rack at the indicated index.
	 * 
	 * @param index
	 *            The index of the desired product rack.
	 * @return the product rack at the indicated index.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public ProductRack getProductRack(int index) throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the return button.
	 * 
	 * @return the return button.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public PushButton getReturnButton() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the push button code interpreter.
	 * 
	 * @return the push button code interpreter.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public PushButtonCodeInterpreter getPushButtonCodeInterpreter()
			throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the coin slot.
	 * 
	 * @return the coin slot.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public CoinSlot getCoinSlot() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the banknote slot.
	 * 
	 * @return the banknote slot.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public BanknoteSlot getBanknoteSlot() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the slot for magnetic stripe cards.
	 * 
	 * @return the card slot.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public CardSlot getCardSlot() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the coin receptacle.
	 * 
	 * @return the coin receptacle.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public CoinReceptacle getCoinReceptacle() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the banknote receptacle.
	 * 
	 * @return the banknote receptacle.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public BanknoteReceptacle getBanknoteReceptacle()
			throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the banknote storage bin.
	 * 
	 * @return the banknote storage bin.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public BanknoteReceptacle getBanknoteStorageBin()
			throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the coin storage bin.
	 * 
	 * @return the coin storage bin.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public CoinReceptacle getCoinStorageBin() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the delivery chute.
	 * 
	 * @return the delivery chute.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public DeliveryChute getDeliveryChute() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns a coin rack at the indicated index.
	 * 
	 * @param index
	 *            The index of the desired coin rack.
	 * @return the coin rack at the indicated index.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 * @throws IndexOutOfBoundsException
	 *             if the index < 0 or the index >= number of coin racks.
	 */
	public CoinRack getCoinRack(int index) throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the textual display.
	 * 
	 * @return the display.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public Display getDisplay() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the configuration panel transmitter.
	 * 
	 * @return the configuration panel transmitter.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public ConfigurationPanelTransmitter getConfigurationPanelTransmitter() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the locale.
	 * 
	 * @return the locale.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * Returns the number of product racks.
	 * 
	 * @return the number of product racks.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public int getNumberOfProductRacks() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the number of selection buttons.
	 * 
	 * @return the number of selection buttons.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public int getNumberOfSelectionButtons() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the number of out of product lights.
	 * 
	 * @return the number of out of product lights.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public int getNumberOfOutOfProductLights() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the number of coin racks.
	 * 
	 * @return the number of coin racks.
	 * @throws NoSuchHardwareException
	 *             if this hardware component does not exist in the
	 *             configuration of the vending machine.
	 */
	public int getNumberOfCoinRacks() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns the Rifffish object for logging purposes.
	 * 
	 * @return the Rifffish object.
	 */
	public Rifffish getRifffishLogger(){
		return rifffLogger;
	}
	
	/**
	 * Returns the Rifffish Machine object for logging purposes.
	 * 
	 * @return the Rifffish Machine object.
	 */
	public Machine getRifffishMachine(){
		return rifffMachine;
	}

	/**
	 * Disables all the components of the hardware that involve physical
	 * movements.
	 */
	public void enableSafety() {
		safetyOn = true;
		// subclasses should disable hardware components here
	}

	/**
	 * Enables all the components of the hardware that involve physical
	 * movements.
	 */
	public void disableSafety() {
		safetyOn = false;
		// subclasses should enable hardware components here
	}

	/**
	 * Returns whether the safety is currently engaged.
	 * 
	 * @return <code>true</code> if safety is enabled; <code>false</code>
	 *         otherwise.
	 */
	public boolean isSafetyEnabled() {
		return safetyOn;
	}

}
