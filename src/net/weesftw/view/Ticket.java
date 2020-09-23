package net.weesftw.view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

import net.weesftw.constraint.Status;
import net.weesftw.model.Button;
import net.weesftw.model.ComboBox;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextArea;
import net.weesftw.model.TextField;

public class Ticket extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	
	public Ticket()
	{
		super("Ticket", false, true, false, true);
		
		Button submit = new Button("Submit");
		ComboBox<Status> company = new ComboBox<>(Status.values(), 30, 21);
		JCheckBox priority = new JCheckBox("Priority");
		TextField client = new TextField();
		TextField title = new TextField();
		JTextPane description = new JTextPane();
		JScrollPane s = new JScrollPane(description);
		
//		description.setLineWrap(true);
//		description.setWrapStyleWord(true);
		
		s.setViewportView(description);
		
		Panel p = new Panel("New Ticket", 4, 4, 2, 4);
		
		Container c = getContentPane();
		
		p.setComponent(new Label("CPF: "), 0, 0);
		p.setComponent(client, 0, 1, 120);
		
		p.setComponent(new Label("Company: "), 1, 0);
		
		//ComboBox de companys vinculadas ao CPF
		p.setComponent(company, 1, 1, 120);
		
		p.setComponent(new Label("Title: "), 0, 2);
		p.setComponent(title, 0, 3, 140);
		
		p.setComponent(new Label("Product: "), 1, 2);
		
		//ComboBox de produtos da company
		p.setComponent(new TextField(), 1, 3, 120);
		
		p.setComponent(new Label("Description: "), 0, 4);
		p.setComponent(s, 0, 5, 200, 150, 2);
		description.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		p.setComponent(priority, 0, 6);
		p.setComponent(submit, 1, 6);
		
		c.add(p);
		
		pack();
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
