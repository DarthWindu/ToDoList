package frontend;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.*;

import javafx.stage.Stage;
import javafx.util.*;
import backend.*;

public class HistoryWindow{
	Stage frame = new Stage();
	HistoryWindow(Task task) {
		ArrayList<HistoryItem> history = task.getHistoryItems();
		for(HistoryItem his : history){
			Date day = his.getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy | hh:mm aa");
			String text = sdf.format(day);
			text = text + " | " + his.getText();
			String addText = "";
			try{
				NameChange name = (NameChange)his;
				addText = "Changed name from " + name.getOldName() + " to " + name.getNewName();
			}catch(Exception e){
			}
			
			try{
				Comment com = (Comment)his;
				addText = com.getComment();
			}catch(Exception e){
			}
			
			try{
				PriorityChange pri = (PriorityChange)his;
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
				
				addText = "Changed priority from" + priOld + " to " + priNew;
				
			}catch(Exception e){
			}
			
			text = text + "\n" + addText;
		}
	}

}

