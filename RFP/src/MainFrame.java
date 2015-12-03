import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame {

	private JFrame frame;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
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
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.window);
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Catagory 1");
		chckbxNewCheckBox.setBounds(6, 37, 128, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Catagory 2");
		chckbxNewCheckBox_1.setBounds(6, 72, 128, 23);
		frame.getContentPane().add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Catagory 3");
		chckbxNewCheckBox_2.setBounds(6, 107, 128, 23);
		frame.getContentPane().add(chckbxNewCheckBox_2);
		
		JButton btnAddmodify = new JButton("Add/Modify");
		btnAddmodify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog(btnAddmodify, "Type Catagory Name:"); 
			}
		});
		btnAddmodify.setBounds(6, 443, 117, 29);
		frame.getContentPane().add(btnAddmodify);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(149, 6, 6, 472);
		frame.getContentPane().add(separator);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showInternalOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue)
			}
		});
		btnNewButton.setBounds(149, 443, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modify");
		btnNewButton_1.setBounds(259, 443, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Do you want to delete?", "Delete Confirmation", 
			               JOptionPane.YES_NO_OPTION);
			      if (reply == JOptionPane.YES_OPTION) {
			         JOptionPane.showMessageDialog(null, "Succesfully Deleted!!");
			      } else {
			         frame.disable();
			      }
			}
		});
		btnNewButton_2.setBounds(376, 443, 117, 29);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Exit");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_3.setBounds(490, 443, 117, 29);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Help?");
		btnNewButton_4.setForeground(SystemColor.controlHighlight);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Helloo?");
				
			}
		});
		btnNewButton_4.setBackground(SystemColor.desktop);
		btnNewButton_4.setBounds(587, 6, 63, 29);
		frame.getContentPane().add(btnNewButton_4);
		
		textField = new JTextField();
		textField.setBounds(344, 5, 134, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		JButton btnNewButton_5 = new JButton("Search");
		btnNewButton_5.setBackground(SystemColor.controlHighlight);
		btnNewButton_5.setForeground(SystemColor.desktop);
		btnNewButton_5.setBounds(472, 6, 74, 29);
		frame.getContentPane().add(btnNewButton_5);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		menuBar.add(file);
		
		JMenuItem import1 = new JMenuItem("Import");
		import1.setMnemonic(KeyEvent.VK_I);
		file.add(import1);
		JMenuItem export1 = new JMenuItem("Export");
		export1.setMnemonic(KeyEvent.VK_E);
		file.add(export1);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		file.add(exit);
		
		
		
		
		
		
		
	}
}
