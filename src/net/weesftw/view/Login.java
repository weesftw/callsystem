package net.weesftw.view;

import javax.swing.WindowConstants;

import net.weesftw.manager.Action;
import net.weesftw.model.Button;
import net.weesftw.model.Frame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.PasswordField;
import net.weesftw.model.TextField;

public class Login extends UI<Frame>
{
	private TextField user;
	private PasswordField passwd;
	private Button submit;

	public Login()
	{
		super(new Frame("Login"));
				
		Panel p = new Panel("Sign In", 4, 4, 4, 4);
		user = new TextField(10);
		passwd = new PasswordField(10);
		submit = new Button("Sign In");
		
		p.setComponent(new Label("User: "), 0, 0);
		p.setComponent(user, 1, 0);
		
		p.setComponent(new Label("Password: "), 0, 1);
		p.setComponent(passwd, 1, 1);
		
		p.setComponent(submit, 1, 2);
		submit.addActionListener(new Action(this));
		
		ui.add(p);
		
		ui.setSize(250, 200);
		ui.setResizable(false);
		ui.setLocationRelativeTo(null);
		ui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ui.setVisible(true);
	}
	
	public TextField getUser() 
	{
		return user;
	}

	public PasswordField getPasswd() 
	{
		return passwd;
	}

	public Button getSubmit() 
	{
		return submit;
	}
}