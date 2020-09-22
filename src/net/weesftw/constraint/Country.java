package net.weesftw.constraint;

public enum Country 
{
	BR("Brazil", 55);
	
	Country(String name, int code)
	{
		this.name = name;
		this.code = code;
	}
	
	private int code;
	private String name;
	
	public int getCode()
	{
		return code;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
