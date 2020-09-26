package net.weesftw.model;

import java.awt.Component;

import javax.swing.JDesktopPane;

import net.weesftw.view.UI;

public class DesktopPane extends JDesktopPane 
{
	private static final long serialVersionUID = 1L;
	
	public DesktopPane()
	{
		
	}
	
	public void add(UI<?> ui)
	{
		add((Component) ui.getUI());
	}
}
