package net.weesftw.model;

import java.text.SimpleDateFormat;

import javax.swing.table.AbstractTableModel;

import net.weesftw.vo.SellVO;

public class SellAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private String[] columnIndex = {"ID", "Cliente", "Data", "Por", "Observacão", "Status"};
	
	@Override
	public int getRowCount() 
	{				
		return SellVO.list.size();
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
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
		
		switch(columnIndex)
		{
		case 0:
			return SellVO.list.get(rowIndex).getId();
		case 1:
			return SellVO.list.get(rowIndex).getPeople().getFirstName() + " " + SellVO.list.get(rowIndex).getPeople().getLastName();
		case 2:
			return d.format(SellVO.list.get(rowIndex).getTimestamp());
		case 3:
			return SellVO.list.get(rowIndex).getBy().getUsername();
		case 4:
			return SellVO.list.get(rowIndex).getObservation();
		case 5:
			return SellVO.list.get(rowIndex).getStatus();
		default:
			return null;
		}
	}
}
