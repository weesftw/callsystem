package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.weesftw.manager.Action;
import net.weesftw.manager.MouseAction;
import net.weesftw.model.Button;
import net.weesftw.model.CompanyAbstractTable;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.ScrollPane;
import net.weesftw.model.Table;
import net.weesftw.model.TextField;

public class CompanyTable extends UI<InternalFrame>
{
	private static CompanyTable instance;
	
	private TableRowSorter<TableModel> sorter;
	private CompanyAbstractTable at;
	private Button search;
	private TextField id, name, owner, zipCode;
	
	private CompanyTable() 
	{
		super(new InternalFrame("Company", false, true, false, true));
		
		at = new CompanyAbstractTable();
		sorter = new TableRowSorter<TableModel>(at);
		search = new Button("Search");
		id = new TextField(15);
		name = new TextField(15);
		owner = new TextField(15);
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
		t.getColumnModel().getColumn(0).setPreferredWidth(90);
		t.getColumnModel().getColumn(1).setPreferredWidth(180);
		t.getColumnModel().getColumn(2).setPreferredWidth(90);
		t.getColumnModel().getColumn(3).setPreferredWidth(120);
		
		for(int i = 0; i < t.getColumnCount(); i++)
		{
			if(i != 1)
			{
				t.getColumnModel().getColumn(i).setCellRenderer(r);				
			}
		}
		
		p.setComponent(new Label("CNPJ: "));
		p.setComponent(id, 1, 0);
		
		p.setComponent(new Label("Nome: "), 2, 0);
		p.setComponent(name, 3, 0);
		
		p.setComponent(new Label("Responsavel: "), 0, 1);
		p.setComponent(owner, 1, 1);
		
		p.setComponent(new Label("CEP: "), 2, 1);
		p.setComponent(zipCode, 3, 1);
		
		p.setComponent(search, 3, 2);
		search.addActionListener(new Action(this));
		
		ui.add(p, BorderLayout.NORTH);
		ui.add(s);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static CompanyTable getInstance()
	{
		return instance != null ? instance : new CompanyTable(); 
	}

	public TableRowSorter<TableModel> getSorter() 
	{
		return sorter;
	}

	public CompanyAbstractTable getAt()
	{
		return at;
	}

	public Button getSearch() 
	{
		return search;
	}
	
	public TextField getCpf() 
	{
		return id;
	}

	public TextField getName()
	{
		return name;
	}

	public TextField getOwner() 
	{
		return owner;
	}

	public TextField getZipCode() 
	{
		return zipCode;
	}

	public TextField getId() 
	{
		return id;
	}
}
