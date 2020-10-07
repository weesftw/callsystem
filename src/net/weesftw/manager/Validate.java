package net.weesftw.manager;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Validate extends PlainDocument
{
	private static final long serialVersionUID = 1L;

	private int lenght;

	public Validate(int lenght)
	{
		this.lenght = lenght;
	}

	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException 
	{
		int tam = (getLength() + str.length());

		if (str != null)
		{
			if (lenght <= 0)
			{
				super.insertString(offset, str, attr);

				return;
			}

			if (tam <= lenght)
			{
				super.insertString(offset, str, attr);			
			}
		}
		
		return;
	}
}
