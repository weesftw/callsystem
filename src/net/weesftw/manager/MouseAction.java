package net.weesftw.manager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import net.weesftw.constraint.Message;
import net.weesftw.model.Table;
import net.weesftw.view.ClientOpen;
import net.weesftw.view.ClientTable;
import net.weesftw.view.CompanyOpen;
import net.weesftw.view.CompanyTable;
import net.weesftw.view.Main;
import net.weesftw.view.ProductOpen;
import net.weesftw.view.ProductTable;
import net.weesftw.view.ProviderOpen;
import net.weesftw.view.ProviderTable;
import net.weesftw.view.SaleOpen;
import net.weesftw.view.SaleTable;
import net.weesftw.view.TicketOpen;
import net.weesftw.view.TicketTable;
import net.weesftw.view.UI;
import net.weesftw.vo.ClientVO;
import net.weesftw.vo.CompanyVO;
import net.weesftw.vo.ProductVO;
import net.weesftw.vo.ProviderVO;
import net.weesftw.vo.SellVO;
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
		Authenticate a = Main.getInstance().getAuth();
		
		if(e.getClickCount() == 2)
		{
			if(a.getUser().getDepartment().isPrivilege())
			{
				if(ui instanceof TicketTable)
				{
					Table ta = (Table) e.getSource();
					
					int value = ta.getSelectedRow();
					TicketVO tv = TicketVO.list.get(value);
					
					new TicketOpen(tv);
				}
				else if(ui instanceof ClientTable)
				{			
					Table ta = (Table) e.getSource();
					
					int value = ta.getSelectedRow();
					ClientVO p = ClientVO.list.get(value);
					
					new ClientOpen(p);
				}
				else if(ui instanceof ProviderTable)
				{			
					Table ta = (Table) e.getSource();
					
					int value = ta.getSelectedRow();
					ProviderVO p = ProviderVO.list.get(value);
					
					new ProviderOpen(p);
				}
				else if(ui instanceof CompanyTable)
				{	
					Table ta = (Table) e.getSource();
					
					int value = ta.getSelectedRow();
					CompanyVO p = CompanyVO.list.get(value);
					
					new CompanyOpen(p);
				}
				else if(ui instanceof ProductTable)
				{	
					Table ta = (Table) e.getSource();
					
					int value = ta.getSelectedRow();
					ProductVO p = ProductVO.list.get(value);
					
					new ProductOpen(p);
				}
				else if(ui instanceof SaleTable)
				{
					Table ta = (Table) e.getSource();
					
					int value = ta.getSelectedRow();
					SellVO p = SellVO.list.get(value);
					
					new SaleOpen(p);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, Message.PERMISSION.get(null));
			}
		}
	}
}
