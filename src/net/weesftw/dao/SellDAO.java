package net.weesftw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.weesftw.manager.Database;
import net.weesftw.vo.ClientVO;
import net.weesftw.vo.SellVO;
import net.weesftw.vo.SellVO.Status;
import net.weesftw.vo.UserVO;

public class SellDAO implements DataAcess<SellVO> 
{
	public String getIdByClient(String args)
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select (`id`) from `sell` where `client` = ? order by `id` desc limit 1;"))
		{
			stmt.setString(1, args);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				return rs.getString(1);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public boolean create(SellVO e) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("insert into `sell` (`client`, `by`, `observation`) value (?, ?, ?)"))
		{			
			stmt.setString(1, e.getPeople().getCpf());
			stmt.setString(2, e.getBy().getCpf());
			stmt.setString(3, e.getObservation());
			
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
	public SellVO read(String args) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select * from `sell` where `id` = ?"))
		{
			stmt.setString(1, args);
			
			ResultSet rs = stmt.executeQuery();
			
			UserDAO ud = new UserDAO();
			ClientDAO pd = new ClientDAO();
			
			while(rs.next())
			{
				String id = rs.getString(1);
				UserVO by = ud.read(rs.getString(2));
				ClientVO cpf = pd.read(rs.getString(3));				
				Timestamp timestamp = rs.getTimestamp(4);
				String observation = rs.getString(5);
				Status status = Status.valueOf(rs.getString(6).toUpperCase());
				
				return new SellVO(id, by, cpf, observation, timestamp, status);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean update(SellVO e) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("update `sell` set `status` = ? where `id` = ?"))
		{
			stmt.setString(1, e.getStatus().name());
			stmt.setString(2, e.getId());
			
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
	public boolean delete(SellVO e)
	{
		return false;
	}

	@Override
	public List<SellVO> list() 
	{
		List<SellVO> l = new ArrayList<SellVO>();
		
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select * from `sell`"))
		{
			ResultSet rs = stmt.executeQuery();
			
			UserDAO ud = new UserDAO();
			ClientDAO pd = new ClientDAO();
			
			while(rs.next())
			{
				String id = rs.getString(1);
				UserVO by = ud.read(rs.getString(2));
				ClientVO cpf = pd.read(rs.getString(3));				
				Timestamp timestamp = rs.getTimestamp(4);
				String observation = rs.getString(5);
				Status status = Status.valueOf(rs.getString(6).toUpperCase());
				
				l.add(new SellVO(id, by, cpf, observation, timestamp, status));
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
