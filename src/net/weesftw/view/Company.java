package net.weesftw.view;

import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import net.weesftw.frame.Label;
import net.weesftw.frame.Panel;
import net.weesftw.frame.TextField;

public class Company extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	
	private TextField cnpj, name, owner, zipCode;
	
	public Company()
	{
		super("Company");
		
		Panel p = new Panel("New Company");
		Label img = new Label();
		Container c = getContentPane();
		
		img.loadImage("E:/weesftw.png", 120, 120);
		
		p.setComponent(img);
		
		p.setComponent(new Label("CNPJ: "));
		p.setComponent(cnpj = new TextField(), 1, 0, 120);
		
		p.setComponent(new Label("Name: "), 0, 1);
		p.setComponent(name = new TextField(), 1, 1, 120);
		
		p.setComponent(new Label("CPF: "), 0, 2);
		p.setComponent(owner = new TextField(), 1, 2, 120);
		
		p.setComponent(new Label("Zip Code: "), 0, 3);
		p.setComponent(zipCode = new TextField(), 1, 3, 120);
		
		c.add(p);
		
		pack();
		setIconifiable(true);
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
