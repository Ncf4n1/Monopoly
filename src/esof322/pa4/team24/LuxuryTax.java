package esof322.pa4.team24;

public class LuxuryTax extends Space{
	private int taxAmount;
	
	public LuxuryTax(int amount, int x, int y){
		super("LuxuryTax", x, y);
		taxAmount = amount;
	}
	
	public void payLuxuryTax(Player player){
		player.makePayment(taxAmount);
	}
	
	public int getTaxAmount(){
		return taxAmount;
	}
}
