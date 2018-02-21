package backend;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import backend.adapters.LocalDateAdapter;

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
@XmlRootElement(name="Task")
@XmlAccessorType(XmlAccessType.NONE)
public class Task implements Serializable{
	/**
	 * AUTO GENERATED serialVersionUID
	 * 
	 * Pujit - I'm not going to lie, I have no idea what's going on here with the serialization id
	 * 		   Remove at your own risk. T_T
	 */
	private static final long serialVersionUID = 2850384200535302052L;
	@XmlAttribute(name="taskName")
	private String taskName;
	private boolean showDate = false;
	@XmlAttribute(name="status")
	private Integer status; //Integer is serializable - we could change to int if primitive types are serializable, but I'm not sure
	
	@XmlJavaTypeAdapter(value=LocalDateAdapter.class)
	private LocalDate dateUrgentElev = null;
	@XmlJavaTypeAdapter(value=LocalDateAdapter.class)
	private LocalDate dateCurrentElev = null;
	@XmlJavaTypeAdapter(value=LocalDateAdapter.class)
	private LocalDate dateEventualElev = null;
	
	@XmlElementWrapper(name="comments")
	@XmlElement(name="Comment")
	private ArrayList<Comment> comments = new ArrayList<Comment>();//Changed from design - Changed from HistoryItem List to Comment List
	@XmlElementWrapper(name="priorityChanges")
	@XmlElement(name="PriorityChange")
	private ArrayList<PriorityChange> priorityChanges;
	@XmlElementWrapper(name="nameChanges")
	@XmlElement(name="NameChange")
	private ArrayList<NameChange> nameChanges;
	@XmlElementWrapper(name="historyEvents")
	@XmlElement(name="HistoryEvent")
	private ArrayList<HistoryItem> historyEvents = new ArrayList<HistoryItem>();
	//private Date[] priorityChange = new Date[3];
	
	@XmlAttribute(name="dateCreated")
	private Date dateCreated;
	@XmlAttribute(name="uniqueID") 
	private String uniqueID;

	public static final int DEFAULT_STATUS = 2;//Default status (2) means Current
	public static final int INACTIVE = 0,
			EVENTUAL = 1,
			CURRENT = 2,
			URGENT = 3,
			COMPLETED = 4;

	//CONSTRUCTORS----------------------------------------------------------

	public Task(){
		
	}

	public Task(String name) 
	{
		//Date date = Calendar.getInstance().getTime();
		this(name, Calendar.getInstance().getTime());
		//this.initVariables();
		//NOTE that Calendar.getInstance().getTime() might not yield desired result (mm.dd.yy hh:mm am/pm)
		//TODO: make new historyItem
	}

	public Task(String name, Date date) 
	{
		this(name, date, DEFAULT_STATUS); // !! - might be an issue calling a static variable in constructor
		//this.initVariables();
		//TODO: make new historyItem
	}

	public Task(String name, Date date, int initPriority) 
	{
		this.initVariables();
		taskName = name;
		dateCreated = date;
		status = new Integer(initPriority);
		System.out.println(taskName +": " + uniqueID);
		//TODO: make new historyItem
	}

	//-----------------------------------METHODS ---------------------------------------------------------------------------

	private void initVariables() 
	{
		//Might need to change because Serialization may require this method NOT to be called
		comments = new ArrayList<Comment>();
		priorityChanges = new ArrayList<PriorityChange>();
		nameChanges = new ArrayList<NameChange>();
		historyEvents = new ArrayList<HistoryItem>();
		uniqueID = UUID.randomUUID().toString();
	}

	//ACCESSORS*********************************************************888

	/**@return status: integer representation of Priority level*/
	public int getStatus()
	{
		return status.intValue();
	}
	
	/**@return status: String representation of Priority level*/
	public String getStatusString()
	{
		String strStatus = "NULL STATUS";
		
		switch (status.intValue()) {
		
		case INACTIVE: strStatus = "INACTIVE";
		break;
		
		case EVENTUAL: strStatus = "EVENTUAL";
		break;
		
		case CURRENT: strStatus = "CURRENT";
		break;
		
		case URGENT: strStatus = "Urgent";
		break;
		
		case COMPLETED: strStatus = "COMPLETED";
		break;
		
		}
		
		return strStatus;
	}

	/**
	 * Returns LocalDate representation of date Task should elevate to urgent.
	 * Returns null if no date has been set.
	 * @return
	 */
	public LocalDate getUrgentElevDate() {
		return dateUrgentElev;
	}

	/**
	 * Returns LocalDate representation of date Task should elevate to current.
	 * Returns null if no date has been set.
	 * @return
	 */
	public LocalDate getCurrentElevDate() {
		return dateCurrentElev;
	}

	/**
	 * Returns LocalDate representation of date Task should elevate to eventual.
	 * Returns null if no date has been set.
	 * @return
	 */
	public LocalDate getEventualElevDate() {
		return dateEventualElev;
	}

	public LocalDate getEarliestElevDate() {
		if (this.getEventualElevDate() != null) {
			return this.getEventualElevDate();
		} else if (this.getCurrentElevDate() != null) {
			return this.getCurrentElevDate();
		} else if (this.getUrgentElevDate() != null) {
			return this.getUrgentElevDate();
		} else {
			return LocalDate.MIN;
		}
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

	public String getName()
	{
		return taskName;
	}

	public String getFormattedName() {
		String formattedString = this.getName();
		
		switch(this.getStatus()) {
		case Task.URGENT: 
			//formattedString.setText(this.getName());
			//formattedString.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12)); 
			break;

		case Task.CURRENT: 
		break;

		case Task.EVENTUAL: //formattedString.setFont(Font.font(Font.getDefault().getFamily(), FontPosture.ITALIC, 12)); 
		break;

		case Task.INACTIVE:
			if (showDate) {
				if (this.getEventualElevDate() != null) {
					formattedString = (inactiveNameDateFormatter(this.getEventualElevDate()));
				} else if (this.getCurrentElevDate() != null) {
					formattedString = inactiveNameDateFormatter(this.getCurrentElevDate());
				} else if (this.getUrgentElevDate() != null) {
					formattedString = (inactiveNameDateFormatter(this.getUrgentElevDate()));
				} else {
					//formattedString = ("No date of elevation set - " + this.getName());
					System.out.println("DANGER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					setShowDate(false);
				}
			} else {
				//formattedString = "<i>" + this.getName() + "</i>";
			}
			
			//formattedString.setFont(Font.font(Font.getDefault().getFamily(), FontPosture.ITALIC, 12)); 
			
			break;

		default: break;
		}

		return formattedString;

	}

	public boolean isEqual(Task task) 
	{
		return (taskName.equals(task.getName()) && (status.intValue() == task.getStatus()));
		//Returns true if both tasks' names and status values match
	}
	
	public String getID() {
		return uniqueID;
	}


	//MODIFIERS**********************************************************************

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
		if (this.getStatus() != Task.INACTIVE) {
			this.setShowDate(false);
		}
		//if(newStatus == COMPLETED)
		//	MainMenu.getList().switchTaskToCompleted(this);

	}

	public void setShowDate(boolean shudIshowDate) {
		showDate = shudIshowDate;
	}

	/** @param newName for task
	 * Adds a NameChange Event and a HistoryItem event
	 * Also changes the task Name
	 */
	public void changeName(String newName)
	{
		NameChange change = new NameChange(taskName, newName);
		change.setText("Name Changed");
		nameChanges.add(change);
		historyEvents.add(change);
		taskName = newName;
	}

	

	/** @param comment*/
	public void addComment(String comment)
	{
		Comment newComment = (new Comment(comment));
		newComment.setText("New Comment Added");
		comments.add(newComment);
		historyEvents.add(newComment);
	}

	/*public Date[] getDates()
	{
		return priorityChange;
	}*/

	

	public void deleteComment(Comment comment) {
		Comment commCheck = null;

		for(Comment com: comments) {
			if(com.isEqual(comment))
				commCheck = com;
		}
		
		/*for (int counter = 0; counter < comments.size(); counter ++) {
			
		}*/
		if(commCheck != null) {
			comments.remove(commCheck);
			historyEvents.remove(commCheck);
		}
			
	}

	/*public void storeDate(Date day, int index){
		priorityChange[index] = day;
	}*/

	

	public void setUrgentElevDate(LocalDate date) {
		dateUrgentElev = date;
	}

	public void setCurrentElevDate(LocalDate date) {
		dateCurrentElev = date;
	}

	public void setEventualElevDate(LocalDate date) {
		dateEventualElev = date;
	}

	public void checkElevation() {
		for (int priorityToCheck = this.getStatus() + 1; priorityToCheck <= Task.URGENT; priorityToCheck ++) {
			checkElevation(priorityToCheck);
		}
	}



	//***********************PRIVATE METHODS*****************************************

	private void checkElevation(int priority) {
		switch(priority) {
		case Task.EVENTUAL: analyzeElev(Task.EVENTUAL, dateEventualElev);
		break;

		case Task.CURRENT: analyzeElev(Task.CURRENT, dateCurrentElev);
		break;

		case Task.URGENT: analyzeElev(Task.URGENT, dateUrgentElev);
		break;
		}
	}

	/*Basis for analyzeElev(int urgency, LocalDate date):
	 * 
	 * private void checkUrgentElev() {
		if (this.getStatus() < Task.URGENT) {
			//If Task is not yet urgent
			if (dateUrgentElev != null) {
				//If there is a specified date to elevate to urgent
				if (dateUrgentElev.isEqual(LocalDate.now()) || dateUrgentElev.isBefore(LocalDate.now())) {
					this.setStatus(Task.URGENT);
					dateUrgentElev = null;//Reset dateUrgentElev
				}
			}
		}
	}*/

	private void analyzeElev(int urgency, LocalDate date) {
		if (this.getStatus() < urgency) {
			//If Task is not yet as urgent (as indicated by value of urgency)
			if (date != null) {
				//If there is a specified date to elevate to this urgent
				if (date.isEqual(LocalDate.now()) || date.isBefore(LocalDate.now())) {
					this.setStatus(urgency);
					date = null;//Reset date - should point internally to an elevation date
				}
			}
		}
	}


	

	private String inactiveNameDateFormatter(LocalDate date) {
		return date + ", "+ date.getDayOfWeek() + "-\n" + this.getName();
	}

	
	//OVERRIDES

	@Override
	public String toString(){
		return getName();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Task) {
			Task comp = (Task) obj;
			return this.getID().equals(comp.getID());
		}
		
		return false;
	}
}