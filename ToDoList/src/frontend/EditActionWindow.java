package frontend;

import java.io.IOException;

import backend.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;

public class EditActionWindow {
	protected static Task taskBeingEdited = null;
	protected static Stage editActionStage = null;
	
	public EditActionWindow(Task task, Scene scene) {
		taskBeingEdited = task;
		Stage stage = new Stage();
		stage.getIcons().add(new Image(Main.class.getResourceAsStream( "/images/todolistIcon.png" )));
		stage.setResizable(false);
	    Parent root = null;
		try {
			root = FXMLLoader.load(
			    EditActionWindowController.class.getResource("/frontend/editActionWindow.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    stage.setScene(new Scene(root));
	    stage.setTitle("Edit Task");
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(
	        (scene.getWindow()));
	    //stage.show();
	    editActionStage = stage;
	    stage.showAndWait();
	}

}
