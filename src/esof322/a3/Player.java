package esof322.a3;

import java.util.ArrayList;
import java.util.List;

public class Player {
	public String playerName;									// Name of the player
	public String token;										// Token the player is using
	private int money;											// Amount of money the player has
	private Object[][] property = new Object[8][4];	// 2D array containing Objects of type booleans and property
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

		for (int i = 0; i < 8; i++)
			property[i][0] = false;
	}

	//return player name
	public String getName(){
		return playerName;
	}

	//return current location of the player, 0-39, follows array in board class
	public int getLocation(){
		return location;
	}

	//gets the jailed status of the current player
	public boolean getJailedStat(){
		return jailed;
	}

	//gets the current money total of the player
	public int getMoneyTotal(){
		return money;
	}

	//sets the jailed status
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

	//moves the token, follows the board array of spaces
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
			Property prop = (Property) obj;			//sets the incoming deed to type Property
			int color = prop.getMonoColor();		//gets the Monopoly group the property belongs to
			int part = prop.getPartNumber();		//gets the number of properties in the Monopoly
			property [color][part] = obj;			//adds the property to an array that shows what is owned by player
			checkAndChangeMonopolyStat(prop, color);//method call to check if there is a monopoly, and set it to be true if so
		}
		else if (obj instanceof Railroad){
			railroads.add((Railroad) obj);			//sets the incoming deed to type Railroad and adds it to the arraylist of railroads
		}
		else if (obj instanceof Utility){
			utilities.add((Utility) obj);			//sets the incoming deed to type Utility, add to Utility arraylist
		}
		makePayment(obj.getPrice());				//makes the player pay the amount specified by deed
		obj.setOwner(this);							//sets the owner to the current player for the deed
	}

	//mortgages the deed, giving the player the money and setting the deed to mortgaged
	public void mortgage(Deed deed){
		takePayment(deed.getMortgageVal());
		deed.mortgage();
	}

	/*when a property is bought this is called to check if the monopoly is completed
	 * if it is it will place a true boolean in the corresponding color(int) index
	 * in the 0th index*/
	public void checkAndChangeMonopolyStat(Property prop, int color){
		int parts = prop.getNumOfParts();
		for(int i = 1; i <= parts; i++){		//for loops to iterate through 2-D array of properties
			if(property[color][i] != null){		//if the method does not find a null, indicating all deed owned by player, sets the monopoly var to true
				property[color][0] = true;
			}
			else{								//else sets it to false and breaks out of the loop as there is no possibility of a monopoly for that group
				property[color][0] = false;
				break;
			}
		}
	}

	//used to check if rent should be doubled because a the monopoly is owned by one player
	public boolean checkForMonopoly(int color){
		return (boolean) property[color][0];
	}

	/*makes an array of properties that a player can build houses on bases on
	 * if they have the monopoly, and restricts the player to build evenly
	 * across the properties of the monopoly*/
	public List<Property> getHouseBuildableProps(){
		ArrayList<Property> houseBuildableProperties = new ArrayList<>();
			for (int i=0; i<8; i++){
				if ((boolean)property[i][0] == true){
					int parts = ((Property) property[i][1]).getNumOfParts();
					for(int j=1; j<=parts; j++){
						int min = getMinBuilt(i,parts);
						if (((Property) property[i][j]).getNumHouses() == min && ((Property) property[i][j]).getNumHouses()<4){
							houseBuildableProperties.add((Property) property[i][j]);
						}
					}
				}
			}
		return houseBuildableProperties;
		}


	/*makes an array of properties that a player can build hotels on bases on
	 * if they have the monopoly, and restricts the player to build evenly
	 * across the properties of the monopoly*/
	public List<Property> getHotelBuildableProps(){
		ArrayList<Property> HotelBuildableProperties = new ArrayList<>();
		for (int i=0; i<7; i++){
			if ((boolean)property[i][0] == true){
				int parts = ((Property) property[i][1]).getNumOfParts();
				for(int j=1; j<=parts; i++){
					int min = getMinBuilt(i,parts);
					if (min == 4){
						HotelBuildableProperties.add((Property) property[i][j]);
					}
				}
			}
		}
		return HotelBuildableProperties;
	}

	/*makes an array of properties that a player can sell from restricting
	 * the player to maintain even building across the properties of the monopoly*/
	public List<Property> getPropWithSellable(){
		ArrayList<Property> PropWithSellable = new ArrayList<>();
		for (int i=0; i<7; i++){
			if ((boolean)property[i][0] == true){
				int parts = ((Property) property[i][1]).getNumOfParts();
				for(int j=1; j<=parts; i++){
					int max = getMaxBuilt(i,parts);
					if (((Property) property[i][j]).getNumHouses() == max){
						PropWithSellable.add((Property) property[i][j]);
					}
				}
			}
		}
		return PropWithSellable;
	}

	/*helper function to propertiesAvailableToBuild() that returns the max
	 * number of houses built on a single property within a monopoly*/
	public int getMinBuilt(int color, int parts){
		int min = 5;
		for (int i=1; i<= parts; i++){
			if (min > ((Property) property[color][i]).getNumHouses()){
				min = ((Property) property[color][i]).getNumHouses();
			}
		}
		return min;
	}

	public int getMaxBuilt(int color, int parts){
		int max = 0;
		for (int i=1; i<= parts; i++){
			if (max < ((Property) property[color][i]).getNumHouses()){
				max = ((Property) property[color][i]).getNumHouses();
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

	//builds a house, unless there are 4, where it builds a hotel
	public void buildHouse(Property prop){
		int currentHouses = prop.getNumHouses();
		if (currentHouses == 4){
			prop.buildHotel();
		}
		makePayment(prop.getHouseCost());
		prop.buildHouse();
	}

	public void buildHotel(Property prop){
		makePayment(prop.getHouseCost());
		prop.buildHotel();
	}



	public List<Deed> getPropertiesOwned() {
		ArrayList <Deed> allProp = new ArrayList<>();
		for(int i=0; i<8; i++){
			for(int j= 1; j<4; j++){
				if(property[i][j] != null){
					allProp.add((Deed) property[i][j]);
				}
			}
		}
		for(Railroad x: railroads){
			allProp.add(x);
		}
		for(Utility x: utilities){
			allProp.add(x);
		}
		return allProp;
	}
}
