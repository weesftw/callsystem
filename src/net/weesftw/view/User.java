package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

import net.weesftw.constraint.Department;
import net.weesftw.constraint.Gender;
import net.weesftw.constraint.ImagePath;
import net.weesftw.manager.Action;
import net.weesftw.model.Icon;
import net.weesftw.model.Panel;

public class User extends UI<JInternalFrame>
{
	private static User instance;
	
	private JButton submit;
	private JButton choose;
	private JComboBox<Gender> gender;
	private JComboBox<Department> department;
	private JTextField cpf, user, passwd, firstName, lastName, email, zipCode, date, address, city, state, neighborhood;
	private JFormattedTextField phoneNumber;
	private Icon img;
	
	private User()
	{
		super(new JInternalFrame("User", false, true, false, true));
		
		Panel p = new Panel("User", 4, 4, 4, 4);
		Panel p2 = new Panel("Photo", 4, 4, 4, 4);
		Panel p3 = new Panel("New Employee", 4, 4, 4, 4);
		
		department = new JComboBox<Department>(Department.values());
		gender = new JComboBox<Gender>(Gender.values());
		img = new Icon();
		choose = new JButton("Choose");
		submit = new JButton("Submit");
		neighborhood = new JTextField(15);
		address = new JTextField(15);
		city = new JTextField(15);
		state = new JTextField(5);
		firstName = new JTextField(15);
		lastName = new JTextField(15);
		
		try 
		{
			phoneNumber = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		email = new JTextField(15);
		zipCode = new JTextField(15);
		date = new JTextField(15);
		cpf = new JTextField(15);
		user = new JTextField(15);
		passwd = new JTextField(15);
		
		img.setPath(ImagePath.ICON);
		p2.setComponent(img);
		
		p2.setComponent(choose, 0, 1);
		choose.addActionListener(new Action(this));
		
		p3.setComponent(new JLabel("CPF: "));
		p3.setComponent(cpf, 0, 1);
		cpf.setEditable(false);
		
		p3.setComponent(new JLabel("Nome: "), 0, 2);
		p3.setComponent(firstName, 0, 3);
		
		p3.setComponent(new JLabel("Sobrenome: "), 0, 4);
		p3.setComponent(lastName, 0, 5);
		
		p3.setComponent(new JLabel("Telefone: "), 0, 6);
		p3.setComponent(phoneNumber, 0, 7);
		
		p3.setComponent(new JLabel("E-mail: "), 0, 8);
		p3.setComponent(email, 0, 9);
		
		p3.setComponent(new JLabel("Data de Nascimento: "), 0, 10);
		p3.setComponent(date, 0, 11);
		
		p3.setComponent(new JLabel("Genero: "), 0, 12);
		p3.setComponent(gender, 0, 13);
		gender.setBackground(Color.WHITE);
		
		p3.setComponent(new JLabel("CEP: "), 1, 0);
		p3.setComponent(zipCode, 1, 1);
		zipCode.addActionListener(new Action(this));
		
		p3.setComponent(new JLabel("Bairro: "), 1, 2);
		p3.setComponent(neighborhood, 1, 3);
		neighborhood.setEditable(false);
		
		p3.setComponent(new JLabel("Rua: "), 1, 4);
		p3.setComponent(address, 1, 5);
		address.setEditable(false);
		
		p3.setComponent(new JLabel("Cidade: "), 1, 6);
		p3.setComponent(city, 1, 7);
		city.setEditable(false);
		
		p3.setComponent(new JLabel("Estado: "), 1, 8);
		p3.setComponent(state, 1, 9);
		state.setEditable(false);
		
		p.setComponent(new JLabel("Usuario: "), 0, 1);
		p.setComponent(user, 1, 1);
		
		p.setComponent(new JLabel("Senha: "), 0, 2);
		p.setComponent(passwd, 1, 2);
		
		p.setComponent(new JLabel("Departamento: "), 0, 3);
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
		img.setPath(ImagePath.ICON);
	}
	
	public static synchronized User getInstance()
	{
		return instance != null ? instance : new User(); 
	}
	
	public JButton getSubmit() 
	{
		return submit;
	}

	public JButton getChoose() 
	{
		return choose;
	}

	public JComboBox<Gender> getGender() 
	{
		return gender;
	}

	public JComboBox<Department> getDepartment() 
	{
		return department;
	}

	public JTextField getCpf() 
	{
		return cpf;
	}

	public JTextField getUser() 
	{
		return user;
	}

	public JTextField getPasswd() 
	{
		return passwd;
	}

	public JTextField getFirstName() 
	{
		return firstName;
	}

	public JTextField getLastName() 
	{
		return lastName;
	}

	public JFormattedTextField getPhoneNumber() 
	{
		return phoneNumber;
	}

	public JTextField getEmail() 
	{
		return email;
	}

	public JTextField getZipCode() 
	{
		return zipCode;
	}

	public JTextField getDate() 
	{
		return date;
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

	public JTextField getNeighborhood() 
	{
		return neighborhood;
	}

	public Icon getImg() 
	{
		return img;
	}
}
