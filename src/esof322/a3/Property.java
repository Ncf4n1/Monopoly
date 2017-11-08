package esof322.a3;

public class Property extends Deed
{
	
	private String name;				// Name of property
	private int price;					// Purchase price for property
	private Player owner;				// Current owner of property
	private int[] rentRates;			// Rent rates for a certain # of houses on property
	private int houseCost;				// Cost of purchasing a house / hotel
	private int numHouses;				// Number of houses built on property
	private int mortgageVal;			// Money gained from mortgaging property
	private int numberOfMonopolyParts;	// Number of parts needed for monopoly ???
	
	public Property(String name, int price, int[] rentRates, int houseCost, int mortgageVal)
	{
		super(name, price, mortgageVal);
		this.name = name;
		this.price = price;
		this.rentRates = rentRates;
		this.houseCost = houseCost;
		this.mortgageVal = mortgageVal;
		
		owner  = null;
		numHouses = 0;
		//Determine numOfMonopolyParts
	}
	
	// ?????
	public int getNumberOfMonopolyParts()
	{
		return numberOfMonopolyParts;
	}
	
	public int getRent()
	{
		return rentRates[numHouses];
	}
	
	public void sellHouse()
	{
		numHouses--;
	}
}
