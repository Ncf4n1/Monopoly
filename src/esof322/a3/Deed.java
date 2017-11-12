package esof322.a3;

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

	public String getName(){
		return name;
	}
	
	public int getPrice(){
		return price;
	}
	
	public void setOwner(Player player){
		owner = player;
	}
	
	public Player getOwner(){
		return owner;
	}
	
	public int getMortgageVal(){
		return mortgageVal;
	}
	
	public void mortgage(){
		mortgaged = true;
	}
	
	public void unMortgage(){
		mortgaged = false;
	}
	
	public boolean getMortgageStat(){
		return mortgaged;
	}
	
}
