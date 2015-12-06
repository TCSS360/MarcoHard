import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultEditorKit;

import java.awt.Dimension;

import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class Admin_GUI {

	private JFrame frame;
	private JTextField searchField;
	private Control RFP;
	@SuppressWarnings("rawtypes")
	private JList cate_list;
	private JList<CheckBoxList> clause_Area;
	private JList<CheckBoxList> cl_List;
	private JPanel cat_panel;
	private JPanel cl_panel;
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
		String[] cat_name = RFP.getCategoryName();		
		
		frame = new JFrame("LANGUAGE LIBRARY");
		frame.setBounds(100, 100, 1000, 600);
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
		
		JMenu mnAbout = new JMenu("Help");
		menuBar.add(mnAbout);
		
		/*
		 * Set up 2 panel for category and clauses
		 */
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.1);
		splitPane.setOneTouchExpandable(true);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		/*
		 * Set up the right split panel to display the category's clause
		 */
		cl_panel = new JPanel();
		cl_panel.setBorder(BorderFactory.createTitledBorder("Clauses"));	
		cl_panel.setLayout(new GridLayout(1, 1));		
		splitPane.setRightComponent(cl_panel);
		
		clause_Area = new JList<CheckBoxList>();
		JScrollPane clScroll = new JScrollPane(clause_Area);
		cl_panel.add(clScroll);
		
		/*
		 * Set up the left split panel to display the category
		 */
		cat_panel = new JPanel();
		cat_panel.setBorder(BorderFactory.createTitledBorder("Category"));		
		cat_panel.setLayout(new GridLayout(1, 1));
		cat_panel.setMinimumSize(new Dimension(100, 50));
		cat_panel.setPreferredSize(new Dimension(100, 110));
		splitPane.setLeftComponent(cat_panel);
		
		cate_list = new JList(cat_name);
		cate_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		cate_list.setLayoutOrientation(JList.VERTICAL);
		cate_list.setVisibleRowCount(-1);
		cate_list.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {		    	  
		    	  if(!e.getValueIsAdjusting()){	
		    		 
		    	  } else {	  
		    		  createCheckBox();
		    		  
		    		  frame.repaint();		    		  
		    		  frame.setVisible(true);
		    	  }
		    	  
		    	  
		      }
		});		
//		cate_list.setSelectedIndex(0);	//Default is selected the first category	
		JScrollPane listPane = new JScrollPane(cate_list);
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
//				if(!searchField.getText().equals("")){
//					ArrayList<Clause> search_clause = RFP.search(searchField.getText(), hash);
//					textArea.setText("");
//					cate_list.clearSelection();
//					if(search_clause.size() == 0){
//						textArea.append("There is no clause.");
//					} else {
//						for(int i = 0; i < search_clause.size(); i++){
//							textArea.append(search_clause.get(i).toString());
//							if(i != search_clause.size()-1)
//								textArea.append("\n");
//						}
//					}
//					textArea.setCaretPosition(0);		
//				}
			}			
		});
		search_panel.add(btnSearch);
		
		/*
		 * Set up the panel for some buttons which locate under text area
		 */
		JPanel opt_panel = new JPanel();
//		opt_panel.setLayout(new GridLayout(1, 1));
		FlowLayout fl_opt_panel = (FlowLayout) opt_panel.getLayout();
		fl_opt_panel.setHgap(10);
		fl_opt_panel.setVgap(10);
		frame.getContentPane().add(opt_panel, BorderLayout.SOUTH);
		
		JSplitPane splitPane_1 = new JSplitPane();
		opt_panel.add(splitPane_1);
		
		JPanel cate_btn = new JPanel();
		splitPane_1.setLeftComponent(cate_btn);
		
		JLabel space = new JLabel("     ");
		cate_btn.add(space);
		
		JButton btnNew = new JButton("Create Category");
		cate_btn.add(btnNew);
		
		JButton btnRename = new JButton("Rename Category");
		cate_btn.add(btnRename);
		
		JLabel space_1 = new JLabel("     ");
		cate_btn.add(space_1);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_1.setRightComponent(splitPane_2);
		
		JPanel clause_btn = new JPanel();
		splitPane_2.setLeftComponent(clause_btn);
		
		JLabel label = new JLabel("     ");
		clause_btn.add(label);
		
		JButton btnAdd = new JButton("Add Clause");
		clause_btn.add(btnAdd);
		
		JButton btnModify = new JButton("Modify Clause");
		clause_btn.add(btnModify);
		
		JLabel label_1 = new JLabel("     ");
		clause_btn.add(label_1);
		
		JPanel delete_btn = new JPanel();
		splitPane_2.setRightComponent(delete_btn);
		
		JLabel label_2 = new JLabel("     ");
		delete_btn.add(label_2);
		
		JButton btnDel = new JButton("Delete");
		delete_btn.add(btnDel);
		
		JLabel label_3 = new JLabel("     ");
		delete_btn.add(label_3);
		
		JLabel space_5 = new JLabel("            ");
		opt_panel.add(space_5);
		
		JPanel btn_exit = new JPanel();
		FlowLayout fl_btn_exit = (FlowLayout) btn_exit.getLayout();
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
		btn_exit.add(btnExit);
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
	private void createCheckBox(){		
		cl_panel.removeAll();
		Category cat = RFP.getCategory(cate_list.getSelectedIndex());
		
		/*
		 *	Create a check list for category
		 */
		CheckBoxList[] clause = new CheckBoxList[cat.size()];
		for(int i = 0; i < cat.size(); i++){
			clause[i] = new CheckBoxList(cat.getClause(i).toString());
		}
		
		clause_Area = new JList<CheckBoxList>(clause);
		clause_Area.setCellRenderer(new CheckBoxListRenderer());
		clause_Area.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		clause_Area.setLayoutOrientation(JList.VERTICAL);
		clause_Area.setVisibleRowCount(-1);	
		clause_Area.setPreferredSize(new Dimension(300, 300));
		
		clause_Area.addMouseListener(new MouseAdapter(){
	         public void mouseClicked(MouseEvent event){
	        	 JList<CheckBoxList> list = (JList<CheckBoxList>) event.getSource();	        	
	            int index = list.locationToIndex(event.getPoint());
	            CheckBoxList item = (CheckBoxList) list.getModel().getElementAt(index);
	 
	            // Toggle selected state
	            item.setSelected(!item.isSelected());
	 
	            // Repaint cell
	            list.repaint(list.getCellBounds(index, index));
	         }
	      });
		JScrollPane clScroll = new JScrollPane(clause_Area);
		cl_panel.add(clScroll);		
		cl_panel.repaint();
		
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
	 * Represents items in the list that can be selected	 
	 */
	private class CheckBoxList{
		private String label;
		private boolean isSelected = false;
		
		public CheckBoxList(String label) {
			this.label = label;
		}
		 
		public boolean isSelected() {
		    return isSelected;
		}
		 
		public void setSelected(boolean isSelected) {
		    this.isSelected = isSelected;
		}
		 
		public String toString() {
		   return label;
		}
	}
		 
	/**
	 *  Handles rendering cells in the list using a check box		 
	*/
	private class CheckBoxListRenderer extends JCheckBox implements
		ListCellRenderer<CheckBoxList> {
		 
	@Override
	public Component getListCellRendererComponent(
			JList<? extends CheckBoxList> list, CheckBoxList value,
		         int index, boolean isSelected, boolean cellHasFocus) {
		setEnabled(list.isEnabled());
		setSelected(value.isSelected());
		setFont(list.getFont());
		setBackground(list.getBackground());
		setForeground(list.getForeground());
		setText(value.toString());
		return this;
		}
	}
}
