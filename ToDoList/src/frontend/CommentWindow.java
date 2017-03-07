package frontend;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import backend.Comment;
import backend.HistoryItem;
 
public class CommentWindow {
   private JFrame mainFrame;
   private JLabel statusLabel;
   private JPanel controlPanel;
   private JTextField userText;
   
   public CommentWindow(){
	  prepareGUI();
   }
   public static void main(String[] args){
      CommentWindow swingControlDemo = new CommentWindow();      
   }
   private void prepareGUI(){
      mainFrame = new JFrame("Comment Window");
      mainFrame.setSize(500,200);
      mainFrame.setLayout(new GridLayout(0,3));      


      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(controlPanel);
      mainFrame.setVisible(true);  
      JLabel  namelabel= new JLabel("Comment", JLabel.RIGHT);
      userText = new JTextField(6); 
      userText.setPreferredSize( new Dimension(200,24));

      JButton commit = new JButton("Commit Comment");
      commit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
    		  String comment = event.getActionCommand();
    		  if(comment.equals("Commit Comment")){
    			  String g1 = userText.getText();
    			  
    			  
    		  }
		}
      });
      JButton delete = new JButton("Delete Comment");
      controlPanel.add(namelabel);
      controlPanel.add(userText);
      controlPanel.add(commit);
      controlPanel.add(delete);
      mainFrame.setVisible(true);  
   }
	}
