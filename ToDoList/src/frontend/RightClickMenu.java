package frontend;
import backend.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class RightClickMenu extends JPanel{
	 
	
	RightClickMenu(Task task) {
		System.out.println("yyyyyyy");
		
		JPopupMenu menu = new JPopupMenu();
		
		JMenuItem completeItem = new JMenuItem("Set priority to completed");
		
		completeItem.addActionListener(new ActionListener(){//might need to use popupmenulisteners?
			public void actionPerformed(ActionEvent arg0) {
				task.setStatus(Task.COMPLETED);
				System.out.println("complete");
				menu.setVisible(false);
			}
		});
		
		JMenuItem deleteItem = new JMenuItem("Delete");
		
		deleteItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				MainMenu.getList().delete(task);
				System.out.println("delete");
				menu.setVisible(false);
			}
		});
		
		JMenuItem editItem = new JMenuItem("Edit");
		
		editItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				new EditActionItem(task);
				System.out.println("edit");
				menu.setVisible(false);
			}
		});
		
		menu.add(completeItem);
		
		menu.add(deleteItem);
		
		menu.add(editItem);
		
		System.out.println((float) MouseInfo.getPointerInfo().getLocation().getX());
		System.out.println((float) MouseInfo.getPointerInfo().getLocation().getY());
		
		menu.setLocation(MouseInfo.getPointerInfo().getLocation());
		
		menu.setVisible(true);
	}
}
