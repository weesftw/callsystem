package net.weesftw.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import net.weesftw.Control;
import net.weesftw.constraint.Category;
import net.weesftw.constraint.Department;
import net.weesftw.constraint.Gender;
import net.weesftw.constraint.Message;
import net.weesftw.constraint.Regex;
import net.weesftw.constraint.Status;
import net.weesftw.dao.CartDAO;
import net.weesftw.dao.ClientDAO;
import net.weesftw.dao.CompanyDAO;
import net.weesftw.dao.ProductDAO;
import net.weesftw.dao.ProviderDAO;
import net.weesftw.dao.SellDAO;
import net.weesftw.dao.TicketDAO;
import net.weesftw.dao.UserDAO;
import net.weesftw.exception.CepNotFoundException;
import net.weesftw.model.DesktopPane;
import net.weesftw.view.Account;
import net.weesftw.view.Client;
import net.weesftw.view.ClientOpen;
import net.weesftw.view.ClientTable;
import net.weesftw.view.Company;
import net.weesftw.view.CompanyOpen;
import net.weesftw.view.CompanyTable;
import net.weesftw.view.Login;
import net.weesftw.view.Main;
import net.weesftw.view.Menu;
import net.weesftw.view.Product;
import net.weesftw.view.ProductOpen;
import net.weesftw.view.ProductTable;
import net.weesftw.view.Provider;
import net.weesftw.view.ProviderOpen;
import net.weesftw.view.ProviderTable;
import net.weesftw.view.Sale;
import net.weesftw.view.SaleOpen;
import net.weesftw.view.SaleTable;
import net.weesftw.view.Ticket;
import net.weesftw.view.TicketOpen;
import net.weesftw.view.TicketTable;
import net.weesftw.view.UI;
import net.weesftw.view.User;
import net.weesftw.view.UserTable;
import net.weesftw.vo.CartVO;
import net.weesftw.vo.ClientVO;
import net.weesftw.vo.CompanyVO;
import net.weesftw.vo.ProductVO;
import net.weesftw.vo.ProviderVO;
import net.weesftw.vo.SellVO;
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
		Main m = Main.getInstance();
		String action = e.getActionCommand();
		DesktopPane d = m != null ? m.getDesktop() : null;
		
		if(ui instanceof Login)
		{
			Login l = ((Login) ui);
			UserDAO ud = new UserDAO();
			
			Control.host = l.getHost().getText();
			Control.root = l.getRoot().getText();
			Control.pass = l.getPass().getText();
			
			String user = l.getUser().getText();
			String passwd = l.getPasswd().getText();
			
			if((!user.isEmpty() && !passwd.isEmpty()))
			{
				UserVO us = ud.searchByUser(user);
				
				if(us != null)
				{					
					if(ud.isValid(user, passwd))
					{
						ClientDAO pd = new ClientDAO();
						ClientVO p = pd.searchByUser(user);
						
						Reload.populate();
						
						new Main(p, us);
						
						l.getUI().dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, Message.AUTHENTICATED_FAILED.get(null));
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, Message.USERNAME_NOT_FOUND.get(null));
				}
			}
		}
		else if(ui instanceof Client)
		{
			Client c = ((Client) ui);
			
			String cpf = c.getCpf().getText();
			String firstName = c.getFirstName().getText();
			String lastName = c.getLastName().getText();
			String phoneNumber = c.getPhoneNumber().getText();
			String email = c.getEmail().getText();
			String date = c.getDate().getText();
			Gender gender = Gender.valueOf(c.getGender().getSelectedItem().toString());
			String zipCode = c.getZipCode().getText();
			
			if(action.equals(c.getChoose().getActionCommand()))
			{
				JFileChooser f = new JFileChooser();
				
				if(f.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					c.getImg().loadImage(f.getSelectedFile().getPath(), 120, 120);
				}
			}
			else if(action.equals(c.getSubmit().getActionCommand()))
			{		
				String img = c.getImg().getUrl();
				
				if(img != null)
				{
					ClientDAO pd = new ClientDAO();
					ClientVO cl = pd.read(cpf);
					
					if(cl == null)
					{
						if(phoneNumber.matches(Regex.PHONE) && cpf.matches(Regex.CPF) && firstName.matches(Regex.NAME) && lastName.matches(Regex.NAME) && date.matches(Regex.DATE) && email.matches(Regex.EMAIL) && zipCode.matches(Regex.CEP))
						{
							ClientVO cv = new ClientVO(cpf, firstName, lastName, phoneNumber, email, date, gender, zipCode, img);
							
							pd.create(cv);
							
							JOptionPane.showMessageDialog(null, Message.SUCCESSFULLY.get(firstName));							
							
							c.clear();
							c.getUI().dispose();
							
							Reload.refresh();							
						}
						else
						{
							JOptionPane.showMessageDialog(null, Message.INVALID_ARGUMENTS.get(null));
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, Message.EXISTS.get(firstName));
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, Message.IMAGE_INVALID.get(null));
				}
			}
			else if(action.equals(c.getZipCode().getText()))
			{				
				if(zipCode.matches(Regex.CEP))
				{
					try
					{
						CepAPI cep = new CepAPI(zipCode);
						
						c.getNeighborhood().setText(cep.getBairro());
						c.getAddress().setText(cep.getLogradouro());
						c.getCity().setText(cep.getLocalidade());
						c.getState().setText(cep.getUf());
					}
					catch(CepNotFoundException ex)
					{
						JOptionPane.showMessageDialog(null, Message.NOT_EXISTS.get(zipCode));
					}
					catch (ParserConfigurationException | SAXException | IOException ex) 
					{
						ex.printStackTrace();
					}
				}
				else
				{					
					JOptionPane.showMessageDialog(null, Message.NOT_EXISTS.get(zipCode));
				}
			}
			else
			{
				if(!c.getUI().isVisible())
				{
					d.add(c);
					c.getUI().setVisible(true);
				}
				else
				{
					c.getUI().moveToFront();
				}
			}
		}
		else if(ui instanceof Company)
		{
			Company c = ((Company) ui);
			
			String cnpj = c.getCnpj().getText();
			String name = c.getName().getText();
			String owner = c.getOwner().getText();
			String zipCode = c.getZipCode().getText();
			
			if(action.equals(c.getSubmit().getActionCommand()))
			{
				if(!(name.isEmpty() && zipCode.isEmpty()))
				{
					if(cnpj.matches(Regex.CNPJ) && zipCode.matches(Regex.CEP))
					{
						try 
						{
							CepAPI cep = new CepAPI(zipCode);
							CompanyDAO cd = new CompanyDAO();
							ClientDAO cdd = new ClientDAO();
							ClientVO cv = cdd.read(owner);
							
							if(cv != null)
							{
								if(cep != null)
								{
									CompanyVO cvv = new CompanyVO(cnpj, name, cv, zipCode);
									
									cd.create(cvv);
									CompanyVO.list.add(cvv);
									
									JOptionPane.showMessageDialog(null, Message.SUCCESSFULLY.get(name));
									
									c.clear();
									c.getUI().dispose();	
									
									Reload.refresh();
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, Message.NOT_EXISTS.get(owner));
							}
						}
						catch(CepNotFoundException ex)
						{
							JOptionPane.showMessageDialog(null, Message.NOT_EXISTS.get(zipCode));
						}
						catch (ParserConfigurationException | SAXException | IOException e1) 
						{
							e1.printStackTrace();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, Message.INVALID_ARGUMENTS.get(null));
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, Message.FIELDS_EMPTY.get(null));
				}
			}
			else
			{
				if(!c.getUI().isVisible())
				{
					d.add(c);
					c.getUI().setVisible(true);
				}
				else
				{
					c.getUI().moveToFront();
				}
			}
		}
		else if(ui instanceof Ticket)
		{
			Ticket t = ((Ticket) ui);
			
			boolean priority = t.getPriority().isSelected() ? true : false;
			String category = t.getCategory().getSelectedItem().toString();
			String product = t.getProduct().getSelectedItem().toString();
			String client = t.getClient().getText();
			String title = t.getTitle().getText();
			String company = t.getCompany().getText();
			String description = t.getDescription().getText();
			
			if(action.equals(t.getPj().getActionCommand()))
			{
				t.getCompany().setEditable(t.getPj().isSelected() ? true : false);
				t.getClient().setEditable(!t.getCompany().isEditable());
				t.getCompany().setText("");
				t.getClient().setText("");				
			}
			else if(action.equals(t.getSubmit().getActionCommand()))
			{				
				if(!title.isEmpty() && !description.isEmpty())
				{
					TicketDAO td = new TicketDAO();
					ClientDAO pd = new ClientDAO();
					CompanyDAO cd = new CompanyDAO();
					ProductDAO pdd = new ProductDAO();
					
					CompanyVO cv = cd.read(company);
					ProductVO pv = pdd.searchByName(product);
					ClientVO cvv = pd.read(client);
					
					if(t.getPj().isSelected())
					{
						if(cv != null)
						{
							if(pv != null)
							{
								if(cvv != null)
								{
									TicketVO tv = new TicketVO(title, cv, description, Category.valueOf(category), pv, priority);
									
									td.create(tv);
									TicketVO.list.add(tv);
									
									JOptionPane.showMessageDialog(null, Message.SUCCESSFULLY.get("Ticket"));
									
									t.clear();
									t.getUI().dispose();
									
									Reload.refresh();
								}
								else
								{
									JOptionPane.showMessageDialog(null, Message.NOT_FOUND.get(client));
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, Message.NOT_FOUND.get(product));
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, Message.NOT_FOUND.get(company));
						}
					}
					else
					{
						if(pv != null)
						{
							if(cvv != null)
							{
								TicketVO tv = new TicketVO(title, cvv, description, Category.valueOf(category), pv, priority);
								
								td.create(tv);
								TicketVO.list.add(tv);
								
								JOptionPane.showMessageDialog(null, Message.SUCCESSFULLY.get("Ticket"));
								
								t.clear();
								t.getUI().dispose();
								
								Reload.refresh();
							}
							else
							{
								JOptionPane.showMessageDialog(null, Message.NOT_FOUND.get(client));
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, Message.NOT_FOUND.get(product));
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, Message.FIELDS_EMPTY.get(null));
				}
			}
			else
			{				
				if(!t.getUI().isVisible())
				{
					d.add(t);
					t.getUI().setVisible(true);
				}
				else
				{
					t.getUI().moveToFront();
				}
			}
		}
		else if(ui instanceof User)
		{
			User u = ((User) ui);
			
			String cpf = u.getCpf().getText();
			String firstName = u.getFirstName().getText();
			String lastName = u.getLastName().getText();
			String phoneNumber = u.getPhoneNumber().getText();
			String email = u.getEmail().getText();
			String date = u.getDate().getText();
			String zipCode = u.getZipCode().getText();
			String img = u.getImg().getUrl();
			String username = u.getUser().getText();
			String passwd = u.getPasswd().getText();
			Gender gender = Gender.valueOf(u.getGender().getSelectedItem().toString());
			Department department = Department.valueOf(u.getDepartment().getSelectedItem().toString());
			
			if(action.equals(u.getChoose().getActionCommand()))
			{
				JFileChooser f = new JFileChooser();
				
				if(f.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					u.getImg().loadImage(f.getSelectedFile().getPath(), 120, 120);
				}
			}
			else if(action.equals(u.getCpf().getText()))
			{
				ClientDAO pd = new ClientDAO();
				ClientVO cl = pd.read(cpf);
				
				if(cpf != null)
				{
					u.getFirstName().setText(cl.getFirstName());
					u.getLastName().setText(cl.getLastName());
					u.getPhoneNumber().setText(cl.getPhoneNumber());
					u.getEmail().setText(cl.getEmail());
					u.getDate().setText(cl.getDate());
					u.getImg().loadImage(cl.getByte(), 120, 120);
				}
			}
			else if(action.equals(u.getSubmit().getActionCommand()))
			{
				if(img != null)
				{
					if(!(phoneNumber.isEmpty() && username.isEmpty() && passwd.isEmpty()))
					{
						if(cpf.matches(Regex.CPF) && (firstName.matches(Regex.NAME) && lastName.matches(Regex.NAME)) && date.matches(Regex.DATE) && email.matches(Regex.EMAIL) && zipCode.matches(Regex.CEP))
						{
							UserDAO ud = new UserDAO();
							ClientDAO pd = new ClientDAO();			
							UserVO uv = ud.searchByUser(username);
							ClientVO cv = pd.read(cpf);
							
							if(uv == null)
							{
								if(cv != null)
								{
									UserVO uvv = new UserVO(cpf, username, passwd, department);
									
									ud.create(uvv);
									UserVO.list.add(uvv);
									
									JOptionPane.showMessageDialog(null, Message.SUCCESSFULLY.get("User"));
									
									u.clear();
									u.getUI().dispose();
									
									Reload.refresh();
								}
								else
								{
									pd.create(new ClientVO(cpf, firstName, lastName, phoneNumber, email, date, gender, zipCode, img));
									ud.create(new UserVO(cpf, username, passwd, department));
									
									JOptionPane.showMessageDialog(null, Message.SUCCESSFULLY.get("User"));
									
									u.getUI().dispose();	
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, Message.EXISTS.get(username));
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, Message.INVALID_ARGUMENTS.get(null));
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, Message.FIELDS_EMPTY.get(null));
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, Message.IMAGE_INVALID.get(null));
				}
			}
			else if(action.equals(u.getZipCode().getText()))
			{				
				if(zipCode.matches(Regex.CEP))
				{
					try
					{
						CepAPI cep = new CepAPI(zipCode);
						
						u.getNeighborhood().setText(cep.getBairro());
						u.getAddress().setText(cep.getLogradouro());
						u.getCity().setText(cep.getLocalidade());
						u.getState().setText(cep.getUf());							
					} 
					catch(CepNotFoundException ex)
					{
						JOptionPane.showMessageDialog(null, Message.NOT_EXISTS.get(zipCode));
					}
					catch (ParserConfigurationException | SAXException | IOException ex1) 
					{
						ex1.printStackTrace();
					}	
				}
				else
				{					
					JOptionPane.showMessageDialog(null, Message.INVALID_ARGUMENTS.get(null));
				}
			}
			else
			{
				if(!u.getUI().isVisible())
				{
					d.add(u);
					u.getUI().setVisible(true);
				}
				else
				{
					u.getUI().moveToFront();
				}
			}
		}
		else if(ui instanceof Menu)
		{
			Menu me = ((Menu) ui);
			
			if(action.equals(me.getUser().getActionCommand()))
			{
				String args = JOptionPane.showInputDialog("CPF: ");
				
				if(args != null)
				{
					if(args.matches(Regex.CPF))
					{
						UserDAO ud = new UserDAO();
						UserVO c = ud.read(args);
						
						if(c == null)
						{
							User u = User.getInstance();
							
							if(!u.getUI().isVisible())
							{
								d.add(u);
								u.getCpf().setText(args);
								u.getUI().setVisible(true);
							}
							else
							{
								u.getUI().moveToFront();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, Message.EXISTS.get(args));
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, Message.INVALID.get(args));						
					}
				}
			}
			else if(action.equals(me.getClient().getActionCommand()))
			{
				String args = JOptionPane.showInputDialog("CPF: ");
				
				if(args != null)
				{
					if(args.matches(Regex.CPF))
					{
						ClientDAO ud = new ClientDAO();
						ClientVO c = ud.read(args);
						
						if(c == null)
						{
							Client u = Client.getInstance();
							
							if(!u.getUI().isVisible())
							{
								d.add(u);
								u.getCpf().setText(args);
								u.getUI().setVisible(true);
							}
							else
							{
								u.getUI().moveToFront();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, Message.EXISTS.get(args));
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, Message.INVALID.get(args));						
					}
				}
			}
			else if(action.equals(me.getCompany().getActionCommand()))
			{
				String args = JOptionPane.showInputDialog("CNPJ: ");
				
				if(args != null)
				{
					if(args.matches(Regex.CNPJ))
					{
						CompanyDAO ud = new CompanyDAO();
						CompanyVO c = ud.read(args);
						
						if(c == null)
						{
							Company u = Company.getInstance();
							
							if(!u.getUI().isVisible())
							{
								d.add(u);
								u.getCnpj().setText(args);
								u.getUI().setVisible(true);
							}
							else
							{
								u.getUI().moveToFront();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, Message.EXISTS.get(args));
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, Message.INVALID.get(args));						
					}
				}
			}
			else if(action.equals(me.getLogout().getActionCommand()))
			{
				Main main = Main.getInstance();
				
				main.getUI().dispose();
				
				new Login();
			}
		}
		else if(ui instanceof Account)
		{			
			Account i = Account.getInstance();
			
			if(!i.getUI().isVisible())
			{
				d.add(i);
				i.getUI().setVisible(true);
			}
			else
			{
				i.getUI().moveToFront();
			}
		}
		else if(ui instanceof TicketTable)
		{
			TicketTable t = ((TicketTable) ui);
			
			String id = t.getId().getText();
			String title = t.getTitle().getText();
			String client = t.getClient().getText();
			String company = t.getCompany().getText();
			String user = t.getUser().getText();
			String date = t.getDate().getText();
			String priority = t.getPriority().isSelected() ? "*" : "";
			String status = t.getStatus().getSelectedItem().toString();
			
			if(action.equals(t.getBtn().getActionCommand()))
			{				
				if(!id.isEmpty())
				{					
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + id, 0));
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
				if(!t.getUI().isVisible())
				{
					d.add(t);
					t.getUI().setVisible(true);
				}
				else
				{
					t.getUI().moveToFront();
				}		
			}
		}
		else if(ui instanceof TicketOpen)
		{
			TicketOpen t = ((TicketOpen) ui);
			
			TicketVO tv = t.getT();			
			
			Status status = Status.valueOf(t.getStatus().getSelectedItem().toString());
			Category category = Category.valueOf(t.getCategory().getText());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			String description = t.getDescription().getText();
			boolean priority = t.getPriority().isSelected();
			String solution = t.getSolution().getText();
			ProductVO product = tv.getProduct();
			CompanyVO company = tv.getCompany();
			ClientVO client = tv.getClient();
			String title = tv.getTitle();
			UserVO user = tv.getUser();
			String id = tv.getId();
			
			if(action.equals(t.getSubmit().getActionCommand()))
			{
				TicketDAO td = new TicketDAO();
				
				if(!solution.isEmpty())
				{
					td.update(new TicketVO(id, title, client, company, user, description, solution, time, category, product, status, priority));
					
					JOptionPane.showMessageDialog(null, Message.TICKET_UPDATED.get(t.getTitle().getText()));
					
					t.getUI().dispose();
					
					Reload.refresh();
				}
				else
				{
					JOptionPane.showMessageDialog(null, Message.SOLUTION_EMPTY.get(null));
				}
			}
		}
		else if(ui instanceof Product)
		{
			Product p = ((Product) ui);
			
			String name = p.getName().getText();
			String provider = p.getProvider().getText();
			String price = p.getPrice().getText();
			String weight = p.getWeight().getText();
			String length = p.getLength().getText();
			String width = p.getWidth().getText();
			String height = p.getHeight().getText();
			
			if(action.equals(p.getChoose().getActionCommand()))
			{
				JFileChooser f = new JFileChooser();
				
				if(f.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					p.getImg().loadImage(f.getSelectedFile().getPath(), 120, 120);
				}
			}
			else if(action.equals(p.getSubmit().getActionCommand()))
			{
				String path = p.getImg().getUrl();
				
				if(path != null)
				{
					if(!name.isEmpty())
					{
						if(provider.matches(Regex.CNPJ) && price.matches(Regex.PRICE) && weight.matches(Regex.KG) && length.matches(Regex.CM) && width.matches(Regex.CM) && height.matches(Regex.CM))
						{
							ProviderDAO pd = new ProviderDAO();
							ProviderVO pv = pd.read(provider);
							
							if(pv != null)
							{
								ProductDAO pdd = new ProductDAO();
								ProductVO pvv = new ProductVO(pv, name, price, path, weight, length, width, height);
								
								pdd.create(pvv);
								ProductVO.list.add(pvv);
								
								JOptionPane.showMessageDialog(null, Message.SUCCESSFULLY.get("Produto"));
								
								path = null;
								
								p.clear();
								p.getUI().dispose();
								
								Reload.refresh();

							}
							else
							{
								JOptionPane.showMessageDialog(null, Message.NOT_EXISTS.get("Fornecedor"));
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, Message.INVALID_ARGUMENTS.get(null));
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, Message.FIELDS_EMPTY.get(null));
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, Message.IMAGE_INVALID.get(null));
				}
			}
			else
			{
				if(!p.getUI().isVisible())
				{
					d.add(p);
					p.getUI().setVisible(true);
				}
				else
				{
					p.getUI().moveToFront();
				}
			}
		}
		else if(ui instanceof Provider)
		{
			Provider p = ((Provider) ui);
			
			if(action.equals(p.getSubmit().getActionCommand()))
			{
				ProviderDAO pd = new ProviderDAO();
				
				String cnpj = p.getCnpj().getText();
				String name = p.getName().getText();
				String freight = p.getFreight().getText();
				String zipCode = p.getZipCode().getText();
				String phoneNumber = p.getPhoneNumber().getText();
				String category = p.getCategory().getText();
				
				if(!name.isEmpty() && !freight.isEmpty() && !zipCode.isEmpty() && !phoneNumber.isEmpty() && !category.isEmpty())
				{
					if(cnpj.matches(Regex.CNPJ) && freight.matches(Regex.PRICE) && zipCode.matches(Regex.CEP) && phoneNumber.matches(Regex.PHONE))
					{
						try
						{
							CepAPI cep = new CepAPI(zipCode);
							
							if(cep != null)
							{
								if(pd.read(cnpj) == null)
								{
									ProviderVO pv = new ProviderVO(cnpj, name, zipCode, phoneNumber, freight, category);
									
									pd.create(pv);
									ProviderVO.list.add(pv);
									
									JOptionPane.showMessageDialog(null, Message.SUCCESSFULLY.get("Fornecedor"));
									
									p.clear();
									p.getUI().dispose();						
									
									Reload.refresh();
								}
								else
								{
									JOptionPane.showMessageDialog(null, Message.EXISTS.get("Fornecedor"));
								}
							}
						}
						catch(ParserConfigurationException | SAXException | IOException | CepNotFoundException ex)
						{
							JOptionPane.showMessageDialog(null, Message.NOT_EXISTS.get(zipCode));
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, Message.INVALID_ARGUMENTS.get(null));
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, Message.FIELDS_EMPTY.get(null));
				}
			}
			else
			{
				if(!p.getUI().isVisible())
				{
					d.add(p);
					p.getUI().setVisible(true);
				}
				else
				{
					p.getUI().moveToFront();
				}
			}
		}
		else if(ui instanceof ClientTable)
		{
			ClientTable t = ((ClientTable) ui);
			
			if(action.equals(t.getSearch().getActionCommand()))
			{				
				String cpf = t.getCpf().getText();
				String name = t.getName().getText();
				
				if(!cpf.isEmpty())
				{
					if(cpf.matches(Regex.NUMBER))
					{
						t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + cpf, 0));						
					}
				}
				else if(!name.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + name, 1));
				}
				else
				{
					t.getSorter().setRowFilter(null);
				}
			}
			else
			{
				if(!t.getUI().isVisible())
				{
					d.add(t);
					t.getUI().setVisible(true);
				}
				else
				{
					t.getUI().moveToFront();
				}
			}
		}
		else if(ui instanceof ProductTable)
		{
			ProductTable t = ((ProductTable) ui);
			
			if(action.equals(t.getSearch().getActionCommand()))
			{				
				String cpf = t.getCpf().getText();
				String name = t.getName().getText();
				String provider = t.getProvider().getText();
				
				if(!cpf.isEmpty())
				{
					if(cpf.matches(Regex.CPF))
					{
						t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + cpf, 0));
					}
				}
				else if(!name.isEmpty())
				{

					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + name, 1));
				}
				else if(!provider.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + provider, 3));
				}
			}
			else
			{
				if(!t.getUI().isVisible())
				{
					d.add(t);
					t.getUI().setVisible(true);
				}
				else
				{
					t.getUI().moveToFront();
				}
			}
		}
		else if(ui instanceof ProviderTable)
		{
			ProviderTable t = ((ProviderTable) ui);
			
			if(action.equals(t.getSearch().getActionCommand()))
			{				
				String cpf = t.getId().getText();
				String name = t.getName().getText();
				String category = t.getCategory().getText();
				
				if(!cpf.isEmpty())
				{		
					if(cpf.matches(Regex.NUMBER))
					{
						t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + cpf, 0));
					}
				}
				else if(!name.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + name, 1));
				}
				else if(!category.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + category, 2));
				}
				else
				{
					t.getSorter().setRowFilter(null);
				}
			}
			else
			{
				if(!t.getUI().isVisible())
				{
					d.add(t);
					t.getUI().setVisible(true);
				}
				else
				{
					t.getUI().moveToFront();
				}
			}
		}
		else if(ui instanceof CompanyTable)
		{
			CompanyTable t = ((CompanyTable) ui);
			
			if(action.equals(t.getSearch().getActionCommand()))
			{				
				String cnpj = t.getId().getText();
				String name = t.getName().getText();
				String owner = t.getOwner().getText();
				
				if(!cnpj.isEmpty())
				{	
					if(cnpj.matches(Regex.NUMBER))
					{
						t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + cnpj, 0));
					}
				}
				else if(!name.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + name, 1));
				}
				else if(!owner.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + owner, 2));
				}
				else
				{
					t.getSorter().setRowFilter(null);
				}
			}
			else
			{
				if(!t.getUI().isVisible())
				{
					d.add(t);
					t.getUI().setVisible(true);
				}
				else
				{
					t.getUI().moveToFront();
				}
			}
		}
		else if(ui instanceof UserTable)
		{
			UserTable t = ((UserTable) ui);
			
			String cpf = t.getCpf().getText();
			String username = t.getUsername().getText();
			
			if(action.equals(t.getSearch().getActionCommand()))
			{					
				if(!cpf.isEmpty())
				{
					if(cpf.matches(Regex.NUMBER))
					{
						t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + cpf, 0));
					}
				}
				else if(!username.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + username, 1));
				}
				else
				{
					t.getSorter().setRowFilter(null);
				}
			}
			else
			{
				if(!t.getUI().isVisible())
				{
					d.add(t);
					t.getUI().setVisible(true);
				}
				else
				{
					t.getUI().moveToFront();
				}
			}
		}
		else if(ui instanceof Sale)
		{
			Sale s = ((Sale) ui);
			
			ProductDAO pd = new ProductDAO();
			List<CartVO> cart = CartVO.list;
			
			String id = s.getId().getText();
			ProductVO product = pd.read(id);
			String cpf = s.getCpf().getText();
			boolean se = s.getC().isSelected();
			String price = s.getPrice().getText();
			String amount = s.getAmount().getText();
			String observation = s.getObservation().getText();
			
			if(action.equals(s.getC().getActionCommand()))
			{
				if(se)
				{
					s.getCnpj().setText("CNPJ: ");
					s.getPhoneNumber().setText("Owner: ");
				}
				else
				{
					s.getCnpj().setText("CPF: ");
					s.getPhoneNumber().setText("Phone: ");
				}
			}
			else if(action.equals(s.getCpf().getText()))
			{
				if(!se)
				{
					ClientDAO cd = new ClientDAO();
					ClientVO p = cd.read(cpf);
					
					if(p != null)
					{
						s.getName().setText(p.getFirstName() + " " + p.getLastName());
						s.getPhone().setText(p.getPhoneNumber());
						s.getZipCode().setText(p.getZipCode());
						s.getCpf().setEditable(false);
						s.getC().setEnabled(false);
						
						try
						{
							CepAPI cep = new CepAPI(p.getZipCode());
							
							s.getNeighborhood().setText(cep.getBairro());
							s.getAddress().setText(cep.getLogradouro());
							s.getCity().setText(cep.getLocalidade());
							s.getState().setText(cep.getUf());
						}
						catch(IOException | ParserConfigurationException | SAXException | CepNotFoundException ex)
						{
							ex.printStackTrace();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, Message.INVALID.get("CPF"));
					}
				}
				else
				{
					CompanyVO c = new CompanyDAO().read(cpf);
					
					if(c != null)
					{
						s.getName().setText(c.getName());
						s.getPhone().setText(c.getOwner().getFirstName() + " " + c.getOwner().getLastName());
						s.getZipCode().setText(c.getZipCode());
						
						try
						{
							CepAPI cep = new CepAPI(c.getZipCode());
							
							s.getNeighborhood().setText(cep.getBairro());
							s.getAddress().setText(cep.getLogradouro());
							s.getCity().setText(cep.getLocalidade());
							s.getState().setText(cep.getUf());
						}
						catch(IOException | ParserConfigurationException | SAXException | CepNotFoundException ex)
						{
							ex.printStackTrace();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, Message.INVALID.get("CNPJ"));
					}
				}
			}
			else if(action.equals(s.getId().getText()))
			{				
				if(product != null)
				{
					s.getImg().loadImage(product.getPhoto(), 100, 100);
					s.getProduct().setText(product.getName());
					s.getPrice().setText(product.getPrice());
				}
				else
				{
					JOptionPane.showMessageDialog(null, Message.INVALID.get("Produto"));
				}
			}
			else if(action.equals(s.getAdd().getActionCommand()))
			{
				if(product != null)
				{
					if(cpf.matches(Regex.CPF) && id.matches(Regex.NUMBER) && amount.matches(Regex.AMOUNT) && price.matches(Regex.PRICE))
					{
						CartVO c = new CartVO(amount, product);
						
						if(!cart.contains(c))
						{
							cart.add(c);
							
							s.clearAfterInsert();
							
							Reload.refresh();							
						}
						else
						{
							JOptionPane.showMessageDialog(null, Message.EXISTS.get(product.getName()));
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, Message.INVALID_ARGUMENTS.get(null));
					}
				}
				else
					
				{
					JOptionPane.showMessageDialog(null, Message.FIELDS_EMPTY.get(null));
				}
			}
			else if(action.equals(s.getSubmit().getActionCommand()))
			{				
				if(!cart.isEmpty())
				{
					SellDAO sd = new SellDAO();
					CartDAO cad = new CartDAO();
					ClientDAO cd = new ClientDAO();
//					CompanyDAO cdd = new CompanyDAO();
					
					ClientVO client = cd.read(cpf);
//					CompanyVO company = cdd.read(cpf);
					
					SellVO sv = new SellVO(client, observation);					
					sd.create(sv);
					
					for(CartVO cv : cart)
					{
						if(!se)
						{
							cad.create(new CartVO(sd.getIdByClient(cpf), cv.getAmount(), cv.getProduct()));
						}
//						else
//						{
//							cad.create(new CartVO(sd.getIdByClient(cpf), amount, cv.getProduct()));
//						}	
					}
					
					JOptionPane.showMessageDialog(null, Message.PURCHASE.get(null));
					
					s.clear();
					
					Reload.refresh();
				}
				else
				{
					JOptionPane.showMessageDialog(null, Message.CHECKOUT_EMPTY.get(null));
				}
			}
			else
			{
				if(!s.getUI().isVisible())
				{
					d.add(s);
					s.getUI().setVisible(true);
				}
				else
				{
					s.getUI().moveToFront();
				}
			}
		}
		else if(ui instanceof ClientOpen)
		{
			ClientOpen c = ((ClientOpen) ui);
			
			String cpf = c.getCpf().getText();
			String firstName = c.getFirstName().getText();
			String lastName = c.getLastName().getText();
			String phone = c.getPhone().getText();
			String email = c.getEmail().getText();
			String date = c.getDateBorn().getText();
			Gender gender = Gender.valueOf(c.getGender().getSelectedItem().toString());
			String zipCode = c.getZipCode().getText();
			Object img = c.getImg();
			
			if(action.equals(zipCode))
			{
				if(zipCode.matches(Regex.CEP))
				{
					try
					{
						CepAPI cep = new CepAPI(zipCode);
						
						c.getNeighborhood().setText(cep.getBairro());
						c.getAddress().setText(cep.getLogradouro());
						c.getCity().setText(cep.getLocalidade());
						c.getState().setText(cep.getUf());
					}
					catch(CepNotFoundException ex)
					{
						JOptionPane.showMessageDialog(null, Message.NOT_EXISTS.get(zipCode));
					}
					catch (ParserConfigurationException | SAXException | IOException ex) 
					{
						ex.printStackTrace();
					}
				}
				else
				{					
					JOptionPane.showMessageDialog(null, Message.NOT_EXISTS.get(zipCode));
				}
			}
			else if(action.equals(c.getSubmit().getActionCommand()))
			{
				ClientDAO cd = new ClientDAO();
				
				if(cpf.matches(Regex.CPF) && firstName.matches(Regex.NAME) && lastName.matches(Regex.NAME) && date.matches(Regex.DATE) && email.matches(Regex.EMAIL) && zipCode.matches(Regex.CEP))
				{
					cd.update(new ClientVO(cpf, firstName, lastName, phone, email, date, gender, zipCode, (String) img));							
					
					JOptionPane.showMessageDialog(null, Message.UPDATE.get(firstName));
					
					c.getUI().dispose();
					
					Reload.refresh();
				}
				else
				{
					JOptionPane.showMessageDialog(null, Message.INVALID_ARGUMENTS.get(null));
				}
			}
		}
		else if(ui instanceof ProviderOpen)
		{
			ProviderOpen p = ((ProviderOpen) ui);
			
			String cnpj = p.getCnpj().getText();
			String name = p.getName().getText();
			String category = p.getCategory().getText();
			String freight = p.getFreight().getText();
			String phone = p.getPhone().getText();
			String zipCode = p.getZipCode().getText();
			
			if(action.equals(zipCode))
			{
				if(zipCode.matches(Regex.CEP))
				{
					try
					{
						CepAPI cep = new CepAPI(zipCode);
						
						p.getNeighborhood().setText(cep.getBairro());
						p.getAddress().setText(cep.getLogradouro());
						p.getCity().setText(cep.getLocalidade());
						p.getState().setText(cep.getUf());
					}
					catch(CepNotFoundException ex)
					{
						JOptionPane.showMessageDialog(null, Message.NOT_EXISTS.get(zipCode));
					}
					catch (ParserConfigurationException | SAXException | IOException ex) 
					{
						ex.printStackTrace();
					}
				}
				else
				{					
					JOptionPane.showMessageDialog(null, Message.NOT_EXISTS.get(zipCode));
				}
			}
			else if(action.equals(p.getSubmit().getActionCommand()))
			{				
				if(!(name.isEmpty() && category.isEmpty()))
				{
					if(cnpj.matches(Regex.CNPJ) && zipCode.matches(Regex.CEP) && freight.matches(Regex.PRICE) && phone.matches(Regex.PHONE))
					{
						ProviderDAO pd = new ProviderDAO();
						pd.update(new ProviderVO(cnpj, name, zipCode, phone, freight, category));				
						
						JOptionPane.showMessageDialog(null, Message.UPDATE.get(name));
						
						p.getUI().dispose();
						
						Reload.refresh();
					}
					else
					{
						JOptionPane.showMessageDialog(null, Message.INVALID_ARGUMENTS.get(null));
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, Message.FIELDS_EMPTY.get(null));
				}
			}
		}
		else if(ui instanceof CompanyOpen)
		{
			CompanyOpen p = ((CompanyOpen) ui);
			
			String cnpj = p.getCnpj().getText();
			String name = p.getName().getText();
			String owner = p.getOwner().getText();
			String zipCode = p.getZipCode().getText();
			
			if(action.equals(zipCode))
			{
				if(zipCode.matches(Regex.CEP))
				{
					try
					{
						CepAPI cep = new CepAPI(zipCode);
						
						p.getNeighborhood().setText(cep.getBairro());
						p.getAddress().setText(cep.getLogradouro());
						p.getCity().setText(cep.getLocalidade());
						p.getState().setText(cep.getUf());
					}
					catch(CepNotFoundException ex)
					{
						JOptionPane.showMessageDialog(null, Message.NOT_EXISTS.get(zipCode));
					}
					catch (ParserConfigurationException | SAXException | IOException ex) 
					{
						ex.printStackTrace();
					}
				}
				else
				{					
					JOptionPane.showMessageDialog(null, Message.NOT_EXISTS.get(zipCode));
				}
			}
			else if(action.equals(p.getSubmit().getActionCommand()))
			{
				if(!name.isEmpty())
				{
					if(cnpj.matches(Regex.CNPJ) && zipCode.matches(Regex.CEP))
					{
						ClientDAO cd = new ClientDAO();
						ClientVO cv = cd.read(owner);
						
						if(cv != null)
						{
							CompanyDAO pd = new CompanyDAO();
							pd.update(new CompanyVO(cnpj, name, cv, zipCode));				
							
							JOptionPane.showMessageDialog(null, Message.UPDATE.get(name));
							
							p.getUI().dispose();
							
							Reload.refresh();							
						}
						else
						{
							JOptionPane.showMessageDialog(null, Message.NOT_FOUND.get(owner));
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, Message.INVALID_ARGUMENTS.get(null));
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, Message.FIELDS_EMPTY.get(null));
				}
			}
		}
		else if(ui instanceof ProductOpen)
		{
			ProductOpen p = ((ProductOpen) ui);
			
			String id = p.getId().getText();
			String name = p.getName().getText();
			String weight = p.getWeight().getText();
			String price = p.getPrice().getText();
			String length = p.getLength().getText();
			String provider = p.getProvider().getText();
			String height = p.getHeight().getText();
			String width = p.getWidth().getText();
			
			if(action.equals(p.getSubmit().getActionCommand()))
			{
				if(!name.isEmpty())
				{
					if(weight.matches(Regex.KG) && price.matches(Regex.PRICE) && length.matches(Regex.CM) && height.matches(Regex.CM) && width.matches(Regex.CM))
					{
						ProviderDAO pdd = new ProviderDAO();
						ProviderVO pv = pdd.read(provider);
						
						if(pv != null)
						{
							ProductDAO pd = new ProductDAO();
							pd.update(new ProductVO(id, pv, name, price, weight, length, width, height));				
							
							JOptionPane.showMessageDialog(null, Message.UPDATE.get(name));
							
							p.getUI().dispose();
							
							Reload.refresh();							
						}
						else
						{
							JOptionPane.showMessageDialog(null, Message.NOT_FOUND.get(provider));
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, Message.INVALID_ARGUMENTS.get(null));
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, Message.FIELDS_EMPTY.get(null));
				}
			}
		}
		else if(ui instanceof SaleTable)
		{
			SaleTable st = ((SaleTable) ui);
			
			String id = st.getId().getText();
			String name = st.getName().getText();
			String by = st.getBy().getText();
			String observation = st.getObservation().getText();
			String status = st.getStatus().getSelectedItem().toString();
			
			if(action.equals(st.getSearch().getActionCommand()))
			{
				if(!id.isEmpty())
				{
					if(id.matches(Regex.NUMBER))
					{
						st.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + id, 0));
					}
				}
				else if(!name.isEmpty())
				{
					st.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + name, 1));
				}
				else if(!by.isEmpty())
				{
					st.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + by, 3));
				}
				else if(!observation.isEmpty())
				{
					st.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + id, 4));
				}
				else if(!status.isEmpty())
				{
					st.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + status, 5));
				}
				else
				{
					st.getSorter().setRowFilter(null);
				}
			}
			else
			{
				if(!st.getUI().isVisible())
				{
					d.add(st);
					st.getUI().setVisible(true);
				}
				else
				{
					st.getUI().moveToFront();
				}
			}	
		}
		else if(ui instanceof SaleOpen)
		{
			SaleOpen s = ((SaleOpen) ui);
			
			SellVO sv = s.getS();
			
			String id = sv.getId();
			UserVO by = sv.getBy();
			ClientVO cpf = sv.getPeople();
			SellVO.Status status = SellVO.Status.valueOf(s.getStatus().getSelectedItem().toString());
			Timestamp timestamp = sv.getTimestamp();
			String observation = s.getObservation().getText();
			
			if(action.equals(s.getSubmit().getActionCommand()))
			{
				SellDAO sd = new SellDAO();
				
				sd.update(new SellVO(id, by, cpf, observation, timestamp, status));
				
				JOptionPane.showMessageDialog(null, Message.TICKET_UPDATED.get(id));
				
				s.getUI().dispose();
				
				Reload.refresh();
			}
		}
	}
}
