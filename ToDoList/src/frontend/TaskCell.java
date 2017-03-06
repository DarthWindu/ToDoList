package frontend;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class TaskCell extends ListCell<String>{
	private int indexToDelete = -1;
	public TaskCell() {
        setOnDragDetected(event -> {
            if (getItem() == null) {
                return;
                //If item is null, exit this function
            }

            ObservableList<String> items = this.getListView().getItems();
            indexToDelete = items.indexOf(this);

            Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(getItem());
            /*dragboard.setDragView(
                birdImages.get(
                    items.indexOf(
                        getItem()
                    )
                )
            );*/
            dragboard.setContent(content);

            event.consume();
        });

        setOnDragOver(event -> {
            if (event.getGestureSource() != this &&
                   event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });

        setOnDragEntered(event -> {
            if (event.getGestureSource() != this &&
                    event.getDragboard().hasString()) {
                setOpacity(0.3);
            }
        });

        setOnDragExited(event -> {
            if (event.getGestureSource() != this &&
                    event.getDragboard().hasString()) {
                setOpacity(1);
            }
        });

        setOnDragDropped(event -> {
            if (getItem() == null) {
                return;
            }

            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString()) {
            	this.setText(db.getString());

                success = true;
            }
            event.setDropCompleted(success);

            event.consume();
        });

        setOnDragDone(DragEvent::consume);
	}
	
	 @Override
     protected void updateItem(String item, boolean empty) {
         super.updateItem(item, empty);

         if (item != null) {
        	 this.setText(item);
         }
     }
}
