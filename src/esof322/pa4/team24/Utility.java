package esof322.pa4.team24;

public class Utility extends Deed
{
	private String name;
	private int price;
	private Player owner;
	private int mortgageVal;
	
	public Utility(String name, int x, int y)
	{
		super(name, 150, 75, x , y);
		this.name = name;
		price = 150;
		owner = null;
		mortgageVal = 75;
	}
}
