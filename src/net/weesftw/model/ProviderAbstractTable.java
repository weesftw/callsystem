package net.weesftw.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.weesftw.dao.ProviderDAO;
import net.weesftw.vo.ProviderVO;

public class ProviderAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private List<ProviderVO> list = new ProviderDAO().list();
	
	private String[] columnIndex = {"ID", "Nome", "Frete", "Telefone"};
	
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
			return list.get(rowIndex).getFreight();
		case 3:
			return list.get(rowIndex).getPhoneNumber();
		default:
			return null;
		}
	}
	
	public List<?> getList()
	{
		return list;
	}
}
