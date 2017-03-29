package frontend;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.jfoenix.controls.JFXListView;

import backend.Comment;
import backend.HistoryItem;
import backend.NameChange;
import backend.PriorityChange;
import backend.Task;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;

public class FXHistoryWindow {
	private Stage stage;
	private ScrollPane pane;
	//private VBox historyBox;
	private JFXListView<HistoryBox> myCustomListView;
	private Task myTask;
	
	FXHistoryWindow(Task task, Scene scene) {
		myTask = task;
		stage = new Stage();
		stage.getIcons().add(new Image(Main.class.getResourceAsStream( "/images/todolistIcon.png" )));
		stage.setResizable(false);
		pane = new ScrollPane();
		pane.setVbarPolicy(ScrollBarPolicy.NEVER);
		//historyBox = new VBox(8);
		myCustomListView = new JFXListView<HistoryBox>();
		myCustomListView.setPrefWidth(505.0);
		myCustomListView.setPrefHeight(655.0);
		
		ArrayList<HistoryBox> histories = new ArrayList<HistoryBox>();
		for (HistoryItem hist : task.getHistoryItems()) {
			histories.add(new HistoryBox(hist, 5.0));
		}
		Collections.reverse(histories);
		myCustomListView.setItems(FXCollections.observableArrayList(histories));
		
		myCustomListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
		            if(mouseEvent.getClickCount() == 2){
		                System.out.println("Double clicked");
		               HistoryBox selected = myCustomListView.getSelectionModel().getSelectedItem();
		                try {
		                	//Label title = (Label) selected.getChildren().get(0);
		                	Comment comment = (Comment) selected.getItem();
		                	new FXCommentWindow(task,comment, stage);
		                	updateHistoryBox();
		                } catch (Exception ex) {
		                	//ex.printStackTrace();
		                }
		            }
		        }
		    }
		});
		
		pane.setContent(myCustomListView);
		stage.setScene(new Scene(pane, 500, 650));
		stage.setTitle("History window");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(
				(scene.getWindow()));
		stage.showAndWait();
	}
	
	private void updateHistoryBox() {
		ArrayList<HistoryBox> histories = new ArrayList<HistoryBox>();
		for (HistoryItem hist : myTask.getHistoryItems()) {
			histories.add(new HistoryBox(hist, 5.0));
		}
		Collections.reverse(histories);
		myCustomListView.setItems(FXCollections.observableArrayList(histories));
	}
	
	
	
	private class HistoryBox extends VBox {
		private HistoryItem item;
		
		HistoryBox(HistoryItem histItem, double spacing) {
			this.setSpacing(spacing);
			item = histItem;
			
			this.setStyle("-fx-border-style: solid;"
	                + "-fx-border-width: 1;"
	                + "-fx-border-color: black");

			
			
			String histTitle = "";
			Date day = histItem.getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy | hh:mm aa");
			histTitle = sdf.format(day);
			histTitle = histTitle + " | " + histItem.getText();
			
			Label titleLabel = new Label(histTitle);
			
			String addText = "";
			
			//SET History Item Description
			try{
				NameChange name = (NameChange)histItem;//If History Item is a namechange
				addText = "Changed name from " + name.getOldName() + " to " + name.getNewName();
			}catch(Exception e){}

			try{
				Comment com = (Comment)histItem;//If History Item is a Comment
				addText = com.getComment();
			}catch(Exception e){}

			
						
			try{
				PriorityChange pri = (PriorityChange)histItem;//If History Item is a priority change
				String priOld = "";
				String priNew = "";

				switch(pri.getOldStatus()) {
				case 0: priOld = "inactive";break;
				case 1: priOld = "eventual";break;
				case 2: priOld = "current";break;
				case 3: priOld = "urgent";break;
				case 4: priOld = "completed";break; 
				}

				switch(pri.getNewStatus()) {
				case 0: priNew = "inactive";break;
				case 1: priNew = "eventual";break;
				case 2: priNew = "current";break;
				case 3: priNew = "urgent";break;
				case 4: priNew = "completed";break;
				}

				addText = "Changed priority from " + priOld + " to " + priNew;
			}catch(Exception e){}
			//End SET History Item Description
			
			Label histText = new Label(addText);
			histText.setWrapText(true);
			
			this.getChildren().add(titleLabel);
			this.getChildren().add(histText);
		}
		
		public HistoryItem getItem() {
			return item;
		}
	}

}
