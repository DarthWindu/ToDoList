package frontend;

import backend.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.*;

public class EditActionItem extends JPanel implements ActionListener, Printable {
	JRadioButton radioButton, radioButton2, radioButton3, radioButton4;
	JCheckBox checkCurrent, checkUrgent, checkEventual;
	JButton commentButton;
	JButton historyButton;
	JButton print;
	JTextField name;

	EditActionItem(Task task)
	{

		ButtonGroup group = new ButtonGroup();

		//	this.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		//	this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));


		this.setLayout(new GridLayout(0,2));
		this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

		name = new JTextField(task.getName());
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
		BufferedImage im = createImage(this);
		

		name.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){
				name.setText("");
			}

			public void focusLost(FocusEvent e)
			{
				task.changeName(name.getText());
			}
		});

		name.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "enter");
		name.getActionMap().put("enter", new AbstractAction(){
			public void actionPerformed(ActionEvent arg3)
			{
				task.changeName(name.getText());
			}
		});

		historyButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				HistoryWindow hist = new HistoryWindow(task);
			}
		});

		commentButton.addActionListener(new ActionListener ()
		{
			public void actionPerformed(ActionEvent arg1)
			{
				CommentWindow cw = new CommentWindow(task);
			}
		});

		print.addActionListener(new ActionListener ()
		{
			public void actionPerformed( ActionEvent ae ) {
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable( new Printable()
				{
					@Override
					public int print(Graphics pg, PageFormat pf, int pageNum)
					{
						if(pageNum != 0)
							return Printable.NO_SUCH_PAGE;

						Graphics2D g2 = (Graphics2D)pg;
						g2.translate(pf.getImageableX(), pf.getImageableY());
						pg.drawImage(im, 0, 0, im.getWidth(), im.getHeight(), null);
						/* I've tried the following commented codes but they don't work */
						//MainAppPanel.this.printAll(g2);
						//MainAppPanel.this.print(g2);
						//MainAppPanel.this.print(g2);
						return Printable.PAGE_EXISTS;
					}
				});

				boolean didYouPrint = job.printDialog();
				if(didYouPrint) {
					try {
						job.print();
					} catch( PrinterException exc) {
						System.out.println( exc );
					}
				} else 
					System.out.println("You cancelled the print");
				
			}

		});


		checkCurrent.addActionListener(this);
		checkUrgent.addActionListener(this);
		checkEventual.addActionListener(this);

		group.add(radioButton);
		group.add(radioButton2);
		group.add(radioButton3);
		group.add(radioButton4);

		this.add(name);
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

	// Note that over here, would you be changing the task and then sending it
	// back?
	// Where would the task be going from here if you were to change something?
	public void actionPerformed(ActionEvent e) {

	}

	public BufferedImage createImage(EditActionItem panel) {
		panel.setSize(1280,720);
	    BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = bi.createGraphics();
	    panel.paint(g);
	    return bi;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("This");
		Task e = new Task("NameOfTask");

		EditActionItem x = new EditActionItem(e);
		frame.setContentPane(x);
		frame.setVisible(true);
		frame.pack();
	}

	public int print( Graphics g , PageFormat pf , int pageIndex) throws PrinterException{
		return pageIndex;

	}
}
