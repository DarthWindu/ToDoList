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
	private MainController controller = null;
	public static int draggedIndex = 0;
	public TaskCell(MainController mainController) {
		controller = mainController;

		initSetDragDetected();
		initSetOnDragOver();
		initSetOnDragEntered();
		initSetOnDragExited();
		initSetOnDragDropped();
		setOnDragDone(DragEvent::consume);	
	}

	//***********INITIALIZATION*****************************

	private void initSetDragDetected() {
		setOnDragDetected(event -> {
			//System.out.println(this.getText());
			if (getItem() == null) {
				return;
				//If item is null, exit this function
			}
			
			draggedIndex = this.getIndex();

			Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
			ClipboardContent content = new ClipboardContent();
			
			content.putString(getItem());
			dragboard.setContent(content);
			event.consume();
		});
	}

	private void initSetOnDragOver() {
		setOnDragOver(event -> {
			if (event.getGestureSource() != this &&
					event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.MOVE);
			}

			event.consume();
		});
	}

	private void initSetOnDragEntered() {
		setOnDragEntered(event -> {
			if (event.getGestureSource() != this &&
					event.getDragboard().hasString()) {
				setOpacity(0.3);
			}
		});
	}

	private void initSetOnDragExited() {
		setOnDragExited(event -> {
			if (event.getGestureSource() != this &&
					event.getDragboard().hasString()) {
				setOpacity(1);
				//controller.loadTaskNames();
			}
		});
	}

	/**
	 * When something is dropped on this Task Cell
	 */
	private void initSetOnDragDropped() {
		setOnDragDropped(event -> {
			System.out.println("DROPPED");
			if (getItem() == null) {
				return;
			}
			//System.out.println("Happening");
			Dragboard db = event.getDragboard();
			boolean success = false;

			if (db.hasString()) {
				ObservableList<String> items = this.getListView().getItems();
				
				if (draggedIndex > -1) {
					int indexOfThis = this.getIndex();
					ArrayList<Task> tasks = Main.todoList.getActiveTasks();
					
					
					if (draggedIndex == items.size() - 1 && indexOfThis == items.size() - 2) {
						//System.out.println("We in there");
						items.add(items.remove(indexOfThis));
						tasks.add(tasks.remove(indexOfThis));
						success = true;
					} else {
						String cellToMove = items.remove(draggedIndex);
						Task taskToMove = tasks.remove(draggedIndex);
						String idToMove = controller.taskIDs.remove(draggedIndex);
						//System.out.println(cellToMove);
						
						items.add(this.getIndex(), cellToMove);
						tasks.add(this.getIndex(), taskToMove);
						controller.taskIDs.add(this.getIndex(), idToMove);
						
						
						/*if (indexOfThis == items.size() - 1)
						{
							items.add(cellToMove);
							tasks.add(taskToMove);
						}else {
							items.add(indexOfThis, cellToMove);
							tasks.add(indexOfThis, taskToMove);
						}*/

						//this.setText(db.getString());
						success = true;
					}
					int index = 0;
					Task task;
					if (draggedIndex < indexOfThis) {
						//Dragged down - set priority to priority of task above
						index = this.getIndex();//Firgure out where this ends up --> Whether it's still at draggedIndex or at this.getIndex
						int priorIndex = index - 1;
						task = tasks.get(index);
						task.setStatus(tasks.get(priorIndex).getStatus());
					} else {
						//Dragged up - set priority to priority of task below
						index = this.getIndex();
						int afterIndex = index + 1;
						task=tasks.get(index);
						task.setStatus(tasks.get(afterIndex).getStatus());
					}
					
					this.setText(tasks.get(index).getName());
					System.out.println("THIS CELL'S (current) Task: " + tasks.get(index) +"\nIndex: " + this.getIndex());
					//System.out.println("Buggy Index?: " + index);
					/*
					 *Bug: Top task urgent, bottom task inactive and set to elevate to urgent
					 *Drag top below bottom. Double click on the new bottom task (the old top task)
					 */
					
					
					//System.out.println("Drop should be successful");
					/*for (String text : items) {
						//System.out.println("item: " + text);
					}*/
					try {
						if (task != null) {
							switch (task.getStatus()) {
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
			
			//controller.loadTaskNames();
			event.setDropCompleted(success);
			controller.loadTaskNames();
			event.consume();
		});
	}

	//********************OVERRIDES**************************

	@Override
	protected void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);

		if (item != null) {
			//this.setItem(item);
			this.setText(item);

		}
		try {
			String id = controller.taskIDs.get(this.getIndex());
			Task myTask = Main.todoList.getTaskByID(id);
			if (myTask != null) {
				switch (myTask.getStatus()) {
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
		catch (Exception e) {
		}
		
		
	}
}
