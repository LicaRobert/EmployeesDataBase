package controller;

public class MyMultiThreading extends Thread
{

	private DBActionController m_db;
	private String nameAction;
	
	 //List<Object> parametri;
	 int id;
	 String name;
	 String age; 
	 String gender;
	 String idDel;
	
	public MyMultiThreading(String nameMethod,DBActionController connection, 
	           int id, String name, String age, String gender, String idDel)
	{
		nameAction = nameMethod;
		m_db = connection;
		this.id = id;
		this.name = name;
		this.age =  age; 
		this.gender = gender;
		this.idDel =  idDel;
	}

	@Override
	public synchronized void run() 
	{

		if(nameAction =="InsertPerson")
		{
			try 
			{
				m_db.InsertPerson(id,name,age,gender);
			}
			catch(Exception e)
			{
				System.out.println("Thread : " + nameAction + "intererrupted");	
			}
		}
		else if(nameAction == "UpdatePerson")
		{
			try 
			{
				m_db.UpdatePerson(id,name,age,gender);
			}
			catch(Exception e)
			{
				System.out.println("Thread : " + nameAction + "intererrupted");	
			}
		}
		else if(nameAction == "DeleteAfterName")
		{
			try
			{
				m_db.DeleteAfterName(name);
			}
			catch(Exception e)
			{
				System.out.println("Thread :" + nameAction + "intererrupted");	
			}
		}
		else if (nameAction == "DeleteAfterGender")
		{
			try
			{
				m_db.DeleteAfterGender(gender);
			}
			catch(Exception e)
			{
				System.out.println("Thread : " + nameAction + "intererrupted");	
			}
		}
		else if (nameAction == "DeleteAfterAge")
		{
			try
			{
				m_db.DeleteAfterAge(age);
			}
			catch(Exception e)
			{
				System.out.println("Thread : " + nameAction + "intererrupted");	
			}
		}
		else if (nameAction == "DeleteAfterID")
		{
			m_db.DeleteAfterID(idDel);
		}
		else if(nameAction == "DeleteAll")
		{
			try
			{
				m_db.DeleteAll();
			}
			catch(Exception e)
			{
				System.out.println("Thread : " + nameAction + "intererrupted");	
			}
		}
		else if (nameAction == "PopulateTheTables")
		{
			try
			{
				m_db.PopulateTheTables();
			} 
			catch (Exception e)
			{
				System.out.println("Thread : " + nameAction + "intererrupted");	
			}
		}
	}
	public int getID()
	{
		return id;
	}
	public String getNName()
	{
		return name;
	}
	public String getAge()
	{
		return age;	
	}
	public String getGender()
	{
		return gender;
	}
	public String getIDDel()
	{
		return idDel;	
	}
}