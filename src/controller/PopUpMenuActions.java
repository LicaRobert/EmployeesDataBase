package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import javax.swing.table.DefaultTableModel;


import view.InterfaceGUI;

public class PopUpMenuActions
{	
	DBActionController  m_db  = new DBActionController();
	String genderJRadio;
	JFrame framePop = new JFrame(); 
	
	public void PopUpUpdate()
	{
	
		JFrame frame  = new JFrame();
		JPanel panel = new JPanel();
		frame.setTitle("Update a person");
		
		SpinnerModel id =  new SpinnerNumberModel(0,0,100,1);
	    JSpinner spinner = new JSpinner(id); 
	    JLabel idL = new JLabel("ID : ");
	    frame.add(idL);
		idL.setBounds(30,0,200,60);
	    spinner.setBounds(60,15,35,30);    
	    frame.add(spinner);

		
		/*JTextField id = new JTextField(50);
		JLabel idL = new JLabel("ID : ");
		idL.setBounds(10,5,200,60);
		frame.add(idL);
		id.setBounds(70,27,150,18);
		id.getText();
		frame.add(id); */

		JLabel nameL = new JLabel("Name : ");
		nameL.setBounds(5,35,200,60);
		frame.add(nameL);
		JTextField name = new JTextField(5);
		name.setBounds(70,57,150,18);
		name.getText();
		frame.add(name);

		JLabel ageL = new JLabel("Age : ");
		ageL.setBounds(5,65,200,60);
		frame.add(ageL);
		JTextField age = new JTextField(5);
		age.setBounds(70,87,150,18);
		age.getText();
		frame.add(age);
		
		JLabel genderL = new JLabel("Gender : ");
		genderL.setBounds(5,120,200,60);
		frame.add(genderL);
		
		JRadioButton radio1	 =	new JRadioButton(" Female ");    
		JRadioButton radio2  =  new JRadioButton(" Male ");    
		radio1.setBounds(70,135,100,30);    
		radio2.setBounds(165,135,70,30); 
		ButtonGroup bg = new ButtonGroup();    
		bg.add(radio1); bg.add(radio2);    
		frame.add(radio2); frame.add(radio1); 
		radio1.addActionListener(new ActionListener() 
		 {
		        @Override
		        public void actionPerformed(ActionEvent e)
		        {
		        	genderJRadio = "Female";
		        }
		 });
			
		radio2.addActionListener(new ActionListener() 
		 {
		        @Override
		        public void actionPerformed(ActionEvent e)
		        {
		        	genderJRadio = "Male";
		        }
		 });
		
		DefaultTableModel model = (DefaultTableModel) InterfaceGUI.table.getModel();	 
	    int selectedRow = InterfaceGUI.table.getSelectedRow();

	    name.setText(model.getValueAt(selectedRow, 1).toString());
	    age.setText(model.getValueAt(selectedRow, 2).toString());
	    
		JButton submit = new JButton();
		submit.setText("Submit");
		panel.add(submit);
		submit.setBounds(190,225,80,21);
		frame.add(submit);

		JButton cancel = new JButton();
		cancel.setText("Cancel");
		panel.add(cancel);
		cancel.setBounds(95,225,80,21);
		frame.add(cancel);
		
		frame.setSize(300,300); 
		frame.setLocation(640,370);
		frame.setLocationRelativeTo(panel);
		frame.setLayout(null);  
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
				 
		   //m_db.UpdatePerson((Integer)id.getValue(), name.getText(), age.getText(), genderJRadio);	 
		    MyMultiThreading thread2 = new MyMultiThreading
		   ("UpdatePerson", m_db,(Integer)id.getValue(), name.getText(), age.getText(), genderJRadio, null);	
		    thread2.start();
		    
		    DefaultTableModel model = (DefaultTableModel) InterfaceGUI.table.getModel();	 
		    int selectedRow = InterfaceGUI.table.getSelectedRow();
			model.setValueAt(id.getValue(), selectedRow, 0);
			model.setValueAt(name.getText(), selectedRow, 1);
			model.setValueAt(age.getText(), selectedRow, 2);
			model.setValueAt(genderJRadio, selectedRow, 3);
		 
		    frame.dispose();
			 }
	  }); 
	}
	
	public void PopUpDeleteAll()
	{
		m_db.DeleteAll();
		
	}
	
	public void PopUpInsert()
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.setTitle("Insert");
	
		SpinnerModel id =  new SpinnerNumberModel(0,0,100,1);
	    JSpinner spinner = new JSpinner(id); 
	    JLabel idL = new JLabel("ID : ");
	    frame.add(idL);
		idL.setBounds(30,0,200,60);
	    spinner.setBounds(60,15,35,30);    
	    frame.add(spinner);

		/*
		JTextField id = new JTextField(50);
		JLabel idL = new JLabel("ID : ");
		idL.setBounds(10,5,200,60);
		frame.add(idL);
		id.setBounds(70,27,150,18);
		id.getText();
		frame.add(id); */
	    
		JLabel nameL = new JLabel("Name : ");
		nameL.setBounds(5,35,200,60);
		frame.add(nameL);
		JTextField name = new JTextField(5);
		name.setBounds(70,57,150,18);
		name.getText();
		frame.add(name);

		JLabel ageL = new JLabel("Age : ");
		ageL.setBounds(5,65,200,60);
		frame.add(ageL);
		JTextField age = new JTextField(5);
		age.setBounds(70,87,150,18);
		age.getText();
		frame.add(age);

		JLabel genderL = new JLabel("Gender : ");
		genderL.setBounds(5,120,200,60);
		frame.add(genderL);
		
		JRadioButton radio1	 =	new JRadioButton(" Female ");    
		JRadioButton radio2  =  new JRadioButton(" Male ");    
		radio1.setBounds(70,135,100,30);    
		radio2.setBounds(165,135,70,30);   
		
		ButtonGroup bg=new ButtonGroup();    
		bg.add(radio1); bg.add(radio2);    
		frame.add(radio2); frame.add(radio1);   
		
		radio1.addActionListener(new ActionListener() 
		 {
		        @Override
		        public void actionPerformed(ActionEvent e)
		        {
		        	genderJRadio = "Female";
		        }
		 });
			
		radio2.addActionListener(new ActionListener() 
		 {
		        @Override
		        public void actionPerformed(ActionEvent e)
		        {
		        	genderJRadio = "Male";
		        }
		 });
		
		JButton submit = new JButton();
		submit.setText("Submit");
		panel.add(submit);
		submit.setBounds(190,225,80,21);
		frame.add(submit);

		JButton cancel = new JButton();
		cancel.setText("Cancel");
		panel.add(cancel);
		cancel.setBounds(95,225,80,21);
		frame.add(cancel);
		
		frame.setSize(300,300); 
		frame.setLocation(640,370);
		frame.setLayout(null);  
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
		   	   //m_db.InsertPerson((Integer)id.getValue(), name.getText(),  age.getText(), genderJRadio);
			   MyMultiThreading thread1 = new MyMultiThreading
			   ("InsertPerson", m_db,(Integer)id.getValue(), name.getText(), age.getText(), genderJRadio, null);
			   thread1.start();
			  
			   DefaultTableModel model = (DefaultTableModel) InterfaceGUI.table.getModel();
			   model.addRow(new Object[]{ (Integer)id.getValue(), name.getText(),  age.getText(), genderJRadio });
			   frame.dispose();
				}
		 });	
	}	
}