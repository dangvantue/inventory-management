package models;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.Email;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.activation.*;
import javax.mail.internet.*;
import javax.mail.*;
import java.util.*;


public class EmailModel {
	public static void sendMail(String recepient) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
//        properties.put("mail.smtp.ssl.trust", "*");
//        properties.put("mail.transport.protocol", "smtp");
//        properties.put("mail.smtp.starttls.required","true");
//        properties.put("mail.smtp.EnableSSL.enable","true");
//        properties.put("mail.debug", "true");
        String myAccountEmail = "from.c2003l@gmail.com";
        String password = "locdeptrai123";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message = prepareMessage(session, myAccountEmail, recepient);
        Transport.send(message);
        System.out.println("Message sent successfully");
    }
	
	 private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
	        try {
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(myAccountEmail));
	            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
	            message.setSubject("Hello guys");	
	            message.setContent("hello guy", "text/plain");
	            return message;
	        } catch (Exception ex) {
	            Logger.getLogger(EmailModel.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return null;
	    } 

			public static void main(String[] args) throws Exception {
				EmailModel.sendMail("to.c2003l@gmail.com");

			}

		}



