import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class GUI {

	private JFrame frmEaterEggIteration;
	private Egg egg;
	private JLabel team;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmEaterEggIteration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
		egg = new Egg("We Are MACROHARD TEAM");
		egg.setMember("Navy Nguyen");
		egg.setMember("Shelema Bekele");
		egg.setMember("Mubarek Shafi");
		egg.setMember("Ian Henderson");
		egg.setMember("Abdalla Ahmed");
		egg.setMember("Ken Chan");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEaterEggIteration = new JFrame();
		frmEaterEggIteration.setTitle("Eater Egg Iteration");
		frmEaterEggIteration.setBounds(100, 100, 458, 338);
		frmEaterEggIteration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEaterEggIteration.getContentPane().setLayout(null);
		
		JButton btnEaterEgg = new JButton("Eater Egg");
		btnEaterEgg.setBackground(Color.LIGHT_GRAY);
		btnEaterEgg.setForeground(Color.RED);
		btnEaterEgg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEaterEgg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				team.setText(egg.getTeam());
				label.setText(egg.getMember(0));
				label_1.setText(egg.getMember(1));
				label_2.setText(egg.getMember(2));
				label_3.setText(egg.getMember(3));
				label_4.setText(egg.getMember(4));
				label_5.setText(egg.getMember(5));
			}
		});
		btnEaterEgg.setBounds(172, 11, 89, 23);
		frmEaterEggIteration.getContentPane().add(btnEaterEgg);
		
		team = new JLabel("");
		team.setFont(new Font("Tahoma", Font.PLAIN, 12));
		team.setBounds(146, 45, 170, 23);
		frmEaterEggIteration.getContentPane().add(team);
		
		label = new JLabel("");
		label.setBounds(146, 79, 143, 23);
		frmEaterEggIteration.getContentPane().add(label);
		
		label_1 = new JLabel("");
		label_1.setBounds(146, 113, 143, 23);
		frmEaterEggIteration.getContentPane().add(label_1);
		
		label_2 = new JLabel("");
		label_2.setBounds(146, 147, 143, 23);
		frmEaterEggIteration.getContentPane().add(label_2);
		
		label_3 = new JLabel("");
		label_3.setBounds(146, 181, 143, 23);
		frmEaterEggIteration.getContentPane().add(label_3);
		
		label_4 = new JLabel("");
		label_4.setBounds(146, 215, 143, 23);
		frmEaterEggIteration.getContentPane().add(label_4);
		
		label_5 = new JLabel("");
		label_5.setBounds(146, 249, 143, 23);
		frmEaterEggIteration.getContentPane().add(label_5);
	}
}
