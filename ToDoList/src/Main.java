

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import backend.*;
import frontend.*;

public class Main extends Application{
	
	public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[])null);
    }

	
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
            ScrollPane page = (ScrollPane) FXMLLoader.load(getClass().getResource("test1.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("FXML is Simple");
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

		ToDoList tdl;
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

	}
}
