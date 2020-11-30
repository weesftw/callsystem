package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

import net.weesftw.constraint.Gender;
import net.weesftw.constraint.ImagePath;
import net.weesftw.manager.Action;
import net.weesftw.model.Icon;
import net.weesftw.model.Panel;

public class Client extends UI<JInternalFrame>
{	
	private static Client instance;
	
	private JButton submit, choose;
	private JComboBox<Gender> gender;
	private Icon img;
	private JTextField cpf, firstName, lastName, email, zipCode, zipCodeEmployee, date, address, city, state, neighborhood;
	private JFormattedTextField phoneNumber;
	
	private Client()
	{
		super(new JInternalFrame("Client", false, true, false, true));
		
		Panel p1 = new Panel("Photo", 4, 4, 4, 4);
		Panel p2 = new Panel("New Client", 4, 4, 4, 4);
		
		gender = new JComboBox<Gender>(Gender.values());
		submit = new JButton("Submit");
		choose = new JButton("Choose");
		img = new Icon();
		zipCode = new JTextField(3);
		zipCodeEmployee = new JTextField(15);
		neighborhood = new JTextField(15);
		address = new JTextField(15);
		city = new JTextField(15);
		state = new JTextField(15);
		cpf = new JTextField(15);
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
		
		img.setPath(ImagePath.ICON);
		p1.setComponent(img);
		
		p1.setComponent(choose, 0, 1);
		choose.addActionListener(new Action(this));
		
		p2.setComponent(new JLabel("CEP: "));
		p2.setComponent(zipCode, 1, 0);
		zipCode.addActionListener(new Action(this));
		
		p2.setComponent(new JLabel("Rua: "), 2, 0);
		p2.setComponent(neighborhood, 3, 0);
		neighborhood.setEditable(false);
		
		p2.setComponent(new JLabel("Bairro: "), 0, 1);
		p2.setComponent(address, 1, 1);
		address.setEditable(false);
		
		p2.setComponent(new JLabel("Cidade: "), 2, 1);
		p2.setComponent(city, 3, 1);
		city.setEditable(false);
		
		p2.setComponent(new JLabel("Estado: "), 0, 2);
		p2.setComponent(state, 1, 2);
		state.setEditable(false);
		
		p2.setComponent(new JLabel("CPF: "), 2, 2);
		p2.setComponent(cpf, 3, 2);
		cpf.setEditable(false);
		
		p2.setComponent(new JLabel("Nome: "), 0, 3);
		p2.setComponent(firstName, 1, 3);
		
		p2.setComponent(new JLabel("Sobrenome: "), 2, 3);
		p2.setComponent(lastName, 3, 3);
		
		p2.setComponent(new JLabel("Telefone: "), 0, 4);
		p2.setComponent(phoneNumber, 1, 4);
		
		p2.setComponent(new JLabel("E-mail: "), 2, 4);
		p2.setComponent(email, 3, 4);
		
		p2.setComponent(new JLabel("Data de Nascimento: "), 0, 5);
		p2.setComponent(date, 1, 5);
		
		p2.setComponent(new JLabel("Genero: "), 2, 5);
		p2.setComponent(gender, 3, 5);
		gender.setBackground(Color.WHITE);
		
		p2.setComponent(submit, 3, 6);
		submit.addActionListener(new Action(this));
		
		ui.add(p1, BorderLayout.WEST);
		ui.add(p2);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static synchronized Client getInstance()
	{		
		return instance != null ? instance : new Client(); 
	}
	
	public void clear()
	{						
		neighborhood.setText("");
		zipCode.setText("");
		address.setText("");
		city.setText("");
		state.setText("");
		cpf.setText("");
		firstName.setText("");
		lastName.setText("");
		phoneNumber.setText("");
		email.setText("");
		date.setText("");
		img.setIcon(new ImageIcon(ImagePath.ICON.toString()));
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

	public JTextField getZipCodeEmployee() 
	{
		return zipCodeEmployee;
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
}
