package net.weesftw.manager;

import net.weesftw.vo.PeopleVO;

public class Authenticate 
{
	private PeopleVO p;
	
	public Authenticate(PeopleVO p)
	{
		this.p = p;
	}
	
	public PeopleVO getPeople()
	{
		return p;
	}
}
