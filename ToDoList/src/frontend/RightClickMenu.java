package frontend;
import backend.*;

import javax.swing.*;

import java.awt.event.*;

public class RightClickMenu extends JPanel{
	JPopupMenu menu;
	
	RightClickMenu(int x, int y) {
		menu = new JPopupMenu();
		
		JMenuItem completeItem = new JMenuItem("Set priority to completed");
		
		completeItem.addActionListener(new ActionListener(){//might need to use popupmenulisteners
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		
		JMenuItem deleteItem = new JMenuItem("Delete");
		
		deleteItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		
		JMenuItem editItem = new JMenuItem("Edit");
		
		editItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		
		menu.add(completeItem);
		
		menu.add(deleteItem);
		
		menu.add(editItem);

		menu.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {}
}
