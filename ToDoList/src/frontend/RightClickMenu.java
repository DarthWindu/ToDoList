package frontend;
import backend.*;

import javax.swing.*;
import java.awt.MouseInfo;
import java.awt.event.*;

public class RightClickMenu extends JPanel{
	JPopupMenu menu;
	Task tasker;
	ToDoList lister;
	RightClickMenu(Task task, ToDoList list) {
		tasker = task;
		lister = list;
		menu = new JPopupMenu();
		
		JMenuItem completeItem = new JMenuItem("Set priority to completed");
		
		completeItem.addActionListener(new ActionListener(){//might need to use popupmenulisteners?
			public void actionPerformed(ActionEvent arg0) {
				task.setStatus(Task.COMPLETED);
				tasker.setStatus(Task.COMPLETED);
				menu.setVisible(false);
			}
		});
		
		JMenuItem deleteItem = new JMenuItem("Delete");
		
		deleteItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				lister.delete(tasker);
				
				menu.setVisible(false);
			}
		});
		
		JMenuItem editItem = new JMenuItem("Edit");
		
		editItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				new EditActionItem(tasker);
				
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
