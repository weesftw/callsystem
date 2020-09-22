package net.weesftw.model;

import java.awt.Dimension;

import javax.swing.JComboBox;

public class ComboBox<T> extends JComboBox<T>
{
	private static final long serialVersionUID = 1L;
	
	public ComboBox(T[] items, int x, int y)
	{
		super(items);
		
		setPreferredSize(new Dimension(x, y));
	}
}
