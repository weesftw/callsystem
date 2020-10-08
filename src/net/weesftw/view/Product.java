package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.TextField;
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
import net.weesftw.vo.ProviderVO;

public class Product extends UI<InternalFrame> 
{
	private Button choose, submit;
	private ComboBox<?> provider;
	private Label img;
	private TextField name, price;
	
	public Product()
	{
		super(new InternalFrame("Product", false, true, false, true));
		
		ProviderDAO pd = new ProviderDAO();
		Vector<String> v = new Vector<String>();
		
		for(ProviderVO p : pd.list())
		{
			v.add(p.getName());
		}
		
		Panel p = new Panel("Photo", 4, 4, 4, 4);
		Panel p2 = new Panel("New Product", 4, 4, 4, 4);
		
		provider = new ComboBox<String>(v);
		choose = new Button("Choose");
		submit = new Button("Submit");
		img = new Label(ImagePath.ICON, 120, 120);
		name = new TextField(15);
		price = new TextField(15);
		
		p.setComponent(img);
		
		p.setComponent(choose, 0, 1);
		choose.addActionListener(new Action(this));
		
		p2.setComponent(new Label("Name: "));
		p2.setComponent(name, 1, 0);
		
		p2.setComponent(new Label("Price: "), 0, 1);
		p2.setComponent(price, 1, 1);
		
		p2.setComponent(new Label("Provider: "), 0, 2);
		p2.setComponent(provider, 1, 2);
		
		p2.setComponent(submit, 1, 3);
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
}
