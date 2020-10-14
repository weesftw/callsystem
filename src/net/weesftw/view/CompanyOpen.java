package net.weesftw.view;

import java.io.IOException;

import javax.swing.WindowConstants;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import net.weesftw.exception.CepNotFoundException;
import net.weesftw.manager.Action;
import net.weesftw.manager.CepAPI;
import net.weesftw.model.Button;
import net.weesftw.model.Dialog;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextField;
import net.weesftw.vo.CompanyVO;

public class CompanyOpen extends UI<Dialog> 
{
	private Button submit;
	private TextField cnpj, name, owner, zipCode, neighborhood, address, city, state;
	
	public CompanyOpen(CompanyVO c) 
	{
		super(new Dialog("Company: " + c.getName(), true));
		
		Panel p2 = new Panel("Company Details", 4, 4, 4, 4);
		submit = new Button("Update");
		cnpj = new TextField(15);
		name = new TextField(15);
		owner = new TextField(15);
		name = new TextField(15);
		zipCode = new TextField(15);
		neighborhood = new TextField(15);
		address = new TextField(15);
		city = new TextField(15);
		state = new TextField(15);
		
		p2.setComponent(new Label("CNPJ: "));
		p2.setComponent(cnpj, 0, 1);
		cnpj.setText(c.getCnpj());
		cnpj.setEditable(false);
		
		p2.setComponent(new Label("Nome: "), 1, 0);
		p2.setComponent(name, 1, 1);
		name.setText(c.getName());
		
		p2.setComponent(new Label("Responsavel: "), 0, 2);
		p2.setComponent(owner, 0, 3);
		owner.setText(c.getOwner().getCpf());
		
		try 
		{
			CepAPI cep = new CepAPI(c.getZipCode());
			
			p2.setComponent(new Label("CEP: "), 1, 2);
			p2.setComponent(zipCode, 1, 3);
			zipCode.setText(c.getZipCode());
			zipCode.addActionListener(new Action(this));
			
			p2.setComponent(new Label("Rua: "), 0, 4);
			p2.setComponent(address, 0, 5);
			address.setEditable(false);
			address.setText(cep.getLogradouro());
			
			p2.setComponent(new Label("Bairro: "), 1, 4);
			p2.setComponent(neighborhood, 1, 5);
			neighborhood.setEditable(false);
			neighborhood.setText(cep.getBairro());
			
			p2.setComponent(new Label("Cidade: "), 0, 6);
			p2.setComponent(city, 0, 7);
			city.setEditable(false);
			city.setText(cep.getLocalidade());
			
			p2.setComponent(new Label("Estado: "), 1, 6);
			p2.setComponent(state, 1, 7);
			state.setEditable(false);
			state.setText(cep.getUf());
		} 
		catch (ParserConfigurationException | SAXException | IOException | CepNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		p2.setComponent(submit, 1, 8);
		submit.addActionListener(new Action(this));
		
		ui.add(p2);
		
		ui.pack();
		ui.setModal(true);
		ui.setLocationRelativeTo(null);
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

	public TextField getNeighborhood() 
	{
		return neighborhood;
	}

	public TextField getAddress() 
	{
		return address;
	}

	public TextField getCity() 
	{
		return city;
	}

	public TextField getState() 
	{
		return state;
	}
}
