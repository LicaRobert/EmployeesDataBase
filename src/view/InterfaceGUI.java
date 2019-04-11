package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import controller.ComboBox;
import controller.DBActionController;
import controller.MenuBarActions;
import controller.PopUpMenuActions;

@SuppressWarnings("unused")
public class InterfaceGUI 
{ 
	public static JFrame frame;
	@SuppressWarnings("rawtypes")
	static Vector m_data 	= 	new Vector();
	@SuppressWarnings("rawtypes")
	static Vector m_column = 	new Vector();

	DBActionController db  =  new DBActionController();
	public static DefaultTableModel model  = new DefaultTableModel(m_data,m_column);
	public static JTable table = new JTable(model); 
	
	public  InterfaceGUI()
	{
		 db.CreateJTableFillDB();
		 
		 m_data	 	= 	db.getData();
		 m_column 	= 	db.getColumn();
		 
		frame = new JFrame(); 
		JPanel panel = new JPanel();
		DefaultTableModel model = new DefaultTableModel(m_data,m_column); 
		table = new JTable(model); 
		table.setDefaultEditor(Object.class, null);

		final JPopupMenu popupmenu = new JPopupMenu();  
		table.setComponentPopupMenu(popupmenu);
		frame.add(popupmenu);
		
	    JMenuItem updatePop	 = new JMenuItem("Update");  
	    JMenuItem deletePop  = new JMenuItem("DeleteAll");
	    JMenuItem insertPop  = new JMenuItem("Insert");
	    
	    popupmenu.add(updatePop);
	    popupmenu.add(new JSeparator());
	    popupmenu.add(deletePop);
	    popupmenu.add(new JSeparator());
	    popupmenu.add(insertPop);
	    
	    JMenuBar menuBar    =  new JMenuBar();
        JMenu menu   	    =  new JMenu("Menu"); 
        JMenu combo         =  new JMenu("ComboBox");
        JMenu change        =  new JMenu("JTree Mode");
        JMenu submenu	    =  new JMenu("Options");
        JMenuItem popMenu	=  new JMenuItem("Populate");
        JMenuItem comboBox	=  new JMenuItem("Open ComboBox");
        JMenuItem jtree     =  new JMenuItem("Open JTree");
        JMenuItem ascMenu	=  new JMenuItem("Order");
        JMenuItem exitMenu  =  new JMenuItem("Exit");
            
        frame.setJMenuBar(menuBar);  
        menuBar.add(menu);
        menuBar.add(combo);
        menuBar.add(change);
        combo.add(comboBox);
        change.add(jtree);
        menu.add(submenu);  
        menu.add(new JSeparator());
        menu.add(exitMenu);
 
        submenu.add(popMenu);
        submenu.add(new JSeparator());
        submenu.add(ascMenu);
        
        comboBox.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e) 
          	{  
         	try
         		{
         		new ComboBox();
         		} 
         	catch (Exception e1) 
         		{
         			e1.printStackTrace();
         		}	 
          	}  
         }); 
        
        ascMenu.addActionListener(new ActionListener()
        {  
         public void actionPerformed(ActionEvent e) 
         	{  
        	 
        	 TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        	 table.setRowSorter(sorter);
        	 List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        	 sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        	 sorter.setSortKeys(sortKeys);
        	 
         	}  
        }); 
  
        exitMenu.addActionListener(new ActionListener()
        {  
         public void actionPerformed(ActionEvent e) 
         	{ 
        	 System.exit(0);
         	}  
        }); 
        
        jtree.addActionListener(new ActionListener()
        {  
         public void actionPerformed(ActionEvent e) 
         	{ 
        	 new TreeInterface();
        	 frame.dispose();
         	}  
        }); 
        
        frame.addMouseListener(new MouseAdapter()
        {  
            public void mousePressed(MouseEvent e) 
            {       	
            	if(e.getButton() == MouseEvent.MOUSE_PRESSED)
            	{
                int x = e.getX();
         	    int y = e.getY();
                popupmenu.show(table, x, y);  
            	}
            }                 
        });  
        
        popMenu.addActionListener(new ActionListener()
        {  
         public void actionPerformed(ActionEvent e) 
         	{
        	  MenuBarActions menu = new MenuBarActions();
        	  menu.Populate();
         	}  
        }); 
    
        updatePop.addActionListener(new ActionListener()
        {  
         public void actionPerformed(ActionEvent e) 
         	{   
         	 PopUpMenuActions pop = new PopUpMenuActions();
         	 pop.PopUpUpdate();
         	}  
        }); 
        
        deletePop.addActionListener(new ActionListener()
        {  
            public void actionPerformed(ActionEvent e)
            {              
            	 PopUpMenuActions pop = new PopUpMenuActions();
            	 pop.PopUpDeleteAll();
            	 DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            	 tableModel.setRowCount(0);
            }  
        });   
        
        insertPop.addActionListener(new ActionListener()
        {  
         public void actionPerformed(ActionEvent e) 
         	{  
        	 PopUpMenuActions pop = new PopUpMenuActions();
        	 pop.PopUpInsert();  
         	}  
        }); 
          
		frame.setSize(465,370);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane jsp = new JScrollPane(table);
		panel.setLayout(new BorderLayout());
		panel.add(jsp,BorderLayout.WEST);
		frame.setContentPane(panel);
		frame.setVisible(true);	
	} 
}