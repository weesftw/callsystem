package net.weesftw.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.weesftw.dao.TicketDAO;
import net.weesftw.vo.CompanyVO;
import net.weesftw.vo.TicketVO;

public class TicketAbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private List<TicketVO> list = new TicketDAO().list();
	
	private String[] columnIndex = {"ID", "Titulo", "Cliente", "Empresa", "Usuario", "Data", "Prioridade", "Status"};
	
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
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
		CompanyVO cv = list.get(rowIndex).getCompany();
		
		String b = list.get(rowIndex).isPriority() ? "✓" : "";
		
		switch(columnIndex)
		{
		case 0:
			return list.get(rowIndex).getId();
		case 1:
			return list.get(rowIndex).getTitle();
		case 2:
			return list.get(rowIndex).getClient().getFirstName() + " " + list.get(rowIndex).getClient().getLastName();
		case 3:
			return cv != null ? cv.getName() : "";
		case 4:
			return list.get(rowIndex).getUser().getUsername();
		case 5:
			return d.format(new Date(list.get(rowIndex).getTimestamp().getTime()));
		case 6:
			return b;
		case 7:
			return list.get(rowIndex).getStatus();
		default:
			return null;
		}
	}
	
	public List<?> getList()
	{
		return list;
	}
}
