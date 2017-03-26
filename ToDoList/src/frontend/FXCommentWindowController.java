package frontend;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import backend.Comment;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class FXCommentWindowController {
	
	private boolean parentCommentExists = false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextArea txtaComment;

    @FXML
    private JFXButton btnCommit;

    @FXML
    private JFXButton btnDelete;

    @FXML
    void btnCommitAction(ActionEvent event) {
    	if (parentCommentExists) {
    		FXCommentWindow.parentComment.setComment(txtaComment.getText());
    	} else {
    		FXCommentWindow.parentTask.addComment(txtaComment.getText());
    		List<Comment> comments = FXCommentWindow.parentTask.getComments();
    		FXCommentWindow.parentComment = comments.get(comments.size() - 1);//Since we just added a comment, the current comment will be the last one in the list
    	}
    	
    	//Should the window close here?
    	
    	  Stage stage = (Stage) btnCommit.getScene().getWindow();
    	  stage.close();
    	 
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
    	
    	if (parentCommentExists) {
    		//Confirmation dialog?
    		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this comment?");
    		alert.showAndWait().ifPresent(response -> {
    		     if (response == ButtonType.OK) {
    		    	 //If response exists and is OK
    		    	 FXCommentWindow.parentTask.deleteComment(FXCommentWindow.parentComment);
    		     }
    		 });
    	} else {
    		txtaComment.setText("");
    	}
    	
    	
  	  Stage stage = (Stage) btnDelete.getScene().getWindow();
  	  stage.close();
  	 
    }

    @FXML
    void initialize() {
        assert txtaComment != null : "fx:id=\"txtaComment\" was not injected: check your FXML file 'commentWindow.fxml'.";
        assert btnCommit != null : "fx:id=\"btnCommit\" was not injected: check your FXML file 'commentWindow.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'commentWindow.fxml'.";
        
        parentCommentExists = FXCommentWindow.parentComment != null;
        if (parentCommentExists) {
        	txtaComment.setText(FXCommentWindow.parentComment.getComment());
        }
        
    }
}