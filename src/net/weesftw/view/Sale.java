package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import net.weesftw.constraint.ImagePath;
import net.weesftw.manager.Action;
import net.weesftw.model.Button;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.SellAbstractTable;
import net.weesftw.model.Table;
import net.weesftw.model.TextArea;
import net.weesftw.model.TextField;
import net.weesftw.vo.CartVO;

public class Sale extends UI<InternalFrame>
{
	private static Sale instance;
	
	private SellAbstractTable at;
	private Button add, submit;
	private JCheckBox c;
	private Label cnpj, img, phoneNumber;
	private TextField cpf, name, phone, zipCode, address, neighborhood, city, state, id, amount, product, price;
	private TextArea observation;
	
	private Sale() 
	{
		super(new InternalFrame("Sale", false, true, false, true));
		
		at = new SellAbstractTable();
		add = new Button("Insert");
		submit = new Button("Checkout");
		c = new JCheckBox("CNPJ");
		phoneNumber = new Label("Phone: ");
		cnpj = new Label("CPF: ");
		img = new Label();
		observation = new TextArea();
		cpf = new TextField(12);
		name = new TextField(12);
		zipCode = new TextField(12);
		address = new TextField(12);
		city = new TextField(12);
		state = new TextField(12);
		id = new TextField(12);
		amount = new TextField(5);
		product = new TextField(12);
		price = new TextField(5);
		phone = new TextField(12);
		neighborhood = new TextField(12);
		
		Table t = new Table(at);
		JScrollPane s = new JScrollPane(t);
		Panel p = new Panel("Photo", 4, 4, 4, 4);
		Panel p2 = new Panel("Client", 4, 4, 4, 4);
		Panel p3 = new Panel("Product", 4, 4, 4, 4);
		
		s.setPreferredSize(new Dimension(0, 180));
		
		p.setComponent(img);
		img.loadImage(ImagePath.ICON, 120, 120);
		
		p2.setComponent(cnpj);
		p2.setComponent(cpf, 1, 0);
		cpf.addActionListener(new Action(this));
		
		p2.setComponent(c, 2, 0);
		c.addActionListener(new Action(this));
		
		p2.setComponent(new Label("Nome: "), 0, 1);
		p2.setComponent(name, 1, 1);
		name.setEditable(false);
		
		p2.setComponent(phoneNumber, 2, 1);
		p2.setComponent(phone, 3, 1);
		phone.setEditable(false);
		
		p2.setComponent(new Label("CEP: "), 0, 2);
		p2.setComponent(zipCode, 1, 2);
		zipCode.setEditable(false);
		
		p2.setComponent(new Label("Rua: "), 2, 2);
		p2.setComponent(address, 3, 2);
		address.setEditable(false);
		
		p2.setComponent(new Label("Bairro: "), 0, 3);
		p2.setComponent(neighborhood, 1, 3);
		neighborhood.setEditable(false);
		
		p2.setComponent(new Label("Cidade: "), 2, 3);
		p2.setComponent(city, 3, 3);
		city.setEditable(false);
		
		p2.setComponent(new Label("Estado: "), 0, 4);
		p2.setComponent(state, 1, 4);
		state.setEditable(false);
		
		p3.setComponent(new Label("ID: "), 0, 0);
		p3.setComponent(id, 1, 0);
		id.addActionListener(new Action(this));
		
		p3.setComponent(new Label("Produto: "), 0, 1);
		p3.setComponent(product, 1, 1);
		product.setBackground(Color.WHITE);
		product.setEditable(false);
		
		p3.setComponent(new Label("Quantidade: "), 2, 0);
		p3.setComponent(amount, 3, 0);
		amount.setText("1");
		
		p3.setComponent(new Label("Preco: "), 2, 1);
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
	
	public static Sale getInstance()
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
		price.setText("R$: ");
		phone.setText("");
		neighborhood.setText("");
		c.setSelected(false);
		CartVO.list.clear();
	}
	
	public void clearAfterInsert()
	{
		id.setText("");
		amount.setText("1");
		product.setText("");
		price.setText("R$: ");
		img.loadImage(ImagePath.ICON, 100, 100);
	}

	public SellAbstractTable getAt() 
	{
		return at;
	}

	public Button getAdd() 
	{
		return add;
	}

	public Button getSubmit() 
	{
		return submit;
	}

	public JCheckBox getC() 
	{
		return c;
	}

	public Label getCnpj() 
	{
		return cnpj;
	}

	public Label getImg() 
	{
		return img;
	}

	public TextField getCpf() 
	{
		return cpf;
	}

	public TextField getName()
	{
		return name;
	}

	public TextField getPhone() 
	{
		return phone;
	}

	public TextField getZipCode() 
	{
		return zipCode;
	}

	public TextField getAddress() 
	{
		return address;
	}

	public TextField getCity() 
	{
		return city;
	}

	public TextField getState() 
	{
		return state;
	}

	public TextField getId() 
	{
		return id;
	}

	public TextField getAmount() 
	{
		return amount;
	}

	public TextField getProduct() 
	
	{
		return product;
	}

	public TextField getPrice() 
	{
		return price;
	}

	public TextField getNeighborhood() 
	{
		return neighborhood;
	}

	public Label getPhoneNumber() 
	{
		return phoneNumber;
	}

	public TextArea getObservation() 
	{
		return observation;
	}
}
