package net.weesftw.model;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class Table extends JTable
{
	private static final long serialVersionUID = 1L;

	public Table(TableModel tm)
	{
		super(tm);
	}
}
