package net.weesftw.view;

import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import net.weesftw.manager.Action;
import net.weesftw.model.Panel;

public class Company extends UI<JInternalFrame>
{
	private static Company instance;
	
	private JButton submit;
	private JTextField cnpj, name, owner, zipCode;
	
	private Company()
	{
		super(new JInternalFrame("Company", false, true, false, true));
		
		Panel p2 = new Panel("New Company", 4, 4, 4, 4);
		
		cnpj = new JTextField(15);
		name = new JTextField(15);
		owner = new JTextField(15);
		zipCode = new JTextField(15);
		submit = new JButton("Submit");
		
		p2.setComponent(new Label("CNPJ: "));
		p2.setComponent(cnpj, 1, 0);
		cnpj.setEditable(false);
		
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
	
	public void clear()
	{
		cnpj.setText("");
		name.setText("");
		owner.setText("");
		zipCode.setText("");
	}
	
	public static synchronized Company getInstance()
	{
		return instance != null ? instance : new Company(); 
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

	public JTextField getOwner() 
	{
		return owner;
	}

	public JTextField getZipCode() 
	{
		return zipCode;
	}
}
