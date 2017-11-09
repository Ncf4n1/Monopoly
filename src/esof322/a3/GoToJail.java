package esof322.a3;

public class GoToJail extends Space
{

	public GoToJail()
	{
		super("GoToJail");
	}
	
	public void sendToJail(Player player)
	{
		player.setJailedStat(true);
	}
}
