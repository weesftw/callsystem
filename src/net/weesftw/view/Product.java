package net.weesftw.view;

import javax.swing.WindowConstants;

import net.weesftw.model.Button;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.TextField;

@Deprecated
public class Product extends UI<InternalFrame>
{	
	public Product()
	{
		super(new InternalFrame("Product", false, true, false, true));
		
		Button submit = new Button("Submit");
		Panel p = new Panel("New Product", 4, 4, 4, 4);
		TextField product = new TextField(10);
		TextField item = new TextField(10);
		
		p.setComponent(new Label("Product: "));
		p.setComponent(product, 1, 0);
		
		p.setComponent(new Label("Item: "), 0, 1);
		p.setComponent(item, 1, 1);
		
		p.setComponent(submit, 1, 2);
		
		ui.add(p);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ui.setVisible(true);
	}
}
