package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.InterfaceGUI;

public class FillComboBox 
{
	
	@SuppressWarnings({"rawtypes", "unchecked",})
	public FillComboBox() throws Exception
	{
		  	JFrame frame = new JFrame("Delete"); 
		  	JPanel panel = new JPanel();
		  	
		  	frame.setSize(100,100);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			String options[]={"ID","Name","Age","Gender"};        
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
			  DBActionController ac = new  DBActionController();
			
			  ac.DeleteAfterID(JChoose.getText());
			  ac.DeleteAfterName(JChoose.getText());
			  ac.DeleteAfterAge(JChoose.getText());
			  ac.DeleteAfterGender(JChoose.getText());
			  InterfaceGUI.frame.setVisible(false);
			  new InterfaceGUI();
			  TreeInterface.frameTree.dispose();
			  new TreeInterface();
			  frame.dispose();
			}
	   });		  
	}	
}