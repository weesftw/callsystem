package net.weesftw.model;

import javax.swing.table.AbstractTableModel;

import net.weesftw.vo.CompanyVO;

public class CompanyAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private String[] columnIndex = {"CNPJ", "Nome", "Responsavel", "CEP"};
	
	@Override
	public int getRowCount() 
	{				
		return CompanyVO.list.size();
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
			return CompanyVO.list.get(rowIndex).getCnpj();
		case 1:
			return CompanyVO.list.get(rowIndex).getName();
		case 2:
			return CompanyVO.list.get(rowIndex).getOwner().getFirstName() + " " + CompanyVO.list.get(rowIndex).getOwner().getLastName();
		case 3:
			return CompanyVO.list.get(rowIndex).getZipCode();
		default:
			return null;
		}
	}
}
