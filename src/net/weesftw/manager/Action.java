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
import net.weesftw.constraint.Message;
import net.weesftw.constraint.Regex;
import net.weesftw.constraint.Status;
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
import net.weesftw.view.Product;
import net.weesftw.view.ProductOpen;
import net.weesftw.view.ProductTable;
import net.weesftw.view.Provider;
import net.weesftw.view.ProviderOpen;
import net.weesftw.view.ProviderTable;
import net.weesftw.view.Sale;
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
						ClientDAO pd = new ClientDAO();
						ClientVO p = pd.searchByUser(user);
						
						Reload.populate();
						
						new Main(p, us);
						
						c.dispose();
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
			String img = c.getImg().getUrl();
			
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
				if(img != null)
				{
					if(!(phoneNumber.isEmpty()))
					{					
						if(cpf.matches(Regex.CPF) && firstName.matches(Regex.NAME) && lastName.matches(Regex.NAME) && date.matches(Regex.DATE) && email.matches(Regex.EMAIL) && zipCode.matches(Regex.CEP))
						{
							ClientDAO pd = new ClientDAO();
							ClientVO cv = new ClientVO(cpf, firstName, lastName, phoneNumber, email, date, gender, zipCode, img);
							
							pd.create(cv);
							ClientVO.list.add(cv);
							
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
						JOptionPane.showMessageDialog(null, Message.FIELDS_EMPTY.get(null));
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
			Category category = Category.valueOf(t.getCategory().getSelectedItem().toString());
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
									TicketVO tv = new TicketVO(title, cv, description, category, pv, priority);
									
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
								TicketVO tv = new TicketVO(title, cvv, description, category, pv, priority);
								
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
			String price = p.getPrice().getText();
			String path = p.getImg().getUrl();
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
				if(path != null)
				{
					if(!name.isEmpty())
					{
						ProviderDAO pd = new ProviderDAO();
						ProviderVO provider = pd.searchByName(p.getProvider().getSelectedItem().toString());
						
						if(provider != null)
						{
							if(price.matches(Regex.PRICE) && weight.matches(Regex.KG) && length.matches(Regex.CM) && width.matches(Regex.CM) && height.matches(Regex.CM))
							{
								ProductDAO pdd = new ProductDAO();
								ProductVO pv = new ProductVO(provider, name, price, path, weight, length, width, height);
								
								pdd.create(pv);
								ProductVO.list.add(pv);
								
								JOptionPane.showMessageDialog(null, Message.SUCCESSFULLY.get("Produto"));
								
								p.clear();
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
							JOptionPane.showMessageDialog(null, Message.NOT_EXISTS.get("Provedor"));
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
				String phone = t.getPhone().getText();
				String zipCode = t.getZipCode().getText();
				String gender = t.getGender().getSelectedItem().toString();
				
				if(!cpf.isEmpty())
				{					
					t.getSorter().setRowFilter(RowFilter.numberFilter(ComparisonType.EQUAL, Integer.valueOf(cpf), 0));
				}
				else if(!name.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + name, 1));
				}
				else if(!phone.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + phone, 2));
				}
				else if(!zipCode.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + zipCode, 3));
				}
				else if(!gender.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + gender, 5));
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
				String price = t.getPrice().getText();
				String provider = t.getProvider().getText();
				
				if(!cpf.isEmpty())
				{					
					t.getSorter().setRowFilter(RowFilter.numberFilter(ComparisonType.EQUAL, Integer.valueOf(cpf), 0));
				}
				else if(!name.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + name, 1));
				}
				else if(!price.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + price, 3));
				}
				else if(!provider.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + provider, 7));
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
		else if(ui instanceof ProviderTable)
		{
			ProviderTable t = ((ProviderTable) ui);
			
			if(action.equals(t.getSearch().getActionCommand()))
			{				
				String cpf = t.getId().getText();
				String name = t.getName().getText();
				String freight = t.getFreight().getText();
				String phone = t.getPhone().getText();
				
				if(!cpf.isEmpty())
				{					
					t.getSorter().setRowFilter(RowFilter.numberFilter(ComparisonType.EQUAL, Integer.valueOf(cpf), 0));
				}
				else if(!name.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + name, 1));
				}
				else if(!freight.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + freight, 3));
				}
				else if(!phone.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + phone, 7));
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
				String zipCode = t.getZipCode().getText();
				
				if(!cnpj.isEmpty())
				{					
					t.getSorter().setRowFilter(RowFilter.numberFilter(ComparisonType.EQUAL, Integer.valueOf(cnpj), 0));
				}
				else if(!name.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + name, 1));
				}
				else if(!owner.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + owner, 3));
				}
				else if(!zipCode.isEmpty())
				{
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + zipCode, 7));
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
					t.getSorter().setRowFilter(RowFilter.numberFilter(ComparisonType.EQUAL, Integer.valueOf(cpf), 0));
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
					if(cpf.matches(Regex.CPF) && id.matches(Regex.NUMBER) && amount.matches(Regex.NUMBER) && price.matches(Regex.PRICE))
					{							
						CartVO.list.add(new CartVO(amount, product));
						
						s.clearAfterInsert();
						
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
			else if(action.equals(s.getSubmit().getActionCommand()))
			{
				ProductVO p = pd.read(s.getId().getText());
				CartVO cart = new CartVO(amount, p);
				
				if(!CartVO.list.isEmpty())
				{
					SellDAO sd = new SellDAO();
					
					if(!se)
					{
						ClientDAO cd = new ClientDAO();
						ClientVO client = cd.read(cpf);
						
						sd.create(new SellVO(cart, client, observation));						
					}
					else
					{
						CompanyDAO cd = new CompanyDAO();
						CompanyVO company = cd.read(cpf);
						
						sd.create(new SellVO(cart, company, observation));
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
				
				if(img != null)
				{					
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
				else
				{
					cd.update(new ClientVO(cpf, firstName, lastName, phone, email, date, gender, zipCode, (byte[]) img));
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
	}
}
