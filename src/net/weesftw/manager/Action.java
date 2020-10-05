package net.weesftw.manager;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
import net.weesftw.constraint.Type;
import net.weesftw.dao.CompanyDAO;
import net.weesftw.dao.PeopleDAO;
import net.weesftw.dao.TicketDAO;
import net.weesftw.dao.UserDAO;
import net.weesftw.exception.TypeException;
import net.weesftw.model.DesktopPane;
import net.weesftw.view.Account;
import net.weesftw.view.Client;
import net.weesftw.view.Company;
import net.weesftw.view.Login;
import net.weesftw.view.Main;
import net.weesftw.view.Ticket;
import net.weesftw.view.TicketOpen;
import net.weesftw.view.TicketTable;
import net.weesftw.view.UI;
import net.weesftw.view.User;
import net.weesftw.vo.CompanyVO;
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
			
			if(action == c.getChoose().getActionCommand())
			{
				JFileChooser f = new JFileChooser();
				
				if(f.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					c.getImg().loadImage(f.getSelectedFile().getPath(), 120, 120);
				}
			}
			else if(action == c.getSubmit().getActionCommand())
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
			else if(action.length() == 8)
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
			else
			{
				d.add(new Client());
			}
		}
		else if(ui instanceof Company)
		{
			Company co = ((Company) ui);
			PeopleDAO pd = new PeopleDAO();
			
			if(action.length() == 11)
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
			else if(action == co.getSubmit().getActionCommand())
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
			else
			{
				d.add(new Company());
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
				
				if(!(category == null || product == null) || client.isEmpty() || title.isEmpty() || company.isEmpty() || description.isEmpty() || user.isEmpty())
				{
					td.add(new TicketVO(product, priority, category, title, description, company, client, user, null));
					
					JOptionPane.showMessageDialog(null, "Ticket created succefully.");
					
					t.getUI().dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Fields is empty, please fill.");
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
			
			if(action == u.getSubmit().getActionCommand())
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
			
			if(action == t.getBtn().getActionCommand())
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
			
			if(action == t.getSubmit().getActionCommand())
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
				
				if(!(solution.isEmpty()))
				{
					td.update(new TicketVO(id, status, product, priority, category, title, description, company, client, user, solution));
					
					JOptionPane.showMessageDialog(null, "Ticket updated.");
					
					t.getUI().dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Solution is empty.");
				}
			}
		}
	}
}
