package net.weesftw.view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

public class Login extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private JTextField user;
	private JPasswordField passwd;
	
	public Login()
	{
		GridBagConstraints gbc = new GridBagConstraints();
		Container c = getContentPane();
		
		c.setLayout(new GridBagLayout());
		
		setSize(250, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.33;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
		
		c.add(signIn(), gbc);
		
		setVisible(true);
	}
	
	private JPanel signIn()
	{		
		JButton btn = new JButton("Sign In");
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		p.setBorder(new CompoundBorder(new TitledBorder("Sign In"), null));
		
		gbc.gridwidth = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.insets = new Insets(5, 5, 5, 5);
        
		p.add(new JLabel("Username: "), gbc);
		
		gbc.gridy++;
		
		p.add(new JLabel("Password: "), gbc);
		
		gbc.gridx++;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
	        
		p.add(user = new JTextField(), gbc);
		
		gbc.gridy++;
		
		p.add(user = new JPasswordField(), gbc);
		
		gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
		
		p.add(btn, gbc);
		
		btn.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new Main();
				dispose();
			}
		});
		
		return p;
	}
	
	public JTextField getUser()
	{
		return user;
	}
	
	public JPasswordField getPasswd()
	{
		return passwd;
	}
}