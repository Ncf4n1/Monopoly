package esof322.a3;
import java.awt.Image;
import javax.swing.ImageIcon;

public class HarryPotterBoard
{
  private static Space[] harryPotterBoard = new Space[40];  //Data structure for the spaces on the board
	//Property Parameters: name, buy price, rent rates, house cost, mortgage value, first second or third part of a monopoly, x coordinate on the board, y coordinate on the board, monopoly color, properties in monopoly
	//Railroad Parameters: Name, x coordinate, y coordinate
	//Utility Parameters: Name, x coordinate, y coordinate
  public static Image hpboardImage;

  public HarryPotterBoard(){
    harryPotterBoard[0] = new Go(1400, 1400);
    harryPotterBoard[1] = new Property("CupboardUnderTheStairs", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 1, 1243, 1425, 0, 2);
    harryPotterBoard[2] = new CommunityChest(1119, 1425);
    harryPotterBoard[3] = new Property("ShriekingShack", 60, new int[] {4, 20, 60, 180, 320, 450}, 50, 30, 2, 994, 1425, 0, 2);
    harryPotterBoard[4] = new IncomeTax(200, 871, 1425);
    harryPotterBoard[5] = new Railroad("HogwartsExpress", 750, 1425);
    harryPotterBoard[6] = new Property("HagridsHut", 100, new int[] {6, 30, 90, 270, 400, 550}, 50, 50, 1, 626, 1425, 1, 3);
    harryPotterBoard[7] = new Chance(506, 1425);
    harryPotterBoard[8] = new Property("HogsHeadInn", 100, new int[] {6, 30, 90, 270, 400, 550}, 50, 50, 2, 386, 1425, 1, 3);
    harryPotterBoard[9] = new Property("HoneyDukes", 120, new int[] {8, 40, 100, 300, 450, 600}, 50, 60, 3, 262, 1425, 1, 3);
    harryPotterBoard[10] = new Jail(25, 1475);
    harryPotterBoard[11] = new Property("TheBurrow", 140, new int[] {10, 50, 150, 450, 625, 750}, 100, 70, 1, 75,1243, 2, 3);
    harryPotterBoard[12] = new Utility("OwlPost", 75, 1119);
    harryPotterBoard[13] = new Property("TheLeakyCauldron", 160, new int[] {10, 50, 150, 450, 625, 750}, 100, 70, 2, 75, 994, 2, 3);
    harryPotterBoard[14] = new Property("QuidditchPitch", 160, new int[] {12, 60, 180, 500, 700, 900}, 100, 80, 3, 75, 871, 2, 3);
    harryPotterBoard[15] = new Railroad("DurmstrangShip", 75, 750);
    harryPotterBoard[16] = new Property("WeasleysWizardWheezes", 180, new int[] {14, 70, 200, 550, 750, 950}, 100, 90, 1, 75, 626, 3, 3);
    harryPotterBoard[17] = new CommunityChest(75, 506);
    harryPotterBoard[18] = new Property("ShellCottage", 180, new int[] {14, 70, 200, 550, 750, 950}, 100, 90, 2, 75, 386, 3, 3);
    harryPotterBoard[19] = new Property("GreatHall", 200, new int[] {16, 80, 220, 600, 800, 1000}, 100, 100, 3, 75, 262, 3, 3);
    harryPotterBoard[20] = new FreeParking(100, 100);
    harryPotterBoard[21] = new Property("TheThreeBroomsticks", 220, new int[] {18, 90, 250, 700, 875, 1050}, 150, 110, 1, 262, 75, 4, 3);
    harryPotterBoard[22] = new Chance(386, 75);
    harryPotterBoard[23] = new Property("AstronomyTower", 220, new int[] {18, 90, 250, 700, 875, 1050}, 150, 110, 2, 506, 75, 4, 3);
    harryPotterBoard[24] = new Property("FlourishAndBlotts", 240, new int[] {20, 100, 300, 750, 925, 1100}, 150, 120, 3, 625, 75, 4, 3);
    harryPotterBoard[25] = new Railroad("FlyingCar", 750, 75);
    harryPotterBoard[26] = new Property("OllivandersWandShop", 260, new int[] {22, 110, 330, 800, 975, 1150}, 150, 130, 1, 871, 75, 5, 3);
    harryPotterBoard[27] = new Property("12GrimmauldPlace", 260, new int[] {22, 110, 330, 800, 975, 1150}, 150, 130, 2, 994, 75, 5, 3);
    harryPotterBoard[28] = new Utility("FlooNetwork", 1119,75);
    harryPotterBoard[29] = new Property("ChamberOfSecrets", 280, new int[] {24, 120, 360, 850, 1025, 1200}, 150, 140, 3, 1243,75, 5, 3);
    harryPotterBoard[30] = new GoToJail(1400, 75);
    harryPotterBoard[31] = new Property("LestrangeVault", 300, new int[] {26, 130, 390, 900, 1100, 1275}, 200, 150, 1, 1425, 262, 6, 3);
    harryPotterBoard[32] = new Property("HogwartsLibrary", 300, new int[] {26, 130, 390, 900, 1100, 1275}, 200, 150, 2, 1425, 386, 6, 3);
    harryPotterBoard[33] = new CommunityChest(1425,506);
    harryPotterBoard[34] = new Property("DumbledoresOffice", 320, new int[] {28, 150, 450, 1000, 1200, 1400}, 200, 160, 3, 1425, 626, 6, 3);
    harryPotterBoard[35] = new Railroad("KnightBus", 1425, 750);
    harryPotterBoard[36] = new Chance(1425, 871);
    harryPotterBoard[37] = new Property("RoomOfRequirement", 350, new int[] {35, 175, 500, 1100, 1300, 1500}, 200, 175, 1, 1425, 994, 7, 2);
    harryPotterBoard[38] = new LuxuryTax(75, 1425, 1119);
    harryPotterBoard[39] = new Property("GringottsWizardingBank", 400, new int[] {50, 200, 600, 1400, 1700, 2000}, 200, 200, 2, 1425, 1243, 7, 2);

    hpboardImage = new ImageIcon(this.getClass().getResource("HPBoard.png")).getImage();
  }
  public static Space[] getBoard(){
    return harryPotterBoard;
  }

  public static Image getImage(){
    return hpboardImage;
  }
}
