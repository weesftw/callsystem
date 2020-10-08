package net.weesftw.vo;

public class ProductVO 
{
	private int id;
	private String name, path, price;
	private ProviderVO provider;
	private byte[] photo;
	
	public ProductVO(int id, ProviderVO provider, String name, String price, byte[] photo) 
	{
		this.id = id;
		this.provider = provider;
		this.name = name;
		this.price = price;
		this.photo = photo;
	}
	
	public ProductVO(ProviderVO provider, String name, String price, String path) 
	{
		this.provider = provider;
		this.name = name;
		this.price = price;
		this.path = path;
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
	
	
}
