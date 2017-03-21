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
		mainFrame.setSize(900,400);
		mainFrame.setLayout(new GridLayout(0,2));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
		JLabel  namelabel= new JLabel("Comment", JLabel.RIGHT);
		userText = new JTextField(50);
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
