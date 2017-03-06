package frontend;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class CommentWindow {
   private JFrame mainFrame;
   private JLabel statusLabel;
   private JPanel controlPanel;

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
      JTextField userText = new JTextField(6); 
      userText.setPreferredSize( new Dimension(200,24));

      JButton commit = new JButton("Commit Comment");
      JButton delete = new JButton("Delete Comment");
      controlPanel.add(namelabel);
      controlPanel.add(userText);
      controlPanel.add(commit);
      controlPanel.add(delete);
      mainFrame.setVisible(true);  
   }
	}

