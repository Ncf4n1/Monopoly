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
		
		long endTime = System.currentTimeMillis() + (timeLimit*60)*1000;
		while (System.currentTimeMillis() < endTime)
		{
			players[currentPlayer].rollTwoDice();
			
			
			
			
			
			
			
			
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
