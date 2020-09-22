package net.weesftw.model;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Label extends JLabel
{
	private static final long serialVersionUID = 1L;
	
	public Label(String args)
	{
		super(args);
	}
	
	public Label()
	{
		
	}
	
	public void loadImage(int width, int height)
	{
		setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/icon.png")).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
	}
	
	public void loadImage(String url, int width, int height)
	{		
		setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
	}
	
	public void loadImage(URL url, int width, int height)
	{		
		setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
	}
}
