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
	private static Provider instance;
	
	private Button submit;
	private TextField cnpj, name, freight, zipCode, category;
	private JFormattedTextField phoneNumber;
	
	private Provider() 
	{
		super(new InternalFrame("Provider", false, true, false, true));
		
		Panel p = new Panel("New Provider", 4, 4, 4, 4);
		
		submit = new Button("Submit");
		cnpj = new TextField(15);
		name = new TextField(15);
		freight = new TextField(15);
		zipCode = new TextField(15);
		category = new TextField(15);
		
		try 
		{
			phoneNumber = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		p.setComponent(new Label("CNPJ: "));
		p.setComponent(cnpj, 1, 0);
		
		p.setComponent(new Label("Nome: "), 0, 1);
		p.setComponent(name, 1, 1);
		
		p.setComponent(new Label("Frete: "), 0, 2);
		p.setComponent(freight, 1, 2);
		
		p.setComponent(new Label("Categoria: "), 0, 3);
		p.setComponent(category, 1, 3);
		
		p.setComponent(new Label("CEP: "), 0, 4);
		p.setComponent(zipCode, 1, 4);
		
		p.setComponent(new Label("Telefone:"), 0, 5);
		p.setComponent(phoneNumber, 1, 5);
		
		p.setComponent(submit, 1, 6);
		submit.addActionListener(new Action(this));
		
		ui.add(p);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static synchronized Provider getInstance()
	{
		return instance != null ? instance : new Provider(); 
	}
	
	public void clear()
	{
		cnpj.setText("");
		name.setText("");
		freight.setText("");
		category.setText("");
		zipCode.setText("");
		phoneNumber.setText("");
	}

	public Button getSubmit() 
	{
		return submit;
	}	

	public TextField getCnpj() 
	{
		return cnpj;
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
	
	public TextField getCategory()
	{
		return category;
	}

	public JFormattedTextField getPhoneNumber() 
	{
		return phoneNumber;
	}
}
