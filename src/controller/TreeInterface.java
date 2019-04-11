package  controller;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class TreeInterface 
{
	DBActionController db;
	
	JTree tree;
	Connection conn;
	Statement stmt;
	ResultSet rs;
	DefaultMutableTreeNode id = new DefaultMutableTreeNode();
	DefaultMutableTreeNode idTree = new DefaultMutableTreeNode();
	public static JFrame frameTree = new JFrame();
	JPanel panel = new JPanel();

	public TreeInterface()
	{
		
	JButton cancel = new JButton();
	cancel.setText("Cancel");
	cancel.setBounds(370, 210, 90, 20);
	cancel.addActionListener(new ActionListener()
		{
	@Override
	public void actionPerformed(ActionEvent e) 
			{
				frameTree.dispose();
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
		DefaultMutableTreeNode idTree = new DefaultMutableTreeNode(id+ " : " + rs.getInt("ID"));
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
	
	JButton deleteSel = new JButton();
	deleteSel.setText("Delete");
	deleteSel.setBounds(250, 210, 90, 20);
	deleteSel.addActionListener(new ActionListener()
		{
	@Override
	public void actionPerformed(ActionEvent e) 
		{
		
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
			DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
			
			if (selectedNode != tree.getModel().getRoot())
			{
				treeModel.removeNodeFromParent(selectedNode);
			}
			else
			{
				treeModel.setRoot(null);
			}
		}
	});
	
	
	tree = new JTree(id);
	frameTree.setSize(500,300);
	frameTree.setLocationRelativeTo(null);
	frameTree.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JScrollPane jsp = new JScrollPane(tree);
	panel.add(cancel);
	panel.add(deleteSel);
	panel.setLayout(new BorderLayout());
	panel.add(jsp,BorderLayout.WEST);
	frameTree.setContentPane(panel);
	frameTree.setVisible(true);		
	}
}