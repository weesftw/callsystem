package net.weesftw.view;

import java.awt.Container;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

public final class Main extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	static JDesktopPane desk = new JDesktopPane();
	
	public Main()
	{
		super("Call System | Open your ticket.");
		
		Container c = getContentPane();
		
		//setContentPane(new JLabel(new ImageIcon("img/background.jpg")));
		c.add(desk);
		
		setResizable(false);
		setSize(1024, 768);
		setJMenuBar(new Menu());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
