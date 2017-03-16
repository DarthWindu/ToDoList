package backend;

import java.io.Serializable;
import java.util.ArrayList;

public class ToDoList implements Serializable{
	
	private ArrayList<Task> activeTasks;
	private ArrayList<Task> completedTasks;
	
	public ToDoList(){
		activeTasks = new ArrayList<Task>();
		completedTasks = new ArrayList<Task>();
	}
	
	
	public ArrayList<Task> getActiveTasks(){
		return activeTasks;
	}
	
	public void add(Task task){
		activeTasks.add(task);
	}
	
	public void delete(Task task){
		activeTasks.remove(task);
	}
	
	public ArrayList<Task> getCompletedTasks(){
		return completedTasks;
	}
	
	public void switchTaskToCompleted(Task task){
		activeTasks.remove(task);
		completedTasks.add(task);
	}
	
	

}
