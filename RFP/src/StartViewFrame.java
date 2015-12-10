import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultEditorKit;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class StartViewFrame {
	
	private JFrame userFrame;
	
	private Control RFP;

	private JFrame adminFrame;

	private JPanel categoryPanel;

	private Category cate;

	private JPanel titlePanel;

	private JButton btnRename;

	@SuppressWarnings("rawtypes")
	protected JList categoryList;

	private JButton btnRemoveCategory;

	private JTextArea infoClauseArea;

	private JButton btnModify;

	private JButton btnDel;

	@SuppressWarnings("rawtypes")
	private JList titleList;

	private JScrollPane titleScroll;

	private JTextField searchField;

	private JPasswordField passwordField;

	private MyHashMap<String, Value> hash;

	private JFrame welcomeFrame;

	private JTextArea userClauseArea;
	
	private boolean goSearch = false;
	
	private ArrayList<Value> searchResult;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartViewFrame window = new StartViewFrame();
					window.run();
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
		
	}
	
	/**
	 * Run the application.
	 */
	public void run(){
		RFP = new Control();		
		WelcomeGUI();
	}
	
	/**
	 * Welcome frame
	 */
	private void WelcomeGUI() {
		welcomeFrame = new JFrame("Language Library");
		welcomeFrame.setBounds(100, 100, 600, 300);
		welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		welcomeFrame.setLocationRelativeTo(null);
		welcomeFrame.setResizable(false);
		
		JLabel lblWelcome = new JLabel("WELCOME TO");
		lblWelcome.setBounds(122, 30, 349, 80);
		lblWelcome.setForeground(Color.BLUE);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Ravie", Font.PLAIN, 40));
		
		JLabel lblLanguage = new JLabel("LANGUAGE LIBRARY");
		lblLanguage.setBounds(10, 97, 574, 80);
		lblLanguage.setForeground(Color.BLUE);
		lblLanguage.setHorizontalAlignment(SwingConstants.CENTER);
		lblLanguage.setFont(new Font("Ravie", Font.BOLD, 40));
		
		JLabel label = new JLabel("Design by Macrohard");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Goudy Old Style", Font.ITALIC, 20));
		
		JButton btnLoginAsAdmin = new JButton("Login as Admin");
		btnLoginAsAdmin.setBounds(249, 225, 120, 25);
		btnLoginAsAdmin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLoginAsAdmin.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				passwordGUI();
			}
		});
		btnLoginAsAdmin.setForeground(Color.RED);
		
		JButton btnLoginAsUser = new JButton("Login as User");
		btnLoginAsUser.setBounds(110, 225, 120, 25);
		btnLoginAsUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLoginAsUser.setForeground(Color.RED);
		btnLoginAsUser.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				welcomeFrame.dispose();
				UserGUI();
				userFrame.setVisible(true);
			}
			
		});
		
		JLabel lblNewLabel_1 = new JLabel("Design by Macrohard");
		lblNewLabel_1.setBounds(225, 160, 150, 25);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 12));
		welcomeFrame.getContentPane().setLayout(null);
		welcomeFrame.getContentPane().add(lblWelcome);
		welcomeFrame.getContentPane().add(lblLanguage);
		welcomeFrame.getContentPane().add(lblNewLabel_1);
		welcomeFrame.getContentPane().add(btnLoginAsUser);
		welcomeFrame.getContentPane().add(btnLoginAsAdmin);
		
		JButton btnExit_1 = new JButton("Exit");
		btnExit_1.setBounds(387, 225, 100, 25);
		btnExit_1.setForeground(Color.RED);
		btnExit_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExit_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				welcomeFrame.dispose();
				System.exit(0);
			}
		});
		welcomeFrame.getContentPane().add(btnExit_1);
		welcomeFrame.setVisible(true);
	}
	
	/**
	 * The general user interface.
	 * This interface has the list of category and place to display the clauses in chosen category.
	 * User also can search the clause by keywords.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void UserGUI(){
		try {
			ObjectInputStream load = new ObjectInputStream(new FileInputStream("default.ser"));
			RFP = (Control) load.readObject();
			load.close();
		} catch (Exception e) {
			e.printStackTrace();			
		}
		hash = RFP.fillHashMap();
		
		userFrame = new JFrame("LANGUAGE LIBRARY");
		userFrame.setBounds(100, 100, 800, 600);
		userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userFrame.setLocationRelativeTo(null);
		
		/*
		 * Set up the menu bar
		 */
		JMenuBar menuBar = new JMenuBar();
		userFrame.setJMenuBar(menuBar);		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('f');
		mnFile.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setMnemonic('x');
		mntmExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				userFrame.dispose();
	            System.exit(0);
		      }
		});
		mnFile.add(mntmExit);
		
		JMenu mnAbout = new JMenu("Help");		
		menuBar.add(mnAbout);
		
		JMenuItem about_the_user = new JMenuItem("Get Familiar With It ...");
		about_the_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(userFrame, "To Exit:\n[File] Menu ==> [Exit] or Using [Exit] button at the bottom right corner of the window.\n"
						+ "To View Clauses:\nChoose category on the list of categories provided.\n"
						+ "To Search:\nType in a keywords of what you are looking for, then click [Search] button.\n"
						+ "To Log Out:\nClick on [Log Out] button at the bottom right corner of the window.\n", "Help", JOptionPane.INFORMATION_MESSAGE);
//				JOptionPane.showMessageDialog(userFrame, "To Exit: \nLeft Click on [File] -> [Exit]) or (Left Click on the [Exit] button found at the bottom right corner of the window).\n"
//						+ "To View Clauses \n(Left Click on your prefered category on the list of categories provided).\n"
//						+ "To Search \n(Type in a key/character of what you are looking for, then left click on the [Search] button).\n"
//						+ "The [Search] button can be found at the top right corner of the window.\n"
//						+ "To Log Out \n(Left Click on the [Log Out] button found at the bottom right corner of the window).\n");
//						+ "To learn more about the application, please left click on the [About ...] button found at the top left corner of the window).\n");
			}
		});
		mnAbout.add(about_the_user);
		
		/*
		 * Set up 2 panel for category and clauses
		 */
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.2);
		splitPane.setOneTouchExpandable(true);
		userFrame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		/*
		 * Set up the right split panel to display the category's clause
		 */
		JPanel cl_panel = new JPanel();
		cl_panel.setBorder(BorderFactory.createTitledBorder("Clauses"));	
		cl_panel.setLayout(new GridLayout(1, 1));		
		splitPane.setRightComponent(cl_panel);
		
		userClauseArea = new JTextArea(1,10);
		userClauseArea.setLineWrap(true);			//Making the long line to fit into text area
		userClauseArea.setWrapStyleWord(true);	//by going to new line with the whole last word
		userClauseArea.setEditable(false);	
	    userClauseArea.setComponentPopupMenu(popup());
		
		JScrollPane textPane = new JScrollPane(userClauseArea);
		textPane.setPreferredSize(new Dimension(300,200));
		cl_panel.add(textPane);
		
		/*
		 * Set up the left split panel to display the category
		 */
		JPanel cat_panel = new JPanel();
		cat_panel.setBorder(BorderFactory.createTitledBorder("Category"));		
		cat_panel.setLayout(new GridLayout(1, 1));
		cat_panel.setMinimumSize(new Dimension(100, 50));
		cat_panel.setPreferredSize(new Dimension(100, 110));
		splitPane.setLeftComponent(cat_panel);		
		
		categoryList = new JList(RFP.getCategoryName());
		//can select only 1 category at the time
		categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		categoryList.setLayoutOrientation(JList.VERTICAL);
		categoryList.setVisibleRowCount(-1);
		categoryList.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {		    	  
		    	  if(!e.getValueIsAdjusting()){
		    		  displayClauseOfCategory();
		    	  }
		      }
		});
		categoryList.addKeyListener(new KeyAdapter(){
			  public void keyPressed(KeyEvent key){
				  int index = RFP.getCategoryName().length - 1;
				  if(key.getKeyCode() == KeyEvent.VK_DOWN && categoryList.getSelectedIndex() != index){
					  displayClauseOfCategory();
				  }
			  }
		});
		categoryList.addKeyListener(new KeyAdapter(){
			  public void keyPressed(KeyEvent key){
				  if(key.getKeyCode() == KeyEvent.VK_UP && categoryList.getSelectedIndex() != 0){
					 displayClauseOfCategory();
				  }
			  }
		});
		JScrollPane listPane = new JScrollPane(categoryList);
		cat_panel.add(listPane);
		
		/*
		 * Set up the searching area.
		 */
		JPanel search_panel = new JPanel();
		FlowLayout fl_search_panel = (FlowLayout) search_panel.getLayout();
		fl_search_panel.setAlignment(FlowLayout.RIGHT);
		userFrame.getContentPane().add(search_panel, BorderLayout.NORTH);
		
		//Place to enter the searching string.
		searchField = new JTextField();
		searchField.setHorizontalAlignment(SwingConstants.CENTER);
		search_panel.add(searchField);
		searchField.setColumns(60);
		searchField.addKeyListener(new KeyAdapter(){
			public void keyPressed (KeyEvent key){
				if(key.getKeyCode() == KeyEvent.VK_ENTER)
					userSearchAction();
			}
		});
		searchField.setComponentPopupMenu(popup());
		
		//Search button.
		JButton btnSearch = new JButton("Search");
		btnSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSearch.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent arg0) {
				userSearchAction();
			}			
		});
		search_panel.add(btnSearch);
		
		/*
		 * Set up the panel for some buttons which locate under text area
		 */
		ExitButton(userFrame);
		
		userFrame.setVisible(true);
	}
	
	private void displayClauseOfCategory(){
//		Category cate = RFP.getCategory(categoryList.getSelectedIndex());
		userClauseArea.setText("");
		userClauseArea.append(RFP.getCategory(categoryList.getSelectedIndex()).toString());		    		  
		userClauseArea.setCaretPosition(0);
		searchField.setText("");
	}
	
	private void userSearchAction(){
		if(!searchField.getText().equals("")){
			ArrayList<Clause> search_clause = RFP.getClauseFromSearch(RFP.search(searchField.getText(), hash));
			userClauseArea.setText("");			
			if(search_clause.size() == 0){
				userClauseArea.append("No result found.");
			} else {
				for(int i = 0; i < search_clause.size(); i++){
					userClauseArea.append(search_clause.get(i).toString());
					if(i != search_clause.size()-1)
						userClauseArea.append("\n");
				}
			}
			userClauseArea.setCaretPosition(0);		
		}
		if(!categoryList.isSelectionEmpty())
			categoryList.clearSelection();
	}
	
	/**
	 * The password is required to login as Admin.
	 */
	private void passwordGUI(){
		JFrame frame = new JFrame("Login as Admin");
		frame.setBounds(100, 100, 300, 140);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setBounds(26, 25, 55, 20);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(86, 23, 166, 20);
		passwordField.setColumns(30);
		passwordField.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent key){
				if(key.getKeyCode() == KeyEvent.VK_ENTER){
					checkPass(frame);
				}
			}			
		});
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(45, 65, 89, 23);
		btnLogin.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				checkPass(frame);
			}			
		});		
		frame.getContentPane().add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(160, 65, 89, 23);
		btnCancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnCancel);
		frame.setVisible(true);
	}
	
	/**
	 * Checking password function
	 * @param frame is the frame which will be open if the password is corrected.
	 */
	private void checkPass(JFrame frame) {
		if(RFP.passwordMatch(passwordField.getPassword())){
			AdminGUI();
			frame.dispose();
			welcomeFrame.dispose();					
		} else {
			JOptionPane.showMessageDialog(null, "Passwords do not match !", "Wrong Password",
					JOptionPane.ERROR_MESSAGE);			
		}		
	}
	
	/**
	 * Create an exit button and a log out button.
	 * @param frame is the frame which wants to add those buttons.
	 */
	private void ExitButton(JFrame frame){
		JPanel opt_panel = new JPanel();
		FlowLayout fl_opt_panel = (FlowLayout) opt_panel.getLayout();
		fl_opt_panel.setAlignment(FlowLayout.RIGHT);
		frame.getContentPane().add(opt_panel, BorderLayout.SOUTH);
		
		JButton btnLogout = new JButton("Log out");
		btnLogout.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				WelcomeGUI();
			}
		});
		opt_panel.add(btnLogout);
		
		JLabel lblNewLabel = new JLabel("     ");
		opt_panel.add(lblNewLabel);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setMnemonic('x');
		btnExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
                System.exit(0);
		      }
		});
		opt_panel.add(btnExit);	
	}
	
	/**
	 * The window has many functions such as create, rename, delete category,
	 * and add, modify, and delete clauses 
	 */
	@SuppressWarnings("rawtypes")
	private void AdminGUI(){
		try {
//			String path = this.getClass().
			ObjectInputStream load = new ObjectInputStream(new FileInputStream("default.ser"));
			RFP = (Control) load.readObject();
			load.close();
		} catch (Exception e) {
			e.printStackTrace();			
		}
		hash = RFP.fillHashMap();
		
		adminFrame = new JFrame("LANGUAGE LIBRARY");
		adminFrame.setBounds(100, 100, 800, 600);
		adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminFrame.setLocationRelativeTo(null);
		adminFrame.setVisible(true);
		
		/*
		 * Set up the menu bar
		 */
		JMenuBar menuBar = new JMenuBar();
		adminFrame.setJMenuBar(menuBar);		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('f');
		mnFile.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setMnemonic('x');
		mntmExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				adminFrame.dispose();
                System.exit(0);
		      }
		});
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem receive_help = new JMenuItem("Receive Help");
		receive_help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(adminFrame, "To Exit:\n [File] ==> [Exit] or "
						+ "Click on [Exit] button at the bottom right corner of the window.\n"
						+ "To Log Out:\nClick on the [Log Out] button at the bottom right corner of the window.\n"
						+ "To Search:\n Type in keywords of what you are looking for, then click on the [Search] button.\n"
						+ "To Create a Category:\n Choose [Create Category] button => Enter the name of the category => Click on the [Create] button.\n"
						+ "To Rename a Category:\n Chose the categories which you want to rename => Click on [Rename Category] button.\n"
						+ "=> Enter the new name => click on the [Rename] button.\n"
						+ "To Remove a Category:\n Choose categories you want to remove => Click on [Remove Category] button.\n"						
						+ "To add a clause:\n Click on [Add Clause] button => Choose 'Category' => Enter the 'Clause title' \n"
						+ "=> Enter the 'Clause Information' => click the [Save] button.\n"
						+ "To Modify a Clause:\n Choose a clause which you want to modify => click on [Modify Clause] button\n"
						+ "=> Make the proper changes to that certain clause => click the [Save] button.\n"
						+ "To Delete a Clause:\n Choose the clause which you want to delete => click on [Delete Clause] button.");
//				JOptionPane.showMessageDialog(adminFrame, "To Exit the application (Left Click on [File] -> [Exit]) or \n"
//						+ "(Left Click on the [Exit] button found at the bottom right corner of the window).\n"
//						+ "To View Clauses (Left Click on your prefered category on the list of categories provided).\n"
//						+ "To Search (Type in a key/character of what you are looking for, then left click on the [Search] button).\n"
//						+ "The [Search] button can be found at the top right corner of the window.\n"
//						+ "To Log Out (Left Click on the [Log Out] button found at the bottom right corner of the window).\n"
//						+ "To learn more about how to use the application, please left click on the [Help] button found at the top left corner of the window;\n"
//						+ "you would then left click on the [Receive Help] button under the [Help] button).\n"
//						+ "To create a category (Left Click on the [Create Category] button found at the bottom left corner of the window; \n"
//						+ "you would then enter the name of the category and left click on the [Create] button).\n"
//						+ "To rename a category (Left Click on any of the existing categories found on the list of categories;\n "
//						+ "you would then left click on the [Rename Category] button found at the bottom left corner of the window.\n"
//						+ "After that, enter the new name of the category and left click on the [Rename] button).\n"
//						+ "To remove a category (Left Click on any of the existing categories found on the list of categories;\n"
//						+ "you would then left click on the [Remove Category] button found at the bottom left corner of the window.\n"
//						+ "After that, left click on the [Yes] button).\n"
//						+ "To add a clause (left click on the [Add Clause] button found at the bottom of the window;\n"
//						+ "you would then choose the category in which you want to add a clause in.\n"
//						+ "Make a title for the clause and enter in the proper information.\n"
//						+ "After that, left click the [Save] button).\n"
//						+ "To modify a clause (choose the category in which you want to modify a clause in;\n"
//						+ "you would then left click on the [Modify Clause] button found at the bottom of the window.\n"
//						+ "Make the proper changes to that certain clause.\n"
//						+ "After that, left click the [Save] button).\n"
//						+ "To delete a clause (choose the category in which you want to delete a clause in;\n"
//						+ "you would then left click on the [Delete Clause] button found at the bottom of the window\n"
//						+ "after choosing the clause you wish to delete/remove from that category.\n"
//						+ "Thank You for choosing Macrohard, WE LOVE YOU!\n");
			}
		});
		mnHelp.add(receive_help);
		
		/*
		 * split panel into 2 for category and clauses
		 */
		JSplitPane split_Cate = new JSplitPane();
		split_Cate.setResizeWeight(0.15);
		split_Cate.setOneTouchExpandable(true);
		adminFrame.getContentPane().add(split_Cate, BorderLayout.CENTER);
		
		/*
		 * Set up the left split panel to display the category
		 */
		JPanel cat_panel = new JPanel();
		cat_panel.setBorder(BorderFactory.createTitledBorder("Category"));	
		cat_panel.setLayout(new GridLayout(1, 1));
		split_Cate.setLeftComponent(cat_panel);
		
		//Split panel of category into 2: category list and buttons
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		cat_panel.add(splitPane);
		
		//Initialize category list
		categoryPanel = new JPanel();
		categoryPanel.setLayout(new GridLayout(1, 1));
		splitPane.setLeftComponent(categoryPanel);		
		displayCategoryList();
		
		//Panel holds buttons which relate to category
		JPanel cate_btn = new JPanel();
		cate_btn.setLayout(new GridLayout(3, 1));
		splitPane.setRightComponent(cate_btn);
		
		JButton btnNew = new JButton("Create Category");
		btnNew.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newCategory();
			}			
		});
		cate_btn.add(btnNew);
				
		btnRename = new JButton("Rename Category");
		btnRename.setEnabled(false);
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!categoryList.isSelectionEmpty()){
					renameCategory();
				} else { 
					JOptionPane.showMessageDialog(adminFrame, "Please choose the category !",
							"CAUTION", JOptionPane.INFORMATION_MESSAGE);
				}
			}	
		});
		cate_btn.add(btnRename);
		
		btnRemoveCategory = new JButton("Remove Category");
		btnRemoveCategory.setEnabled(false);
		btnRemoveCategory.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){				
				if(!categoryList.isSelectionEmpty()){
					String name = RFP.getCategoryName()[categoryList.getSelectedIndex()];
					int confirm = JOptionPane.showConfirmDialog(adminFrame, "Do you want to remove " + 
				"'" + name + "' category?", "Delete Category", JOptionPane.YES_NO_OPTION);
					if(confirm == JOptionPane.YES_OPTION){
						RFP.deleteCategory(categoryList.getSelectedIndex());
						categoryPanel.removeAll();
						displayCategoryList();				
						adminFrame.repaint();
						adminFrame.setVisible(true);
						
					}
				}
				RFP.save(RFP, "default.ser");
			}
		});
		cate_btn.add(btnRemoveCategory);

		/*
		 * Set up the right split panel to display the category's clause
		 */
		JPanel cl_panel = new JPanel();
		cl_panel.setBorder(BorderFactory.createTitledBorder("Clauses"));	
		cl_panel.setLayout(new GridLayout(1, 1));		
		split_Cate.setRightComponent(cl_panel);
		
		//Devide clause panel into 2 panels: clause title and clause information
		JSplitPane split_Clause = new JSplitPane();
		split_Clause.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split_Clause.setResizeWeight(0.6);
		split_Clause.setOneTouchExpandable(true);
		cl_panel.add(split_Clause);
		
		//Panel for clause information
		JPanel info_panel = new JPanel();
		info_panel.setLayout(new GridLayout(1, 1));
		split_Clause.setRightComponent(info_panel);
		
		//Split panel for clause information into 2 panels: clause information and buttons
		JSplitPane split_Info = new JSplitPane();
		split_Info.setResizeWeight(1.0);
		split_Info.setOrientation(JSplitPane.VERTICAL_SPLIT);
		info_panel.add(split_Info);
		
		//Panel for clause information which has text area
		JPanel clause_info = new JPanel();
		clause_info.setLayout(new GridLayout(1, 1));
		clause_info.setBorder(BorderFactory.createTitledBorder("Information"));
		split_Info.setLeftComponent(clause_info);
				
		infoClauseArea = new JTextArea(1,10);
		infoClauseArea.setLineWrap(true);
		infoClauseArea.setWrapStyleWord(true);
		infoClauseArea.setEditable(false);				
//	    infoClauseArea.setComponentPopupMenu(popup());
			    
	    JScrollPane textPane = new JScrollPane(infoClauseArea);
	    textPane.setPreferredSize(new Dimension(300,200));
	    clause_info.add(textPane);
	    
	    //Panel holds buttons which relate to clauses
	    JPanel clause_btn = new JPanel();
	    clause_btn.setLayout(new GridLayout(1, 1));
		split_Info.setRightComponent(clause_btn);
				
		JButton btnAdd = new JButton("Add Clause");		
		btnAdd.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				addClause("Add New Clause");
			}			
		});
		clause_btn.add(btnAdd);
		
		btnModify = new JButton("Modify Clause");
		btnModify.setEnabled(false);
		btnModify.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				addClause("Modify Clause");
			}			
		});
		clause_btn.add(btnModify);
				
		btnDel = new JButton("Delete Clause");
		btnDel.setEnabled(false);
		btnDel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!goSearch){
					RFP.getCategory(categoryList.getSelectedIndex()).delete(titleList.getSelectedIndex());
					clauseOfCategory();					
				} else {
					Value choose = searchResult.get(titleList.getSelectedIndex());
					for(int i = 0; i < RFP.getCategoryName().length; i++){
						if(choose.getCategoryName().equals(RFP.getCategoryName()[i])){
							RFP.getCategory(i).delete(choose.getClauseID());
							break;
						}
					}
					hash = RFP.fillHashMap();
					
					displaySearchClauseInAdmin();
					
					btnModify.setEnabled(false);
					btnDel.setEnabled(false);
//					goSearch = true;
				}
				if(titleList.isSelectionEmpty()){
					btnDel.setEnabled(false);
					btnModify.setEnabled(false);
				}
				infoClauseArea.setText("");;
				RFP.save(RFP, "default.ser");
			}			
		});
		clause_btn.add(btnDel);
		
		//Set up the clause title panel
		titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createTitledBorder("Title"));	
		titlePanel.setLayout(new GridLayout(1, 1));
		split_Clause.setLeftComponent(titlePanel);
		
		//Add empty panel for all clause titles of chosen category
		titleList = new JList();		
		titleScroll = new JScrollPane(titleList);
		titlePanel.add(titleScroll);
				
		/*
		 * Set up the searching area
		 */
		JPanel search_panel = new JPanel();
		FlowLayout fl_search_panel = (FlowLayout) search_panel.getLayout();
		fl_search_panel.setAlignment(FlowLayout.RIGHT);
		adminFrame.getContentPane().add(search_panel, BorderLayout.NORTH);
		
		searchField = new JTextField();
		searchField.setHorizontalAlignment(SwingConstants.CENTER);
		searchField.setColumns(60);
		searchField.setComponentPopupMenu(popup());
		searchField.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent key){
				if(key.getKeyCode() == KeyEvent.VK_ENTER){
					displaySearchClauseInAdmin();
					btnModify.setEnabled(false);
					btnDel.setEnabled(false);
					goSearch = true;
				}
			}
		});
		search_panel.add(searchField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSearch.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent arg0) {
				displaySearchClauseInAdmin();
				btnModify.setEnabled(false);
				btnDel.setEnabled(false);
				goSearch = true;
			}		
		});
		search_panel.add(btnSearch);
		
		ExitButton(adminFrame);	
	
		adminFrame.setVisible(true);
	}
	
	private void displaySearchClauseInAdmin() {
		if(!searchField.getText().equals("")){
			searchResult = RFP.search(searchField.getText(), hash);
			ArrayList<Clause> search_clause = RFP.getClauseFromSearch(searchResult);										
			if(search_clause.size() == 0){
				infoClauseArea.setText("");
				infoClauseArea.append("'No result .'");
				searchClause(search_clause);
			} else {
				infoClauseArea.setText("");
				searchClause(search_clause);
			}							
		}
		if(!categoryList.isSelectionEmpty())
			categoryList.clearSelection();		
	}	
	
	/**
	 * Create a list of clauses that match to the string input
	 * @param search_clause is the list of clauses
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void searchClause(ArrayList<Clause> search_clause){
		titlePanel.removeAll();	//remove the old clause's titles
		
		//Get the title of searched clauses
		String[] title = new String[search_clause.size()];
		for(int i = 0; i < search_clause.size(); i++){
			title[i] = search_clause.get(i).getTitle();
		}
		
		titleList = new JList(title);		  
		titleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		titleList.setLayoutOrientation(JList.VERTICAL);
		titleList.setVisibleRowCount(-1);
		titleList.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {		    	  
		    	  if(e.getValueIsAdjusting()){ 		    		  
		    		  int index = titleList.getSelectedIndex();
		    		  infoClauseArea.setText("");
		    		  infoClauseArea.append(search_clause.get(index).getInfo());
		    		  infoClauseArea.setCaretPosition(0);
		    		  
		    		  btnDel.setEnabled(true);
		    		  btnModify.setEnabled(true);
		    	  }
		      }
		});
		
		titleList.addKeyListener(new KeyAdapter(){
			  public void keyPressed(KeyEvent key){
				  int index = titleList.getSelectedIndex();
				  if(key.getKeyCode() == KeyEvent.VK_DOWN && index != title.length - 1){			    	
			    	infoClauseArea.setText("");
		    		infoClauseArea.append(search_clause.get(index+1).getInfo());
		    		infoClauseArea.setCaretPosition(0);
			    }}});
		
		titleList.addKeyListener(new KeyAdapter(){
			  public void keyPressed(KeyEvent key){
				  int index = titleList.getSelectedIndex();
				  if(key.getKeyCode() == KeyEvent.VK_UP && index != 0){			    	
			    	infoClauseArea.setText("");
		    		infoClauseArea.append(search_clause.get(index-1).getInfo());
		    		infoClauseArea.setCaretPosition(0);
			    }}});
		titleScroll = new JScrollPane(titleList);
		titlePanel.add(titleScroll);
		
		adminFrame.repaint();
		adminFrame.setVisible(true);
	}

	/**
	 * Display the category list.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void displayCategoryList() {		
		categoryList = new JList(RFP.getCategoryName());
		categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		categoryList.setLayoutOrientation(JList.VERTICAL);
		categoryList.setVisibleRowCount(-1);
		
		//When you choose the category by clicking on it.
		categoryList.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {		    	  
		    	  if(e.getValueIsAdjusting()){		    		  
		    		  btnRename.setEnabled(true);
		    		  btnRemoveCategory.setEnabled(true);
		    		  
		    		  cate = RFP.getCategory(categoryList.getSelectedIndex());
		    		  clauseOfCategory();
		    		  
		    		  btnModify.setEnabled(false);
		    		  btnDel.setEnabled(false);
		    		  searchField.setText("");
		    		  goSearch = false;
		    	  }
		      }
		});
		
		//Choose the category by using down key
		categoryList.addKeyListener(new KeyAdapter(){
			  public void keyPressed(KeyEvent key){
				  int index = RFP.getCategoryName().length - 1;
				  if(key.getKeyCode() == KeyEvent.VK_DOWN && categoryList.getSelectedIndex() != index){
					  cate = RFP.getCategory(categoryList.getSelectedIndex()+1);
					  clauseOfCategory();
					  
					  btnModify.setEnabled(false);
					  btnDel.setEnabled(false);
					  goSearch = false;
				  }
			  }
		});
		
		//Choose the category by using up key
		categoryList.addKeyListener(new KeyAdapter(){
			  public void keyPressed(KeyEvent key){
				  if(key.getKeyCode() == KeyEvent.VK_UP && categoryList.getSelectedIndex() != 0){
					  cate = RFP.getCategory(categoryList.getSelectedIndex()-1);
					  clauseOfCategory();
					  
					  btnModify.setEnabled(false);
					  btnDel.setEnabled(false);
					  goSearch = false;
				  }
			  }
		});
		//list.setSelectedIndex(0);	//Default is selected the first category	
		JScrollPane listPane = new JScrollPane(categoryList);
		categoryPanel.add(listPane);		
	}

	/**
	 * An add new Category window.
	 */
	private void newCategory() {
		JFrame Cate_frame = new JFrame();
		Cate_frame.setTitle("Create Category");
		Cate_frame.setBounds(100, 100, 450, 175);
		Cate_frame.setResizable(false);
		Cate_frame.setLocationRelativeTo(null);
		Cate_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Cate_frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Enter Category Name");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(23, 40, 130, 25);
		Cate_frame.getContentPane().add(label);
		
		JTextField textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(151, 40, 260, 25);
		Cate_frame.getContentPane().add(textField);
		
		JButton create = new JButton("Create");
		create.setBounds(121, 90, 80, 25);
		create.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().equals("")){
					RFP.addCategory(new Category(textField.getText()));
					categoryPanel.removeAll();
					
					displayCategoryList();				
					
					adminFrame.repaint();
					adminFrame.setVisible(true);
					Cate_frame.dispose();
				} else {
					JOptionPane.showMessageDialog(adminFrame, "Please enter the name of category !",
							"Text field is empty", JOptionPane.INFORMATION_MESSAGE);
				}
				RFP.save(RFP, "default.ser");
			}
			
		});
		Cate_frame.getContentPane().add(create);
		
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(240, 90, 80, 25);
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Cate_frame.dispose();
			}
		});
		Cate_frame.getContentPane().add(cancel);
		Cate_frame.setVisible(true);
	}

	/**
	 * Rename Category Window
	 */
	private void renameCategory() {
		JFrame Cate_frame = new JFrame();
		Cate_frame.setTitle("Rename Category");
		Cate_frame.setBounds(100, 100, 450, 175);
		Cate_frame.setResizable(false);
		Cate_frame.setLocationRelativeTo(null);
		Cate_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Cate_frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Category Name");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(30, 40, 130, 25);
		Cate_frame.getContentPane().add(label);
		
		JTextField textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(151, 40, 260, 25);
		textField.setText(cate.getName());
		Cate_frame.getContentPane().add(textField);
		
		JButton create = new JButton("Rename");
		create.setBounds(110, 90, 90, 25);
		create.addActionListener(new ActionListener(){			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().equals("")){
					String name = textField.getText();
					RFP.getCategory(categoryList.getSelectedIndex()).setName(name);				
					categoryPanel.removeAll();
					
					displayCategoryList();				
					
					adminFrame.repaint();
					adminFrame.setVisible(true);
					Cate_frame.dispose();
					RFP.save(RFP, "default.ser");
				} else {
				JOptionPane.showMessageDialog(adminFrame, "Please enter the name of category !",
						"Text field is empty", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		});
		Cate_frame.getContentPane().add(create);
		
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(250, 90, 80, 25);
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Cate_frame.dispose();
			}
		});
		Cate_frame.getContentPane().add(cancel);
		Cate_frame.setVisible(true);
	}

	/**
	 * 	Create a list of clause's title when click on each categoy	
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void clauseOfCategory(){
		titlePanel.removeAll();		
		
		String[] cl_title = new String[cate.size()];
		for(int i = 0; i < cl_title.length; i++){
			cl_title[i] = cate.getClause(i).getTitle();
		}
		
		titleList = new JList(cl_title);		  
		titleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		titleList.setLayoutOrientation(JList.VERTICAL);
		titleList.setVisibleRowCount(-1);
		titleList.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {		    	  
		    	  if(e.getValueIsAdjusting()){
		    		  btnModify.setEnabled(true);
		    		  btnDel.setEnabled(true);
		    		  
		    		  int index = titleList.getSelectedIndex();
		    		  infoClauseArea.setText("");
		    		  infoClauseArea.append(cate.getClause(index).getInfo());
		    		  infoClauseArea.setCaretPosition(0);		    		  
		    	  }
		      }
		});
		
		titleList.addKeyListener(new KeyAdapter(){
			  public void keyPressed(KeyEvent key){
				  int index = titleList.getSelectedIndex();
				  if(key.getKeyCode() == KeyEvent.VK_DOWN && index != cl_title.length - 1){			    	
			    	infoClauseArea.setText("");
		    		infoClauseArea.append(cate.getClause(index+1).getInfo());
		    		infoClauseArea.setCaretPosition(0);
			    }}});
		
		titleList.addKeyListener(new KeyAdapter(){
			  public void keyPressed(KeyEvent key){
				  int index = titleList.getSelectedIndex();
				  if(key.getKeyCode() == KeyEvent.VK_UP && index != 0){			    	
			    	infoClauseArea.setText("");
		    		infoClauseArea.append(cate.getClause(index-1).getInfo());
		    		infoClauseArea.setCaretPosition(0);
			    }}});
		
		titleScroll = new JScrollPane(titleList);
		titlePanel.add(titleScroll);
		
		adminFrame.repaint();
		adminFrame.setVisible(true);		
	}
	
	/**
	 * Add clause window
	 */
	private void addClause(String frame_name){
		//When we modify the clause which is the resulf of searching.
		Value temp = new Value("", 0);
		int cateIndex = 0;
		if(goSearch){			
			temp = searchResult.get(titleList.getSelectedIndex());
			for(int i = 0; i < RFP.getCategoryName().length; i++){
				if(temp.getCategoryName().equals(RFP.getCategoryName()[i])){
					cateIndex = i;
					break;
				}
			}	
		}
		JFrame addClause = new JFrame(frame_name);
		addClause.setBounds(100, 100, 700, 500);
		addClause.setLocationRelativeTo(null);
		addClause.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.01);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		addClause.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new GridLayout(1,1));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.1);
		splitPane_1.setEnabled(false);
		panel.add(splitPane_1);
		
		JPanel cate_panel = new JPanel();
		cate_panel.setBorder(BorderFactory.createTitledBorder("Category"));
		splitPane_1.setLeftComponent(cate_panel);
		cate_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		/*
		 * Set up list of Category to choose when create a new clause
		 */
		String[] cate_name = new String[RFP.getCategoryName().length+1];
		cate_name[0] = "<Choose Category>";
		for(int i = 1; i < cate_name.length; i++){
			cate_name[i] = RFP.getCategoryName()[i-1];
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox comboBox = new JComboBox(cate_name);
		if(!categoryList.isSelectionEmpty() && !goSearch)
			comboBox.setSelectedIndex(categoryList.getSelectedIndex()+1);
		
		//When we modify the clause which is the resulf of searching. 
		if(goSearch)
			comboBox.setSelectedIndex(cateIndex+1);
		
		if(frame_name.equals("Modify Clause"))
			comboBox.setEnabled(false);
		
		cate_panel.add(comboBox);
		
		JPanel title_panel = new JPanel();
		title_panel.setBorder(BorderFactory.createTitledBorder("Clause Title"));
		title_panel.setLayout(new GridLayout(1,1));
		splitPane_1.setRightComponent(title_panel);
		
		JTextArea clause_title = new JTextArea();
		clause_title.setLineWrap(true);
		clause_title.setWrapStyleWord(true);
		clause_title.setComponentPopupMenu(popup());
		if(frame_name.equals("Modify Clause"))
			clause_title.setText(titleList.getSelectedValue().toString());
		title_panel.add(clause_title);
		
		JPanel info_panel = new JPanel();
		info_panel.setBorder(BorderFactory.createTitledBorder("Clause Information"));
		info_panel.setLayout(new GridLayout(1,1));
		splitPane.setRightComponent(info_panel);
		
		JTextArea clause_info = new JTextArea();
		clause_info.setLineWrap(true);
		clause_info.setWrapStyleWord(true);
		clause_info.setComponentPopupMenu(popup());
		if(frame_name.equals("Modify Clause")){
			if(!goSearch)
				clause_info.setText(cate.getClause(titleList.getSelectedIndex()).getInfo());
			else {
				//When we modify the clause which is the resulf of searching.
				clause_info.setText(RFP.getCategory(cateIndex).getClause(temp.getClauseID()).getInfo());				
			}	
		}					
		info_panel.add(clause_info);
		
		JPanel panel_4 = new JPanel();
		addClause.getContentPane().add(panel_4, BorderLayout.SOUTH);
		
		JButton btnNew = new JButton("Save");
		btnNew.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!comboBox.getSelectedItem().equals("<Choose Category>")){
					if(!clause_title.getText().equals("")){
						if(frame_name.equals("Add New Clause")){
							boolean chosen = categoryList.isSelectionEmpty();
							if(!RFP.getCategory(comboBox.getSelectedIndex()-1).existClauseTitle(clause_title.getText())){
								RFP.getCategory(comboBox.getSelectedIndex()-1).add(
									new Clause(clause_title.getText(),clause_info.getText(),
										RFP.getCategory(comboBox.getSelectedIndex()-1).size()));							
								if(!chosen)	clauseOfCategory();
								addClause.dispose();
							} else {
								JOptionPane.showMessageDialog(null, "The clause title is existed.",
									"Warning", JOptionPane.WARNING_MESSAGE);
							}
						}
						
						if(frame_name.equals("Modify Clause")){							
							int index = titleList.getSelectedIndex();
							
							if(!goSearch){
								RFP.getCategory(comboBox.getSelectedIndex()-1).modifyInformation(
									titleList.getSelectedIndex(), new Clause(clause_title.getText(), 
										clause_info.getText(), RFP.getCategory(comboBox.getSelectedIndex()-1).size()));														
								
								infoClauseArea.setText("");
					    		infoClauseArea.append(cate.getClause(index).getInfo());
					    		infoClauseArea.setCaretPosition(0);
							} else {
								//When we modify the clause which is the resulf of searching.
								Value temp = searchResult.get(index);								
								for(int i = 0; i < RFP.getCategoryName().length; i++){
									if(temp.getCategoryName().equals(RFP.getCategoryName()[i])){
										RFP.getCategory(i).modifyInformation(temp.getClauseID(), new Clause(
											clause_title.getText(), clause_info.getText(), 
												RFP.getCategory(i).size()));
										
										infoClauseArea.setText("");
										infoClauseArea.append(RFP.getCategory(i).getClause(temp.getClauseID()).getInfo());
							    		infoClauseArea.setCaretPosition(0);
										break;
									}
								}								
							}
							addClause.dispose();
						}						
					} else {
						JOptionPane.showMessageDialog(null, "The clause title is empty.",
								"Warning", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Category was not chosen!",
							"Warning", JOptionPane.WARNING_MESSAGE);
				}
				RFP.save(RFP, "default.ser");
				hash = RFP.fillHashMap();
			}	
		});
		panel_4.add(btnNew);
		
		JLabel lbl = new JLabel("          ");
		panel_4.add(lbl);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addClause.dispose();
			}			
		});
		panel_4.add(btnCancel);
		
		addClause.setVisible(true);
	}
	
	/**
	 * Pop-up menu when right click on the text area
	 * This menu has copy, cut, and paste functions
	 * @return pop-up menu
	 */
	public JPopupMenu popup(){
		JPopupMenu popup = new JPopupMenu();	    
	    JMenuItem item = new JMenuItem(new DefaultEditorKit.CutAction());
	    item.setText("Cut");
	    item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
	    popup.add(item);
	    item = new JMenuItem(new DefaultEditorKit.CopyAction());
	    item.setText("Copy");
	    item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
	    popup.add(item);
	    item = new JMenuItem(new DefaultEditorKit.PasteAction());
	    item.setText("Paste");
	    item.setAccelerator(KeyStroke.getKeyStroke("control V"));
	    popup.add(item);	    
	    return popup;
	}
	
}