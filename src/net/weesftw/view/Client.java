package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;

import net.weesftw.constraint.Country;
import net.weesftw.model.Button;
import net.weesftw.model.ComboBox;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextField;

public class Client extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	
	private FileInputStream i;
	
	public Client()
	{
		super("Client", false, true, false, true);
		
		Button submit = new Button("Submit");
		Button choose = new Button("Choose");
		ComboBox<Country> country = new ComboBox<>(Country.values(), 30, 21);
		Label img = new Label();
		Panel p1 = new Panel("Photo", 4, 4, 4, 4);
		Panel p2 = new Panel("New Client", 4, 4, 4, 4);
		TextField cpf = new TextField();
		TextField firstName = new TextField();
		TextField lastName = new TextField();
		TextField phoneNumber = new TextField();
		TextField email = new TextField();
		TextField zipCode = new TextField();
		TextField date = new TextField();
		
		Container c = getContentPane();
		
		img.loadImage(120, 120);
		
		p1.setComponent(img);
		
		p1.setComponent(choose, 0, 1);
		choose.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{				
				JFileChooser f = new JFileChooser();
				
				if(f.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					try 
					{
						i = new FileInputStream(f.getSelectedFile());
						
						System.out.println(i);
					} 
					catch (FileNotFoundException ex) 
					{
						ex.printStackTrace();
					}
					
					img.loadImage(f.getSelectedFile().getPath(), 120, 120);
				}
			}
		});
		
		p2.setComponent(new Label("Zip Code: "));
		p2.setComponent(zipCode, 1, 0, 120);
		
		p2.setComponent(new Label("Country: "), 2, 0);
		p2.setComponent(country, 3, 0, 120);
		country.setBackground(Color.WHITE);
		
		p2.setComponent(new Label("CPF: "), 0, 1);
		p2.setComponent(cpf, 1, 1, 120);
		
		p2.setComponent(new Label("First Name: "), 2, 1);
		p2.setComponent(firstName, 3, 1, 120);
		
		p2.setComponent(new Label("Last Name: "), 0, 2);
		p2.setComponent(lastName, 1, 2, 120);
		
		p2.setComponent(new Label("Phone Number: "), 2, 2);
		p2.setComponent(phoneNumber, 3, 2, 120);
		
		p2.setComponent(new Label("E-mail: "), 0, 3);
		p2.setComponent(email, 1, 3, 120);
		
		p2.setComponent(new Label("Date Birth: "), 2, 3);
		p2.setComponent(date, 3, 3, 120);
		
		p2.setComponent(submit, 3, 4);
		
		c.add(p1, BorderLayout.WEST);
		c.add(p2, BorderLayout.EAST);
		
		pack();
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
