package net.weesftw.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import net.weesftw.manager.Action;
import net.weesftw.model.Panel;

public class Login extends UI<JFrame>
{
	private JTextField user, root, pass, host;
	private JPasswordField passwd;
	private JButton submit;

	public Login()
	{
		super(new JFrame("Login"));
		
		JTabbedPane t = new JTabbedPane(); 
		Panel p = new Panel("Sign In", 4, 4, 4, 4);
		Panel p2 = new Panel("Connection", 4, 4, 4, 4);
		
		host = new JTextField(10);
		root = new JTextField(10);
		pass = new JTextField(10);
		user = new JTextField(10);
		passwd = new JPasswordField(10);
		submit = new JButton("Sign In");
		
		t.add("Login", p);
		t.add("Connection", p2);
		
		p.setComponent(new JLabel("Usuario: "), 0, 0);
		p.setComponent(user, 1, 0);
		
		p.setComponent(new JLabel("Senha: "), 0, 1);
		p.setComponent(passwd, 1, 1);
		
		p2.setComponent(new JLabel("Host: "), 0, 0);
		p2.setComponent(host, 1, 0);
		host.setText("localhost");
		
		p2.setComponent(new JLabel("Username: "), 0, 1);
		p2.setComponent(root, 1, 1);
		root.setText("root");
		
		p2.setComponent(new JLabel("Passwd: "), 0, 2);
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
	
	public JTextField getUser() 
	{
		return user;
	}

	public JPasswordField getPasswd() 
	{
		return passwd;
	}

	public JButton getSubmit() 
	{
		return submit;
	}
	
	public JTextField getRoot()
	{
		return root;
	}
	
	public JTextField getPass()
	{
		return pass;
	}
	
	public JTextField getHost()
	{
		return host;
	}
}