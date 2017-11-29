package esof322.a3;

public class GoToJail extends Space
{

	public GoToJail(int x, int y)
	{
		super("GoToJail", x, y);
	}
	
	public static void sendToJail(Player player)
	{
		player.setJailedStat(true);
	}
}
