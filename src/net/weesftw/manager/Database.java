package net.weesftw.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database implements AutoCloseable 
{
	public Connection con;
	
	public Database() throws SQLException
	{		
		con = DriverManager.getConnection("jdbc:mysql://181.191.197.104:3306/callsystem?useTimezone=true&serverTimezone=UTC", "weesftw", "GE0n5CN1sw3q8PYw");
//		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/callsystem?useTimezone=true&serverTimezone=UTC", "root", "");
//		con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/callsystem?useTimezone=true&serverTimezone=UTC", "weesftw", "f0bbf17c");
	}

	@Override
	public void close() throws SQLException 
	{
		con.close();
	}
}
