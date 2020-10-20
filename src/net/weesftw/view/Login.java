package net.weesftw.view;

import javax.swing.JTabbedPane;
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
	private TextField user, root, pass, host;
	private PasswordField passwd;
	private Button submit;

	public Login()
	{
		super(new Frame("Login"));
		
		JTabbedPane t = new JTabbedPane(); 
		Panel p = new Panel("Sign In", 4, 4, 4, 4);
		Panel p2 = new Panel("Connection", 4, 4, 4, 4);
		
		host = new TextField(10);
		root = new TextField(10);
		pass = new TextField(10);
		user = new TextField(10);
		passwd = new PasswordField(10);
		submit = new Button("Sign In");
		
		t.add("Login", p);
		t.add("Connection", p2);
		
		p.setComponent(new Label("Usuario: "), 0, 0);
		p.setComponent(user, 1, 0);
		
		p.setComponent(new Label("Senha: "), 0, 1);
		p.setComponent(passwd, 1, 1);
		
		p2.setComponent(new Label("Host: "), 0, 0);
		p2.setComponent(host, 1, 0);
		host.setText("localhost");
		
		p2.setComponent(new Label("Username: "), 0, 1);
		p2.setComponent(root, 1, 1);
		root.setText("root");
		
		p2.setComponent(new Label("Passwd: "), 0, 2);
		p2.setComponent(pass, 1, 2);
		
		p.setComponent(submit, 1, 2);
		submit.addActionListener(new Action(this));
		
		ui.add(t);
		
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
	
	public TextField getRoot()
	{
		return root;
	}
	
	public TextField getPass()
	{
		return pass;
	}
	
	public TextField getHost()
	{
		return host;
	}
}