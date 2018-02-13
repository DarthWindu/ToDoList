package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import backend.*;

public class Main extends Application{
	public static ToDoList todoList = new ToDoList();
	public static GsonBuilder gsonBuilder = new GsonBuilder();
	protected static Stage primStage = null;
	//public static File originOfToDoList;

	public static void main(String[] args) {
		Application.launch(Main.class, (java.lang.String[])null);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		boolean testingMode = false;//Change to FALSE When Delivering
		primStage = primaryStage;
		primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream( "/images/todolistIcon.png" )));
		primaryStage.setResizable(false);
		gsonBuilder.setPrettyPrinting();

		//Deserialize TodoList
		try {
			Gson gson = gsonBuilder.create();
			todoList = gson.fromJson(new FileReader("ToDoList.json"), ToDoList.class);

			todoList.setTestingMode(testingMode);

		} catch (FileNotFoundException e) {
			/*
			 * This will occur during the first run of the program. Make a prompt asking user if it's their first time.
			 */



		} catch (Exception e1) {
			//This is to ensure that the program keeps running.
			e1.printStackTrace();//For debugging
		} 

		if (todoList.getActiveTasks() == null) {
			//ToDoList could not be de-serialized

			/*
			 * PROMPTT!!!!!!
			 */

			todoList = new ToDoList();
		}

		//Show Gui
		//MainController.TDL_CHANGE_STATUS = MainController.TDL_UNCHANGED;
		try {

			ScrollPane page = (ScrollPane) FXMLLoader.load(getClass().getResource("test1.fxml"));
			Scene scene = new Scene(page);
			primaryStage.setScene(scene);
			primaryStage.setTitle("ToDoList - Student Edition");
			primaryStage.show();
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@Override
	public void stop(){
		System.out.println("Stage is closing");

		if (todoList.testingModeIsEnabled()) {
			for (Task task : todoList.getActiveTasks()) {
				System.out.println(task.getName());
			}
		}


		//Serialize Todo List
		try {
			Gson gson = gsonBuilder.create();
			gson.toJson(todoList, new FileWriter("toDoList.json"));

		} catch(IOException i) {
			i.printStackTrace();//for debugging
		}
		System.exit(0);//EXIT
	}
}
