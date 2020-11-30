package net.weesftw.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import net.weesftw.manager.Action;
import net.weesftw.model.Dialog;
import net.weesftw.model.Panel;
import net.weesftw.vo.ProductVO;

public class ProductOpen extends UI<JDialog> 
{
	private JButton submit;
	private JTextField id, name, weight, price, length, provider, width, height;
	
	public ProductOpen(ProductVO p) 
	{
		super(new Dialog("Product: " + p.getName(), true));
		
		Panel p2 = new Panel("Product Details", 4, 4, 4, 4);
		submit = new JButton("Update");
		id = new JTextField(15);
		name = new JTextField(15);
		weight = new JTextField(5);
		price = new JTextField(15);
		width = new JTextField(5);
		length = new JTextField(5);
		provider = new JTextField(15);
		height = new JTextField(5);
		
		p2.setComponent(new JLabel("ID: "));
		p2.setComponent(id, 0, 1);
		id.setText(p.getId());
		id.setEditable(false);
		
		p2.setComponent(new JLabel("Nome : "), 0, 2);
		p2.setComponent(name, 0, 3);
		name.setText(p.getName());
				
		p2.setComponent(new JLabel("Preco: "), 0, 4);
		p2.setComponent(price, 0, 5);
		price.setText(p.getPrice());
		
		p2.setComponent(new JLabel("Fornecedor: "), 0, 6);
		p2.setComponent(provider, 0, 7);
		provider.setText(p.getProvider().getCnpj());
		
		p2.setComponent(new JLabel("Peso (kg): "), 2, 0);
		p2.setComponent(weight, 3, 0);
		weight.setText(p.getWeight());
		
		p2.setComponent(new JLabel("Comprimento (cm): "), 2, 1);
		p2.setComponent(length, 3, 1);
		length.setText(p.getLength());
		
		p2.setComponent(new JLabel("Largura (cm): "), 2, 2);
		p2.setComponent(width, 3, 2);
		width.setText(p.getWidth());
		
		p2.setComponent(new JLabel("Altura (cm): "), 2, 3);
		p2.setComponent(height, 3, 3);
		height.setText(p.getHeight());
		
		p2.setComponent(submit, 3, 7);
		submit.addActionListener(new Action(this));
		
		ui.add(p2);
		
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

	public JTextField getName() 
	{
		return name;
	}

	public JTextField getWeight() 
	{
		return weight;
	}

	public JTextField getPrice() 
	{
		return price;
	}

	public JTextField getLength()
	{
		return length;
	}

	public JTextField getProvider() 
	{
		return provider;
	}

	public JTextField getWidth() 
	{
		return width;
	}

	public JTextField getHeight() 
	{
		return height;
	}
}
