package esof322.a3;


public class GameDriver 
{
	private static int numPlayers;
	private static Player[] players;

	public static void main(String[] args) 
	{
		Gui gui = new Gui();
		Board board = new Board();
		
		
		for (int i = 0; i < numPlayers; i++)
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
}
