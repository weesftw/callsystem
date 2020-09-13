package net.weesftw.view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ticket extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	
	private JTextField cnpj, name, owner, zipCode;
	
	public Ticket()
	{
		super("Company");
		
		Container c = getContentPane();

		c.add(main());
		
		pack();
		setIconifiable(true);
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private JPanel main()
	{
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel p = new JPanel(new GridBagLayout());
		JButton submit = new JButton("Submit");
		
		p.setBorder(BorderFactory.createTitledBorder("New Ticket"));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		p.add(component(), gbc);
		
		return p;
	}
	
	
	private JPanel component()
	{
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel p = new JPanel(new GridBagLayout());
		
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		return p;
	}
}
