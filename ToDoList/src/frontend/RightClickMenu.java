package frontend;
import backend.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class RightClickMenu{
	RightClickMenu(Task task) {
		JPopupMenu menu = new JPopupMenu();
		
		JMenuItem completeItem = new JMenuItem("Set priority to completed");
		
		completeItem.addActionListener(new ActionListener(){//might need to use popupmenulisteners?
			public void actionPerformed(ActionEvent arg0) {
				task.setStatus(Task.COMPLETED);
				
				menu.setVisible(false);
			}
		});
		
		JMenuItem deleteItem = new JMenuItem("Delete");
		
		deleteItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				MainMenu.getList().delete(task);
				
				menu.setVisible(false);
			}
		});
		
		JMenuItem editItem = new JMenuItem("Edit");
		
		editItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				new EditActionItem(task);
				
				menu.setVisible(false);
			}
		});
		
		
		
		menu.add(completeItem);
		
		menu.add(deleteItem);
		
		menu.add(editItem);
		
		menu.setLocation(MouseInfo.getPointerInfo().getLocation());
		
		menu.setVisible(true);
	}
}
