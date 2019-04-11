package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ChooseInterface 
{
  public ChooseInterface() throws Exception
  {	
	  	ImageIcon jtableImage = new ImageIcon("jtable.png");
	  	ImageIcon jtreeImage = new ImageIcon("jtree.png");
	  
	  	JFrame frame = new JFrame();  
        JPanel panel = new JPanel();  
        frame.add(panel);  
        panel.setLayout(new BorderLayout());
        
        
        JButton jtable = new JButton(jtableImage);  
        panel.add(jtable);  
        jtable.setBounds(7, 5, 200, 280);
        jtable.addActionListener(new ActionListener()
		{
			@Override
		public void actionPerformed(ActionEvent e) 
			{		
				new InterfaceGUI();
				frame.dispose();
			}
		});
        
        JButton jtree = new JButton(jtreeImage);
		panel.add(jtree);
		jtree.setBounds(230, 5, 250, 280);
		jtree.addActionListener(new ActionListener()
		{
			@Override
		public void actionPerformed(ActionEvent e) 
			{		
				new TreeInterface();
				frame.dispose();
			}
		});
		
		frame.setSize(510,340);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane jsp = new JScrollPane();
		panel.setLayout(new BorderLayout());
		panel.add(jsp,BorderLayout.CENTER);
		frame.setContentPane(panel);
		frame.setVisible(true);	     
  }
}