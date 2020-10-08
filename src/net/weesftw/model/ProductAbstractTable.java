package net.weesftw.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.weesftw.dao.ProductDAO;
import net.weesftw.vo.ProductVO;

public class ProductAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private List<ProductVO> list = new ProductDAO().list();
	
	private String[] columnIndex = {"ID", "Name", "Price", "Provider"};
	
	@Override
	public int getRowCount() 
	{				
		return list.size();
	}
	
	@Override
	public int getColumnCount() 
	{
		return columnIndex.length;
	}
	
	@Override
	public String getColumnName(int columnIndex)
	{
		return this.columnIndex[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		switch(columnIndex)
		{
		case 0:
			return list.get(rowIndex).getId();
		case 1:
			return list.get(rowIndex).getName();
		case 2:
			return list.get(rowIndex).getPrice();
		case 3:
			return list.get(rowIndex).getProvider();
		default:
			return null;
		}
	}
	
	public List<?> getList()
	{
		return list;
	}
}
