package net.weesftw.view;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import net.weesftw.manager.Authenticate;
import net.weesftw.model.DesktopPane;
import net.weesftw.vo.ClientVO;
import net.weesftw.vo.UserVO;

public final class Main extends UI<JFrame>
{
	private static Main instance;
	
	private DesktopPane d;
	private Authenticate a;
	
	public Main(ClientVO p, UserVO vs)
	{
		super(new JFrame("Call System | Open your ticket."), true);
		
		Container c = ui.getContentPane();		
		
		instance = this;
		a = new Authenticate(p, vs);	
		
		c.add(d = new DesktopPane());
		
		ui.setSize(1168, 768);
		ui.setResizable(false);
		ui.setLocationRelativeTo(null);
		ui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ui.setJMenuBar(new Menu().getUI());
		ui.setVisible(true);
	}
	
	public static Main getInstance()
	{
		return instance;
	}
	
	public DesktopPane getDesktop()
	{
		return d;
	}
	
	public Authenticate getAuth()
	{
		return a;
	}
}
