package esof322.a3;

public class Bank {
	
	private int houses, hotels;
	
	public Bank () {
		houses = 32;
		hotels = 12;
	}
	
	public void sellHouse(Property property, Player player) {
		
		if(player.checkForMonopoly(property) > property.getHousePrice()) {
			
		}
			
		
	}
	
	public void sellHotel() {
		
	}
	
	//give player money
	public void giveMoney(Player player, int money) {
		
		player.takePayment(money);
		
	}

	
}
