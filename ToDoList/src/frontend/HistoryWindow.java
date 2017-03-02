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
				if(pri.getOldStatus() == 0){
					priOld = "inactive";
				}else if(pri.getOldStatus() == 1){
					priOld = "eventual";
				}else if(pri.getOldStatus() == 2){
					priOld = "current";
				}else if(pri.getOldStatus() == 3){
					priOld = "urgent";
				}else if(pri.getOldStatus() == 4){
					priOld = "completed";
				}
				
				if(pri.getNewStatus() == 0){
					priNew = "inactive";
				}else if(pri.getNewStatus() == 1){
					priNew = "eventual";
				}else if(pri.getNewStatus() == 2){
					priNew = "current";
				}else if(pri.getNewStatus() == 3){
					priNew = "urgent";
				}else if(pri.getNewStatus() == 4){
					priNew = "completed";
				}
				
				addText = "Changed priority from" + priOld + " to " + priNew;
			}catch(Exception e){
			}
			text = text + "\n" + addText;
		}
	}

}

