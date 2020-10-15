package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

import net.weesftw.constraint.Department;
import net.weesftw.constraint.Gender;
import net.weesftw.constraint.ImagePath;
import net.weesftw.manager.Action;
import net.weesftw.model.Button;
import net.weesftw.model.ComboBox;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextField;

public class User extends UI<InternalFrame>
{
	private static User instance;
	
	private Button submit;
	private Button choose;
	private ComboBox<Gender> gender;
	private ComboBox<Department> department;
	private TextField cpf, user, passwd, firstName, lastName, email, zipCode, date, address, city, state, neighborhood;
	private JFormattedTextField phoneNumber;
	private Label img;
	
	private User()
	{
		super(new InternalFrame("User", false, true, false, true));
		
		Panel p = new Panel("User", 4, 4, 4, 4);
		Panel p2 = new Panel("Photo", 4, 4, 4, 4);
		Panel p3 = new Panel("New Employee", 4, 4, 4, 4);
		
		department = new ComboBox<Department>(Department.values());
		gender = new ComboBox<Gender>(Gender.values());
		img = new Label();
		choose = new Button("Choose");
		submit = new Button("Submit");
		neighborhood = new TextField(15);
		address = new TextField(15);
		city = new TextField(15);
		state = new TextField(5);
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
		cpf = new TextField(15);
		user = new TextField(15);
		passwd = new TextField(15);
				
		p2.setComponent(img);
		img.loadImage(ImagePath.ICON, 120, 120);
		
		p2.setComponent(choose, 0, 1);
		choose.addActionListener(new Action(this));
		
		p3.setComponent(new Label("CPF: "));
		p3.setComponent(cpf, 0, 1);
		cpf.setEditable(false);
		
		p3.setComponent(new Label("Nome: "), 0, 2);
		p3.setComponent(firstName, 0, 3);
		
		p3.setComponent(new Label("Sobrenome: "), 0, 4);
		p3.setComponent(lastName, 0, 5);
		
		p3.setComponent(new Label("Telefone: "), 0, 6);
		p3.setComponent(phoneNumber, 0, 7);
		
		p3.setComponent(new Label("E-mail: "), 0, 8);
		p3.setComponent(email, 0, 9);
		
		p3.setComponent(new Label("Data de Nascimento: "), 0, 10);
		p3.setComponent(date, 0, 11);
		
		p3.setComponent(new Label("Genero: "), 0, 12);
		p3.setComponent(gender, 0, 13);
		gender.setBackground(Color.WHITE);
		
		p3.setComponent(new Label("CEP: "), 1, 0);
		p3.setComponent(zipCode, 1, 1);
		zipCode.addActionListener(new Action(this));
		
		p3.setComponent(new Label("Bairro: "), 1, 2);
		p3.setComponent(neighborhood, 1, 3);
		neighborhood.setEditable(false);
		
		p3.setComponent(new Label("Rua: "), 1, 4);
		p3.setComponent(address, 1, 5);
		address.setEditable(false);
		
		p3.setComponent(new Label("Cidade: "), 1, 6);
		p3.setComponent(city, 1, 7);
		city.setEditable(false);
		
		p3.setComponent(new Label("Estado: "), 1, 8);
		p3.setComponent(state, 1, 9);
		state.setEditable(false);
		
		p.setComponent(new Label("Usuario: "), 0, 1);
		p.setComponent(user, 1, 1);
		
		p.setComponent(new Label("Senha: "), 0, 2);
		p.setComponent(passwd, 1, 2);
		
		p.setComponent(new Label("Departamento: "), 0, 3);
		p.setComponent(department, 1, 3);
		department.setBackground(Color.WHITE);
		
		p.setComponent(submit, 1, 4);
		submit.addActionListener(new Action(this));
		
		ui.add(p2, BorderLayout.WEST);
		ui.add(p3);
		ui.add(p, BorderLayout.SOUTH);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public void clear()
	{
		cpf.setText("");
		firstName.setText("");
		lastName.setText("");
		phoneNumber.setText("");
		email.setText("");
		date.setText("");
		gender.setSelectedIndex(0);;
		zipCode.setText("");
		neighborhood.setText("");
		address.setText("");
		city.setText("");
		state.setText("");
		user.setText("");
		passwd.setText("");
		department.setSelectedIndex(0);
		img.loadImage(ImagePath.ICON, 120, 120);
	}
	
	public static User getInstance()
	{
		return instance != null ? instance : new User(); 
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

	public ComboBox<Department> getDepartment() 
	{
		return department;
	}

	public TextField getCpf() 
	{
		return cpf;
	}

	public TextField getUser() 
	{
		return user;
	}

	public TextField getPasswd() 
	{
		return passwd;
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

	public Label getImg() 
	{
		return img;
	}
}
