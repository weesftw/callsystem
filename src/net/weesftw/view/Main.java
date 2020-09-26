package net.weesftw.view;

import java.awt.Container;

import javax.swing.WindowConstants;

import net.weesftw.model.DesktopPane;
import net.weesftw.model.Frame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;

public final class Main extends UI<Frame>
{
	public static DesktopPane d;
	
	public Main()
	{
		super(new Frame("Call System | Open your ticket."), true);
		
		Container c = ui.getContentPane();
		
		Panel p1 = new Panel("Informations", 4, 4, 4, 4);
		Label img = new Label();
		
		//Panel p2 = new Panel("Tickets", 4, 4, 4, 4);
		
		p1.setComponent(new Label());
		
		//c.add(p1, BorderLayout.WEST);
		//c.add(p2, BorderLayout.EAST);
		c.add(d = getDesktop());
		
		ui.setResizable(false);
		ui.setSize(1024, 768);
		ui.setLocationRelativeTo(null);
		ui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ui.setJMenuBar(new Menu().getUI());
		ui.setVisible(true);
	}
}
