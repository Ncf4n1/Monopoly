package esof322.a3;

public class Board 
{
	static Space [] board = new Space [40];
	private static final int BROWN = 0;
	private static final int LIGHT_BLUE = 1;
	private static final int PINK = 2;
	private static final int ORANGE = 3;
	private static final int RED = 4;
	private static final int YELLOW = 5;
	private static final int GREEN = 6;
	private static final int DARK_BLUE = 7;
	
	
	public Board(){
		board[0] = new Go(1400, 1400);
		board[1] = new Property("MediterraneanAvenue", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 2, 1243, 1425, 0);
		board[2] = new CommunityChest(1119, 1425);
		board[3] = new Property("BalticAvenue", 60, new int[] {4, 20, 60, 180, 320, 450}, 50, 30, 2, 994, 1425, 0);
		board[4] = new IncomeTax(200, 871, 1425);
		board[5] = new Railroad("ReadingRailroad", 750, 1425);
		board[6] = new Property("OrientalAvenue", 100, new int[] {6, 30, 90, 270, 400, 550}, 50, 50, 3, 626, 1425, 1);
		board[7] = new Chance(506, 1425);
		board[8] = new Property("VermontAvenue", 100, new int[] {6, 30, 90, 270, 400, 550}, 50, 50, 3, 386, 1425, 1);
		board[9] = new Property("ConnecticutAvenue", 120, new int[] {8, 40, 100, 300, 450, 600}, 50, 60, 3, 262, 1425, 1);
		board[10] = new Jail(25, 1475);
		board[11] = new Property("St.CharlesPlace", 140, new int[] {10, 50, 150, 450, 625, 750}, 100, 70, 3, 75,1243, 2);
		board[12] = new Utility("ElectricCompany", 75, 1119);
		board[13] = new Property("StatesAvenue", 140, new int[] {10, 50, 150, 450, 625, 750}, 100, 70, 3, 75, 994, 2);
		board[14] = new Property("VirginiaAvenue", 160, new int[] {12, 60, 180, 500, 700, 900}, 100, 80, 3, 75, 871, 2);
		board[15] = new Railroad("PennsylvaniaRailroad", 75, 750);
		board[16] = new Property("St.JamesPlace", 180, new int[] {14, 70, 200, 550, 750, 950}, 100, 90, 3, 75, 626, 3);
		board[17] = new CommunityChest(75, 506);
		board[18] = new Property("TennesseeAvenue", 180, new int[] {14, 70, 200, 550, 750, 950}, 100, 90, 3, 75, 386, 3);
		board[19] = new Property("NewYorkAvenue", 200, new int[] {16, 80, 220, 600, 800, 1000}, 100, 100, 3, 75, 262, 3);
		board[20] = new FreeParking(100, 100);
		board[21] = new Property("KentuckyAvenue", 220, new int[] {18, 90, 250, 700, 875, 1050}, 150, 110, 3, 262, 75, 4);
		board[22] = new Chance(386, 75);
		board[23] = new Property("IndianaAvenue", 220, new int[] {18, 90, 250, 700, 875, 1050}, 150, 110, 3, 506, 75, 4);
		board[24] = new Property("IllinoisAvenue", 240, new int[] {20, 100, 300, 750, 925, 1100}, 150, 120, 3, 625, 75, 4);
		board[25] = new Railroad("BAndORailroad", 750, 75);
		board[26] = new Property("AtlanticAvenue", 260, new int[] {22, 110, 330, 800, 975, 1150}, 150, 130, 3, 871, 75, 5);
		board[27] = new Property("VentnorAvenue", 260, new int[] {22, 110, 330, 800, 975, 1150}, 150, 130, 3, 994, 75, 5);
		board[28] = new Utility("WaterWorks", 1119,75);
		board[29] = new Property("Marvin Gardens", 280, new int[] {24, 120, 360, 850, 1025, 1200}, 150, 140, 3, 1243,75, 5);
		board[30] = new GoToJail(1400, 75);
		board[31] = new Property("PacificAvenue", 300, new int[] {26, 130, 390, 900, 1100, 1275}, 200, 150, 3, 1425, 262, 6);
		board[32] = new Property("NorthCarolinaAvenue", 300, new int[] {26, 130, 390, 900, 1100, 1275}, 200, 150, 3, 1425, 386, 6);
		board[33] = new CommunityChest(1425,506);
		board[34] = new Property("PennsylvaniaAvenue", 320, new int[] {28, 150, 450, 1000, 1200, 1400}, 200, 160, 3, 1425, 626, 6);
		board[35] = new Railroad("ShortLine", 1425, 750);
		board[36] = new Chance(1425, 871);
		board[37] = new Property("ParkPlace", 350, new int[] {35, 175, 500, 1100, 1300, 1500}, 200, 175, 2, 1425, 994, 7);
		board[38] = new LuxuryTax(100, 1425, 1119);
		board[39] = new Property("Boardwalk", 400, new int[] {50, 200, 600, 1400, 1700, 2000}, 200, 200, 2, 1425, 1243, 7);
	}

	public static Space getSpace(int location) {
		return board[location];
	}
}
