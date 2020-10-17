package net.weesftw.model;

import javax.swing.table.AbstractTableModel;

import net.weesftw.vo.CartVO;

public class CartAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private String[] columnIndex = {"Qntd", "Produto", "Fornecedor", "Preco Unit."};
	
	@Override
	public int getRowCount() 
	{				
		return CartVO.list.size();
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
			return CartVO.list.get(rowIndex).getAmount();
		case 1:
			return CartVO.list.get(rowIndex).getProduct().getName();
		case 2:
			return CartVO.list.get(rowIndex).getProduct().getProvider().getName();
		case 3:
			return CartVO.list.get(rowIndex).getProduct().getPrice();
		default:
			return null;
		}
	}
}
