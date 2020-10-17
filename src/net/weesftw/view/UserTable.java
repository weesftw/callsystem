package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.weesftw.manager.Action;
import net.weesftw.manager.MouseAction;
import net.weesftw.model.Button;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.ScrollPane;
import net.weesftw.model.Table;
import net.weesftw.model.TextField;
import net.weesftw.model.UserAbstractTable;

public class UserTable extends UI<InternalFrame>
{
	private static UserTable instance;
	
	private TableRowSorter<TableModel> sorter;
	private UserAbstractTable at;
	private Button search;
	private TextField cpf, username;
	
	private UserTable() 
	{
		super(new InternalFrame("User", false, true, false, true));
		
		at = new UserAbstractTable();
		sorter = new TableRowSorter<TableModel>(at);
		search = new Button("Search");
		cpf = new TextField(15);
		username = new TextField(15);
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		Table t = new Table(at);
		Panel p = new Panel("Filter", 4, 4, 4, 4);
		ScrollPane s = new ScrollPane(t);
		
		r.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		
		t.setRowSorter(sorter);
		t.setBackground(Color.WHITE);
		t.addMouseListener(new MouseAction(this));
		t.getTableHeader().setReorderingAllowed(false);
		
		for(int i = 0; i < t.getColumnCount(); i++)
		{
			if(i != 1)
			{
				t.getColumnModel().getColumn(i).setCellRenderer(r);				
			}
		}
		
		p.setComponent(new Label("CPF: "));
		p.setComponent(cpf, 1, 0);
		
		p.setComponent(new Label("Usuario: "), 2, 0);
		p.setComponent(username, 3, 0);
		
		p.setComponent(search, 3, 1);
		search.addActionListener(new Action(this));
		
		ui.add(p, BorderLayout.NORTH);
		ui.add(s);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static UserTable getInstance()
	{
		return instance != null ? instance : new UserTable(); 
	}

	public TableRowSorter<TableModel> getSorter() 
	{
		return sorter;
	}

	public UserAbstractTable getAt() 
	{
		return at;
	}

	public Button getSearch() 
	{
		return search;
	}

	public TextField getCpf() 
	{
		return cpf;
	}

	public TextField getUsername() 
	{
		return username;
	}
}
