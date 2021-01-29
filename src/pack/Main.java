package pack;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Main {

  Sender sender;

  private JFrame frmMailsendergmailByEduard;

  private JLabel lblSubject;
  public static JTextField textSubject;

  private JLabel lblText;
  private JScrollPane scrollPane;
  public static JTextArea textText;

  private JLabel lblEmailAddress;
  public static JTextField textEmailAddress;
  private JLabel lblPassword;
  public static JTextField textPassword;
  private JLabel lblToEmailAddress;
  public static JTextField textToEmailAddress;

  private JButton btnInfo;
  public static JButton btnAppend;
  private JButton btnSend;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Sender sender = new Sender();
          Main main = new Main(sender);
          main.frmMailsendergmailByEduard.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public Main(Sender sender) {
    this.sender = sender;
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {

    // Frame
    frmMailsendergmailByEduard = new JFrame();
    frmMailsendergmailByEduard.setResizable(false);
    frmMailsendergmailByEduard.setTitle("GMailSender by Eduard Hermann");
    frmMailsendergmailByEduard.setBounds(100, 100, 600, 400);
    frmMailsendergmailByEduard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frmMailsendergmailByEduard.setLocationRelativeTo(null);
    frmMailsendergmailByEduard.getContentPane().setLayout(null);

    // The field for the subject of the Email
    lblSubject = new JLabel("Subject:");
    lblSubject.setBounds(10, 11, 172, 14);
    frmMailsendergmailByEduard.getContentPane().add(lblSubject);

    textSubject = new JTextField();
    textSubject.setBounds(10, 25, 382, 20);
    frmMailsendergmailByEduard.getContentPane().add(textSubject);
    textSubject.setColumns(10);

    // The Email
    lblText = new JLabel("Text:");
    lblText.setBounds(10, 56, 46, 14);
    frmMailsendergmailByEduard.getContentPane().add(lblText);
    
    textText = new JTextArea();
    scrollPane = new JScrollPane(textText);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setBounds(10, 69, 382, 281);
    frmMailsendergmailByEduard.getContentPane().add(scrollPane);

    // The field for the users Email
    lblEmailAddress = new JLabel("From:");
    lblEmailAddress.setBounds(402, 11, 172, 14);
    frmMailsendergmailByEduard.getContentPane().add(lblEmailAddress);

    textEmailAddress = new JTextField();
    textEmailAddress.setBounds(402, 24, 172, 37);
    frmMailsendergmailByEduard.getContentPane().add(textEmailAddress);
    textEmailAddress.setColumns(10);

    // The field for the users password
    textPassword = new JTextField();
    textPassword.setColumns(10);
    textPassword.setBounds(402, 85, 172, 37);
    frmMailsendergmailByEduard.getContentPane().add(textPassword);

    lblPassword = new JLabel("Password:");
    lblPassword.setBounds(402, 72, 172, 14);
    frmMailsendergmailByEduard.getContentPane().add(lblPassword);

    // The field for the Email of the receiver
    lblToEmailAddress = new JLabel("To:");
    lblToEmailAddress.setBounds(402, 133, 172, 14);
    frmMailsendergmailByEduard.getContentPane().add(lblToEmailAddress);

    textToEmailAddress = new JTextField();
    textToEmailAddress.setColumns(10);
    textToEmailAddress.setBounds(402, 146, 172, 37);
    frmMailsendergmailByEduard.getContentPane().add(textToEmailAddress);

    // Info Button
    btnInfo = new JButton("Info");
    btnInfo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,
            "1) You need to fill in all of the required data in order to send an Email!\n"
                + "2) You are only able to choose one file you want to send within your Email!\n"
                + "3) You are able to overwrite an appended file!\n"
                + "4) You need to make sure that you trust less secure apps on your Google account!\n"
                + "5) Look up the README.md file on my HitHub for further informations!",
            "Info", JOptionPane.INFORMATION_MESSAGE);
      }
    });
    btnInfo.setFont(new Font("Tahoma", Font.PLAIN, 28));
    btnInfo.setBounds(402, 194, 172, 37);
    frmMailsendergmailByEduard.getContentPane().add(btnInfo);

    // Append Button
    btnAppend = new JButton("Append");
    btnAppend.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Sender.appendFile();
      }
    });
    btnAppend.setFont(new Font("Tahoma", Font.PLAIN, 28));
    btnAppend.setBounds(402, 239, 172, 37);
    frmMailsendergmailByEduard.getContentPane().add(btnAppend);

    // Send Button
    btnSend = new JButton("Send");
    btnSend.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Sender.sendMail();
      }
    });
    btnSend.setFont(new Font("Tahoma", Font.PLAIN, 28));
    btnSend.setBounds(402, 287, 172, 63);
    frmMailsendergmailByEduard.getContentPane().add(btnSend);
  }
}
