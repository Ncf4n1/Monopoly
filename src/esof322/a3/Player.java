package esof322.a3;

import java.util.ArrayList;

public class Player {
	public String playerName;
	public String token;
	private int money;
	//private ArrayList<Deed> property = new ArrayList<>();
	private ArrayList<Deed> property = new ArrayList<>();
	private ArrayList<Deed> railroads = new ArrayList<>();
	private ArrayList<Deed> utilities = new ArrayList<>();
	private int location;
	private boolean jailed;
	private int doublesRolled;
	private int turnsInJail;
	private int railroadsOwned = railroads.size();
	private int utilitiesOwned = utilities.size();

	public Player(String name, String selectedToken){
		playerName = name;
		token = selectedToken;
		money = 1500;
		location = 0;
		jailed = false;
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

	public void makePayment(int amount){
		money = money - amount;
	}

	public void takePayment(int amount){
		money = money + amount;
	}

	public void moveToken(int spacesMoved){
		location = location + spacesMoved;
		//reset so as to stay in sync with board, and not have values go past possible
		if (location > 39){
			location = location - 39 - 1;
		}
	}

	//RollTwoDice() is used for rollForTurnOrder() and move()
	public int rollTwoDice(){
		int die1 = rollDie();
		int die2 = rollDie();

    if (checkIfDoubles(die1, die2)){
			if (doublesRolled < 3){
				//setnextplayer if doubles
			}
			else{
				//send player to jail
			}
		}

		int total = die1 + die2;
		return total;
	}

	//check for doubles
	public boolean checkIfDoubles(int die1, int die2){
		if(die1 == die2){
			doublesRolled = doublesRolled + 1;
			return true;
		}
		else
			return false;
	}

	//roll to leave jail
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
	}

	//add property to ArrayList, then make payment
	public void buyProperty(Deed obj){
		if (obj instanceof Property){
			property.add(obj);
		}
		else if (obj instanceof Railroad){
			railroads.add(obj);
		}
		else if (obj instanceof Utility){
			utilities.add(obj);
		}
		makePayment(obj.getPrice());
	}

	public int mortgage (Deed deed){
		takePayment(property.get(property.indexOf(deed)).getMortgageVal());
		//set deed as mortgaged
		//deed.setMortgage(true);
	}



	public boolean checkForMonopoly(Property prop){
		//count that indicates player owns property
		int count = 1;

		//for loop to iterate through property arraylist
		for(int i = 0; i < property.size(); i++){
			//check if property in arraylist has the same code as property being checked
			if(property.get(i).getMonoColor() == prop.getMonoColor())
				count++;
			//break out of loop and return true if count = numOfMonopolyParts
			if (count == prop.getNumberOfMonopolyParts())
				return true;
		}

		return false;
	}

	//returns the number of railroads owned
	public int getRailroadOwnedCount(){
		return railroadsOwned;
	}

	//adds current bought railroad to count
	public int addRailroad(){
		railroadsOwned++;
	}

	//removes railroad from count
	public int removeRailroad(){
		railroadsOwned--;
	}

	//rollDie() will get a single instance of a die
	public int rollDie(){
		Die roll1 = new Die();
		int die = roll1.getDie();
		return die;
	}
}
