package frontend;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import backend.*;

public class HistoryWindow implements MouseListener{
	JFrame frame;
	JPanel panel,holder;
	JScrollPane scrollPane;
	JLabel changeType,changeText;
	long firstClick = 0;
	boolean commented = false;
	boolean in,out;

	HistoryWindow(Task task) {
		ArrayList<HistoryItem> history = task.getHistoryItems();
		String text = "";

		frame = new JFrame("History Window");
		frame.setSize(new Dimension(1600,850));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setSize(new Dimension(1600,850));
		panel.setLayout(new GridLayout(0,1,0,20));
		panel.addMouseListener(this);

		scrollPane = new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		frame.add(scrollPane);
		frame.setVisible(true);

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
				commented = true;
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

			changeType = new JLabel(text);
			changeText = new JLabel(addText);

			holder = new JPanel();
			if(commented) {
				holder.addMouseListener(new MouseListener() {
					public void mouseEntered(MouseEvent e) {
						in = true;
						out = false;
					}

					public void mouseExited(MouseEvent e) {
						in = false;
						out = true;
					}
					public void mouseClicked(MouseEvent e) {
						if(firstClick == 0 || System.currentTimeMillis()/100 - firstClick > 4 && in) {
							firstClick = System.currentTimeMillis()/100;
							//System.out.println("First click: "+firstClick);
						}
						else if(System.currentTimeMillis()/100 - firstClick <= 4 && in) {
							//System.out.println("First try?");
							firstClick = 0;
							JPanel a = (JPanel)e.getSource();
							JLabel b = (JLabel)a.getComponent(1);
							String withdraw = b.getText();
							System.out.println(withdraw);
							for(HistoryItem hist: history) {
								try {
									Comment comm = (Comment)hist;
									if(comm.getComment().equals(withdraw))
										new CommentWindow(task,comm);
								} catch(Exception ex){}
							}
						}
					}
					public void mousePressed(MouseEvent e) {}
					public void mouseReleased(MouseEvent e) {}
				});
				commented = false;
			}
			holder.setLayout(new GridLayout(0,1,0,10));
			holder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			holder.setMinimumSize(new Dimension(10,100));

			holder.add(changeType);
			holder.add(changeText);
			panel.add(holder);

		}
		scrollPane.revalidate();
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

}