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
import net.weesftw.constraint.Type;
import net.weesftw.exception.TypeException;
import net.weesftw.manager.Database;
import net.weesftw.vo.PeopleVO;

public class PeopleDAO implements DataAcess<PeopleVO>
{	
	public PeopleVO search(Type type, String args) throws TypeException
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select `people`.`cpf`, `people`.`firstName`, `people`.`lastName`, `people`.`phoneNumber`, `people`.`email`, `people`.`dateBorn`, `people`.`gender`, `people`.`zipCode`, `people`.`photo` as `people` from `people` where `people`.`cpf` = ?");
				PreparedStatement stmt2 = d.con.prepareStatement("select `people`.`cpf`, `people`.`firstName`, `people`.`lastName`, `people`.`phoneNumber`, `people`.`email`, `people`.`dateBorn`, `people`.`gender`, `people`.`zipCode`, `people`.`photo` as `people` from `people` join `user` on `people`.`cpf` = `user`.`cpf` where `user`.`username` = ?"))
		{
			stmt.setString(1, args);
			stmt2.setString(1, args);
			
			ResultSet rs = null;
			
			if(type == Type.CPF)
			{
				rs = stmt.executeQuery();
			}
			else if(type == Type.USERNAME)
			{
				rs = stmt2.executeQuery();
			}
			else
			{
				throw new TypeException();
			}
			
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
				
				return new PeopleVO(cpf, firstName, lastName, phoneNumber, email, date, gender, zipCode, b);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public boolean add(PeopleVO p) 
	{
		try(Database d = new Database(); 
				PreparedStatement stmt = d.con.prepareStatement("insert into `people` (`cpf`, `firstName`, `lastName`, `phoneNumber`, `email`, `dateBorn`, `gender`, `zipCode`, `photo`) value (?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
	public PeopleVO search(PeopleVO p) 
	{
		try(Database d = new Database(); 
				PreparedStatement stmt = d.con.prepareStatement("select * from `people` where cpf = ?"))
		{
			stmt.setString(1, p.getCpf());

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
				String zipCode = rs.getString(7);
				byte[] img = rs.getBytes(9);
				
				return new PeopleVO(cpf, firstName, lastName, phoneNumber, email, date, gender, zipCode, img);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean update(PeopleVO p) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("update `people` set `firstName` = ?, `lastName` = ?, `phoneNumber` = ?, `email` = ?, `dateBorn` = ?, `zipCode` = ?, `photo` = ? where `cpf` = ?");
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
				PreparedStatement stmt = d.con.prepareStatement("select * from `people`"))
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
				byte[] img = rs.getBytes(9);
				
				l.add(new PeopleVO(cpf, firstName, lastName, phoneNumber, email, date, gender, zipCode, img));
				
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
