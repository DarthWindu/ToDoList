import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import backend.*;
import frontend.*;

public class Main extends Application{
	protected static ToDoList todoList = new ToDoList();
	
	public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[])null);
    }

	
	public void start(Stage primaryStage) throws Exception {
		//import code the saved to do list is saved in todoList
		try {
			FileInputStream fileIn = new FileInputStream("src/" + "todolist" + ".java");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			todoList = (ToDoList) in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e1) {
			MainMenu a = new MainMenu(null);
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		if (todoList.getActiveTasks() == null) {
			todoList = new ToDoList();
		}
		
		//Show Gui
		
		try {
            ScrollPane page = (ScrollPane) FXMLLoader.load(getClass().getResource("test1.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("FXML is Simple");
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

	}
	
	@Override
	public void stop(){
	    System.out.println("Stage is closing");
	    for (Task task : todoList.getActiveTasks()) {
	    	System.out.println(task.getName());
	    }
	  //export code
	    try
        {
			FileOutputStream fileOut = new FileOutputStream("src/" + "todolist" + ".java");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(todoList); // change this to the toDoList you are trying to save - Done
			out.close();
			fileOut.close();
        }catch(IOException i)
        {
            i.printStackTrace();
        }
	}
}
