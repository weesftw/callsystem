package net.weesftw.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.weesftw.dao.PeopleDAO;
import net.weesftw.vo.PeopleVO;

public class ClientAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private List<PeopleVO> list = new PeopleDAO().list();
	
	private String[] columnIndex = {"CPF", "Name", "Phone", "Email", "Birth", "Gender", "Zip Code"};
	
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
			return list.get(rowIndex).getFirstName() + " " + list.get(rowIndex).getLastName();
		case 2:
			return list.get(rowIndex).getPhoneNumber();
		case 3:
			return list.get(rowIndex).getEmail();
		case 4:
			return list.get(rowIndex).getDate();
		case 5:
			return list.get(rowIndex).getGender().toString();
		case 6:
			return list.get(rowIndex).getZipCode();
		default:
			return null;
		}
	}
	
	public List<?> getList()
	{
		return list;
	}
}
