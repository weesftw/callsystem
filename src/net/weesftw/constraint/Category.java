package net.weesftw.constraint;

public enum Category 
{
	CANCELAMENT(1), FINANCIAL(2), SUPPORT(3), UPGRADE(4);
	
	Category(int id)
	{
		this.id = id;
	}
	
	private int id;
	
	public int getId()
	{
		return id;
	}
}
