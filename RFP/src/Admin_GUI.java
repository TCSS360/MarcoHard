import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultEditorKit;

import java.awt.Dimension;

import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.JTextArea;

public class Admin_GUI {

	private JFrame frame;
	private JTextField searchField;
	private Control RFP;
	@SuppressWarnings("rawtypes")
	private JList list;
	private JList title_list;
	private JTextArea textArea;
	private JScrollPane titlePane;
	private JPanel title_panel;
	protected Category cate;
	private JPanel cate_list;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_GUI window = new Admin_GUI();
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
	public Admin_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		
		try {
			ObjectInputStream load = new ObjectInputStream(new FileInputStream("default.ser"));
			RFP = (Control) load.readObject();
			load.close();
//			System.out.println(restore.toString());
		} catch (Exception e) {
			e.printStackTrace();			
		}
		MyHashMap<String,Value> hash = RFP.fillHashMap();	
		
		frame = new JFrame("LANGUAGE LIBRARY");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
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
		JSplitPane split_Cate = new JSplitPane();
		split_Cate.setResizeWeight(0.15);
		split_Cate.setOneTouchExpandable(true);
		frame.getContentPane().add(split_Cate, BorderLayout.CENTER);
		
		/*
		 * Set up the left split panel to display the category
		 */
		JPanel cat_panel = new JPanel();
		cat_panel.setBorder(BorderFactory.createTitledBorder("Category"));	
		cat_panel.setLayout(new GridLayout(1, 1));
		split_Cate.setLeftComponent(cat_panel);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		cat_panel.add(splitPane);
		
		cate_list = new JPanel();
		cate_list.setLayout(new GridLayout(1, 1));
		splitPane.setLeftComponent(cate_list);
		
		createCategoryList();
//		list = new JList(RFP.getCategoryName());
//		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
//		list.setLayoutOrientation(JList.VERTICAL);
//		list.setVisibleRowCount(-1);
//		list.addListSelectionListener(new ListSelectionListener() {
//		      public void valueChanged(ListSelectionEvent e) {		    	  
//		    	  if(e.getValueIsAdjusting()){		    	 
//		    		  cate = RFP.getCategory(list.getSelectedIndex());
//		    		  createClauseList();
//		    	  }
//		      }
//		});
//		list.addKeyListener(new KeyAdapter(){
//			  public void keyPressed(KeyEvent key){
//				  int index = RFP.getCategoryName().length - 1;
//				  if(key.getKeyCode() == KeyEvent.VK_DOWN && list.getSelectedIndex() != index){
//					  cate = RFP.getCategory(list.getSelectedIndex()+1);
//					  createClauseList();
//				  }
//			  }
//		});
//		list.addKeyListener(new KeyAdapter(){
//			  public void keyPressed(KeyEvent key){
//				  if(key.getKeyCode() == KeyEvent.VK_UP && list.getSelectedIndex() != 0){
//					  cate = RFP.getCategory(list.getSelectedIndex()-1);
//					  createClauseList();
//				  }
//			  }
//		});
//		//list.setSelectedIndex(0);	//Default is selected the first category	
//		JScrollPane listPane = new JScrollPane(list);
//		cate_list.add(listPane);
		
		JPanel cate_btn = new JPanel();
		cate_btn.setLayout(new GridLayout(3, 1));
		splitPane.setRightComponent(cate_btn);
		
		JButton btnNew = new JButton("Create Category");
		btnNew.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				createCategory();
			}
			
		});
		cate_btn.add(btnNew);
				
		JButton btnRename = new JButton("Rename Category");		
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!list.isSelectionEmpty())
					renameCategory();
				else 
					JOptionPane.showMessageDialog(frame, "Please choose the category !");
			}
		});
		cate_btn.add(btnRename);
		
		JButton btnRemoveCategory = new JButton("Remove Category");
		cate_btn.add(btnRemoveCategory);

		/*
		 * Set up the right split panel to display the category's clause
		 */
		JPanel cl_panel = new JPanel();
		cl_panel.setBorder(BorderFactory.createTitledBorder("Clauses"));	
		cl_panel.setLayout(new GridLayout(1, 1));		
		split_Cate.setRightComponent(cl_panel);
		
		//Devide clause panel to 2 panel
		JSplitPane split_Clause = new JSplitPane();
		split_Clause.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split_Clause.setResizeWeight(0.6);
		split_Clause.setOneTouchExpandable(true);
		cl_panel.add(split_Clause);
		JPanel info_panel = new JPanel();
		info_panel.setLayout(new GridLayout(1, 1));
		split_Clause.setRightComponent(info_panel);
				
		JSplitPane split_Info = new JSplitPane();
		split_Info.setResizeWeight(1.0);
		split_Info.setOrientation(JSplitPane.VERTICAL_SPLIT);
		info_panel.add(split_Info);
				
		JPanel clause_info = new JPanel();
		clause_info.setLayout(new GridLayout(1, 1));
		clause_info.setBorder(BorderFactory.createTitledBorder("Information"));
		split_Info.setLeftComponent(clause_info);
				
		textArea = new JTextArea(1,10);
		textArea.setLineWrap(true);			//Making the long line to fit into text area
		textArea.setWrapStyleWord(true);	//by going to new line with the whole last word
		textArea.setEditable(false); 
				
	    textArea.setComponentPopupMenu(popup());
			    
	    JScrollPane textPane = new JScrollPane(textArea);
	    textPane.setPreferredSize(new Dimension(300,200));
	    clause_info.add(textPane);
			    
	    JPanel clause_btn = new JPanel();
	    clause_btn.setLayout(new GridLayout(1, 1));
		split_Info.setRightComponent(clause_btn);
				
		JButton btnAdd = new JButton("Add Clause");
		clause_btn.add(btnAdd);
		
		JButton btnModify = new JButton("Modify Clause");
		clause_btn.add(btnModify);
				
		JButton btnDel = new JButton("Delete Clause");
		clause_btn.add(btnDel);
		
		//Set up the clause title panel
		title_panel = new JPanel();
		title_panel.setBorder(BorderFactory.createTitledBorder("Title"));	
		title_panel.setLayout(new GridLayout(1, 1));
		split_Clause.setLeftComponent(title_panel);
		
		//Add empty panel
		title_list = new JList();		
		titlePane = new JScrollPane(title_list);
		title_panel.add(titlePane);
				
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
					ArrayList<Clause> search_clause = RFP.getClauseFromSearch(RFP.search(searchField.getText(), hash));
										
					if(search_clause.size() == 0){
						textArea.append("There is no clause.");
					} else {						
						clauseList(search_clause);
					}							
				}
			}			
		});
		search_panel.add(btnSearch);
		
		/*
		 * Set up the panel for some buttons which locate under text area
		 */
		JPanel opt_panel = new JPanel();
//		opt_panel.setLayout(new GridLayout(1, 1));
		FlowLayout fl_opt_panel = (FlowLayout) opt_panel.getLayout();
		fl_opt_panel.setAlignment(FlowLayout.RIGHT);
		frame.getContentPane().add(opt_panel, BorderLayout.SOUTH);
		
		JLabel space_5 = new JLabel("            ");
		opt_panel.add(space_5);
		
		JPanel btn_exit = new JPanel();
//		FlowLayout fl_btn_exit = (FlowLayout) btn_exit.getLayout();
		opt_panel.add(btn_exit);
		
		JButton btnSwitch = new JButton("Switch User");
		btnSwitch.setMnemonic('u');
		btn_exit.add(btnSwitch);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setMnemonic('x');
		btnExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
                System.exit(0);
		      }
		});
		
		JLabel lblNewLabel = new JLabel("     ");
		btn_exit.add(lblNewLabel);
		btn_exit.add(btnExit);
	}
	
	private void createCategoryList() {		
		list = new JList(RFP.getCategoryName());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		list.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {		    	  
		    	  if(e.getValueIsAdjusting()){		    	 
		    		  cate = RFP.getCategory(list.getSelectedIndex());
		    		  createClauseList();
		    	  }
		      }
		});
		list.addKeyListener(new KeyAdapter(){
			  public void keyPressed(KeyEvent key){
				  int index = RFP.getCategoryName().length - 1;
				  if(key.getKeyCode() == KeyEvent.VK_DOWN && list.getSelectedIndex() != index){
					  cate = RFP.getCategory(list.getSelectedIndex()+1);
					  createClauseList();
				  }
			  }
		});
		list.addKeyListener(new KeyAdapter(){
			  public void keyPressed(KeyEvent key){
				  if(key.getKeyCode() == KeyEvent.VK_UP && list.getSelectedIndex() != 0){
					  cate = RFP.getCategory(list.getSelectedIndex()-1);
					  createClauseList();
				  }
			  }
		});
		//list.setSelectedIndex(0);	//Default is selected the first category	
		JScrollPane listPane = new JScrollPane(list);
		cate_list.add(listPane);		
	}

	/**
	 * 	Create a list of clause's title when click on each categoy	
	 */
	private void createClauseList(){
		title_panel.removeAll();		
		
		String[] cl_title = new String[cate.size()];
		for(int i = 0; i < cl_title.length; i++){
			cl_title[i] = cate.getClause(i).getTitle();
		}
		
		title_list = new JList(cl_title);		  
		title_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		title_list.setLayoutOrientation(JList.VERTICAL);
		title_list.setVisibleRowCount(-1);
		title_list.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {		    	  
		    	  if(e.getValueIsAdjusting()){ 		    		  
		    		  int index = title_list.getSelectedIndex();
		    		  textArea.setText("");
		    		  textArea.append(cate.getClause(index).getInfo());
		    		  textArea.setCaretPosition(0);
		    	  }
		      }
		});
		title_list.addKeyListener(new KeyAdapter(){
			  public void keyPressed(KeyEvent key){
				  int index = title_list.getSelectedIndex();
				  if(key.getKeyCode() == KeyEvent.VK_DOWN && index != cl_title.length - 1){			    	
			    	textArea.setText("");
		    		textArea.append(cate.getClause(index+1).getInfo());
		    		textArea.setCaretPosition(0);
			    }}});
		title_list.addKeyListener(new KeyAdapter(){
			  public void keyPressed(KeyEvent key){
				  int index = title_list.getSelectedIndex();
				  if(key.getKeyCode() == KeyEvent.VK_UP && index != 0){			    	
			    	textArea.setText("");
		    		textArea.append(cate.getClause(index-1).getInfo());
		    		textArea.setCaretPosition(0);
			    }}});
		titlePane = new JScrollPane(title_list);
		title_panel.add(titlePane);
		frame.repaint();
		frame.setVisible(true);		
	}
	
	/**
	 * Create a list of clauses that match to the string input
	 * @param search_clause is the list of clauses
	 */
	private void clauseList(ArrayList<Clause> search_clause){
		title_panel.removeAll();
		
		String[] title = new String[search_clause.size()];
		for(int i = 0; i < search_clause.size(); i++){
			title[i] = search_clause.get(i).getTitle();
		}
		
		title_list = new JList(title);		  
		title_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		title_list.setLayoutOrientation(JList.VERTICAL);
		title_list.setVisibleRowCount(-1);
		title_list.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {		    	  
		    	  if(e.getValueIsAdjusting()){ 		    		  
		    		  int index = title_list.getSelectedIndex();
		    		  textArea.setText("");
		    		  textArea.append(search_clause.get(index).getInfo());
		    		  textArea.setCaretPosition(0);
		    	  }
		      }
		});
		title_list.addKeyListener(new KeyAdapter(){
			  public void keyPressed(KeyEvent key){
				  int index = title_list.getSelectedIndex();
				  if(key.getKeyCode() == KeyEvent.VK_DOWN && index != title.length - 1){			    	
			    	textArea.setText("");
		    		textArea.append(search_clause.get(index+1).getInfo());
		    		textArea.setCaretPosition(0);
			    }}});
		title_list.addKeyListener(new KeyAdapter(){
			  public void keyPressed(KeyEvent key){
				  int index = title_list.getSelectedIndex();
				  if(key.getKeyCode() == KeyEvent.VK_UP && index != 0){			    	
			    	textArea.setText("");
		    		textArea.append(search_clause.get(index-1).getInfo());
		    		textArea.setCaretPosition(0);
			    }}});
		titlePane = new JScrollPane(title_list);
		title_panel.add(titlePane);
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
	/**
	 * A creating Category window
	 */
	private void createCategory() {
		JFrame Cate_frame = new JFrame();
		Cate_frame.setTitle("Create Category");
		Cate_frame.setBounds(100, 100, 450, 175);
		Cate_frame.setResizable(false);
		Cate_frame.setLocationRelativeTo(null);
		Cate_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				RFP.addCategory(new Category(textField.getText()));
				cate_list.removeAll();
				createCategoryList();				
				frame.repaint();
				frame.setVisible(true);
				Cate_frame.dispose();				
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
		Cate_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				String name = textField.getText();
				RFP.getCategory(list.getSelectedIndex()).setName(name);				
				cate_list.removeAll();
				createCategoryList();				
				frame.repaint();
				frame.setVisible(true);
				Cate_frame.dispose();				
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
}
