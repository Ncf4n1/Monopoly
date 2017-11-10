package esof322.a3;

public class IncomeTax extends Space
{
	private int taxAmount;
	
	public IncomeTax(int amount, int x, int y)
	{
		super("IncomeTax", x, y);
		taxAmount = amount;
	}
	
	public void payIncomeTax(Player player)
	{
		player.makePayment(taxAmount);
	}
	
	public int getTaxAmount()
	{
		return taxAmount;
	}
}
