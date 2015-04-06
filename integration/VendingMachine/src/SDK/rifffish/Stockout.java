package SDK.rifffish;

import java.sql.Timestamp;

import SDK.rifffish.Rifffish.StockoutTypes;

/**
 * Represents a Problem Object
 * (converted from json to a Java object - gson)
 */
public class Stockout {
	public Integer product_id = null;
	public String description = null;
	public String timestamp = null;
	
	/**
	 * Problem Object. All params are required.
	 * See API/SDK documentation for more information
	 * 
	 * @param id the product id
	 * @param type ENUM of a StockoutTypes see API for more details
	 */
	public Stockout(Integer id, StockoutTypes type) {
		this.product_id = id;
		this.description = parseStockoutType(type);	
		java.util.Date date = new java.util.Date();
		this.timestamp = (new Timestamp(date.getTime())).toString();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return product_id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Convert StockType enum to the string format used by the server
	 * 
	 * @param pMethod
	 * @return
	 */
	private String parseStockoutType(StockoutTypes pMethod) {
		switch (pMethod) {
			case OUTOFSTOCK: 
				return "out_of_stock";
			case ALMOSTOUT: 
				return "almost_out";
			default:
				return null;
		}
	}
	
}

