package esof322.a3;

import java.util.*;

public class GameDriver
{
	private static int numPlayers;				// Number of Players playing the game (2-4)
	private static String[] playernames;		// List of the names for each player
	private static Player[] players;			// List of players playing the game
	private static long timeLimit;				// Time limit for the game
	private static boolean buyProperty = true;	// Determines if the player wants to buy a property
	private static Board board = new Board();
	private static int currentPlayer = 0;					// Index of the current player for the player array
	private static int currentPlayerLocation = 0;			// Location of the current turn player after the dice roll
	private static Die die1 = new Die();
	private static Die die2 = new Die();
	public static int doublesInARow = 0;					// Number of times doubles has been rolled in a row
	public static int rollTotal = 0;						// Total for the dice roll
	public static int previousPlayerLocation = 0;			// Location of the current turn player prior to the dice roll
	public static Property currentLandedProperty = null;	// Placeholder for a property if the player lands on it
	public static Railroad currentLandedRailroad = null;	// Placeholder for railroad if the player lands on it
	public static Utility currentLandedUtility = null;	// Placeholder for utility if the player lands on it


	public static void main(String[] args)
	{
		Gui gui = new Gui();
		while(gui.initialized == false){

		}
		ArrayList<Property> propertiesAvailableToBuild = new ArrayList<>();

		// Conversion of the time limit into milliseconds then stored as a long
		long endTime = System.currentTimeMillis() + (timeLimit*60)*1000;

		// Loop for a player taking a turn while within the time limit
		while (System.currentTimeMillis() < endTime)
		{
			propertiesAvailableToBuild = (ArrayList) players[currentPlayer].propertiesAvailableToBuild();

			previousPlayerLocation = players[currentPlayer].getLocation();
			rollTotal = rollDice();
			currentPlayerLocation = players[currentPlayer].moveToken(rollTotal);

			// Checks if the player is passing GO and if so, pay the player
			if ( (previousPlayerLocation <= 39) && (currentPlayerLocation >= 0) )
			{
				players[currentPlayer].takePayment(200);
			}

			// Handle which type of the square the player landed on
			switch (currentPlayerLocation)
			{
				// Cases for Chance, Community Chest, Free Parking, and Both Jail Squares (Do Nothing)
				case 2: case 7: case 10: case 17: case 20: case 22: case 30: case 33: case 36: break;

				// Cases for two Tax spaces (Make appropriate tax payment)
				case 4: players[currentPlayer].makePayment(200);
						break;
				case 38: players[currentPlayer].makePayment(100);
						break;

				// Case for landing on a railroad and either paying the correct rent or buying it
				case 5: case 15: case 25: case 35: currentLandedRailroad = (Railroad) board.getSpace(currentPlayerLocation);
						if ( (currentLandedRailroad.getOwner() != null) && (!currentLandedRailroad.getMortgageStat()))
						{
							players[currentPlayer].makePayment(currentLandedRailroad.getRent(currentLandedRailroad.getOwner().getRailroadOwnedCount()));
						}
						else
						{
							if (buyProperty)
							{
								players[currentPlayer].buyProperty(currentLandedRailroad);
							}
						}
						break;

				// Case for landing on a utility and paying the correct rent based on dice roll or buying it
				case 12: case 28: currentLandedUtility = (Utility) board.getSpace(currentPlayerLocation);
								  if ( (currentLandedUtility.getOwner() != null) && (!currentLandedUtility.getMortgageStat()))
								  {
									  if (currentLandedUtility.getOwner().getUtilitysOwned() == 1)
									  {
										  players[currentPlayer].makePayment(4*rollTotal);
									  }
									  else if (currentLandedUtility.getOwner().getUtilitysOwned() == 2)
									  {
										  players[currentPlayer].makePayment(10*rollTotal);
									  }
								  }

				// Handle if the player landed on a property
				default: currentLandedProperty = (Property) board.getSpace(currentPlayerLocation);

						 // Check if the property is owned and not mortgaged and if not, buy it if the user wants
						 if ( (currentLandedProperty.getOwner() != null) && (currentLandedProperty.getMortgageStat()))
						 {
							 // Check if the property is part of a monopoly and has no houses (Must double rent)
							 if ( (currentLandedProperty.getOwner().checkForMonopoly(currentLandedProperty)) && (currentLandedProperty.getNumberOfHouses() == 0) )
							 {
								 players[currentPlayer].makePayment(2*currentLandedProperty.getRent());
								 currentLandedProperty.getOwner().takePayment(2*currentLandedProperty.getRent());
							 }
							 else
							 {
								 players[currentPlayer].makePayment(currentLandedProperty.getRent());
								 currentLandedProperty.getOwner().takePayment(currentLandedProperty.getRent());
							 }
						 }
						 else
						 {
							 if (buyProperty)
							 {
								 players[currentPlayer].buyProperty(currentLandedProperty);
							 }
						 }

			}

			// Check if doubles were rolled and set the next player
			if (die1.getDie() != die2.getDie())
			{
				doublesInARow = 0;
				if (currentPlayer == players.length-1)
				{
					currentPlayer = 0;
				}
				else
				{
					currentPlayer++;
				}
			}
			else
			{
				doublesInARow++;

				if (doublesInARow == 3)
				{
					// Handle going to jail
				}
			}
		}

		// Determine the winner of the game
		Player[] winners = new Player[numPlayers];
		int winnersIndex = 0;
		winners[winnersIndex] = players[0];

		// Cycle through players and determine who has the most money
		for (int i = 1; i < players.length; i++)
		{
			if (players[i].getMoneyTotal() > winners[winnersIndex].getMoneyTotal())
			{
				winners[winnersIndex] = players[i];
			}

			// If multiple players have the same highest amount of money, check who has more properties
			else if (players[i].getMoneyTotal() == winners[winnersIndex].getMoneyTotal())
			{
				if (players[i].getPropertiesOwned() > winners[winnersIndex].getPropertiesOwned())
				{
					winners[winnersIndex] = players[i];
				}

				// If multiple players have the same highest amount of money and same number of properties, they are all winners
				else
				{
					winnersIndex++;
					winners[winnersIndex] = players[i];
				}
			}
		}
	}

	public static int rollDice(){
		die1.rollDie();
		die2.rollDie();
		if(die1.getDie() == die2.getDie())
			doublesInARow++;
		return die1.getDie() + die2.getDie();
	}

	public static void movePlayerToken(){
		currentPlayerLocation = players[currentPlayer].moveToken(rollTotal);
	}
	// Function used by the GUI to get the number of players
	public static void setNumPlayers(int players)
	{
		GameDriver.numPlayers = players;
	}

	// Function used by the GUI to get the input names of the players and initialize each player with a token
	public static void setPlayerNames(String[] names)
	{
		GameDriver.playernames = names;
		players = new Player[numPlayers];
		String[] tokens = {"Scottish Terrier", "Battleship", "Automobile", "Top Hat", "Penguin", "T-Rex", "Cat", "Rubber Ducky"};

		for (int i = 0; i < players.length; i++)
		{
			players[i] = new Player(GameDriver.playernames[i], tokens[i]);
		}
	}

	// Function used by the GUI to set the time limit of the game
	public static void setTimeLimit(long timeLimit)
	{
		GameDriver.timeLimit = timeLimit;
	}

	// FUnction used by the GUI to determine if the player wants to buy the property
	public static void setBuyProperty(boolean decision)
	{
		GameDriver.buyProperty = decision;
	}

	public static Player getCurrentPlayer(){
		return players[currentPlayer];
	}
	public static Player[] getPlayers(){
		return players;
	}

	public static int getXCoordinate(Player p){
		//return board.getSpace(currentPlayerLocation).getX();
		int x = 0;
		for(int i = 0; i < players.length; i++){
			if (players[i] == p)
				x = board.getSpace(players[i].getLocation()).getX();
		}
		return x;
	}

	public static int getYCoordinate(Player p){
		//return board.getSpace(currentPlayerLocation).getY();
		int y = 0;
		for(int i = 0; i < players.length; i++){
			if (players[i] == p)
				y = board.getSpace(players[i].getLocation()).getY();
		}
		return y;
	}
}
