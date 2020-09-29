package net.weesftw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.weesftw.manager.Database;
import net.weesftw.vo.CompanyVO;

public class CompanyDAO implements DataAcess<CompanyVO> 
{
	@Override
	public boolean add(CompanyVO c) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("insert into `company` (`cnpj`, `name`, `owner`, `zipCode`) value (?, ?, ?, ?)"))
		{
			stmt.setString(1, c.getCnpj());
			stmt.setString(2, c.getName());
			stmt.setString(3, c.getOwner());
			stmt.setString(4, c.getZipCode());
			
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
	public CompanyVO search(CompanyVO c) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select * from `company`"))
		{
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String cnpj = rs.getString(1);
				String name = rs.getString(2);
				String owner = rs.getString(3);
				String zipCode = rs.getString(4);
				
				return new CompanyVO(cnpj, name, owner, zipCode);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean update(CompanyVO c) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("update `company` set `cnpj` = ?, `name` = ?, `owner` = ?, `zipCode` = ? where `cnpj` = ?"))
		{
			stmt.setString(1, c.getCnpj());
			stmt.setString(2, c.getName());
			stmt.setString(3, c.getOwner());
			stmt.setString(4, c.getZipCode());
			
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
	public boolean remove(CompanyVO c) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("delete from `company` where `cnpj` = ?"))
		{
			stmt.setString(1, c.getCnpj());
			
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
	public List<CompanyVO> list() 
	{
		List<CompanyVO> l = new ArrayList<CompanyVO>();
		
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select * from `company`"))
		{
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String cnpj = rs.getString(1);
				String name = rs.getString(2);
				String owner = rs.getString(3);
				String zipCode = rs.getString(4);
				
				l.add(new CompanyVO(cnpj, name, owner, zipCode));
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
