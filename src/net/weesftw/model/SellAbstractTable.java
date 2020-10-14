package net.weesftw.model;

import javax.swing.table.AbstractTableModel;

import net.weesftw.vo.CartVO;

public class SellAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private String[] columnIndex = {"Qntd", "Produto", "Fornecedor", "Preco Unit.", "Subtotal"};
	
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
		double subtotal =+ Double.parseDouble(CartVO.list.get(rowIndex).getProduct().getPrice()) * Double.parseDouble(CartVO.list.get(rowIndex).getAmount());
		
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
		case 4:
			return subtotal;
		default:
			return null;
		}
	}
}
