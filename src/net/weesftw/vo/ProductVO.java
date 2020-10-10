package net.weesftw.vo;

public class ProductVO 
{
	private int id;
	private String name, path, price, weight, length, width, height;
	private ProviderVO provider;
	private byte[] photo;
	
	public ProductVO(int id, ProviderVO provider, String name, String price, byte[] photo, String weight, String length, String width, String height) 
	{
		this.id = id;
		this.provider = provider;
		this.name = name;
		this.price = price;
		this.photo = photo;
		this.weight = weight;
		this.length = length;
		this.width = width;
		this.height = height;
	}
	
	public ProductVO(ProviderVO provider, String name, String price, String path, String weight, String length, String width, String height) 
	{
		this.provider = provider;
		this.name = name;
		this.price = price;
		this.path = path;
		this.weight = weight;
		this.length = length;
		this.width = width;
		this.height = height;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public ProviderVO getProvider() 
	{
		return provider;
	}
	
	public String getPath() 
	{
		return path;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getPrice()
	{
		return price;
	}
	
	public byte[] getPhoto()
	{
		return photo;
	}

	public String getWeight() 
	{
		return weight;
	}

	public String getLength() 
	{
		return length;
	}

	public String getWidth() 
	{
		return width;
	}

	public String getHeight() 
	{
		return height;
	}
}
