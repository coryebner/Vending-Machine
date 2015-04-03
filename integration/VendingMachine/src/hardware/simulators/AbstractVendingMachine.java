package hardware.simulators;

import java.net.Socket;
import java.util.Locale;

import hardware.exceptions.NoSuchHardwareException;
import hardware.funds.BanknoteReceptacle;
import hardware.funds.BanknoteSlot;
import hardware.funds.CardSlot;
import hardware.funds.CoinReceptacle;
import hardware.funds.CoinSlot;
import hardware.racks.CoinRack;
import hardware.racks.ProductRack;
import hardware.ui.DeliveryChute;
import hardware.ui.Display;
import hardware.ui.IndicatorLight;
import hardware.ui.PushButton;
import hardware.ui.PushButtonCodeInterpreter;

public abstract class AbstractVendingMachine {

	protected boolean safetyOn = false;
	protected Locale locale;
	/**
	 * Returns the out of order light.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public IndicatorLight getOutOfOrderLight() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the exact change light.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public IndicatorLight getExactChangeLight() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the no Internet connection light.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public IndicatorLight getNoInternetConnectionLight() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns an out of product light at the indicated index.
	 * 
	 * @param index
	 *            The index of the desired out of product light.
	 * @throws NoSuchHardwareException
	 */
	public IndicatorLight getOutOfProductLight(int index) throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns a selection button at the indicated index.
	 * 
	 * @param index
	 *            The index of the desired selection button.
	 * @throws NoSuchHardwareException
	 */
	public PushButton getSelectionButton(int index) throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns a product rack at the indicated index.
	 * 
	 * @param index
	 *            The index of the desired product rack.
	 * @throws NoSuchHardwareException 
	 */
	// public ProductRack getProductRack(int index) throws NoSuchHardwareException {
	public ProductRack getProductRack(int index) throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns the return button.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public PushButton getReturnButton() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns the push button code interpreter.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public PushButtonCodeInterpreter getPushButtonCodeInterpreter() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns the coin slot.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public CoinSlot getCoinSlot() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns the banknote slot.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public BanknoteSlot getBanknoteSlot() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns the slot for magnetic stripe cards.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public CardSlot getCardSlot() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the coin receptacle.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public CoinReceptacle getCoinReceptacle() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the banknote receptacle.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public BanknoteReceptacle getBanknoteReceptacle() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the banknote storage bin.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public BanknoteReceptacle getBanknoteStorageBin() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns the coin storage bin.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public CoinReceptacle getCoinStorageBin() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the delivery chute.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public DeliveryChute getDeliveryChute() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns a coin rack at the indicated index.
	 * 
	 * @param index
	 *            The index of the desired coin rack.
	 * @throws NoSuchHardwareException
	 * @throws IndexOutOfBoundsException
	 *             if the index < 0 or the index >= number of coin racks.
	 */
	public CoinRack getCoinRack(int index) throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the 1-line textual display.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public Display getDisplay() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the keyboard.
	 * 
	 * @throws NoSuchHardwareException
	 */
	// public Keyboard getKeyboard() throws NoSuchHardwareException {
	public Object getKeyboard() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the socket.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public Socket getSocket() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns the configuration panel.
	 * 
	 * @throws NoSuchHardwareException
	 */
	// public ConfigurationPanel getConfigurationPanel() throws NoSuchHardwareException {
	public Object getConfigurationPanel() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	/**
	 * Returns the number of product racks.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public int getNumberOfProductRacks() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns the number of selection buttons.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public int getNumberOfSelectionButtons() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the number of out of product lights.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public int getNumberOfOutOfProductLights() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns the number of coin racks.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public int getNumberOfCoinRacks() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
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
	 */
	public boolean isSafetyEnabled() {
		return safetyOn;
	}

}
