package backend;

import java.util.Date;
/**
 * 
 * @author Pujit
 * 
 * @version p 0.1 (p is for prototype, v is for version)
 * 
 * Represents an event that should be displayed in a task's history
 *
 */
public abstract class HistoryItem {
	protected Date whenDone;
	
	/**
	 * @author Pujit
	 * @return Date whenDone: When the HistoryItem event took place
	 */
	public Date getDate() {
		return whenDone;
	}
	
	/**
	 * @author Pujit
	 * @param date: Date HistoryItem takes/took/will take place
	 * May be unecessary
	 */
	public void setDate (Date date) {
		whenDone = date;
	}
}