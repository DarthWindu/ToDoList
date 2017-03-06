package frontend;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import backend.Task;
import backend.ToDoList;

public class MainMenu extends JPanel implements MouseListener,MouseWheelListener{
	JTextField checkEnter;
	JFrame frame;
	JPanel panel;
	JScrollPane scrollPane;
	ArrayList<Task> activeTasks;
	private static ToDoList toDoList;
	final static int WIDTH = 1000;
	final static int HEIGHT = 800;
	MainMenu() {
		toDoList = new ToDoList();
		checkEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

			}
		});
		frame = new JFrame("babushka");
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(WIDTH,HEIGHT*2));
		scrollPane = new JScrollPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		scrollPane.setVisible(true);
		frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		scrollPane.setPreferredSize(new Dimension(WIDTH,HEIGHT*2));
		frame.add(scrollPane);
		frame.pack();
		frame.setLocationRelativeTo(null);
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
