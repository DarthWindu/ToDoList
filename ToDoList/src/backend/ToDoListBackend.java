package backend;

import java.util.ArrayList;

public class ToDoListBackend {
	//Should this be serialized??
	private ArrayList<Task> tasks;
	
	public ArrayList<Task> getTasks()
	{
		return tasks;
	}
	
	public void add(Task task)
	{
		tasks.add(task);
	}
	
	public boolean deleteTask(Task deleteTask)
	{
		for (int counter = 0; counter < tasks.size(); counter ++)
		{
			if (deleteTask.isEqual(tasks.get(counter))) {
				tasks.remove(counter);
				return true;//Item was deleted
			}
		}
		
		return false; //Item not found
	}
}
