package main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		primaryStage.setResizable(true);
		//import code the saved to do list is saved in todoList
		
		try{
			Reader reader = new InputStreamReader(new FileInputStream("./" + "Output" + ".json"), "UTF-8");
            Gson gson = new GsonBuilder().create();
            todoList = gson.fromJson(reader, ToDoList.class);
            if (todoList != null) {
            	System.out.println("NOT NULL");
            }
            //System.out.println(p);
        }
		
		/*try {
			FileInputStream fileIn = new FileInputStream("./" + "todolist" + ".java");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			todoList = (ToDoList) in.readObject();
			todoList.setTestingFlag(testingMode);
			in.close();
			fileIn.close();
		}*/ //catch (FileNotFoundException e1) {
			//MainMenu a = new MainMenu(null);
			//e1.printStackTrace();
		/*}*/ catch (IOException e1) {
			//e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (todoList.getActiveTasks() == null) {
			//ToDoList could not be de-serialized
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
		Writer writer;
		try {
			writer = new FileWriter("Output.json");
			
			Gson gson = new GsonBuilder().create();
	        gson.toJson(todoList, writer);
	        //gson.toJson(123, writer);

	        writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
		
	    /*System.out.println("Stage is closing");
	    for (Task task : todoList.getActiveTasks()) {
	    	System.out.println(task.getName());
	    }
	  //export code
	    try
        {
			FileOutputStream fileOut = new FileOutputStream("./" + "todolist" + ".java");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(todoList); // change this to the toDoList you are trying to save - Done
			out.close();
			fileOut.close();
        }catch(IOException i)
        {
            i.printStackTrace();
        }*/
	    System.exit(0);
	}
}
