package backend;

import java.util.Calendar;

/**
 * 
 * @author Pujit
 * 
 * @version p 0.1 (p is for prototype, v is for version)
 * 
 * Extends backend.HistoryItem
 * Represents Comment on a task
 *
 */
public class Comment extends HistoryItem {
	private String comment;
	
	Comment(String userComment)
	{
		comment = userComment;
		this.setDate(Calendar.getInstance().getTime());//Is this the correct way to set the date?
	}
	
	/**
	 * @author Pujit
	 * @return comment: Returns comment the object represents
	 * 
	 * Updated p 0.1
	 */
	public String getComment() 
	{
		return comment;
	}
	
	/**
	 * @author Pujit
	 * @param newComment: The new comment that replaces the old one
	 * 
	 * Updated p 0.1
	 */
	public void setComment(String newComment)
	{
		comment = newComment;
	}
}