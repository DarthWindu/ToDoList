package backend;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
	         Collections.reverse(activeTasks);
	         ArrayList<Task> inactiveTasks = new ArrayList<Task>();
	         
	         int inactiveItemsCounter = 0;
	         int indexOfFirstInactive = 0;
	         int indexOfLastInactive = activeTasks.size() ;
	         
	         for (int counter = 0; counter < activeTasks.size(); counter ++) {
	        	 Task task = activeTasks.get(counter);
	        	 if (task.getStatus() == Task.INACTIVE) {
	        		 inactiveTasks.add(task);
	        		 if (inactiveItemsCounter == 0) {
	        			 //First Inactive Item
	        			 indexOfFirstInactive = counter;
	        		 }
	        		 inactiveItemsCounter ++;
	        	 }
	         }
	         
	         if (inactiveItemsCounter > 0) {
	        	 Collections.sort(activeTasks.subList(indexOfFirstInactive, indexOfLastInactive), new Comparator<Task>() {
	        		  @Override
					public int compare(Task o1, Task o2) {
	        			  
	        			  /*System.out.println("Task 1: " + o1);
	        			  System.out.println("Task 2: " + o2);
	        			  System.out.println("Task 1 Elev: " + o1.getEarliestElevDate());
	        			  System.out.println("Task 2 Elev: " + o2.getEarliestElevDate());*/
	        			  
	        		      return o1.getEarliestElevDate().compareTo(o2.getEarliestElevDate());//To reverse, switch o1 and o2
	        		  }
	        		});
	        	 setInactiveDates(inactiveTasks);
	         }
	         
	          
	        // Collections.sort(inactiveT, c);
	}
	
	private void setInactiveDates(List<Task> inactiveTasks) {
		if (inactiveTasks.size() > 0) {
			
			if (checkIfElevDateExists(inactiveTasks.get(0))) {
				inactiveTasks.get(0).setShowDate(true);
				System.out.println("First task set");
			}
		}
		
		 for (int counter = 1; counter < inactiveTasks.size(); counter ++) {
			 Task current = inactiveTasks.get(counter);
			 Task above = inactiveTasks.get(counter - 1);
			 try {
				 if (current.getEarliestElevDate().isEqual(above.getEarliestElevDate())) {
					 current.setShowDate(false);
				 } else {
					 current.setShowDate(true);
				 }
			 } catch (Exception ex) {
				 System.out.println(ex);
			 }
			 /*System.out.println(current + ": ");
        	 if (checkIfElevDateExists(above) && checkIfElevDateExists(current)){
        		 if (above.getEventualElevDate() != null) {
        			 compareAndSetDates(current, above.getEventualElevDate());
        		 } else if (above.getCurrentElevDate() != null) {
        			 compareAndSetDates(current, above.getCurrentElevDate());
        		 } else if (above.getUrgentElevDate() != null) {
        			 compareAndSetDates(current, above.getUrgentElevDate());
        		 } else {
        			 current.setShowDate(false);
        		 }
        	 } else if (!checkIfElevDateExists(above) && checkIfElevDateExists(current)) {
        		 current.setShowDate(true);
        		 System.out.println("SHOW DATE true bc above Elev date DNE");
        	 } else {
        		 current.setShowDate(false);
        	 }*/
         }
	}
	
	private void compareAndSetDates(Task currentTask, LocalDate aboveDate) {
		if (currentTask.getEventualElevDate() != null) {
			 if(!currentTask.getEventualElevDate().isEqual(aboveDate)) {
				 currentTask.setShowDate(true);
				 System.out.println("SHOW DATE true bc above Eventual Elev is not Equal");
			 }
		 } else if (currentTask.getCurrentElevDate() != null) {
			 if(!currentTask.getCurrentElevDate().isEqual(aboveDate)) {
				 currentTask.setShowDate(true);
				 System.out.println("SHOW DATE true bc above Current Elev is not Equal");
			 }
		 } else {
			 //Urgent Elev Date exists
			 if (!currentTask.getUrgentElevDate().isEqual(aboveDate)) {
				 currentTask.setShowDate(true);
				 System.out.println("SHOW DATE true bc above Urgent Elev is not Equal");
			 }
		 }
	}
	
	private boolean checkIfElevDateExists(Task task) {
		return (task.getEventualElevDate() != null ||
				task.getCurrentElevDate() != null ||
				task.getUrgentElevDate() != null);
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
			if (containsDate(taskName)) {
				int oldDash = taskName.indexOf("-");
				int newDash = taskName.indexOf("-", oldDash + 1);
				oldDash = newDash;
				
				newDash = taskName.indexOf("-", oldDash + 1);
				oldDash = newDash;
				taskName = taskName.substring(newDash);
				//System.out.println("prioir: " + taskName);
				newDash = taskName.indexOf("-");
				
				
				taskName = taskName.substring(newDash + 1).trim();
				//System.out.println(taskName);
			}
			
			if (activeTasks.get(counter).getName().equals(taskName)) {
				return activeTasks.get(counter);
			}
		}
		
		return null;
		//No Task with this name was found
	}
	
	private boolean containsDate(String taskName) {
		return taskName.toUpperCase().contains("MONDAY") ||
				taskName.toUpperCase().contains("SUNDAY") ||
				taskName.toUpperCase().contains("TUESDAY") ||
				taskName.toUpperCase().contains("WEDNESDAY") ||
				taskName.toUpperCase().contains("THURSDAY") ||
				taskName.toUpperCase().contains("FRIDAY") ||
				taskName.toUpperCase().contains("SATURDAY");
				
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
	
	/*public boolean setTaskCompleted(String taskName) {
		boolean stopForLoop = false;
		for (int counter = 0; counter < activeTasks.size() && !stopForLoop; counter ++) {
			if (activeTasks.get(counter).getName().equals(taskName)) {
				completedTasks.add(activeTasks.remove(counter));
				stopForLoop = true;
			}
		}
		
		return !stopForLoop;//True if successfully switched.
	}*/
	
	public boolean setTaskCompleted(Task taskName) {
		boolean stopForLoop = false;
		for (int counter = 0; counter < activeTasks.size() && !stopForLoop; counter ++) {
			if (activeTasks.get(counter).getName().equals(taskName.getName())) {
				completedTasks.add(activeTasks.remove(counter));
				stopForLoop = true;
			}
		}
		
		return stopForLoop;//True if successfully switched.
	}
	
	public boolean setCompletedTaskActive(Task taskName) {
		boolean stopForLoop = false;
		
		for (int counter = 0; counter < completedTasks.size() && !stopForLoop; counter ++) {
			//System.out.println(completedTasks.get);
			if (completedTasks.get(counter).getName().equals(taskName.getName())) {
				System.out.println("Found" + true);
				activeTasks.add(completedTasks.remove(counter));
				stopForLoop = true;
			}
		}
		
		return stopForLoop;//True if successfully switched.
	}
	
	

}
