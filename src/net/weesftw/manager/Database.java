package net.weesftw.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.weesftw.Control;

public final class Database implements AutoCloseable 
{
	public Connection con;
	
	public Database() throws SQLException
	{		
		con = DriverManager.getConnection("jdbc:mysql://" + Control.host + ":3306/callSystem?useTimezone=true&serverTimezone=UTC", Control.root, Control.pass);
	}

	@Override
	public void close() throws SQLException 
	{
		con.close();
	}
}
