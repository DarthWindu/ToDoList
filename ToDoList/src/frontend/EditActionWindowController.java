package frontend;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.DateCell;

import backend.Task;
import main.Main;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.util.Callback;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Window;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.application.Platform;

public class EditActionWindowController {
	private Task task = null;
	private int eventHiddenCounter = 0;
	private boolean proceed = false;
	private boolean extRequest = false;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private JFXTextField txtfName;

	@FXML
	private JFXRadioButton radUrgent;

	@FXML
	private ToggleGroup priorityGroup;

	@FXML
	private JFXRadioButton radCurrent;

	@FXML
	private JFXRadioButton radEventual;

	@FXML
	private JFXRadioButton radInactive;

	@FXML
	private JFXCheckBox chkbUrgent;

	@FXML
	private JFXCheckBox chkbCurrent;

	@FXML
	private JFXDatePicker currentDatePicker;

	@FXML
	private JFXDatePicker urgentDatePicker;

	@FXML
	private JFXCheckBox chkbEventual;

	@FXML
	private JFXDatePicker eventualDatePicker;

	@FXML
	private JFXButton btnComment;

	@FXML
	private JFXButton btnHistory;

	@FXML
	private JFXButton btnPrint;

	@FXML
	private JFXButton btnClose;

	@FXML
	private JFXTextField txtfMessage;

	@FXML
	void btnCommentAction(ActionEvent event) {
		new FXCommentWindow(task,EditActionWindow.editActionStage);
	}

	@FXML
	void btnHistoryOnAction(ActionEvent event) {
		/*SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new HistoryWindow(task, EditActionWindow.editActionStage);
			}
		});*/
		/*Platform.runLater(new Runnable() {
			@Override
			public void run() {*/
		new FXHistoryWindow(task, EditActionWindow.editActionStage.getScene());
		/*}
		});*/

	}

	@FXML
	void btnPrintOnAction(ActionEvent event) {
		//Figure out what we want program to print -
	}

	@FXML
	void chkbCurrentAction(ActionEvent event) {
		if (currentDatePicker.getValue() != null && chkbCurrent.isSelected()) {
			task.setCurrentElevDate(currentDatePicker.getValue());
		} else {
			task.setCurrentElevDate(null);
			task.setShowDate(false);
		}
	}

	@FXML
	void chkbEventualAction(ActionEvent event) {
		if (eventualDatePicker.getValue() != null && chkbEventual.isSelected()) {
			task.setEventualElevDate(eventualDatePicker.getValue());
		} else {
			task.setEventualElevDate(null);
			task.setShowDate(false);
		}
	}

	@FXML
	void chkbUrgentAction(ActionEvent event) {
		if (urgentDatePicker.getValue() != null && chkbUrgent.isSelected()) {
			task.setUrgentElevDate(urgentDatePicker.getValue());
		} else {
			task.setUrgentElevDate(null);
			task.setShowDate(false);
		}
	}

	@FXML
	void currentDatePickerOnHidden(Event event) {
		if (currentDatePicker.getValue() != null && chkbCurrent.isSelected()) {
			task.setCurrentElevDate(currentDatePicker.getValue());
		}
	}

	@FXML
	void currentDatePickerOnHiding(Event event) {
		if (currentDatePicker.getValue() != null) {
			datePickerOnHiding(currentDatePicker, chkbCurrent);
		}
	}

	@FXML
	void eventualDatePickerOnHidden(Event event) {
		if (eventualDatePicker.getValue() != null && chkbEventual.isSelected()) {
			task.setEventualElevDate(eventualDatePicker.getValue());
		}
	}

	@FXML
	void eventualDatePickerOnHiding(Event event) {
		if (eventualDatePicker.getValue() != null) {
			datePickerOnHiding(eventualDatePicker, chkbEventual);
		}
	}

	@FXML
	void radCurrentAction(ActionEvent event) {
		setStatus(Task.CURRENT);
	}

	@FXML
	void radEventualAction(ActionEvent event) {
		setStatus(Task.EVENTUAL);
	}

	@FXML
	void radInactiveAction(ActionEvent event) {
		setStatus(Task.INACTIVE);
	}

	@FXML
	void radUrgentAction(ActionEvent event) {
		setStatus(Task.URGENT);
	}

	@FXML
	void urgentDatePickerOnHidden(Event event) {
		//System.out.println(urgentDatePicker.getValue().toString());
		if (eventHiddenCounter%2 == 0) {
			if (urgentDatePicker.getValue() != null && chkbUrgent.isSelected()) {
				task.setUrgentElevDate(urgentDatePicker.getValue());
			}
		}

	}

	@FXML
	void urgentDatePickerOnHiding(Event event) {
		if (urgentDatePicker.getValue() != null) {
			datePickerOnHiding(urgentDatePicker, chkbUrgent);
		}
	}

	@FXML
	void initialize() {
		assert txtfName != null : "fx:id=\"txtfName\" was not injected: check your FXML file 'editActionWindow.fxml'.";
		assert radUrgent != null : "fx:id=\"radUrgent\" was not injected: check your FXML file 'editActionWindow.fxml'.";
		assert priorityGroup != null : "fx:id=\"priorityGroup\" was not injected: check your FXML file 'editActionWindow.fxml'.";
		assert radCurrent != null : "fx:id=\"radCurrent\" was not injected: check your FXML file 'editActionWindow.fxml'.";
		assert radEventual != null : "fx:id=\"radEventual\" was not injected: check your FXML file 'editActionWindow.fxml'.";
		assert radInactive != null : "fx:id=\"radInactive\" was not injected: check your FXML file 'editActionWindow.fxml'.";
		assert chkbUrgent != null : "fx:id=\"chkbUrgent\" was not injected: check your FXML file 'editActionWindow.fxml'.";
		assert chkbCurrent != null : "fx:id=\"chkbCurrent\" was not injected: check your FXML file 'editActionWindow.fxml'.";
		assert currentDatePicker != null : "fx:id=\"currentDatePicker\" was not injected: check your FXML file 'editActionWindow.fxml'.";
		assert urgentDatePicker != null : "fx:id=\"urgentDatePicker\" was not injected: check your FXML file 'editActionWindow.fxml'.";
		assert chkbEventual != null : "fx:id=\"chkbEventual\" was not injected: check your FXML file 'editActionWindow.fxml'.";
		assert eventualDatePicker != null : "fx:id=\"eventualDatePicker\" was not injected: check your FXML file 'editActionWindow.fxml'.";
		assert btnComment != null : "fx:id=\"btnComment\" was not injected: check your FXML file 'editActionWindow.fxml'.";
		assert btnHistory != null : "fx:id=\"btnHistory\" was not injected: check your FXML file 'editActionWindow.fxml'.";
		assert btnPrint != null : "fx:id=\"btnPrint\" was not injected: check your FXML file 'editActionWindow.fxml'.";
		assert btnClose != null : "fx:id=\"btnClose\" was not injected: check your FXML file 'editActionWindow.fxml'.";
		
		

		initTaskInfo();
		setNameFocusListener();
		setDisabledDays();
		
	}

	private void datePickerOnHiding(JFXDatePicker datePicker, JFXCheckBox chkBox) {	
			int counter = 0;
			boolean entered = false;
			
			if (datePicker.getValue().isBefore(LocalDate.now()) ||datePicker.getValue().isEqual(LocalDate.now())) {
				if ((eventHiddenCounter % 2) == 0) {
					/*System.out.println("HAHA");//Testing
					txtfMessage.setVisible(true);
					txtfMessage.setText("Please select a future date.");
					Alert alert = new Alert(AlertType.WARNING, "Please select a future date.");
					//alert.showAndWait();
					datePicker.setValue(null);
					
					counter ++;
					entered = true;*/
				}
			} else {
				txtfMessage.setVisible(false);
				chkBox.setSelected(true);
			}
			
			//Testing
			/*System.out.println("" + eventHiddenCounter + ": " + ((eventHiddenCounter % 2) == 0));
			System.out.println("COUNTER: " + counter + "\nENTERED: " + entered);
			entered = false;
			eventHiddenCounter ++;*/
	}

	private void setStatus(int newStatus) {
		if (task.getStatus() == Task.COMPLETED) {
			System.out.println("Get From Comp List: " + Main.todoList.getCompletedTask(task.getName()));
			System.out.println(Main.todoList.setCompletedTaskActive(task));

		} 
		task.setStatus(newStatus);
	}


	private void initTaskInfo() {
		task = EditActionWindow.taskBeingEdited;
		if (task == null) {
			System.out.println("null");
		}
		txtfName.setText(task.getName());

		switch(task.getStatus()) {
		case Task.URGENT: radUrgent.setSelected(true);
		break;

		case Task.CURRENT: radCurrent.setSelected(true);
		break;

		case Task.EVENTUAL: radEventual.setSelected(true);
		break;

		case Task.INACTIVE: radInactive.setSelected(true);
		break;
		}

		if (task.getUrgentElevDate() != null) {
			chkbUrgent.setSelected(true);
			urgentDatePicker.setValue(task.getUrgentElevDate());
		}

		if (task.getCurrentElevDate() != null) {
			chkbCurrent.setSelected(true);
			currentDatePicker.setValue(task.getCurrentElevDate());
		}

		if (task.getEventualElevDate() != null) {
			chkbEventual.setSelected(true);
			eventualDatePicker.setValue(task.getEventualElevDate());
		}

	}

	private void setNameFocusListener() {
		txtfName.focusedProperty().addListener(new ChangeListener<Boolean> ()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean inFocus)
			{
				if (inFocus)
				{
					//System.out.println("Textfield on focus");
				}
				else
				{
					if (!extRequest) {
						String newName = txtfName.getText().trim();
						if (!newName.equals("") && !newName.equals(task.getName())) {
							if (Main.todoList.getTask(newName) == null) {
								if (Main.todoList.getCompletedTask(newName) == null) {
									task.changeName(txtfName.getText().trim());
								} else {
									Alert alert = new Alert(AlertType.INFORMATION, "This task has already been completed!");
									alert.showAndWait();
								}
							} else {
								Alert alert = new Alert(AlertType.INFORMATION, "This task already exists!");
								alert.showAndWait();
							}

						}
					}
					//System.out.println("Textfield out focus");
					
				}
			}
		});
		
	}
	
	private void setDisabledDays() {
		final Callback<DatePicker, DateCell> dayCellFactory = 
	            new Callback<DatePicker, DateCell>() {
	                @Override
	                public DateCell call(final DatePicker datePicker) {
	                    return new DateCell() {
	                        @Override
	                        public void updateItem(LocalDate item, boolean empty) {
	                            super.updateItem(item, empty);
	                           
	                            if (item.isBefore(
	                            		LocalDate.now().plusDays(1))
	                            		
	                                ) {
	                                    setDisable(true);
	                                    setStyle("-fx-background-color: #ffc0cb;");
	                            }   
	                    }
	                };
	            }
	        };
	        
	        urgentDatePicker.setDayCellFactory(dayCellFactory);
	        currentDatePicker.setDayCellFactory(dayCellFactory);
	        eventualDatePicker.setDayCellFactory(dayCellFactory);
	}
	
	
	/**
	 * Does more than simply get text from the Name textfield
	 * 
	 * It changes the task name if the task name is valid, and it sets error flags if not. 
	 * To fire any errors, call fireError().
	 * @return text from the Name TextField
	 */
	public String getNameField() {
		String newName = txtfName.getText().trim();
		
		if (!newName.equals("") && !newName.equals(task.getName())) {
			if (Main.todoList.getTask(newName) == null) {
				if (Main.todoList.getCompletedTask(newName) == null) {
					task.changeName(txtfName.getText().trim());
					proceed = true;
				} else {
					proceed = false;
				}
			} else {
				proceed = false;
			}

		} else {
			proceed = true;
		}
		
		return newName;
	}
	
	public boolean proceed() {
		return proceed;
	}
	
	public boolean fireError() {
		if (!proceed) {
			txtfMessage.requestFocus();
			Alert al = new Alert(AlertType.ERROR, "This task already exists!");
			al.showAndWait();
			return true;
		} else {
			return false;
		}
		
	}
	
	public void setExternalRequest(boolean val) {
		extRequest = val;
	}
}
