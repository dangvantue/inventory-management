package frames;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class JPanelUpdate extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public JPanelUpdate() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel jpanelUpdate = new JPanel();
		add(jpanelUpdate);
		jpanelUpdate.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update Account");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Candara", Font.BOLD, 20));
		lblNewLabel.setBounds(163, 11, 145, 32);
		jpanelUpdate.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(73, 59, 46, 14);
		jpanelUpdate.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(157, 56, 86, 20);
		jpanelUpdate.add(textField);
		textField.setColumns(10);
		
		JButton jbtnSave = new JButton("Save");
		jbtnSave.setBounds(163, 215, 89, 23);
		jpanelUpdate.add(jbtnSave);

	}
}
