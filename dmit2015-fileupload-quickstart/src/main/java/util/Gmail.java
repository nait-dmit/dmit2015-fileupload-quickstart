package util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class is use to send emails using a Gmail acccount.
 * To send an email using this class create an instance of this class
 * with a Gmail username and password and then call the sendmail()
 * to send the email. For example:
 * 
 * 		Gmail mailer = new Gmail("gmailUserName","gmailPassword");
 *      mailer.sendmail("sendfrom@gmail.com","sendto@gmail.com","Email Subject","Email Message");
 *  
 * @author Sam Wu
 * @version 2007.03.12
 */
public class Gmail 
{
	private String username;
	private String password;
	
	/**
	 * @param username The username of a Gmail account.
	 * @param password The password of the username of the Gmail account.
	 */
	public Gmail(String username, String password) 
	{
		this.username = username;
		this.password = password;
	}

	/**
	 * This method sends the email.
	 * @param from Any email address. Gmail mail server will replaced it with the sender's gmail address.
	 * @param to The mail to email address.
	 * @param subject The subject of the email.
	 * @param message The message of the email.
	 * @throws Exception There was an error communicating with the Gmail server.
	 */
	public void sendmail(
		String from, String to, 
		String subject, String message) throws Exception 
	{
        String host ="smtp.gmail.com";
        
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465"); 
        props.put("mail.smtp.socketFactory.class", 
        		"javax.net.ssl.SSLSocketFactory"); 
        props.put("mail.smtp.socketFactory.fallback", "false"); 

        Authenticator auth = new SmtpAuthenticator(username, password);
        Session session = Session.getInstance(props, auth);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        InternetAddress[] toAddress = InternetAddress.parse(to);
        msg.setRecipients(Message.RecipientType.TO, toAddress);
        msg.setSubject(subject);
        msg.setContent(message,"text/html");
        Transport.send(msg);                     
	}
	
	public Message[] getMessages() throws MessagingException {	
		String host = "imap.gmail.com";

		Properties props = new Properties();
		
		Session session = Session.getDefaultInstance(props, null);

		Store store = session.getStore("imaps");
		store.connect(host, username, password);

		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);

		Message messages[] = folder.getMessages();

		folder.close(false);
		store.close();

		return messages;
	}
}

class SmtpAuthenticator extends Authenticator 
{ 
	private String username;
	private String password;

	public SmtpAuthenticator(String username, String password) 
	{ 
		super(); 
		this.username = username;
		this.password = password;
	} 
	
	protected PasswordAuthentication getPasswordAuthentication() 
	{ 
		return new PasswordAuthentication(username, password); 
	} 
}