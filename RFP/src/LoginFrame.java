import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame {

	private JFrame frame;
	private JTextField txtWelcomeToLanguage;
	private JPasswordField passwordField;
	private JTextField txtUserid;
	private JTextField textField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtWelcomeToLanguage = new JTextField();
		txtWelcomeToLanguage.setBounds(0, 0, 450, 28);
		txtWelcomeToLanguage.setText("Welcome To Language Library");
		frame.getContentPane().add(txtWelcomeToLanguage);
		txtWelcomeToLanguage.setColumns(10);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print("THis is the first Step!");
			}
		});
		btnNewButton.setForeground(SystemColor.windowText);
		btnNewButton.setBackground(SystemColor.inactiveCaptionText);
		btnNewButton.setBounds(156, 206, 84, 29);
		frame.getContentPane().add(btnNewButton);
		
		
		textField = new JTextField();
		textField.setBounds(187, 140, 111, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(187, 166, 111, 28);
		frame.getContentPane().add(passwordField_1);
		
		JLabel lblUserid = new JLabel("UserID:");
		lblUserid.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblUserid.setBounds(104, 145, 71, 16);
		frame.getContentPane().add(lblUserid);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblPassword.setBounds(87, 171, 88, 16);
		frame.getContentPane().add(lblPassword);
		
		
	}
}
