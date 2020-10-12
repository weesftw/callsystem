package net.weesftw.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.weesftw.vo.ClientVO;

public class ClientAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private List<ClientVO> list;
	
	private String[] columnIndex = {"CPF", "Nome", "Telefone", "Email", "Data de Nascimento", "Genero", "CEP"};
	
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
	
	public void setList(List<ClientVO> list)
	{
		this.list = list;
	}
}
