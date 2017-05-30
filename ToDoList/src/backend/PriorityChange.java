package backend;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
@XmlRootElement(name="PriorityChange")
public class PriorityChange extends HistoryItem implements Serializable {
	@XmlElement(name="oldStatus")
	private int oldStatus;
	@XmlElement(name="newStatus")
	private int newStatus;
	PriorityChange(){
		
	}
	PriorityChange(int userOldStatus, int userNewStatus)
	{
		this(userOldStatus, userNewStatus, Calendar.getInstance().getTime());//Is this the correct way to get today's date (plus hh.mm am/pm time)?
	}
	
	PriorityChange(int userOldStatus, int userNewStatus, Date dateOfEvent)
	{
		oldStatus = userOldStatus;
		newStatus = userNewStatus;
		setDate(dateOfEvent);
	}
	
	/**
	 * @author Pujit
	 * @return oldStatus Value
	 */
	public int getOldStatus()
	{
		return oldStatus;
	}
	
	/**
	 * @author Pujit
	 * @return newStatus Value
	 */
	public int getNewStatus()
	{
		return newStatus;
	}
}