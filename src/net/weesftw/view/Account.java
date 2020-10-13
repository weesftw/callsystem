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
		
		p.setComponent(new Label("Logado como: " + auth.getUser().getUsername()));
		p.setComponent(new Label("Bem vindo " + auth.getPeople().getFirstName() + "."), 0, 1);
		p.setComponent(new Label("Você possui " + td.getTicketOpen() + "chamados para serem analisados."), 0, 2);
		p.setComponent(new Label("Seus privilégios são: "), 0, 3);
		p.setComponent(new Label(" • " + auth.getUser().getDepartment().getDescription()), 0, 4);
		
		ui.add(p);
		
		ui.pack();
		ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static Account getInstance()
	{
		return instance != null ? instance : new Account(); 
	}
}
