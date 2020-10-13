package net.weesftw.vo;

import java.util.ArrayList;
import java.util.List;
import net.weesftw.constraint.Department;
import net.weesftw.dao.UserDAO;

public class UserVO 
{
	public static final List<UserVO> LIST = new UserDAO().list();
        
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
