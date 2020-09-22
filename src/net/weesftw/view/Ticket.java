package net.weesftw.view;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.Panel;

import javax.swing.JInternalFrame;

public class Ticket extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	
	public Ticket()
	{
		super("Company", false, true, false, true);
		
		Panel p = new Panel();
		
		Container c = getContentPane();
		
		
		c.add(p);
		
		pack();
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
