package net.weesftw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.weesftw.manager.CepAPI;
import net.weesftw.manager.Database;

@Deprecated
public class ZipCodeDAO implements DataAcess<CepAPI> 
{
//	private int getId(Country type, String args)
//	{
//		try(Database d = new Database();
//				PreparedStatement stmt = d.con.prepareStatement("select `id` from `" + type + "` where `name` = ?"))
//		{
//			stmt.setString(1, args);
//			
//			ResultSet rs = stmt.executeQuery();
//			
//			while(rs.next())
//			{
//				return rs.getInt(1);
//			}
//		}
//		catch(SQLException ex)
//		{
//			ex.printStackTrace();
//		}
//		
//		return -1;
//	}
	
	@Override
	public boolean add(CepAPI c) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("insert into `uf` (`name`, `ddd`, `country`) value (?, ?, ?)");
				PreparedStatement stmt2 = d.con.prepareStatement("insert into `city` (`name`, `uf`) value (?, ?)");
				PreparedStatement stmt3 = d.con.prepareStatement("insert into `neighborhood` (`name`, `city`) value (?, ?)");
				PreparedStatement stmt4 = d.con.prepareStatement("insert into `zipCode` (`id`, `name`, `neighborhood`) value (?, ?, ?)"))
		{			
//			if(aux.getUf() == null)
//			{
//				stmt.setString(1, c.getUf());
//				stmt.setString(2, c.getDDD());
//				stmt.setInt(2, getId(Country.COUNTRY, c.getCountry()));
//				
//				stmt.execute();
//				
//				System.out.println("UF created.");
//			}
//			
//			if(aux.getLocalidade() == null)
//			{
//				stmt2.setString(1, c.getLocalidade());
//				stmt2.setInt(2, getId(Country.UF, c.getUf()));
//				
//				stmt2.execute();
//				
//				System.out.println("City created.");
//			}
//			
//			if(aux.getBairro() == null)
//			{
//				stmt3.setString(1, c.getBairro());
//				stmt3.setInt(2, getId(Country.CITY, c.getLocalidade()));
//				
//				stmt3.execute();
//				
//				System.out.println("Neighborhood created.");
//			}
//			
//			if(aux.getCep() == null)
//			{
//				stmt4.setString(1, c.getCep());
//				stmt4.setString(2, c.getLogradouro());
//				stmt4.setInt(2, getId(Country.NEIGHBORHOOD, c.getBairro()));
//				
//				stmt4.execute();
//				
//				System.out.println("Zip Code created.");
//			}
			
			return true;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public CepAPI search(CepAPI c) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select `zipcode`.`id`, `zipcode`.`nome` as `zipcode`, `neighborhood`.`name` as `neighborhood`, `city`.`nome` as `city`, `uf`.`name`, `uf`.`ddd` as `uf`, `country`.`name` as `country` from `zipcode`"
						+ " join `neighborhood` on `zipcode`.`neighborhood` = `neighborhood`.`id` join `city` on `neighborhood`.`city` = `city`.`id` join `uf` on `city`.`uf` = `uf`.`id` join `country` on `uf`.`country` = `country`.`id` where `id` = ?"))
		{
			stmt.setString(1, c.getCep());
					
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String cep = rs.getString(1);
				String logradouro = rs.getString(2);
				String bairro = rs.getString(3);
				String localidade = rs.getString(4);
				String uf = rs.getString(5);
				String ddd = rs.getString(6);
				String country = rs.getString(7);
				
				return new CepAPI(cep, logradouro, bairro, localidade, uf, ddd, country);				
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean update(CepAPI c) 
	{
		return false;
	}

	@Override
	public boolean remove(CepAPI c) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("delete from `zipcode` where `cpf` = ?"))
		{
			stmt.setString(1, c.getCep());
			
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
	public List<CepAPI> list()
	{		
		List<CepAPI> l = new ArrayList<CepAPI>();
		
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select `zipcode`.`id`, `zipcode`.`name` as `zipcode`, `neighborhood`.`name` as `neighborhood`, `city`.`name` as `city`, `uf`.`name`, `uf`.`ddd` as `uf`, `country`.`name` as `country` from `zipcode`"
						+ " join `neighborhood` on `zipcode`.`neighborhood` = `neighborhood`.`id` join `city` on `neighborhood`.`city` = `city`.`id` join `uf` on `city`.`uf` = `uf`.`id` join `country` on `uf`.`country` = `country`.`id`"))
		{			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String cep = rs.getString(1);
				String logradouro = rs.getString(2);
				String bairro = rs.getString(3);
				String localidade = rs.getString(4);
				String uf = rs.getString(5);
				String ddd = rs.getString(6);
				String country = rs.getString(7);
				
				l.add(new CepAPI(cep, logradouro, bairro, localidade, uf, ddd, country));
			}
			
			for(CepAPI c : l)
			{
				System.out.println(c.getCountry());
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
