package model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class DomDefaultHandler 
{
	Connection DBConnection	 			 = 	null;
	PreparedStatement myPrepareStatement =  null;
	ResultSet myRS						 =  null;
	
	 public static String id;
	 public static String name;
	 public static String age;
	 public static String gender;
	
	
	public DomDefaultHandler() throws Exception
	{
		   try {
		    	 
 		       DBConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/angajati","root","ProElite");
	           File fXmlFile = new File("angajati.xml");
	           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	           org.w3c.dom.Document doc = (org.w3c.dom.Document) dBuilder.parse(fXmlFile);
	            			
	           ((org.w3c.dom.Document) doc).getDocumentElement().normalize();

	           System.out.println("Root element :" + ((org.w3c.dom.Document) doc).getDocumentElement().getNodeName());
	            			
	           NodeList nList = ((org.w3c.dom.Document) doc).getElementsByTagName("Employee");
	            			
	           System.out.println("----------------------------");

	           for (int temp = 0; temp < nList.getLength(); temp++)
	           {
	           Node nNode = nList.item(temp);
	            				
	           System.out.println("\nCurrent Element :" + nNode.getNodeName());
	            				
	           if (nNode.getNodeType() == Node.ELEMENT_NODE)
	           		{
	        	   
	           Element eElement = (Element) nNode;
	            
	            id  	=  eElement.getAttribute("id");
	            name 	=  eElement.getElementsByTagName("name").item(0).getTextContent();
	            age	    =  eElement.getElementsByTagName("age").item(0).getTextContent();
	            gender	=  eElement.getElementsByTagName("gender").item(0).getTextContent();
	           
	           String sql = "insert into `angajati` values ("+ id +  ",'" + name + "','" + age + "','" + gender + "'" +")";   
	           Statement myStatement = DBConnection.createStatement();
	           myStatement.executeUpdate(sql);
	            
	            	}
	            } 	
	    	} 
		      catch (Exception e) 
 	   			{
	            System.out.println(e);
	    	    }
    }
}