package net.weesftw.view;

import javax.swing.JInternalFrame;

public class User extends JInternalFrame 
{
	private static final long serialVersionUID = 1L;
	
	public User()
	{
		super("User", false, true, false, true);
		
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
