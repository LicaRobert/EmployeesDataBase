package controller;

import view.InterfaceGUI;

public class MenuBarActions
{
	DBActionController m_db = new DBActionController();

  public void Populate()
  {
	try
		{
		m_db.PopulateTheTables();
		InterfaceGUI.frame.dispose();
		new InterfaceGUI();
		
		} 
		catch (Exception e1) 
			{
				e1.printStackTrace();
			}
  }

  public void ComboBox()
  {
	  try 
	  {
		new ComboBox();
	  }
	  catch (Exception e)
	  	{
		  e.printStackTrace();
	 	}
  }
}