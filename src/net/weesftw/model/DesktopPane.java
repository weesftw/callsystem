package net.weesftw.model;

import javax.swing.JDesktopPane;

import net.weesftw.view.UI;

public class DesktopPane extends JDesktopPane 
{
	private static final long serialVersionUID = 1L;
	
	public void add(UI<?> ui)
	{
		add((InternalFrame) ui.getUI());							
	}
}
