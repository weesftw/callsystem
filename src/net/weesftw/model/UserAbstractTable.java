package net.weesftw.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.weesftw.dao.UserDAO;
import net.weesftw.vo.UserVO;

public class UserAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private List<UserVO> list = new UserDAO().list();
	
	private String[] columnIndex = {"CPF", "Username"};
	
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
			return list.get(rowIndex).getCpf();
		case 1:
			return list.get(rowIndex).getUsername();
		default:
			return null;
		}
	}
	
	public List<?> getList()
	{
		return list;
	}
}
