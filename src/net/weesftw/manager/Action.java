package net.weesftw.manager;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import net.weesftw.dao.UserDAO;
import net.weesftw.model.DesktopPane;
import net.weesftw.view.Client;
import net.weesftw.view.Company;
import net.weesftw.view.Login;
import net.weesftw.view.Main;
import net.weesftw.view.Product;
import net.weesftw.view.Ticket;
import net.weesftw.view.UI;
import net.weesftw.view.User;
import net.weesftw.vo.UserVO;

public class Action implements ActionListener
{
	private UI<?> ui;
	
	public Action(UI<?> ui)
	{
		this.ui = ui;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		DesktopPane m = Main.d;
		
		if(ui instanceof Login)
		{
			UserDAO u = new UserDAO();
			Window c = (Window) ui.getUI();
			
			if(e.getID() == 1001)
			{
				String user = ((Login) ui).getUser().getText();
				String passwd = ((Login) ui).getPasswd().getText();
				
				if(!(user.isEmpty() && passwd.isEmpty()))
				{
					UserVO us = u.search(user);
					
					if(us != null)
					{
						if(u.isValid(user, passwd))
						{
							c.dispose();
							
							new Main();							
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Username or Password incorrect.");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Username not found.");
					}
				}
			}
		}
		else if(ui instanceof Client)
		{			
			m.add(new Client());
		}
		else if(ui instanceof Company)
		{
			m.add(new Company());
		}
		else if(ui instanceof Ticket)
		{
			m.add(new Ticket());
		}
		else if(ui instanceof User)
		{
			m.add(new User());
		}
		else if(ui instanceof Product)
		{
			m.add(new Product());
		}
	}
}
