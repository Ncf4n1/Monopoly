package esof322.a3;
import java.awt.Image;
import javax.swing.ImageIcon;

public class NormalBoard
{
  private static Space[] normalBoard = new Space[40];  //Data structure for the spaces on the board
	//Property Parameters: name, buy price, rent rates, house cost, mortgage value, first second or third part of a monopoly, x coordinate on the board, y coordinate on the board, monopoly color, properties in monopoly
	//Railroad Parameters: Name, x coordinate, y coordinate
	//Utility Parameters: Name, x coordinate, y coordinate
  public static Image normalboardImage;   //image of the normal monopoly board

  public NormalBoard(){
    normalBoard[0] = new Go(1400, 1400);
    normalBoard[1] = new Property("MediterraneanAvenue", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 1, 1243, 1425, 0, 2);
    normalBoard[2] = new CommunityChest(1119, 1425);
    normalBoard[3] = new Property("BalticAvenue", 60, new int[] {4, 20, 60, 180, 320, 450}, 50, 30, 2, 994, 1425, 0, 2);
    normalBoard[4] = new IncomeTax(200, 871, 1425);
    normalBoard[5] = new Railroad("ReadingRailroad", 750, 1425);
    normalBoard[6] = new Property("OrientalAvenue", 100, new int[] {6, 30, 90, 270, 400, 550}, 50, 50, 1, 626, 1425, 1, 3);
    normalBoard[7] = new Chance(506, 1425);
    normalBoard[8] = new Property("VermontAvenue", 100, new int[] {6, 30, 90, 270, 400, 550}, 50, 50, 2, 386, 1425, 1, 3);
    normalBoard[9] = new Property("ConnecticutAvenue", 120, new int[] {8, 40, 100, 300, 450, 600}, 50, 60, 3, 262, 1425, 1, 3);
    normalBoard[10] = new Jail(25, 1475);
    normalBoard[11] = new Property("St.CharlesPlace", 140, new int[] {10, 50, 150, 450, 625, 750}, 100, 70, 1, 75,1243, 2, 3);
    normalBoard[12] = new Utility("ElectricCompany", 75, 1119);
    normalBoard[13] = new Property("StatesAvenue", 140, new int[] {10, 50, 150, 450, 625, 750}, 100, 70, 2, 75, 994, 2, 3);
    normalBoard[14] = new Property("VirginiaAvenue", 160, new int[] {12, 60, 180, 500, 700, 900}, 100, 80, 3, 75, 871, 2, 3);
    normalBoard[15] = new Railroad("PennsylvaniaRailroad", 75, 750);
    normalBoard[16] = new Property("St.JamesPlace", 180, new int[] {14, 70, 200, 550, 750, 950}, 100, 90, 1, 75, 626, 3, 3);
    normalBoard[17] = new CommunityChest(75, 506);
    normalBoard[18] = new Property("TennesseeAvenue", 180, new int[] {14, 70, 200, 550, 750, 950}, 100, 90, 2, 75, 386, 3, 3);
    normalBoard[19] = new Property("NewYorkAvenue", 200, new int[] {16, 80, 220, 600, 800, 1000}, 100, 100, 3, 75, 262, 3, 3);
    normalBoard[20] = new FreeParking(100, 100);
    normalBoard[21] = new Property("KentuckyAvenue", 220, new int[] {18, 90, 250, 700, 875, 1050}, 150, 110, 1, 262, 75, 4, 3);
    normalBoard[22] = new Chance(386, 75);
    normalBoard[23] = new Property("IndianaAvenue", 220, new int[] {18, 90, 250, 700, 875, 1050}, 150, 110, 2, 506, 75, 4, 3);
    normalBoard[24] = new Property("IllinoisAvenue", 240, new int[] {20, 100, 300, 750, 925, 1100}, 150, 120, 3, 625, 75, 4, 3);
    normalBoard[25] = new Railroad("BAndORailroad", 750, 75);
    normalBoard[26] = new Property("AtlanticAvenue", 260, new int[] {22, 110, 330, 800, 975, 1150}, 150, 130, 1, 871, 75, 5, 3);
    normalBoard[27] = new Property("VentnorAvenue", 260, new int[] {22, 110, 330, 800, 975, 1150}, 150, 130, 2, 994, 75, 5, 3);
    normalBoard[28] = new Utility("WaterWorks", 1119,75);
    normalBoard[29] = new Property("Marvin Gardens", 280, new int[] {24, 120, 360, 850, 1025, 1200}, 150, 140, 3, 1243,75, 5, 3);
    normalBoard[30] = new GoToJail(1400, 75);
    normalBoard[31] = new Property("PacificAvenue", 300, new int[] {26, 130, 390, 900, 1100, 1275}, 200, 150, 1, 1425, 262, 6, 3);
    normalBoard[32] = new Property("NorthCarolinaAvenue", 300, new int[] {26, 130, 390, 900, 1100, 1275}, 200, 150, 2, 1425, 386, 6, 3);
    normalBoard[33] = new CommunityChest(1425,506);
    normalBoard[34] = new Property("PennsylvaniaAvenue", 320, new int[] {28, 150, 450, 1000, 1200, 1400}, 200, 160, 3, 1425, 626, 6, 3);
    normalBoard[35] = new Railroad("ShortLine", 1425, 750);
    normalBoard[36] = new Chance(1425, 871);
    normalBoard[37] = new Property("ParkPlace", 350, new int[] {35, 175, 500, 1100, 1300, 1500}, 200, 175, 1, 1425, 994, 7, 2);
    normalBoard[38] = new LuxuryTax(100, 1425, 1119);
    normalBoard[39] = new Property("Boardwalk", 400, new int[] {50, 200, 600, 1400, 1700, 2000}, 200, 200, 2, 1425, 1243, 7, 2);

    normalboardImage = new ImageIcon(this.getClass().getResource("BoardResized.png")).getImage();

  }
  //return the normal board array of spaces
  public Space[] getBoard(){
    return normalBoard;
  }

  //return the normal image of the board
  public Image getImage(){
    return normalboardImage;
  }
}
