package net.weesftw.view;

import java.awt.Color;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import net.weesftw.manager.Action;
import net.weesftw.model.Dialog;
import net.weesftw.model.Panel;
import net.weesftw.vo.CartVO;
import net.weesftw.vo.SellVO;
import net.weesftw.vo.SellVO.Status;

public class SaleOpen extends UI<JDialog> 
{	
	private JButton submit;
	private JComboBox<?> status;
	private JTextArea observation, product;
	private JTextField id, client, by, date;
	private SellVO s;
	
	public SaleOpen(SellVO s) 
	{
		super(new Dialog("Sale: " + s.getId() + " of " + s.getPeople().getFirstName() + " " + s.getPeople().getLastName(), true));
		
		this.s = s;
		
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
		Panel p = new Panel("Cart Detail", 4, 4, 4, 4);
		
		id = new JTextField(15);
		id.setText(s.getId());
		id.setEditable(false);
		
		client = new JTextField(15);
		client.setText(s.getPeople().getFirstName() + " " + s.getPeople().getLastName());
		client.setEditable(false);
		
		by = new JTextField(15);
		by.setText(s.getBy().getUsername());
		by.setEditable(false);
		
		date = new JTextField(15);
		date.setText(d.format(s.getTimestamp()));
		date.setEditable(false);
		
		status = new JComboBox<Status>(Status.values());
		status.setBackground(Color.WHITE);
		status.setSelectedItem(s.getStatus());
		status.setEnabled((status.getSelectedItem() != Status.COMPLETE) ? true : false);
		
		observation = new JTextArea(1, 1);
		observation.setLineWrap(true);
		observation.setText(s.getObservation());
		observation.setEditable((status.getSelectedItem() != Status.COMPLETE) ? true : false);
		observation.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		product = new JTextArea(1, 1);		
		product.setText(getArgs());
		product.setEditable(false);
		product.setLineWrap(true);
		product.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		submit = new JButton("Submit");
		submit.setEnabled(status.getSelectedItem() != Status.COMPLETE ? true : false);
		submit.addActionListener(new Action(this));
		
		JScrollPane sp = new JScrollPane(observation);
		JScrollPane sp2 = new JScrollPane(product);
		
		p.setComponent(new JLabel("ID: "));
		p.setComponent(id, 1, 0);
		
		p.setComponent(new JLabel("Title: "), 2, 0);
		p.setComponent(client, 3, 0);
		
		p.setComponent(new JLabel("Date: "), 0, 1);
		p.setComponent(date, 1, 1);
		
		p.setComponent(new JLabel("By: "), 2, 1);
		p.setComponent(by, 3, 1);
		
		p.setComponent(new JLabel("Status: "), 0, 2);
		p.setComponent(status, 1, 2);
		
		p.setComponent(new JLabel("Observation: "), 0, 3);
		p.setComponent(sp, 0, 4, 100, 150, 2);
		
		p.setComponent(new JLabel("Items: "), 3, 3);
		p.setComponent(sp2, 3, 4, 100, 150, 2);
		
		p.setComponent(submit, 3, 5);
		
		ui.add(p);
		
		ui.pack();
		ui.setModal(true);
		ui.setLocationRelativeTo(null);
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ui.setVisible(true);
	}
	
	private String getArgs()
	{
		String args = "";
		float price = 0.00F;
		float freight = 0.00F;
		
		for(CartVO ca : CartVO.listAll)
		{
			
			if(ca.getId().equals(s.getId()))
			{
				price += Float.parseFloat(ca.getProduct().getPrice());
				freight += Float.parseFloat(ca.getProduct().getWeight()) + Float.parseFloat(ca.getProduct().getProvider().getFreight());
				
				args += (ca.getAmount() + "x " + ca.getProduct().getName() + " (" + ca.getProduct().getPrice() + ")" + "\n");
			}
		}
		
		args += "\n\nTotal: R$" + String.format("%.2f", price) + "\n";
		args += "Frete: R$ " + String.format("%.2f", freight);
		
		return args;
	}
	
	public JButton getSubmit() 
	{
		return submit;
	}

	public JTextField getId() 
	{
		return id;
	}

	public JComboBox<?> getStatus() 
	{
		return status;
	}

	public JTextArea getObservation() 
	{
		return observation;
	}

	public JTextField getClient() 
	{
		return client;
	}

	public JTextField getBy() 
	{
		return by;
	}

	public JTextField getDate()
	{
		return date;
	}

	public SellVO getS() 
	{
		return s;
	}	
}
