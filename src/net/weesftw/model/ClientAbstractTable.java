package net.weesftw.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.weesftw.vo.ClientVO;

public class ClientAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
		
	private String[] columnIndex = {"CPF", "Nome", "Telefone", "Email", "Data de Nascimento", "Genero", "CEP"};
	
	@Override
	public int getRowCount() 
	{				
		return ClientVO.LIST.size();
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
			return ClientVO.LIST.get(rowIndex).getCpf();
		case 1:
			return ClientVO.LIST.get(rowIndex).getFirstName() + " " + ClientVO.LIST.get(rowIndex).getLastName();
		case 2:
			return ClientVO.LIST.get(rowIndex).getPhoneNumber();
		case 3:
			return ClientVO.LIST.get(rowIndex).getEmail();
		case 4:
			return ClientVO.LIST.get(rowIndex).getDate();
		case 5:
			return ClientVO.LIST.get(rowIndex).getGender().toString();
		case 6:
			return ClientVO.LIST.get(rowIndex).getZipCode();
		default:
			return null;
		}
	}
	
	public List<?> getList()
	{
		return ClientVO.LIST;
	}
}
