package esof322.a3;

import java.util.*;

import javax.swing.JOptionPane;

public class GameDriver
{
	private static int numPlayers;				// Number of Players playing the game (2-4)
	private static String[] playernames;		// List of the names for each player
	private static Player[] players;			// List of players playing the game
	private static int turnLimit;				// Time limit for the game
	//private static long endTime;
	private static boolean buyProperty = true;	// Determines if the player wants to buy a property
	private static Board board = new Board();
	private static int currentPlayer = 0;					// Index of the current player for the player array
	private static int currentPlayerLocation = 0;			// Location of the current turn player after the dice roll
	private static Die die1 = new Die();
	private static Die die2 = new Die();
	private static int doublesInARow = 0;					// Number of times doubles has been rolled in a row
	private static int rollTotal = 0;						// Total for the dice roll
	private static int previousPlayerLocation = 0;			// Location of the current turn player prior to the dice roll
	private static Property propLoction = null;	// Placeholder for a property if the player lands on it
	private static Railroad rRLocation = null;	// Placeholder for railroad if the player lands on it
	private static Utility utilLocation = null;	// Placeholder for utility if the player lands on it
    private static ArrayList<Property> propertiesAvailableToBuild = new ArrayList<>();
    private static int turns = 0;
    private static ChanceDeck chanceDeck = new ChanceDeck();
    private static CommunityChestDeck communityDeck = new CommunityChestDeck();
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

	public static void checkSpace(){
		Space location = board.getSpace(currentPlayerLocation);
		String turnInfo = gitTurnInfo(location);
		JOptionPane.showMessageDialog(null, die1.getDie() + " & " + die2.getDie() + ", " + location.getName());
		propLoction = null;
        rRLocation = null;
        utilLocation = null;
		// Handle which type of the square the player landed on
		switch (currentPlayerLocation){
			 // Go Space	
			case 0:		
				break;
				
			// Visiting Jail Square
		   case 10: 
			   break;
			   
			// Free Parking
		   case 20:  
				break;
				  
		    // Chance
		   case 7: case 22: case 36: 	 
				chanceDeck.drawCard(players[currentPlayer], board);
				break;
			
			// Community Chest
		   case 2: case 17: case 33: 	
				communityDeck.drawCard(players[currentPlayer]);
				break;	
					
			// Got to Jail
		   case 30: 		
				players[currentPlayer].setJailedStat(true);
				break;
	
			// Income Tax
		   case 4: 		
				players[currentPlayer].makePayment(((IncomeTax) location).getTaxAmount());
				break;
					
			// Luxury Tax
			case 38: 		
				players[currentPlayer].makePayment(((LuxuryTax) location).getTaxAmount());
				break;
	
			//Railroads
			case 5: case 15: case 25: case 35:  
				rRLocation = (Railroad) location;
				if ( (rRLocation.getOwner() != null) && (!rRLocation.getMortgageStat())){
					players[currentPlayer].makePayment(rRLocation.getRent(rRLocation.getOwner().getRailroadOwnedCount()));
				}
	            else if (rRLocation.getOwner() == null){
	               ButtonPanel.getInstance().enablePropertyButton();
				}
				break;
	
			// Utility
			case 12: case 28: 		
				utilLocation = (Utility) location;
	            if ((utilLocation.getOwner() != null) && (!utilLocation.getMortgageStat())) {                                          
	                players[currentPlayer].makePayment(utilLocation.getOwner().getUtilitysMultiplyer()*rollTotal);
	                utilLocation.getOwner().takePayment(utilLocation.getOwner().getUtilitysMultiplyer()*rollTotal);
	            }
	            else if (utilLocation.getOwner() == null){
	                ButtonPanel.getInstance().enablePropertyButton();
	            }
				break;
	
			// Handle if the player landed on a property
			default: 		
				propLoction = (Property) location;
				// Check if the property is owned and not mortgaged and if not, buy it if the user wants
				if ( (propLoction.getOwner() != null) && (!propLoction.getMortgageStat())){
					 
					// Check if the property is part of a monopoly and has no houses (Must double rent)
					if ( (propLoction.getOwner().checkForMonopoly(propLoction.getMonoColor())) && (propLoction.getNumHouses() == 0) ){
						players[currentPlayer].makePayment(2*propLoction.getRent());
						propLoction.getOwner().takePayment(2*propLoction.getRent());
					}
					else{
						players[currentPlayer].makePayment(propLoction.getRent());
						propLoction.getOwner().takePayment(propLoction.getRent());
					}
				}
	            	else if (propLoction.getOwner() == null){
	            		ButtonPanel.getInstance().enablePropertyButton();
				}
			 	break;
		}
		
	}
	
	public static String gitTurnInfo(Space location){
		String info = ("You have landed on: " + location.getName() + ".\n");
		if (location instanceof Deed){	
			Deed spaceDeed = (Deed) location;
			Player owner = null;
			owner = spaceDeed.getOwner();
			if(owner != null){
				int rent = getRentOwned(spaceDeed);	
				String rentInfo = ("You owe " + owner + "$"+ rent + ".\n");
				info = (info + rentInfo);
			}
			else{
				int price = spaceDeed.getPrice();
				String priceInfo = ("This is unowned. You may buy it for $"+ price + ".\n");
				info = (info + priceInfo);
			}
		}
		else{
			int tax = 0;
			if(location instanceof LuxuryTax){
				LuxuryTax taxSpace = (LuxuryTax) location;
				tax = taxSpace.getTaxAmount();
			}
			if(location instanceof IncomeTax){
				IncomeTax taxSpace = (IncomeTax) location;
				tax = taxSpace.getTaxAmount();
			}
			info = (info + "You are being taxed $" + tax + ".");
		}
		return info;
	}
	
	
	public static int getRentOwned(Deed space){
		int rent;
		if (space instanceof Railroad){
			Railroad railSpace = (Railroad) space;
			rent = railSpace.getRent(players[currentPlayer].getRailroadOwnedCount());
		}
		if (space instanceof Utility){
			Utility utilSpace = (Utility) space;
			rent = (utilSpace.getOwner().getUtilitysMultiplyer() * rollTotal);
		}
		else{
			Property propSpace = (Property) space;
			rent = propSpace.getRent();
		}
		return rent;
	}
	
	public static void buyProperty(){
            if (propLoction == null && utilLocation == null){
            	players[currentPlayer].buyProperty(rRLocation);
            }
            else if (propLoction == null && rRLocation == null){
                players[currentPlayer].buyProperty(utilLocation);
            }
            else if (rRLocation == null && utilLocation == null){
                players[currentPlayer].buyProperty(propLoction);
            }
            else{
            	System.out.println("Not bought");
            }
	}

	// Function used by the GUI to get the number of players
	public static void setPlayers(Player[] players){
            GameDriver.players = players;
            numPlayers = players.length;
	}

	// Function used by the GUI to set the time limit of the game
	public static void setTurnLimit(int turnLimit){
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
}
