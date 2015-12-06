import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JPanel;
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
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
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
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultEditorKit;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class StartViewFrame {

	private JFrame welcome_frame;
	
	private JFrame frame;
	
	private Control control;
	
	private JTextArea textArea;
	
	@SuppressWarnings("rawtypes")
	private JList list;
	
	private JTextField searchField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartViewFrame window = new StartViewFrame();
					window.welcome_frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * Create the panel.
	 */
	public StartViewFrame() {
		welcome_GUI();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void welcome_GUI() {
		welcome_frame = new JFrame("Language Library");
		welcome_frame.setBounds(100, 100, 600, 400);
		welcome_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		welcome_frame.setLocationRelativeTo(null);
		welcome_frame.setResizable(false);
		
		JLabel lblWelcome = new JLabel("WELCOME TO");
		lblWelcome.setForeground(Color.BLUE);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Ravie", Font.PLAIN, 40));
		
		JLabel lblLanguage = new JLabel("LANGUAGE LIBRARY");
		lblLanguage.setForeground(Color.BLUE);
		lblLanguage.setHorizontalAlignment(SwingConstants.CENTER);
		lblLanguage.setFont(new Font("Ravie", Font.BOLD, 40));
		
		JLabel label = new JLabel("Design by Macrohard");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Goudy Old Style", Font.ITALIC, 20));
		
		JButton btnLoginAsAdmin = new JButton("Login as Admin");
		btnLoginAsAdmin.setForeground(Color.RED);
		
		JButton btnLoginAsUser = new JButton("Login as User");
		btnLoginAsUser.setForeground(Color.RED);
		btnLoginAsUser.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				welcome_frame.dispose();
				user_GUI();
				frame.setVisible(true);
			}
			
		});
		GroupLayout groupLayout = new GroupLayout(welcome_frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblWelcome, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
					.addGap(14))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLanguage, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(376, Short.MAX_VALUE)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(441)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnLoginAsUser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
						.addComponent(btnLoginAsAdmin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(50))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addComponent(lblWelcome, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLanguage, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(46)
					.addComponent(btnLoginAsAdmin)
					.addGap(30)
					.addComponent(btnLoginAsUser, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(44))
		);
		welcome_frame.getContentPane().setLayout(groupLayout);		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	/**
	 * 
	 */
	private void user_GUI(){		
	/*
	 * Load the data from the "default.ser" file
	 */
	try {
		ObjectInputStream load = new ObjectInputStream(new FileInputStream("default.ser"));
		control = (Control) load.readObject();
		load.close();
//		System.out.println(restore.toString());
	} catch (Exception e) {
		e.printStackTrace();			
	}
	MyHashMap<String,Value> hash = control.fillHashMap();
	String[] cat_name = control.getCategoryName();		
	/*
	 * Create the new window
	 */
	frame = new JFrame("LANGUAGE LIBRARY");
	frame.setBounds(100, 100, 800, 600);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
	
	/*
	 * Set up the menu bar
	 */
	JMenuBar menuBar = new JMenuBar();
	frame.setJMenuBar(menuBar);		
	JMenu mnFile = new JMenu("File");
	mnFile.setMnemonic('f');
	mnFile.setHorizontalAlignment(SwingConstants.CENTER);
	menuBar.add(mnFile);
	
	JMenuItem mntmExit = new JMenuItem("Exit");
	mntmExit.setMnemonic('x');
	mntmExit.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
            System.exit(0);
	      }
	});
	mnFile.add(mntmExit);
	
	JMenu mnAbout = new JMenu("About ...");
	menuBar.add(mnAbout);
	
	/*
	 * Set up 2 panel for category and clauses
	 */
	JSplitPane splitPane = new JSplitPane();
	splitPane.setResizeWeight(0.2);
	splitPane.setOneTouchExpandable(true);
	frame.getContentPane().add(splitPane, BorderLayout.CENTER);
	
	/*
	 * Set up the right split panel to display the category's clause
	 */
	JPanel cl_panel = new JPanel();
	cl_panel.setBorder(BorderFactory.createTitledBorder("Clauses"));	
	cl_panel.setLayout(new GridLayout(1, 1));		
	splitPane.setRightComponent(cl_panel);
	
	textArea = new JTextArea(1,10);
	textArea.setLineWrap(true);			//Making the long line to fit into text area
	textArea.setWrapStyleWord(true);	//by going to new line with the whole last word
	textArea.setEditable(false); 
	
    textArea.setComponentPopupMenu(popup());
	
	JScrollPane textPane = new JScrollPane(textArea);
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
	
	list = new JList(cat_name);
	//can select only 1 category at the time
	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
	list.setLayoutOrientation(JList.VERTICAL);
	list.setVisibleRowCount(-1);
	list.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {		    	  
	    	  if(!e.getValueIsAdjusting()){
//	    		  Category cat = c.getCategory(list.getSelectedIndex());
//	    		  textArea.setText("");
//	    		  textArea.append(cat.toString());
//	    		  textArea.setCaretPosition(textArea.getDocument().getLength());
	    	  } else {
	    		  Category cat = control.getCategory(list.getSelectedIndex());
	    		  textArea.setText("");
	    		  textArea.append(cat.toString());		    		  
	    		  textArea.setCaretPosition(0);//set the start point from beginning of the text
	    		  
	    		  //set the start point at the end of the text
//	    		  textArea.setCaretPosition(textArea.getDocument().getLength());
	    	  }
	      }
	});
//	list.setSelectedIndex(0);	//Default is selected the first category	
	JScrollPane listPane = new JScrollPane(list);
	cat_panel.add(listPane);
	
	/*
	 * Set up the searching area
	 */
	JPanel search_panel = new JPanel();
	FlowLayout fl_search_panel = (FlowLayout) search_panel.getLayout();
	fl_search_panel.setAlignment(FlowLayout.RIGHT);
	frame.getContentPane().add(search_panel, BorderLayout.NORTH);
	
	searchField = new JTextField();
	searchField.setHorizontalAlignment(SwingConstants.CENTER);
	search_panel.add(searchField);
	searchField.setColumns(60);
	searchField.setComponentPopupMenu(popup());
	
	JButton btnSearch = new JButton("Search");
	btnSearch.setHorizontalAlignment(SwingConstants.RIGHT);
	btnSearch.addActionListener(new ActionListener(){			
		public void actionPerformed(ActionEvent arg0) {
			if(!searchField.getText().equals("")){
				ArrayList<Clause> search_clause = control.search(searchField.getText(), hash);
				textArea.setText("");
				list.clearSelection();
				if(search_clause.size() == 0){
					textArea.append("There is no clause.");
				} else {
					for(int i = 0; i < search_clause.size(); i++){
						textArea.append(search_clause.get(i).toString());
						if(i != search_clause.size()-1)
							textArea.append("\n");
					}
				}
				textArea.setCaretPosition(0);		
			}
		}			
	});
	search_panel.add(btnSearch);
	
	/*
	 * Set up the panel for some buttons which locate under text area
	 */
	JPanel opt_panel = new JPanel();
	FlowLayout fl_opt_panel = (FlowLayout) opt_panel.getLayout();
	fl_opt_panel.setAlignment(FlowLayout.RIGHT);
	frame.getContentPane().add(opt_panel, BorderLayout.SOUTH);
	
	JButton btnNewButton_1 = new JButton("Switch User");
	btnNewButton_1.setMnemonic('u');
	opt_panel.add(btnNewButton_1);
	
	JButton btnNewButton = new JButton("Exit");
	btnNewButton.setMnemonic('x');		
	btnNewButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
            System.exit(0);
	      }
	});
	opt_panel.add(btnNewButton);
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