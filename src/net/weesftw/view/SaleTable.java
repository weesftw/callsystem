package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.weesftw.constraint.Status;
import net.weesftw.manager.Action;
import net.weesftw.manager.MouseAction;
import net.weesftw.model.Button;
import net.weesftw.model.ComboBox;
import net.weesftw.model.SellAbstractTable;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;
import net.weesftw.model.ScrollPane;
import net.weesftw.model.Table;
import net.weesftw.model.TextField;

public class SaleTable extends UI<InternalFrame>
{
	private static SaleTable instance;
	
	private TableRowSorter<TableModel> sorter;
	private SellAbstractTable at;
	private Button search;
	private TextField id, name, by, observation;
	private ComboBox<?> status;
	
	private SaleTable() 
	{
		super(new InternalFrame("Sale", false, true, false, true));
		
		at = new SellAbstractTable();
		sorter = new TableRowSorter<TableModel>(at);
		status = new ComboBox<Status>(Status.values());
		search = new Button("Search");
		id = new TextField(15);
		by = new TextField(15);
		name = new TextField(15);
		observation = new TextField(15);
		
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
		
		p.setComponent(new Label("Nome: "), 2, 0);
		p.setComponent(name, 3, 0);
		
		p.setComponent(new Label("Por: "), 0, 1);
		p.setComponent(by, 1, 1);
		
		p.setComponent(new Label("Observacão: "), 2, 1);
		p.setComponent(observation, 3, 1);
		
		p.setComponent(new Label("Status: "), 0, 4);
		p.setComponent(status, 1, 4);
		status.setBackground(Color.WHITE);
		
		p.setComponent(search, 3, 5);
		search.addActionListener(new Action(this));
		
		ui.add(p, BorderLayout.NORTH);
		ui.add(s);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static SaleTable getInstance()
	{
		return instance != null ? instance : new SaleTable(); 
	}

	public TableRowSorter<TableModel> getSorter() 
	{
		return sorter;
	}
	
	public void clear()
	{
		id.setText("");
		name.setText("");
		by.setText("");
		observation.setText("");
		status.setSelectedIndex(0);
	}

	public SellAbstractTable getAt() 
	{
		return at;
	}

	public Button getSearch() 
	{
		return search;
	}

	public TextField getId()
	{
		return id;
	}

	public TextField getName()
	{
		return name;
	}

	public TextField getBy() 
	{
		return by;
	}

	public TextField getObservation() 
	{
		return observation;
	}

	public ComboBox<?> getStatus() 
	{
		return status;
	}	
}
