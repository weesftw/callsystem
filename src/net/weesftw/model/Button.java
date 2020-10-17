package net.weesftw.model;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import net.weesftw.constraint.ImagePath;

public class Button extends JButton
{
	private static final long serialVersionUID = 1L;
	
	public Button(String args)
	{
		super(args);
	}
	
	public Button(ImagePath path, int width, int height)
	{
		setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(path.toString())).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
	}
}
