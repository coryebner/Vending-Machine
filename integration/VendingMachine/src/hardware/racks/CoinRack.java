package hardware.racks;

import hardware.acceptors.AbstractCoinAcceptor;
import hardware.channels.CoinChannel;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;
import hardware.funds.Coin;

/**
 * Represents a device that stores coins of a particular denomination to
 * dispense them as change.
 * <p>
 * Coin racks can receive coins from other sources. To simplify the simulation,
 * no check is performed on the value of each coin, meaning it is an external
 * responsibility to ensure the correct routing of coins.
 */
public class CoinRack extends AbstractRack<CoinRackListener, Coin, CoinChannel> implements AbstractCoinAcceptor {
	/**
	 * Creates a coin rack with the indicated maximum capacity.
	 * @param capacity
	 * 		  the indicated maximum capacity of the rack.
	 */
	public CoinRack(int capacity) {
		super(capacity);
	}

	/**
	 * Causes the indicated coin to be added into the rack. If successful, a
	 * "coinAdded" event is announced to its listeners. If a successful coin
	 * addition causes the rack to become full, a "coinsFull" event is announced
	 * to its listeners.
	 * 
	 * @throws DisabledException
	 *             if the coin rack is currently disabled.
	 * @throws CapacityExceededException
	 *             if the coin rack is already full.
	 */
	@Override
	public void acceptCoin(Coin coin) throws CapacityExceededException,
	DisabledException {

		/**
		 * Adds a coin to the current rack; invokes super class addToRack(<U extends IRackable>)
		 * @see AbstractRack
		 */
		addToRack(coin);
		notifyCoinAdded(coin);

		if(getQueue().size() >= getMaxCapacity())
			notifyCoinsFull();
	}

	/**
	 * Releases a single coin from this coin rack. If successful, a
	 * "coinRemoved" event is announced to its listeners. If a successful coin
	 * removal causes the rack to become empty, a "coinsEmpty" event is
	 * announced to its listeners.
	 * 
	 * @throws CapacityExceededException
	 *             if the output channel is unable to accept another coin.
	 * @throws EmptyException
	 *             if no coins are present in the rack to release.
	 * @throws DisabledException
	 *             if the rack is currently disabled.
	 */
	public void releaseCoin() throws CapacityExceededException, EmptyException,
	DisabledException {

		/* 
		 * Retrieve a coin from the current rack invokes super class removeFromRack() 
		 * @see AbstractRack
		 */
		Coin coin = removeFromRack();

		notifyCoinRemoved(coin);
		getSink().deliver(coin);

		if(getQueue().isEmpty())
			notifyCoinsEmpty();
	}

	/**
	 * notifyCoinAdded
	 * 
	 * @synopsis
	 * 			Notification method used to custom tailor AbstractHardware associated reflection call
	 * 			- with the assumption that listeners registered to the affected CoinRack as defined in 
	 * 		 	CoinRackListener class will be notified (triggering a method invocation to coinAdded).
	 * 
	 * @param 	coin
	 * 
	 * @see		AbstractHardware, CoinRackListener, CoinRackListenerStub, AbstractStub
	 */
	private void notifyCoinAdded(Coin coin) {
		Class<?>[] parameterTypes =
				new Class<?>[] { CoinRack.class, Coin.class };
		Object[] args = new Object[] { this, coin };
		notifyListeners(CoinRackListener.class, "coinAdded", parameterTypes, args);
	}

	/**
	 * notifyCoinRemoved
	 * 
	 * @synopsis
	 * 			Notification method used to custom tailor AbstractHardware associated reflection call
	 * 			- with the assumption that listeners registered to the affected CoinRack as defined in 
	 * 		 	CoinRackListener class will be notified (triggering a method invocation to coinRemoved).
	 * 
	 * @param 	coin
	 * 
	 * @see		AbstractHardware, CoinRackListener, CoinRackListenerStub, AbstractStub
	 */
	private void notifyCoinRemoved(Coin coin) {
		Class<?>[] parameterTypes =
				new Class<?>[] { CoinRack.class, Coin.class };
		Object[] args = new Object[] { this, coin };
		notifyListeners(CoinRackListener.class, "coinRemoved", parameterTypes, args);
	}

	/**
	 * notifyCoinsFull
	 * 
	 * @synopsis
	 * 			Notification method used via AbstractHardware reflection API (notifyListeners)
	 * 			to signal that the associated coin rack is full of coins.
	 * 
	 * 
	 * @see		AbstractHardware, CoinRackListener, CoinRackListenerStub, AbstractStub
	 */
	private void notifyCoinsFull() {
		Class<?>[] parameterTypes = new Class<?>[] { CoinRack.class };
		Object[] args = new Object[] { this };
		notifyListeners(CoinRackListener.class, "coinsFull", parameterTypes, args);
	}

	/**
	 * notifyCoinsEmpty
	 * 
	 * @synopsis
	 * 			Notification method used via AbstractHardware reflection API (notifyListeners)
	 * 			to signal that the associated coin rack is empty of coins.
	 * 
	 * 
	 * @see		AbstractHardware, CoinRackListener, CoinRackListenerStub, AbstractStub
	 */
	private void notifyCoinsEmpty() {
		Class<?>[] parameterTypes = new Class<?>[] { CoinRack.class };
		Object[] args = new Object[] { this };
		notifyListeners(CoinRackListener.class, "coinsEmpty", parameterTypes, args);
	}

}
