package backend;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Pujit
 * 
 * @version p 0.1
 * 
 * Represents Priority change Date Item
 *
 */

//DOESNT THIS NEED A DATE FIELD??? - I added one
public class PriorityChange extends HistoryItem {
	Integer oldStatus, newStatus;
	
	PriorityChange(int userOldStatus, int userNewStatus)
	{
		this(userOldStatus, userNewStatus, Calendar.getInstance().getTime());//Is this the correct way to get today's date (plus hh.mm am/pm time)?
	}
	
	PriorityChange(int userOldStatus, int userNewStatus, Date dateOfEvent)
	{
		oldStatus = new Integer(userOldStatus);
		newStatus = new Integer(userNewStatus);
		setDate(dateOfEvent);
	}
	
	/**
	 * @author Pujit
	 * @return oldStatus Value
	 */
	public int getOldStatus()
	{
		return oldStatus.intValue();
	}
	
	/**
	 * @author Pujit
	 * @return newStatus Value
	 */
	public int getNewStatus()
	{
		return newStatus.intValue();
	}
}