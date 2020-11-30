package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import net.weesftw.model.SellAbstractTable;
import net.weesftw.vo.SellVO;

public class SaleTable extends UI<JInternalFrame>
{
	private static SaleTable instance;
	
	private TableRowSorter<TableModel> sorter;
	private SellAbstractTable at;
	private JButton search;
	private JTextField id, name, by, observation;
	private JComboBox<String> status;
	
	private SaleTable() 
	{
		super(new JInternalFrame("Sale", false, true, false, true));
		
		at = new SellAbstractTable();
		sorter = new TableRowSorter<TableModel>(at);
		status = new JComboBox<String>();
		search = new JButton("Search");
		id = new JTextField(15);
		by = new JTextField(15);
		name = new JTextField(15);
		observation = new JTextField(15);
		
		status.addItem("");
		
		for(SellVO.Status s : SellVO.Status.values())
		{
			status.addItem(s.name());
		}
		
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
		t.getColumnModel().getColumn(1).setPreferredWidth(180);
		t.getColumnModel().getColumn(2).setPreferredWidth(90);
		t.getColumnModel().getColumn(3).setPreferredWidth(100);
		t.getColumnModel().getColumn(3).setPreferredWidth(120);
		
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
		
		p.setComponent(new JLabel("Por: "), 0, 1);
		p.setComponent(by, 1, 1);
		
		p.setComponent(new JLabel("Observacão: "), 2, 1);
		p.setComponent(observation, 3, 1);
		
		p.setComponent(new JLabel("Status: "), 0, 4);
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

	public JButton getSearch() 
	{
		return search;
	}

	public JTextField getId()
	{
		return id;
	}

	public JTextField getName()
	{
		return name;
	}

	public JTextField getBy() 
	{
		return by;
	}

	public JTextField getObservation() 
	{
		return observation;
	}

	public JComboBox<?> getStatus() 
	{
		return status;
	}	
}
