package net.weesftw.model;

import javax.swing.JTextField;

public class TextField extends JTextField
{
	private static final long serialVersionUID = 1L;
	
	public TextField(int width)
	{
		super(width);
	}
	
	public TextField(int width, String args)
	{
		super(args);
	}
	
	public TextField(int width, String args, boolean editable)
	{
		super(args);
		
		setEditable(editable);
	}
}
