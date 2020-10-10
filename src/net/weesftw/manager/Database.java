package net.weesftw.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database implements AutoCloseable 
{
	public Connection con;
	
	public Database() throws SQLException
	{		
//		con = DriverManager.getConnection("jdbc:mysql://181.191.197.80:3306/callsystem?useTimezone=true&serverTimezone=UTC", "weesftw", "0FmLzV3zMJ30Nano");
//		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/callsystem?useTimezone=true&serverTimezone=UTC", "root", "");
		con = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10369045?useTimezone=true&serverTimezone=UTC", "sql10369045", "gSMQ7vzGX6");
	}

	@Override
	public void close() throws SQLException 
	{
		con.close();
	}
}
