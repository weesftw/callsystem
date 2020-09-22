package net.weesftw.view;

import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.JInternalFrame;

public class Ticket extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	
	public Ticket()
	{
		super("Company");
		
		Container c = getContentPane();
		
		pack();
		setIconifiable(true);
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
