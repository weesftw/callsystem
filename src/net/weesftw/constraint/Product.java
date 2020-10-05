package net.weesftw.constraint;

public enum Product 
{
	MOVEL(1), INTERNET(2), TV(3);
	
	Product(int id)
	{
		this.id = id;
	}
	
	private int id;
	
	public int getId()
	{
		return id;
	}
}
