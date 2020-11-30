package net.weesftw.model;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import net.weesftw.constraint.ImagePath;

public class Icon extends JLabel
{
	private static final long serialVersionUID = 1L;
	
	private Object path;

	public Object getPath()
	{
		return path;
	}
	
	public void setPath(Object path)
	{
		this.path = path;
		
		int width = 120;
		int height = 120;
		int imageScale = Image.SCALE_SMOOTH;
		
		if(path instanceof String)
		{
			setIcon(new ImageIcon(new ImageIcon(path.toString()).getImage().getScaledInstance(width, height, imageScale)));						
		}
		else if(path instanceof ImagePath)
		{
			setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(path.toString())).getImage().getScaledInstance(width, height, imageScale)));
		}
		else
		{
			setIcon(new ImageIcon(new ImageIcon((byte[]) path).getImage().getScaledInstance(width, height, imageScale)));
		}
	}
}
