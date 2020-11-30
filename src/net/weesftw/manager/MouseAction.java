package net.weesftw.manager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import net.weesftw.constraint.Message;
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
				JTable ta = (JTable) e.getSource();
				
				if(ui instanceof TicketTable)
				{
					Object value = ta.getValueAt(ta.getSelectedRow(), ta.getSelectedColumn());					
					
					for(TicketVO c : TicketVO.list)
					{						
						if(c.equals(value))
						{
							new TicketOpen(c);
						}
					}
				}
				else if(ui instanceof ClientTable)
				{			
					Object value = ta.getValueAt(ta.getSelectedRow(), ta.getSelectedColumn());					
					
					for(ClientVO c : ClientVO.list)
					{						
						if(c.equals(value))
						{
							new ClientOpen(c);
						}
					}					
				}
				else if(ui instanceof ProviderTable)
				{			
					Object value = ta.getValueAt(ta.getSelectedRow(), ta.getSelectedColumn());					
					
					for(ProviderVO c : ProviderVO.list)
					{						
						if(c.equals(value))
						{
							new ProviderOpen(c);
						}
					}
				}
				else if(ui instanceof CompanyTable)
				{	
					Object value = ta.getValueAt(ta.getSelectedRow(), ta.getSelectedColumn());					
					
					for(CompanyVO c : CompanyVO.list)
					{						
						if(c.equals(value))
						{
							new CompanyOpen(c);
						}
					}
				}
				else if(ui instanceof ProductTable)
				{	
					Object value = ta.getValueAt(ta.getSelectedRow(), ta.getSelectedColumn());					
					
					for(ProductVO c : ProductVO.list)
					{						
						if(c.equals(value))
						{
							new ProductOpen(c);
						}
					}
				}
				else if(ui instanceof SaleTable)
				{
					Object value = ta.getValueAt(ta.getSelectedRow(), ta.getSelectedColumn());					
					
					for(SellVO c : SellVO.list)
					{						
						if(c.equals(value))
						{
							new SaleOpen(c);
						}
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, Message.PERMISSION.get(null));
			}
		}
	}
}
