package business.funds;
/** Description of PrepaidController
 * @author James Pihooja
 * @author Nabil Muthanna
 * 
 * Class to interact with hardware to conduct a Transaction with all subcomponents
 * 1. Prepaid
 * 2. Bills
 * 3. Coins - change provided
 * 4. PayPal
 */
public class Funds {

		private boolean prepaidPresent;
		private boolean billsPresent;
		private boolean coinsPresent;
		private boolean payPalPresent;
		
		private PrepaidController prepaidController;
		private BankNoteController bankNoteController;
		private CoinsController coinsController;
		
		/** Description of ConductTransaction with all available payment methods
		 * @param price 	The price in cents of the transaction attempted
		 * @return 			The return code based on success of the transaction
		 */
		public TransactionReturnCode ConductTransaction(int price){
			return null;
		}

		/** Description of isPrepaidPresent for determining if there is prepaid on this machine
		 * @return 			Indicates if the prepaid functionality is present
		 */
		public boolean isPrepaidPresent() {
			return prepaidPresent;
		}

		/** Description of isBillsPresent for determining if there are bills on this machine
		 * @return 			Indicates if the bills functionality is present
		 */
		public boolean isBillsPresent() {
			return billsPresent;
		}

		/** Description of isCoinsPresent for determining if there are coins on this machine
		 * @return 			Indicates if the coins functionality is present
		 */
		public boolean isCoinsPresent() {
			return coinsPresent;
		}

		/** Description of isPayPalPresent for determining if there is PayPal on this machine
		 * @return 			Indicates if the PayPal functionality is present
		 */
		public boolean isPayPalPresent() {
			return payPalPresent;
		}

		/** Description of isExactChangeActive if no change will be provided
		 * @return 			The state of not providing change due to exact change status
		 */
		public boolean isExactChangeActive() {
			return false;
		}

		/** Description of isFullOfChange if at least one coin rack is full of coins
		 * @return 			The state of at least one rack being full of coins
		 */
		public boolean isFullOfChangeActive() {
			return false;
		}

		/** Description of getPrepaidController 
		 * @return 			The PrepaidController for registration
		 */
		public PrepaidController getPrepaidController() {
			return prepaidController;
		}

		/** Description of getBillsController
		 * @return 			The BillsController for registration
		 */
		public BankNoteController getBankNoteController() {
			return bankNoteController;
		}

		/** Description of getCoinsController
		 * @return 			The CoinsController for registration
		 */
		public CoinsController getCoinsController() {
			return coinsController;
		}

		/** Description of getCoinRackControllers
		 * @return 			An array of CoinRackControllers for registration
		 */
		public CoinRackController[] getCoinRackControllers(){
			return null;
		}

		

		
}
