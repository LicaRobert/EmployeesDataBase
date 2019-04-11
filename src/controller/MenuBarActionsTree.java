package controller;

import view.TreeInterface;

public class MenuBarActionsTree
{
	DBActionController m_db = new DBActionController();

	  public void Populate()
	  {
		try
			{
			m_db.PopulateTheTables();
			TreeInterface.frameTree.setVisible(false);
			new TreeInterface();
			} 
			catch (Exception e1) 
				{
					e1.printStackTrace();
				}
	  }
}
