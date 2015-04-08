package SDK.logger;

public class LogDate{
	private int day;
	private int hour;
	private int minute;
	private LoggingType type;
	private static enum LoggingType {Daily, WEEKLY, MONTHLY};
	
	/**
	 * Date object to store info needed for Set time logging
	 * 
	 * @param type A LoggingType enum
	 * @param startDay Day to send logs to the server. All future logs are based on this date.
	 * @param startHour Hour to send the logs to the server
	 * @param startMinute Minute to send the logs to the server
	 */
	public LogDate(LoggingType type, int startDay, int startHour, int startMinute){
		this.type = type;
		this.day = startDay;
		this.hour = startHour;
		this.minute = startMinute;
	}
}