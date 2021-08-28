package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Customer;
import models.CustomerModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewCustomerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField jtextFieldName;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewCustomerFrame frame = new NewCustomerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewCustomerFrame() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer Name :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(72, 30, 96, 14);
		contentPane.add(lblNewLabel);
		
		jtextFieldName = new JTextField();
		jtextFieldName.setBounds(184, 27, 86, 20);
		contentPane.add(jtextFieldName);
		jtextFieldName.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone :");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setBounds(72, 58, 96, 14);
		contentPane.add(lblPhone);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(184, 55, 86, 20);
		contentPane.add(textField);
		
		JButton jbtnCreate = new JButton("CREATE");
		jbtnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = jtextFieldName.getText();
				Customer customer = new Customer();
				CustomerModel customerModel = new CustomerModel();
				customer.setName(name);
				if(customerModel.CreateCustomer(customer)) {
					JOptionPane.showMessageDialog(null, "Success");
					dispose();
				}
				else {
					JOptionPane.showConfirmDialog(null, "FAILED");
				}
				
			}
		});
		jbtnCreate.setBounds(162, 112, 89, 23);
		contentPane.add(jbtnCreate);
	}
}
