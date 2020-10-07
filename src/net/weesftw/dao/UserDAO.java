package net.weesftw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.weesftw.constraint.Department;
import net.weesftw.manager.Database;
import net.weesftw.vo.UserVO;

public class UserDAO implements DataAcess<UserVO> 
{
	public UserVO search(String username) 
	{
		try(Database d = new Database(); 
				PreparedStatement stmt = d.con.prepareStatement("select `user`.`cpf`, `user`.`username`, `user`.`passwd`, `department`.`name` from `user` join `department` on `user`.`department` = `department`.`id` where `username` = ?"))
		{	
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String cpf = rs.getString(1);
				String user = rs.getString(2);
				String passwd = rs.getString(3);
				Department department = Department.valueOf(rs.getString(4).toUpperCase());
				
				return new UserVO(cpf, user, passwd, department);
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
				PreparedStatement stmt = d.con.prepareStatement("select `username`, `passwd` from `user` where `username`= ? && `passwd` = ?"))
		{
			stmt.setString(1, user);
			stmt.setString(2, passwd);
			
			ResultSet rs = stmt.executeQuery();
			
			return rs.next();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean create(UserVO u) 
	{
		try(Database d = new Database(); 
				PreparedStatement stmt = d.con.prepareStatement("insert into `user` (`cpf`, `username`, `passwd`, `department`) value (?, ?, ?, ?)"))
		{			
			stmt.setString(1, u.getCpf());
			stmt.setString(2, u.getUsername());
			stmt.setString(3, u.getPasswd());
			stmt.setInt(4, u.getDepartment().getId());
			
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
	public UserVO read(UserVO u) 
	{
		try(Database d = new Database(); 
				PreparedStatement stmt = d.con.prepareStatement("select `user`.`cpf`, `user`.`username`, `user`.`passwd`, `department`.`name` from `user` join `department` on `user`.`department` = `department`.`id` where `cpf` = ?"))
		{
			stmt.setString(1, u.getCpf());
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String cpf = rs.getString(1);
				String username = rs.getString(2);
				String passwd = rs.getString(3);
				Department department = Department.valueOf(rs.getString(4));
				
				return new UserVO(cpf, username, passwd, department);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public boolean update(UserVO u) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("update `user` set `username` = ?, `passwd` = ?, `department` = ? where `cpf` = ?"))
		{
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPasswd());
			stmt.setInt(3, u.getDepartment().getId());
			
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
	public boolean delete(UserVO p) 
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
				PreparedStatement stmt = d.con.prepareStatement("select `user`.`cpf`, `user`.`username`, `user`.`passwd`, `department`.`name` from `user` join `department` on `user`.`department` = `department`.`id`"))
		{
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String cpf = rs.getString(1);
				String username = rs.getString(2);
				String passwd = rs.getString(3);
				Department department = Department.valueOf(rs.getString(4));
				
				l.add(new UserVO(cpf, username, passwd, department));				
			}
			
			return l;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}
}
