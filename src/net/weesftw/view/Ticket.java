package net.weesftw.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.WindowConstants;

import net.weesftw.constraint.Category;
import net.weesftw.constraint.Product;
import net.weesftw.manager.Action;
import net.weesftw.model.Button;
import net.weesftw.model.ComboBox;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.ScrollPane;
import net.weesftw.model.TextArea;
import net.weesftw.model.TextField;

public class Ticket extends UI<InternalFrame>
{
	private Button submit;
	private ComboBox<?> category, product;
	private JCheckBox priority, pj;
	private TextArea description;
	private TextField client, title, company;
	
	public Ticket()
	{
		super(new InternalFrame("Ticket", false, true, false, true));
		
		submit = new Button("Submit");
		category = new ComboBox<Category>(Category.values(), 15, 21);
		product = new ComboBox<Product>(Product.values(), 15, 21);
		priority = new JCheckBox("Priority");
		pj = new JCheckBox("PJ");
		client = new TextField(15);
		title = new TextField(15);
		company = new TextField(15);
		description = new TextArea(1, 1);
		ScrollPane s = new ScrollPane(description);
		
		description.setLineWrap(true);
//		description.setWrapStyleWord(true);
		
		Panel p = new Panel("New Ticket", 4, 4, 2, 4);
		
		p.setComponent(new Label("CPF: "), 0, 0);
		p.setComponent(client, 0, 1);
		
		p.setComponent(new Label("Company: "), 1, 0);
		p.setComponent(company, 1, 1);
		company.setEditable(false);
		
		p.setComponent(new Label("Title: "), 0, 2);
		p.setComponent(title, 0, 3);
		
		p.setComponent(new Label("Category: "), 1, 2);
		p.setComponent(category, 1, 3);
		category.setBackground(Color.WHITE);
		
		p.setComponent(new Label("Product: "), 0, 4);
		p.setComponent(product, 0, 5);
		product.setBackground(Color.WHITE);
		
		p.setComponent(new Label("Select: "), 1, 4);
		p.setComponent(pj, 1, 5);
		pj.addActionListener(new Action(this));
		
		p.setComponent(new Label("Description: "), 0, 6);
		p.setComponent(s, 0, 7, 100, 150, 2);
		description.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		p.setComponent(priority, 0, 8);
		p.setComponent(submit, 1, 8);
		submit.addActionListener(new Action(this));
		
		ui.add(p);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ui.setVisible(true);
	}
	
	public JCheckBox getPj()
	{
		return pj;
	}

	public Button getSubmit() 
	{
		return submit;
	}

	public ComboBox<?> getCategory() 
	{
		return category;
	}

	public ComboBox<?> getProduct() 
	{
		return product;
	}

	public JCheckBox getPriority() 
	{
		return priority;
	}

	public TextArea getDescription() 
	{
		return description;
	}

	public TextField getClient() 
	{
		return client;
	}

	public TextField getTitle() 
	{
		return title;
	}

	public TextField getCompany() 
	{
		return company;
	}
}
