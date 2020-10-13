package net.weesftw.vo;

import net.weesftw.constraint.Gender;
import java.util.ArrayList;
import java.util.List;
import net.weesftw.dao.ClientDAO;
       
public class ClientVO 
{
        public static final List<ClientVO> LIST = new ClientDAO().list(); 
      
	private String cpf, firstName, lastName, phoneNumber, email, zipCode, date, path;
	private Gender gender;
	private byte[] b;
	
	public ClientVO(String cpf, String firstName, String lastName, String phoneNumber, String email, String date, Gender gender, String zipCode, byte[] b) 
	{
		this.cpf = cpf;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.date = date;
		this.gender = gender;
		this.zipCode = zipCode;
		this.b = b;
	}

	public ClientVO(String cpf, String firstName, String lastName, String phoneNumber, String email, String date, Gender gender, String zipCode, String path) 
	{
		this.cpf = cpf;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.date = date;
		this.gender = gender;
		this.zipCode = zipCode;
		this.path = path;
	}

	public String getCpf() 
	{
		return cpf;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public String getPhoneNumber() 
	{
		return phoneNumber;
	}

	public String getEmail() 
	{
		return email;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public Gender getGender()
	{
		return gender;
	}

	public String getZipCode() 
	{
		return zipCode;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public byte[] getByte()
	{
		return b;
	}
}
