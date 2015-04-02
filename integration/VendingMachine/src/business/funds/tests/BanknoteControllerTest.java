/**
 * Unit Test for the Banknote Controller Class
 */
package business.funds.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.funds.BanknoteController;
import business.funds.TransactionReturnCode;
import hardware.funds.Banknote;
import hardware.funds.BanknoteReceptacle;

/**
 * 
 * @author Jan Clarin
 * @author Andrei (Andy) Savu
 * @author Arthur Lee
 * @author Olabode (Sam) Adegbayike
 */

public class BanknoteControllerTest {

	BanknoteController banknoteController; 
	BanknoteReceptacle banknoteReceptacle;
	Banknote banknoteFive;
	Banknote banknoteTen;
	Banknote banknoteTwenty;
	
	/**
	 * 
	 * Instantiating all objects;
	 */
	@Before
	public void setUp() throws Exception {
		banknoteController = new BanknoteController();
		banknoteReceptacle = new BanknoteReceptacle(20);
		banknoteFive = new Banknote(5);
		banknoteTen = new Banknote(10);
		banknoteTwenty = new Banknote(20);
	}

	/**
	 *Re instantiate all objects;
	 */
	@After
	public void tearDown() throws Exception {
		banknoteController = null;
		banknoteReceptacle = null;
		banknoteFive = null;
		banknoteTen = null;
		banknoteTwenty = null;
	}

	/**
	 * Test TransactionConducted() method for success (just enough balance)
	 */
	@Test 
	public void TransactionConductedExactSuccess() {
		banknoteController.banknoteAdded(banknoteReceptacle, banknoteTen);
		assertEquals(TransactionReturnCode.SUCCESSFUL, banknoteController.ConductTransaction(10));
	}
	
	/**
	 * Test TransactionConducted() method for success (more than enough balance)
	 */
	@Test 
	public void TransactionConductedExceedingSuccess() {
		banknoteController.banknoteAdded(banknoteReceptacle, banknoteTwenty);
		assertEquals(TransactionReturnCode.SUCCESSFUL, banknoteController.ConductTransaction(5));
	}
	
	/**
	 * Test TransactionConducted() method for failure (not enough balance)
	 */
	@Test 
	public void TransactionConductedFail() {
		banknoteController.banknoteAdded(banknoteReceptacle, banknoteFive);
		assertEquals(TransactionReturnCode.INSUFFICIENTFUNDS, banknoteController.ConductTransaction(10));
	}
	
	/**
	 * Test banknoteAdded() method for updating available balance
	 */
	@Test
	public void BankNoteAddedTest(){
		banknoteController.banknoteAdded(banknoteReceptacle, banknoteTwenty);		
		assertEquals(20, banknoteController.getAvailableBalance());
	}
	

	/**
	 * Test banknoteRemoved) method for removing all available balance 
	 */
	@Test
	public void BankNoteRemovedTest(){
		banknoteController.banknoteAdded(banknoteReceptacle, banknoteTwenty);
		banknoteController.banknoteAdded(banknoteReceptacle, banknoteTen);
		banknoteController.BanknoteRemoved(banknoteReceptacle);
		assertEquals(0, banknoteController.getAvailableBalance());
	}
	

}
