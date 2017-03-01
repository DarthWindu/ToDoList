package frontend;
import backend.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class RightClickMenu extends JPanel implements ActionListener{
	Menu menu;
	JMenuItem[] menuItems;
	
	
	RightClickMenu() {
		menu = new Menu("right");
		
		menuItems = new JMenuItem[3];
	}
	
	public void actionPerformed(ActionEvent arg0) {}
}
