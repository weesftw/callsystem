package net.weesftw.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.weesftw.vo.UserVO;

public class UserAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private String[] columnIndex = {"CPF", "Usuario"};
	
	@Override
	public int getRowCount() 
	{				
		return UserVO.LIST.size();
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
			return UserVO.LIST.get(rowIndex).getCpf();
		case 1:
			return UserVO.LIST.get(rowIndex).getUsername();
		default:
			return null;
		}
	}
	
	public List<?> getList()
	{
		return UserVO.LIST;
	}
}
