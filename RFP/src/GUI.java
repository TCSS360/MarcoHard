import javax.swing.JFrame;  
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GUI {
	
//	public static void main(String[] args) {
//		new GUI ();
//	}
	
	public GUI () {
		JFrame GUIFrame = new JFrame();
		
		GUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		GUIFrame.setTitle("Language Library Application Ver 1.0"); 
		GUIFrame.setSize(600,300);
		
		JMenuBar menuBar = new JMenuBar();
		GUIFrame.setJMenuBar(menuBar);
		
		JMenu login = new JMenu("Login");
		login.setMnemonic(KeyEvent.VK_L);
		menuBar.add(login);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic(KeyEvent.VK_F);
		menuBar.add(mnFile);
		
		JMenu categ = new JMenu("Category");
		categ.setMnemonic(KeyEvent.VK_C);
		menuBar.add(categ);
		
		JMenu add_data = new JMenu("Add Data");
		add_data.setMnemonic(KeyEvent.VK_A);
		menuBar.add(add_data);
		
		JMenu modify_data = new JMenu("Modify Data");
		modify_data.setMnemonic(KeyEvent.VK_M);
		menuBar.add(modify_data);
		
		JMenu delete_data = new JMenu("Delete Data");
		delete_data.setMnemonic(KeyEvent.VK_D);
		menuBar.add(delete_data);
		
		JMenu searchbar = new JMenu("Search");
		searchbar.setMnemonic(KeyEvent.VK_S);
		menuBar.add(searchbar);
		
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		menuBar.add(help);
		
		JMenuItem mlogin = new JMenuItem("Log In Page");
		login.add(mlogin);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIFrame.dispose();
			}
		});
		mnFile.add(mntmExit);
		
		JMenuItem categ1 = new JMenuItem("Category 1");
		categ.add(categ1);
		
		JMenuItem categ2 = new JMenuItem("Category 2");
		categ.add(categ2);
		
		JMenuItem categ3 = new JMenuItem("Category 3");
		categ.add(categ3);
		
		JMenuItem create_categ = new JMenuItem("Create Category");
		categ.add(create_categ);
		
		JMenuItem rename_categ = new JMenuItem("Rename Category");
		categ.add(rename_categ);
		
		JMenuItem add_info = new JMenuItem("Add");
		add_data.add(add_info);
		
		JMenuItem modify_info = new JMenuItem("Modify/Edit");
		modify_data.add(modify_info);
		
		JMenuItem delete_info = new JMenuItem("Delete/Remove");
		delete_data.add(delete_info);
		
		JMenuItem search_information = new JMenuItem("Search Information");
		searchbar.add(search_information);
		
		JMenuItem receive_help = new JMenuItem("Receive Help");
		receive_help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(GUIFrame, "Help is Here!!!");
			}
		});
		help.add(receive_help);
		
		GUIFrame.setVisible(true);
	}
}