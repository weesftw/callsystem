package net.weesftw.manager;

import net.weesftw.dao.CartDAO;
import net.weesftw.dao.ClientDAO;
import net.weesftw.dao.CompanyDAO;
import net.weesftw.dao.ProductDAO;
import net.weesftw.dao.ProviderDAO;
import net.weesftw.dao.SellDAO;
import net.weesftw.dao.TicketDAO;
import net.weesftw.dao.UserDAO;
import net.weesftw.view.ClientTable;
import net.weesftw.view.CompanyTable;
import net.weesftw.view.ProductTable;
import net.weesftw.view.ProviderTable;
import net.weesftw.view.Sale;
import net.weesftw.view.SaleTable;
import net.weesftw.view.TicketTable;
import net.weesftw.view.UserTable;
import net.weesftw.vo.CartVO;
import net.weesftw.vo.ClientVO;
import net.weesftw.vo.CompanyVO;
import net.weesftw.vo.ProductVO;
import net.weesftw.vo.ProviderVO;
import net.weesftw.vo.SellVO;
import net.weesftw.vo.TicketVO;
import net.weesftw.vo.UserVO;

public class Reload 
{	
	public static void populate()
	{
		CartDAO cv = new CartDAO();
		ClientDAO cd = new ClientDAO();
		CompanyDAO cdd = new CompanyDAO();
		ProductDAO pd = new ProductDAO();
		ProviderDAO pdd = new ProviderDAO();
		SellDAO sd = new SellDAO();
		TicketDAO td = new TicketDAO();
		UserDAO ud = new UserDAO();
		
		CartVO.listAll = cv.list();
		ClientVO.list = cd.list();
		CompanyVO.list = cdd.list();
		ProductVO.list = pd.list();
		ProviderVO.list = pdd.list();
		SellVO.list = sd.list();
		TicketVO.list = td.list();
		UserVO.list = ud.list();
	}
	
	public static void refresh()
	{
		ClientTable ct = ClientTable.getInstance();
		CompanyTable ctt = CompanyTable.getInstance();
		ProductTable pt = ProductTable.getInstance();
		ProviderTable ptt = ProviderTable.getInstance();
		Sale s = Sale.getInstance();
		SaleTable st = SaleTable.getInstance();
		TicketTable tt = TicketTable.getInstance();
		UserTable ut = UserTable.getInstance();
		
		ct.getAt().fireTableDataChanged();
		ctt.getAt().fireTableDataChanged();
		pt.getAt().fireTableDataChanged();
		ptt.getAt().fireTableDataChanged();
		s.getAt().fireTableDataChanged();
		st.getAt().fireTableDataChanged();
		tt.getAt().fireTableDataChanged();
		ut.getAt().fireTableDataChanged();
		
		populate();
	}
}
