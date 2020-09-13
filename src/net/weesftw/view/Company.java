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

public class Company extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	
	private JTextField cnpj, name, owner, zipCode;
	
	public Company()
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
		
		p.setBorder(BorderFactory.createTitledBorder("New Company"));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		p.add(component(), gbc);
		
		gbc.ipadx = 60;
		gbc.gridy++;
		gbc.insets = new Insets(15, 15, 5, 15);
		
		p.add(submit, gbc);
		
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
		
		p.add(new JLabel("CNPJ: "), gbc);
		
		gbc.ipadx = 120;
		gbc.gridx++;
		
		p.add(cnpj = new JTextField(), gbc);
		
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		
		p.add(new JLabel("Name: "), gbc);
		
		gbc.ipadx = 120;
		gbc.gridx++;
		
		p.add(name = new JTextField(), gbc);
		
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		
		p.add(new JLabel("Owner: "), gbc);
		
		gbc.ipadx = 120;
		gbc.gridx++;
		
		p.add(owner = new JTextField(), gbc);
		
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		
		p.add(new JLabel("Zip Code: "), gbc);
		
		gbc.ipadx = 120;
		gbc.gridx++;
		
		p.add(zipCode = new JTextField(), gbc);
		
		return p;
	}
}
