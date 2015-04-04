package business.funds.tests;

import static org.junit.Assert.*;
import hardware.funds.Card;
import hardware.funds.Card.CardType;

import java.util.List;
import java.util.Locale;

import org.junit.*;

import business.funds.PayPalController;
import business.funds.PayPalTransaction;
import business.funds.TransactionReturnCode;

import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

public class PayPalTest {
	
	PayPalTransaction a;
	
	@Before
	public void setup () throws PayPalRESTException {
		a = new PayPalTransaction();
	}
	
	@After
	public void tearDown() {}
	
	@Test
	public void testStandardSetup () throws PayPalRESTException {
		new PayPalTransaction();
	}
	
	@Test (expected = PayPalRESTException.class)
	public void testIncorrectCredentials () throws PayPalRESTException {
		new PayPalTransaction("a", "b");		
	}

	@Test
	public void testSetUpTransactionWithWholeNumber () {
		List<Transaction> t = a.setTransaction(300);
		
		assertEquals("There is one transaction in the list", 1, t.size());
		assertEquals("The value of that one transaction is 3.00", "3.00", t.get(0).getAmount().getTotal());
	}
	
	@Test
	public void testSetUpTransactionWithTwentyCents () {
		List<Transaction> t = a.setTransaction(220);
		
		assertEquals("There is one transaction in the list", 1, t.size());
		assertEquals("The value of that one transaction is 2.20", "2.20", t.get(0).getAmount().getTotal());
	}
	
	@Test
	public void testSetUpTransactionWithTwentyFiveCents () {
		List<Transaction> t = a.setTransaction(225);
		
		assertEquals("There is one transaction in the list", 1, t.size());
		assertEquals("The value of that one transaction is 2.25", "2.25", t.get(0).getAmount().getTotal());
	}
	
	@Test
	public void testCreatePayment () throws PayPalRESTException {
		List<Transaction> t = a.setTransaction(225);
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		Payment p = a.createPayment(t, payer);
		
		assertEquals("The state of the payment is created", "created", p.getState());
		assertEquals("There is one transaction in the payment", 1, p.getTransactions().size());
		assertEquals("The value of that transaction is 2.25", "2.25", p.getTransactions().get(0).getAmount().getTotal());
		assertEquals("The intent is sale", "sale", p.getIntent());
	}
	
	@Test (expected = PayPalRESTException.class)
	public void testNullTransactionWhenCreatingPayment () throws PayPalRESTException {
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		
		a.createPayment(null, payer);
	}
	
	@Test (expected = PayPalRESTException.class)
	public void testNullPayerWhenCreatingPayment () throws PayPalRESTException {
		List<Transaction> t = a.setTransaction(225);
		
		a.createPayment(t, null);
	}
	
	@Test (expected = PayPalRESTException.class)
	public void testFailedExecutePayment () throws PayPalRESTException {
		List<Transaction> t = a.setTransaction(225);
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		Payment p = a.createPayment(t, payer);
		
		a.executePayment(p, "abc");
	}
	
	@Test
	public void testCreditCardPayment () {
		Card cc = new Card(CardType.VISA, "4214021408540409", "Tim Johnson","1234", "03/2020", Locale.US, 1000);
				
		TransactionReturnCode c = PayPalController.ConductCreditCardTransaction(200, cc);
		
		assertEquals("Transaction should be successful", TransactionReturnCode.SUCCESSFUL, c);
	}
	
	@Test
	public void testInvalidCreditCardPayment () {
		Card cc = new Card(CardType.VISA, "123", "Tim Johnson","1234", "03/2020", Locale.US, 1000);
		
		TransactionReturnCode c = PayPalController.ConductCreditCardTransaction(200, cc);
		
		assertEquals("Transaction should be credit card error", TransactionReturnCode.CREDITCARDERROR, c);
	}
}
