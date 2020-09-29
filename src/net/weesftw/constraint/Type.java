package net.weesftw.constraint;

public enum Type 
{
	CPF("cpf"), USERNAME("username");
	
	Type(String name)
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
