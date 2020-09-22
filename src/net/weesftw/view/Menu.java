package net.weesftw.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public final class Menu extends JMenuBar 
{
	private static final long serialVersionUID = 1L;
	
	public Menu()
	{		
		JMenu file = new JMenu("File");
		JMenu add = new JMenu("New");
		JMenuItem client = new JMenuItem("Client");
		client.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Main.desk.add(new Client());
			}
		});
		
		JMenuItem company = new JMenuItem("Company");
		company.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Main.desk.add(new Company());
			}
		});
		
		JMenuItem ticket = new JMenuItem("Ticket");
		ticket.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Main.desk.add(new Ticket());
			}
		});
		
		JMenu edit = new JMenu("Edit");
		JMenu info = new JMenu("Info");
		
		file.add(add);
		add.add(client);
		add.add(company);
		add.add(ticket);

		add(file);
		add(edit);
		add(info);
	}
}
