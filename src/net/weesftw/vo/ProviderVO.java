package net.weesftw.vo;

public class ProviderVO 
{
	private int id;
	private String name, zipCode, phoneNumber, freight;
	
	public ProviderVO(int id, String name, String zipCode, String phoneNumber, String freight) 
	{
		this.id = id;
		this.name = name;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
		this.freight = freight;
	}
	
	public ProviderVO(String name, String zipCode, String phoneNumber, String freight) 
	{
		this.name = name;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
		this.freight = freight;
	}

	public int getId() 
	{
		return id;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public String getZipCode() 
	{
		return zipCode;
	}
	
	public String getPhoneNumber() 
	{
		return phoneNumber;
	}
	
	public String getFreight() 
	{
		return freight;
	}
}
