package net.weesftw.view;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import net.weesftw.manager.Action;
import net.weesftw.model.Button;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.PasswordField;
import net.weesftw.model.TextField;

public class Login extends UI
{
	private static final long serialVersionUID = 1L;
	
	public Login()
	{
		super(new JFrame());
		
		Panel p = new Panel("Sign In", 4, 4, 4, 4);
		TextField user = new TextField(10);
		PasswordField passwd = new PasswordField(10);
		Button submit = new Button("Sign In");
		
		p.setComponent(new Label("Username: "), 0, 0);
		p.setComponent(user, 1, 0);
		
		p.setComponent(new Label("Password: "), 0, 1);
		p.setComponent(passwd, 1, 1);
		
		p.setComponent(submit, 1, 2);
		submit.addActionListener(new Action(this));
		
		c.add(p);
		
		c.setSize(250, 200);
		((JFrame) c).setResizable(false);
		((Window) c).setLocationRelativeTo(null);
		((JFrame) c).setDefaultCloseOperation(EXIT_ON_CLOSE);
		c.setVisible(true);
	}
}