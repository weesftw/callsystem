package net.weesftw.vo;

import java.sql.Timestamp;

import net.weesftw.constraint.Category;
import net.weesftw.constraint.Product;
import net.weesftw.constraint.Status;

public class TicketVO 
{	
	private int id;
	private String title, client, company, user, description, solution;
	private Timestamp time;
	private Category category;
	private Product product;
	private Status status;
	private boolean priority;
	
	public TicketVO(String title, String client, String company, String user, String description, Category category, Product product, boolean priority)
	{
		this.title = title;
		this.client = client;
		this.company = company;
		this.user = user;
		this.description = description;
		this.category = category;
		this.product = product;
		this.priority = priority;
	}
	
	public TicketVO(int id, String title, String client, String company, String user, String description, String solution, Timestamp time, Category category, Product product, Status status, boolean priority) 
	{
		this.id = id;
		this.title = title;
		this.client = client;
		this.company = company;
		this.user = user;
		this.description = description;
		this.solution = solution;
		this.time = time;
		this.category = category;
		this.product = product;
		this.status = status;
		this.priority = priority;
	}

	public int getId() 
	{
		return id;
	}
	
	public String getTitle() 
	{
		return title;
	}
	
	public String getClient() 
	{
		return client;
	}
	
	public String getCompany()
	{
		return company;
	}
	
	public String getUser() 
	{
		return user;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public String getSolution() 
	{
		return solution;
	}
	
	public Timestamp getTimestamp()
	{
		return time;
	}
	
	public Category getCategory() 
	{
		return category;
	}
	
	public Product getProduct() 
	{
		return product;
	}
	
	public Status getStatus() 
	{
		return status;
	}
	
	public boolean isPriority() 
	{
		
		return priority;
	}	
}
