package esof322.a3;

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
