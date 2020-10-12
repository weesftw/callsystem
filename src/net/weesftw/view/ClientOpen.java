package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.WindowConstants;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import net.weesftw.constraint.Gender;
import net.weesftw.exception.CepNotFoundException;
import net.weesftw.manager.Action;
import net.weesftw.manager.CepAPI;
import net.weesftw.model.Button;
import net.weesftw.model.ComboBox;
import net.weesftw.model.Dialog;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextField;
import net.weesftw.vo.ClientVO;

public class ClientOpen extends UI<Dialog> 
{
	private Button submit;
	private ComboBox<?> gender;
	private Label img;
	private TextField cpf, firstName, lastName, phone, email, dateBorn, zipCode, neighborhood, address, city, state;
	
	public ClientOpen(ClientVO p) 
	{
		super(new Dialog("Client: " + p.getFirstName(), true));
		
		Panel p2 = new Panel("Client Details", 4, 4, 4, 4);
		Panel p1 = new Panel("Photo", 4, 4, 4, 4);
		Label img = new Label();
		submit = new Button("Update");
		gender = new ComboBox<Gender>(Gender.values());		
		cpf = new TextField(15);
		firstName = new TextField(15);
		lastName = new TextField(15);
		phone = new TextField(15);
		email = new TextField(15);
		dateBorn = new TextField(15);
		zipCode = new TextField(15);
		neighborhood = new TextField(15);
		address = new TextField(15);
		city = new TextField(15);
		state = new TextField(15);
		
		p1.setComponent(img);
		img.loadImage(p.getByte(), 120, 120);
		
		p2.setComponent(new Label("Nome: "));
		p2.setComponent(firstName, 0, 1);
		firstName.setText(p.getFirstName());
		
		p2.setComponent(new Label("Sobrenome: "), 1, 0);
		p2.setComponent(lastName, 1, 1);
		lastName.setText(p.getLastName());
		
		p2.setComponent(new Label("CPF: "), 0, 2);
		p2.setComponent(cpf, 0, 3);
		cpf.setText(p.getCpf());
		cpf.setEditable(false);
		
		p2.setComponent(new Label("Data de Nascimento: "), 1, 2);
		p2.setComponent(dateBorn, 1, 3);
		dateBorn.setText(p.getDate());
		
		p2.setComponent(new Label("Genero: "), 0, 4);
		p2.setComponent(gender, 0, 5);
		gender.setSelectedItem(p.getGender());
		gender.setBackground(Color.WHITE);
		gender.setPreferredSize(new Dimension(0, 15));
		
		p2.setComponent(new Label("Telefone: "), 1, 4);
		p2.setComponent(phone, 1, 5);
		phone.setText(p.getPhoneNumber());
		
		p2.setComponent(new Label("E-mail: "), 0, 6);
		p2.setComponent(email, 0, 7);
		email.setText(p.getEmail());
		
		try 
		{
			CepAPI c = new CepAPI(p.getZipCode());
			
			p2.setComponent(new Label("CEP: "), 1, 6);
			p2.setComponent(zipCode, 1, 7);
			zipCode.setText(p.getZipCode());
			zipCode.addActionListener(new Action(this));
			
			p2.setComponent(new Label("Rua: "), 0, 8);
			p2.setComponent(address, 0, 9);
			address.setEditable(false);
			address.setText(c.getLogradouro());
			
			p2.setComponent(new Label("Bairro: "), 1, 8);
			p2.setComponent(neighborhood, 1, 9);
			neighborhood.setEditable(false);
			neighborhood.setText(c.getBairro());
			
			p2.setComponent(new Label("Cidade: "), 0, 10);
			p2.setComponent(city, 0, 11);
			city.setEditable(false);
			city.setText(c.getLocalidade());
			
			p2.setComponent(new Label("Estado: "), 1, 10);
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
		ui.add(p2, BorderLayout.EAST);
		
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

	public ComboBox<?> getGender() 
	{
		return gender;
	}

	public Label getImg() 
	{
		return img;
	}

	public TextField getCpf()
	{
		return cpf;
	}

	public TextField getFirstName() 
	{
		return firstName;
	}

	public TextField getLastName()
	{
		return lastName;
	}

	public TextField getPhone()
	{
		return phone;
	}

	public TextField getEmail() 
	{
		return email;
	}

	public TextField getDateBorn() 
	{
		return dateBorn;
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
