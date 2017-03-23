package frontend;
// Program to print simple text on a Printer

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.awt.print.*;

class Printer extends JPanel implements Printable  {

	JButton print;

	Printer() {
		buildGUI();
		hookUpEvents();
	}

	public void buildGUI() {
		JFrame fr = new JFrame("Program to Print on a Printer");
		JPanel p = new JPanel();
		print = new JButton("Print");
		setPreferredSize( new Dimension ( 200,200 ) );
		p.setBackground( Color.black );
		fr.add(p);
		p.add( print , BorderLayout.CENTER );
		fr.pack();
		fr.setVisible( true );
	}

	public void hookUpEvents() {
		print.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent ae ) {
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable( new Printer() );
				boolean doPrint = job.printDialog();
				if( doPrint ) {
					try {
						job.print();
					} catch( PrinterException exc) {
						System.out.println( exc );
					}
				}
			}
		});
	}

	@Override
	public int print( Graphics g , PageFormat pf , int pageIndex) throws PrinterException{
		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		g.drawString( "testing..." , 100 , 100 );
		return PAGE_EXISTS;
	}

	public static void main( String args[] ) {
		new Printer();
	}
}