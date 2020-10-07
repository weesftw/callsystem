package net.weesftw.view;

import java.awt.Container;

import javax.swing.WindowConstants;

import net.weesftw.manager.Authenticate;
import net.weesftw.model.DesktopPane;
import net.weesftw.model.Frame;
import net.weesftw.vo.PeopleVO;

public final class Main extends UI<Frame>
{	
	public static Main instance;	
	
	private DesktopPane d;
	private Authenticate auth;
	
	public Main(PeopleVO p)
	{
		super(new Frame("Call System | Open your ticket."), true);
		
		Container c = ui.getContentPane();
		
		instance = this;
		this.auth = new Authenticate(p);
		
		c.add(d = new DesktopPane());
		
		ui.setSize(1176, 800);
		ui.setResizable(false);
		ui.setLocationRelativeTo(null);
		ui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ui.setJMenuBar(new Menu().getUI());
		ui.setVisible(true);
	}
	
	public DesktopPane getDesktop()
	{
		return d;
	}
	
	public Authenticate getAuth()
	{
		return auth;
	}
}
