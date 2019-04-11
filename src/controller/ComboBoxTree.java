package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import view.TreeInterface;

public class ComboBoxTree
{
	DBActionController m_db = new DBActionController();
	public static JTree tree = TreeInterface.tree;
	@SuppressWarnings("unchecked")
	public ComboBoxTree() throws Exception
	{

		  	JFrame frame = new JFrame("Delete"); 
		  	frame.setSize(100,100);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		  	JPanel panel = new JPanel();
			
	        DefaultListModel<String> l1 = new DefaultListModel<>();  
	        l1.addElement("ID");  
	        l1.addElement("Name");  
	        l1.addElement("Age");  
	        l1.addElement("Gender");  
	        JList<String> list = new JList<>(l1);  
	        list.setBounds(20,80,75,75);  
	        frame.add(list);  
		  	
			String options[]={"ID","Name","Age","Gender"};        
		    @SuppressWarnings("rawtypes")
			JComboBox combo=new JComboBox(options);    
		    combo.setBounds(20,50,100,20); 
		    
			JTextField JChoose = new JTextField(5);
			JChoose.setBounds(140,50,125,20);
			JChoose.getText();
			frame.add(JChoose); 
			
			JButton submit = new JButton();
			submit.setText("Submit");
			panel.add(submit);
			submit.setBounds(220,130,80,21);
			frame.add(submit);

			JButton cancel = new JButton();
			cancel.setText("Cancel");
			panel.add(cancel);
			cancel.setBounds(120,130,80,21);
			frame.add(cancel);
			
			frame.add(submit);
			frame.add(combo);        
		    frame.setLayout(null);    
			frame.setSize(340,200);    
			frame.setVisible(true);  
			
			cancel.addActionListener(new ActionListener()
			{
				@Override 
				public void actionPerformed(ActionEvent e)
				{
					frame.dispose();
				}
			});
			
			submit.addActionListener(new ActionListener()
		    { 
				 @Override
		 public void actionPerformed(ActionEvent e)
			 { 
					 
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)
			tree.getSelectionPath().getLastPathComponent();
		    DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
		    
				if (selectedNode != tree.getModel().getRoot())
				{
					treeModel.removeNodeFromParent(selectedNode);
				}
				else
				{
			     	treeModel.setRoot(null);
				}
			  String option = (String) combo.getItemAt(combo.getSelectedIndex());
		
			  if (option == "ID")
			  {
			//m_db.DeleteAfterID(JChoose.getText());
			MyMultiThreading thread5 = new MyMultiThreading
			("DeleteAfterID", m_db, 0, null, null, null, JChoose.getText());
			 thread5.start();
				 
			  }
			  else if (option == "Name")
			  {
			// m_db.DeleteAfterName(JChoose.getText());
			MyMultiThreading thread3 = new MyMultiThreading
			("DeleteAfterName", m_db, 0, JChoose.getText(), null, null, null);
			 thread3.start();
			  }
			  else if (option == "Age")
			  {
		    // m_db.DeleteAfterAge(JChoose.getText());
		    MyMultiThreading thread6  = new MyMultiThreading
		   ("DeleteAfterAge", m_db, 0, null, JChoose.getText(), null, null);	
		    thread6.start();
			  }
			  else if (option == "Gender")
			  {
			//m_db.DeleteAfterGender(JChoose.getText());
		    MyMultiThreading thread4 = new MyMultiThreading
		   ("DeleteAfterGender", m_db, 0, null, null, JChoose.getText(), null);
		    thread4.start();
			  }
			    
			  
			  //JList next
			  String optionList  = list.getSelectedValue();
		
			  if (optionList == "ID")
			  {
				//m_db.DeleteAfterID(JChoose.getText());
				MyMultiThreading thread5 = new MyMultiThreading
			    ("DeleteAfterID", m_db, 0, null, null, null, JChoose.getText());
			    thread5.start();
			  }
			  else if (optionList == "Name")
			  {
				// m_db.DeleteAfterName(JChoose.getText());
				MyMultiThreading thread3 = new MyMultiThreading
				("DeleteAfterName", m_db, 0, JChoose.getText(), null, null, null);
				thread3.start();;
			  }
			  else if (optionList == "Age")
			  {
				// m_db.DeleteAfterAge(JChoose.getText());
				MyMultiThreading thread6  = new MyMultiThreading
			    ("DeleteAfterAge", m_db, 0, null, JChoose.getText(), null, null);	
				thread6.start();
			  }
			  else if (optionList == "Gender")
			  {
			    //m_db.DeleteAfterGender(JChoose.getText());
			    MyMultiThreading thread4 = new MyMultiThreading
			    ("DeleteAfterGender", m_db, 0, null, null, JChoose.getText(), null);
			    thread4.start();
			  }
			  frame.dispose();
			 }
	   });		  
	}
}
