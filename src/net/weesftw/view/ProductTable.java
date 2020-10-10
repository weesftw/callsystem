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
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.ProductAbstractTable;
import net.weesftw.model.ScrollPane;
import net.weesftw.model.Table;
import net.weesftw.model.TextField;

public class ProductTable extends UI<InternalFrame>
{
	private TableRowSorter<TableModel> sorter;
	
	private ProductAbstractTable at;
	private Button search, refresh;
	private TextField id, name, price, provider;
	
	public ProductTable() 
	{
		super(new InternalFrame("Product", false, true, false, true));
		
		at = new ProductAbstractTable();
		sorter = new TableRowSorter<TableModel>(at);
		search = new Button("Search");
		refresh = new Button("Refresh");
		id = new TextField(15);
		name = new TextField(15);
		price = new TextField(15);
		provider = new TextField(15);
		
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
		
		p.setComponent(new Label("ID: "));
		p.setComponent(id, 1, 0);
		
		p.setComponent(new Label("Name: "), 2, 0);
		p.setComponent(name, 3, 0);
		
		p.setComponent(new Label("Price: "), 0, 1);
		p.setComponent(price, 1, 1);
		
		p.setComponent(new Label("Provider: "), 2, 1);
		p.setComponent(provider, 3, 1);
		
		p.setComponent(refresh, 1, 2);
		refresh.addActionListener(new Action(this));
		
		p.setComponent(search, 3, 2);
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

	public ProductAbstractTable getAt()
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

	public TextField getPrice() 
	{
		return price;
	}

	public TextField getProvider() 
	{
		return provider;
	}

	public TextField getId() 
	{
		return id;
	}

	public Button getRefresh() 
	{
		return refresh;
	}
}
