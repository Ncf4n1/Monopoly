package esof322.a3;

public class LuxuryTax extends Space
{
	private int taxAmount;
	
	public LuxuryTax(int amount, int x, int y)
	{
		super("LuxuryTax", x, y);
		taxAmount = amount;
	}
	
	public void payLuxuryTax(Player player)
	{
		player.makePayment(taxAmount);
	}
}
