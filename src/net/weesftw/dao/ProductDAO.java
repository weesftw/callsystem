package net.weesftw.dao;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.weesftw.manager.Database;
import net.weesftw.vo.ProductVO;
import net.weesftw.vo.ProviderVO;

public class ProductDAO implements DataAcess<ProductVO>
{
	public ProductVO searchByName(String args)
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select * from `product` where `name` = ?"))
		{
			stmt.setString(1, args);
			
			ResultSet rs = stmt.executeQuery();
			
			ProviderDAO pd = new ProviderDAO();
			
			while(rs.next())
			{
				String id = rs.getString(1);
				String name = rs.getString(2);
				String price = rs.getString(3);
				String weight = rs.getString(4);
				String length = rs.getString(5);
				String width = rs.getString(6);
				String height = rs.getString(7);
				ProviderVO provider = pd.read(rs.getString(8));
				byte[] photo = rs.getBytes(9);
				
				return new ProductVO(id, provider, name, price, photo, weight, length, width, height);
			}
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public boolean create(ProductVO e)
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("insert into `product` (`name`, `price`, `weight`, `length`, `width`, `height`, `provider`, `photo`) value (?, ?, ?, ?, ?, ?, ?, ?)");
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				FileInputStream file = new FileInputStream(e.getPath()))
		{
			int img = file.read();
			
			while(img != -1)
			{
				buffer.write(img);
				img = file.read();
			}
	
			stmt.setString(1, e.getName());
			stmt.setString(2, e.getPrice());
			stmt.setString(3, e.getWeight());
			stmt.setString(4, e.getLength());
			stmt.setString(5, e.getWidth());
			stmt.setString(6, e.getHeight());
			stmt.setString(7, e.getProvider().getCnpj());
			stmt.setBytes(8, buffer.toByteArray());
			
			stmt.execute();
			
			return true;
		}
		catch(SQLException | IOException ex)
		{
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public ProductVO read(String id) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select * from `product` where id = ?"))
		{
			stmt.setString(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			ProviderDAO pd = new ProviderDAO();
			
			while(rs.next())
			{
				String id1 = rs.getString(1);
				String name = rs.getString(2);
				String price = rs.getString(3);
				String weight = rs.getString(4);
				String length = rs.getString(5);
				String width = rs.getString(6);
				String height = rs.getString(7);
				ProviderVO provider = pd.read(rs.getString(8));
				byte[] photo = rs.getBytes(9);
				
				return new ProductVO(id1, provider, name, price, photo, weight, length, width, height);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean update(ProductVO e) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("update `product` set `name` = ?, `price` = ?, `weight` = ?, `length` = ?, `width` = ?, `height` = ?, `provider` = ? where `id` = ?"))
		{			
			stmt.setString(1, e.getName());
			stmt.setString(2, e.getPrice());
			stmt.setString(3, e.getWeight());
			stmt.setString(4, e.getLength());
			stmt.setString(5, e.getWidth());
			stmt.setString(6, e.getHeight());
			stmt.setString(7, e.getProvider().getCnpj());
			stmt.setString(8, e.getId());
			
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
	public boolean delete(ProductVO e) 
	{
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("delete from `product` where `id` = ?"))
		{
			stmt.setString(1, e.getId());
			
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
	public List<ProductVO> list() 
	{
		List<ProductVO> l = new ArrayList<ProductVO>();
		
		try(Database d = new Database();
				PreparedStatement stmt = d.con.prepareStatement("select * from `product`"))
		{
			ResultSet rs = stmt.executeQuery();
			
			ProviderDAO pd = new ProviderDAO();
			
			while(rs.next())
			{
				String id = rs.getString(1);
				String name = rs.getString(2);
				String price = rs.getString(3);
				String weight = rs.getString(4);
				String length = rs.getString(5);
				String width = rs.getString(6);
				String height = rs.getString(7);
				ProviderVO provider = pd.read(rs.getString(8));
				byte[] photo = rs.getBytes(9);
				
				l.add(new ProductVO(id, provider, name, price, photo, weight, length, width, height));
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