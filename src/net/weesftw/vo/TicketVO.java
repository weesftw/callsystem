package net.weesftw.vo;

import java.sql.Timestamp;

import net.weesftw.constraint.Category;
import net.weesftw.constraint.Product;
import net.weesftw.constraint.Status;

public class TicketVO 
{	
	private int id;
	private Status status;
	private Timestamp timestamp;
	private Product product;
	private boolean priority;
	private Category category;
	private String title, description, company, client, user, solution;

	public TicketVO(int id, Status status, Timestamp timestamp, Product product, boolean priority, Category category, String title, String description, String company, String client, String user, String solution) 
	{
		this.id = id;
		this.status = status;
		this.timestamp = timestamp;
		this.product = product;
		this.priority = priority;
		this.category = category;
		this.title = title;
		this.description = description;
		this.company = company;
		this.client = client;
		this.user = user;
		this.solution = solution;
	}
	
	public TicketVO(int id, Status status, Product product, boolean priority, Category category, String title, String description, String company, String client, String user, String solution) 
	{
		this.id = id;
		this.status = status;
		this.product = product;
		this.priority = priority;
		this.category = category;
		this.title = title;
		this.description = description;
		this.company = company;
		this.client = client;
		this.user = user;
		this.solution = solution;
	}
	
	public TicketVO(Product product, boolean priority, Category category, String title, String description, String company, String client, String user, String solution) 
	{
		this.product = product;
		this.priority = priority;
		this.category = category;
		this.title = title;
		this.description = description;
		this.company = company;
		this.client = client;
		this.user = user;
		this.solution = solution;
	}

	public int getId() 
	{
		return id;
	}

	public Status getStatus() 
	{
		return status;
	}

	public Timestamp getTimestamp() 
	{
		return timestamp;
	}

	public Product getProduct() 
	{
		return product;
	}

	public boolean isPriority() 
	{
		return priority;
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

	public String getCompany() 
	{
		return company;
	}

	public String getClient() 
	{
		return client;
	}

	public String getUser() 
	{
		return user;
	}
	
	public String getSolution()
	{
		return solution;
	}
}
