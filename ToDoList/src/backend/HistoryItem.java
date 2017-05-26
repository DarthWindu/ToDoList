package backend;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public abstract class HistoryItem implements Serializable{
	@XmlElement(name="whenDone")
	private Date whenDone;
	private String text = "";
	
	public Date getDate() {
		return whenDone;
	}
	
	public void setDate (Date date) {
		whenDone = date;
	}

	public void setText(String eventDescription){
		text = eventDescription;
	}

	public String getText() {
		return text;
	}

}
