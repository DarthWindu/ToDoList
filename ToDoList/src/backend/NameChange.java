package backend;

import java.util.Calendar;
/**
 * 
 * @author Pujit
 * 
 * @version p 0.1
 * 
 * Represents a name change event
 * No changes, except for possible addition of serialization implementation is expected.
 */
public class NameChange extends HistoryItem {
	private String oldName, newName;
	
	NameChange(String old_Name, String new_Name)
	{
		oldName = old_Name;
		newName = new_Name;
		this.setDate(Calendar.getInstance().getTime());
	}
	
	public String getOldName()
	{
		return oldName;
	}
	
	public String getNewName()
	{
		return newName;
	}
}