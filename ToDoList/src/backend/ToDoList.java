package backend;

import java.util.ArrayList;

public class ToDoList {
	
	private ArrayList<Task> activeTasks;
	private ArrayList<Task> completedTasks;
	
	
	public ArrayList<Task> getactiveTasks(){
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
