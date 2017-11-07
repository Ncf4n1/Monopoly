package esof322.a3;

public class Railroad extends Deed
{
	private String name;
	private int price;
	private Player owner;
	private int[] rentRates;
	private int mortgageVal;
	
	public Railroad(String name)
	{
		this.name = name;
		price = 200;
		//owner = Bank
		rentRates = new int[]{25, 50, 100, 200};
		mortgageVal = 100;
	}

	
}
