package net.weesftw.dao;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.weesftw.constraint.Gender;
import net.weesftw.manager.Database;
import net.weesftw.vo.ClientVO;

public class ClientDAO implements DataAcess<ClientVO>
{	
	public ClientVO searchByUser(String user)
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select `client`.`cpf`, `client`.`firstName`, `client`.`lastName`, `client`.`phoneNumber`, `client`.`email`, `client`.`dateBorn`, `client`.`gender`, `client`.`zipCode`, `client`.`photo` as `client` from `client` join `user` on `client`.`cpf` = `user`.`cpf` where `user`.`username` = ?"))
		{
			stmt.setString(1, user);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String cpf = rs.getString(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String phoneNumber = rs.getString(4);
				String email = rs.getString(5);
				String date = rs.getString(6);
				Gender gender = Gender.valueOf(rs.getString(7));
				String zipCode = rs.getString(8);
				byte[] b = rs.getBytes(9);
				
				return new ClientVO(cpf, firstName, lastName, phoneNumber, email, date, gender, zipCode, b);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public boolean create(ClientVO p) 
	{
		try(Database d = new Database(); 
				PreparedStatement stmt = d.con.prepareStatement("insert into `client` (`cpf`, `firstName`, `lastName`, `phoneNumber`, `email`, `dateBorn`, `gender`, `zipCode`, `photo`) value (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				FileInputStream file = new FileInputStream(p.getPath()))
		{
			int img = file.read();
			
			while(img != -1)
			{
				buffer.write(img);
				img = file.read();
			}
			
			stmt.setString(1, p.getCpf());
			stmt.setString(2, p.getFirstName());
			stmt.setString(3, p.getLastName());
			stmt.setString(4, p.getPhoneNumber());
			stmt.setString(5, p.getEmail());
			stmt.setString(6, p.getDate());
			stmt.setString(7, p.getGender().name());
			stmt.setString(8, p.getZipCode());
			stmt.setBytes(9, buffer.toByteArray()); 
			
			stmt.execute();
			
			return true;
		}
		catch(SQLException | IOException ex)
		{
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public ClientVO read(String cpf) 
	{
		try(Database d = new Database(); 
				PreparedStatement stmt = d.con.prepareStatement("select * from `client` where cpf = ?"))
		{
			stmt.setString(1, cpf);

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String id = rs.getString(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String phoneNumber = rs.getString(4);
				String email = rs.getString(5);
				String date = rs.getString(6);
				Gender gender = Gender.valueOf(rs.getString(7));
				String zipCode = rs.getString(8);
				byte[] b = rs.getBytes(9);
				
				return new ClientVO(id, firstName, lastName, phoneNumber, email, date, gender, zipCode, b);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean update(ClientVO p) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("update `client` set `firstName` = ?, `lastName` = ?, `phoneNumber` = ?, `email` = ?, `dateBorn` = ?, `zipCode` = ?, `photo` = ? where `cpf` = ?");
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				FileInputStream file = new FileInputStream(p.getPath()))
		{
			int img = file.read();
			
			while(img != -1)
			{
				buffer.write(img);
				img = file.read();
			}
			
			stmt.setString(1, p.getFirstName());
			stmt.setString(2, p.getLastName());
			stmt.setString(3, p.getPhoneNumber());
			stmt.setString(4, p.getEmail());
			stmt.setString(5, p.getDate());
			stmt.setString(6, p.getGender().toString());
			stmt.setString(7, p.getZipCode());
			stmt.setBytes(8, buffer.toByteArray());
			stmt.setString(9, p.getCpf());
			
			stmt.execute();
			
			return true;
		}
		catch(SQLException | IOException ex)
		{
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(ClientVO p) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("delete from `client` where `cpf` = ?"))
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
	public List<ClientVO> list()
	{
		List<ClientVO> l = new ArrayList<ClientVO>();
		
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select `client`.`cpf`, `client`.`firstName`, `client`.`lastName`, `client`.`phoneNumber`, `client`.`email`, `client`.`dateBorn`, `client`.`gender`, `client`.`zipCode`, `client`.`photo` from `client` where `client`.`cpf` not in (select `cpf` from `user`)"))
		{			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String cpf = rs.getString(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String phoneNumber = rs.getString(4);
				String email = rs.getString(5);
				String date = rs.getString(6);
				Gender gender = Gender.valueOf(rs.getString(7));
				String zipCode = rs.getString(8);
				byte[] b = rs.getBytes(9);
				
				l.add(new ClientVO(cpf, firstName, lastName, phoneNumber, email, date, gender, zipCode, b));				
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
