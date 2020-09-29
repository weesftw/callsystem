package net.weesftw.view;

import java.awt.BorderLayout;

import javax.swing.WindowConstants;

import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.vo.PeopleVO;

public class Account extends UI<InternalFrame>
{	
	public Account() 
	{
		super(new InternalFrame("Account", false, true, false, true));
		
		Panel p2 = new Panel("Your Account", 4, 4, 4, 4);
		PeopleVO p = Main.instance.getAuth().getPeople();
		Panel p1 = new Panel("Photo", 4, 4, 4, 4);
		Label img = new Label();
		
		img.loadImage(p.getByte(), 120, 120);
		
		p1.setComponent(img);
		
		p2.setComponent(new Label("Name: " + p.getFirstName() + " " + p.getLastName() + " (" + p.getCpf() + ")"));
		
		p2.setComponent(new Label("Birth: " + p.getDate()), 0, 1);
		
		p2.setComponent(new Label("Gender: " + p.getGender().name()), 0, 2);
		
		p2.setComponent(new Label("Phone: " + p.getPhoneNumber()), 0, 3);
		
		p2.setComponent(new Label("E-mail: " + p.getEmail()), 0, 4);
		
		p2.setComponent(new Label("Address: " + p.getZipCode()), 0, 5);
		
		ui.add(p1, BorderLayout.WEST);
		ui.add(p2, BorderLayout.EAST);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ui.setVisible(true);
	}
}
