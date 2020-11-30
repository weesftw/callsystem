package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import net.weesftw.constraint.ImagePath;
import net.weesftw.manager.Action;
import net.weesftw.model.Icon;
import net.weesftw.model.Panel;

public class Product extends UI<JInternalFrame>
{
	private static Product instance;
	
	private JTextField provider, name, price, weight, length, width, height;
	private Button choose, submit;
	private Icon img;
	
	private Product() 
	{
		super(new JInternalFrame("Product", false, true, false, true));
		
		Panel p = new Panel("Photo", 4, 4, 4, 4);
		Panel p2 = new Panel("New Product", 4, 4, 4, 4);
		
		choose = new Button("Choose");
		submit = new Button("Submit");
		img = new Icon();
		name = new JTextField(15);
		price = new JTextField(15);
		provider = new JTextField(15);
		weight = new JTextField(5);
		length = new JTextField(5);
		width = new JTextField(5);
		height = new JTextField(5);
		
		img.setPath(ImagePath.ICON);
		p.setComponent(img);
		
		p.setComponent(choose, 0, 1);
		choose.addActionListener(new Action(this));
		
		p2.setComponent(new JLabel("Nome : "));
		p2.setComponent(name, 1, 0);
				
		p2.setComponent(new JLabel("Peso (kg): "), 2, 0);
		p2.setComponent(weight, 3, 0);
		
		p2.setComponent(new JLabel("Preco: "), 0, 1);
		p2.setComponent(price, 1, 1);
		
		p2.setComponent(new JLabel("Comprimento (cm): "), 2, 1);
		p2.setComponent(length, 3, 1);
		
		p2.setComponent(new JLabel("Fornecedor: "), 0, 2);
		p2.setComponent(provider, 1, 2);
		
		p2.setComponent(new JLabel("Largura (cm): "), 2, 2);
		p2.setComponent(width, 3, 2);
		
		p2.setComponent(new JLabel("Altura (cm): "), 2, 3);
		p2.setComponent(height, 3, 3);
		
		p2.setComponent(submit, 3, 4);
		submit.addActionListener(new Action(this));
		
		ui.add(p, BorderLayout.WEST);
		ui.add(p2, BorderLayout.EAST);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static synchronized Product getInstance()
	{		
		return instance != null ? instance : new Product(); 
	}
	
	public void clear()
	{
		name.setText("");
		price.setText("");
		weight.setText("");
		length.setText("");
		width.setText("");
		height.setText("");
		provider.setText("");
		img.setPath(ImagePath.ICON);
	}

	public Button getChoose() 
	{
		return choose;
	}

	public Button getSubmit()
	{
		return submit;
	}

	public JTextField getProvider() 
	{
		return provider;
	}

	public Icon getImg()
	{
		return img;
	}

	public JTextField getName() 
	{
		return name;
	}

	public JTextField getPrice() 
	{
		return price;
	}
	
	public JTextField getWeight() 
	{
		return weight;
	}

	public JTextField getLength() 
	{
		return length;
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
