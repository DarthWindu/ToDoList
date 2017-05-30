package backend;

import java.io.Serializable;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author Pujit
 * 
 * @version p 0.1
 * 
 * Represents a name change event
 * No changes, except for possible addition of serialization implementation is expected.
 */
@XmlRootElement(name="NameChange")
public class NameChange extends HistoryItem implements Serializable{
	@XmlElement(name="oldName")
	private String oldName;
	@XmlElement(name="newName")
	private String newName;
	
	NameChange(){
		
	}
	NameChange(String old_Name, String new_Name)
	{
		oldName = old_Name;
		newName = new_Name;
		setDate(Calendar.getInstance().getTime());
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