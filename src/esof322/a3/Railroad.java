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
		super(name, 200, 100);
		this.name = name;
		price = 200;
		owner = null;
		rentRates = new int[]{25, 50, 100, 200};
		mortgageVal = 100;
	}
	
	public int getRent(int numOwned)
	{
		return rentRates[numOwned-1];
	}
}
