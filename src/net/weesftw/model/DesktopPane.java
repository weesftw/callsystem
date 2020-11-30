package net.weesftw.model;

import java.awt.Component;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import net.weesftw.view.UI;

public class DesktopPane extends JDesktopPane 
{
	private static final long serialVersionUID = 1L;
	
	public void add(UI<?> ui)
	{
		Component c = (JInternalFrame) ui.getUI();
		
		remove(c);
		add(c);
	}
}
