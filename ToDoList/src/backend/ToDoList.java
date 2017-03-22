package backend;

import java.io.Serializable;
import java.util.ArrayList;

public class ToDoList implements Serializable{
	
	private ArrayList<Task> activeTasks;
	private ArrayList<Task> completedTasks;
	//initializes variables
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
	
	public void sortTasks(){
		 int n = activeTasks.size();  
	        Task temp;  
	         for(int i=0; i < n; i++){  
	                 for(int k=1; k < (n-i); k++){  
	                          if(activeTasks.get(k-1).getStatus() > activeTasks.get(k).getStatus()){    
	                                 temp = activeTasks.get(k-1);  
	                                 activeTasks.set(k-1, activeTasks.get(k));
	                                 activeTasks.set(k, temp);
	                         }  
	                          
	                 }  
	         }  
	}
	
	public ArrayList<Task> getCompletedTasks(){
		return completedTasks;
	}
	
	public void switchTaskToCompleted(Task task){
		activeTasks.remove(task);
		completedTasks.add(task);
	}
	
	

}
