package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.WindowConstants;

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
	private Button submit, choose, search;
	private ComboBox<Gender> gender;
	private Label img;
	private TextField cnpj, name, owner, cpf, firstName, lastName, phoneNumber, email, zipCode, zipCodeEmployee, date, address, city, state, neighborhood;
	
	public Client()
	{
		super(new InternalFrame("Client", false, true, false, true));
		
		boolean privilege = Main.instance.getAuth().isPrivilege();
		Panel p1 = new Panel("Photo", 4, 4, 4, 4);
		Panel p2 = new Panel("New Client", 4, 4, 4, 4);
		
		gender = new ComboBox<Gender>(Gender.values(), 30, 21);
		submit = new Button("Submit");
		choose = new Button("Choose");
		search = new Button(ImagePath.SEARCH, 13, 13);
		img = new Label(ImagePath.ICON, 120, 120);
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
		phoneNumber = new TextField(15);
		email = new TextField(15);
		zipCode = new TextField(15);
		date = new TextField(15);
				
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
		ui.add(p2);
				
		if(privilege)
		{			
			Panel p3 = new Panel("New Company", 4, 4, 4, 4);
			
			p3.setComponent(new Label("CNPJ: "));
			p3.setComponent(cnpj, 1, 0);
			
			p3.setComponent(new Label("Name: "), 0, 1);
			p3.setComponent(name, 1, 1);
			
			p3.setComponent(new Label("Zip Code: "), 0, 3);
			p3.setComponent(zipCodeEmployee, 1, 3);
			
			p3.setComponent(submit, 1, 4);
			submit.addActionListener(new Action(this));
			
			ui.add(p3, BorderLayout.EAST);
		}
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ui.setVisible(true);
	}
	
	public Button getSubmit() 
	{
		return submit;
	}
	
	public Button getSearch()
	{
		return search;
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
