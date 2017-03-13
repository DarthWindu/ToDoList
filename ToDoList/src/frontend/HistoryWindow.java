package frontend;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

import backend.*;

//Kudos to whoever did this one, my only advice would be to shorten it down because all of 
//the code is in the constructor
public class HistoryWindow{
	JFrame frame;
	JPanel panel,holder;
	JScrollPane scrollPane;
	JLabel changeType,changeText;

	HistoryWindow(Task task) {
		ArrayList<HistoryItem> history = task.getHistoryItems();
		String text = "";

		frame = new JFrame("History Window");
		frame.setPreferredSize(new Dimension(500,500));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(500,500));
		panel.setLayout(new GridLayout(0,1,0,20));
		
		scrollPane = new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		frame.add(scrollPane);
		frame.pack();
		frame.setVisible(true);
		
		panel.revalidate();
		
		for(HistoryItem his : history){
			Date day = his.getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy | hh:mm aa");
			text = sdf.format(day);

			text = text + " | " + his.getText();
			String addText = "";

			try{
				NameChange name = (NameChange)his;
				addText = "Changed name from " + name.getOldName() + " to " + name.getNewName();
			}catch(Exception e){}

			try{
				Comment com = (Comment)his;
				addText = com.getComment();
			}catch(Exception e){}

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

				addText = "Changed priority from " + priOld + " to " + priNew;
			}catch(Exception e){}

			holder = new JPanel();
			holder.setLayout(new GridLayout(0,1));
			holder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			changeType = new JLabel(text);
			changeText = new JLabel(addText);
			
			holder.add(changeType);
			holder.add(changeText);
			panel.add(holder);
		}
	}

}