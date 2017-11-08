package esof322.a3;

public class LuxuryTax extends Space
{
	private int taxAmount;
	
	public LuxuryTax(int amount)
	{
		super("LuxuryTax");
		taxAmount = amount;
	}
	
	public void payLuxuryTax(Player player)
	{
		player.makePayment(taxAmount);
	}
}
