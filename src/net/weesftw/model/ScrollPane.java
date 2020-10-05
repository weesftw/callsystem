package net.weesftw.model;

import java.awt.Component;

import javax.swing.JScrollPane;

public class ScrollPane extends JScrollPane
{
	private static final long serialVersionUID = 1L;
	
	public ScrollPane(Component view, int width, int height)
	{
		super(view);
		
		setSize(width, height);
	}
	
	public ScrollPane(Component view)
	{
		super(view);
	}
}
