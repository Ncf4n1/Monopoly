package esof322.a3;


public class GameDriver 
{
	private static int numPlayers;
	private static Player[] players;
	private static long timeLimit;

	public static void main(String[] args) 
	{
		Gui gui = new Gui();
		Board board = new Board();
		
		long endTime = System.currentTimeMillis() + timeLimit*1000;
		while (System.currentTimeMillis() < endTime)
		{
			
			
			
			
			
			
			
			
			
		}
		
	}
	
	public static void setNumPlayers(int players)
	{
		GameDriver.numPlayers = players;
	}
	
	public static void setPlayers(Player[] players)
	{
		GameDriver.players = players;
	}
	
	public static void setTimeLimit(long timeLimit)
	{
		GameDriver.timeLimit = timeLimit;
	}
}
