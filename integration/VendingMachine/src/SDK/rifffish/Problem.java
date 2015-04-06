package SDK.rifffish;

import java.sql.Timestamp;

import SDK.rifffish.Rifffish.ProblemTypes;

/**
 * Represents a Problem Object
 * (converted from json to a Java object - gson)
 */
public class Problem {
	public Integer machine_id = null;
	public String description;
	public String timestamp = null;
	
	/**
	 * Problem Object. All params are required.
	 * See API/SDK documentation for more information
	 * 
	 * @param problem A ENUM of a ProblemTypes, see API for more details
	 */
	public Problem(ProblemTypes problem) {
		this.description = parseProblemType(problem);	
		java.util.Date date = new java.util.Date();
		this.timestamp = (new Timestamp(date.getTime())).toString();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return machine_id;
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
	 * Convert ProblemType enum to the string format used by the server
	 * 
	 * @param pMethod
	 * @return
	 */
	private String parseProblemType(ProblemTypes pMethod) {
		switch (pMethod) {
			case FAIL: 
				return "fail_whale";
		case OUTOFORDER:				
				return "out_of_order";
				
			default:
				return null;
		}
	}
	
}

