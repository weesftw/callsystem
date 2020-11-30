package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import net.weesftw.constraint.Gender;
import net.weesftw.exception.CepNotFoundException;
import net.weesftw.manager.Action;
import net.weesftw.manager.CepAPI;
import net.weesftw.model.Dialog;
import net.weesftw.model.Icon;
import net.weesftw.model.Panel;
import net.weesftw.vo.ClientVO;

public class ClientOpen extends UI<JDialog> 
{
	private JButton submit;
	private JComboBox<?> gender;
	private Icon img;
	private JTextField cpf, firstName, lastName, phone, email, dateBorn, zipCode, neighborhood, address, city, state;
	
	public ClientOpen(ClientVO p) 
	{
		super(new Dialog("Client: " + p.getFirstName(), true));
		
		Panel p2 = new Panel("Client Details", 4, 4, 4, 4);
		Panel p1 = new Panel("Photo", 4, 4, 4, 4);
		img = new Icon();
		submit = new JButton("Update");
		gender = new JComboBox<Gender>(Gender.values());		
		cpf = new JTextField(15);
		firstName = new JTextField(15);
		lastName = new JTextField(15);
		phone = new JTextField(15);
		email = new JTextField(15);
		dateBorn = new JTextField(15);
		zipCode = new JTextField(15);
		neighborhood = new JTextField(15);
		address = new JTextField(15);
		city = new JTextField(15);
		state = new JTextField(15);
		
		img.setPath(p.getByte());
		p1.setComponent(img);
		
		p2.setComponent(new JLabel("Nome: "));
		p2.setComponent(firstName, 0, 1);
		firstName.setText(p.getFirstName());
		
		p2.setComponent(new JLabel("Sobrenome: "), 1, 0);
		p2.setComponent(lastName, 1, 1);
		lastName.setText(p.getLastName());
		
		p2.setComponent(new JLabel("CPF: "), 0, 2);
		p2.setComponent(cpf, 0, 3);
		cpf.setText(p.getCpf());
		cpf.setEditable(false);
		
		p2.setComponent(new JLabel("Data de Nascimento: "), 1, 2);
		p2.setComponent(dateBorn, 1, 3);
		dateBorn.setText(p.getDate());
		
		p2.setComponent(new JLabel("Genero: "), 0, 4);
		p2.setComponent(gender, 0, 5);
		gender.setSelectedItem(p.getGender());
		gender.setBackground(Color.WHITE);
		gender.setPreferredSize(new Dimension(0, 15));
		
		p2.setComponent(new JLabel("Telefone: "), 1, 4);
		p2.setComponent(phone, 1, 5);
		phone.setText(p.getPhoneNumber());
		
		p2.setComponent(new JLabel("E-mail: "), 0, 6);
		p2.setComponent(email, 0, 7);
		email.setText(p.getEmail());
		
		try 
		{
			CepAPI c = new CepAPI(p.getZipCode());
			
			p2.setComponent(new JLabel("CEP: "), 1, 6);
			p2.setComponent(zipCode, 1, 7);
			zipCode.setText(p.getZipCode());
			zipCode.addActionListener(new Action(this));
			
			p2.setComponent(new JLabel("Rua: "), 0, 8);
			p2.setComponent(address, 0, 9);
			address.setEditable(false);
			address.setText(c.getLogradouro());
			
			p2.setComponent(new JLabel("Bairro: "), 1, 8);
			p2.setComponent(neighborhood, 1, 9);
			neighborhood.setEditable(false);
			neighborhood.setText(c.getBairro());
			
			p2.setComponent(new JLabel("Cidade: "), 0, 10);
			p2.setComponent(city, 0, 11);
			city.setEditable(false);
			city.setText(c.getLocalidade());
			
			p2.setComponent(new JLabel("Estado: "), 1, 10);
			p2.setComponent(state, 1, 11);
			state.setEditable(false);
			state.setText(c.getUf());
		} 
		catch (ParserConfigurationException | SAXException | IOException | CepNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		p2.setComponent(submit, 1, 12);
		submit.addActionListener(new Action(this));

		
		ui.add(p1, BorderLayout.WEST);
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

	public JComboBox<?> getGender() 
	{
		return gender;
	}

	public Icon getImg() 
	{
		return img;
	}

	public JTextField getCpf()
	{
		return cpf;
	}

	public JTextField getFirstName() 
	{
		return firstName;
	}

	public JTextField getLastName()
	{
		return lastName;
	}

	public JTextField getPhone()
	{
		return phone;
	}

	public JTextField getEmail() 
	{
		return email;
	}

	public JTextField getDateBorn() 
	{
		return dateBorn;
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
