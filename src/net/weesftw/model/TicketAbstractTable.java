package net.weesftw.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import net.weesftw.vo.CompanyVO;
import net.weesftw.vo.TicketVO;

public class TicketAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private String[] columnIndex = {"ID", "Titulo", "Cliente", "Empresa", "Usuario", "Data", "Prioridade", "Status"};
	
	@Override
	public int getRowCount() 
	{				
		return TicketVO.list.size();
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
		String b = TicketVO.list.get(rowIndex).isPriority() ? "*" : "";
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
		CompanyVO cv = TicketVO.list.get(rowIndex).getCompany();
		
		switch(columnIndex)
		{
		case 0:
			return TicketVO.list.get(rowIndex).getId();
		case 1:
			return TicketVO.list.get(rowIndex).getTitle();
		case 2:
			return TicketVO.list.get(rowIndex).getClient().getFirstName() + " " + TicketVO.list.get(rowIndex).getClient().getLastName();
		case 3:
			return cv != null ? cv.getName() : "";
		case 4:
			return TicketVO.list.get(rowIndex).getUser().getUsername();
		case 5:
			return d.format(new Date(TicketVO.list.get(rowIndex).getTimestamp().getTime()));
		case 6:
			return b;
		case 7:
			return TicketVO.list.get(rowIndex).getStatus();
		default:
			return null;
		}
	}
}
