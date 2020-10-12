package net.weesftw.view;

import javax.swing.WindowConstants;

import net.weesftw.model.InternalFrame;

public class Account extends UI<InternalFrame>
{
	private static Account instance;
	
	private Account() 
	{
		super(new InternalFrame("Account", false, true, false, true));
		
		
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static Account getInstance()
	{
		return instance != null ? instance : new Account(); 
	}
}
