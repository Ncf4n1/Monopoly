package esof322.a3;

public class GoToJail extends Space
{

	public GoToJail()
	{
		super("Go To Jail");
	}
	
	public void sendToJail(Player player)
	{
		player.setJailedStat(true);
	}
}
