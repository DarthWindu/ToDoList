package frontend;
import backend.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EditActionItem extends JPanel implements ActionListener{
	JRadioButton radioButton;
	JCheckBox checkCurrent,checkUrgent,checkEventual;
	JButton commentButton;
	JButton historyButton;
	JButton print;

	EditActionItem() {
		radioButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

			}
		});
		checkCurrent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

			}
		});
		checkUrgent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

			}
		});
		checkEventual.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

			}
		});
		commentButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

			}
		});
		historyButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

			}
		});
		print.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

	public void actionPerformed(ActionEvent e) {}
}
