package mai.lesson7.lesson7;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.JButton;

public class Gui2 extends JFrame {
	public Gui2() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
	
	
		
		JButton btnNewButton_1 = new JButton("New button");
		toolBar.add(btnNewButton_1);
	}

}
