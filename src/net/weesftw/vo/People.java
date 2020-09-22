package net.weesftw.vo;

public class People 
{
	private String cpf, firstName, lastName, phoneNumber, email, zipCode, department;

	public People(String cpf, String firstName, String lastName, String phoneNumber, String email, String zipCode,
			String department) 
	{
		this.cpf = cpf;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.zipCode = zipCode;
		this.department = department;
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

	public String getZipCode() 
	{
		return zipCode;
	}

	public String getDepartment() 
	{
		return department;
	}
}
