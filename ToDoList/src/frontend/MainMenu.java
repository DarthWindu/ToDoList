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
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.ScrollPaneConstants;

import backend.Task;
import backend.ToDoList;

public class MainMenu extends JPanel implements MouseListener,MouseWheelListener{
	JTextField addTask;
	JFrame frame;
	JPanel panel;
	JScrollPane scrollPane;
	JMenuBar bar, fileBar;
	JList<Task> display;
	ArrayList<Task> activeTasks;
	
	JMenu fileMenu;
	JMenuItem backup, restore, print, closedItems;
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

			}
		});
		
		frame = new JFrame("To Do List");
		
		
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		
		
		bar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		
		fileMenu.add(backup);
		
		fileMenu.add(restore);//initialize all of these
		
		fileMenu.add(print);
		
		bar.add(fileMenu);// make it fit and to the left
		
		bar.add(closedItems);
		
		bar.add(Box.createHorizontalGlue());
		
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
		
		scrollPane.setPreferredSize(new Dimension(WIDTH,HEIGHT*2));
		
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//display = new JList<Task>((Task[]) activeTasks.toArray());
		
		display = new JList<Task>();
		
		scrollPane.add(display);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		scrollPane.setVisible(true);
		
		frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		
		
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
