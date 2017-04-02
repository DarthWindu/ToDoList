package main;


import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import backend.Task;
import backend.ToDoList;
import frontend.EditActionWindow;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;

public class MainController {

	public static int TDL_CHANGE_STATUS = 0;// 0 means unchanged, 1 means changed, add other meanings if necessary. Used to determine overwrite of default save.
	public static int TDL_UNCHANGED = 0,
			TDL_CHANGED = 1;

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
	
	@FXML
	private MenuItem menuItemCAI;

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
		/*	Aliveness tests - @author Pujit
		 * 
		 * assert myScrollPane != null : "fx:id=\"myScrollPane\" was not injected: check your FXML file 'test1.fxml'.";
		 * assert myAnchorPane != null : "fx:id=\"myAnchorPane\" was not injected: check your FXML file 'test1.fxml'.";
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

		 */


		//ALL ACTION HANDLERS GO HERE

		setDoubleClickAction();
		//setClickCompTasksAction();
		setListRightClickMenu();
		initBackupFunctionality();
		initRestoreFunctionality();
		initLoadTasks();	
	}
	
	//==================================================================
	//FXML ACTION LISTENERS - AUTO INJECTED ==> Begins
	//==================================================================
	@FXML
	public void onEnter(ActionEvent ae){
		//When 'Enter' is pressed in the textfield
		String text = myTextField.getText();
		text = text.trim();//Removes leading/trialing whitespace
		if (!text.equals("")) {
			if (tdl.getTask(text) == null) {
				if (tdl.getCompletedTask(text) == null) {
					
					//data.add(text);
					data.add(0, text);
					try {
						tdl.add(new Task(text,Calendar.getInstance().getTime(), Task.URGENT));
					}catch (NullPointerException e) {
						//e.printStackTrace();
						System.out.println("tdl is NULL");
					}
					
					
				} else {
					Alert alert = new Alert(AlertType.INFORMATION, "This task has already been completed!");
					alert.showAndWait();
				}
			} else {
				Alert alert = new Alert(AlertType.INFORMATION, "This task already exists!");
				alert.showAndWait();
			}
		}

		myTextField.clear();
		//System.out.println("test") ;
	}



	@FXML
	public void completedTasksOnAction(ActionEvent evt) {
		System.out.print("hkjhkjfd");
	}
	
	@FXML
	public void onViewCAIaction(ActionEvent evt) {
		new CompletedTasksWindow(Main.primStage);
		loadTaskNames();
	}
	//==================================================================
	//FXML ACTION LISTENERS - AUTO INJECTED ==> ENDS
	//==================================================================
	
	
	
	
	
	//==================================================================
	//Utility Methods ==> BEGIN
	//==================================================================
	
	public void loadTaskNames() {
		//SHOULD CHECK ELEVATIONS ALSO GO HERE?
		
		tdl.sortTasks();
		tdl.checkElevations();
		//tdl = Main.todoList;
		if (tdl != null) {
			ArrayList<Task> tasks = tdl.getActiveTasks();
			if (tasks != null) {
				ArrayList<String> taskNames = new ArrayList<String>();
				for (Task activeTask: tasks) {
					taskNames.add(activeTask.getFormattedName());
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

	public boolean saveFile(File file) {
		try
		{
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(tdl); // change this to the toDoList you are trying to save - Done
			out.close();
			fileOut.close();
			return true;
		}catch(IOException i)
		{
			i.printStackTrace();
			return false;
		}
	}

	public boolean loadFromSave(File file) {
		ToDoList todoList_temp = null;
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			todoList_temp = (ToDoList) in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e1) {
			//MainMenu a = new MainMenu(null);
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			if (todoList_temp.getActiveTasks() == null) {
				//ToDoList could not be de-serialized
				System.out.println("Loading FAILED!!!!");
				Alert alert = new Alert(AlertType.INFORMATION, "Restoration of this Todolist was not successful. No changes have been made.");
				alert.showAndWait();
				return false;
			} else {
				System.out.println("Loading Succeeded!");
				tdl = todoList_temp;//Could cause bug
				Main.todoList = todoList_temp;//Could cause bug
				//MainController.TDL_CHANGE_STATUS = MainController.TDL_CHANGED;
				loadTaskNames();
				/*Alert alert = new Alert(AlertType.INFORMATION, "!");
				alert.showAndWait();*/
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION, "Restoration of this Todolist was not successful. No changes have been made.");
			alert.showAndWait();
			return false;
		}
		
		
		
	}
	//==================================================================
	//Utility Methods ==> END
	//==================================================================
	
	
	
	
	//==================================================================
	//INITIALIZATION Methods ==> BEGIN
	//==================================================================

	private void setDoubleClickAction() {
		//Double Click Action Listener for List view of active tasks
		myCustomListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent click) {

				if (click.getClickCount() == 2 && click.getButton() == MouseButton.PRIMARY) {
					
					//Use ListView's getSelected Item
					String item = myCustomListView.getSelectionModel().getSelectedItem();
					System.out.println("Double Click on: " + item);//Works
					Task taskToEdit = tdl.getTask(item);
					new EditActionWindow(taskToEdit, Main.primStage.getScene());//loads modally
					//new EditActionItem(taskToEdit);
					//System.out.println("fall through");
					loadTaskNames();//Works
					//use this to do whatever you want to. Open Link etc.
				}
			}
		});
		//-----------------------------------------------------------------------
	}


	private void setListRightClickMenu() {
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
				try {
					Task taskToComplete = tdl.getTask(item);
					taskToComplete.setStatus(Task.COMPLETED);
					tdl.setTaskCompleted(taskToComplete);
					loadTaskNames();
				}catch (Exception e) {/*Usually a Null Pointer*/}
			}
		});
		deleteTask.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String item = myCustomListView.getSelectionModel().getSelectedItem();//Get task name
				System.out.println("Right Click on: " + item);//Works
				try {
					Task taskToDelete = tdl.getTask(item);
					Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this task?");
		    		alert.showAndWait().ifPresent(response -> {
		    		     if (response == ButtonType.OK) {
		    		    	 //If response exists and is OK
		    		    	 tdl.getActiveTasks().remove(taskToDelete);//Function is not verified to be working
		    					loadTaskNames();
		    		     }
		    		 });
				}catch (Exception ex) {/*Usually a Null Pointer*/}
			}
		});
		edit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String item = myCustomListView.getSelectionModel().getSelectedItem();//Get task name
				System.out.println("Right Click on: " + item);//Works
				try {
					Task taskToEdit = tdl.getTask(item);
					contextMenu.hide();//Hides context menu before opening Edit Action Window
					new EditActionWindow(taskToEdit, Main.primStage.getScene());
					loadTaskNames();//Works
				}catch (Exception ex) {/*Usually a Null Pointer*/}
			}
		});
		myCustomListView.setContextMenu(contextMenu);
		//-------------------------------------------------------------------------------
	}

	private void initBackupFunctionality() {
		menuCreateBackup.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();

				//Set extension filter
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Serialized JAVA objects and files (*.java)", "*.java");
				fileChooser.getExtensionFilters().add(extFilter);

				//Show save file dialog
				File file = fileChooser.showSaveDialog(Main.primStage);

				if(file != null){
					saveFile(file);
				}
			}
		});
		//-----------------------------------------------------------------------------------
	}

	private void initRestoreFunctionality() {
		menuRestore_Backup.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();

				//Set extension filter
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Serialized JAVA objects and files (*.java)", "*.java");
				fileChooser.getExtensionFilters().add(extFilter);

				//Show open file dialog
				File file = fileChooser.showOpenDialog(Main.primStage);

				if(file != null){
					loadFromSave(file);
				}
			}
		});
	}

	private void initLoadTasks() {
		//Load Task Names into List
		tdl = Main.todoList;
		tdl.sortTasks();
		tdl.checkElevations();//Checks if priorities need to be updated
		if (tdl != null) {
			ArrayList<Task> tasks = tdl.getActiveTasks();
			if (tasks != null) {
				ArrayList<String> taskNames = new ArrayList<String>();
				for (Task activeTask: tasks) {
					//activeTask.checkElevation();
					taskNames.add(activeTask.getFormattedName());
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
	//==================================================================
	//INITIALIZATION Methods ==> END
	//==================================================================
}
