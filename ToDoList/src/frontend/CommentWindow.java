package frontend;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CommentWindow extends JFrame{
	JButton commit, delete;
	JFrame frame;
	JTextField field;
	JPanel panel;
	CommentWindow() {
		frame = new JFrame("Comment Window");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new JPanel();
		field = new JTextField();
		panel.add(field,BorderLayout.NORTH);
		JButton commit = new JButton("Commit");
		commit.setBounds(50, 50, 50, 50);
		panel.add(commit);
		frame.setContentPane(panel);
		frame.setSize(1600,900);
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args){
		CommentWindow test = new CommentWindow();
	}
}
