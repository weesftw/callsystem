package net.weesftw.model;


import java.util.Vector;

import javax.swing.JComboBox;

public class ComboBox<T> extends JComboBox<T>
{
	private static final long serialVersionUID = 1L;
	
	public ComboBox(T[] items)
	{
		super(items);
	}
	
	public ComboBox(Vector<T> items)
	{
		super(items);
	}
	
	public ComboBox()
	{
		super();
	}
}
