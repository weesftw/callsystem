package net.weesftw.manager;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import net.weesftw.constraint.Department;
import net.weesftw.constraint.Gender;
import net.weesftw.constraint.Type;
import net.weesftw.dao.CompanyDAO;
import net.weesftw.dao.PeopleDAO;
import net.weesftw.dao.UserDAO;
import net.weesftw.exception.TypeException;
import net.weesftw.model.DesktopPane;
import net.weesftw.view.Account;
import net.weesftw.view.Client;
import net.weesftw.view.Company;
import net.weesftw.view.Login;
import net.weesftw.view.Main;
import net.weesftw.view.Product;
import net.weesftw.view.Ticket;
import net.weesftw.view.UI;
import net.weesftw.view.User;
import net.weesftw.vo.CompanyVO;
import net.weesftw.vo.PeopleVO;
import net.weesftw.vo.UserVO;

@SuppressWarnings("deprecation")
public class Action implements ActionListener
{
	private UI<?> ui;
	
	public Action(UI<?> ui)
	{
		this.ui = ui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		DesktopPane d = null;
		String a = e.getActionCommand();
		
		Main m = Main.instance;
		
		if(m != null)
		{
			d = m.getDesktop();
		}
		
		if(ui instanceof Login)
		{
			Login l = ((Login) ui);
			UserDAO ud = new UserDAO();
			PeopleDAO pd = new PeopleDAO();
			Window c = (Window) ui.getUI();
			
			String user = l.getUser().getText();
			String passwd = l.getPasswd().getText();
			
			if((!user.isEmpty() && !passwd.isEmpty()))
			{
				UserVO us = ud.search(user);
				
				if(us != null)
				{
					if(ud.isValid(user, passwd))
					{
						c.dispose();
						
						try 
						{
							PeopleVO p = pd.search(Type.USERNAME, user);
							
							new Main(p);
						} 
						catch(TypeException ex) 
						{
							ex.printStackTrace();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Username or Password incorrect.");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Username not found.");
				}
			}
		}
		else if(ui instanceof Client)
		{
			Client c = ((Client) ui);
			
			if(a.equalsIgnoreCase(c.getClass().getSimpleName()))
			{
				d.add(new Client());
			}
			else if(a == c.getChoose().getActionCommand())
			{
				JFileChooser f = new JFileChooser();
				
				if(f.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					c.getImg().loadImage(f.getSelectedFile().getPath(), 120, 120);
				}
			}
			else if(a == c.getSubmit().getActionCommand())
			{
				PeopleDAO pd = new PeopleDAO();
				
				String cpf = c.getCpf().getText();
				String firstName = c.getFirstName().getText();
				String lastName = c.getLastName().getText();
				String phoneNumber = c.getPhoneNumber().getText();
				String email = c.getEmail().getText();
				String date = c.getDate().getText();
				Gender gender = Gender.valueOf(c.getGender().getSelectedItem().toString());
				String zipCode = c.getZipCode().getText();
				String img = c.getImg().getRoot();
				
				if(!(cpf.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || date.isEmpty() || gender == null || zipCode.isEmpty() || img.isEmpty()))
				{
					pd.add(new PeopleVO(cpf, firstName, lastName, phoneNumber, email, date, gender, zipCode, img));
					
					JOptionPane.showMessageDialog(null, "Client created succefully.");
					
					c.getUI().dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Fields is empty, please fill.");
				}
				
			}
			else if(a.length() == 8)
			{
				try 
				{
					CepAPI cep = new CepAPI(e.getActionCommand());
					
					c.getNeighborhood().setText(cep.getBairro());
					c.getAddress().setText(cep.getLogradouro());
					c.getCity().setText(cep.getLocalidade());
					c.getState().setText(cep.getUf());
				} 
				catch (ParserConfigurationException | SAXException | IOException e1) 
				{
					e1.printStackTrace();
				}
			}
		}
		else if(ui instanceof Company)
		{
			Company co = ((Company) ui);
			PeopleDAO pd = new PeopleDAO();
			
			if(a.equalsIgnoreCase(co.getClass().getSimpleName()))
			{
				d.add(new Company());				
			}
			else if(a.length() == 11)
			{
				PeopleVO p;
				
				try 
				{
					p = pd.search(Type.CPF, co.getOwner().getText());
					
					co.getImage().loadImage(p.getByte());
				} 
				catch(TypeException ex) 
				{
					ex.printStackTrace();
				}
				
			}
			else if(a == co.getSubmit().getActionCommand())
			{
				CompanyDAO cod = new CompanyDAO();
				
				String cnpj = co.getCnpj().getText();
				String name = co.getName().getText();
				String owner = co.getOwner().getText();
				String zipCode = co.getZipCode().getText();
				
				if(!(cnpj.isEmpty() || name.isEmpty() || owner.isEmpty() || zipCode.isEmpty()))
				{
					cod.add(new CompanyVO(cnpj, name, owner, zipCode));
					
					JOptionPane.showMessageDialog(null, "Company created succefully.");
					
					co.getUI().dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Fields is empty, please fill.");
				}
			}
		}
		else if(ui instanceof Ticket)
		{
			d.add(new Ticket());
		}
		else if(ui instanceof User)
		{
			User u = ((User) ui);
			
			if(a.equalsIgnoreCase(u.getClass().getSimpleName()))
			{
				d.add(new User());
			}
			else if(a == u.getSubmit().getActionCommand())
			{
				UserDAO ud = new UserDAO();
				
				String cpf = u.getCpf().getText();
				String username = u.getUser().getText();
				String passwd = u.getPasswd().getText();
				Department department = Department.valueOf(u.getDepartment().getSelectedItem().toString());
				
				if(!(cpf.isEmpty() || username.isEmpty() || passwd.isEmpty()))
				{
					ud.add(new UserVO(cpf, username, passwd, department));
					
					JOptionPane.showMessageDialog(null, "User created succefully.");
					
					u.getUI().dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Fields is empty, please fill.");
				}
			}
		}
		else if(ui instanceof Product)
		{
			d.add(new Product());
		}
		else if(ui instanceof Account)
		{
			Account acc = ((Account) ui);
			
			if(a.equalsIgnoreCase(acc.getClass().getSimpleName()))
			{
				d.add(new Account());
			}
		}
	}
}
