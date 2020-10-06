package net.weesftw.constraint;

public enum ImagePath 
{
	ICON("/icon.png"), SEARCH("/search.png");
	
	ImagePath(String name)
	{
		this.name = name;
	}
	
	private String name;
	
	@Override
	public String toString()
	{
		return name;
	}
}
