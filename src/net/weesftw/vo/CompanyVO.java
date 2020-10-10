package net.weesftw.vo;

public class CompanyVO 
{
	private String cnpj, name, zipCode;
	private PeopleVO owner;
	
	public CompanyVO(String cnpj, String name, PeopleVO owner, String zipCode)
	{
		this.cnpj = cnpj;
		this.name = name;
		this.owner = owner;
		this.zipCode = zipCode;
	}

	public String getCnpj() 
	{
		return cnpj;
	}

	public String getName() 
	{
		return name;
	}

	public PeopleVO getOwner() 
	{
		return owner;
	}

	public String getZipCode() 
	{
		return zipCode;
	}
}
