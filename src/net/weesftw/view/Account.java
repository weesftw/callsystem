package net.weesftw.view;

import javax.swing.WindowConstants;

import net.weesftw.dao.TicketDAO;
import net.weesftw.manager.Authenticate;
import net.weesftw.model.InternalFrame;
import net.weesftw.model.Label;
import net.weesftw.model.Panel;

public class Account extends UI<InternalFrame>
{
	private static Account instance;
	
	private Account() 
	{
		super(new InternalFrame("Account", false, true, false, true));
		
		Authenticate auth = Main.getInstance().getAuth();
		TicketDAO td = new TicketDAO();
		Panel p = new Panel("Your Account", 4, 4, 4, 4);
		
		p.setComponent(
				new Label("<html><h1>Logado como: " + auth.getUser().getUsername() + " </h1>\n"
						+ "	<hr>"
						+ "	<p style='text-align: justify;'>Bem vindo " + auth.getPeople().getFirstName() + "<br/>\n"
						+ "	Atualmente possui " + td.getTicketOpen() + " chamados em aberto.<br><br>\n"
						+ "	Seus privilégios são: <br/>"
						+ auth.getUser().getDepartment().getDescription() + " </p></html>")
				);
		
		ui.add(p);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static Account getInstance()
	{
		return instance != null ? instance : new Account(); 
	}
}
