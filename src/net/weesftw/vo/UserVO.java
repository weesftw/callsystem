package net.weesftw.vo;

public class UserVO 
{
	private String cpf, username, passwd;
	
	public UserVO(String cpf, String username, String passwd)
	{
		this.cpf = cpf;
		this.username = username;
		this.passwd = passwd;
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
}
