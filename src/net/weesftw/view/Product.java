package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.WindowConstants;

import net.weesftw.constraint.ImagePath;
import net.weesftw.dao.ProviderDAO;
import net.weesftw.manager.Action;
import net.weesftw.model.Button;
import net.weesftw.model.ComboBox;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextField;
import net.weesftw.vo.ProviderVO;

public class Product extends UI<InternalFrame>
{
	private Button choose, submit;
	private ComboBox<?> provider;
	private Label img;
	private TextField name, price, weight, length, width, height;
	
	public Product() 
	{
		super(new InternalFrame("Product", false, true, false, true));
		
		ProviderDAO pd = new ProviderDAO();
		Vector<String> v = new Vector<>();
		
		for(ProviderVO pv : pd.list())
		{
			v.add(pv.getName());
		}
		
		Panel p = new Panel("Photo", 4, 4, 4, 4);
		Panel p2 = new Panel("New Product", 4, 4, 4, 4);
		
		choose = new Button("Choose");
		submit = new Button("Submit");
		provider = new ComboBox<String>(v);
		img = new Label(ImagePath.ICON, 120, 120);
		name = new TextField(15);
		price = new TextField(15);
		weight = new TextField(5);
		length = new TextField(5);
		width = new TextField(5);
		height = new TextField(5);
		
		p.setComponent(img);
		
		p.setComponent(choose, 0, 1);
		choose.addActionListener(new Action(this));
		
		p2.setComponent(new Label("Name : "));
		p2.setComponent(name, 1, 0);
				
		p2.setComponent(new Label("Weight (kg): "), 2, 0);
		p2.setComponent(weight, 3, 0);
		
		p2.setComponent(new Label("Price: "), 0, 1);
		p2.setComponent(price, 1, 1);
		
		p2.setComponent(new Label("Length (cm): "), 2, 1);
		p2.setComponent(length, 3, 1);
		
		p2.setComponent(new Label("Provider: "), 0, 2);
		p2.setComponent(provider, 1, 2);
		provider.setBackground(Color.WHITE);
		
		p2.setComponent(new Label("Width (cm): "), 2, 2);
		p2.setComponent(width, 3, 2);
		
		p2.setComponent(new Label("Height (cm): "), 2, 3);
		p2.setComponent(height, 3, 3);
		
		p2.setComponent(submit, 3, 4);
		submit.addActionListener(new Action(this));
		
		ui.add(p, BorderLayout.WEST);
		ui.add(p2, BorderLayout.EAST);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ui.setVisible(true);
	}

	public Button getChoose() 
	{
		return choose;
	}

	public Button getSubmit()
	{
		return submit;
	}

	public ComboBox<?> getProvider() 
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
