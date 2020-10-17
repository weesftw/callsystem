package net.weesftw.model;

import javax.swing.table.AbstractTableModel;

import net.weesftw.vo.ProductVO;

public class ProductAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private String[] columnIndex = {"ID", "Nome", "Preco", "Fornecedor"};
	
	@Override
	public int getRowCount() 
	{				
		return ProductVO.list.size();
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
			return ProductVO.list.get(rowIndex).getId();
		case 1:
			return ProductVO.list.get(rowIndex).getName();
		case 2:
			return ProductVO.list.get(rowIndex).getPrice();
		case 3:
			return ProductVO.list.get(rowIndex).getProvider().getName();
		default:
			return null;
		}
	}
}
