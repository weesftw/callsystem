package net.weesftw.view;

import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

import net.weesftw.manager.Action;
import net.weesftw.model.Panel;

public class Provider extends UI<JInternalFrame> 
{
	private static Provider instance;
	
	private JButton submit;
	private JTextField cnpj, name, freight, zipCode, category;
	private JFormattedTextField phoneNumber;
	
	private Provider() 
	{
		super(new JInternalFrame("Provider", false, true, false, true));
		
		Panel p = new Panel("New Provider", 4, 4, 4, 4);
		
		submit = new JButton("Submit");
		cnpj = new JTextField(15);
		name = new JTextField(15);
		freight = new JTextField(15);
		zipCode = new JTextField(15);
		category = new JTextField(15);
		
		try 
		{
			phoneNumber = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		p.setComponent(new JLabel("CNPJ: "));
		p.setComponent(cnpj, 1, 0);
		
		p.setComponent(new JLabel("Nome: "), 0, 1);
		p.setComponent(name, 1, 1);
		
		p.setComponent(new JLabel("Frete: "), 0, 2);
		p.setComponent(freight, 1, 2);
		
		p.setComponent(new JLabel("Categoria: "), 0, 3);
		p.setComponent(category, 1, 3);
		
		p.setComponent(new JLabel("CEP: "), 0, 4);
		p.setComponent(zipCode, 1, 4);
		
		p.setComponent(new JLabel("Telefone:"), 0, 5);
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

	public JButton getSubmit() 
	{
		return submit;
	}	

	public JTextField getCnpj() 
	{
		return cnpj;
	}

	public JTextField getName() 
	{
		return name;
	}

	public JTextField getFreight() 
	{
		return freight;
	}

	public JTextField getZipCode() 
	{
		return zipCode;
	}
	
	public JTextField getCategory()
	{
		return category;
	}

	public JFormattedTextField getPhoneNumber() 
	{
		return phoneNumber;
	}
}
