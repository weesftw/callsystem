package net.weesftw.view;

import javax.swing.WindowConstants;

import net.weesftw.manager.Action;
import net.weesftw.model.Button;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextField;

public class Company extends UI<InternalFrame>
{
	private static Company instance;
	
	private Button submit;
	private TextField cnpj, name, owner, zipCode;
	
	private Company()
	{
		super(new InternalFrame("Company", false, true, false, true));
		
		Panel p2 = new Panel("New Company", 4, 4, 4, 4);
		
		cnpj = new TextField(15);
		name = new TextField(15);
		owner = new TextField(15);
		zipCode = new TextField(15);
		submit = new Button("Submit");
		
		p2.setComponent(new Label("CNPJ: "));
		p2.setComponent(cnpj, 1, 0);
		
		p2.setComponent(new Label("Nome: "), 0, 1);
		p2.setComponent(name, 1, 1);
		
		p2.setComponent(new Label("Responsavel (CPF): "), 0, 2);
		p2.setComponent(owner, 1, 2);
		owner.addActionListener(new Action(this));
		
		p2.setComponent(new Label("CEP: "), 0, 3);
		p2.setComponent(zipCode, 1, 3);
		
		p2.setComponent(submit, 1, 4);
		submit.addActionListener(new Action(this));
		
		ui.add(p2);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static Company getInstance()
	{
		return instance != null ? instance : new Company(); 
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

	public TextField getOwner() 
	{
		return owner;
	}

	public TextField getZipCode() 
	{
		return zipCode;
	}
}
