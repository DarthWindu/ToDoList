package frontend;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import backend.*;

public class CommentWindow implements WindowListener{
	private JFrame mainFrame;
	//private JLabel statusLabel;
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
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		mainFrame.addWindowListener(new WindowAdapter() {

			public void windowClosed(WindowEvent e) {
				
			}
        });

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
		c.insets = new Insets(10,20,20,20);    //top padding
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
					userTask.deleteComment(new Comment(g1));
					mainFrame.dispose();
				}
			}
		});
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.SOUTH;//bottom of space
		c.insets = new Insets(10,0,20,20);    //top padding
		c.gridx = 1;       					//aligned with button 2
		c.gridy = 2;  
		gridbag.setConstraints(delete,c);
		controlPanel.add(delete);
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
	public void windowClosed(WindowEvent e) {
		
	}
	
	public void windowActivated(WindowEvent e){}
	public void windowClosing(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowOpened(WindowEvent e){}
}
