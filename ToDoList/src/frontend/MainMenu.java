package frontend;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuBar;
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
	
	RightClickMenu rightClick;
	
	private static ToDoList toDoList;
	final static int WIDTH = 1000;
	final static int HEIGHT = 800;
	
	public MainMenu(ToDoList list) {
		if(list == null)
			toDoList = new ToDoList();
		else
			toDoList = list;
		
		//toDoList.checkPriorityChange(Calendar.getInstance().getTime());
		
		activeTasks = toDoList.getActiveTasks();
		
		
		addTask = new JTextField();
		addTask.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		rightClick = new RightClickMenu(null);
		rightClick.hide();
		
		frame = new JFrame("To Do List");
		
		
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		
		
		bar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		
		backup = new JMenuItem("Backup");
		
		backup.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//back up to a chosen file
			}
		});
		
		restore = new JMenuItem("Restore");
		
		restore.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//restore list from chosen file
			}
		});
		
		print = new JMenuItem("Print");
		
		print.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//print
			}
		});
		
		fileMenu.add(backup);
		
		fileMenu.add(restore);
		
		fileMenu.add(print);
		
		bar.add(fileMenu);
		
		closedItems = new JMenuItem("Completed Action Items");
		
		closedItems.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//open completed action items
			}
		});
		
		//closedItems.setPreferredSize(new Dimension(50, 50));//y is it so big
		
		bar.add(closedItems);
		
		//bar.add(Box.createGlue());
		
		//bar.setPreferredSize(new Dimension(50,50));
		
		frame.addWindowListener(new WindowAdapter() {
			  @Override
			public void windowClosing(WindowEvent e) {
				  try
			        {
						FileOutputStream fileOut = new FileOutputStream("src/" + "todolist" + ".java");//should save to default file
						ObjectOutputStream out = new ObjectOutputStream(fileOut);
						out.writeObject(toDoList);
						out.close();
						fileOut.close();
			        }catch(IOException i)
			        {
			        }

			  }
		});
		
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(WIDTH,HEIGHT*2));
		
		scrollPane = new JScrollPane(panel);
		
		scrollPane.setPreferredSize(new Dimension(WIDTH,HEIGHT*2));
		
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		display = new JList(toDoList.getActiveTasks().toArray());
		
		display.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//may need to change
		
		//display.getModel();
		
		//	ListCellRenderer<Task> cl = new ListCellRenderer<Task>();
		
		//display.setCellRenderer(cellRenderer);
		
		display.addMouseListener( new MouseAdapter(){//doesn't work when there is stuff added in???
			@Override
			public void mouseClicked(MouseEvent e){
				
				System.out.println("a");
		        if (e.getButton() == MouseEvent.BUTTON3)
		        {
		        	
		            //int row = display.locationToIndex(e.getPoint());
		            //display.setSelectedIndex(row);
		            
		            //(Task)toDoList.getActiveTasks().toArray()[display.locationToIndex(e.getPoint())]
		            display.setSelectedIndex(display.locationToIndex(e.getPoint()));
		            rightClick.popup(display.getSelectedValue(), true);//
		            
		            
		        }
		    }
		   });
		/*
		display.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent me) {// TODO this is where right click should open
				System.out.println(display.getSelectedValue().getName());
				
				if(me.getButton() == MouseEvent.BUTTON2)
					rightClick.popup(display.getSelectedValue(), true);
				
				
			}

			
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		*/
		scrollPane.setViewportView(display);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		scrollPane.setVisible(true);
		
		frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		addTask.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!addTask.getText().equals("")){
					toDoList.add(new Task(addTask.getText()));
					
					//toDoList.checkPriorityChange(Calendar.getInstance().getTime());
					
					activeTasks = toDoList.getActiveTasks();
					
					display = new JList(activeTasks.toArray());
					
					scrollPane.setViewportView(display);
					
					addTask.setText("");
				}
			}
		});
		
		//add listselectionlistener? for rightclick
		
		
		
		
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
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		String msg;
		int notches = e.getWheelRotation();
	}
	
}
