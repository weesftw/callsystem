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
import net.weesftw.constraint.ImagePath;
import net.weesftw.constraint.Message;
import net.weesftw.constraint.Status;
import net.weesftw.dao.CartDAO;
import net.weesftw.dao.CompanyDAO;
import net.weesftw.dao.ClientDAO;
import net.weesftw.dao.ProductDAO;
import net.weesftw.dao.ProviderDAO;
import net.weesftw.dao.SellDAO;
import net.weesftw.dao.TicketDAO;
import net.weesftw.dao.UserDAO;
import net.weesftw.exception.CepNotFoundException;
import net.weesftw.model.DesktopPane;
import net.weesftw.view.Account;
import net.weesftw.view.Client;
import net.weesftw.view.ClientTable;
import net.weesftw.view.Login;
import net.weesftw.view.Main;
import net.weesftw.view.Product;
import net.weesftw.view.ProductTable;
import net.weesftw.view.Provider;
import net.weesftw.view.ProviderTable;
import net.weesftw.view.Sale;
import net.weesftw.view.Ticket;
import net.weesftw.view.TicketOpen;
import net.weesftw.view.TicketTable;
import net.weesftw.view.UI;
import net.weesftw.view.User;
import net.weesftw.view.UserTable;
import net.weesftw.vo.CartVO;
import net.weesftw.vo.CompanyVO;
import net.weesftw.vo.ClientVO;
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
		String action = e.getActionCommand();
		Main m = Main.instance;
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
						
						c.dispose();
						
						new Main(p, us);
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
				
				return;
			}
			else if(action.equals(c.getSubmit().getActionCommand()))
			{
				ClientDAO pd = new ClientDAO();
				
				if(!(cpf.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || date.isEmpty() || gender == null || zipCode.isEmpty() || img == null))
				{
					if(cpf.matches(Regex.CPF) && (firstName.matches(Regex.NAME) && lastName.matches(Regex.NAME)) && date.matches(Regex.DATE) && email.matches(Regex.EMAIL) && zipCode.matches(Regex.CEP))
					{
						pd.create(new ClientVO(cpf, firstName, lastName, phoneNumber, email, date, gender, zipCode, img));
						
						JOptionPane.showMessageDialog(null, Message.SUCCESSFULLY.get(firstName));
						
						c.getUI().dispose();						
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
				d.add(new Client());
			}
		}
		else if(ui instanceof Ticket)
		{
			Ticket t = ((Ticket) ui);
			
			if(action.equals(t.getPj().getActionCommand()))
			{
				t.getCompany().setEditable(t.getPj().isSelected() ? true : false);
				t.getClient().setEditable(!t.getCompany().isEditable());
				t.getCompany().setText("");
				t.getClient().setText("");				
			}
			else if(action.equals(t.getSubmit().getActionCommand()))
			{
				TicketDAO td = new TicketDAO();
				ClientDAO pd = new ClientDAO();
				CompanyDAO cd = new CompanyDAO();
				ProductDAO pdd = new ProductDAO();
				
				Category category = Category.valueOf(t.getCategory().getSelectedItem().toString());
				ProductVO product = pdd.searchByName(t.getProduct().getSelectedItem().toString());
				boolean priority = t.getPriority().isSelected() ? true : false;
				ClientVO client = pd.read(t.getClient().getText());
				String title = t.getTitle().getText();
				CompanyVO company = cd.read(t.getCompany().getText());
				String description = t.getDescription().getText();
				
				if(category != null && product != null && !title.isEmpty() && !description.isEmpty())
				{
					if(t.getPj().isSelected())
					{
						if(company != null)
						{
							if(company.getCnpj().matches(Regex.CNPJ))
							{
	
								td.create(new TicketVO(title, company, description, category, product, priority));
								
								JOptionPane.showMessageDialog(null, Message.SUCCESSFULLY.get("Ticket"));
								
								t.getUI().dispose();
							}
							else
							{
								JOptionPane.showMessageDialog(null, Message.INVALID_ARGUMENTS.get(null));
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, Message.NOT_FOUND.get(t.getCompany().getText()));
						}
					}
					else
					{
						if(client != null)
						{
							if(client.getCpf().matches(Regex.CPF))
							{							
								td.create(new TicketVO(title, client, description, category, product, priority));
								
								JOptionPane.showMessageDialog(null, Message.SUCCESSFULLY.get("Ticket"));
								
								t.getUI().dispose();
							}
							else
							{
								JOptionPane.showMessageDialog(null, Message.INVALID_ARGUMENTS.get(null));
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, Message.NOT_FOUND.get(t.getClient().getText()));
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
				d.add(new Ticket());
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
			Gender gender = Gender.valueOf(u.getGender().getSelectedItem().toString());
			String zipCode = u.getZipCode().getText();
			String img = u.getImg().getUrl();
			String username = u.getUser().getText();
			String passwd = u.getPasswd().getText();
			Department department = Department.valueOf(u.getDepartment().getSelectedItem().toString());
			
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
				ClientDAO pd = new ClientDAO();
				
				if(!(cpf.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || date.isEmpty() || gender == null || zipCode.isEmpty() || img.isEmpty() && !cpf.isEmpty() && !username.isEmpty() && !passwd.isEmpty() && department == null))
				{
					if(cpf.matches(Regex.CPF) && (firstName.matches(Regex.NAME) && lastName.matches(Regex.NAME)) && date.matches(Regex.DATE) && email.matches(Regex.EMAIL) && zipCode.matches(Regex.CEP))
					{
						if(ud.searchByUser(username) == null)
						{
							if(pd.read(cpf) != null)
							{
								ud.create(new UserVO(cpf, username, passwd, department));
								
								JOptionPane.showMessageDialog(null, Message.SUCCESSFULLY.get("User"));
								
								u.getUI().dispose();															
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
			else if(action.equals(t.getRefresh().getActionCommand()))
			{
				t.getAt().fireTableDataChanged();
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
				ClientDAO pdd = new ClientDAO();
				CompanyDAO cd = new CompanyDAO();
				UserDAO ud = new UserDAO();
				
				int id = Integer.valueOf(t.getId().getText());
				Status status = Status.valueOf(t.getStatus().getSelectedItem().toString());
				ProductVO product = pd.searchByName(t.getProduct().getText());
				boolean priority = t.getPriority().isSelected();
				Category category = Category.valueOf(t.getCategory().getText());
				String title = t.getTitle().getText();
				String description = t.getDescription().getText();
				CompanyVO company = cd.read(t.getCompany().getText());
				ClientVO client = pdd.read(t.getClient().getText());
				UserVO user = ud.searchByUser(t.getUser().getText());
				String solution = t.getSolution().getText();
				Timestamp time = new Timestamp(System.currentTimeMillis());
				
				if(!(solution.isEmpty()))
				{
					td.update(new TicketVO(id, title, client, company, user, description, solution, time, category, product, status, priority));
					
					JOptionPane.showMessageDialog(null, Message.TICKET_UPDATED.get(t.getTitle().getText()));
					
					t.getUI().dispose();
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
			
			ProviderDAO pd = new ProviderDAO();
			ProductDAO pdd = new ProductDAO();
			
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
				
				return;
			}
			else if(action.equals(p.getSubmit().getActionCommand()))
			{
				if(!name.isEmpty() && !price.isEmpty() && path != null && !weight.isEmpty() && !length.isEmpty() && !width.isEmpty() && !height.isEmpty())
				{
					ProviderVO provider = pd.searchByName(p.getProvider().getSelectedItem().toString());
					
					if(provider != null)
					{
						if(price.matches(Regex.PRICE) && weight.matches(Regex.KG) && length.matches(Regex.CM) && width.matches(Regex.CM) && height.matches(Regex.CM))
						{						
							pdd.create(new ProductVO(provider, name, price, path, weight, length, width, height));		
							
							JOptionPane.showMessageDialog(null, Message.SUCCESSFULLY.get("Produto"));
							
							p.getUI().dispose();
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
					if(name.matches(Regex.NAME) && freight.matches(Regex.PRICE) && zipCode.matches(Regex.CEP) && phoneNumber.matches(Regex.PHONE))
					{
						if(pd.searchByName(name) == null)
						{
							pd.create(new ProviderVO(name, zipCode, phoneNumber, freight));
							
							JOptionPane.showMessageDialog(null, Message.SUCCESSFULLY.get("Fornecedor"));
							
							p.getUI().dispose();							
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
				d.add(new Provider());
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
					t.getSorter().setRowFilter(RowFilter.regexFilter("^(?i)" + gender, 7));
				}
				else
				{
					t.getSorter().setRowFilter(null);
				}
			}
			else if(action.equals(t.getRefresh().getActionCommand()))
			{
				t.getAt().fireTableDataChanged();
			}
			else
			{
				d.add(new ClientTable());				
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
			else if(action.equals(t.getRefresh().getActionCommand()))
			{
				t.getAt().setList(new ProductDAO().list());
				t.getAt().fireTableDataChanged();
			}
			else
			{
				d.add(new ProductTable());
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
			else if(action.equals(t.getRefresh().getActionCommand()))
			{
				t.getAt().fireTableDataChanged();
			}
			else
			{
				d.add(new ProviderTable());
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
			else if(action.equals(t.getRefresh().getActionCommand()))
			{
				t.getAt().fireTableDataChanged();
			}
			else
			{
				d.add(new UserTable());
			}
		}
		else if(ui instanceof Sale)
		{
			Sale s = ((Sale) ui);
			
			boolean se = s.getC().isSelected();
			
			CartDAO cd = new CartDAO();
			SellDAO sd = new SellDAO();
			ProductDAO pd = new ProductDAO();
			CompanyDAO cdd = new CompanyDAO();
			ClientDAO pdd = new ClientDAO();
			
			String amount = s.getAmount().getText();
			String cpf = s.getCpf().getText();
			String observation = s.getObservation().getText();
			String id = s.getId().getText();
			String product = s.getProduct().getText();
			String price = s.getPrice().getText();
			
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
					ClientVO p = new ClientDAO().read(cpf);
					
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
				ProductVO p = pd.read(s.getId().getText());
				
				if(p != null)
				{
					s.getImg().loadImage(p.getPhoto(), 100, 100);
					s.getProduct().setText(p.getName());
					s.getPrice().setText(price.concat(p.getPrice()));
				}
				else
				{
					JOptionPane.showMessageDialog(null, Message.INVALID.get("Produto"));
				}
			}
			else if(action.equals(s.getAdd().getActionCommand()))
			{
				ProductVO pv = pd.read(s.getId().getText());
				
				if(pv != null)
				{
					if(!(cpf.isEmpty() && id.isEmpty() && amount.isEmpty() && product.isEmpty() && price.isEmpty()))
					{
						if(cpf.matches(Regex.CPF) && id.matches(Regex.NUMBER) && amount.matches(Regex.NUMBER))
						{
							s.getId().setText("");
							s.getAmount().setText("1");
							s.getProduct().setText("");
							s.getPrice().setText("R$: ");
							s.getImg().loadImage(ImagePath.ICON, 100, 100);
							
							s.getAt().getList().add(new CartVO(amount, pv));
							s.getAt().fireTableDataChanged();
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
					JOptionPane.showMessageDialog(null, Message.NOT_EXISTS.get("Produto"));
				}
			}
			else if(action.equals(s.getSubmit().getActionCommand()))
			{
				ProductVO p = pd.read(s.getId().getText());
				CartVO cart = new CartVO(amount, p);
				
				if(!s.getAt().getList().isEmpty())
				{
					if(!se)
					{
						CompanyVO company = cdd.read(cpf);
						
						cd.create(cart);
						sd.create(new SellVO(cart, company, observation));				
					}
					else
					{
						ClientVO client = pdd.read(cpf);
						
						cd.create(cart);
						sd.create(new SellVO(cart, client, observation));
					}
					
					JOptionPane.showMessageDialog(null, Message.PURCHASE.get(null));	
				}
				else
				{
					JOptionPane.showMessageDialog(null, Message.CHECKOUT_EMPTY.get(null));
				}
			}
			else
			{
				d.add(new Sale());
			}
		}
	}
}
