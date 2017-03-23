package frontend;

import backend.*;


import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	JTextField CurrentMonth, CurrentDay, CurrentYear, 
	UrgentMonth, UrgentDay, UrgentYear, EventualMonth, EventualDay, EventualYear;
	Task tasker;
	ButtonGroup groupButton;
	public EditActionItem(Task task)
	{
		tasker = task;
		groupButton = new ButtonGroup();


		//this.setLayout(new GridLayout(0,2));
		//	this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

		name = new JTextField(tasker.getName());
		CurrentMonth = new JTextField("MM");
		CurrentDay = new JTextField("DD");
		CurrentYear = new JTextField("YY");
		UrgentMonth = new JTextField("MM");
		UrgentDay = new JTextField("DD");
		UrgentYear = new JTextField("YY");
		EventualMonth = new JTextField("MM");
		EventualDay = new JTextField("DD");
		EventualYear = new JTextField("YY");

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

		final BufferedImage im = createImage(this);

		name.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){
				//name.setText(""); <--what were you thinking?
			}

			public void focusLost(FocusEvent e)
			{
				tasker.changeName(name.getText());
			}
		});
		
		

		CurrentMonth.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e)
			{
				CurrentMonth.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
		
		});
		CurrentDay.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e)
			{
				CurrentDay.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
		
		});
		CurrentYear.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e)
			{
				CurrentYear.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
		
		});
		UrgentMonth.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e)
			{
				UrgentMonth.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
		
		});
		UrgentDay.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e)
			{
				UrgentDay.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
		
		});
		UrgentYear.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e)
			{
				UrgentYear.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
		
		});
		EventualMonth.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e)
			{
				EventualMonth.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
		
		});
		EventualDay.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e)
			{
				EventualDay.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
		
		});
		EventualYear.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e)
			{
				EventualYear.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
		
		});
		
		
		
		

		name.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "enter");
		name.getActionMap().put("enter", new AbstractAction(){
			public void actionPerformed(ActionEvent arg3)
			{
				tasker.changeName(name.getText());

			}
		});
		historyButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				HistoryWindow hist = new HistoryWindow(tasker);
			}
		});
		commentButton.addActionListener(new ActionListener ()
		{
			public void actionPerformed(ActionEvent arg1)
			{
				CommentWindow cw = new CommentWindow(tasker);
			}
		});


		/////////////////////////////////////////////////////
		//////////////////////////////////////////////////////
		/////////////////////////////////////////////////////
		print.addActionListener(new ActionListener ()
		{
			public void actionPerformed( ActionEvent ae ) {
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable( new Printable()
				{
					public int print(Graphics pg, PageFormat pf, int pageNum)
					{
						if(pageNum != 0)
							return Printable.NO_SUCH_PAGE;

						Graphics2D g2 = (Graphics2D)pg;
						g2.translate(pf.getImageableX(), pf.getImageableY());
						pg.drawImage(im, 0, 0, im.getWidth(), im.getHeight(), null);
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

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(name)
						.addGroup(layout.createParallelGroup()
								.addComponent(radioButton)
								.addComponent(radioButton2)
								.addComponent(radioButton3)
								.addComponent(radioButton4))

						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup()
										.addComponent(checkUrgent)
										.addComponent(checkCurrent)
										.addComponent(checkEventual))
								.addGroup(layout.createParallelGroup()
										.addComponent(UrgentMonth)
										.addComponent(CurrentMonth)
										.addComponent(EventualMonth))
								.addGroup(layout.createParallelGroup()
										.addComponent(UrgentDay)
										.addComponent(CurrentDay)
										.addComponent(EventualDay))
								.addGroup(layout.createParallelGroup()
										.addComponent(UrgentYear)
										.addComponent(CurrentYear)
										.addComponent(EventualYear))
								.addGroup(layout.createParallelGroup()
										.addComponent(print)
										.addComponent(commentButton)
										.addComponent(historyButton)))));


		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(name)

				.addGroup(layout.createParallelGroup(LEADING)
						.addComponent(radioButton))
				.addGroup(layout.createParallelGroup(LEADING)
						.addComponent(radioButton2))
				.addGroup(layout.createParallelGroup(LEADING)
						.addComponent(radioButton3))
				.addGroup(layout.createParallelGroup(LEADING)
						.addComponent(radioButton4))
				.addGroup(layout.createParallelGroup(LEADING)
						.addComponent(checkUrgent)
						.addComponent(UrgentMonth)
						.addComponent(UrgentDay)
						.addComponent(UrgentYear)
						.addComponent(print))
				.addGroup(layout.createParallelGroup(LEADING)
						.addComponent(checkCurrent)
						.addComponent(CurrentMonth)
						.addComponent(CurrentDay)
						.addComponent(CurrentYear)
						.addComponent(historyButton))
				.addGroup(layout.createParallelGroup(LEADING)
						.addComponent(checkEventual)
						.addComponent(EventualMonth)
						.addComponent(EventualDay)
						.addComponent(EventualYear)
						.addComponent(commentButton)));



		checkCurrent.addActionListener(this);
		checkUrgent.addActionListener(this);
		checkEventual.addActionListener(this);

		groupButton.add(radioButton);
		groupButton.add(radioButton2);
		groupButton.add(radioButton3);
		groupButton.add(radioButton4);
		
		//JFrame frame = new JFrame(tasker.getName());
		JDialog dialog = new JDialog();
		dialog.setContentPane(this);
		dialog.setModal(true);
		dialog.setPreferredSize(new Dimension(600,300));
		dialog.setSize(600, 300);
		dialog.setVisible(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		/*frame.setContentPane(this);
		frame.setPreferredSize(new Dimension(600,300));
		frame.pack();
		frame.setVisible(true);*/
	}


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
		
	}

	public int print(Graphics g , PageFormat pf , int pageIndex) throws PrinterException{
		return pageIndex;

	}
}
