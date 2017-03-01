package backend;

import java.util.Date;

public class PlannedPriorityChange {
	private Date dateOfChange;
	private Integer newPriorityLevel;
	
	PlannedPriorityChange(Date date, int priority)
	{
		dateOfChange = date;
		newPriorityLevel = priority;
	}
	
	public Date getDateOfChange()
	{
		return dateOfChange;
	}
	
	public int getNewPriorityLevel()
	{
		return newPriorityLevel.intValue();
	}

}
