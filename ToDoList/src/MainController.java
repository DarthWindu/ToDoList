

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import backend.Task;
import backend.ToDoList;
import frontend.EditActionItem;
import frontend.MainMenu;
import frontend.TaskCell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainController {

	private ObservableList<String> data;
	private ToDoList tdl = null;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="myScrollPane"
	private ScrollPane myScrollPane; // Value injected by FXMLLoader

	@FXML // fx:id="myAnchorPane"
	private AnchorPane myAnchorPane; // Value injected by FXMLLoader

	@FXML // fx:id="mainMenuBar"
	private MenuBar mainMenuBar; // Value injected by FXMLLoader

	@FXML // fx:id="menuFile"
	private Menu menuFile; // Value injected by FXMLLoader

	@FXML // fx:id="menuCreateBackup"
	private MenuItem menuCreateBackup; // Value injected by FXMLLoader

	@FXML // fx:id="menuRestore_Backup"
	private MenuItem menuRestore_Backup; // Value injected by FXMLLoader

	@FXML // fx:id="menuPrint"
	private MenuItem menuPrint; // Value injected by FXMLLoader

	@FXML // fx:id="menuCAI"
	private Menu menuCAI; // Value injected by FXMLLoader

	@FXML // fx:id="menuQuit"
	private Menu menuQuit; // Value injected by FXMLLoader

	@FXML // fx:id="menuHelp"
	private Menu menuHelp; // Value injected by FXMLLoader

	@FXML // fx:id="menuAbout"
	private MenuItem menuAbout; // Value injected by FXMLLoader

	@FXML // fx:id="myCustomListView"
	private JFXListView<String> myCustomListView; // Value injected by FXMLLoader

	@FXML // fx:id="myTextField"
	private JFXTextField myTextField; // Value injected by FXMLLoader

	@FXML
	public void exitApplication(ActionEvent event) {
		Platform.exit();
	}

	@SuppressWarnings("deprecation")
	@FXML // This method is called by the FXMLLoader when initialization is complete
	public void initialize() {
		assert myScrollPane != null : "fx:id=\"myScrollPane\" was not injected: check your FXML file 'test1.fxml'.";
		assert myAnchorPane != null : "fx:id=\"myAnchorPane\" was not injected: check your FXML file 'test1.fxml'.";
		assert mainMenuBar != null : "fx:id=\"mainMenuBar\" was not injected: check your FXML file 'test1.fxml'.";
		assert menuFile != null : "fx:id=\"menuFile\" was not injected: check your FXML file 'test1.fxml'.";
		assert menuCreateBackup != null : "fx:id=\"menuCreateBackup\" was not injected: check your FXML file 'test1.fxml'.";
		assert menuRestore_Backup != null : "fx:id=\"menuRestore_Backup\" was not injected: check your FXML file 'test1.fxml'.";
		assert menuPrint != null : "fx:id=\"menuPrint\" was not injected: check your FXML file 'test1.fxml'.";
		assert menuCAI != null : "fx:id=\"menuCAI\" was not injected: check your FXML file 'test1.fxml'.";
		assert menuQuit != null : "fx:id=\"menuQuit\" was not injected: check your FXML file 'test1.fxml'.";
		assert menuHelp != null : "fx:id=\"menuHelp\" was not injected: check your FXML file 'test1.fxml'.";
		assert menuAbout != null : "fx:id=\"menuAbout\" was not injected: check your FXML file 'test1.fxml'.";
		assert myCustomListView != null : "fx:id=\"myCustomListView\" was not injected: check your FXML file 'test1.fxml'.";
		assert myTextField != null : "fx:id=\"myTextField\" was not injected: check your FXML file 'test1.fxml'.";

		//ALL ACTION HANDLERS GO HERE

		//Double Click Action Listener for List view of active tasks
		myCustomListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent click) {

				if (click.getClickCount() == 2) {
					//Use ListView's getSelected Item
					String item = myCustomListView.getSelectionModel().getSelectedItem();
					System.out.println("Double Click on: " + item);//Works
					Task taskToEdit = Main.todoList.getTask(item);
					new EditActionItem(taskToEdit);
					loadTaskNames();//Works
					//use this to do whatever you want to. Open Link etc.
				}
			}
		});
		//-----------------------------------------------------------------------
		menuCAI.setGraphic(
                ButtonBuilder.create()
                .text("Completed Tasks")
                .onAction(new EventHandler<ActionEvent>(){
                    @Override public void handle(ActionEvent event) {
                        //TODO
                    	System.out.println("CAI Action Fired");
        				//Use ListView's getSelected Item
        				
        				Stage stage = new Stage();
        			    Parent root = null;
        				try {
        					root = FXMLLoader.load(CompletedTasksController.class.getResource("completedTasks.fxml"));
        				} catch (IOException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
        			    stage.setScene(new Scene(root));
        			    stage.setTitle("Completed Tasks");
        			    stage.initModality(Modality.WINDOW_MODAL);
        			    stage.initOwner(
        			        ((Node)event.getSource()).getScene().getWindow() );
        			    stage.show();
                 } })
                .build());
		
		//Set Right Click Menu
		final ContextMenu contextMenu = new ContextMenu();
		MenuItem completed = new MenuItem("Set task to completed");
		MenuItem deleteTask = new MenuItem("Delete");
		MenuItem edit = new MenuItem("Edit");
		contextMenu.getItems().addAll(completed, deleteTask, edit);

		completed.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String item = myCustomListView.getSelectionModel().getSelectedItem();//Get task name
				System.out.println("Right Click on: " + item);//Works
				Task taskToComplete = Main.todoList.getTask(item);
				taskToComplete.setStatus(Task.COMPLETED);
				tdl.setTaskCompleted(item);
				loadTaskNames();
			}
		});
		deleteTask.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String item = myCustomListView.getSelectionModel().getSelectedItem();//Get task name
				System.out.println("Right Click on: " + item);//Works
				Task taskToDelete = Main.todoList.getTask(item);
				tdl.getActiveTasks().remove(taskToDelete);//Function is not verified to be working
				loadTaskNames();
			}
		});
		edit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String item = myCustomListView.getSelectionModel().getSelectedItem();//Get task name
				System.out.println("Right Click on: " + item);//Works
				Task taskToEdit = Main.todoList.getTask(item);
				contextMenu.hide();
				new EditActionItem(taskToEdit);
				loadTaskNames();//Works
			}
		});
		myCustomListView.setContextMenu(contextMenu);
		//-------------------------------------------------------------------------------


		//Load Task Names into List
		tdl = Main.todoList;
		if (tdl != null) {
			ArrayList<Task> tasks = tdl.getActiveTasks();
			if (tasks != null) {
				ArrayList<String> taskNames = new ArrayList<String>();
				for (Task activeTask: tasks) {
					taskNames.add(activeTask.getName());
				}
				data = FXCollections.observableArrayList(taskNames);
			} else {
				data = FXCollections.observableArrayList();
			}
		} else {
			System.out.println("TDL is NULL");
			data = FXCollections.observableArrayList();
		}

		//SAMPLE - 

		myCustomListView.setItems(data);
		myCustomListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> list) {
				return new TaskCell();
			}
		});
	}

	@FXML
	public void onEnter(ActionEvent ae){
		//When 'Enter' is pressed in the textfield
		String text = myTextField.getText();
		text = text.trim();//Removes leading/trialing whitespace
		if (!text.equals("")) {
			data.add(text);
			try {
				tdl.add(new Task(text));
			}catch (NullPointerException e) {
				//e.printStackTrace();
				System.out.println("tdl is NULL");
			}
		}

		myTextField.clear();
		//System.out.println("test") ;
	}

	public void loadTaskNames() {
		tdl = Main.todoList;
		if (tdl != null) {
			ArrayList<Task> tasks = tdl.getActiveTasks();
			if (tasks != null) {
				ArrayList<String> taskNames = new ArrayList<String>();
				for (Task activeTask: tasks) {
					taskNames.add(activeTask.getName());
				}
				data = FXCollections.observableArrayList(taskNames);
			} else {
				data = FXCollections.observableArrayList();
			}
		} else {
			System.out.println("TDL is NULL");
			data = FXCollections.observableArrayList();
		}
		myCustomListView.setItems(data);
		myCustomListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> list) {
				return new TaskCell();
			}
		});
	}
	
	@FXML
	public void completedTasksOnAction(ActionEvent evt) {
		System.out.print("hkjhkjfd");
	}
}
