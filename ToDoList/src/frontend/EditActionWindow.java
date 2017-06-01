package frontend;

import java.io.IOException;

import backend.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	    FXMLLoader loader = new FXMLLoader( EditActionWindowController.class.getResource("/frontend/editActionWindow.fxml"));
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EditActionWindowController controller = (EditActionWindowController) loader.getController();
	    stage.setScene(new Scene(root));
	    stage.setTitle("Edit Task");
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(
	        (scene.getWindow()));
	    //stage.show();
	    editActionStage = stage;
	    
	    stage.setOnCloseRequest(event -> {
	        controller.getNameField();
	    	controller.setExternalRequest(true);
	        if (controller.fireError()) {
	        	controller.setExternalRequest(false);
	        	event.consume();
	        } 
	        
	        controller.setExternalRequest(false);
	        
	        
	        /*
	         * IMPORTANT DOCUMENTATION - The Puj
	         * 
	         * To avoid a bajillion popups, meticulously setting external request flags is crucial
	         * 
	         * The focus oscillates when the alert is closed, so failing to set those flags will trigger
	         * extraneous error popups from the Name textfield focusListener. 
	         * 
	         */
	    });
	    stage.showAndWait();
	}

}
