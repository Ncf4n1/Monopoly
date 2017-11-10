package esof322.a3;

public class Deed extends Space
{
	
	private String name;
	private int price;
	private Player owner;
	private int mortgageVal;
	private boolean mortgaged = false;
	
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
	
	public Player getOwner(){
		return owner;
	}
	
	public int getMortgageVal(){
		return mortgageVal;
	}
	
	public void mortgage(){
		mortgaged = true;
	}
	
	public boolean getMortgageStat(){
		return mortgaged;
	}
	
}
