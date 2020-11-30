package net.weesftw.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import net.weesftw.constraint.Gender;
import net.weesftw.manager.Action;
import net.weesftw.manager.MouseAction;
import net.weesftw.model.ClientAbstractTable;
import net.weesftw.model.Panel;

public class ClientTable extends UI<JInternalFrame>
{
	private static ClientTable instance;
	
	private TableRowSorter<TableModel> sorter;
	private ClientAbstractTable at;
	private JButton search;
	private JComboBox<?> gender;
	private JTextField cpf, name;
	private JCheckBox priority;
	
	private ClientTable() 
	{
		super(new JInternalFrame("Client", false, true, false, true));
		
		at = new ClientAbstractTable();
		sorter = new TableRowSorter<TableModel>(at);
		gender = new JComboBox<Gender>(Gender.values());
		search = new JButton("Search");
		cpf = new JTextField(15);
		name = new JTextField(15);
		
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
		t.getColumnModel().getColumn(0).setPreferredWidth(100);
		t.getColumnModel().getColumn(1).setPreferredWidth(170);
		t.getColumnModel().getColumn(2).setPreferredWidth(110);
		t.getColumnModel().getColumn(3).setPreferredWidth(90);
		
		for(int i = 0; i < t.getColumnCount(); i++)
		{
			if(i != 1)
			{
				t.getColumnModel().getColumn(i).setCellRenderer(r);				
			}
		}
		
		p.setComponent(new JLabel("CPF: "));
		p.setComponent(cpf, 1, 0);
		
		p.setComponent(new JLabel("Nome: "), 2, 0);
		p.setComponent(name, 3, 0);
		
		p.setComponent(search, 3, 4);
		search.addActionListener(new Action(this));
		
		ui.add(p, BorderLayout.NORTH);
		ui.add(s);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static ClientTable getInstance()
	{
		return instance != null ? instance : new ClientTable(); 
	}

	public TableRowSorter<TableModel> getSorter() 
	{
		return sorter;
	}

	public ClientAbstractTable getAt()
	{
		return at;
	}

	public JButton getSearch() 
	{
		return search;
	}

	public JComboBox<?> getGender() 
	{
		return gender;
	}

	public JTextField getCpf() 
	{
		return cpf;
	}

	public JTextField getName()
	{
		return name;
	}

	public JCheckBox getPriority() 
	{
		return priority;
	}
}
