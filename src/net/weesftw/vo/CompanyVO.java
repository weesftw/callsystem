package net.weesftw.vo;

public class CompanyVO 
{
	private String cnpj, name, zipCode;
	private ClientVO owner;
	
	public CompanyVO(String cnpj, String name, ClientVO owner, String zipCode)
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

	public ClientVO getOwner() 
	{
		return owner;
	}

	public String getZipCode() 
	{
		return zipCode;
	}
}
