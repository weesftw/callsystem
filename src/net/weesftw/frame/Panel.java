package net.weesftw.frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

public class Panel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private GridBagConstraints gbc;
	
	public Panel(String args)
	{
		setBorder(BorderFactory.createTitledBorder(args));
		
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.anchor = GridBagConstraints.EAST;
		
		setLayout(new GridBagLayout());
	}
	
	public void setComponent(Component c)
	{
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 0;
		
		add(c, gbc);
	}
	
	public void setComponent(Component c, int x, int y)
	{
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.ipadx = 0;
		
		add(c, gbc);
	}
	
	public void setComponent(Component c, int x, int y, int ipadx)
	{
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.ipadx = ipadx;
		
		add(c, gbc);
	}
}
