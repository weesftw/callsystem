package net.weesftw.manager;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Timestamp;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import net.weesftw.constraint.Category;
import net.weesftw.constraint.Department;
import net.weesftw.constraint.Gender;
import net.weesftw.constraint.Product;
import net.weesftw.constraint.Status;
import net.weesftw.dao.PeopleDAO;
import net.weesftw.dao.TicketDAO;
import net.weesftw.dao.UserDAO;
import net.weesftw.model.DesktopPane;
import net.weesftw.view.Account;
import net.weesftw.view.Client;
import net.weesftw.view.Login;
import net.weesftw.view.Main;
import net.weesftw.view.Ticket;
import net.weesftw.view.TicketOpen;
import net.weesftw.view.TicketTable;
import net.weesftw.view.UI;
import net.weesftw.view.User;
import net.weesftw.vo.PeopleVO;
import net.weesftw.vo.TicketVO;
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
		String action = e.getActionCommand();
		DesktopPane d = null;
		Main m = Main.instance;		
		
		if(m != null)
		{
			d = m.getDesktop();
		}
		
		if(ui instanceof Login)
		{
			Login l = ((Login) ui);
			UserDAO ud = new UserDAO();
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
						PeopleDAO pd = new PeopleDAO();
						PeopleVO p = pd.searchByUser(user);
						
						c.dispose();
						
						new Main(p);
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
			
			if(action.equals(c.getChoose().getActionCommand()))
			{
				JFileChooser f = new JFileChooser();
				
				if(f.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					c.getImg().loadImage(f.getSelectedFile().getPath(), 120, 120);
				}
				
				return;
			}
			else if(action.equals(c.getSubmit().getActionCommand()))
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
				String img = c.getImg().getUrl();
				
				if(!(cpf.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || date.isEmpty() || gender == null || zipCode.isEmpty() || img.isEmpty()))
				{
					pd.create(new PeopleVO(cpf, firstName, lastName, phoneNumber, email, date, gender, zipCode, img));
					
					JOptionPane.showMessageDialog(null, "Register created successfully.");
					
					c.getUI().dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "There are empty fields, please fill in.");
				}
			}
			else if(action.equals(c.getSearch().getActionCommand()))
			{				
				if(c.getZipCode().getText().length() == 8)
				{
					try
					{
						CepAPI cep = new CepAPI(e.getActionCommand());
						
						c.getNeighborhood().setText(cep.getBairro());
						c.getAddress().setText(cep.getLogradouro());
						c.getCity().setText(cep.getLocalidade());
						c.getState().setText(cep.getUf());
					} 
					catch (ParserConfigurationException | SAXException | IOException ex) 
					{
						ex.printStackTrace();
					}	
				}
				else
				{					
					JOptionPane.showMessageDialog(null, "Zip Code Invalid.");
				}
			}
			else
			{
				d.add(new Client());					
			}
		}
		else if(ui instanceof Ticket)
		{
			Ticket t = ((Ticket) ui);
			
			if(action == t.getSubmit().getActionCommand())
			{
				TicketDAO td = new TicketDAO();
				
				Category category = Category.valueOf(t.getCategory().getSelectedItem().toString());
				Product product = Product.valueOf(t.getProduct().getSelectedItem().toString());
				boolean priority = t.getPriority().isSelected() ? true : false;
				String client = t.getClient().getText();
				String title = t.getTitle().getText();
				String company = t.getCompany().getText();
				String description = t.getDescription().getText();
				String user = m.getAuth().getPeople().getCpf();
				
				if(category != null && product != null && !client.isEmpty() && !title.isEmpty() && !company.isEmpty() && !description.isEmpty() && !user.isEmpty())
				{
					td.create(new TicketVO(title, client, company, user, description, category, product, priority));
					
					JOptionPane.showMessageDialog(null, "Ticket created successfully.");
					
					t.getUI().dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "There are empty fields, please fill in.");
				}
			}
			else
			{				
				d.add(new Ticket());
			}
		}
		else if(ui instanceof User)
		{
			User u = ((User) ui);
			
			if(action.equals(u.getChoose().getActionCommand()))
			{
				JFileChooser f = new JFileChooser();
				
				if(f.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					u.getImg().loadImage(f.getSelectedFile().getPath(), 120, 120);
				}
				
				return;
			}
			if(action.equals(u.getSubmit().getActionCommand()))
			{
				UserDAO ud = new UserDAO();
				PeopleDAO pd = new PeopleDAO();
				
				String cpf = u.getCpf().getText();
				String firstName = u.getFirstName().getText();
				String lastName = u.getLastName().getText();
				String phoneNumber = u.getPhoneNumber().getText();
				String email = u.getEmail().getText();
				String date = u.getDate().getText();
				Gender gender = Gender.valueOf(u.getGender().getSelectedItem().toString());
				String zipCode = u.getZipCode().getText();
				String img = u.getImg().getUrl();
				String username = u.getUser().getText();
				String passwd = u.getPasswd().getText();
				Department department = Department.valueOf(u.getDepartment().getSelectedItem().toString());
				
				if(!(cpf.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || date.isEmpty() || gender == null || zipCode.isEmpty() || img.isEmpty() && !cpf.isEmpty() && !username.isEmpty() && !passwd.isEmpty() && department != null))
				{
					pd.create(new PeopleVO(cpf, firstName, lastName, phoneNumber, email, date, gender, zipCode, img));
					ud.create(new UserVO(cpf, username, passwd, department));
					
					JOptionPane.showMessageDialog(null, "Register created succefully.");
					
					u.getUI().dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "There are empty fields, please fill in.");
				}
			}
			else
			{
				d.add(new User());
			}
		}
//		else if(ui instanceof Product)
//		{
//			d.add(new Product());
//		}
		else if(ui instanceof Account)
		{
//			Account acc = ((Account) ui);
			
			d.add(new Account());
		}
		else if(ui instanceof TicketTable)
		{
			TicketTable t = ((TicketTable) ui);
			
			if(action.equals(t.getBtn().getActionCommand()))
			{				
				String id = t.getId().getText();
				String title = t.getTitle().getText();
				String client = t.getClient().getText();
				String company = t.getCompany().getText();
				String user = t.getUser().getText();
				String date = t.getDate().getText();
				String priority = t.getPriority().isSelected() ? "✓" : "";
				String status = t.getStatus().getSelectedItem().toString();
				
				if(!id.isEmpty())
				{					
					t.getSorter().setRowFilter(RowFilter.numberFilter(ComparisonType.EQUAL, Integer.valueOf(id), 0));
				}
				else if(!title.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + title, 1));
				}
				else if(!client.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + client, 2));
				}
				else if(!company.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + company, 3));
				}
				else if(!user.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + user, 4));
				}
				else if(!date.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + date, 5));
				}
				else if(!priority.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + priority, 6));
				}
				else if(!status.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + status, 7));
				}
				else
				{
					t.getSorter().setRowFilter(null);
				}
			}
			else
			{
				d.add(new TicketTable());			
			}
		}
		else if(ui instanceof TicketOpen)
		{
			TicketOpen t = ((TicketOpen) ui);
			
			if(action.equals(t.getSubmit().getActionCommand()))
			{
				TicketDAO td = new TicketDAO();
				
				int id = Integer.valueOf(t.getId().getText());
				Status status = Status.valueOf(t.getStatus().getSelectedItem().toString());
				Product product = Product.valueOf(t.getProduct().getText());
				boolean priority = t.getPriority().isSelected();
				Category category = Category.valueOf(t.getCategory().getText());
				String title = t.getTitle().getText();
				String description = t.getDescription().getText();
				String company = t.getCompany().getText();
				String client = t.getClient().getText();
				String user = t.getUser().getText();
				String solution = t.getSolution().getText();
				Timestamp time = new Timestamp(System.currentTimeMillis());
				
				if(!(solution.isEmpty()))
				{
					td.update(new TicketVO(id, title, client, company, user, description, solution, time, category, product, status, priority));
					
					JOptionPane.showMessageDialog(null, "Ticket updated.");
					
					t.getUI().dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "The solution's empty.");
				}
			}
		}
	}
}
