package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ToDoList implements Serializable{
	
	/**
	 * Don't change this
	 */
	private static final long serialVersionUID = 1582472173711463948L;
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
	
	/*public void checkPriorityChange(Date date){
		for(Task task : activeTasks){
			Date[] priority = task.getDates();
			for(int i = 0; i < priority.length; i++){
				if(priority[i] != null){
					if(date.after(priority[i])){
						task.setStatus(i + 1);
						task.storeDate(null,i);
					}
				}

			}
		}
	}*/
	
	public void checkElevations() {
		for (Task task : activeTasks) {
			task.checkElevation();
		}
	}
	
	public Task getTask(String taskName) {
		for (int counter = 0; counter < activeTasks.size(); counter ++) {
			if (activeTasks.get(counter).getName().equals(taskName)) {
				return activeTasks.get(counter);
			}
		}
		
		return null;
		//No Task with this name was found
	}
	
	public Task getCompletedTask(String taskName) {
		for (int counter = 0; counter < completedTasks.size(); counter ++) {
			if (completedTasks.get(counter).getName().equals(taskName)) {
				return completedTasks.get(counter);
			}
		}
		
		return null;
		//No Task with this name was found
	}
	
	public boolean setTaskCompleted(String taskName) {
		boolean stopForLoop = false;
		for (int counter = 0; counter < activeTasks.size() && !stopForLoop; counter ++) {
			if (activeTasks.get(counter).getName().equals(taskName)) {
				completedTasks.add(activeTasks.remove(counter));
				stopForLoop = true;
			}
		}
		
		return !stopForLoop;//True if successfully switched.
	}
	
	

}
