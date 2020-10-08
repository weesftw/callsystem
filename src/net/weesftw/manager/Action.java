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
import net.weesftw.constraint.Status;
import net.weesftw.dao.CompanyDAO;
import net.weesftw.dao.PeopleDAO;
import net.weesftw.dao.ProductDAO;
import net.weesftw.dao.ProviderDAO;
import net.weesftw.dao.TicketDAO;
import net.weesftw.dao.UserDAO;
import net.weesftw.model.DesktopPane;
import net.weesftw.view.Account;
import net.weesftw.view.Client;
import net.weesftw.view.Login;
import net.weesftw.view.Main;
import net.weesftw.view.Product;
import net.weesftw.view.Provider;
import net.weesftw.view.Ticket;
import net.weesftw.view.TicketOpen;
import net.weesftw.view.TicketTable;
import net.weesftw.view.UI;
import net.weesftw.view.User;
import net.weesftw.vo.PeopleVO;
import net.weesftw.vo.ProductVO;
import net.weesftw.vo.ProviderVO;
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
				UserVO us = ud.searchByUser(user);
				
				if(us != null)
				{
					if(ud.isValid(user, passwd))
					{
						PeopleDAO pd = new PeopleDAO();
						PeopleVO p = pd.searchByUser(user);
						
						c.dispose();
						
						new Main(p, us);
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
					if(cpf.matches(Regex.CPF) && (firstName.matches(Regex.NAME) && lastName.matches(Regex.NAME)) && date.matches(Regex.DATE) && email.matches(Regex.EMAIL) && zipCode.matches(Regex.CEP))
					{
						pd.create(new PeopleVO(cpf, firstName, lastName, phoneNumber, email, date, gender, zipCode, img));
						
						JOptionPane.showMessageDialog(null, "Register created successfully.");
						
						c.getUI().dispose();						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Some field contains invalid information.");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "There are empty fields, please fill in.");
				}
			}
			else if(action.equals(c.getZipCode().getText()))
			{				
				String zipCode = c.getZipCode().getText();
				
				if(zipCode.length() == 8 && zipCode.matches(Regex.CEP))
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
			
			if(action.equals(t.getPj().getActionCommand()))
			{
				t.getCompany().setEditable(t.getPj().isSelected() ? true : false);
			}
			else if(action.equals(t.getSubmit().getActionCommand()))
			{
				TicketDAO td = new TicketDAO();
				PeopleDAO pd = new PeopleDAO();
				CompanyDAO cd = new CompanyDAO();
				ProductDAO pdd = new ProductDAO();
				
				Category category = Category.valueOf(t.getCategory().getSelectedItem().toString());
				ProductVO product = pdd.searchByName(t.getProduct().getSelectedItem().toString());
				boolean priority = t.getPriority().isSelected() ? true : false;
				String client = t.getClient().getText();
				String title = t.getTitle().getText();
				String company = t.getCompany().getText();
				String description = t.getDescription().getText();
				String user = m.getAuth().getPeople().getCpf();
				
				if(category != null && product != null && !title.isEmpty() && !description.isEmpty() && !user.isEmpty())
				{
					if(t.getPj().isSelected())
					{
						if(company.matches(Regex.CNPJ) && client.matches(Regex.CPF))
						{
							if(pd.read(client) != null && cd.read(company) != null)
							{
								td.create(new TicketVO(title, client, company, user, description, category, product, priority));
								
								JOptionPane.showMessageDialog(null, "Ticket created successfully.");
								
								t.getUI().dispose();							
							}
							else
							{
								JOptionPane.showMessageDialog(null, "There is no record of this company.");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Some field contains invalid information.");
						}
					}
					else
					{
						if(client.matches(Regex.CPF))
						{							
							if(pd.read(client) != null)
							{
								td.create(new TicketVO(title, client, company, user, description, category, product, priority));
								
								JOptionPane.showMessageDialog(null, "Ticket created successfully.");
								
								t.getUI().dispose();							
							}
							else
							{
								JOptionPane.showMessageDialog(null, "There is no record of this client.");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Some field contains invalid information.");
						}
					}
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
			else if(action.equals(u.getSubmit().getActionCommand()))
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
					if(cpf.matches(Regex.CPF) && (firstName.matches(Regex.NAME) && lastName.matches(Regex.NAME)) && date.matches(Regex.DATE) && email.matches(Regex.EMAIL) && zipCode.matches(Regex.CEP))
					{
						if(ud.searchByUser(username) != null)
						{
							pd.create(new PeopleVO(cpf, firstName, lastName, phoneNumber, email, date, gender, zipCode, img));
							ud.create(new UserVO(cpf, username, passwd, department));
							
							JOptionPane.showMessageDialog(null, "Register created succefully.");
							
							u.getUI().dispose();							
						}
						else
						{
							JOptionPane.showMessageDialog(null, "There is already someone with that username.");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Some field contains invalid information.");
					}
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
		else if(ui instanceof Account)
		{			
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
				ProductDAO pd = new ProductDAO();
				
				int id = Integer.valueOf(t.getId().getText());
				Status status = Status.valueOf(t.getStatus().getSelectedItem().toString());
				ProductVO product = pd.searchByName(t.getProduct().getText());
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
		else if(ui instanceof Product)
		{
			Product p = ((Product) ui);
			
			if(action.equals(p.getChoose().getActionCommand()))
			{
				JFileChooser f = new JFileChooser();
				
				if(f.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					p.getImg().loadImage(f.getSelectedFile().getPath(), 120, 120);
				}
				
				return;
			}
			else if(action.equals(p.getSubmit().getActionCommand()))
			{
				ProviderDAO pd = new ProviderDAO();
				ProductDAO pdd = new ProductDAO();
				
				String name = p.getName().getText();
				String price = p.getPrice().getText();
				ProviderVO provider = pd.searchByName(p.getProvider().getSelectedItem().toString());
				String path = p.getImg().getUrl();
				
				if(!name.isEmpty() && !price.isEmpty() && provider != null && !path.isEmpty())
				{
					if(price.matches(Regex.PRICE))
					{						
						pdd.create(new ProductVO(provider, name, price, path));		
						
						JOptionPane.showMessageDialog(null, "Register created succefully.");
						
						p.getUI().dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Some field contains invalid information.");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "There are empty fields, please fill in.");
				}
			}
			else
			{
				d.add(new Product());
			}
		}
		else if(ui instanceof Provider)
		{
			Provider p = ((Provider) ui);
			
			if(action.equals(p.getSubmit().getActionCommand()))
			{
				ProviderDAO pd = new ProviderDAO();
				
				String name = p.getName().getText();
				String freight = p.getFreight().getText();
				String zipCode = p.getZipCode().getText();
				String phoneNumber = p.getPhoneNumber().getText();
				
				if(!name.isEmpty() && !freight.isEmpty() && !zipCode.isEmpty() && !phoneNumber.isEmpty())
				{
					if(freight.matches(Regex.PRICE))
					{
						pd.create(new ProviderVO(name, zipCode, phoneNumber, freight));
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Some field contains invalid information.");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "There are empty fields, please fill in.");
				}
			}
			else
			{
				d.add(new Provider());
			}
		}
	}
}
