package  view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.ComboBoxTree;
import controller.MenuBarActionsTree;
import controller.PopUpMenuActionsTree;

public class TreeInterface 
{
	
	public static JTree tree = new JTree();
	Connection conn;
	Statement stmt;
	public static ResultSet rs;
	public static DefaultMutableTreeNode id = new DefaultMutableTreeNode();
	public static DefaultMutableTreeNode idTree = new DefaultMutableTreeNode();
	public static JFrame frameTree = new JFrame();
	JPanel panel = new JPanel();

	public TreeInterface()
	{
		
	final JPopupMenu popupmenu = new JPopupMenu();  
	tree.setComponentPopupMenu(popupmenu);
	frameTree.add(popupmenu);
		
	JMenuItem updatePop	 = new JMenuItem("Update");  
	JMenuItem deletePop  = new JMenuItem("DeleteAll");
	JMenuItem comboBox   = new JMenuItem("ComboBox");
	JMenuItem insertPop  = new JMenuItem("Insert");
	
	popupmenu.add(updatePop);
	popupmenu.add(new JSeparator());
    popupmenu.add(deletePop);
	popupmenu.add(new JSeparator());
    popupmenu.add(comboBox);
	popupmenu.add(new JSeparator());
	popupmenu.add(insertPop);
	
	JMenuBar menuBar    =  new JMenuBar();
    JMenu menu   	    =  new JMenu("Menu");
    JMenu change        =  new JMenu("JTable mode");
    JMenu submenu	    =  new JMenu("Options");
    JMenuItem popMenu	=  new JMenuItem("Populate");
    JMenuItem jtable     =  new JMenuItem("Open JTable");
    JMenuItem exitMenu  =  new JMenuItem("Exit");
    
    frameTree.setJMenuBar(menuBar);  
    menuBar.add(menu);
    menuBar.add(change);
    menu.add(submenu);  
    change.add(jtable);
    menu.add(new JSeparator());
    menu.add(exitMenu);
    submenu.add(popMenu);
	    
	   deletePop.addActionListener(new ActionListener()
       {  
           public void actionPerformed(ActionEvent e)
           {              
        	 PopUpMenuActionsTree pop = new PopUpMenuActionsTree();
          	 pop.PopUpDeleteAll();
        	 tree.setModel(null);
           }  
       });   
	   
	   updatePop.addActionListener(new ActionListener()
       {  
           public void actionPerformed(ActionEvent e)
           {              
        	 PopUpMenuActionsTree pop = new PopUpMenuActionsTree();
          	 pop.PopUpUpdate();
           }  
       }); 
	   
	   insertPop.addActionListener(new ActionListener()
       {  
           public void actionPerformed(ActionEvent e)
           {              
        	 PopUpMenuActionsTree pop = new PopUpMenuActionsTree();
          	 pop.PopUpInsert();
           }  
       }); 
	
	
	   comboBox.addActionListener(new ActionListener()
       {  
           public void actionPerformed(ActionEvent e)
           {              
            try 
       		{
             new ComboBoxTree();
       		} 
       		catch (Exception e1)
       			{
       			e1.printStackTrace();
       			}
           }  
       });   
	
	   exitMenu.addActionListener(new ActionListener()
       {  
        public void actionPerformed(ActionEvent e) 
        	{ 
       	 System.exit(0);
        	}  
       }); 
	   
	   
       jtable.addActionListener(new ActionListener()
       {  
        public void actionPerformed(ActionEvent e) 
        	{ 
       	 new InterfaceGUI();
       	 frameTree.dispose();
        	}  
       }); 
	   
	   popMenu.addActionListener(new ActionListener()
       {  
        public void actionPerformed(ActionEvent e) 
        	{
       	  MenuBarActionsTree menu = new MenuBarActionsTree();
       	  menu.Populate();
        	}  
       }); 
 
	DefaultMutableTreeNode id = new DefaultMutableTreeNode("ID Employee");	
	try
	{
	Class.forName("org.mariadb.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/angajati","root","ProElite");
	stmt = conn.createStatement();
	rs = stmt.executeQuery("SELECT id,name,age,gender from angajati");
	
	while(rs.next())
		{
		DefaultMutableTreeNode idTree = new DefaultMutableTreeNode(id + " : " + rs.getInt("ID"));
		id.add(idTree);
		DefaultMutableTreeNode nameTree = new DefaultMutableTreeNode("Name : " + rs.getString("Name"));
		idTree.add(nameTree);
		DefaultMutableTreeNode ageTree = new DefaultMutableTreeNode("Age : " + rs.getInt("Age"));
		idTree.add(ageTree);
		DefaultMutableTreeNode genderTree = new DefaultMutableTreeNode("Gender : "  + rs.getString("Gender"));
		idTree.add(genderTree);
		}
	
	}
	catch(Exception e)
		{
			e.printStackTrace();
		}
	
	tree = new JTree(id);
	frameTree.setSize(400,450);
	frameTree.setLocationRelativeTo(null);
	frameTree.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JScrollPane jsp = new JScrollPane(tree);
	panel.setLayout(new BorderLayout());
	tree.setComponentPopupMenu(popupmenu);
	panel.add(popupmenu);
	panel.add(jsp);
	frameTree.setContentPane(panel);
	frameTree.setVisible(true);		
	
	}
}