package net.weesftw.vo;

import java.sql.Timestamp;

import net.weesftw.constraint.Status;
import net.weesftw.view.Main;

public class SellVO 
{
	private int id;
	private ClientVO cpf;
	private CompanyVO cnpj;
	private String observation;
	private UserVO by;
	private Timestamp timestamp;
	private Status status;
	
	public SellVO(int id, UserVO by, ClientVO cpf, CompanyVO cnpj, String observation, Timestamp timestamp, Status status) 
	{
		this.id = id;
		this.by = by;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.observation = observation;
		this.timestamp = timestamp;
		this.status = status;
	}
	
	public SellVO(CompanyVO cnpj, String observation) 
	{
		this.by = Main.getInstance().getAuth().getUser();
		this.cnpj = cnpj;
		this.observation = observation;
	}
	
	public SellVO(ClientVO cpf, String observation) 
	{
		this.by = Main.getInstance().getAuth().getUser();
		this.cpf = cpf;
		this.observation = observation;
	}

	public int getId() 
	{
		return id;
	}

	public ClientVO getPeople() 
	{
		return cpf;
	}

	public CompanyVO getCompany() 
	{
		return cnpj;
	}

	public String getObservation() 
	{
		return observation;
	}

	public UserVO getBy() 
	{
		return by;
	}
	
	public Timestamp getTimestamp()
	{
		return timestamp;
	}
	
	public Status getStatus()
	{
		return status;
	}
}
