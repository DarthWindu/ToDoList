package frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
//import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import backend.*;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HistoryWindow implements MouseListener{
	private int clickCounter = 0;
	JFrame frame;
	JPanel panel,holder;
	JScrollPane scrollPane;
	JLabel changeType,changeText;
	Task t;
	long firstClick = 0;
	boolean commented = false;
	boolean in,out;

	HistoryWindow(Task task, Scene scene) {
		ArrayList<HistoryItem> history = task.getHistoryItems();
		String text = "";
		t = task;

		/*frame = new JFrame("History Window");
		frame.setSize(new Dimension(1600,850));
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);*/

		panel = new JPanel();
		panel.setSize(new Dimension(1600,850));
		panel.setLayout(new GridLayout(0,1,0,20));
		panel.addMouseListener(this);

		scrollPane = new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		Stage stage = new Stage();
		ScrollPane pane = new ScrollPane();
		SwingNode swingN = new SwingNode();


		//frame.add(scrollPane);
		//frame.setVisible(true);

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
					@Override
					public void mouseEntered(MouseEvent e) {
						in = true;
						out = false;
					}
					@Override
					public void mouseExited(MouseEvent e) {
						in = false;
						out = true;
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						/*JPanel a = (JPanel)e.getSource();
						JLabel b = (JLabel)a.getComponent(1);
						String withdraw = b.getText();
						for(HistoryItem hist: history) {
							try {
								Comment comm = (Comment)hist;
								if(comm.getComment().equals(withdraw))
									System.out.println("alsjdlkajsd");
									new FXCommentWindow(task, stage);
									System.out.println("Something happened");
							} catch(Exception ex){}
						}*/
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
							//System.out.println(withdraw);
							for(HistoryItem hist: history) {
								try {
									Comment comm = (Comment)hist;
									if(comm.getComment().equals(withdraw)) {
										clickCounter++;
										if ((clickCounter % 2) != 0) {
											//Ensures window is only opened once
											System.out.println("alsjdlkajsd");
											//Must run on new thread
											Platform.runLater(new Runnable() {
												@Override
												public void run() {
													new FXCommentWindow(task,comm, stage);
												}
											});
										}
									}


								} catch(Exception ex){System.out.println(ex);}
							}
						}
					}
					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
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

		createSwingContent(swingN);
		pane.setContent(swingN);
		stage.setScene(new Scene(pane, 1600, 850));
		stage.setTitle("History window");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(
				(scene.getWindow()));
		//stage.show();
		stage.showAndWait();
	}

	private void createSwingContent(final SwingNode swingNode) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				swingNode.setContent(scrollPane);
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}

	/*public void makeWindow() {
		frame.dispose();
		HistoryWindow history = new HistoryWindow(t);
	}*/
}