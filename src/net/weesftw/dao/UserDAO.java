package net.weesftw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.weesftw.manager.Database;
import net.weesftw.vo.UserVO;

public class UserDAO implements DataAcess<UserVO> 
{
	@Override
	public boolean add(UserVO u) 
	{
		try(Database d = new Database(); 
				PreparedStatement stmt = d.con.prepareStatement("insert into `user` (`cpf`, `username`, `passwd`) value (?, ?, ?)"))
		{			
			stmt.setString(1, u.getCpf());
			stmt.setString(2, u.getUsername());
			stmt.setString(3, u.getPasswd());
			
			stmt.execute();
			
			return true;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public UserVO search(String args) 
	{
		try(Database d = new Database(); 
				PreparedStatement stmt = d.con.prepareStatement("select * from `user` where `user` = ?");
				ResultSet rs = stmt.executeQuery())
		{
			stmt.setString(1, args);
			
			while(rs.next())
			{
				String cpf = rs.getString(1);
				String username = rs.getString(2);
				String passwd = rs.getString(3);
				
				return new UserVO(cpf, username, passwd);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean update(String column, String value, UserVO p) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("update `user` set `?` = ? where `cpf` = ?"))
		{
			stmt.setString(1, column);
			stmt.setString(2, value);
			stmt.setString(3, p.getCpf());
			
			stmt.execute();
			
			return true;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean remove(UserVO p) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("delete from `user` where `cpf` = ?"))
		{
			stmt.setString(1, p.getCpf());
			
			stmt.execute();
			
			return true;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public List<UserVO> list()
	{
		List<UserVO> l = new ArrayList<UserVO>();
		
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select * from `user`");
				ResultSet rs = stmt.executeQuery())
		{			
			while(rs.next())
			{
				String cpf = rs.getString(1);
				String username = rs.getString(2);
				String passwd = rs.getString(3);
				
				l.add(new UserVO(cpf, username, passwd));
				
				return l;
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public boolean isValid(String user, String passwd)
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select `username`, `passwd` from `user` where `username`= ? && `passwd` = ?");
				ResultSet rs = stmt.executeQuery())
		{			
			return rs.next();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return false;
	}
}
