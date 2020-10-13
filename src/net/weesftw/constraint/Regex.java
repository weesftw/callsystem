package net.weesftw.constraint;

public final class Regex 
{
	public static final String CPF = "^[0-9]{11}$";
	public static final String NAME = "^[A-Z][a-z]{3,}";
	public static final String CEP = "^[0-9]{8}$";
	public static final String DATE = "^[0-3]{1}[0-9]{1}/[0-1]{1}[0-9]{1}/[0-9]{4}$";
	public static final String EMAIL = "^(.+)@(.+)$";
	public static final String CNPJ = "^[0-9]{14}$";
	public static final String PRICE = "^[0-9]+([.,][0-9]{2})?";
	public static final String PHONE = "^[(]{1}[0-9]{2}[)]{1}[0-9]{5}[-]{1}[0-9]{4}$";
	public static final String CM = "^[0-9]{1,}[.,][0.9]{2}$";
	public static final String KG = "^[0-9]{1,}[.,][0-9]{1}$";
	public static final String NUMBER = "^[0-9]{1,}$";
}
