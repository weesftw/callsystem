package net.weesftw.model;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JComboBox;

public class ComboBox<T> extends JComboBox<T>
{
	private static final long serialVersionUID = 1L;
	
	public ComboBox(T[] items, int width, int height)
	{
		super(items);
				
		setPreferredSize(new Dimension(width, height));
	}
	
	public ComboBox(Vector<T> items, int width, int height)
	{
		super(items);
		
		setPreferredSize(new Dimension(width, height));
	}
	
	public ComboBox(T[] items)
	{
		super(items);
	}
	
	public ComboBox(Vector<T> items)
	{
		super(items);
	}
}
