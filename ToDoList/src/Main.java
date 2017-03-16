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
	
	public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[])null);
    }

	
	public void start(Stage primaryStage) throws Exception {
		try {
            ScrollPane page = (ScrollPane) FXMLLoader.load(getClass().getResource("test1.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("FXML is Simple");
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
		//import code the saved to do list is saved in tdl
		ToDoList tdl = new ToDoList();
		try {
			FileInputStream fileIn = new FileInputStream("src/" + "todolist" + ".java");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			tdl = (ToDoList) in.readObject();
			in.close();
			fileIn.close();
			MainMenu menu = new MainMenu(tdl);
		} catch (FileNotFoundException e1) {
			MainMenu a = new MainMenu(null);
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		//export code
		try
        {
			FileOutputStream fileOut = new FileOutputStream("src/" + "todolist" + ".java");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(new ToDoList()); // change this to the toDoList you are trying to save
			out.close();
			fileOut.close();
        }catch(IOException i)
        {
            i.printStackTrace();
        }

	}
}
