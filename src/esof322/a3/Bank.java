package esof322.a3;

public final class Bank {
	
	private static int houses = 32;
	private static int hotels = 12;
	
	private Bank() {
	}
	
	//deduct 1 from the number  of houses
	public static void sellHouse() {		
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
