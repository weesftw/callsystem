package net.weesftw.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.weesftw.dao.TicketDAO;
import net.weesftw.vo.TicketVO;

public class AbstractTable extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private List<TicketVO> list = new TicketDAO().list();
	
	private String[] columnIndex = {"ID", "Title", "Client", "Company", "User", "Date", "Priority", "Status"};
	
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
		
		switch(columnIndex)
		{
		case 0:
			return list.get(rowIndex).getId();
		case 1:
			return list.get(rowIndex).getTitle();
		case 2:
			return list.get(rowIndex).getClient();
		case 3:
			return list.get(rowIndex).getCompany();
		case 4:
			return list.get(rowIndex).getUser();
		case 5:
			return d.format(new Date(list.get(rowIndex).getTimestamp().getTime()));
		case 6:
			return list.get(rowIndex).isPriority() ? "✓" : "✗";
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
