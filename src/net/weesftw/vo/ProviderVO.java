package net.weesftw.vo;

import java.util.ArrayList;
import java.util.List;

public class ProviderVO 
{
	public static List<ProviderVO> list = new ArrayList<>(); 
	
	private String cnpj, name, zipCode, phoneNumber, freight, category;
	
	public ProviderVO(String cnpj, String name, String zipCode, String phoneNumber, String freight, String category) 
	{
		this.cnpj = cnpj;
		this.name = name;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
		this.freight = freight;
		this.category = category;
	}

	public String getCnpj() 
	{
		return cnpj;
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

	public String getCategory() 
	{
		return category;
	}
}
