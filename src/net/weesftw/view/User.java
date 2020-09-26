package net.weesftw.view;

import javax.swing.WindowConstants;

import net.weesftw.model.Button;
import net.weesftw.model.ComboBox;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextField;

public class User extends UI<InternalFrame>
{	
	public User()
	{
		super(new InternalFrame("User", false, true, false, true));
		
		Button submit = new Button("Submit");
		Panel p = new Panel("New User", 4, 4, 4, 4);
		TextField cpf = new TextField(10);
		TextField user = new TextField(10);
		TextField passwd = new TextField(10);
		
		p.setComponent(new Label("CPF: "));
		p.setComponent(cpf, 1, 0);
		
		p.setComponent(new Label("Username: "), 0, 1);
		p.setComponent(user, 1, 1);
		
		p.setComponent(new Label("Password: "), 0, 2);
		p.setComponent(passwd, 1, 2);
		
		p.setComponent(submit, 1, 3);
		
		ui.add(p);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ui.setVisible(true);
	}
}
