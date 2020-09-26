package net.weesftw;

import java.sql.SQLException;

import net.weesftw.manager.Database;
import net.weesftw.view.Login;

public final class Control 
{	
	public static void main(String[] args)
	{		
		try(Database d = new Database())
		{
			if(d != null)
			{
				new Login();			
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
