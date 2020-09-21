package net.weesftw.frame;

import java.awt.Image;

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
	
	public void loadImage(String url, int width, int height)
	{		
		setIcon(new ImageIcon(new ImageIcon("E:/weesftw.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
	}
}
