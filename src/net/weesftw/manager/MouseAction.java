package net.weesftw.manager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import net.weesftw.model.Table;
import net.weesftw.view.ClientOpen;
import net.weesftw.view.ClientTable;
import net.weesftw.view.TicketOpen;
import net.weesftw.view.TicketTable;
import net.weesftw.view.UI;
import net.weesftw.vo.ClientVO;
import net.weesftw.vo.TicketVO;

public class MouseAction extends MouseAdapter
{
	private UI<?> ui;
	
	public MouseAction(UI<?> ui)
	{
		this.ui = ui;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{	
		if(ui instanceof TicketTable)
		{
			TicketTable t = ((TicketTable) ui);
			
			if(e.getClickCount() == 2)
			{
				Table ta = (Table) e.getSource();
				
				int value = ta.getSelectedRow();
				TicketVO tv = (TicketVO) t.getAt().getList().get(value != 1 ? value - 1 : 0);
				
				new TicketOpen(tv);
			}
		}
		else if(ui instanceof ClientTable)
		{
			ClientTable t = ((ClientTable) ui);
			
			if(e.getClickCount() == 2)
			{
				Table ta = (Table) e.getSource();
				
				int value = ta.getSelectedRow();
				ClientVO p = (ClientVO) t.getAt().getList().get(value);
				
				new ClientOpen(p);
			}
		}
	}
}
