package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.weesftw.manager.Action;
import net.weesftw.manager.MouseAction;
import net.weesftw.model.Panel;
import net.weesftw.model.UserAbstractTable;

public class UserTable extends UI<JInternalFrame>
{
	private static UserTable instance;
	
	private TableRowSorter<TableModel> sorter;
	private UserAbstractTable at;
	private JButton search;
	private JTextField cpf, username;
	
	private UserTable() 
	{
		super(new JInternalFrame("User", false, true, false, true));
		
		cpf = new JTextField(15);
		at = new UserAbstractTable();
		search = new JButton("Search");
		username = new JTextField(15);
		sorter = new TableRowSorter<TableModel>(at);
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		JTable t = new JTable(at);
		Panel p = new Panel("Filter", 4, 4, 4, 4);
		JScrollPane s = new JScrollPane(t);
		
		r.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		
		t.setRowSorter(sorter);
		t.setBackground(Color.WHITE);
		t.addMouseListener(new MouseAction(this));
		t.getTableHeader().setReorderingAllowed(false);
		
		for(int i = 0; i < t.getColumnCount(); i++)
		{
			if(i != 1 || i != 3)
			{
				t.getColumnModel().getColumn(i).setCellRenderer(r);				
			}
		}
		
		p.setComponent(new JLabel("CPF: "));
		p.setComponent(cpf, 1, 0);
		
		p.setComponent(new JLabel("Usuario: "), 2, 0);
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

	public JButton getSearch() 
	{
		return search;
	}

	public JTextField getCpf() 
	{
		return cpf;
	}

	public JTextField getUsername() 
	{
		return username;
	}
}
