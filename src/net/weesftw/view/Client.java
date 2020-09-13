package net.weesftw.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.weesftw.constraint.Department;

public class Client extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	
	private JComboBox<Department> department = new JComboBox<>(Department.values());
	private JTextField firstName, lastName, zipCode, email, phone, cpf;
	private JComboBox<String> country = new JComboBox<String>();
	private JLabel img;
	
	public Client()
	{
		super("Client");
		
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
		JButton choose = new JButton("Choose");
		JButton submit = new JButton("Submit");
		
		p.setBorder(BorderFactory.createTitledBorder("New Client"));
		
		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		p.add(photo(), gbc);
		
		gbc.gridx++;
		
		p.add(component(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.insets = new Insets(4, 4, 4, 4);
		
		p.add(choose, gbc);
		choose.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser jfc = new JFileChooser();
				int r = jfc.showOpenDialog(null);
				
				if(r == JFileChooser.APPROVE_OPTION)
				{
					File f = jfc.getSelectedFile();
					
					img.setIcon(new ImageIcon(new ImageIcon(f.getPath()).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
					p.revalidate();
					p.repaint();
				}
			}
		});
		
		gbc.gridx++;
		gbc.ipadx = 120;
		
		p.add(submit, gbc);
		
		return p;
	}
	
	private JPanel photo()
	{
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(15, 15, 5, 15);
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		p.add(img = new JLabel(), gbc);
		img.setIcon(new ImageIcon(new ImageIcon("img/icon.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
		
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
		
		p.add(new JLabel("CPF: "), gbc);
		
		gbc.ipadx = 120;
		gbc.gridx++;
		
		p.add(cpf = new JTextField(), gbc);
		cpf.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{				
				if(cpf.getText().equalsIgnoreCase("a"))
				{
					cpf.setText("");
					JOptionPane.showMessageDialog(null, "CPF already exists in database.");
				}
			}
		});
		
		cpf.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				if(cpf.getText().length() >= 11)
				{
					e.consume();
				}
			}
		});
		
		gbc.ipadx = 0;
		gbc.gridx++;
		
		p.add(new JLabel("Zip Code: "), gbc);
		
		gbc.ipadx = 120;
		gbc.gridx++;
		
		p.add(zipCode = new JTextField(), gbc);
		
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		
		p.add(new JLabel("Department: "), gbc);
		
		gbc.ipadx = 52;
		gbc.gridx++;
		
		p.add(department = new JComboBox<Department>(), gbc);
		department.setBackground(Color.WHITE);
		department.setPreferredSize(new Dimension(73, 27));
		
		gbc.ipadx = 0;
		gbc.gridx++;
		
		p.add(new JLabel("Country: "), gbc);
		
		gbc.ipadx = 52;
		gbc.gridx++;
				
		p.add(country = new JComboBox<String>(), gbc);
		country.setBackground(Color.WHITE);
		country.setPreferredSize(new Dimension(73, 27));
		
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		
		p.add(new JLabel("First Name: "), gbc);
		
		gbc.ipadx = 120;
		gbc.gridx++;
		
		p.add(firstName = new JTextField(), gbc);
		
		gbc.ipadx = 0;
		gbc.gridx++;
		
		p.add(new JLabel("Last Name: "), gbc);
		
		gbc.ipadx = 120;
		gbc.gridx++;
		
		p.add(lastName = new JTextField(), gbc);
		
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		
		p.add(new JLabel("Phone: "), gbc);
		
		gbc.ipadx = 120;
		gbc.gridx++;
		
		p.add(phone = new JTextField(), gbc);
		
		gbc.ipadx = 0;
		gbc.gridx++;
		
		p.add(new JLabel("Email: "), gbc);
		
		gbc.ipadx = 120;
		gbc.gridx++;
		
		p.add(email = new JTextField(), gbc);
		
		return p;
	}
}
