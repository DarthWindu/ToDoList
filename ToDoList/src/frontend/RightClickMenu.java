package frontend;
import backend.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class RightClickMenu{
	private Task task;
	private boolean canComplete;
	JPopupMenu menu;
	EditActionItem edit;
	
	RightClickMenu(Task passedTask){
		menu = new JPopupMenu();
		
		JMenuItem completeItem = new JMenuItem("Set priority to completed");
		
		task = passedTask;
		
		completeItem.addActionListener(new ActionListener(){//might need to use popupmenulisteners?
			public void actionPerformed(ActionEvent arg0){
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
				//edit = new EditActionItem(task);
				
				JFrame frame = new JFrame(task.getName());
				
				EditActionItem x = new EditActionItem(task);
				frame.setContentPane(x);
				frame.setVisible(true);
				frame.pack();
				
				menu.setVisible(false);
			}
		});
		
		menu.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				menu.setVisible(false);
			}
		});
		
		menu.add(completeItem);
		
		menu.add(deleteItem);
		
		menu.add(editItem);
		
		menu.setLocation(MouseInfo.getPointerInfo().getLocation());
		
		menu.setVisible(true);
	}
	
	public void popup(Task t, boolean setCanComplete){//has to work with
		task = t;
		
		if(canComplete)
		
		menu.setLocation(MouseInfo.getPointerInfo().getLocation());
		
		menu.setVisible(true);
	}
}
