package frontend;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JMenuBar;

import backend.Task;
import backend.ToDoList;

public class MainMenu extends JPanel implements MouseListener,MouseWheelListener{
	JTextField addTask;
	JFrame frame;
	JPanel panel;
	JScrollPane scrollPane;
	JMenuBar bar;
	ArrayList<Task> activeTasks;
	private static ToDoList toDoList;
	final static int WIDTH = 1000;
	final static int HEIGHT = 800;
	MainMenu(ToDoList list) {
		
		if(list == null)
			toDoList = new ToDoList();
		else
			toDoList = list;
		
		
		
		
		
		addTask = new JTextField();
		addTask.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		frame = new JFrame("babushka");
		
		
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		
		
		
		
		
		frame.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
				  //make sure that all save operations are done
			  }
		});
		
		
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(WIDTH,HEIGHT*2));
		
		scrollPane = new JScrollPane(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		scrollPane.setVisible(true);
		
		frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		scrollPane.setPreferredSize(new Dimension(WIDTH,HEIGHT*2));
		
		frame.add(scrollPane);
		frame.add(addTask);
		
		frame.pack();
		//frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void arrangeList() {
		activeTasks = toDoList.getActiveTasks();
	}
	public static ToDoList getList() {
		return toDoList;
	}
	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseWheelMoved(MouseWheelEvent e) {
		String msg;
		int notches = e.getWheelRotation();
		
	}
}
