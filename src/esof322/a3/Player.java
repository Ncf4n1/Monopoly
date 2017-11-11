package esof322.a3;

import java.util.ArrayList;
import java.util.List;

public class Player {
	public String playerName;									// Name of the player
	public String token;										// Token the player is using
	private int money;											// Amount of money the player has
	private ArrayList<Property> property = new ArrayList<>();	// List of currently owned properties
	private ArrayList<Railroad> railroads = new ArrayList<>();	// List of currently owned railroad
	private ArrayList<Utility> utilities = new ArrayList<>();	// List of currently owned utilites
	private int location;										// Current location on the board
	private boolean jailed;										// Jailed status of player (future implementation)
	private int turnsInJail;									// Number of turns the player has been in jail


	public Player(String name, String selectedToken){
		playerName = name;
		token = selectedToken;
		money = 1500;
		location = 0;
		jailed = false;
	}
	
	public String getName(){
		return playerName;
  }
  
	public int getLocation()
	{
		return location;

	}

	public boolean getJailedStat(){
		return jailed;
	}

	public int getMoneyTotal(){
		return money;
	}

	public void setJailedStat(boolean stat){
		jailed = stat;
	}

	// Player must pay bank or another player
	public void makePayment(int amount){
		money = money - amount;
	}

	// Player receives money from the bank or another player
	public void takePayment(int amount){
		money = money + amount;
	}

	public int moveToken(int spacesMoved){
		location = location + spacesMoved;
		//reset so as to stay in sync with board, and not have values go past possible
		if (location > 39){
			location = location - 39 - 1;
		}
		
		return location;
	}

	/*//roll to leave jail
	public void rollToGetOutOfJail(){
		int die1 = rollDie();
		int die2 = rollDie();

		//checks if the die are doubles
		if (checkIfDoubles(die1, die2)){
			setJailedStat(false);
			moveToken((die1+die2));
		}
		//else iterates turns in jail or forces payment
		else{
			if(turnsInJail < 3){
				turnsInJail++;
			}
			else{
				payToLeaveJail();
			}
		}
	}

	//pay to leave jail
	public void payToLeaveJail(){
		makePayment(50);
		setJailedStat(false);
		turnsInJail = 0;
		moveToken(rollTwoDice());
	}*/

	//add property to ArrayList, then make payment
	public void buyProperty(Deed obj){
		if (obj instanceof Property){
			property.add((Property) obj);
		}
		else if (obj instanceof Railroad){
			railroads.add((Railroad) obj);
		}
		else if (obj instanceof Utility){
			utilities.add((Utility) obj);
		}
		makePayment(obj.getPrice());
		obj.setOwner(this);
	}

	public void mortgage(Deed deed){
		takePayment(property.get(property.indexOf(deed)).getMortgageVal());
		deed.mortgage();
	}
	
	// Checks if the given property is part of a monopoly
	public boolean checkForMonopoly(Property prop){
		//count that indicates player owns property
		int count = 0;

		//for loop to iterate through property arraylist
		for(int i = 0; i < property.size(); i++){
			//check if property in arraylist has the same code as property being checked
			if(((Property) property.get(i)).getMonoColor() == prop.getMonoColor())
				count++;
			//break out of loop and return true if count = numOfMonopolyParts
			if (count == prop.getNumberOfMonopolyParts())
				return true;
		}
		return false;
	}
	
	public List<Property> propertiesAvailableToBuild()
	{
		ArrayList<Property> buildProperties = new ArrayList<>();
		
		for (int i = 0; i < property.size(); i++)
		{
			if ( (checkForMonopoly(property.get(i))))
			{
				buildProperties.add(property.get(i));
			}
		}
		return buildProperties;
	}

	//returns the number of railroads owned
	public int getRailroadOwnedCount(){
		return railroads.size();
	}

	//returns the number of utilities owned
	public int getUtilitysOwned(){
		return utilities.size();
	}
	
	//returns the number of properties owned
	public int getPropertiesOwned() {
		return property.size();
	}
}
