package frontend;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CommentWindow extends JPanel implements ActionListener{
	JButton commit, delete;
	CommentWindow() {
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
	public void actionPerformed(ActionEvent arg0) {}
}
