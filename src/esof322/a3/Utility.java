package esof322.a3;

public class Utility extends Deed
{
	private String name;
	private int price;
	private Player owner;
	private int mortgageVal;
	
	public Utility(String name)
	{
		this.name = name;
		price = 150;
		//owner = Bank
		mortgageVal = 75;
	}
	
}
