import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class StartViewFrame {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartViewFrame window = new StartViewFrame();
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
	public StartViewFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnAdminlogin = new JButton("AdminLogin");
		btnAdminlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showInputDialog(btnAdminlogin, "PassWord:"); 
				
			
			 //mylogin.initialize();
			 //System.out.println("THis is user loginFrame!");
			}
		});
		btnAdminlogin.setForeground(SystemColor.controlHighlight);
		btnAdminlogin.setBackground(SystemColor.inactiveCaptionText);
		btnAdminlogin.setBounds(87, 263, 117, 29);
		frame.getContentPane().add(btnAdminlogin);
		
		JButton btnUserlogin = new JButton("UserLogin");
		btnUserlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUserlogin.setForeground(SystemColor.controlHighlight);
		btnUserlogin.setBackground(SystemColor.windowText);
		btnUserlogin.setBounds(249, 263, 117, 29);
		frame.getContentPane().add(btnUserlogin);
		
		JLabel lblWelcome = new JLabel("WELCOME TO");
		lblWelcome.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		lblWelcome.setBounds(53, 44, 350, 47);
		frame.getContentPane().add(lblWelcome);
		
		JLabel lblNewLabel = new JLabel("LANGUAGE LIBRARY");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 44));
		lblNewLabel.setBounds(53, 122, 455, 94);
		frame.getContentPane().add(lblNewLabel);
		
		
		
	}

}
