package net.weesftw.manager;

import net.weesftw.vo.ClientVO;
import net.weesftw.vo.UserVO;

public class Authenticate 
{
	private ClientVO p;
	private UserVO u;
	
	public Authenticate(ClientVO p, UserVO u)
	{
		this.p = p;
		this.u = u;
	}
	
	public ClientVO getPeople()
	{
		return p;
	}
	
	public UserVO getUser()
	{
		return u;
	}

	public boolean isPrivilege()
	{
		return u.getDepartment().isPrivilege();
	}
}
