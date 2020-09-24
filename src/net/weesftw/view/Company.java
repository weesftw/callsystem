package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.JInternalFrame;

import net.weesftw.model.Button;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextField;

public class Company extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	
	public Company()
	{
		super("Company", false, true, false, true);
		
		Button btn = new Button("Submit");
		Label img = new Label();
		Panel p1 = new Panel("Details", 4, 4, 4, 4);
		Panel p2 = new Panel("New Company", 4, 4, 4, 4);
		TextField cnpj = new TextField(15);
		TextField name = new TextField(15);
		TextField owner = new TextField(15);
		TextField zipCode = new TextField(15);
		
		Container c = getContentPane();
		
		img.loadImage(120, 120);
		
		p1.setComponent(img);
		
		p2.setComponent(new Label("CNPJ: "));
		p2.setComponent(cnpj, 1, 0);
		
		p2.setComponent(new Label("Name: "), 0, 1);
		p2.setComponent(name, 1, 1);
		
		p2.setComponent(new Label("CPF: "), 0, 2);
		p2.setComponent(owner, 1, 2);
		
		p2.setComponent(new Label("Zip Code: "), 0, 3);
		p2.setComponent(zipCode, 1, 3);
		
		p2.setComponent(btn, 1, 4);
		
		c.add(p1, BorderLayout.WEST);
		c.add(p2, BorderLayout.EAST);
		
		pack();
		setIconifiable(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
