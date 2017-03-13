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

	}
}
