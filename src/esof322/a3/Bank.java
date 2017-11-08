package esof322.a3;

public class Bank {
	
	private int houses, hotels;
	
	public Bank () {
		houses = 32;
		hotels = 12;
	}
	
	//deduct 1 from the number  of houses
	public void sellHouse(Property property, Player player) {
		
		houses--;
		
	}
	
	//deduct 1 from the number of hotels
	public void sellHotel() {
		
		hotels--;
		
	}
	
	//add 1 to the number  of houses
	public void buybackHouse(Property property, Player player) {
			
		houses++;
			
	}
		
	//add 1 to the number of hotels
	public void buybacHotel() {
			
		hotels++;
		
	}
	
	//give player money
	public void giveMoney(Player player, int money) {
		
		player.takePayment(money);
		
	}
	
	//get the number of houses left to build
	public int getHouses()
	{
		return houses;
	}
	
	//get the number of hotels left to build
	public int getHotels()
	{
		return hotels;
	}

	
}
