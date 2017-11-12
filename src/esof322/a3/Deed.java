package esof322.a3;

public class Deed extends Space
{
	
	protected String name;
	protected int price;
	protected Player owner;
	protected int mortgageVal;
	protected boolean mortgaged = false;
	
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
