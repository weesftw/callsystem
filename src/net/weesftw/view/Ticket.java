package net.weesftw.view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import net.weesftw.constraint.Category;
import net.weesftw.dao.ProductDAO;
import net.weesftw.manager.Action;
import net.weesftw.model.Panel;
import net.weesftw.vo.ProductVO;

public class Ticket extends UI<JInternalFrame>
{
	private static Ticket instance;
	
	private JButton submit;
	private JComboBox<?> category, product;
	private JCheckBox priority, pj;
	private JTextArea description;
	private JTextField client, title, company;
	
	private Ticket()
	{
		super(new JInternalFrame("Ticket", false, true, false, true));
		
		ProductDAO pd = new ProductDAO();
		Vector<String> v = new Vector<String>();
		
		for(ProductVO pv : pd.list())
		{
			v.add(pv.getName());
		}
		
		submit = new JButton("Submit");
		category = new JComboBox<Category>(Category.values());
		product = new JComboBox<String>(v);
		priority = new JCheckBox("Prioridade");
		pj = new JCheckBox("PJ");
		client = new JTextField(15);
		title = new JTextField(15);
		company = new JTextField(15);
		description = new JTextArea(1, 1);
		JScrollPane s = new JScrollPane(description);
		
		description.setLineWrap(true);
//		description.setWrapStyleWord(true);
		
		Panel p = new Panel("New Ticket", 4, 4, 2, 4);
		
		p.setComponent(new JLabel("CPF: "), 0, 0);
		p.setComponent(client, 0, 1);
		
		p.setComponent(new JLabel("Empresa: "), 1, 0);
		p.setComponent(company, 1, 1);
		company.setEditable(false);
		
		p.setComponent(new JLabel("Titulo: "), 0, 2);
		p.setComponent(title, 0, 3);
		
		p.setComponent(new JLabel("Categoria: "), 1, 2);
		p.setComponent(category, 1, 3);
		category.setBackground(Color.WHITE);
		
		p.setComponent(new JLabel("Produto: "), 0, 4);
		p.setComponent(product, 0, 5);
		product.setPreferredSize(new Dimension(50, 13));
		product.setBackground(Color.WHITE);
		
		p.setComponent(new JLabel("Selecione: "), 1, 4);
		p.setComponent(pj, 1, 5);
		pj.addActionListener(new Action(this));
		
		p.setComponent(new JLabel("Descricão: "), 0, 6);
		p.setComponent(s, 0, 7, 100, 150, 2);
		description.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		p.setComponent(priority, 0, 8);
		p.setComponent(submit, 1, 8);
		submit.addActionListener(new Action(this));
		
		ui.add(p);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public void clear()
	{
		client.setText("");
		title.setText("");
		company.setText("");
		category.setSelectedIndex(0);
		product.setSelectedIndex(0);
		pj.setSelected(false);;
		description.setText("");
		priority.setSelected(false);
	}
	
	public static synchronized Ticket getInstance()
	{
		return instance != null ? instance : new Ticket(); 
	}
	
	public JCheckBox getPj()
	{
		return pj;
	}

	public JButton getSubmit() 
	{
		return submit;
	}

	public JComboBox<?> getCategory() 
	{
		return category;
	}

	public JComboBox<?> getProduct() 
	{
		return product;
	}

	public JCheckBox getPriority() 
	{
		return priority;
	}

	public JTextArea getDescription() 
	{
		return description;
	}

	public JTextField getClient() 
	{
		return client;
	}

	public JTextField getTitle() 
	{
		return title;
	}

	public JTextField getCompany() 
	{
		return company;
	}
}
