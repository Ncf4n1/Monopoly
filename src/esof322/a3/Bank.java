package esof322.a3;

public final class Bank {
	
	private static int houses;
	private static int hotels;
	
	private Bank() {
		houses = 32;
		hotels = 12;
	}
	
	//deduct 1 from the number  of houses
	public void sellHouse() {		
		houses--;	
	}
	
	//deduct 1 from the number of hotels
	public static void sellHotel() {		
		hotels--;	
	}
	
	//add 1 to the number  of houses
	public static void buyBackHouse() {			
		houses++;		
	}
		
	//add 1 to the number of hotels
	public static void buyBackHotel() {
		hotels++;	
		houses = houses-4;
	}
	
	//get the number of houses left to build
	public static int getHouses(){
		return houses;
	}
	
	//get the number of hotels left to build
	public static int getHotels(){
		return hotels;
	}

	
}
