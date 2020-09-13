package net.weesftw.model;

import net.weesftw.constraint.Department;

public class User 
{
	private String cpf, username, passwd;
	private Department privilege;
	
	public User(String cpf, String username, String passwd, Department privilege)
	{
		this.cpf = cpf;
		this.username = username;
		this.passwd = passwd;
		this.privilege = privilege;
	}
	
	public String getCpf()
	{
		return cpf;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPasswd()
	{
		return passwd;
	}
	
	public Department getDepartment()
	{
		return privilege;
	}
}
