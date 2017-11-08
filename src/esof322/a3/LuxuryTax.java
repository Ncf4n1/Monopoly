package esof322.a3;

public class LuxuryTax extends Space
{
	private int taxAmount;
	
	public LuxuryTax(int amount)
	{
		super("Luxury Tax");
		taxAmount = amount;
	}
	
	public void payLuxuryTax(Player player)
	{
		player.makePayment(taxAmount);
	}
}
