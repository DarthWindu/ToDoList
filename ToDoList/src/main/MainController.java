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
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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
	private MainController main = this;

	public static int TDL_CHANGE_STATUS = 0;// 0 means unchanged, 1 means changed, add other meanings if necessary. Used to determine overwrite of default save.
	public static int TDL_UNCHANGED = 0,
			TDL_CHANGED = 1;

	private ObservableList<String> data;
	protected ToDoList tdl = null;
	protected ArrayList<String> taskIDs = new ArrayList<String>();

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

			data.add(0, text);
			Task newTask = new Task(text,Calendar.getInstance().getTime(), Task.URGENT);
			
			try {
				tdl.add(0, newTask);
			}catch (NullPointerException e) {
				//e.printStackTrace();
				System.out.println("tdl is NULL");
			}
			
			taskIDs.add(0, newTask.getID());
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
		tdl.sortTasks();

		tdl.checkElevations();
		//tdl = Main.todoList;
		if (tdl != null) {
			ArrayList<Task> tasks = tdl.getActiveTasks();
			if (tasks != null) {
				ArrayList<String> taskNames = new ArrayList<String>();
				taskIDs = new ArrayList<String>();
				for (Task activeTask: tasks) {
					taskNames.add(activeTask.getFormattedName());
					taskIDs.add(activeTask.getID());
				}
				data = FXCollections.observableArrayList(taskNames);
			} else {
				data = FXCollections.observableArrayList();
			}
		} else {
			System.out.println("TDL is NULL");
			data = FXCollections.observableArrayList();
		}

		for (String str : myCustomListView.getItems()) {
			System.out.println("\n Item: " + str);
		}
		myCustomListView.setItems(data);
		myCustomListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> list) {
				return new TaskCell(main);
			}
		});

	}

	public boolean saveFile(File file) {
		try
        {
	        JAXBContext context = JAXBContext.newInstance(ToDoList.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	        // Write to File
	        m.marshal(tdl, file);
	        return true;

        }catch(Exception i)
        {
            i.printStackTrace();
            return false;
        }
	}

	public boolean loadFromSave(File file) {
		ToDoList todoList_temp = null;
		
		try {
	        JAXBContext context = JAXBContext.newInstance(ToDoList.class);
	        Unmarshaller unmarshaller = context.createUnmarshaller();
	        todoList_temp = (ToDoList)unmarshaller.unmarshal(new FileReader(file));

		} catch (Exception e1) {
			//MainMenu a = new MainMenu(null);
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
				Alert alert = new Alert(AlertType.INFORMATION, "Successfully loaded from backup!");
				alert.showAndWait();
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
					//String item = myCustomListView.getSelectionModel().getSelectedItem();
					int index = myCustomListView.getSelectionModel().getSelectedIndex();
					//System.out.println("Double Click on: " + item);//Works
					Task taskToEdit = tdl.getTaskByID(taskIDs.get(index));
					new EditActionWindow(taskToEdit, Main.primStage.getScene());//loads modally
					//new EditActionItem(taskToEdit);
					//System.out.println("fall through");
					loadTaskNames();//Works
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
		contextMenu.getItems().addAll(completed, edit, deleteTask);

		completed.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String item = myCustomListView.getSelectionModel().getSelectedItem();//Get task name
				System.out.println("Right Click on: " + item);//Works
				int index = myCustomListView.getSelectionModel().getSelectedIndex();
				try {
					Task taskToComplete = tdl.getTaskByID(taskIDs.get(index));
					taskToComplete.setStatus(Task.COMPLETED);
					tdl.setTaskCompletedByID(taskIDs.get(index));
					loadTaskNames();
				}catch (Exception e) {/*Usually a Null Pointer caused by right clicking and selecting an action on an empty row */}
			}
		});
		deleteTask.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String item = myCustomListView.getSelectionModel().getSelectedItem();//Get task name
				System.out.println("Right Click on: " + item);//Works
				int index = myCustomListView.getSelectionModel().getSelectedIndex();
				try {
					Task taskToDelete = tdl.getTaskByID(taskIDs.get(index));
					Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this task?");
					alert.showAndWait().ifPresent(response -> {
						if (response == ButtonType.OK) {
							//If response exists and is OK
							tdl.deleteByID(taskIDs.get(index));
							//tdl.getActiveTasks().remove(taskToDelete);//Function is not verified to be working
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
				int index = myCustomListView.getSelectionModel().getSelectedIndex();
				try {
					Task taskToEdit = tdl.getTaskByID(taskIDs.get(index));
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
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ToDoList XML (*.xml)", "*.xml");
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
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ToDoList XML (*.xml)", "*.xml");
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
					taskNames.add(activeTask.getName());
					taskIDs.add(activeTask.getID());
					//System.out.println(activeTask.getID());
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

		/*for (String str : myCustomListView.getItems()) {
			System.out.println("\n Item: " + str);
		}*/


		myCustomListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> list) {
				return new TaskCell(main);
			}
		});
		myCustomListView.setItems(data);
		loadTaskNames();

	}
	//==================================================================
	//INITIALIZATION Methods ==> END
	//==================================================================
}
