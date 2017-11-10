package esof322.a3;


public class GameDriver 
{
	private static int numPlayers;
	private static String[] playernames;
	private static Player[] players;
	private static long timeLimit;

	public static void main(String[] args) 
	{
		Gui gui = new Gui();
		Board board = new Board();
		int currentPlayer = 0;
		int rollTotal = 0;
		int previousPlayerLocation = 0;
		int currentPlayerLocation = 0;
		
		long endTime = System.currentTimeMillis() + (timeLimit*60)*1000;
		while (System.currentTimeMillis() < endTime)
		{
			previousPlayerLocation = players[currentPlayer].getLocation();
			rollTotal = players[currentPlayer].rollTwoDice();
			currentPlayerLocation = players[currentPlayer].moveToken(rollTotal);
			
			if ( (previousPlayerLocation <= 39) && (currentPlayerLocation >= 0) )
			{
				players[currentPlayer].takePayment(200);
			}
			
			switch (currentPlayerLocation)
			{
				case 2: case 7: case 10: case 17: case 20: case 22: case 30: case 33: case 36: break;
			
				case 4: players[currentPlayer].makePayment(200);
				case 38: players[currentPlayer].makePayment(100);
			
			}
			
			
			
			
			
			
			
			
			
			
			
		}
		
	}
	
	public static void setNumPlayers(int players)
	{
		GameDriver.numPlayers = players;
	}
	
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
	
	public static void setTimeLimit(long timeLimit)
	{
		GameDriver.timeLimit = timeLimit;
	}
}
