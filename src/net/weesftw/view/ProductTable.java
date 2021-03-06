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
import net.weesftw.model.ProductAbstractTable;

public class ProductTable extends UI<JInternalFrame>
{
	private static ProductTable instance;
	
	private TableRowSorter<TableModel> sorter;
	private ProductAbstractTable at;
	private JButton search;
	private JTextField id, name, provider;
	
	private ProductTable() 
	{
		super(new JInternalFrame("Product", false, true, false, true));
		
		at = new ProductAbstractTable();
		sorter = new TableRowSorter<TableModel>(at);
		search = new JButton("Search");
		id = new JTextField(15);
		name = new JTextField(15);
		provider = new JTextField(15);
		
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
		t.getColumnModel().getColumn(0).setPreferredWidth(70);
		t.getColumnModel().getColumn(1).setPreferredWidth(220);
		t.getColumnModel().getColumn(2).setPreferredWidth(90);
		t.getColumnModel().getColumn(3).setPreferredWidth(130);
		
		for(int i = 0; i < t.getColumnCount(); i++)
		{
			if(i != 1 || i != 3)
			{
				t.getColumnModel().getColumn(i).setCellRenderer(r);
			}
		}
		
		p.setComponent(new JLabel("ID: "));
		p.setComponent(id, 1, 0);
		
		p.setComponent(new JLabel("Nome: "), 2, 0);
		p.setComponent(name, 3, 0);
		
		p.setComponent(new JLabel("Fornecedor: "), 0, 1);
		p.setComponent(provider, 1, 1);
		
		p.setComponent(search, 3, 2);
		search.addActionListener(new Action(this));
		
		ui.add(p, BorderLayout.NORTH);
		ui.add(s);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static ProductTable getInstance()
	{
		return instance != null ? instance : new ProductTable(); 
	}

	public TableRowSorter<TableModel> getSorter() 
	{
		return sorter;
	}

	public ProductAbstractTable getAt()
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

	public JTextField getProvider() 
	{
		return provider;
	}

	public JTextField getId() 
	{
		return id;
	}
}
