package net.weesftw.manager;

import net.weesftw.vo.PeopleVO;
import net.weesftw.vo.UserVO;

public class Authenticate 
{
	private PeopleVO p;
	private UserVO u;
	
	public Authenticate(PeopleVO p, UserVO u)
	{
		this.p = p;
		this.u = u;
	}
	
	public PeopleVO getPeople()
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
