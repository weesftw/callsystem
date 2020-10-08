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
		JMenu fileMenu = new JMenu("New");
		JMenu edit = new JMenu("View");
		JMenu info = new JMenu("Info");
		
		JMenuItem client = new JMenuItem("Client");
		client.addActionListener(new Action(new Client()));
		
		JMenuItem product = new JMenuItem("Product");
		product.addActionListener(new Action(new Product()));
		
		JMenuItem provider = new JMenuItem("Provider");
		provider.addActionListener(new Action(new Provider()));
		
		JMenuItem ticket = new JMenuItem("Ticket");
		ticket.addActionListener(new Action(new Ticket()));
		
		JMenuItem user = new JMenuItem("User");
		user.addActionListener(new Action(new User()));
		
		JMenuItem ticketTable = new JMenuItem("Ticket");
		ticketTable.addActionListener(new Action(new TicketTable()));
		
		JMenuItem account = new JMenuItem("Account");
		account.addActionListener(new Action(new Account()));
		
		file.add(fileMenu);
		fileMenu.add(client);
		fileMenu.add(product);
		fileMenu.add(provider);
		fileMenu.add(ticket);
		fileMenu.add(user);
		
		edit.add(ticketTable);
		
		info.add(account);

		ui.add(file);
		ui.add(edit);
		ui.add(info);
	}
}
