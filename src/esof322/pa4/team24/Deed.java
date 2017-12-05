package esof322.pa4.team24;

public class Deed extends Space
{	
	protected String name;                // Name of the deed
	protected int price;                  //Purchase price of the deed
	protected Player owner;               // Current owner of the deed
	protected int mortgageVal;            // Money gained from mortgaging the deed
	protected boolean mortgaged = false;  // Flag for if the property is mortgaged or not

	public Deed(String name, int price, int mortgageVal, int x, int y){
		super(name, x, y);
		this.name = name;
		this.price = price;
		owner = null;
		this.mortgageVal = mortgageVal;
	}

	//returns the name of the deed
	public String getName(){
		return name;
	}
	
	//gets the buying price for the deed
	public int getPrice(){
		return price;
	}
	
	//sets a new owner for the current deed
	public void setOwner(Player player){
		owner = player;
	}
	
	//gets the current owner
	public Player getOwner(){
		return owner;
	}
	
	//gets the mortgage value for the current deed
	public int getMortgageVal(){
		return mortgageVal;
	}
	
	//mortgages the current deed
	public void mortgage(){
		mortgaged = true;
	}
	
	//removes the mortgages from the current deed
	public void unMortgage(){
		mortgaged = false;
	}
	
	//returns the mortgage status of the current deed
	public boolean getMortgageStat(){
		return mortgaged;
	}
	
}
