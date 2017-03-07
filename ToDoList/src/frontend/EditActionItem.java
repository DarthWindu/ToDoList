package frontend;
import backend.*;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.*;

public class EditActionItem extends JPanel implements ActionListener, Printable{
	JRadioButton radioButton, radioButton2, radioButton3, radioButton4;
	JCheckBox checkCurrent,checkUrgent,checkEventual;
	JButton commentButton;
	JButton historyButton;
	JButton print;
	Task paramTask;

	EditActionItem(Task task)
	{

		ButtonGroup group = new ButtonGroup();

		this.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		radioButton = new JRadioButton("Urgent");
		radioButton2 = new JRadioButton("Current");
		radioButton3 = new JRadioButton("Eventual");
		radioButton4 = new JRadioButton("Inactive");
		checkCurrent = new JCheckBox("Elevates to Current on: ");
		checkUrgent = new JCheckBox("Elevates to Urgent on: ");
		checkEventual = new JCheckBox("Elevates to Eventual on: ");
		commentButton = new JButton("Comment");
		historyButton = new JButton("History");
		print = new JButton("Print");

		historyButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
					
			}
		});

		commentButton.addActionListener(new ActionListener ()
		{
			public void actionPerformed(ActionEvent arg1)
			{

			}
		});

		print.addActionListener(new ActionListener ()
		{
			public void actionPerformed(ActionEvent arg2)
			{
				Printer pr = new Printer();
			}

		});


		checkCurrent.addActionListener(this);
		checkUrgent.addActionListener(this);
		checkEventual.addActionListener(this);

		group.add(radioButton);
		group.add(radioButton2);
		group.add(radioButton3);
		group.add(radioButton4);

		this.add(radioButton);
		this.add(radioButton2);
		this.add(radioButton3);
		this.add(radioButton4);
		this.add(checkCurrent);
		this.add(checkUrgent);
		this.add(checkEventual);
		this.add(commentButton);
		this.add(historyButton);
		this.add(print);


	}

	//Note that over here, would you be changing the task and then sending it back?
	//Where would the task be going from here if you were to change something?
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

	@Override
	public int print(Graphics arg0, PageFormat arg1, int arg2) throws PrinterException {
		// TODO Auto-generated method stub
		return 0;
	}
}
