package net.weesftw.view;

import javax.swing.WindowConstants;

import net.weesftw.model.InternalFrame;
import net.weesftw.model.Panel;

public class Sale extends UI<InternalFrame>
{
	public Sale() 
	{
		super(new InternalFrame("Sale", false, true, false, true));
		
		Panel p = new Panel("New Sale", 4, 4, 4, 4);
		
		
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ui.setVisible(true);
	}

}
