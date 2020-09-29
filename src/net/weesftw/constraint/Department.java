package net.weesftw.constraint;

public enum Department 
{
	ADMINISTRATOR(1, true), TI(2, true), RH(3, false), ATTENDANCE(4, false);
	
	Department(int id, boolean privilege)
	{
		this.id = id;
		this.privilege = privilege;
	}
	
	private int id;
	private boolean privilege;
	
	public boolean getPrivilege()
	{
		return privilege;
	}
	
	public int getId()
	{
		return id;
	}
}
