package net.weesftw.model;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import net.weesftw.constraint.ImagePath;

public class Label extends JLabel
{
	private static final long serialVersionUID = 1L;
	
	private String url;
	private byte[] b;
	
	public Label(String args)
	{
		super(args);
	}
	
	public Label()
	{
		
	}
	
	public void loadImage(ImagePath path, int width, int height)
	{				
		setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(path.toString())).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
	}
	
	public void loadImage(String url, int width, int height)
	{		
		this.url = url;
		
		setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
	}
	
	public void loadImage(byte[] b, int width, int height)
	{
		if(b != null)
		{
			this.b = b;
			
			setIcon(new ImageIcon(new ImageIcon(b).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		}
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public byte[] getByte()
	{
		return b;
	}
}
