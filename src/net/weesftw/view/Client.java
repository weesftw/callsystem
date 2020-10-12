package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

import net.weesftw.constraint.Gender;
import net.weesftw.constraint.ImagePath;
import net.weesftw.manager.Action;
import net.weesftw.model.Button;
import net.weesftw.model.ComboBox;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextField;

public class Client extends UI<InternalFrame>
{	
	private static Client instance;
	
	private Button submit, choose;
	private ComboBox<Gender> gender;
	private Label img;
	private TextField cnpj, name, owner, cpf, firstName, lastName, email, zipCode, zipCodeEmployee, date, address, city, state, neighborhood;
	private JFormattedTextField phoneNumber;
	
	private Client()
	{
		super(new InternalFrame("Client", false, true, false, true));
		
		Panel p1 = new Panel("Photo", 4, 4, 4, 4);
		Panel p2 = new Panel("New Client", 4, 4, 4, 4);
		
		gender = new ComboBox<Gender>(Gender.values());
		submit = new Button("Submit");
		choose = new Button("Choose");
		img = new Label();
		cnpj = new TextField(15);
		name = new TextField(15);
		zipCode = new TextField(3);
		zipCodeEmployee = new TextField(15);
		neighborhood = new TextField(15);
		address = new TextField(15);
		city = new TextField(15);
		state = new TextField(15);
		cpf = new TextField(15);
		firstName = new TextField(15);
		lastName = new TextField(15);
		
		try 
		{
			phoneNumber = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		email = new TextField(15);
		zipCode = new TextField(15);
		date = new TextField(15);
				
		p1.setComponent(img);
		img.loadImage(ImagePath.ICON, 120, 120);
		
		p1.setComponent(choose, 0, 1);
		choose.addActionListener(new Action(this));
		
		p2.setComponent(new Label("CEP: "));
		p2.setComponent(zipCode, 1, 0);
		zipCode.addActionListener(new Action(this));
		
		p2.setComponent(new Label("Rua: "), 2, 0);
		p2.setComponent(neighborhood, 3, 0);
		neighborhood.setEditable(false);
		
		p2.setComponent(new Label("Bairro: "), 0, 1);
		p2.setComponent(address, 1, 1);
		address.setEditable(false);
		
		p2.setComponent(new Label("Cidade: "), 2, 1);
		p2.setComponent(city, 3, 1);
		city.setEditable(false);
		
		p2.setComponent(new Label("Estado: "), 0, 2);
		p2.setComponent(state, 1, 2);
		state.setEditable(false);
		
		p2.setComponent(new Label("CPF: "), 2, 2);
		p2.setComponent(cpf, 3, 2);
		
		p2.setComponent(new Label("Nome: "), 0, 3);
		p2.setComponent(firstName, 1, 3);
		
		p2.setComponent(new Label("Sobrenome: "), 2, 3);
		p2.setComponent(lastName, 3, 3);
		
		p2.setComponent(new Label("Telefone: "), 0, 4);
		p2.setComponent(phoneNumber, 1, 4);
		
		p2.setComponent(new Label("E-mail: "), 2, 4);
		p2.setComponent(email, 3, 4);
		
		p2.setComponent(new Label("Data de Nascimento: "), 0, 5);
		p2.setComponent(date, 1, 5);
		
		p2.setComponent(new Label("Genero: "), 2, 5);
		p2.setComponent(gender, 3, 5);
		gender.setBackground(Color.WHITE);
		
		p2.setComponent(submit, 3, 6);
		submit.addActionListener(new Action(this));
		
		ui.add(p1, BorderLayout.WEST);
		ui.add(p2);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static Client getInstance()
	{
		return instance != null ? instance : new Client(); 
	}
	
	public Button getSubmit() 
	{
		return submit;
	}

	public Button getChoose() 
	{
		return choose;
	}

	public ComboBox<Gender> getGender() 
	{
		return gender;
	}

	public Label getImg() 
	{
		return img;
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

	public JFormattedTextField getPhoneNumber() 
	{
		return phoneNumber;
	}

	public TextField getEmail() 
	{
		return email;
	}

	public TextField getZipCode() 
	{
		return zipCode;
	}

	public TextField getZipCodeEmployee() 
	{
		return zipCodeEmployee;
	}

	public TextField getDate() 
	{
		return date;
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

	public TextField getNeighborhood() 
	{
		return neighborhood;
	}
}
