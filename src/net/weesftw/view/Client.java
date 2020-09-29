package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.WindowConstants;

import net.weesftw.constraint.Gender;
import net.weesftw.manager.Action;
import net.weesftw.model.Button;
import net.weesftw.model.ComboBox;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextField;

public class Client extends UI<InternalFrame>
{
	private Button submit;
	private Button choose;
	private ComboBox<Gender> gender;
	private Label img;
	private TextField cpf;
	private TextField firstName;
	private TextField lastName;
	private TextField phoneNumber;
	private TextField email;
	private TextField zipCode;
	private TextField date;
	private TextField address;
	private TextField city;
	private TextField state;
	private TextField neighborhood;
	
	public Client()
	{
		super(new InternalFrame("Client", false, true, false, true));
		
//		ComboBox<Country> country = new ComboBox<Country>(Country.values(), 30, 21);
		Panel p1 = new Panel("Photo", 4, 4, 4, 4);
		Panel p2 = new Panel("New Client", 4, 4, 4, 4);
		
		neighborhood = new TextField(15);
		address = new TextField(15);
		city = new TextField(15);
		state = new TextField(5);
		submit = new Button("Submit");
		choose = new Button("Choose");
		gender = new ComboBox<Gender>(Gender.values(), 30, 21);
		cpf = new TextField(15);
		firstName = new TextField(15);
		lastName = new TextField(15);
		phoneNumber = new TextField(15);
		email = new TextField(15);
		zipCode = new TextField(15);
		date = new TextField(15);
		img = new Label();
		
		img.loadImage();
		
		p1.setComponent(img);
		
		p1.setComponent(choose, 0, 1);
		choose.addActionListener(new Action(this));
		
		p2.setComponent(new Label("Zip Code: "));
		p2.setComponent(zipCode, 1, 0);
		zipCode.addActionListener(new Action(this));
		
		p2.setComponent(new Label("Neighborhood: "), 2, 0);
		p2.setComponent(neighborhood, 3, 0);
		neighborhood.setEditable(false);
		
		p2.setComponent(new Label("Address: "), 0, 1);
		p2.setComponent(address, 1, 1);
		address.setEditable(false);
		
		p2.setComponent(new Label("City: "), 2, 1);
		p2.setComponent(city, 3, 1);
		city.setEditable(false);
		
		p2.setComponent(new Label("State: "), 0, 2);
		p2.setComponent(state, 1, 2);
		state.setEditable(false);
		
		p2.setComponent(new Label("CPF: "), 2, 2);
		p2.setComponent(cpf, 3, 2);
		
		p2.setComponent(new Label("First Name: "), 0, 3);
		p2.setComponent(firstName, 1, 3);
		
		p2.setComponent(new Label("Last Name: "), 2, 3);
		p2.setComponent(lastName, 3, 3);
		
		p2.setComponent(new Label("Phone: "), 0, 4);
		p2.setComponent(phoneNumber, 1, 4);
		
		p2.setComponent(new Label("E-mail: "), 2, 4);
		p2.setComponent(email, 3, 4);
		
		p2.setComponent(new Label("Birth: "), 0, 5);
		p2.setComponent(date, 1, 5);
		
		p2.setComponent(new Label("Gender: "), 2, 5);
		p2.setComponent(gender, 3, 5);
		gender.setBackground(Color.WHITE);
		
		p2.setComponent(submit, 3, 6);
		submit.addActionListener(new Action(this));
		
		ui.add(p1, BorderLayout.WEST);
		ui.add(p2, BorderLayout.EAST);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ui.setVisible(true);
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

	public TextField getPhoneNumber() 
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

	public TextField getDate() 
	{
		return date;
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
