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
import net.weesftw.vo.ProviderVO;

public class ProviderOpen extends UI<JDialog> 
{
	private JButton submit;
	private JTextField cnpj, name, phone, freight, category, zipCode, neighborhood, address, city, state;
	
	public ProviderOpen(ProviderVO p) 
	{
		super(new Dialog("Provider: " + p.getName(), true));
		
		Panel p2 = new Panel("Provider Details", 4, 4, 4, 4);
		submit = new JButton("Update");
		cnpj = new JTextField(15);
		name = new JTextField(15);
		phone = new JTextField(15);
		freight = new JTextField(15);
		category = new JTextField(15);
		zipCode = new JTextField(15);
		neighborhood = new JTextField(15);
		address = new JTextField(15);
		city = new JTextField(15);
		state = new JTextField(15);
		
		p2.setComponent(new JLabel("CNPJ: "));
		p2.setComponent(cnpj, 0, 1);
		cnpj.setText(p.getCnpj());
		cnpj.setEditable(false);
		
		p2.setComponent(new JLabel("Nome: "), 1, 0);
		p2.setComponent(name, 1, 1);
		name.setText(p.getName());
		
		p2.setComponent(new JLabel("Categoria: "), 0, 2);
		p2.setComponent(category, 0, 3);
		category.setText(p.getCategory());
		
		p2.setComponent(new JLabel("Frete: "), 1, 2);
		p2.setComponent(freight, 1, 3);
		freight.setText(p.getFreight());
		
		p2.setComponent(new JLabel("Telefone: "), 0, 4);
		p2.setComponent(phone, 0, 5);
		phone.setText(p.getPhoneNumber());
		
		try 
		{
			CepAPI c = new CepAPI(p.getZipCode());
			
			p2.setComponent(new JLabel("CEP: "), 1, 4);
			p2.setComponent(zipCode, 1, 5);
			zipCode.setText(p.getZipCode());
			zipCode.addActionListener(new Action(this));
			
			p2.setComponent(new JLabel("Rua: "), 0, 6);
			p2.setComponent(address, 0, 7);
			address.setEditable(false);
			address.setText(c.getLogradouro());
			
			p2.setComponent(new JLabel("Bairro: "), 1, 6);
			p2.setComponent(neighborhood, 1, 7);
			neighborhood.setEditable(false);
			neighborhood.setText(c.getBairro());
			
			p2.setComponent(new JLabel("Cidade: "), 0, 8);
			p2.setComponent(city, 0, 9);
			city.setEditable(false);
			city.setText(c.getLocalidade());
			
			p2.setComponent(new JLabel("Estado: "), 1, 8);
			p2.setComponent(state, 1, 9);
			state.setEditable(false);
			state.setText(c.getUf());
		} 
		catch (ParserConfigurationException | SAXException | IOException | CepNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		p2.setComponent(submit, 1, 12);
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
	
	public JTextField getCategory()
	{
		return category;
	}

	public JTextField getName() 
	{
		return name;
	}

	public JTextField getPhone()
	{
		return phone;
	}

	public JTextField getZipCode() 
	{
		return zipCode;
	}
	
	public JTextField getFreight() 
	{
		return freight;
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
