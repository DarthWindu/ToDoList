package frontend;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import backend.*;

public class CommentWindow {
	private JFrame mainFrame;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private JButton commit,delete;
	private JTextField userText;
	private JScrollPane scrollPane;
	private Task userTask = null;
	private Comment userComment = null;

	public CommentWindow(Task task){
		prepareGUI_ONLYtask();
		userTask = task;
	}
	
	public CommentWindow(Task task, Comment commentToEdit){
		userComment = commentToEdit;
		prepareGUI();
		userTask = task;
	}
	private void prepareGUI(){
		mainFrame = new JFrame("Comment Window");
		mainFrame.setSize(900,800);
		mainFrame.setLayout(new GridLayout(0,1));
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(3,6));
		//controlPanel.setLayout(new FlowLayout());
		JLabel label1 = new JLabel();
		JLabel label2;
		JLabel label3;
		JLabel label4;
		JLabel label5;
		JLabel label6;
		JLabel label7;
		JLabel label8;
		JLabel label9;
		JLabel label10;
		JLabel label11;
		JLabel label12;
		JLabel label13;
		JLabel label14;
		scrollPane = new JScrollPane(controlPanel);

		mainFrame.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainFrame.setVisible(true);
		JLabel  namelabel= new JLabel("Comment", JLabel.RIGHT);
		userText = new JTextField(25);
		if(userComment != null)
			userText.setText(userComment.getComment());

		commit = new JButton("Commit Comment");
		commit.setPreferredSize(new Dimension(200,30));
		commit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				String commandName = event.getActionCommand();
				if(commandName.equals("Commit Comment")){
					String g1 = userText.getText();
					userTask.addComment(g1);
				}
			}
		});
		delete = new JButton("Delete Comment");
		delete.setPreferredSize(new Dimension(200,30));
		delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				String commandName = event.getActionCommand();
				if(commandName.equals("Delete Comment")){
					String g1 = userText.getText();
					userTask.deleteComment(new Comment(g1));
				}
			}
		});
		controlPanel.add(namelabel);
		controlPanel.add(userText);
		controlPanel.add(label1);
		controlPanel.add(commit);
		controlPanel.add(delete);
		mainFrame.setVisible(true);
	}
	
	private void prepareGUI_ONLYtask(){
		mainFrame = new JFrame("Comment Window");
		mainFrame.setSize(900,400);
		mainFrame.setLayout(new GridLayout(0,4));      
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
		
		JLabel  namelabel= new JLabel("Comment", JLabel.RIGHT);
		userText = new JTextField(50); 
		
		JButton commit = new JButton("Commit Comment");
		commit.setPreferredSize(new Dimension(100,100));
		commit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				String commandName = event.getActionCommand();
				if(commandName.equals("Commit Comment")){
					String g1 = userText.getText();
					userTask.addComment(g1);
				}
			}
		});
		JButton delete = new JButton("Delete Comment");
		delete.setPreferredSize(new Dimension(100,100));
		delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				String commandName = event.getActionCommand();
				if(commandName.equals("Delete Comment")){
					String g1 = userText.getText();
					userTask.deleteComment(userComment);
				}
			}
		});
		controlPanel.add(namelabel);
		controlPanel.add(userText);
		controlPanel.add(commit);
		controlPanel.add(delete);
		mainFrame.setVisible(true);  
	}
}
