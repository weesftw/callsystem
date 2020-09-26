package net.weesftw.vo;

import java.sql.Timestamp;

import net.weesftw.constraint.Status;

public class TicketVO 
{
	private int id;
	private Timestamp time;
	private Status status;
	private String title, description;
	private PeopleVO client;
	private CompanyVO company;
	private boolean priority;
	private UserVO user;
	
	public TicketVO(int id, String title, PeopleVO client, CompanyVO company, String description, Timestamp time, UserVO user, boolean priority, Status status) 
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

	public PeopleVO getAuthor() 
	{
		return client;
	}
	
	public CompanyVO getCompany()
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
	
	public UserVO getUser() 
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
