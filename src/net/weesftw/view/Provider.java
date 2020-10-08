package net.weesftw.view;

import java.awt.TextField;

import javax.swing.WindowConstants;

import net.weesftw.manager.Action;
import net.weesftw.model.Button;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;

public class Provider extends UI<InternalFrame> 
{
	private Button submit;
	private TextField name, freight, zipCode, phoneNumber;
	
	public Provider()
	{
		super(new InternalFrame("Provider", false, true, false, true));
		
		Panel p = new Panel("New Provider", 4, 4, 4, 4);
		
		submit = new Button("Submit");
		name = new TextField(15);
		freight = new TextField(15);
		zipCode = new TextField(15);
		phoneNumber = new TextField(15);
		
		p.setComponent(new Label("Name: "));
		p.setComponent(name, 1, 0);
		
		p.setComponent(new Label("Freight: "), 0, 1);
		p.setComponent(freight, 1, 1);
		
		p.setComponent(new Label("Zip Code: "), 0, 2);
		p.setComponent(zipCode, 1, 2);
		
		p.setComponent(new Label("Phone: "), 0, 3);
		p.setComponent(phoneNumber, 1, 3);
		
		p.setComponent(submit, 1, 4);
		submit.addActionListener(new Action(this));
		
		ui.add(p);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ui.setVisible(true);
	}

	public Button getSubmit() 
	{
		return submit;
	}

	public TextField getName() 
	{
		return name;
	}

	public TextField getFreight() 
	{
		return freight;
	}

	public TextField getZipCode() 
	{
		return zipCode;
	}

	public TextField getPhoneNumber() 
	{
		return phoneNumber;
	}
}
