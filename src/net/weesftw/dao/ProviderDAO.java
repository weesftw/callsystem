package net.weesftw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.weesftw.manager.Database;
import net.weesftw.vo.ProviderVO;

public class ProviderDAO implements DataAcess<ProviderVO>  
{
	public ProviderVO searchByName(String args)
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select * from `provider` where `name` = ?"))
		{
			stmt.setString(1, args);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String id1 = rs.getString(1);
				String name = rs.getString(2);
				String category = rs.getString(3);
				String freight = rs.getString(4);
				String zipCode = rs.getString(5);
				String phoneNumber = rs.getString(6);
				
				return new ProviderVO(id1, name, zipCode, phoneNumber, freight, category);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public boolean create(ProviderVO e) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("insert into `provider` (`cnpj`, `name`, `category`, `freight`, `zipCode`, `phoneNumber`) value (?, ?, ?, ?, ?, ?)"))
		{
			stmt.setString(1, e.getCnpj());
			stmt.setString(2, e.getName());
			stmt.setString(3, e.getCategory());
			stmt.setString(4, e.getFreight());
			stmt.setString(5, e.getZipCode());
			stmt.setString(6, e.getPhoneNumber());
			
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
	public ProviderVO read(String id) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select * from `provider` where `cnpj` = ?"))
		{
			stmt.setString(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String id1 = rs.getString(1);
				String name = rs.getString(2);
				String category = rs.getString(3);
				String freight = rs.getString(4);
				String zipCode = rs.getString(5);
				String phoneNumber = rs.getString(6);
				
				return new ProviderVO(id1, name, zipCode, phoneNumber, freight, category);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean update(ProviderVO e) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("update `provider` set `name` = ?, `category` = ?, `freight` = ?, `zipCode` = ?, `phoneNumber` = ? where `cnpj` = ?"))
		{
			stmt.setString(1, e.getName());
			stmt.setString(2, e.getCategory());
			stmt.setString(3, e.getFreight());
			stmt.setString(4, e.getZipCode());
			stmt.setString(5, e.getPhoneNumber());
			stmt.setString(6, e.getCnpj());
			
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
	public boolean delete(ProviderVO e) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("delete from `provider` where `id` = ?"))
		{
			stmt.setString(1, e.getCnpj());
			
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
	public List<ProviderVO> list() 
	{
		List<ProviderVO> l = new ArrayList<ProviderVO>();
		
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select * from `provider`"))
		{
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String id1 = rs.getString(1);
				String name = rs.getString(2);
				String category = rs.getString(3);
				String freight = rs.getString(4);
				String zipCode = rs.getString(5);
				String phoneNumber = rs.getString(6);
				
				l.add(new ProviderVO(id1, name, zipCode, phoneNumber, freight, category));
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
