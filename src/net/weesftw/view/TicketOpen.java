package net.weesftw.view;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.WindowConstants;

import net.weesftw.constraint.Status;
import net.weesftw.manager.Action;
import net.weesftw.model.Button;
import net.weesftw.model.ComboBox;
import net.weesftw.model.Dialog;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.ScrollPane;
import net.weesftw.model.TextArea;
import net.weesftw.model.TextField;
import net.weesftw.vo.TicketVO;

public class TicketOpen extends UI<Dialog> 
{	
	private Button submit;
	private ComboBox<?> status;
	private TextArea description, solution;
	private TextField id, timestamp, product, category, title, company, client, user;
	private JCheckBox priority;
	private TicketVO t;
	
	public TicketOpen(TicketVO t) 
	{
		super(new Dialog("Ticket: " + t.getId(), true));
		
		this.t = t;
		
		boolean privilege = Main.getInstance().getAuth().isPrivilege();
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
		Panel p = new Panel("Ticket Detail", 4, 4, 4, 4);
		
		id = new TextField(15);
		id.setText(String.valueOf(t.getId()));
		id.setEditable(false);
		
		status = new ComboBox<Status>(Status.values());
		status.setSelectedItem(t.getStatus());
		status.setEnabled((status.getSelectedItem() != Status.CLOSED && privilege) ? true : false);
		
		timestamp = new TextField(15);
		timestamp.setText(d.format(new Date(t.getTimestamp().getTime())));
		timestamp.setEditable(false);
		
		product = new TextField(15);
		product.setText(t.getProduct().getName());
		product.setEditable(false);
		
		category = new TextField(15);
		category.setText(t.getCategory().name());
		category.setEditable(false);
		
		title = new TextField(15);
		title.setText(t.getTitle());
		title.setEditable(false);
		
		description = new TextArea(1, 1);
		description.setText(t.getDescription());
		description.setEditable(false);
		description.setLineWrap(true);
		description.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		company = new TextField(15);
		company.setText(t.getCompany() != null ? t.getCompany().getName() : "");
		company.setEditable(false);
		
		client = new TextField(15);
		client.setText(t.getClient().getFirstName() + " " + t.getClient().getLastName());
		client.setEditable(false);
		
		user = new TextField(15);
		user.setText(t.getUser().getUsername());
		user.setEditable(false);
		
		priority = new JCheckBox("Prioridade");
		priority.setSelected(t.isPriority());
		priority.setEnabled((status.getSelectedItem() != Status.CLOSED && privilege) ? true : false);
		
		solution = new TextArea(1, 1);
		solution.setText(t.getSolution());
		solution.setEditable(status.getSelectedItem() != Status.CLOSED ? true : false);
		solution.setLineWrap(true);
		solution.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		submit = new Button("Submit");
		submit.setEnabled(status.getSelectedItem() != Status.CLOSED ? true : false);
		submit.addActionListener(new Action(this));
		
		ScrollPane s = new ScrollPane(description);
		ScrollPane s2 = new ScrollPane(solution);
		
		p.setComponent(new Label("ID: "));
		p.setComponent(id, 1, 0);
		
		p.setComponent(new Label("Title: "), 2, 0);
		p.setComponent(title, 3, 0);
		
		p.setComponent(new Label("Date: "), 0, 1);
		p.setComponent(timestamp, 1, 1);
		
		p.setComponent(new Label("Open by: "), 2, 1);
		p.setComponent(user, 3, 1);
		
		p.setComponent(new Label("Category: "), 0, 2);
		p.setComponent(category, 1, 2);
		
		p.setComponent(new Label("Product: "), 2, 2);
		p.setComponent(product, 3, 2);
		
		p.setComponent(new Label("Client: "), 0, 3);
		p.setComponent(client, 1, 3);
		
		p.setComponent(new Label("Company: "), 2, 3);
		p.setComponent(company, 3, 3);
		
		p.setComponent(new Label("Status: "), 0, 4);
		p.setComponent(status, 1, 4);
		status.setBackground(Color.WHITE);
		
		p.setComponent(priority, 2, 4);
		
		p.setComponent(new Label("Description: "), 0, 5);
		p.setComponent(s, 0, 6, 100, 150, 2);
		
		if(privilege)
		{
			p.setComponent(new Label("Solution: "), 3, 5);
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
	
	public Button getSubmit() 
	{
		return submit;
	}

	public TextField getId() 
	{
		return id;
	}

	public ComboBox<?> getStatus() 
	{
		return status;
	}

	public TextField getTimestamp() 
	{
		return timestamp;
	}

	public TextField getProduct() 
	{
		return product;
	}

	public TextField getCategory() 
	{
		return category;
	}

	public TextField getTitle() 
	{
		return title;
	}

	public TextArea getDescription() 
	{
		return description;
	}
	
	public TextArea getSolution()
	{
		return solution;
	}

	public TextField getCompany() 
	{
		return company;
	}

	public TextField getClient() 
	{
		return client;
	}

	public TextField getUser() 
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
