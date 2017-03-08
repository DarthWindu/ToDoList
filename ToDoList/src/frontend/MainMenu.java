package frontend;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import backend.Task;
import backend.ToDoList;

public class MainMenu extends JPanel implements MouseListener,MouseWheelListener{
	JTextField addTask;
	JFrame frame;
	JPanel panel;
	JScrollPane scrollPane;
	JMenuBar bar;
	JMenu fileMenu;
	JMenuItem open;
	ArrayList<Task> activeTasks;
	private static ToDoList toDoList;
	final static int WIDTH = 1000;
	final static int HEIGHT = 800;
	
	public MainMenu(ToDoList list) {
		
		if(list == null)
			toDoList = new ToDoList();
		else
			toDoList = list;
		
		
		
		addTask = new JTextField();
		addTask.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				toDoList.add(new Task(addTask.getText()));
			}
		});
		
		frame = new JFrame("To Do List");
		
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		
		
		bar = new JMenuBar();
		
		fileMenu = new JMenu("file");
		
		open = new JMenuItem("open");
		
		
		fileMenu.setSize(new Dimension(50,50));
		
		
		fileMenu.add(open);
		
		bar.add(fileMenu);// make it fit and to the left
		
		bar.setPreferredSize(new Dimension(50,50));
		
		
		frame.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
				  try
			        {
						FileOutputStream fileOut = new FileOutputStream("src/" + "todolist" + ".java");
						ObjectOutputStream out = new ObjectOutputStream(fileOut);
						out.writeObject(toDoList);
						out.close();
						fileOut.close();
			        }catch(IOException i)
			        {
			            i.printStackTrace();
			        }

			  }
		});
		
		
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(WIDTH,HEIGHT*2));
		
		scrollPane = new JScrollPane(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		scrollPane.setVisible(true);
		
		frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		scrollPane.setPreferredSize(new Dimension(WIDTH,HEIGHT*2));
		
		frame.add(bar);
		frame.add(scrollPane);// I dont think we need a horizontal scroll bar
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
