package frontend;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.*;

import backend.*;

public class HistoryWindow extends JPanel{
	JScrollPane scrollPane;
	HistoryWindow(Task task) {
		ArrayList<HistoryItem> history = task.getHistoryItems();
		for(HistoryItem his : history){
			Date day = his.getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy | hh:mm aa");
			String text = sdf.format(day);
			text = text + " | " + his.getText();
			String addText;
			try{
				NameChange name = (NameChange)his;
			}catch(Exception e){
				
			}
			JLabel item = new JLabel(his.getText());
			scrollPane.add(item);
		}
	}
}

