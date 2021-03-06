package net.weesftw.vo;

import java.util.ArrayList;
import java.util.List;

public class CompanyVO 
{
	public static List<CompanyVO> list = new ArrayList<>(); 
	
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
	
	@Override
	public boolean equals(Object obj) 
	{
		String other = (String) obj;
		
		if(cnpj.equalsIgnoreCase(other) || name.equalsIgnoreCase(other) || (owner.getFirstName() + " " + owner.getLastName()).equalsIgnoreCase(other) || zipCode.equalsIgnoreCase(other))
		{
			return true;
		}
		
		return false;
	}
}
