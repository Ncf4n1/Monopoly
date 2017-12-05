package esof322.pa4.team24;

public class Railroad extends Deed{

	private int[] rentRates;
	
	public Railroad(String name, int x, int y){
		super(name, 200, 100, x, y);
		this.name = name;
		price = 200;
		owner = null;
		rentRates = new int[]{25, 50, 100, 200};
		mortgageVal = 100;
	}
	
	public int getRent(int numOwned){
		return rentRates[numOwned-1];
	}
}
