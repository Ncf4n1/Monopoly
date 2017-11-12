package esof322.a3;

import java.util.ArrayList;
import java.util.List;

public class Player {
	public String playerName;									// Name of the player
	public String token;										// Token the player is using
	private int money;											// Amount of money the player has
	private static Object [][] property = new Object [][4];	// 2D array containing Objects of type booleans and property
	private ArrayList<Railroad> railroads = new ArrayList<>();	// List of currently owned railroad
	private ArrayList<Utility> utilities = new ArrayList<>();	// List of currently owned utilites
	private int location;										// Current location on the board
	private boolean jailed;										// Jailed status of player (future implementation)
	//private int turnsInJail;									// Number of turns the player has been in jail

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
  
	public int getLocation(){
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
			//property.add((Property) obj);
			Property prop = (Property) obj;
			int color = prop.getMonoColor();
			int part = prop.getPartNumber();
			property [color][part] = obj;
			checkAndChangeMonopolyStat(prop, color);
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
		takePayment(deed.getMortgageVal());
		deed.mortgage();
	}
	
	/*when a property is bought this is called to check if the monopoly is completed
	 * if it is it will place a true boolean in the corresponding color(int) index 
	 * in the 0th index*/
	public void checkAndChangeMonopolyStat(Property prop, int color){
		int parts = prop.getNumOfParts();
		for(int i = 1; i <= parts; i++){
			if(property[color][i] != null){
				property[color][0] = true;
			}
			else{
				property[color][0] = false;
				break;
			}
		}
	}
	
	//used to check if rent should be doubled because a the monopoly is owned by one player
	public boolean checkForMonopoly(int color){
		return (boolean) property[color][0];
	}
	
	/*makes an array of properties that a player can build on bases on 
	 * if they have the monopoly, and restricts the player to build evenly 
	 * across the properties of the monopoly*/
	public List<Property> getBuildableProperties(){
		ArrayList<Property> buildableProperties = new ArrayList<>();
		for (int i=0; i<7; i++){
			if ((boolean)property[i][0] == true){
				int parts = ((Property) property[i][1]).getNumOfParts();
				for(int j=1; j<=parts; i++){
					int max = getMaxBuilt(i,parts);
					if (((Property) property[i][j]).getNumHouses() <= max && ((Property) property[i][j]).getNumHouses()<5)){
						buildableProperties.add((Property) property[i][j]);
					}
				}
			}
		}
		return buildableProperties;
	}

	/*helper function to propertiesAvailableToBuild() that returns the max
	 * number of houses built on a single property within a monopoly*/
	public static int getMaxBuilt(int color, int parts){
		int max = 0;
		for (int i=1; i<= parts; i++){
			if (max < ((Property) property[color][parts]).getNumHouses()){
				max = ((Property) property[color][parts]).getNumHouses();
			}
		}
		return max;
	}
	
	//returns the number of railroads owned
	public int getRailroadOwnedCount(){
		return railroads.size();
	}

	//returns the number of utilities owned
	public int getUtilitysOwned(){
		return utilities.size();
	}
	
	public void buildHouse(Property prop){
		makePayment(prop.getHouseCost());
		prop.buildHouse();
	}
	
//	//returns the number of properties owned
//	public int getPropertiesOwned() {
//		return property.size();
//	}
}
