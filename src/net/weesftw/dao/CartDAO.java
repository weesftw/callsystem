package net.weesftw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.weesftw.manager.Database;
import net.weesftw.vo.CartVO;
import net.weesftw.vo.ProductVO;

public class CartDAO implements DataAcess<CartVO> 
{
	@Override
	public boolean create(CartVO e) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("insert into `cart` (`product`, `amount`) value (?, ?)"))
		{
			stmt.setInt(1, e.getProduct().getId());
			stmt.setString(2, e.getAmount());
			
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
	public CartVO read(String args) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select * from `cart` where `id` = ?"))
		{
			stmt.setString(1, args);
			
			ResultSet rs = stmt.executeQuery();
			
			ProductDAO pd = new ProductDAO();
			
			while(rs.next())
			{
				String id = rs.getString(1);
				ProductVO product = pd.read(rs.getString(2));
				String amount = rs.getString(3);
				
				return new CartVO(id, amount, product);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean update(CartVO e) 
	{		
		return false;
	}

	@Override
	public boolean delete(CartVO e) 
	{
		return false;
	}

	@Override
	public List<CartVO> list() 
	{
		List<CartVO> l = new ArrayList<CartVO>();
		
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select * from `cart`"))
		{
			ResultSet rs = stmt.executeQuery();
			
			ProductDAO pd = new ProductDAO();
			
			while(rs.next())
			{
				String id = rs.getString(1);
				ProductVO product = pd.read(rs.getString(2));
				String amount = rs.getString(3);
				
				l.add(new CartVO(id, amount, product));
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
