package net.weesftw.vo;

import java.sql.Timestamp;

import net.weesftw.constraint.Category;
import net.weesftw.constraint.Product;
import net.weesftw.constraint.Status;

public class TicketVO 
{	
	private int id;
	private UserVO user;
	private Status status;
	private Timestamp time;
	private Product product;
	private PeopleVO client;
	private boolean priority;
	private CompanyVO company;
	private Category category;
	private String title, description;
	
	public TicketVO(int id, UserVO user, Status status, Timestamp time, Product product, PeopleVO client, boolean priority, CompanyVO company, Category category, String title, String description) 
	{
		this.id = id;
		this.user = user;
		this.status = status;
		this.time = time;
		this.product = product;
		this.client = client;
		this.priority = priority;
		this.company = company;
		this.category = category;
		this.title = title;
		this.description = description;
	}

	public int getId() 
	{
		return id;
	}

	public UserVO getUser() 
	{
		return user;
	}

	public Status getStatus() 
	{
		return status;
	}

	public Timestamp getTime() 
	{
		return time;
	}

	public Product getProduct() 
	{
		return product;	
	}

	public PeopleVO getClient() 
	{
		return client;
	}

	public boolean isPriority() 
	{
		return priority;
	}

	public CompanyVO getCompany() 
	{
		return company;
	}

	public Category getCategory()
	{
		return category;
	}

	public String getTitle() 
	{
		return title;
	}

	public String getDescription() 
	{
		return description;
	}
}
