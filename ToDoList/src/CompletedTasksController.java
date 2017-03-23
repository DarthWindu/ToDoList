import java.util.ArrayList;

import com.jfoenix.controls.JFXListView;

import backend.Task;
import backend.ToDoList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class CompletedTasksController {

    @FXML
    private JFXListView<String> myListView;
    
    @FXML
    public void initialize() {
    	ToDoList tdl = Main.todoList;
    	ObservableList<String> data = null;
    	if (tdl != null) {
			ArrayList<Task> tasks = tdl.getCompletedTasks();
			if (tasks != null) {
				ArrayList<String> taskNames = new ArrayList<String>();
				for (Task compTask: tasks) {
					taskNames.add(compTask.getName());
				}
				data = FXCollections.observableArrayList(taskNames);
			} else {
				data = FXCollections.observableArrayList();
			}
		} else {
			System.out.println("TDL is NULL");
			data = FXCollections.observableArrayList();
		}
		myListView.setItems(data);
    }

}
