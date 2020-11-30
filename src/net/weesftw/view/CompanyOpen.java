package net.weesftw.view;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import net.weesftw.exception.CepNotFoundException;
import net.weesftw.manager.Action;
import net.weesftw.manager.CepAPI;
import net.weesftw.model.Dialog;
import net.weesftw.model.Panel;
import net.weesftw.vo.CompanyVO;

public class CompanyOpen extends UI<JDialog> 
{
	private JButton submit;
	private JTextField cnpj, name, owner, zipCode, neighborhood, address, city, state;
	
	public CompanyOpen(CompanyVO c) 
	{
		super(new Dialog("Company: " + c.getName(), true));
		
		Panel p2 = new Panel("Company Details", 4, 4, 4, 4);
		submit = new JButton("Update");
		cnpj = new JTextField(15);
		name = new JTextField(15);
		owner = new JTextField(15);
		name = new JTextField(15);
		zipCode = new JTextField(15);
		neighborhood = new JTextField(15);
		address = new JTextField(15);
		city = new JTextField(15);
		state = new JTextField(15);
		
		p2.setComponent(new JLabel("CNPJ: "));
		p2.setComponent(cnpj, 0, 1);
		cnpj.setText(c.getCnpj());
		cnpj.setEditable(false);
		
		p2.setComponent(new JLabel("Nome: "), 1, 0);
		p2.setComponent(name, 1, 1);
		name.setText(c.getName());
		
		p2.setComponent(new JLabel("Responsavel: "), 0, 2);
		p2.setComponent(owner, 0, 3);
		owner.setText(c.getOwner().getCpf());
		
		try 
		{
			CepAPI cep = new CepAPI(c.getZipCode());
			
			p2.setComponent(new JLabel("CEP: "), 1, 2);
			p2.setComponent(zipCode, 1, 3);
			zipCode.setText(c.getZipCode());
			zipCode.addActionListener(new Action(this));
			
			p2.setComponent(new JLabel("Rua: "), 0, 4);
			p2.setComponent(address, 0, 5);
			address.setEditable(false);
			address.setText(cep.getLogradouro());
			
			p2.setComponent(new JLabel("Bairro: "), 1, 4);
			p2.setComponent(neighborhood, 1, 5);
			neighborhood.setEditable(false);
			neighborhood.setText(cep.getBairro());
			
			p2.setComponent(new JLabel("Cidade: "), 0, 6);
			p2.setComponent(city, 0, 7);
			city.setEditable(false);
			city.setText(cep.getLocalidade());
			
			p2.setComponent(new JLabel("Estado: "), 1, 6);
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

	public JTextField getNeighborhood() 
	{
		return neighborhood;
	}

	public JTextField getAddress() 
	{
		return address;
	}

	public JTextField getCity() 
	{
		return city;
	}

	public JTextField getState() 
	{
		return state;
	}
}
