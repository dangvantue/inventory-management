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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class JFrameProfile extends JFrame {

	private JPanel contentPane;
	private JTextField jtextFieldUserName;
	private JTextField jtextFieldEmail;
	private JTextField jtextFieldPhone;
	private JPasswordField jpasswordFieldPassword;
	private JDateChooser jdateChooserBirthday;
	private JTextField jtextFieldFullName;
	private JTextField jtextFieldAddress;
	private JComboBox jcomboBoxRoleAccount;
	private Map<String, Object> data = new HashMap<String, Object>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameProfile frame = new JFrameProfile();
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
	public JFrameProfile() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 421, 409);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UPDATE ACCOUNT");
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
		
		JButton jbtnSave = new JButton("Save");
		jbtnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnSave_actionPerformed(e);
			}
		});
		jbtnSave.setBounds(177, 335, 62, 33);
		panel.add(jbtnSave);
		
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
		
		JButton jbtnLogin = new JButton("");
		jbtnLogin.setIcon(new ImageIcon(JFrameProfile.class.getResource("/resources/Exit.png")));
		jbtnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnExit_actionPerformed(e);
			}
		});
		jbtnLogin.setBounds(249, 335, 42, 33);
		panel.add(jbtnLogin);
		
		JLabel jbtnAccount = new JLabel("");
		jbtnAccount.setIcon(new ImageIcon(JFrameProfile.class.getResource("/img/register1.jpg")));
		jbtnAccount.setBounds(0, 0, 421, 409);
		panel.add(jbtnAccount);
				
	}
	
	public JFrameProfile(Map<String, Object> data) {
		this();
		this.data = data;
		loadDataCombobox();
		loadData();
	}
	
	public void jbtnExit_actionPerformed(ActionEvent e) {
		//JMainFrame jmainFrame = new JMainFrame();
		//jmainFrame.setVisible(true);
		this.setVisible(false);
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
	
	private void loadDataCombobox() {
		RoleModel roleModel = new RoleModel();
		fillDataToJCombobox(roleModel.findAll());
	}
		
	private void loadData() {
		Account account = (Account) data.get("username");
		jtextFieldAddress.setText(account.getAddress());
		jtextFieldEmail.setText(account.getEmail());
		jtextFieldFullName.setText(account.getFullname());
		jtextFieldPhone.setText(String.valueOf(account.getPhone()));
		jdateChooserBirthday.setDate(account.getBirthday());
	}
	
	
	public void jbtnSave_actionPerformed(ActionEvent e) {
		
	}
}
