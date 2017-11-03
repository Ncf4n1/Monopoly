package esof322.a3;

import java.util.ArrayList;

public class Player {
	public String playerName;
	public Token token;
	private int money;
	private ArrayList<Deed> property = new ArrayList<>();
	private int location;
	private boolean jailed;
	private int doublesRolled;
	private int turnsInJail;
	private int railroadsOwned;
	
	public Player(String name, Token seclectedToken){
		playerName = name;
		token = seclectedToken;
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
	}
	
	//RollTwoDice() is used for rollForTurnOrder() and move()
	public int rollTwoDice(){
		int die1 = rollDie();
		int die2 = rollDie();
		//need to check for doubles
		int total = die1 + die2;
		return total;
	}
	
	public void rollToGetOutOfJail(){
		int die1 = rollDie();
		int die2 = rollDie();
		//Need code for below sudo
		//check for doubles
			//if doubles rollToMove()
			//else check turnsInJail
				//if turnsInJail<3 
					//turnsInJail=turnsInJail+1
				//else payToLeaveJail and move(die1 + die2)
	}
	
	//rollDie() will get a single instance of a die
	public int rollDie(){
		Die roll1 = new Die();
		int die = roll1.getDie();
		return die;
	}
	
	
	
}
