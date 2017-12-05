package esof322.pa4.team24;

public class Property extends Deed
{
	
	private String name;				// Name of property
	private int price;					// Purchase price for property
	private int[] rentRates;			// Rent rates for a certain # of houses on property
	private int houseCost;				// Cost of purchasing a house / hotel
	private int numHouses;				// Number of houses built on property
	private int mortgageVal;			// Money gained from mortgaging property
	private int monopolyPart;			// Number of parts needed for monopoly
	private int monoColor;				// Manages which properties go together to make a monopoly
	private int numOfParts;
	
	public Property(String name, int price, int[] rentRates, int houseCost, int mortgageVal, int monopolyPart, int x, int y, int monoColor, int numOfParts){
		super(name, price, mortgageVal, x, y);
		this.name = name;
		this.price = price;
		this.rentRates = rentRates;
		this.houseCost = houseCost;
		this.mortgageVal = mortgageVal;
		this.monopolyPart = monopolyPart;
		this.monoColor = monoColor;
		this.numOfParts = numOfParts;
		numHouses = 0;
	}
	
	public int getPrice(){
		return price;
	}
	
	public int getPartNumber(){
		return monopolyPart;
	}
	
	public int getNumOfParts(){
		return numOfParts;
	}
	
	public int getRent(){
		return rentRates[numHouses];
	}
	
	public void buildHouse(){
		numHouses++;
		Bank.sellHouse();
	}
	
	public void buildHotel(){
		for(int i=0; i<4; i++){
			Bank.buyBackHouse();
		}
		Bank.sellHotel();
		this.numHouses++;
	}
	
	public void sellHouse(){
		numHouses--;
		owner.takePayment(houseCost/2);
	}
	
	public void sellHotel(){
		if (Bank.getHouses() >= 4){
			sellHouse();
			numHouses = 4;
			Bank.buyBackHotel();
		}
		else{
			int houseDiff = 4 - Bank.getHouses();
			numHouses = 4;
			for (int i=0; i<houseDiff; i++){
				sellHouse();
			}
		}	
	}
	
	public int getMonoColor(){
		return monoColor;
	}
	
	public int getNumHouses(){
		return numHouses;
	}
	
	public int getMortgageValue(){
		return mortgageVal;
	}
	
	public int getHouseCost(){
		return houseCost;
	}
	
	public String getName(){
		return name;
	}
}
