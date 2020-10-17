package net.weesftw.constraint;

public enum Category 
{
	CANCELAMENT(1), FINANCIAL(2), SUPPORT(3);
	
	private int id;
	
	Category(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
}
