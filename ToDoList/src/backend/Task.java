package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import frontend.MainMenu;

/**
 * @author Pujit M.
 * 
 * @version p 0.1 (p is for prototype, v is for version)
 * 
 * Object representing a To-Do- List task
 * 
 * test
 * 
 * Status:
 * 0 means inactive
 * 1 means eventual
 * 2 means current
 * 3 means urgent
 * 4 means completed
 *
 */

public class Task implements Serializable{
	/**
	 * AUTO GENERATED serialVersionUID
	 * 
	 * Pujit - I'm not going to lie, I have little idea what's going on here with the serialization id
	 */
	private static final long serialVersionUID = 2850384200535302052L;
	private String taskName;
	private Integer status; //Integer is serializable - we could change to int if primitive types are serializable, but I'm not sure
	private ArrayList<Comment> comments = new ArrayList<Comment>();//Changed from design - Changed from HistoryItem List to Comment List
	private ArrayList<PriorityChange> priorityChanges;
	private ArrayList<NameChange> nameChanges;
	private ArrayList<HistoryItem> historyEvents = new ArrayList<HistoryItem>();
	private Date[] priorityChange;
	private Date dateCreated;
	
	public static final int DEFAULT_STATUS = 2;//Default status (2) means Current
	public static final int INACTIVE = 0,
							EVENTUAL = 1,
							CURRENT = 2,
							URGENT = 3,
							COMPLETED = 4;
	
//CONSTRUCTORS----------------------------------------------------------
	
	public Task(String name) 
	{
		//Date date = Calendar.getInstance().getTime();
		this(name, Calendar.getInstance().getTime());
		this.initVariables();
		//NOTE that Calendar.getInstance().getTime() might not yield desired result (mm.dd.yy hh:mm am/pm)
		//TODO: make new historyItem
	}
	
	Task(String name, Date date) 
	{
		this(name, date, DEFAULT_STATUS); // !! - might be an issue calling a static variable in constructor
		this.initVariables();
		//TODO: make new historyItem
	}
	
	Task(String name, Date date, int initPriority) 
	{
		this.initVariables();
		taskName = name;
		dateCreated = date;
		status = new Integer(initPriority);
		//TODO: make new historyItem
	}
	
//METHODS ---------------------------------------------------------------------------
	
	private void initVariables() 
	{
		//Might need to change because Serialization may require this method NOT to be called
		comments = new ArrayList<Comment>();
		priorityChanges = new ArrayList<PriorityChange>();
		nameChanges = new ArrayList<NameChange>();
		historyEvents = new ArrayList<HistoryItem>();
	}
	
	/**@return status: integer representation of Priority level*/
	public int getStatus()
	{
		return status.intValue();
	}
	
	/** @param newStatus - PriorityLevel
	 * Adds PrirityChange and HistoryItem events, and changes status/priority level
	 */
	public void setStatus(int newStatus) 
	{
		PriorityChange change = new PriorityChange(status, newStatus);
		change.setText("Priority Changed");
		priorityChanges.add(change);
		historyEvents.add(change);
		status = new Integer(newStatus);
		
		if(newStatus == COMPLETED)
			MainMenu.getList().switchTaskToCompleted(this);
		
	}
	
	/** @param newName for task
	 * Adds a NameChange Event and a HistoryItem event
	 * Also changes the task Name
	 */
	public void changeName(String newName)
	{
		NameChange change = new NameChange(taskName, newName);
		change.setText("Priority Changed");
		nameChanges.add(change);
		historyEvents.add(change);
		taskName = newName;
	}
	
	/** @return List of comments */
	public ArrayList<Comment> getComments()
	{
		return comments;//NEED TO ADD addComment method
	}
	
	/** @return List of history items */
	public ArrayList<HistoryItem> getHistoryItems()
	{
		return historyEvents;
	}
	
	/** @return priorityChange Events */
	public ArrayList<PriorityChange> getPriorityChanges()
	{
		return priorityChanges;
	}
	
	/**  @return nameChange Events*/
	public ArrayList<NameChange> getNameChanges()
	{
		return nameChanges;
	}
	
	/** @param comment*/
	public void addComment(String comment)
	{
		Comment newComment = (new Comment(comment));
		newComment.setText("New Comment Added");
		comments.add(newComment);
		historyEvents.add(newComment);
	}
	
	public Date[] getDates()
	{
		return priorityChange;
	}
	
	public String getName()
	{
		return taskName;
	}
	
	public boolean isEqual(Task task) 
	{
		return (taskName.equals(task.getName()) && (status.intValue() == task.getStatus()));
		//Returns true if both tasks' names and status values match
	}
	
	public void deleteComment(Comment comment) {
		for(Comment com: comments) {
			if(com.isEqual(comment))
				comments.remove(com);
		}
	}

	//METHODS THAT NEED TO BE DESIGNED/WORKED ON---------------------------------------------------
	//Joe: we should not need this
	/*public void checkElevation() //This method needs to be redesigned and discussed extensively, especially with the serialization. This will be a major source for bugs.
	{//Doesn't really work - just a rough outline - uncertain of what to do in every situation except if the date meets it.
		//Also, what if 
		PlannedPriorityChange mostRecentPPC = priorityChangeDates.get(0);
		Date mostRecent = mostRecentPPC.getDateOfChange();
		
		for (int counter = 1; counter < priorityChangeDates.size(); counter ++)
		{
			if (priorityChangeDates.get(counter).getDateOfChange().after(mostRecent)) {
				mostRecentPPC = priorityChangeDates.get(counter);
				mostRecent = mostRecentPPC.getDateOfChange();
			}
		}
		
		Date currentDate = Calendar.getInstance().getTime();
		if (currentDate.after(mostRecent)) 
		{
			//Prompt user if user wants to change
		} else if (currentDate.getYear() == mostRecent.getYear() && currentDate.getMonth() == mostRecent.getMonth() && currentDate.getDate() == mostRecent.getDate())
		{
			setStatus(mostRecentPPC.getNewPriorityLevel());
		}
	}*/
}
