package net.weesftw.vo;

import java.io.File;

public class PeopleVO 
{
	private String cpf, firstName, lastName, phoneNumber, email, zipCode, department, date;
	private File img;
	private byte[] imgLoad;

	public PeopleVO(String cpf, String firstName, String lastName, String phoneNumber, String email, String date, String zipCode,
			String department, File img) 
	{
		this.cpf = cpf;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.date = date;
		this.zipCode = zipCode;
		this.department = department;
		this.img = img;
	}
	
	public PeopleVO(String cpf, String firstName, String lastName, String phoneNumber, String email, String date, String zipCode,
			String department, byte[] imgLoad) 
	{
		this.cpf = cpf;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.date = date;
		this.zipCode = zipCode;
		this.department = department;
		this.imgLoad = imgLoad;
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

	public String getZipCode() 
	{
		return zipCode;
	}

	public String getDepartment() 
	{
		return department;
	}
	
	public File getImage()
	{
		return img;
	}
	
	public byte[] getImageLoad()
	{
		return imgLoad;
	}
}
