package net.weesftw.constraint;

public enum Department 
{
	ADMINISTRATOR(1, true, "Cadastro de Clientes, empresas, chamados, produtos, fornecedores e usuários, responder, visualizar e editar informações do mesmo."),
	TI(2, true, "Cadastro de Clientes, empresas, chamados, produtos, fornecedores e usuários, responder, visualizar e editar informações do mesmo."),
	RH(3, false, "Cadastro de Clientes, empresas e chamados, visualizar informações do mesmo."),
	ATTENDANCE(4, false, "Cadastro de Clientes, empresas e chamados, visualizar informações do mesmo.");
	
	private int id;
	private boolean privilege;
	private String description;
	
	Department(int id, boolean privilege, String description)
	{
		this.id = id;
		this.privilege = privilege;
		this.description = description;
	}
	
	public boolean isPrivilege()
	{
		return privilege;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public int getId()
	{
		return id;
	}
}
