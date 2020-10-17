package net.weesftw.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.weesftw.view.Main;

public class SellVO 
{
	public static List<SellVO> list = new ArrayList<>();
	
	private String id;
	private ClientVO cpf;
//	private CompanyVO cnpj;
	private String observation;
	private UserVO by;
	private Timestamp timestamp;
	private Status status;
	
	public enum Status
	{
		PENDENT, CANCELED, COMPLETE;
	}
	
	public SellVO(String id, UserVO by, ClientVO cpf, String observation, Timestamp timestamp, Status status) 
	{
		this.id = id;
		this.by = by;
		this.cpf = cpf;
//		this.cnpj = cnpj;
		this.observation = observation;
		this.timestamp = timestamp;
		this.status = status;
	}
	
	public SellVO(CompanyVO cnpj, String observation) 
	{
		this.by = Main.getInstance().getAuth().getUser();
//		this.cnpj = cnpj;
		this.observation = observation;
	}
	
	public SellVO(ClientVO cpf, String observation) 
	{
		this.by = Main.getInstance().getAuth().getUser();
		this.cpf = cpf;
		this.observation = observation;
	}

	public String getId() 
	{
		return id;
	}

	public ClientVO getPeople() 
	{
		return cpf;
	}

//	public CompanyVO getCompany() 
//	{
//		return cnpj;
//	}

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
