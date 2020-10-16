package net.weesftw.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import net.weesftw.manager.Action;
import net.weesftw.manager.Authenticate;

public final class Menu extends UI<JMenuBar>
{
	private JMenuItem user;
	
	public Menu()
	{
		super(new JMenuBar());
		
		Authenticate a = Main.getInstance().getAuth();
		
		JMenu file = new JMenu("File");
		JMenu fileMenu = new JMenu("New");
		JMenu edit = new JMenu("View");
		JMenu info = new JMenu("Info");
		
		JMenuItem client = new JMenuItem("Client");		
		client.addActionListener(new Action(Client.getInstance()));
		
		JMenuItem company = new JMenuItem("Company");		
		company.addActionListener(new Action(Company.getInstance()));
		
		JMenuItem product = new JMenuItem("Product");
		product.setEnabled(a.getUser().getDepartment().isPrivilege() ? true : false);
		product.addActionListener(new Action(Product.getInstance()));
		
		JMenuItem provider = new JMenuItem("Provider");
		provider.setEnabled(a.getUser().getDepartment().isPrivilege() ? true : false);
		provider.addActionListener(new Action(Provider.getInstance()));
		
		JMenuItem ticket = new JMenuItem("Ticket");
		ticket.addActionListener(new Action(Ticket.getInstance()));
		
		user = new JMenuItem("User");
		user.setEnabled(a.getUser().getDepartment().isPrivilege() ? true : false);
		user.addActionListener(new Action(this));
		
		JMenuItem clientTable = new JMenuItem("Client");
		clientTable.addActionListener(new Action(ClientTable.getInstance()));
		
		JMenuItem companyTable = new JMenuItem("Company");
		companyTable.addActionListener(new Action(CompanyTable.getInstance()));
		
		JMenuItem saleTable = new JMenuItem("Sale");
		saleTable.addActionListener(new Action(SaleTable.getInstance()));
		
		JMenuItem ticketTable = new JMenuItem("Ticket");
		ticketTable.addActionListener(new Action(TicketTable.getInstance()));
		
		JMenuItem productTable = new JMenuItem("Product");
		productTable.addActionListener(new Action(ProductTable.getInstance()));
		
		JMenuItem providerTable = new JMenuItem("Provider");
		providerTable.addActionListener(new Action(ProviderTable.getInstance()));
		
		JMenuItem sale = new JMenuItem("Sale");
		sale.addActionListener(new Action(Sale.getInstance()));
		
		JMenuItem userTable = new JMenuItem("User");
		userTable.setEnabled(a.getUser().getDepartment().isPrivilege() ? true : false);
		userTable.addActionListener(new Action(UserTable.getInstance()));
		
		JMenuItem account = new JMenuItem("Account");
		account.addActionListener(new Action(Account.getInstance()));
		
		file.add(fileMenu);
		fileMenu.add(client);
		fileMenu.add(company);
		fileMenu.add(product);
		fileMenu.add(provider);
		fileMenu.add(sale);
		fileMenu.add(ticket);
		fileMenu.add(user);
		
		edit.add(clientTable);
		edit.add(companyTable);
		edit.add(productTable);
		edit.add(providerTable);
		edit.add(saleTable);
		edit.add(ticketTable);
		edit.add(userTable);
		
		info.add(account);

		ui.add(file);
		ui.add(edit);
		ui.add(info);
	}

	public JMenuItem getUser() 
	{
		return user;
	}
}
