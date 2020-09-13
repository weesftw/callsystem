package net.weesftw.constraint;

public enum Department 
{
	FINANCIAL(0), HR(0), COMMERCIAL(0), TECH(1), ADMINISTRATOR(1);
	
	Department(int id)
	{
		this.id = id;
	}
	
	private int id;
	
	public int getId()
	{
		return id;
	}
}
