package frontend;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CommentWindow extends JFrame{
	JButton commit, delete;
	JFrame frame;
	CommentWindow() {
		frame = new JFrame("Comment Window");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		commit = new JButton();
		commit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	public static void main(String[] args){
		CommentWindow test = new CommentWindow();
	}
}
