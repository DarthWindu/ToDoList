package main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
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
import backend.adapters.TodolistLoadState;
import backend.adapters.UserTodolistAdapter;
import backend.config.AbstractAppConfigAdapter;
import backend.config.AppConfigBuilder;
import backend.config.GlobalConfigManager;

public class Main extends Application{
	public static ToDoList todoList = new ToDoList();
	public static GsonBuilder gsonBuilder = new GsonBuilder();
	protected static Stage primStage = null;
	private String fileName = "ToDoList.json";
	public static final boolean DEBUGGING_ENABLED = true;
	public static AbstractAppConfigAdapter appProps = new AppConfigBuilder().create();
	//public static File originOfToDoList;

	public static void main(String[] args) {
		Application.launch(Main.class, (java.lang.String[])null);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		//appProps.load(new FileInputStream("./src/backend/config/AppConfig.properties"));
		System.out.println("Checkpt 1 reached");
		System.out.println("Path to Last List: " + appProps.getProperty(AbstractAppConfigAdapter.KEYNAME_PATHTOLASTLIST));
		
		boolean testingMode = true;//Change to FALSE When Delivering
		GlobalConfigManager.setGlobalTestingMode(testingMode);

		primStage = primaryStage;
		primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream( "/images/todolistIcon.png" )));
		primaryStage.setResizable(false);
		gsonBuilder.setPrettyPrinting();

		//Deserialize TodoList
		try {
			Gson gson = gsonBuilder.create();
			todoList = gson.fromJson(new FileReader(appProps.getProperty("pathToLastList")), ToDoList.class);
			todoList.setTestingMode(testingMode);

		} catch (FileNotFoundException e) {
			UserTodolistAdapter.setLoadState(TodolistLoadState.FILE_NOT_FOUND);

			if(Main.DEBUGGING_ENABLED)
				System.err.println("File not Found");


		} catch (NullPointerException e1) {
			//The file was found, but the serialization data is either absent or corrupted
			//BLOCK NAME: CORRUPTION CHECK

			if(Main.DEBUGGING_ENABLED)
				e1.printStackTrace();

			UserTodolistAdapter.setLoadState(TodolistLoadState.CORRUPT);
			todoList = new ToDoList();
		} 

		//Final, Brute-Force check -- May have been replaced by ^ CORRUPTION CHECK block ^
		if (todoList.getActiveTasks() == null) {
			UserTodolistAdapter.setLoadState(TodolistLoadState.CORRUPT);
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

		File currentFile = new File(Main.appProps.getProperty(AbstractAppConfigAdapter.KEYNAME_PATHTOLASTLIST));
		System.out.println("Path to Last List: " + appProps.getProperty(AbstractAppConfigAdapter.KEYNAME_PATHTOLASTLIST));

		//Serialize Todo List
		try (Writer writer = new FileWriter(currentFile)){
			Gson gson = gsonBuilder.create();
			gson.toJson(todoList, writer);
			//writer.flush();


			System.out.println(gson.toJson(todoList));
			//System.out.println("\n\n\n");

			//readTest();

		} catch(IOException i) {
			i.printStackTrace();//for debugging
		}
		
		//Save AppConfig File
		appProps.saveAppConfig();
		System.exit(0);//EXIT
	}


	private void readTest() {
		Path p = Paths.get(fileName);
		try {
			List<String> l = Files.readAllLines(p);

			for(String s : l) {
				System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
