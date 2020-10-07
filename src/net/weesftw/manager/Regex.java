package net.weesftw.manager;

public final class Regex 
{
	public static final String CPF = "^[0-9]{11}$";
	public static final String NAME = "^[A-Z][a-z]{3,}$";
	public static final String CEP = "^[0-9]{8}$";
	public static final String DATE = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$";
	public static final String EMAIL = "^(.+)@(.+)$";
	public static final String CNPJ = "^[0-9]{14}$";
}
