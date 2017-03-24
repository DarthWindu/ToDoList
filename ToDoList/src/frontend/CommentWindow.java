package frontend;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import backend.*;

public class CommentWindow {
	private JFrame mainFrame;
	//private JLabel statusLabel;
	private JPanel controlPanel;
	private JButton commit,delete;
	private JTextField userText;
	private JScrollPane scrollPane;
	private Task userTask = null;
	private Comment userComment = null;
	private HistoryWindow history;

	public CommentWindow(Task task){
		prepareGUI_ONLYtask();
		userTask = task;
	}

	public CommentWindow(Task task, Comment commentToEdit, HistoryWindow hist){
		history = hist;
		userComment = commentToEdit;
		prepareGUI();
		userTask = task;
	}
	private void prepareGUI(){
		mainFrame = new JFrame("Comment Window");
		mainFrame.setSize(600,300);
		mainFrame.setLayout(new GridLayout(0,1));
		mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				history.makeWindow();
			}
		});

		controlPanel = new JPanel();
		controlPanel.setSize(600,300);
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		controlPanel.setLayout(gridbag);
		c.fill = GridBagConstraints.HORIZONTAL;

//		scrollPane = new JScrollPane(controlPanel);
//		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		mainFrame.setVisible(true);
		mainFrame.add(controlPanel);
		//mainFrame.add(scrollPane);

		JLabel  nameLabel = new JLabel("Comment", JLabel.RIGHT);
		c.insets = new Insets(10,0,0,20);
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		gridbag.setConstraints(nameLabel, c);
		controlPanel.add(nameLabel);

		userText = new JTextField(25);
		if(userComment != null)
			userText.setText(userComment.getComment());
		c.insets = new Insets(10,0,0,0);
		c.gridx = 1;
		c.gridy = 0;
		gridbag.setConstraints(userText, c);
		controlPanel.add(userText);

		commit = new JButton("Commit Comment");
		commit.setPreferredSize(new Dimension(100,30));
		commit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				String commandName = event.getActionCommand();
				if(commandName.equals("Commit Comment")){
					String g1 = userText.getText();
					userTask.changeComment(g1, userComment);
					mainFrame.dispose();
				}
			}
		});
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.SOUTH;//bottom of space
		c.insets = new Insets(10,0,20,20); //top padding
		c.gridx = 2;       					//aligned with button 2
		c.gridy = 2;       					//third row
		gridbag.setConstraints(commit, c);
		controlPanel.add(commit);

		delete = new JButton("Delete Comment");
		delete.setPreferredSize(new Dimension(100,30));
		delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				String commandName = event.getActionCommand();
				if(commandName.equals("Delete Comment")){
					String g1 = userText.getText();
					userTask.deleteComment(userComment);
					mainFrame.dispose();
				}
			}
		});
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.SOUTH;//bottom of space
		c.insets = new Insets(10,0,20,20);  //top padding
		c.gridx = 1;       					//aligned with button 2
		c.gridy = 2;  						//third row
		gridbag.setConstraints(delete,c);
		controlPanel.add(delete);
	}

	private void prepareGUI_ONLYtask(){
		mainFrame = new JFrame("Comment Window");
		mainFrame.setSize(900,800);
		mainFrame.setLayout(new GridLayout(0,1));
		mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		controlPanel = new JPanel();
		controlPanel.setSize(900,1000);
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		controlPanel.setLayout(gridbag);
		c.fill = GridBagConstraints.HORIZONTAL;

		scrollPane = new JScrollPane(controlPanel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		mainFrame.setVisible(true);
		mainFrame.add(scrollPane);

		JLabel  nameLabel = new JLabel("Comment", JLabel.RIGHT);
		c.insets = new Insets(10,0,0,20);
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		gridbag.setConstraints(nameLabel, c);
		controlPanel.add(nameLabel);

		userText = new JTextField(25);
		if(userComment != null)
			userText.setText(userComment.getComment());
		c.insets = new Insets(10,0,0,0);
		c.gridx = 1;
		c.gridy = 0;
		gridbag.setConstraints(userText, c);
		controlPanel.add(userText);

		commit = new JButton("Commit Comment");
		commit.setPreferredSize(new Dimension(100,30));
		commit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				String commandName = event.getActionCommand();
				if(commandName.equals("Commit Comment")){
					String g1 = userText.getText();
					userTask.addComment(g1);
					mainFrame.dispose();
				}
			}
		});
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.SOUTH;//bottom of space
		c.insets = new Insets(10,20,20,20); //top padding
		c.gridx = 2;       					//aligned with button 2
		c.gridy = 2;       					//third row
		gridbag.setConstraints(commit, c);
		controlPanel.add(commit);

		delete = new JButton("Delete Comment");
		delete.setPreferredSize(new Dimension(100,30));
		delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				String commandName = event.getActionCommand();
				if(commandName.equals("Delete Comment")){
					String g1 = userText.getText();
					mainFrame.dispose();
				}
			}
		});
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.SOUTH;//bottom of space
		c.insets = new Insets(10,0,20,20);  //top padding
		c.gridx = 1;       					//aligned with button 2
		c.gridy = 2;  						//third row
		gridbag.setConstraints(delete,c);
		controlPanel.add(delete);
	}
}