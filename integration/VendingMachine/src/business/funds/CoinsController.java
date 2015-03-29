package business.funds;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.funds.Coin;
import hardware.funds.CoinReceptacle;
import hardware.funds.CoinReceptacleListener;

/** Description of PrepaidController
 * @author Jan Clarin
 * @author Andrei (Andy) Savu
 * @author Arthur Lee
 * @author Olabode (Sam) Adegbayike
 * 
 * Class to interact with hardware to conduct a coins Transaction
 */
public class CoinsController implements CoinReceptacleListener{

	private CoinRackController[] coinRackControllers;	// Storage of the coin racks
	private boolean exactChangeStatus;
	private boolean fullOfChangeStatus;
	
	/** Description of ConductTransaction with coins
	 * @param price 	The price in cents of the transaction attempted
	 * @return 			The return code based on success of the transaction
	 */
	protected TransactionReturnCode ConductTransaction(int price)
	{
		return null;
	}
	
	/** Description of provideChange with coins
	 * 
	 * Makes calls to each coinRackHandler to dispense their change in order (greatest to least)
	 * 
	 * @param amount 	The amount in cents of the amount of change to dispense
	 * @return 			The code of the success of change being provided
	 */
	protected TransactionReturnCode provideChange(int amount)
	{
		return null;
	}
	
	/** Description of getAvailableBalance for all of the coins
	 * @return 			The value of the coins in the temporary storage bin available for a transaction
	 */
	protected int getAvailableBalance(){
		return 0;
	}
	
	/** Description of getExactChangeStatus for coins
	 * @return 			The current status of the exact change state
	 */
	protected boolean getExactChangeStatus(){
		return this.exactChangeStatus;
	}
	
	/** Description of getCoinRackControllers for coins
	 * @return 			The array of coin rack controllers (for use to register)
	 */
	protected CoinRackController[] getCoinRackControllers(){
		return coinRackControllers;
	}
	
	/** Description of isExactChangeActive if no change will be provided
	 * @return 			The state of not providing change due to exact change status
	 */
	protected boolean isExactChangeActive() {
		return exactChangeStatus;
	}

	/** Description of isFullOfChangeActive if at least one coin rack is full of coins
	 * @return 			The state of at least one rack being full of coins
	 */
	protected boolean isFullOfChangeActive() {
		return fullOfChangeStatus;
	}

	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinAdded(CoinReceptacle receptacle, Coin coin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinsRemoved(CoinReceptacle receptacle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinsFull(CoinReceptacle receptacle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enabled(CoinReceptacle receptacle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(CoinReceptacle receptacle) {
		// TODO Auto-generated method stub
		
	}
	
}
