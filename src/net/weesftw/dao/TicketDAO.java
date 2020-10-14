package net.weesftw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.weesftw.constraint.Category;
import net.weesftw.constraint.Status;
import net.weesftw.manager.Database;
import net.weesftw.vo.CompanyVO;
import net.weesftw.vo.ClientVO;
import net.weesftw.vo.ProductVO;
import net.weesftw.vo.TicketVO;
import net.weesftw.vo.UserVO;

public class TicketDAO implements DataAcess<TicketVO>
{
	public int getTicketOpen()
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select count(`id`) from `ticket` where `status` = 'Open'"))
		{
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				return rs.getInt(1);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public boolean create(TicketVO e) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("insert into `ticket` (`title`, `client`, `company`, `user`, `category`, `product`, `description`, `priority`) value (?, ?, ?, ?, ?, ?, ?, ?)"))
		{
			stmt.setString(1, e.getTitle());
			stmt.setString(2, e.getClient().getCpf());
			stmt.setString(3, e.getCompany() != null ? e.getCompany().getCnpj() : null);
			stmt.setString(4, e.getUser().getCpf());
			stmt.setInt(5, e.getCategory().getId());
			stmt.setString(6, e.getProduct().getId());
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
	public TicketVO read(String id) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select `ticket`.`id`, `ticket`.`title`, `ticket`.`client`, `ticket`.`company`, `ticket`.`user`, `ticket`.`time`, `category`.`name`, `product`.`name`, `ticket`.`description`, `ticket`.`solution`, `ticket`.`priority`, `ticket`.`status` from `ticket` join `category` on `category`.`id` = `ticket`.`id` join `product` on `product`.`id` = `ticket`.`product` where `ticket`.`id` = ?"))
		{
			stmt.setString(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			CompanyDAO cd = new CompanyDAO();
			ProductDAO pd = new ProductDAO();
			ClientDAO pdd = new ClientDAO();
			UserDAO ud = new UserDAO();
			
			while(rs.next())
			{
				int id1 = rs.getInt(1);
				String title = rs.getString(2);
				ClientVO client = pdd.read(rs.getString(3));
				CompanyVO company = cd.read(rs.getString(4));
				UserVO user = ud.searchByUser(rs.getString(5));
				Timestamp time = rs.getTimestamp(6);
				Category category = Category.valueOf(rs.getString(7));
				ProductVO product = pd.searchByName(rs.getString(8));
				String description = rs.getString(9);
				String solution = rs.getString(10);
				boolean priority = rs.getBoolean(11);
				Status status = Status.valueOf(rs.getString(12));
				
				return new TicketVO(id1, title, client, company, user, description, solution, time, category, product, status, priority);
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
				PreparedStatement stmt = d.con.prepareStatement("select `ticket`.`id`, `ticket`.`title`, `ticket`.`client`, `ticket`.`company`, `ticket`.`user`, `ticket`.`time`, `category`.`name`, `ticket`.`product`, `ticket`.`description`, `ticket`.`solution`, `ticket`.`priority`, `ticket`.`status` from `ticket` join `category` on `ticket`.`category` = `category`.`id`"))
		{
			ResultSet rs = stmt.executeQuery();
			
			CompanyDAO cd = new CompanyDAO();
			ProductDAO pd = new ProductDAO();
			ClientDAO pdd = new ClientDAO();
			UserDAO ud = new UserDAO();
			
			while(rs.next())
			{
				int id = rs.getInt(1);
				String title = rs.getString(2);
				ClientVO client = pdd.read(rs.getString(3));
				CompanyVO company = cd.read(rs.getString(4));
				UserVO user = ud.read(rs.getString(5));
				Timestamp time = rs.getTimestamp(6);
				Category category = Category.valueOf(rs.getString(7).toUpperCase());
				ProductVO product = pd.read(rs.getString(8));
				String description = rs.getString(9);
				String solution = rs.getString(10);
				boolean priority = rs.getBoolean(11);
				Status status = Status.valueOf(rs.getString(12).toUpperCase());
				
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
