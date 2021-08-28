package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import entities.Account;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JFrameEmail extends JFrame {

	private JPanel contentPane;
	private JTextField jtextFieldUsername;
	private JTextField jtextFieldEmail;
	private Map<String, Object> data = new HashMap<String, Object>();
	private JButton jbtnSend;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameEmail frame = new JFrameEmail();
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
	public JFrameEmail() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("SEND TO EMAIL");
		lblNewLabel.setFont(new Font("Bell MT", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(134, 23, 168, 26);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(69, 51, 63, 31);
		contentPane.add(lblNewLabel_1);

		jtextFieldUsername = new JTextField();
		jtextFieldUsername.setBounds(159, 51, 143, 31);
		contentPane.add(jtextFieldUsername);
		jtextFieldUsername.setColumns(10);

		jtextFieldEmail = new JTextField();
		jtextFieldEmail.setColumns(10);
		jtextFieldEmail.setBounds(159, 93, 143, 31);
		contentPane.add(jtextFieldEmail);

		JLabel lblNewLabel_1_1 = new JLabel("Email");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(69, 93, 63, 32);
		contentPane.add(lblNewLabel_1_1);

		jbtnSend = new JButton("Send");
		jbtnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbtnSend_actionPerformed(arg0);
			}
		});
		jbtnSend.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jbtnSend.setBounds(159, 135, 89, 33);
		contentPane.add(jbtnSend);

		JButton jbtnExit = new JButton("");
		jbtnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		jbtnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		jbtnExit.setIcon(new ImageIcon(JFrameEmail.class.getResource("/resources/Exit.png")));
		jbtnExit.setBounds(270, 135, 32, 33);
		contentPane.add(jbtnExit);
	}

	public void jbtnSend_actionPerformed(ActionEvent arg0) {
//		String toEmail = jtextFieldEmail.getText();

		String toEmail = "leco93ktdn@gmail.com";
		String fromEmail = "quickloansystem@gmail.com";
		String fromEmailPassword = "Abcd@1234";
		String subjects = "test";

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587"); // 587 - 465
		// properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//        properties.put("mail.smtp.socketFactory.port", "587");
//        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//        properties.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, fromEmailPassword);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
//            message.setText(jtextFieldEmail.getText());
			message.setSubject(subjects);
			message.setContent("hi sir", "text/plain");
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void loadData() {
		Account account = (Account) data.get("account");
		jtextFieldUsername.setText(account.getUsername());
	}

	public JFrameEmail(Map<String, Object> data) {
		this();
		this.data = data;
		loadData();

	}
}
