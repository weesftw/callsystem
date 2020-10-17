package net.weesftw.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.weesftw.vo.ClientVO;

public class ClientAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
		
	private String[] columnIndex = {"CPF", "Nome", "Telefone", "CEP"};
	
	@Override
	public int getRowCount() 
	{				
		return ClientVO.list.size();
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
			return ClientVO.list.get(rowIndex).getCpf();
		case 1:
			return ClientVO.list.get(rowIndex).getFirstName() + " " + ClientVO.list.get(rowIndex).getLastName();
		case 2:
			return ClientVO.list.get(rowIndex).getPhoneNumber();
		case 3:
			return ClientVO.list.get(rowIndex).getZipCode();
		default:
			return null;
		}
	}
	
	public List<?> getList()
	{
		return ClientVO.list;
	}
}
