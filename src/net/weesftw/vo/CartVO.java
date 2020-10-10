package net.weesftw.vo;

public class CartVO 
{
	private String id, amount;
	private ProductVO product;
	
	public CartVO(String id, String amount, ProductVO product)
	{
		this.id = id;
		this.amount = amount;
		this.product = product;
	}
	
	public CartVO(String amount, ProductVO product)
	{
		this.amount = amount;
		this.product = product;
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getAmount()
	{
		return amount;
	}
	
	public ProductVO getProduct()
	{
		return product;
	}
}
