package net.weesftw.constraint;

public enum Department 
{
	ADMINISTRATOR(1, true), TI(2, true), RH(3, false), ATTENDANCE(4, false);
	
	private int id;
	private boolean privilege;
	
	Department(int id, boolean privilege)
	{
		this.id = id;
		this.privilege = privilege;
	}
	
	public boolean isPrivilege()
	{
		return privilege;
	}
	
	public int getId()
	{
		return id;
	}
}
