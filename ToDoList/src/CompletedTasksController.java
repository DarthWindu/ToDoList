import java.util.ArrayList;

import com.jfoenix.controls.JFXListView;

import backend.Task;
import backend.ToDoList;
import frontend.EditActionWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class CompletedTasksController {

	private ToDoList tdl = null;

	@FXML
	private JFXListView<String> myListView;

	@FXML
	public void initialize() {
		loadTaskNames();
		//-------------------------------------------
		final ContextMenu contextMenu = new ContextMenu();
		//MenuItem completed = new MenuItem("Set task to completed");
		MenuItem deleteTask = new MenuItem("Delete");
		MenuItem edit = new MenuItem("Edit");
		contextMenu.getItems().addAll(/*completed,*/ deleteTask, edit);

		/*completed.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String item = myListView.getSelectionModel().getSelectedItem();//Get task name
				System.out.println("Right Click on: " + item);//Works
				Task taskToComplete = Main.todoList.getTask(item);
				taskToComplete.setStatus(Task.COMPLETED);
				tdl.setTaskCompleted(item);
				loadTaskNames();
			}
		});*/
		deleteTask.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String item = myListView.getSelectionModel().getSelectedItem();//Get task name
				System.out.println("Right Click on: " + item);//Works
				Task taskToDelete = Main.todoList.getCompletedTask(item);
				Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this task?");
				alert.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK) {
						//If response exists and is OK
						tdl.getCompletedTasks().remove(taskToDelete);//Function is not verified to be working
						loadTaskNames();
					}
				});
			}
		});
		edit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String item = myListView.getSelectionModel().getSelectedItem();//Get task name
				System.out.println("Right Click on: " + item);//Works
				Task taskToEdit = Main.todoList.getCompletedTask(item);
				contextMenu.hide();
				new EditActionWindow(taskToEdit, CompletedTasksWindow.compTasksStage.getScene());
				loadTaskNames();//Works
			}
		});
		myListView.setContextMenu(contextMenu);
		//-----------------------------------------
		myListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent click) {

				if (click.getClickCount() == 2) {
					//Use ListView's getSelected Item
					String item = myListView.getSelectionModel().getSelectedItem();
					System.out.println("Double Click on: " + item);//Works
					Task taskToEdit = Main.todoList.getCompletedTask(item);
					new EditActionWindow(taskToEdit, CompletedTasksWindow.compTasksStage.getScene());
					loadTaskNames();//Works
					//use this to do whatever you want to. Open Link etc.
				}
			}
		});
	}

	private void loadTaskNames() {
		tdl = Main.todoList;
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
