package esof322.pa4.team24;

public class Go extends Space
{
	
	public Go(int x, int y)
	{
		super("Go", x, y);
	}
	
	public void collectPassingGo(Player player)
	{
		player.takePayment(200);
	}
}
