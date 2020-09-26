package net.weesftw.vo;

public class CategoryVO 
{
	class Product
	{
		private int id;
		private String name;
		private CategoryVO category;
		
		public Product(int id, String name, CategoryVO category)
		{
			this.id = id;
			this.name = name;
			this.category = category;
		}
		
		public int getId()
		{
			return id;
		}
		
		public String getName()
		{
			return name;
		}
		
		public CategoryVO getCategory()
		{
			return category;
		}
	}
	
	private int id;
	private String name;
	
	public CategoryVO(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
}
