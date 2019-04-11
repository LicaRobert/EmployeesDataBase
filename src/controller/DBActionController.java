package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.DomDefaultHandler;

public class DBActionController 
	{
	
	@SuppressWarnings("rawtypes")
	Vector data 	= 	new Vector();
	@SuppressWarnings("rawtypes")
	Vector row 		= 	new Vector();
	@SuppressWarnings("rawtypes")
	Vector column 	=	new Vector();
	
	@SuppressWarnings("rawtypes")
	public Vector getData()
    {
			return data;
	}
	@SuppressWarnings("rawtypes")
	public Vector getColumn()
	{
		  return column;
	}
	@SuppressWarnings("rawtypes")
	public Vector getRow()
	{
		return row;
	}
	public int id;
	public String idDel;
	public String age;
	public String name;
	public String gender;
	
	Statement myStatement	 		     = 		null;
	PreparedStatement myPrepareStatement =		null;
	ResultSet myRS 						 = 		null;
	Connection DBConnection	 			 = 		null;
	String select;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public synchronized void CreateJTableFillDB()
	{
		try {
			  Class.forName("org.mariadb.jdbc.Driver").newInstance();	
			  DBConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/angajati","root","ProElite");
			  myStatement = DBConnection.createStatement();
	          select = "select * from angajati";
	          myRS = myStatement.executeQuery(select);
	          ResultSetMetaData metadata = myRS.getMetaData();
	          int columns = metadata.getColumnCount();
	       
	          for (int i = 1; i <= columns; i++) 
	          {
	        	column.add(metadata.getColumnName(i));
	          }
	        	while (myRS.next())
	        	{
	        	row = new Vector(columns);
	        		for ( int i = 1; i <= columns; i++)
	        		 {
	        		   row.add(myRS.getString(i));
	        		 }
	        	data.add(row);
	            }
			}
				catch (Exception e) 
				{
					JOptionPane.showMessageDialog(null,"Error");
				}
				finally 
				{
				try 
				    {
					  myStatement.close();
					  myRS.close();
				    }
					catch(Exception e)
					{
					JOptionPane.showMessageDialog(null, "Eroare Close");
					}
				}
	}
	
     public synchronized void PopulateTheTables() throws Exception
     {
    	new DomDefaultHandler();   	
     }
     
     public synchronized void DeleteAll()
     {
    	 try {
				DBConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/angajati","root","ProElite");
				myStatement = DBConnection.createStatement();
				String sql ="DELETE angajati from angajati";
				myStatement.executeUpdate(sql);
				myStatement.close();
    	 	 }
    	 	catch (SQLException e1) 
    	 	{
    	 	e1.printStackTrace();			
    	 	}
    	 	finally 
    	 	{
    	 		try 
    	 		{
    	 			if (myStatement != null)
    	 				myStatement.close();
    	 		}
    	 		catch (SQLException se)
    	 			{
    	 			se.printStackTrace();
    	 			}
    	 	}
     }
     
     public synchronized void DeleteAfterID(String idDel)
 	{
 		try {
 			DBConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/angajati","root","ProElite");
 			String query  ="DELETE FROM angajati WHERE id = ?";
 			myPrepareStatement = DBConnection.prepareStatement(query);
 			myPrepareStatement.setString(1, idDel);
 			myPrepareStatement.executeQuery();
 			myPrepareStatement.close();
 			}
 			catch (SQLException e1) 
 			{
 			e1.printStackTrace();
 			} 
 			finally 
 			{
 				try 
 				{
 					if (myStatement != null)
 						myStatement.close();
 				}
 				catch (SQLException se)
 					{
 					se.printStackTrace();
 					} 
 			}	
 	}
     
     public synchronized void DeleteAfterAge(String age)
  	{
  		try {
  			DBConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/angajati","root","ProElite");
  			String query  ="DELETE FROM angajati WHERE age = ?";
  			myPrepareStatement = DBConnection.prepareStatement(query);
  			myPrepareStatement.setString(1, age);
  			myPrepareStatement.executeQuery();
  			myPrepareStatement.close();
  			}
  			catch (SQLException e1) 
  			{
  			e1.printStackTrace();
  			} 
  			finally 
  				{
  				try 
  					{
  					if (myStatement != null)
  						myStatement.close();
  					}
  					catch (SQLException se)
  					{
  						se.printStackTrace();
  					} 
  				}	
  	}
     
     public synchronized void DeleteAfterGender(String gender)
  	{
  		try {
  			DBConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/angajati","root","ProElite");
  			String query  ="DELETE FROM angajati WHERE gender = ?";
  			myPrepareStatement = DBConnection.prepareStatement(query);
  			myPrepareStatement.setString(1, gender);
  			myPrepareStatement.executeQuery();
  			myPrepareStatement.close();
  			}
  			  catch (SQLException e1) 
  			  {
  			  e1.printStackTrace();
  			  } 
  			  finally 
  				{
  				try 
  					{
  					if (myStatement != null)
  						myStatement.close();
  					}
  					catch (SQLException se)
  					{
  						se.printStackTrace();
  					} 
  				}	
  	}
     
     public synchronized void DeleteAfterName(String name)
	    {

		    try {
			 	DBConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/angajati","root","ProElite");
	 			String query  ="DELETE FROM angajati WHERE name = ? ";
	 			myPrepareStatement = DBConnection.prepareStatement(query);
	 			myPrepareStatement.setString(1, name);
	 			myPrepareStatement.executeQuery();
	 			myPrepareStatement.close();
		 		}
	    		catch (SQLException e1) 
	 			{
	    		e1.printStackTrace();
	 			} 
	    		finally 
	    		{
	    		 try 
	    		   {
	    		   if (myStatement != null)
	    			  myStatement.close();
	    			} 
	    			catch (SQLException se)
	    			{
	    			se.printStackTrace();
	    			} 
	 			}			
	 	} 
     
    public synchronized void InsertPerson(int id, String name, String age, String gender)
     {
    	
    	 	try {
    		 	DBConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/angajati","root","ProElite");
				String query ="INSERT INTO angajati(id, name, age, gender)values(?, ?, ?, ?)";
				PreparedStatement myPrepareStatement = DBConnection.prepareStatement(query);
				myPrepareStatement.setInt(1, id);
				myPrepareStatement.setString(2, name);
				myPrepareStatement.setString(3, age);
				myPrepareStatement.setString(4, gender);
				myPrepareStatement.execute();
				myPrepareStatement.close();
			    } 
    	 		catch (SQLException e1)
    	 		{
    	 			e1.printStackTrace();
    	 		} 
    	     	finally
    	     	{	
    	     	try
    	        	{
    	     		if (myStatement != null)
    	     			myStatement.close();
    	     		}
    	     		catch (SQLException se) 
    	     		{
    	     			se.printStackTrace();
    	     		} 
    	     	}
     }
    
	public synchronized void UpdatePerson(int id, String name, String age, String gender)
     {
		try {	
			DBConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/angajati","root","ProElite");
			String query ="UPDATE angajati SET name = ?, age = ?, gender = ? WHERE id = ?";
			
			PreparedStatement myPrepareStatement = DBConnection.prepareStatement(query);
			myPrepareStatement.setString(1, name);
			myPrepareStatement.setString(2, age);
			myPrepareStatement.setString(3, gender);
			myPrepareStatement.setInt(4, id);
			myPrepareStatement.executeUpdate();
			myPrepareStatement.close();
		    } 
			catch (SQLException e1)
			{
				e1.printStackTrace();
			} 
			finally 
			{	
				try 
				{
					if (myStatement != null)
						myStatement.close();
				}
				catch (SQLException se)
				{
					se.printStackTrace();
				} 
			}
     }
}