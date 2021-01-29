package pack;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Sender {

  private static String SubjectEmail;
  private static String TextEmail;

  private static String EmailAddress;
  private static String Password;
  private static String ToEmailAddress;

  private static File AppendFile;

  /**
   * Opens a menu where the user is able to choose one file which will be send within the Email.
   */
  public static void appendFile() {
    try {
      Main.btnAppend.setText("Choosing...");
      JFileChooser chooser = new JFileChooser();
      chooser.setDialogTitle("Choose the one file you want to add to your Email");
      chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      // Opens up the chooser and return the integer value 0 if you have choosen one file
      int result = chooser.showOpenDialog(Main.btnAppend);
      // "JFileChooser.APPROVE_OPTION" is a predefined Integer with the value 0
      if (result == JFileChooser.APPROVE_OPTION) {
        AppendFile = chooser.getSelectedFile();
      }
      if (AppendFile != null) {
        Main.btnAppend.setText("Got file!");
      } else {
        Main.btnAppend.setText("Append");
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("File couldn't get added to your Email!");
    }
  }

  /**
   * Sends an Email.
   */
  public static void sendMail() {
    try {
      getGivenData();

      // The requirements that you need to log into your GMail account are described here
      // The meaning of "smpt" is "Simple Mail Transfer Protocol".
      Properties properties = new Properties();
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.host", "smtp.gmail.com");
      properties.put("mail.smtp.port", "587");

      // Checks if the given data is valid or not.
      Session session = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(EmailAddress, Password);
        }
      });

      // The message is getting created with all the given data.
      // The message is getting send afterwards.
      if (AppendFile == null) {
        Message message = prepareMessage(session, EmailAddress, ToEmailAddress, null);
        Transport.send(message);
      } else {
        Message message = prepareMessage(session, EmailAddress, ToEmailAddress, AppendFile);
        Transport.send(message);
      }
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Check if all your given data if correct!", "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Here is a general konfiguration of an Email. "MimeMessage" represents the Email which will be
   * send.
   */
  public static Message prepareMessage(Session session, String Email, String recepient, File file) {
    try {
      // The given Email address is getting converted into an "InternetAddress" in order to make it
      // usable.
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(Email));
      // The given recepient is getting used as the primary recepient.
      message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
      message.setSubject(SubjectEmail);
      // Splitting up the Email to two different part in order to attach a file.
      try {
        MimeMultipart mimeMultipart = new MimeMultipart();
        // Text part
        MimeBodyPart mimeBodyPart1 = new MimeBodyPart();
        // Setting the Text
        mimeBodyPart1.setText(TextEmail);
        // Adding the text part to the main part
        mimeMultipart.addBodyPart(mimeBodyPart1);
        // Attached File part
        if (AppendFile != null) {
          // File part
          MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
          // Adding file
          mimeBodyPart2.attachFile(AppendFile);
          // Adding the file part to the main part
          mimeMultipart.addBodyPart(mimeBodyPart2);
        }
        // Adding all of the parts to the message.
        message.setContent(mimeMultipart);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return message;
    } catch (MessagingException e) {
      e.printStackTrace();
      System.out.println("Message couldn't get created!");
    }
    return null;
  }

  /**
   * Collects all of the given data.
   */
  public static void getGivenData() {
    SubjectEmail = Main.textSubject.getText();
    TextEmail = Main.textText.getText();
    EmailAddress = Main.textEmailAddress.getText();
    Password = Main.textPassword.getText();
    ToEmailAddress = Main.textToEmailAddress.getText();
  }
}
