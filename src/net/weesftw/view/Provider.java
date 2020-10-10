package net.weesftw.view;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

import net.weesftw.manager.Action;
import net.weesftw.model.Button;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextField;

public class Provider extends UI<InternalFrame> 
{
	private Button submit;
	private TextField name, freight, zipCode;
	private JFormattedTextField phoneNumber;
	
	public Provider() 
	{
		super(new InternalFrame("Provider", false, true, false, true));
		
		Panel p = new Panel("New Provider", 4, 4, 4, 4);
		
		submit = new Button("Submit");
		name = new TextField(15);
		freight = new TextField(15);
		zipCode = new TextField(15);
		
		try 
		{
			phoneNumber = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		p.setComponent(new Label("Name: "));
		p.setComponent(name, 1, 0);
		
		p.setComponent(new Label("Freight"), 0, 1);
		p.setComponent(freight, 1, 1);
		
		p.setComponent(new Label("Zip Code: "), 0, 2);
		p.setComponent(zipCode, 1, 2);
		
		p.setComponent(new Label("Phone Number:"), 0, 3);
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

	public JFormattedTextField getPhoneNumber() 
	{
		return phoneNumber;
	}
}
