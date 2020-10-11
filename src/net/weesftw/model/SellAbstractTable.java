package net.weesftw.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.weesftw.vo.CartVO;

public class SellAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private List<CartVO> list = new ArrayList<CartVO>();
	
	private String[] columnIndex = {"Amount", "Product", "Provider", "Unit Price", "Subtotal"};
	
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
		double subtotal = Double.parseDouble(list.get(rowIndex).getProduct().getPrice()) * Double.parseDouble(list.get(rowIndex).getAmount());
		double total =+ subtotal;
		
		switch(columnIndex)
		{
		case 0:
			return list.get(rowIndex).getAmount();
		case 1:
			return list.get(rowIndex).getProduct().getName();
		case 2:
			return list.get(rowIndex).getProduct().getProvider().getName();
		case 3:
			return list.get(rowIndex).getProduct().getPrice();
		case 4:
			return total;
		default:
			return null;
		}
	}
	
	public List<CartVO> getList()
	{
		return list;
	}
}
