package net.weesftw.view;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.WindowConstants;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import net.weesftw.manager.CepAPI;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextField;
import net.weesftw.vo.PeopleVO;

public class Account extends UI<InternalFrame>
{	
	public Account() 
	{
		super(new InternalFrame("Account", false, true, false, true));
		
		PeopleVO p = Main.instance.getAuth().getPeople();
		Panel p2 = new Panel("Your Account", 4, 4, 4, 4);
		Panel p1 = new Panel("Photo", 4, 4, 4, 4);
		Label img = new Label();		
		
		p1.setComponent(img);
		img.loadImage(p.getByte(), 160, 160);
		
		p2.setComponent(new Label("Name: "));
		p2.setComponent(new TextField(30, p.getFirstName() + " " + p.getLastName(), false), 0, 1);
		
		p2.setComponent(new Label("CPF: "), 1, 0);
		p2.setComponent(new TextField(30, p.getCpf(), false), 1, 1);
		
		p2.setComponent(new Label("Birth: "), 0, 2);
		p2.setComponent(new TextField(30, p.getDate(), false), 0, 3);
		
		p2.setComponent(new Label("Gender: "), 1, 2);
		p2.setComponent(new TextField(30, p.getGender().name(), false), 1, 3);
		
		p2.setComponent(new Label("Phone: "), 0, 4);
		p2.setComponent(new TextField(30, p.getPhoneNumber(), false), 0, 5);
		
		p2.setComponent(new Label("E-mail: "), 1, 4);
		p2.setComponent(new TextField(30, p.getEmail(), false), 1, 5);
		
		try 
		{
			CepAPI c = new CepAPI(p.getZipCode());
			
			p2.setComponent(new Label("Zip Code: "), 0, 6);
			p2.setComponent(new TextField(30, p.getZipCode(), false), 0, 7);
			
			p2.setComponent(new Label("Address: "), 1, 6);
			p2.setComponent(new TextField(30, c.getLogradouro(), false), 1, 7);
			
			p2.setComponent(new Label("Neighborhood: "), 0, 8);
			p2.setComponent(new TextField(30, c.getBairro(), false), 0, 9);
			
			p2.setComponent(new Label("City: "), 1, 8);
			p2.setComponent(new TextField(30, c.getLocalidade(), false), 1, 9);
			
			p2.setComponent(new Label("State: "), 0, 10);
			p2.setComponent(new TextField(30, c.getUf(), false), 0, 11);
		} 
		catch (ParserConfigurationException | SAXException | IOException e) 
		{
			e.printStackTrace();
		}
		
		ui.add(p1, BorderLayout.WEST);
		ui.add(p2, BorderLayout.EAST);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ui.setVisible(true);
	}
}
