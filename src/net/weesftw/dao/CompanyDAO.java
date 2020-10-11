package net.weesftw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.weesftw.manager.Database;
import net.weesftw.vo.CompanyVO;
import net.weesftw.vo.ClientVO;

public class CompanyDAO implements DataAcess<CompanyVO> 
{	
	@Override
	public boolean create(CompanyVO c) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("insert into `company` (`cnpj`, `name`, `owner`, `zipCode`) value (?, ?, ?, ?)"))
		{
			stmt.setString(1, c.getCnpj());
			stmt.setString(2, c.getName());
			stmt.setString(3, c.getOwner().getCpf());
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
	public CompanyVO read(String cnpj) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select * from `company` where `cnpj` = ?"))
		{
			stmt.setString(1, cnpj);
			
			ResultSet rs = stmt.executeQuery();
			
			ClientDAO pd = new ClientDAO();
			
			while(rs.next())
			{
				String id = rs.getString(1);
				String name = rs.getString(2);
				ClientVO owner = pd.read(rs.getString(3));
				String zipCode = rs.getString(4);
				
				return new CompanyVO(id, name, owner, zipCode);
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
				PreparedStatement stmt = d.con.prepareStatement("update `company` set `owner` = ?, `name` = ?, `zipCode` = ? where `cnpj` = ?"))
		{
			stmt.setString(1, c.getName());
			stmt.setString(2, c.getOwner().getCpf());
			stmt.setString(3, c.getZipCode());
			stmt.setString(4, c.getCnpj());
			
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
	public boolean delete(CompanyVO c) 
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
			
			ClientDAO pd = new ClientDAO();
			
			while(rs.next())
			{
				String cnpj = rs.getString(1);
				String name = rs.getString(2);
				ClientVO owner = pd.read(rs.getString(3));
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
