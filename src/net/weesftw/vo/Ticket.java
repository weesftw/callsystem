package net.weesftw.vo;

import java.sql.Timestamp;

import net.weesftw.constraint.Status;

public class Ticket 
{
	private int id;
	private String tittle, author, description, company;
	private Timestamp time;
	private Status status;
	
	public Ticket(int id, String tittle, String author, String company, String description, Timestamp time, Status status) 
	{
		this.id = id;
		this.tittle = tittle;
		this.author = author;
		this.company = company;
		this.description = description;
		this.time = time;
		this.status = status;
	}

	public int getId() 
	{
		return id;
	}

	public String getTittle() 
	{
		return tittle;
	}

	public String getAuthor() 
	{
		return author;
	}
	
	public String getCompany()
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

	public Status getStatus() 
	{
		return status;
	}
}
