package net.weesftw.vo;

import java.sql.Timestamp;

import net.weesftw.constraint.Status;
import net.weesftw.view.Main;

public class SellVO 
{
	private int id;
	private PeopleVO cpf;
	private CompanyVO cnpj;
	private CartVO cart;
	private String observation;
	private UserVO by;
	private Timestamp timestamp;
	private Status status;
	
	public SellVO(int id, CartVO cart, UserVO by, PeopleVO cpf, CompanyVO cnpj, String observation, Timestamp timestamp, Status status) 
	{
		this.id = id;
		this.cart = cart;
		this.by = by;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.observation = observation;
		this.timestamp = timestamp;
		this.status = status;
	}
	
	public SellVO(CartVO cart, CompanyVO cnpj, String observation) 
	{
		this.cart = cart;
		this.by = Main.instance.getAuth().getUser();
		this.cnpj = cnpj;
		this.observation = observation;
	}
	
	public SellVO(CartVO cart, PeopleVO cpf, String observation) 
	{
		this.cart = cart;
		this.by = Main.instance.getAuth().getUser();
		this.cpf = cpf;
		this.observation = observation;
	}

	public int getId() 
	{
		return id;
	}

	public PeopleVO getPeople() 
	{
		return cpf;
	}

	public CompanyVO getCompany() 
	{
		return cnpj;
	}

	public CartVO getCart() 
	{
		return cart;
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
