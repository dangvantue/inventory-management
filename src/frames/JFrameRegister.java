package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.mindrot.jbcrypt.BCrypt;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import entities.Account;
import entities.RoleAccount;
import models.AccountModel;
import models.RoleModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class JFrameRegister extends JFrame {

	private JPanel contentPane;
	private JTextField jtextFieldUserName;
	private JTextField jtextFieldEmail;
	private JTextField jtextFieldPhone;
	private JPasswordField jpasswordFieldPassword;
	private JDateChooser jdateChooserBirthday;
	private JTextField jtextFieldFullName;
	private JTextField jtextFieldAddress;
	private JComboBox jcomboBoxRoleAccount;
	private JCheckBox jCheckBoxStatus;
	private JButton jbtnExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameRegister frame = new JFrameRegister();
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
	public JFrameRegister() {
		setResizable(false);
		setBounds(100, 100, 428, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 421, 409);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTER NOW");
		lblNewLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(104, 36, 201, 44);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setBounds(88, 83, 78, 20);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setBounds(88, 111, 78, 20);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email");
		lblNewLabel_1_2.setBounds(88, 174, 78, 20);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Phone");
		lblNewLabel_1_3.setBounds(88, 205, 69, 20);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Address");
		lblNewLabel_1_4.setBounds(88, 234, 78, 20);
		panel.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Birthday");
		lblNewLabel_1_5.setBounds(88, 269, 78, 20);
		panel.add(lblNewLabel_1_5);
		
		jdateChooserBirthday = new JDateChooser();
		jdateChooserBirthday.setBounds(177, 265, 126, 26);
		panel.add(jdateChooserBirthday);
		
		jtextFieldUserName = new JTextField();
		jtextFieldUserName.setBounds(176, 78, 128, 26);
		panel.add(jtextFieldUserName);
		jtextFieldUserName.setColumns(10);
		
		jtextFieldEmail = new JTextField();
		jtextFieldEmail.setColumns(10);
		jtextFieldEmail.setBounds(176, 171, 128, 26);
		panel.add(jtextFieldEmail);
		
		jtextFieldPhone = new JTextField();
		jtextFieldPhone.setColumns(10);
		jtextFieldPhone.setBounds(176, 202, 128, 26);
		panel.add(jtextFieldPhone);
		
		JButton jbtnRegister = new JButton("Register");
		jbtnRegister.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jbtnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnRegister_actionPerformed(e);
			}
		});
		jbtnRegister.setBounds(143, 335, 89, 33);
		panel.add(jbtnRegister);
		
		jpasswordFieldPassword = new JPasswordField();
		jpasswordFieldPassword.setBounds(177, 108, 127, 26);
		panel.add(jpasswordFieldPassword);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Full Name");
		lblNewLabel_1_1_1.setBounds(88, 142, 78, 20);
		panel.add(lblNewLabel_1_1_1);
		
		jtextFieldFullName = new JTextField();
		jtextFieldFullName.setColumns(10);
		jtextFieldFullName.setBounds(177, 139, 128, 26);
		panel.add(jtextFieldFullName);
		
		jtextFieldAddress = new JTextField();
		jtextFieldAddress.setColumns(10);
		jtextFieldAddress.setBounds(177, 234, 128, 26);
		panel.add(jtextFieldAddress);
		
		JLabel lblNewLabel_2 = new JLabel("Role");
		lblNewLabel_2.setBounds(88, 302, 78, 14);
		panel.add(lblNewLabel_2);
		
		jcomboBoxRoleAccount = new JComboBox();
		jcomboBoxRoleAccount.setBounds(177, 297, 128, 27);
		panel.add(jcomboBoxRoleAccount);
		
		jCheckBoxStatus = new JCheckBox("Status");
		jCheckBoxStatus.setVisible(false);
		jCheckBoxStatus.setBounds(311, 298, 97, 23);
		panel.add(jCheckBoxStatus);
		
		jbtnExit = new JButton("");
		jbtnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnExit_actionPerformed(e);
			}
		});
		jbtnExit.setIcon(new ImageIcon(JFrameRegister.class.getResource("/resources/Exit.png")));
		jbtnExit.setBounds(242, 335, 36, 33);
		panel.add(jbtnExit);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(JFrameRegister.class.getResource("/img/register1.jpg")));
		lblNewLabel_3.setBounds(0, 0, 421, 409);
		panel.add(lblNewLabel_3);
		
		loadData();
		
	}
	
	private void fillDataToJCombobox(List<RoleAccount> roleAccounts) {
		DefaultComboBoxModel<RoleAccount> defaultComboBoxModel = new DefaultComboBoxModel<RoleAccount>();
		for(RoleAccount roleAccount : roleAccounts) {
			defaultComboBoxModel.addElement(roleAccount);
		}
		jcomboBoxRoleAccount.setModel(defaultComboBoxModel);
		jcomboBoxRoleAccount.setRenderer(new roleAccountListCellRender());
	}
	
	private class roleAccountListCellRender extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			RoleAccount roleAccount = (RoleAccount) value;
			return super.getListCellRendererComponent(list, roleAccount.getName(), index, isSelected, cellHasFocus);
		}	
	}
	
	public void jbtnExit_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
		
	private void loadData() {
		RoleModel roleModel = new RoleModel();
		fillDataToJCombobox(roleModel.findAll());
		jCheckBoxStatus.setSelected(true);
	}
	
	public void jbtnRegister_actionPerformed(ActionEvent e) {
		Account account = new Account();
		AccountModel accountModel = new AccountModel();
		account.setAddress(jtextFieldAddress.getText());
		account.setBirthday(jdateChooserBirthday.getDate());
		account.setPhone(Integer.parseInt(jtextFieldPhone.getText()));
		account.setEmail(jtextFieldEmail.getText());
		account.setFullname(jtextFieldFullName.getText());
		account.setStatus(jCheckBoxStatus.isSelected());;
		String password = new String(jpasswordFieldPassword.getPassword());
		String hash = BCrypt.hashpw(password, BCrypt.gensalt());
		account.setPassword(hash);
		account.setUsername(jtextFieldUserName.getText());
		RoleAccount roleAccount = (RoleAccount) jcomboBoxRoleAccount.getSelectedItem();
		account.setRole_id(roleAccount.getId());
		System.out.println(roleAccount.getId());
		if (accountModel.create(account)) {
			JOptionPane.showMessageDialog(null, "Done");
		}else {
			JOptionPane.showMessageDialog(null, "Failed");
		}
	}
}
