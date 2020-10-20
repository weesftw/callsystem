package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.weesftw.constraint.Status;
import net.weesftw.manager.Action;
import net.weesftw.manager.MouseAction;
import net.weesftw.model.TicketAbstractTable;
import net.weesftw.model.Button;
import net.weesftw.model.ComboBox;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.ScrollPane;
import net.weesftw.model.Table;
import net.weesftw.model.TextField;

public class TicketTable extends UI<InternalFrame>
{
	private static TicketTable instance;
	
	private TableRowSorter<TableModel> sorter;
	private TicketAbstractTable at;
	private Button btn;
	private ComboBox<String> status;
	private TextField id, title, client, company, user, date;
	private JCheckBox priority;
	
	private TicketTable() 
	{
		super(new InternalFrame("Ticket", false, true, false, true));
		
		at = new TicketAbstractTable();
		sorter = new TableRowSorter<TableModel>(at);
		status = new ComboBox<String>();
		btn = new Button("Search");
		id = new TextField(15);
		title = new TextField(15);
		client = new TextField(15);
		company = new TextField(15);
		user = new TextField(15);
		date = new TextField(15);
		priority = new JCheckBox("Priority");
		
		status.addItem("");
		
		for(Status s : Status.values())
		{
			status.addItem(s.name());
		}
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		Table t = new Table(at);
		Panel p = new Panel("Filter", 4, 4, 4, 4);
		ScrollPane s = new ScrollPane(t);
		
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
		
		p.setComponent(new Label("ID: "));
		p.setComponent(id, 1, 0);
		
		p.setComponent(new Label("Titulo: "), 2, 0);
		p.setComponent(title, 3, 0);
		
		p.setComponent(new Label("Cliente: "), 0, 1);
		p.setComponent(client, 1, 1);
		
		p.setComponent(new Label("Empresa: "), 2, 1);
		p.setComponent(company, 3, 1);
		
		p.setComponent(new Label("Usuario: "), 0, 2);
		p.setComponent(user, 1, 2);
		
		p.setComponent(new Label("Data: "), 2, 2);
		p.setComponent(date, 3, 2);
		
		p.setComponent(new Label("Status: "), 0, 3);
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

	public Button getBtn() 
	{
		return btn;
	}

	public ComboBox<?> getStatus() 
	{
		return status;
	}

	public TextField getId() 
	{
		return id;
	}

	public TextField getTitle() 
	{
		return title;
	}

	public TextField getClient() 
	{
		return client;
	}

	public TextField getCompany() 
	{
		return company;
	}

	public TextField getUser() 
	{
		return user;
	}

	public TextField getDate() 
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
