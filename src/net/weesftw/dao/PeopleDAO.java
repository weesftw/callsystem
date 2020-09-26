package net.weesftw.dao;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.weesftw.manager.Database;
import net.weesftw.vo.PeopleVO;

public class PeopleDAO implements DataAcess<PeopleVO>
{
	@Override
	public boolean add(PeopleVO p) 
	{
		try(Database d = new Database(); 
				PreparedStatement stmt = d.con.prepareStatement("insert into `people` (`cpf`, `firstName`, `lastName`, `phoneNumber`, `email`, `dateBorn`, `zipCode`, `department`, `photo`) value (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				FileInputStream file = new FileInputStream(p.getImage()))
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
			stmt.setString(7, p.getZipCode());
			stmt.setString(8, p.getDepartment());
			stmt.setBytes(9, buffer.toByteArray()); 
			
			stmt.execute();
			
			return true;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		catch(IOException ex1)
		{
			ex1.printStackTrace();
		}
		
		return false;
	}

	@Override
	public PeopleVO search(String args) 
	{
		try(Database d = new Database(); 
				PreparedStatement stmt = d.con.prepareStatement("select * from `people` where cpf = ?");
				ResultSet rs = stmt.executeQuery())
		{
			stmt.setString(1, args);
			
			while(rs.next())
			{
				String cpf = rs.getString(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String phoneNumber = rs.getString(4);
				String email = rs.getString(5);
				String date = rs.getString(6);
				String zipCode = rs.getString(7);
				String department = rs.getString(8);
				byte[] img = rs.getBytes(9);
				
				return new PeopleVO(cpf, firstName, lastName, phoneNumber, email, date, zipCode, department, img);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean update(String column, String value, PeopleVO p) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("update `people` set `?` = ? where `cpf` = ?"))
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
	public boolean remove(PeopleVO p) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("delete from `people` where `cpf` = ?"))
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
	public List<PeopleVO> list()
	{
		List<PeopleVO> l = new ArrayList<PeopleVO>();
		
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select * from `people`");
				ResultSet rs = stmt.executeQuery())
		{			
			while(rs.next())
			{
				String cpf = rs.getString(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String phoneNumber = rs.getString(4);
				String email = rs.getString(5);
				String date = rs.getString(6);
				String zipCode = rs.getString(7);
				String department = rs.getString(8);
				byte[] imgLoad = rs.getBytes(9);
				
				l.add(new PeopleVO(cpf, firstName, lastName, phoneNumber, email, date, zipCode, department, imgLoad));
				
				return l;
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}
}
