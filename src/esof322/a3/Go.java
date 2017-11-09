package esof322.a3;

public class Go extends Space
{
	
	public Go()
	{
		super("Go");
	}
	
	public void collectPassingGo(Player player)
	{
		player.takePayment(200);
	}
}
