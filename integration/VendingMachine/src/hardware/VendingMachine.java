package hardware;

public abstract class VendingMachine {

	protected boolean safetyOn = false;
	
	/**
	 * Returns the out of order light.
	 * 
	 * @throws NoSuchHardwareException
	 */
	// public IndicatorLight getOutOfOrderLight() {
	public Object getOutOfOrderLight() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the exact change light.
	 * 
	 * @throws NoSuchHardwareException
	 */
	// public IndicatorLight getExactChangeLight() throws NoSuchHardwareException {
	public Object getExactChangeLight() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the no Internet connection light.
	 * 
	 * @throws NoSuchHardwareException
	 */
	// public IndicatorLight getNoInternetConnectionLight() throws NoSuchHardwareException {
	public Object getNoInternetConnectionLight() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns an out of product light at the indicated index.
	 * 
	 * @param index
	 *            The index of the desired out of product light.
	 * @throws NoSuchHardwareException
	 */
	// public IndicatorLight getOutOfProductLight(int index) throws NoSuchHardwareException {
	public Object getOutOfProductLight(int index) throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns a selection button at the indicated index.
	 * 
	 * @param index
	 *            The index of the desired selection button.
	 * @throws NoSuchHardwareException
	 */
	// public PushButton getSelectionButton(int index) throws NoSuchHardwareException {
	public Object getSelectionButton(int index) throws NoSuchHardwareException {
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
	public Object getProductRack(int index) throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns the return button.
	 * 
	 * @throws NoSuchHardwareException
	 */
	// public PushButton getReturnButton() throws NoSuchHardwareException {
	public Object getReturnButton() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns the push button code interpreter.
	 * 
	 * @throws NoSuchHardwareException
	 */
	// public PushButtonCodeInterpreter getPushButtonCodeInterpreter() throws NoSuchHardwareException {
	public Object getPushButtonCodeInterpreter() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns the coin slot.
	 * 
	 * @throws NoSuchHardwareException
	 */
	// public CoinSlot getCoinSlot() {
	public Object getCoinSlot() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns the bill slot.
	 * 
	 * @throws NoSuchHardwareException
	 */
	// public BillSlot getBillSlot() {
	public Object getBillSlot() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns the slot for magnetic stripe cards.
	 * 
	 * @throws NoSuchHardwareException
	 */
	// public CardSlot getCardSlot() {
	public Object getCardSlot() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the coin receptacle.
	 * 
	 * @throws NoSuchHardwareException
	 */
	// public CoinReceptacle getCoinReceptacle() throws NoSuchHardwareException {
	public Object getCoinReceptacle() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the bill receptacle.
	 * 
	 * @throws NoSuchHardwareException
	 */
	// public BillReceptacle getBillReceptacle() throws NoSuchHardwareException {
	public Object getBillReceptacle() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns the storage bin.
	 * 
	 * @throws NoSuchHardwareException
	 */
	// public CoinReceptacle getStorageBin() throws NoSuchHardwareException {
	public Object getStorageBin() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the delivery chute.
	 * 
	 * @throws NoSuchHardwareException
	 */
	// public DeliveryChute getDeliveryChute() throws NoSuchHardwareException {
	public Object getDeliveryChute() throws NoSuchHardwareException {
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
	// public CoinRack getCoinRack(int index) throws NoSuchHardwareException {
	public Object getCoinRack(int index) throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}

	/**
	 * Returns the 1-line textual display.
	 * 
	 * @throws NoSuchHardwareException
	 */
	// public Display getDisplay() throws NoSuchHardwareException {
	public Object getDisplay() throws NoSuchHardwareException {
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
	// public Socket getSocket() throws NoSuchHardwareException {
	public Object getSocket() throws NoSuchHardwareException {
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
	
	/**
	 * Returns the number of product racks.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public int getNumberOfProductRacks() throws NoSuchHardwareException {
		throw new NoSuchHardwareException();
	}
	
	/**
	 * Returns the number of push buttons.
	 * 
	 * @throws NoSuchHardwareException
	 */
	public int getNumberOfPushButtons() throws NoSuchHardwareException {
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
		safetyOn = true;
		// subclasses should enable hardware components here
	}

	/**
	 * Returns whether the safety is currently engaged.
	 */
	public boolean isSafetyEnabled() {
		return safetyOn;
	}

}
