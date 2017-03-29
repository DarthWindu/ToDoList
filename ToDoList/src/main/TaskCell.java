package main;


import java.util.ArrayList;

import backend.Task;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class TaskCell extends ListCell<String>{ //Chaange string to Text if you choose that route
	private int indexToDelete = -1;
	public TaskCell() {
		setOnDragDetected(event -> {
			if (getItem() == null) {
				return;
				//If item is null, exit this function
			}

			/*ObservableList<String> items = this.getListView().getItems();
			indexToDelete = items.indexOf(this.getText());
			System.out.println(indexToDelete);*/

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
				/*ObservableList<String> items = this.getListView().getItems();
				indexToDelete = items.indexOf(this.getText());
				System.out.println(indexToDelete);*/
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
			//System.out.println("Happening");
			Dragboard db = event.getDragboard();
			boolean success = false;

			if (db.hasString()) {
				//System.out.println("Entered Drop Block");
				ObservableList<String> items = this.getListView().getItems();
				//ArrayList<String> strItems = new ArrayList<String>();
				/*for (Text txt : items) {
					strItems.add(txt.getText());
				}*/
				int draggedIndex = items.indexOf(db.getString());
				if (draggedIndex > -1) {
					int indexOfThis = items.indexOf(this.getItem());
					ArrayList<Task> tasks = Main.todoList.getActiveTasks();
					
					if (draggedIndex == items.size() - 1 && indexOfThis == items.size() - 2) {
						//System.out.println("We in there");
						items.add(items.remove(indexOfThis));
						tasks.add(tasks.remove(indexOfThis));
						success = true;
					} else {
						String cellToMove = items.remove(draggedIndex);
						Task taskToMove = tasks.remove(draggedIndex);
						//System.out.println(cellToMove);
						
						
						if (indexOfThis == items.size() - 1)
						{
							items.add(cellToMove);
							tasks.add(taskToMove);
						}else {
							items.add(indexOfThis, cellToMove);
							tasks.add(indexOfThis, taskToMove);
						}

						//this.setText(db.getString());
						success = true;
					}
					int index = 0;
					if (draggedIndex < indexOfThis) {
						//Dragged down - set priority to priority of task above
						index = items.indexOf(db.getString());
						int priorIndex = index - 1;
						tasks.get(index).setStatus(tasks.get(priorIndex).getStatus());
					} else {
						//Dragged up - set priority to priority of task below
						index = items.indexOf(db.getString());
						int afterIndex = index + 1;
						tasks.get(index).setStatus(tasks.get(afterIndex).getStatus());
					}
					
					this.setText(tasks.get(index).getName());
					
					//System.out.println("Drop should be successful");
					/*for (String text : items) {
						//System.out.println("item: " + text);
					}*/
				}
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
			//this.setItem(item);
			this.setText(item);
		}
		try {
			if (Main.todoList.getTask(item) != null) {
				switch (Main.todoList.getTask(item).getStatus()) {
				case Task.URGENT: super.setStyle("-fx-font-weight: bold");
					break;
					
				case Task.CURRENT: super.setStyle("-fx-font-weight: normal");
					break;
				
				case Task.EVENTUAL: super.setStyle("-fx-font-style: italic");
					break;
					
				case Task.INACTIVE: super.setStyle("-fx-font-style: italic");
					break;
				
				default: super.setStyle("-fx-font-weight: normal");
				}
			}
		}catch (NullPointerException e) {
		}
		
		
	}
}
