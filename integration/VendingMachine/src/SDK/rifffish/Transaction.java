package SDK.rifffish;

import java.sql.Timestamp;

import SDK.rifffish.Rifffish.PaymentMethod;

/**
 * Represents a Transaction Object
 * (converted from json to a Java object - gson)
 */
public class Transaction {
	public Integer id = null;
	public Integer product_id = null;
	public Integer quantity = null;
	public String payment_method = null;
	public Boolean status = null;
	public String timestamp = null;
	public Object error = null;
	
	/**
	 * Transaction Object. All params are required.
	 * See API/SDK documentation for more information
	 * 
	 * @param ProductId, Integer from the Product ID
	 * @param PaymentMethod, ENUM of a PaymentMethod see API for more details
	 * @param PaymentStatus, Boolean of whether the payment was completed or not
	 */
	public Transaction(Integer ProductId, PaymentMethod PaymentMethod, Boolean PaymentStatus) {
		product_id = ProductId;
		payment_method = parsePaymentMethod(PaymentMethod);
		quantity = 1;
		status = PaymentStatus;
		
		java.util.Date date = new java.util.Date();
		this.timestamp = (new Timestamp(date.getTime())).toString();
	}
	
	/**
	 * Convert Payment enum to the server string format
	 * 
	 * @param pMethod
	 * @return
	 */
	private String parsePaymentMethod(PaymentMethod pMethod) {
		switch (pMethod) {
			case COIN: 
				return "coins";
		case CREDIT_CARD:				
				return "credit_cards";
		case PAYPAL:				
				return "paypal";
				
			default:
				return null;
		}
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the product_id
	 */
	public Integer getProductId() {
		return product_id;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @return the payment_method
	 */
	public String getPaymentMethod() {
		return payment_method;
	}

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the error
	 */
	public Object getError() {
		return error;
	}
	
	
}

