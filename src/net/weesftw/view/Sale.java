package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import net.weesftw.constraint.ImagePath;
import net.weesftw.manager.Action;
import net.weesftw.model.CartAbstractTable;
import net.weesftw.model.Icon;
import net.weesftw.model.Panel;
import net.weesftw.vo.CartVO;

public class Sale extends UI<JInternalFrame>
{
	private static Sale instance;
	
	private CartAbstractTable at;
	private JButton add, submit;
	private JCheckBox c;
	private JLabel cnpj, phoneNumber;
	private JTextField cpf, name, phone, zipCode, address, neighborhood, city, state, id, amount, product, price;
	private JTextArea observation;
	private Icon img;
	
	private Sale() 
	{
		super(new JInternalFrame("Sale", false, true, false, true));
		
		at = new CartAbstractTable();
		add = new JButton("Insert");
		submit = new JButton("Checkout");
		c = new JCheckBox("CNPJ");
		phoneNumber = new JLabel("Phone: ");
		cnpj = new JLabel("CPF: ");
		img = new Icon();
		observation = new JTextArea();
		cpf = new JTextField(12);
		name = new JTextField(12);
		zipCode = new JTextField(12);
		address = new JTextField(12);
		city = new JTextField(12);
		state = new JTextField(12);
		id = new JTextField(12);
		amount = new JTextField(5);
		product = new JTextField(12);
		price = new JTextField(5);
		phone = new JTextField(12);
		neighborhood = new JTextField(12);
		
		JTable t = new JTable(at);
		JScrollPane s = new JScrollPane(t);
		Panel p = new Panel("Photo", 4, 4, 4, 4);
		Panel p2 = new Panel("Client", 4, 4, 4, 4);
		Panel p3 = new Panel("Product", 4, 4, 4, 4);
		
		s.setPreferredSize(new Dimension(0, 180));
		
		img.setPath(ImagePath.ICON);
		p.setComponent(img);
		
		p2.setComponent(cnpj);
		p2.setComponent(cpf, 1, 0);
		cpf.addActionListener(new Action(this));
		
		p2.setComponent(c, 2, 0);
		c.setEnabled(false);
		c.addActionListener(new Action(this));
		
		p2.setComponent(new JLabel("Nome: "), 0, 1);
		p2.setComponent(name, 1, 1);
		name.setEditable(false);
		
		p2.setComponent(phoneNumber, 2, 1);
		p2.setComponent(phone, 3, 1);
		phone.setEditable(false);
		
		p2.setComponent(new JLabel("CEP: "), 0, 2);
		p2.setComponent(zipCode, 1, 2);
		zipCode.setEditable(false);
		
		p2.setComponent(new JLabel("Rua: "), 2, 2);
		p2.setComponent(address, 3, 2);
		address.setEditable(false);
		
		p2.setComponent(new JLabel("Bairro: "), 0, 3);
		p2.setComponent(neighborhood, 1, 3);
		neighborhood.setEditable(false);
		
		p2.setComponent(new JLabel("Cidade: "), 2, 3);
		p2.setComponent(city, 3, 3);
		city.setEditable(false);
		
		p2.setComponent(new JLabel("Estado: "), 0, 4);
		p2.setComponent(state, 1, 4);
		state.setEditable(false);
		
		p3.setComponent(new JLabel("ID: "), 0, 0);
		p3.setComponent(id, 1, 0);
		id.addActionListener(new Action(this));
		
		p3.setComponent(new JLabel("Produto: "), 0, 1);
		p3.setComponent(product, 1, 1);
		product.setBackground(Color.WHITE);
		product.setEditable(false);
		
		p3.setComponent(new JLabel("Quantidade: "), 2, 0);
		p3.setComponent(amount, 3, 0);
		amount.setText("1");
		
		p3.setComponent(new JLabel("Preco: "), 2, 1);
		p3.setComponent(price, 3, 1);
		price.setBackground(Color.WHITE);
		price.setEditable(false);
		
		p3.setComponent(add, 1, 3);
		add.addActionListener(new Action(this));
		
		p3.setComponent(submit, 3, 3);
		submit.addActionListener(new Action(this));
		
		ui.add(p, BorderLayout.WEST);
		ui.add(p2, BorderLayout.NORTH);
		ui.add(p3, BorderLayout.CENTER);
		ui.add(s, BorderLayout.SOUTH);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static synchronized Sale getInstance()
	{
		return instance != null ? instance : new Sale(); 
	}
	
	public void clear()
	{
		observation.setText("");
		cpf.setText("");
		name.setText("");
		zipCode.setText("");
		address.setText("");
		city.setText("");
		state.setText("");
		id.setText("");
		amount.setText("1");
		product.setText("");
		price.setText("");
		phone.setText("");
		neighborhood.setText("");
		c.setSelected(false);
		cpf.setEditable(true);
		CartVO.list.clear();
	}
	
	public void clearAfterInsert()
	{
		id.setText("");
		amount.setText("1");
		product.setText("");
		price.setText("R$: ");
		img.setPath(ImagePath.ICON);
	}

	public CartAbstractTable getAt() 
	{
		return at;
	}

	public JButton getAdd() 
	{
		return add;
	}

	public JButton getSubmit() 
	{
		return submit;
	}

	public JCheckBox getC() 
	{
		return c;
	}

	public JLabel getCnpj() 
	{
		return cnpj;
	}

	public Icon getImg() 
	{
		return img;
	}

	public JTextField getCpf() 
	{
		return cpf;
	}

	public JTextField getName()
	{
		return name;
	}

	public JTextField getPhone() 
	{
		return phone;
	}

	public JTextField getZipCode() 
	{
		return zipCode;
	}

	public JTextField getAddress() 
	{
		return address;
	}

	public JTextField getCity() 
	{
		return city;
	}

	public JTextField getState() 
	{
		return state;
	}

	public JTextField getId() 
	{
		return id;
	}

	public JTextField getAmount() 
	{
		return amount;
	}

	public JTextField getProduct() 
	
	{
		return product;
	}

	public JTextField getPrice() 
	{
		return price;
	}

	public JTextField getNeighborhood() 
	{
		return neighborhood;
	}

	public JLabel getPhoneNumber() 
	{
		return phoneNumber;
	}

	public JTextArea getObservation() 
	{
		return observation;
	}
}
