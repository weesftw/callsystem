package net.weesftw.model;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Panel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private GridBagConstraints gbc;
	
	public Panel(String args, int top, int left, int botton, int right)
	{
		setBorder(BorderFactory.createTitledBorder(args));
		
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(top, left, botton, right);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.BOTH;
		
		setLayout(new GridBagLayout());
	}
	
	public void setComponent(Component c)
	{
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.gridwidth = 1;
		
		add(c, gbc);
	}
	
	public void setComponent(Component c, int x, int y)
	{
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.gridwidth = 1;
		
		add(c, gbc);
	}
	
	public void setComponent(Component c, int x, int y, int ipadx)
	{
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.ipadx = ipadx;
		gbc.gridwidth = 1;
		
		add(c, gbc);
	}
	
	public void setComponent(Component c, int x, int y, int ipadx, int ipady)
	{
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.ipadx = ipadx;
		gbc.ipady = ipady;
		gbc.gridwidth = 1;
		
		add(c, gbc);
	}
	
	public void setComponent(Component c, int x, int y, int ipadx, int ipady, int gridwidth)
	{
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.ipadx = ipadx;
		gbc.ipady = ipady;
		gbc.gridwidth = gridwidth;
		
		add(c, gbc);
	}
}
