package net.weesftw.constraint;

public enum ImagePath 
{
	ICON("/icon.png"), SEARCH("/search.png");
		
	private String name;
	
	ImagePath(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
