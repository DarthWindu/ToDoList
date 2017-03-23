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
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.*;

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
	EditActionItem(Task task)
	{
		tasker = task;
		groupButton = new ButtonGroup();


		//Hundreds and hundreds of variables.
		name = new JTextField(task.getName());
		CurrentMonth = new JTextField("MM");
		CurrentDay = new JTextField("DD");
		CurrentYear = new JTextField("YYYY");
		UrgentMonth = new JTextField("MM");
		UrgentDay = new JTextField("DD");
		UrgentYear = new JTextField("YYYY");
		EventualMonth = new JTextField("MM");
		EventualDay = new JTextField("DD");
		EventualYear = new JTextField("YYYY");

		CurrentMonth.setEnabled(false);
		CurrentDay.setEnabled(false);
		CurrentYear.setEnabled(false);
		UrgentMonth.setEnabled(false);
		UrgentDay.setEnabled(false);
		UrgentYear.setEnabled(false);
		EventualMonth.setEnabled(false);
		EventualDay.setEnabled(false);
		EventualYear.setEnabled(false);

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
				//name.setText("");
			}

			public void focusLost(FocusEvent e)
			{
				tasker.changeName(name.getText());
			}
		});



		CurrentMonth.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e) {CurrentMonth.setText("");}
			public void focusLost(FocusEvent arg0) {}
		});


		CurrentDay.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e)
			{
				CurrentDay.setText("");
			}


			public void focusLost(FocusEvent arg0) {

			}

		});
		CurrentYear.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e)
			{
				CurrentYear.setText("");
			}


			public void focusLost(FocusEvent arg0) {

			}

		});
		UrgentMonth.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e)
			{
				UrgentMonth.setText("");
			}


			public void focusLost(FocusEvent arg0) {

			}

		});
		UrgentDay.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e)
			{
				UrgentDay.setText("");
			}


			public void focusLost(FocusEvent arg0) {

			}

		});
		UrgentYear.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e)
			{
				UrgentYear.setText("");
			}


			public void focusLost(FocusEvent arg0) {

			}

		});
		EventualMonth.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e)
			{
				EventualMonth.setText("");
			}


			public void focusLost(FocusEvent arg0) {

			}

		});
		EventualDay.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e)
			{
				EventualDay.setText("");
			}


			public void focusLost(FocusEvent arg0) {

			}

		});
		EventualYear.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e)
			{
				EventualYear.setText("");
			}


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


		CurrentMonth.addKeyListener(new KeyAdapter(){
			public void keyTyped( KeyEvent e ){
				if (CurrentMonth.getText().length()>=2)
					e.consume();
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // ignore event
				}
			}
		});


		CurrentDay.addKeyListener(new KeyAdapter(){
			public void keyTyped( KeyEvent e ){
				if (CurrentDay.getText().length()>=2)
					e.consume();
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // ignore event
				}
			}
		});


		CurrentYear.addKeyListener(new KeyAdapter(){
			public void keyTyped( KeyEvent e ){
				if (CurrentYear.getText().length()>=4)
					e.consume();
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // ignore event
				}
			}
		});

		UrgentMonth.addKeyListener(new KeyAdapter(){
			public void keyTyped( KeyEvent e ){
				if (UrgentMonth.getText().length()>=2)
					e.consume();
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // ignore event
				}
			}
		});

		UrgentDay.addKeyListener(new KeyAdapter(){
			public void keyTyped( KeyEvent e ){
				if (UrgentDay.getText().length()>=2)
					e.consume();
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // ignore event
				}
			}
		});

		UrgentYear.addKeyListener(new KeyAdapter(){
			public void keyTyped( KeyEvent e ){
				if (UrgentYear.getText().length()>=4)
					e.consume();
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // ignore event
				}
			}
		});

		EventualMonth.addKeyListener(new KeyAdapter(){
			public void keyTyped( KeyEvent e ){
				if (EventualMonth.getText().length()>=2)
					e.consume();
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // ignore event
				}
			}
		});

		EventualDay.addKeyListener(new KeyAdapter(){
			public void keyTyped( KeyEvent e ){
				if (EventualDay.getText().length()>=2)
					e.consume();
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // ignore event
				}
			}
		});

		EventualYear.addKeyListener(new KeyAdapter(){
			public void keyTyped( KeyEvent e ){
				if (EventualYear.getText().length()>=4)
					e.consume();
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // ignore event
				}
			}
		});
		
		//window listener that stores dates when window is closed
		//Date(int year, int month, int date)
		//call task.storeDate(new Date(), int index)^^^
		// index for eventual is 0, current is 1, urgent is 2




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


		checkCurrent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				if (checkCurrent.isSelected())
				{
					CurrentMonth.setEnabled(true);
					CurrentDay.setEnabled(true);
					CurrentYear.setEnabled(true);
				} else 
				{
					CurrentMonth.setEnabled(false);
					CurrentDay.setEnabled(false);
					CurrentYear.setEnabled(false);
				}
			}

		});


		checkUrgent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (checkUrgent.isSelected())
				{
					UrgentMonth.setEnabled(true);
					UrgentDay.setEnabled(true);
					UrgentYear.setEnabled(true);
				} else 
				{
					UrgentMonth.setEnabled(false);
					UrgentDay.setEnabled(false);
					UrgentYear.setEnabled(false);
				}
			}

		});
		checkEventual.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (checkEventual.isSelected())
				{
					EventualMonth.setEnabled(true);
					EventualDay.setEnabled(true);
					EventualYear.setEnabled(true);
				} else 
				{
					EventualMonth.setEnabled(false);
					EventualDay.setEnabled(false);
					EventualYear.setEnabled(false);
				}

			}
		});


		groupButton.add(radioButton);
		groupButton.add(radioButton2);
		groupButton.add(radioButton3);
		groupButton.add(radioButton4);

		JFrame frame = new JFrame(tasker.getName());

		frame.setContentPane(this);
		frame.setPreferredSize(new Dimension(600,300));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
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
		Task w = new Task("WOO");
		EditActionItem a = new EditActionItem(w);
	}

	public int print(Graphics g , PageFormat pf , int pageIndex) throws PrinterException{
		return pageIndex;

	}
}
