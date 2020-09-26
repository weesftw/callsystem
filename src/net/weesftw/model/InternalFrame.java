package net.weesftw.model;

import javax.swing.JInternalFrame;

public class InternalFrame extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	
	public InternalFrame(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable)
	{
		super(title, resizable, closable, maximizable, iconifiable);
	}
}
