package net.weesftw.vo;

import java.sql.Timestamp;

import net.weesftw.constraint.Status;

public class Ticket 
{
	private int id;
	private Timestamp time;
	private Status status;
	private String title, description;
	private People client;
	private Company company;
	private boolean priority;
	private User user;
	
	public Ticket(int id, String title, People client, Company company, String description, Timestamp time, User user, boolean priority, Status status) 
	{
		this.id = id;
		this.title = title;
		this.client = client;
		this.company = company;
		this.description = description;
		this.time = time;
		this.status = status;
		this.user = user;
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

	public People getAuthor() 
	{
		return client;
	}
	
	public Company getCompany()
	{
		return company;
	}

	public String getDescription() 
	{
		return description;
	}

	public Timestamp getTime() 
	{
		return time;
	}
	
	public User getUser() 
	{
		return user;
	}
	
	public boolean getPriority()
	{
		return priority;
	}

	public Status getStatus() 
	{
		return status;
	}
}
