package net.weesftw.vo;

import net.weesftw.constraint.Department;

public class UserVO 
{
	private String cpf, username, passwd;
	private Department department;
	
	public UserVO(String cpf, String username, String passwd, Department department)
	{
		this.cpf = cpf;
		this.username = username;
		this.passwd = passwd;
		this.department = department;
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
		return department;
	}
}
