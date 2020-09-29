package net.weesftw.view;

import javax.swing.WindowConstants;

import net.weesftw.constraint.Department;
import net.weesftw.manager.Action;
import net.weesftw.model.Button;
import net.weesftw.model.ComboBox;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextField;

public class User extends UI<InternalFrame>
{
	private Button submit;
	private ComboBox<Department> department;
	private TextField cpf, user, passwd;
	
	public User()
	{
		super(new InternalFrame("User", false, true, false, true));
		
		Panel p = new Panel("New User", 4, 4, 4, 4);
		
		department = new ComboBox<Department>(Department.values(), 30, 21);
		submit = new Button("Submit");
		cpf = new TextField(15);
		user = new TextField(15);
		passwd = new TextField(15);
		
		p.setComponent(new Label("CPF: "));
		p.setComponent(cpf, 1, 0);
		
		p.setComponent(new Label("Username: "), 0, 1);
		p.setComponent(user, 1, 1);
		
		p.setComponent(new Label("Password: "), 0, 2);
		p.setComponent(passwd, 1, 2);
		
		p.setComponent(new Label("Department: "), 0, 3);
		p.setComponent(department, 1, 3);
		
		p.setComponent(submit, 1, 4);
		submit.addActionListener(new Action(this));
		
		ui.add(p);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ui.setVisible(true);
	}

	public Button getSubmit() 
	{
		return submit;
	}
	
	public TextField getCpf() {
		return cpf;
	}

	public TextField getUser() 
	{
		return user;
	}

	public TextField getPasswd() 
	{
		return passwd;
	}
	
	public ComboBox<?> getDepartment()
	{
		return department;
	}
}
