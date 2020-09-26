package net.weesftw.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import net.weesftw.constraint.Status;
import net.weesftw.model.Button;
import net.weesftw.model.ComboBox;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextArea;
import net.weesftw.model.TextField;

public class Ticket extends UI<InternalFrame>
{	
	public Ticket()
	{
		super(new InternalFrame("Ticket", false, true, false, true));
		
		Button submit = new Button("Submit");
		ComboBox<Status> company = new ComboBox<>(Status.values(), 30, 21);
		JCheckBox priority = new JCheckBox("Priority");
		TextField client = new TextField(15);
		TextField title = new TextField(15);
		TextArea description = new TextArea(1, 1);
		JScrollPane s = new JScrollPane(description);
		
		description.setLineWrap(true);
//		description.setWrapStyleWord(true);
		
		s.setViewportView(description);
		
		Panel p = new Panel("New Ticket", 4, 4, 2, 4);
		
		p.setComponent(new Label("CPF: "), 0, 0);
		p.setComponent(client, 0, 1);
		
		p.setComponent(new Label("Company: "), 1, 0);
		
		//ComboBox de companys vinculadas ao CPF
		p.setComponent(company, 1, 1, 120);
		
		p.setComponent(new Label("Title: "), 0, 2);
		p.setComponent(title, 0, 3);
		
		p.setComponent(new Label("Product: "), 1, 2);
		
		//ComboBox de produtos da company
		p.setComponent(new TextField(15), 1, 3);
		
		p.setComponent(new Label("Description: "), 0, 4);
		p.setComponent(s, 0, 5, 200, 150, 2);
		description.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		p.setComponent(priority, 0, 6);
		p.setComponent(submit, 1, 6);
		
		ui.add(p);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ui.setVisible(true);
	}
}
