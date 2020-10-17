package net.weesftw.view;

import java.awt.BorderLayout;

import javax.swing.WindowConstants;

import net.weesftw.constraint.ImagePath;
import net.weesftw.manager.Action;
import net.weesftw.model.Button;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextField;

public class Product extends UI<InternalFrame>
{
	private static Product instance;
	
	private TextField provider, name, price, weight, length, width, height;
	private Button choose, submit;
	private Label img;
	
	private Product() 
	{
		super(new InternalFrame("Product", false, true, false, true));
		
		Panel p = new Panel("Photo", 4, 4, 4, 4);
		Panel p2 = new Panel("New Product", 4, 4, 4, 4);
		
		choose = new Button("Choose");
		submit = new Button("Submit");
		img = new Label();
		name = new TextField(15);
		price = new TextField(15);
		provider = new TextField(15);
		weight = new TextField(5);
		length = new TextField(5);
		width = new TextField(5);
		height = new TextField(5);
		
		p.setComponent(img);
		img.loadImage(ImagePath.ICON, 120, 120);
		
		p.setComponent(choose, 0, 1);
		choose.addActionListener(new Action(this));
		
		p2.setComponent(new Label("Nome : "));
		p2.setComponent(name, 1, 0);
				
		p2.setComponent(new Label("Peso (kg): "), 2, 0);
		p2.setComponent(weight, 3, 0);
		
		p2.setComponent(new Label("Preco: "), 0, 1);
		p2.setComponent(price, 1, 1);
		
		p2.setComponent(new Label("Comprimento (cm): "), 2, 1);
		p2.setComponent(length, 3, 1);
		
		p2.setComponent(new Label("Fornecedor: "), 0, 2);
		p2.setComponent(provider, 1, 2);
		
		p2.setComponent(new Label("Largura (cm): "), 2, 2);
		p2.setComponent(width, 3, 2);
		
		p2.setComponent(new Label("Altura (cm): "), 2, 3);
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
		img.loadImage(ImagePath.ICON, 120, 120);
	}

	public Button getChoose() 
	{
		return choose;
	}

	public Button getSubmit()
	{
		return submit;
	}

	public TextField getProvider() 
	{
		return provider;
	}

	public Label getImg()
	{
		return img;
	}

	public TextField getName() 
	{
		return name;
	}

	public TextField getPrice() 
	{
		return price;
	}
	
	public TextField getWeight() 
	{
		return weight;
	}

	public TextField getLength() 
	{
		return length;
	}

	public TextField getWidth() 
	{
		return width;
	}

	public TextField getHeight() 
	{
		return height;
	}
}
