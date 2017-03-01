package frontend;
import backend.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainMenu extends JPanel implements MouseListener{
	JTextField checkEnter;
	JFrame frame;
	JPanel panel;
	ArrayList<Task> activeTasks;
	ToDoList toDoList;
	final static int WIDTH = 1000;
	final static int HEIGHT = 800;
	MainMenu() {
		toDoList = new ToDoList();
		checkEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

			}
		});
		frame = new JFrame();
		panel = new JPanel();
		
	}

	public void arrangeList() {
		activeTasks = toDoList.getActiveTasks();
	}
	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}
}
