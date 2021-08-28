package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import entities.Account;
import models.AccountModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class JFrameChangePassword extends JFrame {

	private JPanel contentPane;
	private JTextField jtextFieldUsername;
	private JPasswordField jpasswordFieldPassword;
	private JButton jbtnSave;
	private JButton jbtnExit;
	private Map<String, Object> data = new HashMap<String, Object>();
	private JPasswordField jpasswordFieldRepeatPassword;
	private JCheckBox jCheckBoxStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameChangePassword frame = new JFrameChangePassword();
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
	public JFrameChangePassword() {
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CHANGE PASSWORD");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Californian FB", Font.BOLD, 20));
		lblNewLabel.setBounds(93, 30, 244, 25);
		contentPane.add(lblNewLabel);
		
		jtextFieldUsername = new JTextField();
		jtextFieldUsername.setEnabled(false);
		jtextFieldUsername.setBounds(199, 65, 138, 25);
		contentPane.add(jtextFieldUsername);
		jtextFieldUsername.setColumns(10);
		
		jpasswordFieldPassword = new JPasswordField();
		jpasswordFieldPassword.setBounds(199, 101, 138, 26);
		contentPane.add(jpasswordFieldPassword);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setBounds(78, 65, 108, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("New Password");
		lblNewLabel_1_1.setBounds(78, 102, 108, 25);
		contentPane.add(lblNewLabel_1_1);
		
		jbtnSave = new JButton("Save");
		jbtnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnSave_actionPerformed(e);
			}
		});
		jbtnSave.setBounds(158, 188, 100, 30);
		contentPane.add(jbtnSave);
		
		jbtnExit = new JButton("");
		jbtnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnExit_actionPerformed(e);
			}
		});
		jbtnExit.setIcon(new ImageIcon(JFrameChangePassword.class.getResource("/resources/Exit.png")));
		jbtnExit.setBounds(268, 188, 28, 30);
		contentPane.add(jbtnExit);
		
		jpasswordFieldRepeatPassword = new JPasswordField();
		jpasswordFieldRepeatPassword.setBounds(199, 138, 138, 26);
		contentPane.add(jpasswordFieldRepeatPassword);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Confirm Password");
		lblNewLabel_1_1_1.setBounds(78, 138, 108, 25);
		contentPane.add(lblNewLabel_1_1_1);
		
		jCheckBoxStatus = new JCheckBox("Status");
		jCheckBoxStatus.setVisible(false);
		jCheckBoxStatus.setBounds(343, 140, 97, 23);
		contentPane.add(jCheckBoxStatus);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(JFrameChangePassword.class.getResource("/img/register1.jpg")));
		lblNewLabel_2.setBounds(0, 0, 444, 271);
		contentPane.add(lblNewLabel_2);
	}
	
	public void jbtnSave_actionPerformed(ActionEvent e) {
		boolean status = jCheckBoxStatus.isSelected();
		AccountModel accountModel = new AccountModel();
		Account account = accountModel.find(jtextFieldUsername.getText());
		String password = new String(jpasswordFieldPassword.getPassword());
		String repeatPassword = new String(jpasswordFieldRepeatPassword.getText());		
		if ((!password.isEmpty()) && (password.equals(repeatPassword))){
			String hash = BCrypt.hashpw(password, BCrypt.gensalt());
			account.setPassword(hash);
			account.setStatus(status);
			JOptionPane.showMessageDialog(null, "Done");
//			JMainFrame jMainFrame = new JMainFrame();
//			jMainFrame.setVisible(true);
			JFrameLogin jFrameLogin = new JFrameLogin();
			jFrameLogin.setVisible(true);
			this.setVisible(false);
		}else {
			JOptionPane.showMessageDialog(null, "Confirm failed");
		}
		accountModel.update(account);
	}
	
	public void jbtnExit_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
	
	public JFrameChangePassword(Map<String, Object> data) {
		this();
		this.data = data;
		loadData();
	}
	
	private void loadData() {
		Account account = (Account) data.get("account");
		jtextFieldUsername.setText(account.getUsername());
		jCheckBoxStatus.setSelected(false);
	}
}
