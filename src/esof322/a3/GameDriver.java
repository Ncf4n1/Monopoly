package esof322.a3;

import java.util.*;
import java.awt.Image;


import javax.swing.JOptionPane;

public class GameDriver
{
	private static int numPlayers;				// Number of Players playing the game (2-4)
	private static String[] playernames;		// List of the names for each player
	private static Player[] players;			// List of players playing the game
	private static int turnLimit;				// Time limit for the game
	//private static long endTime;
	private static Tokens[] tokens;
	private static boolean buyProperty = true;	// Determines if the player wants to buy a property
	private static Board board;
	private static Image boardImage;
	private static int currentPlayer = 0;					// Index of the current player for the player array
	private static int currentPlayerLocation = 0;			// Location of the current turn player after the dice roll
	private static Die die1 = new Die();
	private static Die die2 = new Die();
	private static int doublesInARow = 0;					// Number of times doubles has been rolled in a row
	private static int rollTotal = 0;						// Total for the dice roll
	private static int previousPlayerLocation = 0;			// Location of the current turn player prior to the dice roll
	private static Property currentLandedProperty = null;	// Placeholder for a property if the player lands on it
	private static Railroad currentLandedRailroad = null;	// Placeholder for railroad if the player lands on it
	private static Utility currentLandedUtility = null;	// Placeholder for utility if the player lands on it
    private static ArrayList<Property> propertiesAvailableToBuild = new ArrayList<>();
    private static int turns = 0;
    private static ChanceDeck chanceDeck = new ChanceDeck();
//    private static CommunityChest communityDeck = new CommunityChest();

	public static void main(String[] args)
	{
            GuiFrame.getInstance();
    }

    public static String getSpaceName()
    {
        return board.getSpace(currentPlayerLocation).getName();
    }

//	public static void startTurn(){
//		propertiesAvailableToBuild = (ArrayList) players[currentPlayer].propertiesAvailableToBuild();
//	}

	public static void endTurn(){
		if (currentPlayer == players.length -1)
		{
			currentPlayer = 0;
	        turns++;

	        if (turns >= turnLimit)
	        {
	        	setWinnerList();
	        }
		}
		else
		{
			currentPlayer++;
		}
		doublesInARow = 0;
		rollTotal = 0;
//        currentLandedProperty = null;
//        currentLandedRailroad = null;
//        currentLandedUtility = null;
	}

	public static int rollDice(){
		die1.rollDie();
		die2.rollDie();
		if(die1.getDie() == die2.getDie()){
			doublesInARow++;
		}
		else{
			doublesInARow = 0;
		}
			rollTotal = die1.getDie() + die2.getDie();
			return rollTotal;
	}
//	public static int getDie1(){
//		return die1.getDie();
//	}
//	public static int getDie2(){
//		return die2.getDie();
//	}
	public static int getDoublesInARow(){
		return doublesInARow;
	}

	public static int getTurnsTaken()
	{
		return turns;
	}

	public static void movePlayerToken(){
		previousPlayerLocation = players[currentPlayer].getLocation();
		currentPlayerLocation = players[currentPlayer].moveToken(rollTotal);
	}

    public static void passGo()
    {
        if (previousPlayerLocation + rollTotal > 39)
        {
            players[currentPlayer].takePayment(200);
        }
    }

	public static void checkSpace(int Location){
		JOptionPane.showMessageDialog(null, rollTotal + ", " +board.getSpace(Location).getName());
        currentLandedProperty = null;
        currentLandedRailroad = null;
        currentLandedUtility = null;
		// Handle which type of the square the player landed on
		switch (currentPlayerLocation)
		{
			// Community Chest, Free Parking, Go, and Both Jail Squares (Do Nothing)
			case 0: case 2:  case 10: case 17: case 20:  case 33:
					break;

			// Cases for Chance
			case 7: case 22: case 36: chanceDeck.drawCard(players[currentPlayer], board);
					break;

			case 30: players[currentPlayer].setJailedStat(true);
					 break;

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
                                        else if (currentLandedRailroad.getOwner() == null)
					{
                                            ButtonPanel.getInstance().enablePropertyButton();
					}
					break;

			// Case for landing on a utility and paying the correct rent based on dice roll or buying it
			case 12: case 28: currentLandedUtility = (Utility) board.getSpace(currentPlayerLocation);
                                        if ( (currentLandedUtility.getOwner() != null) && (!currentLandedUtility.getMortgageStat()))
                                        {
                                                if (currentLandedUtility.getOwner().getUtilitysOwned() == 1)
                                                {
                                                        players[currentPlayer].makePayment(4*rollTotal);
                                                        currentLandedUtility.getOwner().takePayment(4*rollTotal);
                                                }
                                                else if (currentLandedUtility.getOwner().getUtilitysOwned() == 2)
                                                {
                                                        players[currentPlayer].makePayment(10*rollTotal);
                                                        currentLandedUtility.getOwner().takePayment(10*rollTotal);
                                                }
                                        }
                                        else if (currentLandedUtility.getOwner() == null)
                                        {
                                            ButtonPanel.getInstance().enablePropertyButton();
                                        }
					break;

			// Handle if the player landed on a property
			default: currentLandedProperty = (Property) board.getSpace(currentPlayerLocation);

					 // Check if the property is owned and not mortgaged and if not, buy it if the user wants
					 if ( (currentLandedProperty.getOwner() != null) && (!currentLandedProperty.getMortgageStat()))
					 {
						 // Check if the property is part of a monopoly and has no houses (Must double rent)
						 if ( (currentLandedProperty.getOwner().checkForMonopoly(currentLandedProperty.getMonoColor())) && (currentLandedProperty.getNumHouses() == 0) )
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
                                         else if (currentLandedProperty.getOwner() == null)
					 {
                                             ButtonPanel.getInstance().enablePropertyButton();
					 }
				 	break;
		}

	}
	public static void buyProperty(){
            if (currentLandedProperty == null && currentLandedUtility == null)
            {
            	players[currentPlayer].buyProperty(currentLandedRailroad);
            }
            else if (currentLandedProperty == null && currentLandedRailroad == null)
            {
                players[currentPlayer].buyProperty(currentLandedUtility);
            }
            else if (currentLandedRailroad == null && currentLandedUtility == null)
            {
                players[currentPlayer].buyProperty(currentLandedProperty);
            }
            else
            {
            	System.out.println("Not bought");
            }
	}


	// Function used by the GUI to get the number of players
	public static void setPlayers(Player[] players)
	{
            GameDriver.players = players;
            numPlayers = players.length;
	}

	// Function used by the GUI to set the time limit of the game
	public static void setTurnLimit(int turnLimit)
	{
            GameDriver.turnLimit = turnLimit;
            //endTime = System.currentTimeMillis() + (GameDriver.timeLimit*60)*1000;
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

	public static void setWinnerList()
	{
		ArrayList<Player> winners = new ArrayList<>();
        Player winner = players[0];
        winner.calculateTotalWorth();
        // Cycle through players and determine who has the most money
        for (int i = 1; i < players.length; i++)
        {
        	players[i].calculateTotalWorth();
            if (players[i].getTotalWorth() > winner.getTotalWorth())
            {
                winner = players[i];
            }
        }
        winners.add(winner);
        for (int j = 0; j < players.length; j++)
        {
            if (!players[j].getName().equals(winner.getName()))
            {
                if (players[j].getTotalWorth() == winner.getTotalWorth())
                {
                    winners.add(players[j]);
                }
            }
        }

        ImagePanel.getInstance().declareWinner(winners);
	}

	public static void setBoardandTokens(String type){
		GameStyleFactory factory = new GameStyleFactory();
		GameStyle style = factory.getStyle(type);

		board = style.createBoard();
		tokens = style.createTokens();
		if(type.equalsIgnoreCase("Normal")){
			ImagePanel.setType(type);

		}
		else if(type.equalsIgnoreCase("Harry Potter")){
			ImagePanel.setType(type);
		}
		boardImage = board.getBoardImage();
	}
}
