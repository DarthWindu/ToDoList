package backend;

import java.io.Serializable;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pujit
 * 
 * @version p 0.1 (p is for prototype, v is for version)
 * 
 * Extends backend.HistoryItem
 * Represents Comment on a task
 *
 */
@XmlRootElement(name="Comment")
public class Comment extends HistoryItem implements Serializable {
	
	private String comment;
	public Comment(){
		System.out.println("This constructor should never run.");
	}
	public Comment(String userComment)
	{
		comment = userComment;
		setDate(Calendar.getInstance().getTime());//Is this the correct way to set the date?
	}
	
	/** @author Pujit
	 * @return comment: Returns comment the object represent
	 */
	@XmlElement(name="comment")
	public String getComment() 
	{
		return comment;
	}
	
	/**
	 * @author Pujit
	 * @param newComment: The new comment that replaces the old one
	 */
	public void setComment(String newComment)
	{
		comment = newComment;
	}
	
	public boolean isEqual(Comment comment) {
		if(comment.getComment().equals(this.getComment()))
			return true;
		return false;
	}
}