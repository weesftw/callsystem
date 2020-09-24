package net.weesftw.manager;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.weesftw.view.Login;
import net.weesftw.view.UI;

public class Action implements ActionListener
{
	private UI o;	
	
	public Action(UI o)
	{
		this.o = o;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(o instanceof Login)
		{
			System.out.println("Sim");
		}
		else
		{
			System.out.println("Não");
		}
	}
}
