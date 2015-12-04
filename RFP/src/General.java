import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Dimension;

import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.JTextArea;

public class General {

	private JFrame frame;
	private JTextField searchField;
	private Control c;
	@SuppressWarnings("rawtypes")
	private JList list;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					General window = new General();
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
	public General() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		
		try {
			ObjectInputStream load = new ObjectInputStream(new FileInputStream("default.ser"));
			c = (Control) load.readObject();
			load.close();
//			System.out.println(restore.toString());
		} catch (Exception e) {
			e.printStackTrace();			
		}
		MyHashMap<String,Value> hash = c.fillHashMap();
		String[] cat_name = c.getCategoryName();		
		
		frame = new JFrame("LANGUAGE LIBRARY");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		/*
		 * Set up the menu bar
		 */
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
//		    		  Category cat = c.getCategory(list.getSelectedIndex());
//		    		  textArea.setText("");
//		    		  textArea.append(cat.toString());
//		    		  textArea.setCaretPosition(textArea.getDocument().getLength());
		    	  } else {
		    		  Category cat = c.getCategory(list.getSelectedIndex());
		    		  textArea.setText("");
		    		  textArea.append(cat.toString());		    		  
		    		  textArea.setCaretPosition(0);//set the start point from beginning of the text
		    		  
		    		  //set the start point at the end of the text
//		    		  textArea.setCaretPosition(textArea.getDocument().getLength());
		    	  }
		      }
		});
//		list.setSelectedIndex(0);	//Default is selected the first category	
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
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSearch.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Clause> search_clause = c.search(searchField.getText(), hash);
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

}
