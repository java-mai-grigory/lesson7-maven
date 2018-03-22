package mai.lesson7.lesson7;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class TestGui extends JFrame{
	private JTextField textField;
	public TestGui() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
	}

}
