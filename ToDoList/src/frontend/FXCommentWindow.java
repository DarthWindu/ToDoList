package frontend;

import java.io.IOException;

import backend.Comment;
import backend.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;

public class FXCommentWindow {
	protected static Task parentTask = null;
	protected static Comment parentComment = null;
	
	FXCommentWindow(Task task, Stage parentStage) {
		Stage stage = new Stage();
		stage.getIcons().add(new Image(Main.class.getResourceAsStream( "/images/todolistIcon.png" )));
		stage.setResizable(false);
		Parent root = null;
		
		parentTask = task;
		parentComment = null;
		
		try {
			root = FXMLLoader.load(FXCommentWindowController.class.getResource("commentWindow.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		stage.setScene(new Scene(root));
		stage.setTitle("Comment");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(
				(parentStage.getScene().getWindow()));
		//compTasksStage = stage;
		stage.showAndWait();
	}
	
	FXCommentWindow(Task task, Comment comment, Stage parentStage) {
		parentTask = task;
		parentComment = comment;
		//System.out.println("Begins");
		Stage stage = new Stage();
		//System.out.println("CP -1");
		Parent root = null;
		try {
			//System.out.println("CP 0");
			root = FXMLLoader.load(FXCommentWindowController.class.getResource("commentWindow.fxml"));
			//System.out.println("CP 1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		stage.setScene(new Scene(root));
		stage.setTitle("Comment");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(
				(parentStage.getScene().getWindow()));
		//compTasksStage = stage;
		System.out.println("CP 2");
		stage.showAndWait();
	}

}
