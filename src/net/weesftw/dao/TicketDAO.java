package net.weesftw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.weesftw.constraint.Category;
import net.weesftw.constraint.Product;
import net.weesftw.constraint.Status;
import net.weesftw.manager.Database;
import net.weesftw.vo.TicketVO;

public class TicketDAO implements DataAcess<TicketVO>
{
	@Override
	public boolean create(TicketVO e) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("insert into `ticket` (`title`, `client`, `company`, `user`, `category`, `product`, `description`, `priority`) value (?, ?, ?, ?, ?, ?, ?, ?)"))
		{
			stmt.setString(1, e.getTitle());
			stmt.setString(2, e.getClient());
			stmt.setString(3, e.getCompany());
			stmt.setString(4, e.getUser());
			stmt.setInt(5, e.getCategory().getId());
			stmt.setInt(6, e.getProduct().getId());
			stmt.setString(7, e.getDescription());
			stmt.setBoolean(8, e.isPriority());
			
			stmt.execute();
			
			return true;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public TicketVO read(TicketVO e) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select `ticket`.`id`, `ticket`.`title`, `ticket`.`client`, `ticket`.`company`, `ticket`.`user`, `ticket`.`time`, `category`.`name`, `product`.`name`, `ticket`.`description`, `ticket`.`solution`, `ticket`.`priority`, `ticket`.`status` from `ticket` join `category` on `category`.`id` = `ticket`.`id` join `product` on `product`.`id` = `ticket`.`product`"))
		{
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String client = rs.getString(3);
				String company = rs.getString(4);
				String user = rs.getString(5);
				Timestamp time = rs.getTimestamp(6);
				Category category = Category.valueOf(rs.getString(7));
				Product product = Product.valueOf(rs.getString(8));
				String description = rs.getString(9);
				String solution = rs.getString(10);
				boolean priority = rs.getBoolean(11);
				Status status = Status.valueOf(rs.getString(12));
				
				return new TicketVO(id, title, client, company, user, description, solution, time, category, product, status, priority);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean update(TicketVO e) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("update `ticket` set `solution` = ?, `priority` = ?, `status` = ? where `id` = ?"))
		{
			stmt.setString(1, e.getSolution());
			stmt.setBoolean(2, e.isPriority());
			stmt.setString(3, e.getStatus().name());
			stmt.setInt(4, e.getId());
			
			stmt.execute();
			
			return true;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(TicketVO e) 
	{
		return false;
	}

	@Override
	public List<TicketVO> list() 
	{
		List<TicketVO> l = new ArrayList<TicketVO>();
		
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select `ticket`.`id`, `ticket`.`title`, `people`.`firstName` as `firstName`, `people`.`lastName` as `lastName`, `company`.`name` as `company`, `user`.`username` as `user`, `ticket`.`time`, `category`.`name` as `category`, `product`.`name` as `product`, `ticket`.`description`, `ticket`.`solution`, `ticket`.`priority`, `ticket`.`status` from `ticket` join `category` on `category`.`id` = `ticket`.`category` join `product` on `product`.`id` = `ticket`.`product` join `people` on `people`.`cpf` = `ticket`.`client` join `company` on `company`.`cnpj` = `ticket`.`company` join `user` on `user`.`cpf` = `ticket`.`user`"))
		{
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String client = rs.getString(3) + " " + rs.getString(4);
				String company = rs.getString(5);
				String user = rs.getString(6);
				Timestamp time = rs.getTimestamp(7);				
				Category category = Category.valueOf(rs.getString(8));
				Product product = Product.valueOf(rs.getString(9));
				String description = rs.getString(10);
				String solution = rs.getString(11);
				boolean priority = rs.getBoolean(12);
				Status status = Status.valueOf(rs.getString(13));
				
				l.add(new TicketVO(id, title, client, company, user, description, solution, time, category, product, status, priority));				
			}
			
			return l;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}
}
