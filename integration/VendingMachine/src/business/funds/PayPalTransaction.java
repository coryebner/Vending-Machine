package business.funds;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.FundingInstrument;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

public class PayPalTransaction {
	
	// Client ID and client secret for our merchant account
	// They come from the application ID for our specific merchant account
	String clientID, clientSecret;
	static final String defaultClientID = "AUqkth4JyvL-S5HnPwbPsOX9iR23megQdlsSznWg69KQxqBgTYOmy2OmR74GN-_bdNVOSe-7DDPHOsZx";
	static final String defaultClientSecret = "EEUCy7qzSQQowLeg55m1JXx2Ci6g5MIY449ifjOlSk7gLgbCvptdSPgnJug5yy61dWE5E6BKYxslZAxs";
	
	boolean closed;
	
	// Access token and cancel url needed
	String accessToken = null, cancel_url;
	
	// Contains credentials, sdk configuration and access token for this purchase
	APIContext apiContext;
	
	// Constructor with arguments
	public PayPalTransaction (String cID, String cS) throws PayPalRESTException
	{		
		// Create the sdkConfig for transaction
		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", "sandbox");
		
		// Authorize client ID and client secret
		clientID = cID;
		clientSecret = cS;
		OAuthTokenCredential a = new OAuthTokenCredential(clientID, clientSecret, sdkConfig);
		
		// Get access token for this specific transaction
		accessToken = a.getAccessToken();
		
		// Create API context to store access information and sdk config
		apiContext = new APIContext(accessToken);
		
		// Set the configuration
		apiContext.setConfigurationMap(sdkConfig);
		closed = false;
	}
	
	// Default constructor using our own clientID and clientSecret
	public PayPalTransaction () throws PayPalRESTException
	{
		this(defaultClientID, defaultClientSecret);
	}
	
	// Process payment through paypal
	public TransactionReturnCode processPayPalPayment(int a) throws PayPalRESTException, InterruptedException
	{	
		// Create transaction with price of product
		List<Transaction> transactions = setTransaction(a);
		
		// Create a payer who is paying with paypal
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		
		// Create a payment using transaction and payer
		Payment createdPayment = createPayment(transactions, payer);
		
		// Get links (self, approval, execute)
		Iterator<Links> links = createdPayment.getLinks().iterator();
		String approval_url = null;
		
		// Get the approval_url link
		while (links.hasNext())
		{
			Links link = links.next();
			
			if (link.getRel().equalsIgnoreCase("approval_url"))
				approval_url = link.getHref();
		}
		
		// Open up a browser for user to enter in paypal credentials
		// Brower will automatically close
		final String url = approval_url;
		
		final SwingBrowser browser = new SwingBrowser();
		
		browser.addWindowListener(new java.awt.event.WindowAdapter()
		{
			@Override
			public void windowClosing (java.awt.event.WindowEvent windowEvent) 
			{
				int reply = JOptionPane.showConfirmDialog(browser, "Are you sure you want to close this window?", "Attempt a close", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION)
					closed = true;
					
			}
		});
		
		SwingUtilities.invokeLater(new Runnable () {
			public void run () {
				browser.setVisible(true);
				browser.loadURL(url);
				browser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		// Temporary payment to retrieve payer ID
		Payment temp = null;
		
		try {
			temp = Payment.get(apiContext, createdPayment.getId());
		}
		
		catch (PayPalRESTException e)
		{
			return TransactionReturnCode.UNSUCCESSFUL;
		}
		
		String payerId = null;
		
		// Timeout will occur after one minute
		for (int i = 0; i < 12; i++)
		{
			Thread.sleep(5000);
			
			if (closed)
				return TransactionReturnCode.UNSUCCESSFUL;

			// If payer accepts payment, close browser and set payer ID
			if (temp.getPayer().getPayerInfo().getPayerId() != null)
			{
				payerId = temp.getPayer().getPayerInfo().getPayerId();
				payer = temp.getPayer();
				browser.dispose();
				break;
			}
			
			// If cancel url is visited, close browser and return unsuccessful
			else if (browser.getURL().contains(cancel_url))
			{
				browser.dispose();
				return TransactionReturnCode.UNSUCCESSFUL;
			}
			
			temp = Payment.get(apiContext, createdPayment.getId());
		}
		
		// Execute the payment and transfer funds
		Payment newPayment = null;
		
		try {
			newPayment = executePayment(createdPayment, payerId);
		}
		
		catch (PayPalRESTException e)
		{
			// If payerID was never set, return timeout and close browser
			browser.dispose();
			return TransactionReturnCode.TIMEOUT;
		}
		
		// If payment was approved, return successful
		if (newPayment.getState().equalsIgnoreCase("approved"))
			return TransactionReturnCode.SUCCESSFUL;
		
		// Else, return unsuccessful
		else
			return TransactionReturnCode.UNSUCCESSFUL;
	}
	
	public TransactionReturnCode processCreditCardPayment (String fName, String lName, String type, String number, int month, int year, int a) throws PayPalRESTException
	{
		// Create credit card object from PayPal
		CreditCard cc = new CreditCard();
		cc.setType(type.toLowerCase());
		cc.setNumber(number);
		cc.setExpireMonth(month);
		cc.setExpireYear(year);
		cc.setFirstName(fName);
		cc.setLastName(lName);
		
		// Set funding instrument as credit card
		FundingInstrument fundingInstrument = new FundingInstrument();
		fundingInstrument.setCreditCard(cc);
		
		List<FundingInstrument> fundingInstrumentList = new ArrayList<FundingInstrument>();
		fundingInstrumentList.add(fundingInstrument);
		
		// Set the transaction
		List<Transaction> transactions = setTransaction(a);
		
		// Create a payer who will be paying with a credit card
		Payer payer = new Payer();
		payer.setFundingInstruments(fundingInstrumentList);
		payer.setPaymentMethod("credit_card");
		
		// Set payment with intent, who the payer is and the transaction
		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);
		
		// Create the payment
		Payment createdPayment = null;
		
		try {
			createdPayment = payment.create(apiContext);
		}
		
		catch (PayPalRESTException e)
		{
			return TransactionReturnCode.CREDITCARDERROR;
		}
		
		if (createdPayment.getState().equalsIgnoreCase("approved"))
			return TransactionReturnCode.SUCCESSFUL;
		
		else
		{
			return TransactionReturnCode.CREDITCARDERROR;
		}
	}
	
	// Create a transaction with amount of product
	public List<Transaction> setTransaction (int a)
	{		
		String temp = String.valueOf((double)a / 100.00);
		
		int index = temp.indexOf(".");
		String sub = temp.substring(index);

		String p;
		
		if (sub.length() < 3)
			p = temp.concat("0");
		else
			p = temp;
		
		Amount amount = new Amount();
		amount.setTotal(p);
		amount.setCurrency("USD");
		
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
		
		return transactions;
	}
	
	// Create a payment with intent of sale
	// Also define cancel and return URLs
	public Payment createPayment(List<Transaction> transactions, Payer payer) throws PayPalRESTException
	{
		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);
		
		RedirectUrls redirectUrls = new RedirectUrls();
		cancel_url = "http://www.helloworld.com/";
		redirectUrls.setCancelUrl(cancel_url);
		redirectUrls.setReturnUrl("https://www.sandbox.paypal.com/home");
		payment.setRedirectUrls(redirectUrls);
		
		Payment createdPayment = null;
		
		createdPayment = payment.create(apiContext);
		
		return createdPayment;
	}
	
	// Execute the payment and transfer funds
	public Payment executePayment (Payment createdPayment, String payerId) throws PayPalRESTException
	{
		Payment pay = Payment.get(apiContext, createdPayment.getId());
		PaymentExecution pe = new PaymentExecution();
		pe.setPayerId(payerId);
			
		Payment newPayment = pay.execute(apiContext, pe);
		
		return newPayment;
	}
}