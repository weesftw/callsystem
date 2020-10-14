package net.weesftw.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.weesftw.constraint.Category;
import net.weesftw.constraint.Status;
import net.weesftw.view.Main;

public class TicketVO 
{
	public static List<TicketVO> list = new ArrayList<>(); 
	
	private int id;
	private String title, description, solution;
	private ClientVO client;
	private CompanyVO company;
	private Timestamp time;
	private Category category;
	private ProductVO product;
	private Status status;
	private boolean priority;
	private UserVO user;
	
	public TicketVO(String title, ClientVO client, String description, Category category, ProductVO product, boolean priority)
	{
		this.title = title;
		this.client = client;
		this.user = Main.getInstance().getAuth().getUser();
		this.description = description;
		this.category = category;
		this.product = product;
		this.priority = priority;
	}
	
	public TicketVO(String title, CompanyVO company, String description, Category category, ProductVO product, boolean priority)
	{
		this.title = title;
		this.company = company;
		this.user = Main.getInstance().getAuth().getUser();
		this.description = description;
		this.category = category;
		this.product = product;
		this.priority = priority;
	}
	
	public TicketVO(int id, String title, ClientVO client, CompanyVO company, UserVO user, String description, String solution, Timestamp time, Category category, ProductVO product, Status status, boolean priority) 
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
	
	public ClientVO getClient() 
	{
		return client;
	}
	
	public CompanyVO getCompany()
	{
		return company;
	}
	
	public UserVO getUser() 
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
	
	public ProductVO getProduct() 
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
