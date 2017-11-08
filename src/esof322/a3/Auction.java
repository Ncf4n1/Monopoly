package esof322.a3;

public class Auction 
{
	private Property property;
	private int currentWinningBid;
	private Player currentWinningPlayer;
	
	public Auction(Property property)
	{
		this.property = property;
		currentWinningBid = 0;
		currentWinningPlayer = null;
	}
	
	public void placeNewBid(Player player, int amount)
	{
		if (amount <= currentWinningBid)
		{
			// Display error message saying that bid is not high enough
		}
		else
		{
			currentWinningBid = amount;
			currentWinningPlayer = player;
		}
	}
	
	public void awardWinner()
	{
		// give currentWinningPlayer the auctioned property
	}
}