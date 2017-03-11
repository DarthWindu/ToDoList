/**
 * Sample Skeleton for 'test1.fxml' Controller Class
 */

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import backend.Task;
import backend.ToDoList;
import frontend.MainMenu;
import frontend.TaskCell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
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
		/*ObservableList<String> data = FXCollections.observableArrayList();
        ToDoList todo = new ToDoList();//Load TodoList (from last session)
        for (Task task : todo.getActiveTasks()) {
        	data.add(task.getName());
        }
        myCustomListView.setItems(data);*/
		
		//Load serialized To Do List:
		try {
			FileInputStream fileIn = new FileInputStream("src/" + "todolist" + ".java");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			tdl = (ToDoList) in.readObject();
			in.close();
			fileIn.close();
			//MainMenu menu = new MainMenu(tdl);
		} catch (FileNotFoundException e1) {
			//MainMenu a = new MainMenu(null);
			//e1.printStackTrace();
			System.out.println("FileNotFound - MainController");
			tdl = null;
		} catch (IOException e1) {
			//e1.printStackTrace();
			System.out.println("IO Exception - MainController");
			tdl = null;
		} catch (ClassNotFoundException e1) {
			//e1.printStackTrace();
			System.out.println("ClassNotFound - MainController"); 
			tdl = null;
		}

		//Load Task Names into List
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
}
