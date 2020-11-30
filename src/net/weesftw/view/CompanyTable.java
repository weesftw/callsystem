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
import net.weesftw.model.CompanyAbstractTable;
import net.weesftw.model.Panel;

public class CompanyTable extends UI<JInternalFrame>
{
	private static CompanyTable instance;
	
	private TableRowSorter<TableModel> sorter;
	private CompanyAbstractTable at;
	private JButton search;
	private JTextField id, name, owner;
	
	private CompanyTable() 
	{
		super(new JInternalFrame("Company", false, true, false, true));
		
		at = new CompanyAbstractTable();
		sorter = new TableRowSorter<TableModel>(at);
		search = new JButton("Search");
		id = new JTextField(15);
		name = new JTextField(15);
		owner = new JTextField(15);
		
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
		t.getColumnModel().getColumn(0).setPreferredWidth(110);
		t.getColumnModel().getColumn(1).setPreferredWidth(170);
		t.getColumnModel().getColumn(2).setPreferredWidth(110);
		t.getColumnModel().getColumn(3).setPreferredWidth(95);
		
		for(int i = 0; i < t.getColumnCount(); i++)
		{
			if(i != 1)
			{
				t.getColumnModel().getColumn(i).setCellRenderer(r);				
			}
		}
		
		p.setComponent(new JLabel("CNPJ: "));
		p.setComponent(id, 1, 0);
		
		p.setComponent(new JLabel("Nome: "), 2, 0);
		p.setComponent(name, 3, 0);
		
		p.setComponent(new JLabel("Respons�vel: "), 0, 1);
		p.setComponent(owner, 1, 1);
		
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

	public JButton getSearch() 
	{
		return search;
	}
	
	public JTextField getCpf() 
	{
		return id;
	}

	public JTextField getName()
	{
		return name;
	}

	public JTextField getOwner() 
	{
		return owner;
	}
	
	public JTextField getId() 
	{
		return id;
	}
}
