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
		
		JMenuItem client = new JMenuItem("Client");
		client.addActionListener(new Action(new Client()));
		
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
		
		file.add(fileMenu);
		fileMenu.add(client);
		fileMenu.add(ticket);
		fileMenu.add(user);
		
		edit.add(ticketTable);
		
		info.add(account);

		ui.add(file);
		ui.add(edit);
		ui.add(info);
	}
}
