package net.weesftw.view;

import javax.swing.WindowConstants;

import net.weesftw.manager.Action;
import net.weesftw.model.Button;
import net.weesftw.model.Dialog;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextField;
import net.weesftw.vo.ProductVO;

public class ProductOpen extends UI<Dialog> 
{
	private Button submit;
	private TextField id, name, weight, price, length, provider, width, height;
	
	public ProductOpen(ProductVO p) 
	{
		super(new Dialog("Product: " + p.getName(), true));
		
		Panel p2 = new Panel("Product Details", 4, 4, 4, 4);
		submit = new Button("Update");
		id = new TextField(15);
		name = new TextField(15);
		weight = new TextField(5);
		price = new TextField(15);
		width = new TextField(5);
		length = new TextField(5);
		provider = new TextField(15);
		height = new TextField(5);
		
		p2.setComponent(new Label("ID: "));
		p2.setComponent(id, 0, 1);
		id.setText(p.getId());
		id.setEditable(false);
		
		p2.setComponent(new Label("Nome : "), 0, 2);
		p2.setComponent(name, 0, 3);
		name.setText(p.getName());
				
		p2.setComponent(new Label("Preco: "), 0, 4);
		p2.setComponent(price, 0, 5);
		price.setText(p.getPrice());
		
		p2.setComponent(new Label("Fornecedor: "), 0, 6);
		p2.setComponent(provider, 0, 7);
		provider.setText(p.getProvider().getCnpj());
		
		p2.setComponent(new Label("Peso (kg): "), 2, 0);
		p2.setComponent(weight, 3, 0);
		weight.setText(p.getWeight());		
		
		
		p2.setComponent(new Label("Comprimento (cm): "), 2, 1);
		p2.setComponent(length, 3, 1);
		length.setText(p.getLength());
		
		p2.setComponent(new Label("Largura (cm): "), 2, 2);
		p2.setComponent(width, 3, 2);
		width.setText(p.getWidth());
		
		p2.setComponent(new Label("Altura (cm): "), 2, 3);
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

	public Button getSubmit()
	{
		return submit;
	}

	public TextField getId() 
	{
		return id;
	}

	public TextField getName() 
	{
		return name;
	}

	public TextField getWeight() 
	{
		return weight;
	}

	public TextField getPrice() 
	{
		return price;
	}

	public TextField getLength()
	{
		return length;
	}

	public TextField getProvider() 
	{
		return provider;
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
