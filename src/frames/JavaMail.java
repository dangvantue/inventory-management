package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class JavaMail extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaMail frame = new JavaMail();
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
	public JavaMail() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("From");
		lblNewLabel.setBounds(22, 33, 49, 14);
		contentPane.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(22, 72, 49, 14);
		contentPane.add(lblPassword);

		JLabel lblTo = new JLabel("To:");
		lblTo.setBounds(22, 117, 49, 14);
		contentPane.add(lblTo);

		JLabel lblCc = new JLabel("CC:");
		lblCc.setBounds(22, 166, 49, 14);
		contentPane.add(lblCc);

		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setBounds(22, 211, 49, 14);
		contentPane.add(lblSubject);

		JLabel lblContent = new JLabel("Content");
		lblContent.setBounds(22, 264, 49, 14);
		contentPane.add(lblContent);

		JTextField txtFrom = new JTextField();
		txtFrom.setText("from.c2003l@gmail.com");
		txtFrom.setBounds(151, 30, 321, 20);
		contentPane.add(txtFrom);
		txtFrom.setColumns(10);

		JTextField txtTo = new JTextField();
		txtTo.setText("to.c2003l@gmail.com");
		txtTo.setColumns(10);
		txtTo.setBounds(151, 114, 321, 20);
		contentPane.add(txtTo);

		JTextField txtCC = new JTextField();
		txtCC.setText("Email");
		txtCC.setColumns(10);
		txtCC.setBounds(151, 163, 321, 20);
		contentPane.add(txtCC);

		JTextField txtSub = new JTextField();
		txtSub.setText("Hello");
		txtSub.setColumns(10);
		txtSub.setBounds(151, 208, 321, 20);
		contentPane.add(txtSub);

		JTextArea txtContent = new JTextArea();
		txtContent.setText("Test Mail");
		txtContent.setBounds(151, 265, 321, 53);
		contentPane.add(txtContent);

		JButton txtSend = new JButton("Send");
		txtSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Properties prop = new Properties();
				prop.put("mail.smtp.host", "smtp.gmail.com");
				prop.put("mail.smtp.port", "587");
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.smtp.starttls.enable", "true"); // TLS
				
				

				Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(txtFrom.getText(), new String(txtPass.getPassword()));
					}
				});
				try {
					
					
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(txtFrom.getText()));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(txtTo.getText()));
					message.setSubject(txtSub.getText());
					message.setText(txtContent.getText());

					Transport.send(message);

					JOptionPane.showMessageDialog(rootPane, "Email Send...!");
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(rootPane, e2.getMessage());
					e2.printStackTrace();
				}
			}
		});
		txtSend.setBounds(266, 346, 89, 23);
		contentPane.add(txtSend);

		txtPass = new JPasswordField();
		txtPass.setBounds(151, 69, 321, 17);
		contentPane.add(txtPass);
	}
}
