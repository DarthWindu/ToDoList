package frontend;
import backend.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EditActionItem extends JPanel implements ActionListener{
	JRadioButton radioButton, radioButton2, radioButton3, radioButton4;
	JCheckBox checkCurrent,checkUrgent,checkEventual;
	JButton commentButton;
	JButton historyButton;
	JButton print;

	EditActionItem(Task task)
	{
		ButtonGroup group = new ButtonGroup();
		
		radioButton = new JRadioButton("Urgent");
		radioButton2 = new JRadioButton("Current");
		radioButton3 = new JRadioButton("Eventual");
		radioButton4 = new JRadioButton("Inactive");

		group.add(radioButton);
		group.add(radioButton2);
		group.add(radioButton3);
		group.add(radioButton4);

		this.add(radioButton);
		this.add(radioButton2);
		this.add(radioButton3);
		this.add(radioButton4);
	
		
	}

	public void actionPerformed(ActionEvent e)
	{
		
	}

	public static void main(String [] args)
	{
		JFrame frame = new JFrame("This");
		Task e = new Task("NameOfTask");
		EditActionItem x = new EditActionItem(e);
		frame.setContentPane(x);
		frame.setVisible(true);
		frame.pack();
	}
}
