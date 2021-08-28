package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import data.AccountData;
import entities.Account;
import models.AccountModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;
public class JFrameLogin extends JFrame {

	private JPanel contentPane;
	private JTextField jtextFieldUserName;
	private JPasswordField jpasswordFieldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameLogin frame = new JFrameLogin();
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
	public JFrameLogin() {
		setResizable(false);
		setBounds(100, 100, 516, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 510, 340);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN NOW");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Eras Medium ITC", Font.BOLD, 22));
		lblNewLabel.setBounds(166, 89, 184, 27);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User Name ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(138, 137, 81, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(138, 175, 81, 27);
		panel.add(lblNewLabel_1_1);
		
		jtextFieldUserName = new JTextField();
		jtextFieldUserName.setBounds(229, 139, 184, 27);
		panel.add(jtextFieldUserName);
		jtextFieldUserName.setColumns(10);
		
		JButton jbtnLogin = new JButton("Login");
		jbtnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jbtnLogin.setIcon(new ImageIcon(JFrameLogin.class.getResource("/resources/Login-square.png")));
		jbtnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnLogin_actionPerformed(e);
			}
		});
		jbtnLogin.setBounds(229, 211, 121, 23);
		panel.add(jbtnLogin);
		
		jpasswordFieldPassword = new JPasswordField();
		jpasswordFieldPassword.setBounds(229, 175, 184, 25);
		panel.add(jpasswordFieldPassword);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(JFrameLogin.class.getResource("/img/register1.jpg")));
		lblNewLabel_2.setBounds(0, 0, 510, 340);
		panel.add(lblNewLabel_2);
	}
	
	public void jbtnLogin_actionPerformed(ActionEvent e) {
		String username = jtextFieldUserName.getText().trim();
		String password = new String(jpasswordFieldPassword.getPassword());
		Account account = new Account();
		AccountModel accountModel = new AccountModel();
		if(accountModel.login(username, password)) {
			JOptionPane.showMessageDialog(null, "Login Successfully !");
			Map<String, Object> data = new HashMap<String, Object>();
			AccountData.currentAccount = accountModel.find(username);
			data.put("account", accountModel.find(username));
			JMainFrame jframeMain = new JMainFrame(data);
			if(AccountData.currentAccount.isStatus() == true) {
				JFrameChangePassword jFrameChangePassword = new JFrameChangePassword(data);
				jFrameChangePassword.setVisible(true);
				this.dispose();
			}else {
				jframeMain.setVisible(true);
				this.dispose();
			}	
		}else {
			JOptionPane.showMessageDialog(null, "Wrong username or password !");
		}
	}
}
