package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.weesftw.constraint.Status;
import net.weesftw.manager.Action;
import net.weesftw.manager.MouseAction;
import net.weesftw.model.Panel;
import net.weesftw.model.TicketAbstractTable;

public class TicketTable extends UI<JInternalFrame>
{
	private static TicketTable instance;
	
	private TableRowSorter<TableModel> sorter;
	private TicketAbstractTable at;
	private JButton btn;
	private JComboBox<String> status;
	private JTextField id, title, client, company, user, date;
	private JCheckBox priority;
	
	private TicketTable() 
	{
		super(new JInternalFrame("Ticket", false, true, false, true));
		
		at = new TicketAbstractTable();
		sorter = new TableRowSorter<TableModel>(at);
		status = new JComboBox<String>();
		btn = new JButton("Search");
		id = new JTextField(15);
		title = new JTextField(15);
		client = new JTextField(15);
		company = new JTextField(15);
		user = new JTextField(15);
		date = new JTextField(15);
		priority = new JCheckBox("Priority");
		
		status.addItem("");
		
		for(Status s : Status.values())
		{
			status.addItem(s.name());
		}
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		JTable t = new JTable(at);
		Panel p = new Panel("Filter", 4, 4, 4, 4);
		JScrollPane s = new JScrollPane(t);
		
		r.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		
		t.setRowSorter(sorter);
		t.setBackground(Color.WHITE);
		t.addMouseListener(new MouseAction(this));
		t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		t.getTableHeader().setReorderingAllowed(false);
		t.getColumnModel().getColumn(0).setPreferredWidth(50);
		t.getColumnModel().getColumn(1).setPreferredWidth(120);
		t.getColumnModel().getColumn(2).setPreferredWidth(100);
		t.getColumnModel().getColumn(3).setPreferredWidth(100);
		t.getColumnModel().getColumn(6).setPreferredWidth(70);
		
		for(int i = 0; i < t.getColumnCount(); i++)
		{
			if(i != 1 || i != 2 || i != 3 || i != 4)
			{
				t.getColumnModel().getColumn(i).setCellRenderer(r);				
			}
		}
		
		p.setComponent(new JLabel("ID: "));
		p.setComponent(id, 1, 0);
		
		p.setComponent(new JLabel("Titulo: "), 2, 0);
		p.setComponent(title, 3, 0);
		
		p.setComponent(new JLabel("Cliente: "), 0, 1);
		p.setComponent(client, 1, 1);
		
		p.setComponent(new JLabel("Empresa: "), 2, 1);
		p.setComponent(company, 3, 1);
		
		p.setComponent(new JLabel("Usuario: "), 0, 2);
		p.setComponent(user, 1, 2);
		
		p.setComponent(new JLabel("Data: "), 2, 2);
		p.setComponent(date, 3, 2);
		
		p.setComponent(new JLabel("Status: "), 0, 3);
		p.setComponent(status, 1, 3);
		status.setBackground(Color.WHITE);
		
		p.setComponent(priority, 3, 3);
		
		p.setComponent(btn, 3, 4);
		btn.addActionListener(new Action(this));
		
		ui.add(p, BorderLayout.NORTH);
		ui.add(s);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static TicketTable getInstance()
	{
		return instance != null ? instance : new TicketTable(); 
	}

	public TicketAbstractTable getAt() 
	{
		return at;
	}

	public JButton getBtn() 
	{
		return btn;
	}

	public JComboBox<?> getStatus() 
	{
		return status;
	}

	public JTextField getId() 
	{
		return id;
	}

	public JTextField getTitle() 
	{
		return title;
	}

	public JTextField getClient() 
	{
		return client;
	}

	public JTextField getCompany() 
	{
		return company;
	}

	public JTextField getUser() 
	{
		return user;
	}

	public JTextField getDate() 
	{
		return date;
	}

	public JCheckBox getPriority() 
	{
		return priority;
	}

	public TableRowSorter<TableModel> getSorter() 
	{
		return sorter;
	}
}
