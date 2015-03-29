package business.funds;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.funds.Banknote;
import hardware.funds.BanknoteReceptacle;
import hardware.funds.BanknoteReceptacleListener;

/** Description of PrepaidController
 * @author Jan Clarin
 * @author Andrei (Andy) Savu
 * @author Arthur Lee
 * @author Olabode (Sam) Adegbayike
 * 
 * Class to interact with hardware to conduct a Bills Transaction
 */
public class BankNoteController implements BanknoteReceptacleListener {
	
	private int availableBalance = 0;
	
	/** Description of ConductTransaction with bills
	 * @param price 	The price in cents of the transaction attempted
	 * @return 			The return code based on success of the transaction
	 */
	protected TransactionReturnCode ConductTransaction(int price)
	{
		return null;
	}
	
	/** Description of getAvailableBalance of the bills in the temporary storage bin
	 * @return 			The value of the bills currently stored
	 */
	protected int getAvailableBalance(){
		return availableBalance;
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
	public void banknoteAdded(BanknoteReceptacle receptacle, Banknote banknote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BanknoteRemoved(BanknoteReceptacle receptacle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void banknoteFull(BanknoteReceptacle receptacle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enabled(BanknoteReceptacle receptacle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(BanknoteReceptacle receptacle) {
		// TODO Auto-generated method stub
		
	}
	

}
