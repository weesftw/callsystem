package net.weesftw.view;

import java.awt.Container;

import javax.swing.WindowConstants;

import net.weesftw.manager.Authenticate;
import net.weesftw.model.DesktopPane;
import net.weesftw.model.Frame;
import net.weesftw.vo.PeopleVO;
import net.weesftw.vo.UserVO;

public final class Main extends UI<Frame>
{
	public static Main instance;
	
	private DesktopPane d;
	private Authenticate a;
	
	public Main(PeopleVO p, UserVO vs)
	{
		super(new Frame("Call System | Open your ticket."), true);
		
		Container c = ui.getContentPane();
		
		a = new Authenticate(p, vs);
		instance = this;
		
		c.add(d = new DesktopPane());
		
		ui.setSize(1024, 650);
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
		return a;
	}
}
