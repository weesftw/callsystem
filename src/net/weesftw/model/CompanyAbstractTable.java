package net.weesftw.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.weesftw.dao.CompanyDAO;
import net.weesftw.vo.CompanyVO;

public class CompanyAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private List<CompanyVO> list = new CompanyDAO().list();
	
	private String[] columnIndex = {"CNPJ", "Nome", "Responsavel", "CEP"};
	
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
			return list.get(rowIndex).getCnpj();
		case 1:
			return list.get(rowIndex).getName();
		case 2:
			return list.get(rowIndex).getOwner();
		case 3:
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
