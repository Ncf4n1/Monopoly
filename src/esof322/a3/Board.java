package esof322.a3;

public class Board 
{
	Space [] board = new Space [40];
	
	public Board()
	{
		board[0] = new Go();
		board[1] = new Property("MediterraneanAvenue", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 2);
		board[2] = new CommunityChest();
		board[3] = new Property("BalticAvenue", 60, new int[] {4, 20, 60, 180, 320, 450}, 50, 30, 2);
		board[4] = new IncomeTax(200);
		board[5] = new Railroad("ReadingRailroad");
		board[6] = new Property("OrientalAvenue", 100, new int[] {6, 30, 90, 270, 400, 550}, 50, 50, 3);
		board[7] = new Chance();
		board[8] = new Property("VermontAvenue", 100, new int[] {6, 30, 90, 270, 400, 550}, 50, 50, 3);
		board[9] = new Property("ConnecticutAvenue", 120, new int[] {8, 40, 100, 300, 450, 600}, 50, 60, 3);
		board[10] = new Jail();
		board[11] = new Property("St.CharlesPlace", 140, new int[] {10, 50, 150, 450, 625, 750}, 100, 70, 3);
		board[12] = new Utility("ElectricCompany");
		board[13] = new Property("StatesAvenue", 140, new int[] {10, 50, 150, 450, 625, 750}, 100, 70, 3);
		board[14] = new Property("VirginiaAvenue", 160, new int[] {12, 60, 180, 500, 700, 900}, 100, 80, 3);
		board[15] = new Railroad("PennsylvaniaRailroad");
		board[16] = new Property("St.JamesPlace", 180, new int[] {14, 70, 200, 550, 750, 950}, 100, 90, 3);
		board[17] = new CommunityChest();
		board[18] = new Property("TennesseeAvenue", 180, new int[] {14, 70, 200, 550, 750, 950}, 100, 90, 3);
		board[19] = new Property("NewYorkAvenue", 200, new int[] {16, 80, 220, 600, 800, 1000}, 100, 100, 3);
		board[20] = new FreeParking();
		board[21] = new Property("KentuckyAvenue", 220, new int[] {18, 90, 250, 700, 875, 1050}, 150, 110, 3);
		board[22] = new Chance();
		board[23] = new Property("IndianaAvenue", 220, new int[] {18, 90, 250, 700, 875, 1050}, 150, 110, 3);
		board[24] = new Property("IllinoisAvenue", 240, new int[] {20, 100, 300, 750, 925, 1100}, 150, 120, 3);
		board[25] = new Railroad("BAndORailroad");
		board[26] = new Property("AtlanticAvenue", 260, new int[] {22, 110, 330, 800, 975, 1150}, 150, 130, 3);
		board[27] = new Property("VentnorAvenue", 260, new int[] {22, 110, 330, 800, 975, 1150}, 150, 130, 3);
		board[28] = new Utility("WaterWorks");
		board[29] = new Property("Marvin Gardens", 280, new int[] {24, 120, 360, 850, 1025, 1200}, 150, 140, 3);
		board[30] = new GoToJail();
		board[31] = new Property("PacificAvenue", 300, new int[] {26, 130, 390, 900, 1100, 1275}, 200, 150, 3);
		board[32] = new Property("NorthCarolinaAvenue", 300, new int[] {26, 130, 390, 900, 1100, 1275}, 200, 150, 3);
		board[33] = new CommunityChest();
		board[34] = new Property("PennsylvaniaAvenue", 320, new int[] {28, 150, 450, 1000, 1200, 1400}, 200, 160, 3);
		board[35] = new Railroad("ShortLine");
		board[36] = new Chance();
		board[37] = new Property("ParkPlace", 350, new int[] {35, 175, 500, 1100, 1300, 1500}, 200, 175, 2);
		board[38] = new LuxuryTax(100);
		board[39] = new Property("Boardwalk", 400, new int[] {50, 200, 600, 1400, 1700, 2000}, 200, 200, 2);
	}
}
