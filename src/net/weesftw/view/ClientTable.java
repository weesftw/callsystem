package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.weesftw.constraint.Gender;
import net.weesftw.manager.Action;
import net.weesftw.manager.MouseAction;
import net.weesftw.model.Button;
import net.weesftw.model.ClientAbstractTable;
import net.weesftw.model.ComboBox;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.ScrollPane;
import net.weesftw.model.Table;
import net.weesftw.model.TextField;

public class ClientTable extends UI<InternalFrame>
{
	private TableRowSorter<TableModel> sorter;
	
	private ClientAbstractTable at;
	private Button search;
	private ComboBox<?> gender;
	private TextField cpf, name, phone, zipCode;
	private JCheckBox priority;
	
	public ClientTable() 
	{
		super(new InternalFrame("Client", false, true, false, true));
		
		at = new ClientAbstractTable();
		sorter = new TableRowSorter<TableModel>(at);
		gender = new ComboBox<Gender>(Gender.values());
		search = new Button("Search");
		cpf = new TextField(15);
		name = new TextField(15);
		phone = new TextField(15);
		zipCode = new TextField(15);
		
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
		t.getColumnModel().getColumn(0).setPreferredWidth(120);
		t.getColumnModel().getColumn(1).setPreferredWidth(180);
		t.getColumnModel().getColumn(2).setPreferredWidth(120);
		t.getColumnModel().getColumn(3).setPreferredWidth(220);
		t.getColumnModel().getColumn(4).setPreferredWidth(120);
		t.getColumnModel().getColumn(6).setPreferredWidth(120);
		
		for(int i = 0; i < t.getColumnCount(); i++)
		{
			if(i != 1)
			{
				t.getColumnModel().getColumn(i).setCellRenderer(r);				
			}
		}
		
		p.setComponent(new Label("CPF: "));
		p.setComponent(cpf, 1, 0);
		
		p.setComponent(new Label("Name: "), 2, 0);
		p.setComponent(name, 3, 0);
		
		p.setComponent(new Label("Phone: "), 0, 1);
		p.setComponent(phone, 1, 1);
		
		p.setComponent(new Label("Zip Code: "), 2, 1);
		p.setComponent(zipCode, 3, 1);
		
		p.setComponent(new Label("Gender: "), 0, 3);
		p.setComponent(gender, 1, 3);
		gender.setBackground(Color.WHITE);
		
		p.setComponent(search, 3, 4);
		search.addActionListener(new Action(this));
		
		ui.add(p, BorderLayout.NORTH);
		ui.add(s);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ui.setVisible(true);
	}

	public TableRowSorter<TableModel> getSorter() 
	{
		return sorter;
	}

	public ClientAbstractTable getAt()
	{
		return at;
	}

	public Button getSearch() 
	{
		return search;
	}

	public ComboBox<?> getGender() 
	{
		return gender;
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

	public JCheckBox getPriority() 
	{
		return priority;
	}
}
