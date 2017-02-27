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
	private Date whenDone;
	private String text;
	
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
	/**
	 * @author Ryan Sutton
	 * @param string: sets the text for the HistoryItem such as “comment created” 
	 * 
	 * Updated p 0.1
	 */
	public void setText(String eventDescription){
		text = eventDescription;
	}
	/**
	 * @author Ryan Sutton
	 * @return String text: the text of the History Item 
	 * 
	 * Updated p 0.1
	 */
	public String getText() {
		return text;
	}

}
