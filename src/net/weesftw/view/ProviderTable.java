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
import net.weesftw.model.ProviderAbstractTable;

public class ProviderTable extends UI<JInternalFrame>
{
	private static ProviderTable instance;
	
	private TableRowSorter<TableModel> sorter;
	private ProviderAbstractTable at;
	private JButton search;
	private JTextField cnpj, name, phoneNumber, freight, category;
	
	private ProviderTable() 
	{
		super(new JInternalFrame("Provider", false, true, false, true));
		
		at = new ProviderAbstractTable();
		sorter = new TableRowSorter<TableModel>(at);
		search = new JButton("Search");
		cnpj = new JTextField(15);
		name = new JTextField(15);
		category = new JTextField(15);
		
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
		t.getColumnModel().getColumn(0).setPreferredWidth(130);
		t.getColumnModel().getColumn(1).setPreferredWidth(180);
		t.getColumnModel().getColumn(2).setPreferredWidth(85);
		t.getColumnModel().getColumn(3).setPreferredWidth(85);
		t.getColumnModel().getColumn(4).setPreferredWidth(120);
		
		for(int i = 0; i < t.getColumnCount(); i++)
		{
			if(i != 1 || i != 2)
			{
				t.getColumnModel().getColumn(i).setCellRenderer(r);				
			}
		}
		
		p.setComponent(new JLabel("CNPJ: "));
		p.setComponent(cnpj, 1, 0);
		
		p.setComponent(new JLabel("Nome: "), 2, 0);
		p.setComponent(name, 3, 0);
		
		p.setComponent(new JLabel("Categoria: "), 0, 1);
		p.setComponent(category, 1, 1);
		
		p.setComponent(search, 3, 5);
		search.addActionListener(new Action(this));
		
		ui.add(p, BorderLayout.NORTH);
		ui.add(s);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static ProviderTable getInstance()
	{
		return instance != null ? instance : new ProviderTable(); 
	}

	public TableRowSorter<TableModel> getSorter() 
	{
		return sorter;
	}

	public ProviderAbstractTable getAt()
	{
		return at;
	}

	public JButton getSearch() 
	{
		return search;
	}

	public JTextField getId() 
	{
		return cnpj;
	}

	public JTextField getName()
	{
		return name;
	}

	public JTextField getPhone() 
	{
		return phoneNumber;
	}

	public JTextField getFreight() 
	{
		return freight;
	}

	public JTextField getCategory() 
	{
		return category;
	}
}
