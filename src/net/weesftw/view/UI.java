package net.weesftw.view;

import net.weesftw.model.DesktopPane;

public abstract class UI<T>
{
	protected T ui;
	protected DesktopPane d;
	
	public UI(T ui)
	{
		this.ui = ui;
	}
	
	public UI(T ui, boolean value)
	{
		this.ui = ui;
		
		d = value ? new DesktopPane() : null;
	}
	
	public T getUI()
	{		
		return ui;
	}
	
	public DesktopPane getDesktop()
	{
		return d;
	}
}
