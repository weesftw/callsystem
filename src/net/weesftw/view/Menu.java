package net.weesftw.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import net.weesftw.manager.Action;

public final class Menu extends UI<JMenuBar>
{	
	public Menu()
	{
		super(new JMenuBar());
		
		JMenu file = new JMenu("File");
		JMenu add = new JMenu("New");
		
		JMenuItem client = new JMenuItem("Client");
		client.addActionListener(new Action(new Client()));
		
		JMenuItem company = new JMenuItem("Company");
		company.addActionListener(new Action(new Company()));
		
//		JMenuItem product = new JMenuItem("Product");
//		product.addActionListener(new Action(new Product()));
		
		JMenuItem ticket = new JMenuItem("Ticket");
		ticket.addActionListener(new Action(new Ticket()));
		
		JMenuItem user = new JMenuItem("User");
		user.addActionListener(new Action(new User()));
		
		JMenu edit = new JMenu("View");
		
		JMenuItem ticketTable = new JMenuItem("Ticket");
		ticketTable.addActionListener(new Action(new TicketTable()));
		
		JMenu info = new JMenu("Info");
		
		JMenuItem account = new JMenuItem("Account");
		account.addActionListener(new Action(new Account()));
		
		file.add(add);
		add.add(client);
		add.add(company);
//		add.add(product);
		add.add(ticket);
		add.add(user);
		
		edit.add(ticketTable);
		
		info.add(account);

		ui.add(file);
		ui.add(edit);
		ui.add(info);
	}
}
