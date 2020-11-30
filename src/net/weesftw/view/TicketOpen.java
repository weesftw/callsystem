package net.weesftw.view;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import net.weesftw.constraint.Status;
import net.weesftw.manager.Action;
import net.weesftw.model.Dialog;
import net.weesftw.model.Panel;
import net.weesftw.vo.TicketVO;

public class TicketOpen extends UI<JDialog> 
{	
	private JButton submit;
	private JComboBox<?> status;
	private JTextArea description, solution;
	private JTextField id, timestamp, product, category, title, company, client, user;
	private JCheckBox priority;
	private TicketVO t;
	
	public TicketOpen(TicketVO t) 
	{
		super(new Dialog("Ticket: " + t.getId(), true));
		
		this.t = t;
		
		boolean privilege = Main.getInstance().getAuth().isPrivilege();
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
		Panel p = new Panel("Ticket Detail", 4, 4, 4, 4);
		
		id = new JTextField(15);
		id.setText(String.valueOf(t.getId()));
		id.setEditable(false);
		
		status = new JComboBox<Status>(Status.values());
		status.setSelectedItem(t.getStatus());
		status.setEnabled((status.getSelectedItem() != Status.CLOSED && privilege) ? true : false);
		
		timestamp = new JTextField(15);
		timestamp.setText(d.format(new Date(t.getTimestamp().getTime())));
		timestamp.setEditable(false);
		
		product = new JTextField(15);
		product.setText(t.getProduct().getName());
		product.setEditable(false);
		
		category = new JTextField(15);
		category.setText(t.getCategory().name());
		category.setEditable(false);
		
		title = new JTextField(15);
		title.setText(t.getTitle());
		title.setEditable(false);
		
		description = new JTextArea(1, 1);
		description.setText(t.getDescription());
		description.setEditable(false);
		description.setLineWrap(true);
		description.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		company = new JTextField(15);
		company.setText(t.getCompany() != null ? t.getCompany().getName() : "");
		company.setEditable(false);
		
		client = new JTextField(15);
		client.setText(t.getClient().getFirstName() + " " + t.getClient().getLastName());
		client.setEditable(false);
		
		user = new JTextField(15);
		user.setText(t.getUser().getUsername());
		user.setEditable(false);
		
		priority = new JCheckBox("Prioridade");
		priority.setSelected(t.isPriority());
		priority.setEnabled((status.getSelectedItem() != Status.CLOSED && privilege) ? true : false);
		
		solution = new JTextArea(1, 1);
		solution.setText(t.getSolution());
		solution.setEditable(status.getSelectedItem() != Status.CLOSED ? true : false);
		solution.setLineWrap(true);
		solution.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		submit = new JButton("Submit");
		submit.setEnabled(status.getSelectedItem() != Status.CLOSED ? true : false);
		submit.addActionListener(new Action(this));
		
		JScrollPane s = new JScrollPane(description);
		JScrollPane s2 = new JScrollPane(solution);
		
		p.setComponent(new JLabel("ID: "));
		p.setComponent(id, 1, 0);
		
		p.setComponent(new JLabel("Title: "), 2, 0);
		p.setComponent(title, 3, 0);
		
		p.setComponent(new JLabel("Date: "), 0, 1);
		p.setComponent(timestamp, 1, 1);
		
		p.setComponent(new JLabel("Open by: "), 2, 1);
		p.setComponent(user, 3, 1);
		
		p.setComponent(new JLabel("Category: "), 0, 2);
		p.setComponent(category, 1, 2);
		
		p.setComponent(new JLabel("Product: "), 2, 2);
		p.setComponent(product, 3, 2);
		
		p.setComponent(new JLabel("Client: "), 0, 3);
		p.setComponent(client, 1, 3);
		
		p.setComponent(new JLabel("Company: "), 2, 3);
		p.setComponent(company, 3, 3);
		
		p.setComponent(new JLabel("Status: "), 0, 4);
		p.setComponent(status, 1, 4);
		status.setBackground(Color.WHITE);
		
		p.setComponent(priority, 2, 4);
		
		p.setComponent(new JLabel("Description: "), 0, 5);
		p.setComponent(s, 0, 6, 100, 150, 2);
		
		if(privilege)
		{
			p.setComponent(new JLabel("Solution: "), 3, 5);
			p.setComponent(s2, 3, 6, 100, 150);			
		}
		
		p.setComponent(submit, 3, 7);
		
		ui.add(p);
		
		ui.pack();
		ui.setModal(true);
		ui.setLocationRelativeTo(null);
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ui.setVisible(true);
	}
	
	public JButton getSubmit() 
	{
		return submit;
	}

	public JTextField getId() 
	{
		return id;
	}

	public JComboBox<?> getStatus() 
	{
		return status;
	}

	public JTextField getTimestamp() 
	{
		return timestamp;
	}

	public JTextField getProduct() 
	{
		return product;
	}

	public JTextField getCategory() 
	{
		return category;
	}

	public JTextField getTitle() 
	{
		return title;
	}

	public JTextArea getDescription() 
	{
		return description;
	}
	
	public JTextArea getSolution()
	{
		return solution;
	}

	public JTextField getCompany() 
	{
		return company;
	}

	public JTextField getClient() 
	{
		return client;
	}

	public JTextField getUser() 
	{
		return user;
	}

	public JCheckBox getPriority() 
	{
		return priority;
	}

	public TicketVO getT() 
	{
		return t;
	}	
}
