package frontend;
import backend.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class RightClickMenu{
	private Task task;
	JPopupMenu menu;
	EditActionItem edit;
	
	RightClickMenu(Task passedTask){
		menu = new JPopupMenu();
		
		JMenuItem completeItem = new JMenuItem("Set priority to completed");
		
		task = passedTask;
		
		completeItem.addActionListener(new ActionListener(){//might need to use popupmenulisteners?
			public void actionPerformed(ActionEvent arg0) {
				task.setStatus(Task.COMPLETED);
				
			}
		});
		
		JMenuItem deleteItem = new JMenuItem("Delete");
		
		deleteItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				MainMenu.getList().delete(task);
				
			}
		});
		
		JMenuItem editItem = new JMenuItem("Edit");
		
		editItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("a");
				
				edit = new EditActionItem(task);
				
			}
		});
		
		menu.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				menu.setVisible(false);
			}

			public void mouseEntered(MouseEvent arg0) {}

			public void mouseExited(MouseEvent arg0) {}

			public void mousePressed(MouseEvent arg0) {}

			public void mouseReleased(MouseEvent arg0) {}
		});
		
		menu.add(completeItem);
		
		menu.add(deleteItem);
		
		menu.add(editItem);
		
		
		
		menu.setLocation(MouseInfo.getPointerInfo().getLocation());
		
		menu.setVisible(true);
	}
}
