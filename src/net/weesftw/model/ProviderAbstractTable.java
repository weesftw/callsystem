package net.weesftw.model;

import javax.swing.table.AbstractTableModel;

import net.weesftw.vo.ProviderVO;

public class ProviderAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private String[] columnIndex = {"CNPJ", "Nome", "Categoria", "Frete", "Telefone"};
	
	@Override
	public int getRowCount() 
	{				
		return ProviderVO.list.size();
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
			return ProviderVO.list.get(rowIndex).getCnpj();
		case 1:
			return ProviderVO.list.get(rowIndex).getName();
		case 2:
			return ProviderVO.list.get(rowIndex).getCategory();
		case 3:
			return ProviderVO.list.get(rowIndex).getFreight();
		case 4:
			return ProviderVO.list.get(rowIndex).getPhoneNumber();
		default:
			return null;
		}
	}
}
