package net.weesftw.model;

import javax.swing.JDialog;

import net.weesftw.view.Main;

public class Dialog extends JDialog
{
	private static final long serialVersionUID = 1L;
	
	public Dialog(String title, boolean modal)
	{
		super(Main.getInstance().getUI(), title, modal);
	}
}
