import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CompletedTasksWindow {
	protected static Stage compTasksStage = null;
	public CompletedTasksWindow(Stage primStage) {
		Stage stage = new Stage();
		Parent root = null;
		try {
			root = FXMLLoader.load(CompletedTasksController.class.getResource("completedTasks.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.setScene(new Scene(root));
		stage.setTitle("Completed Tasks");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(
				(primStage.getScene().getWindow()));
		compTasksStage = stage;
		stage.showAndWait();
	}
}
