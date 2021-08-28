package frames;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Dimension;

public class tests extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public tests() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setPreferredSize(new Dimension(100, 22));
		panel_2.add(comboBox);
		
		JPanel panel_3 = new JPanel();
		add(panel_3);
		
		JComboBox comboBox_1 = new JComboBox();
		panel_3.add(comboBox_1);

	}

}
