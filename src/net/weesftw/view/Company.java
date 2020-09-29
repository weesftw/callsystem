package net.weesftw.view;

import java.awt.BorderLayout;

import javax.swing.WindowConstants;

import net.weesftw.manager.Action;
import net.weesftw.model.Button;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextField;

public class Company extends UI<InternalFrame>
{
	private Button submit;
	private Label img;
	private TextField cnpj, name, owner, zipCode;
	
	public Company()
	{
		super(new InternalFrame("Company", false, true, false, true));
		
		Panel p1 = new Panel("Details", 4, 4, 4, 4);
		Panel p2 = new Panel("New Company", 4, 4, 4, 4);
		
		img = new Label();
		cnpj = new TextField(15);
		name = new TextField(15);
		owner = new TextField(15);
		zipCode = new TextField(15);
		submit = new Button("Submit");
		
		img.loadImage();
		
		p1.setComponent(img);
		
		p2.setComponent(new Label("CNPJ: "));
		p2.setComponent(cnpj, 1, 0);
		
		p2.setComponent(new Label("Name: "), 0, 1);
		p2.setComponent(name, 1, 1);
		
		p2.setComponent(new Label("CPF: "), 0, 2);
		p2.setComponent(owner, 1, 2);
		owner.addActionListener(new Action(this));
		
		p2.setComponent(new Label("Zip Code: "), 0, 3);
		p2.setComponent(zipCode, 1, 3);
		
		p2.setComponent(submit, 1, 4);
		submit.addActionListener(new Action(this));
		
		ui.add(p1, BorderLayout.WEST);
		ui.add(p2, BorderLayout.EAST);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ui.setVisible(true);
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
	
	public Label getImage()
	{
		return img;
	}
}
