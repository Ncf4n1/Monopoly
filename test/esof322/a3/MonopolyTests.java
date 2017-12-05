package esof322.a3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MonopolyTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	//BANK
	
	@Test 
	public void testBankSellHouse()
	{
		Bank.sellHouse();
		
		assertEquals("'sell' a house from bank", 31, Bank.getHouses());
		
		Bank.testHouse(32);
		Bank.testHotel(12);
	}
	
	@Test 
	public void testBankBuyBackHouse()
	{
		Bank.sellHouse();
		Bank.sellHouse();
		Bank.sellHouse();
		
		Bank.buyBackHouse();
		
		assertEquals("'buy' back a house from property", 30, Bank.getHouses());

		Bank.testHouse(32);
		Bank.testHotel(12);;	
		
	}
	
	@Test
	public void testBankSellHotel()
	{
		Bank.sellHotel();
		
		assertEquals("'sell' hotel from bank", 11, Bank.getHotels());
		
		Bank.buyBackHotel();
		
		Bank.testHouse(32);
		Bank.testHotel(12);
	}
	
	@Test
	public void testBankBuyBackHotel()
	{
		Bank.sellHotel();
		Bank.sellHotel();
		Bank.sellHotel();
		
		Bank.buyBackHotel();
		
		assertEquals("'buy' back  hotel from property", 10, Bank.getHotels());
		
		Bank.testHouse(32);
		Bank.testHotel(12);
	}

	
	//BOARD
	
	@Test
	public void testBoard ()
	{
		Board board = new Board("normal");
		
		assertEquals("Check for initialization of properties", "TennesseeAvenue", board.getSpace(18).getName());
		assertEquals("Check for initialization of Railroads", "PennsylvaniaRailroad", board.getSpace(15).getName());
		assertEquals("Check for initialization of utilities", "ElectricCompany", board.getSpace(12).getName());
	}
	
	@Test
	public void testBoardAlt ()
	{
		Board board = new Board("hp");
		
		assertEquals("Check for initialization of properties", "ShellCottage", board.getSpace(18).getName());
		assertEquals("Check for initialization of Railroads", "DurmstrangShip", board.getSpace(15).getName());
		assertEquals("Check for initialization of utilities", "OwlPost", board.getSpace(12).getName());
	}
	
	//LuxuryTax
	
	@Test
	public void testLuxuryTax ()
	{
		Player player = new Player("TestPlayerName", "TestToken");
		
		LuxuryTax tax = new LuxuryTax(200, 0, 0);
		
		tax.payLuxuryTax(player);
		
		assertEquals("Check if player paid tax", 1300, player.getMoneyTotal());
	}
	
	@Test
	public void testGetLuxury()
	{
		LuxuryTax tax = new LuxuryTax(200, 0, 0);
		
		assertEquals("Check if player paid tax", 200, tax.getTaxAmount());
	}
	
	//IncomeTax
	
		@Test
		public void testIncomeTax ()
		{
			Player player = new Player("TestPlayerName", "TestToken");
			
			IncomeTax tax = new IncomeTax(100, 0, 0);
			
			tax.payIncomeTax(player);
			
			assertEquals("Check income tax amount", 100, tax.getTaxAmount());
			assertEquals("Check if player paid tax", 1400, player.getMoneyTotal());
		}
	
	//GoToJail
	
	@Test
	public void testGoToJail()
	{
		Player player = new Player("TestPlayerName", "TestToken");
		
		GoToJail.sendToJail(player);
		
		assertTrue("Check if payer is sent to jail", player.getJailedStat());
	}
	
	//DEED
	
	Deed deed = new Deed ("TestName", 500, 250, 10, 20);
	
	@Test
	public void testDeedName ()
	{
		assertEquals("check for intialization of property", "TestName", deed.getName());
	}
	
	@Test
	public void testDeedPrice ()
	{
		assertEquals("check for intialization of property", 500, deed.getPrice());
	}
	
	@Test
	public void testDeedOwnerInitial ()
	{
		assertNull("check for intialization of property", deed.getOwner());
	}
	
	@Test
	public void testDeedOwnerChanged ()
	{
		Player player = new Player("Test", "Test");
		
		deed.setOwner(player);
		
		assertEquals("check for a change in ownership", "Test", deed.getOwner().getName());
	}
	
	@Test
	public void testDeedMortVal ()
	{
		assertEquals("check for getting the mortgage value", 250, deed.getMortgageVal());
	}
	
	@Test
	public void testDeedSetMortgage ()
	{
		assertFalse("check for a intial status of mortgage", deed.getMortgageStat());
		
		deed.mortgage();
		
		assertTrue("check for a change in mortgage status to true", deed.getMortgageStat());
		
		deed.unMortgage();
		
		assertFalse("check for a change in mortgage status to false", deed.getMortgageStat());
	}
	
	//GO
		
	@Test
	public void testGo ()
	{
		Player player = new Player("Test", "Test");
		Go go = new Go (0, 0);
		
		go.collectPassingGo(player);
		
		assertEquals("check for passing go money to player", 1700, player.getMoneyTotal());
	}
	
	//PROPERTY
	
	Property prop = new Property("MediterraneanAvenue", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 1, 1243, 1425, 0, 2);
	
	@Test
	public void testPropertyPrice ()
	{
		assertEquals("check for correct intialization of property", 60, prop.getPrice());
	}
	
	@Test
	public void testPropertyMortVal ()
	{
		assertEquals("Check for getting mortgage value", 30, prop.getMortgageValue());
	}
	
	@Test
	public void testPropertyInitRent ()
	{
		assertEquals("check for correct intialization of property", 2, prop.getRent());
	}
	
	@Test
	public void testPropertyInitHouses ()
	{
		assertEquals("check for correct intialization of property", 0, prop.getNumHouses());
	}
	
	@Test
	public void testPropertyAddHouses ()
	{
		Player player = new Player ("TestName", "TestToken");
		player.buyProperty(prop);
		prop.buildHouse();
		
		assertEquals("check for building a house on a property", 1, prop.getNumHouses());
		
		Bank.testHouse(32);
		Bank.testHotel(12);
	}
	
	@Test
	public void testPropertySellHouse ()
	{
		Player player = new Player("TestName", "TestToken");
		player.buyProperty(prop);
		prop.buildHouse();
		prop.sellHouse();
		
		assertEquals("check for selling a house", 0, prop.getNumHouses());
		assertEquals("check for payment of selling a house", 1465, player.getMoneyTotal());
		
		Bank.testHouse(32);
		Bank.testHotel(12);
	}
	
	@Test
	public void testPropertyBuildHotel() 
	{
		Player player = new Player("TestName", "TestToken");
		
		Property prop2 =  new Property("Boardwalk", 400, new int[] {50, 200, 600, 1400, 1700, 2000}, 200, 200, 2, 1425, 1243, 7, 2);
		
		player.buyProperty(prop2);
		
		prop2.buildHouse();
		prop2.buildHouse();
		prop2.buildHouse();
		prop2.buildHouse();
		
		prop2.buildHotel();
		
		assertEquals("Ensure bank has houses back after buying hotel", 32, Bank.getHouses());
		assertEquals("Ensure bank sells hotel", 11, Bank.getHotels());
	
		Bank.testHouse(32);
		Bank.testHotel(12);
	}
	
	@Test
	public void testSellHotelWithHousesAvailable()
	{
		Player player = new Player("TestName", "TestToken");
		
		Property prop2 =  new Property("Boardwalk", 400, new int[] {50, 200, 600, 1400, 1700, 2000}, 200, 200, 2, 1425, 1243, 7, 2);
		
		player.buyProperty(prop2);
		
		prop2.buildHouse();
		prop2.buildHouse();
		prop2.buildHouse();
		prop2.buildHouse();
		
		prop2.buildHotel();
		
		prop2.sellHotel();
		
		assertEquals("Check if houses given back to bank when hotel bought", 28, Bank.getHouses());
		assertEquals("Check if property has hotel", 4, prop2.getNumHouses());
	
		Bank.testHouse(32);
		Bank.testHotel(12);
	}
	
	@Test
	public void testSellHotelWithoutEnoughHouses()
	{
		Player player = new Player("TestName", "TestToken");
		
		Property prop2 =  new Property("Boardwalk", 400, new int[] {50, 200, 600, 1400, 1700, 2000}, 200, 200, 2, 1425, 1243, 7, 2);
		
		player.buyProperty(prop2);
		
		prop2.buildHouse();
		prop2.buildHouse();
		prop2.buildHouse();
		prop2.buildHouse();
		
		prop2.buildHotel();
		
		Bank.testHouse(2);
		
		assertEquals("Check houses available in Bank", 2, Bank.getHouses());

		prop2.sellHotel();
		assertEquals("Check if only available houses are added back to property", 2, prop2.getNumHouses());
		
		Bank.testHouse(32);
		Bank.testHotel(12);
	}
	
	@Test
	public void testPropertyHouseRent ()
	{
		prop.buildHouse();
		prop.buildHouse();
		
		assertEquals("check for change in rent", 30, prop.getRent());
	
		Bank.testHouse(32);
		Bank.testHotel(12);
	}
	
	@Test
	public void testMonopolyPart ()
	{
		assertEquals("check for the number in monopoly", 1, prop.getPartNumber());
	}
	
	@Test
	public void testPropertyNumofMonopolyParts ()
	{
		assertEquals("check for the number of monopoly part initialization", 2, prop.getNumOfParts());
	}
	
	//RAILROAD
	
	@Test
	public void testRailroad ()
	{
		Railroad railroad = new Railroad("Test", 0, 0);
		Player player = new Player("Test", "Test");
		
		player.buyProperty(railroad);
		
		assertEquals("check for correct initialization of railroad", 25, railroad.getRent(player.getRailroadOwnedCount()));
	}
	
	//SPACE
	
	Space space = new Space ("Test", 0, 0);
	
	@Test
	public void testSpace ()
	{	
		assertEquals("check for correct initialization of space", "Test" , space.getName());
	}
	
	@Test
	public void testGetX ()
	{	
		assertEquals("check for correct initialization of space", 0 , space.getX());
	}
	
	@Test
	public void testGetY ()
	{	
		assertEquals("check for correct initialization of space", 0 , space.getY());
	}
	
	//UTILITY
	
	@Test
	public void testUtility ()
	{
		Utility util = new Utility ("Test", 0, 0);
		assertEquals("check for correct initialization of utility", "Test", util.getName());
	}
	
	//PLAYER
	Player player = new Player("TestPlayerName", "TestToken");
	
	@Test 
	public void testPlayerName ()
	{
		assertEquals("check for correct initialization of player", "TestPlayerName", player.getName());
	}
	
	@Test 
	public void testPlayerInitLocat ()
	{
		assertEquals("check for correct initialization of player", 0, player.getLocation());
	}
	
	@Test 
	public void testPlayerInitJailStat ()
	{
		assertFalse("check for correct initialization of player", player.getJailedStat());
	}
	
	@Test 
	public void testPlayerInitMoney ()
	{
		assertEquals("check for correct initialization of player", 1500, player.getMoneyTotal());
	}
	
	@Test 
	public void testPlayerChangeJailStat ()
	{
		player.setJailedStat(true);
		
		assertTrue("check for change in player jail status to in jail", player.getJailedStat());
		
		player.setJailedStat(false);
		
		assertFalse("check for change in player jail status to out of jail", player.getJailedStat());
	}
	
	@Test 
	public void testPlayerPayments ()
	{
		player.makePayment(500);
		
		assertEquals("check player make payment", 1000, player.getMoneyTotal());
		
		player.takePayment(1000);
		
		assertEquals("check player take payment", 2000, player.getMoneyTotal());
	}
	
	@Test 
	public void testPlayerLocationChange ()
	{
		player.moveToken(10);
		
		assertEquals("check player location change", 10, player.getLocation());
	}
	
	@Test 
	public void testPlayerLocationPast39 ()
	{
		player.moveToken(56);
		
		assertEquals("check player location overflow", 16, player.getLocation());
	}
	
	@Test 
	public void testPlayerBuyProperty ()
	{
		Property prop = new Property("MediterraneanAvenue", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 1, 1243, 1425, 0, 2);
		player.buyProperty(prop);
		
		assertEquals("check for correct values of player when buying property", 1440, player.getMoneyTotal());
		assertEquals("check for correct values of property when bought", player, prop.getOwner());
	}

	@Test
	public void testPlayerBuildHouseNot4()
	{
		Property prop = new Property("IllinoisAvenue", 240, new int[] {20, 100, 300, 750, 925, 1100}, 150, 120, 3, 625, 75, 4, 3);
		
		player.buyProperty(prop);
		
		player.buildHouse(prop);
		
		assertEquals("Check player money after building house", 1110, player.getMoneyTotal());
		assertEquals("Check houses on property", 1, prop.getNumHouses());
	
		Bank.testHouse(32);
		Bank.testHotel(12);
	}
	
	@Test
	public void testPlayerBuildHouse4()
	{
		Property prop = new Property("IllinoisAvenue", 240, new int[] {20, 100, 300, 750, 925, 1100}, 150, 120, 3, 625, 75, 4, 3);
		
		player.buyProperty(prop);
		
		player.buildHouse(prop);
		player.buildHouse(prop);
		player.buildHouse(prop);
		player.buildHouse(prop);
		
		assertEquals("Check player money after building house", 660, player.getMoneyTotal());
		assertEquals("Check houses on property", 4, prop.getNumHouses());
	}
	
	@Test
	public void testPlayerBuildHotel()
	{
		Property prop = new Property("IllinoisAvenue", 240, new int[] {20, 100, 300, 750, 925, 1100}, 150, 120, 3, 625, 75, 4, 3);
		
		player.buyProperty(prop);
		
		player.buildHouse(prop);
		player.buildHouse(prop);
		player.buildHouse(prop);
		player.buildHouse(prop);
		player.buildHotel(prop);
		
		assertEquals("Check player money after building house", 510, player.getMoneyTotal());
		assertEquals("Check houses on property", 5, prop.getNumHouses());
	
		Bank.testHouse(32);
		Bank.testHotel(12);
	}
	
	@Test 
	public void testPlayerBuyRailroad ()
	{
		Railroad rr = new Railroad("ReadingRailroad", 750, 1425);
		player.buyProperty(rr);
		
		assertEquals("check for correct values of player when buying railroad", 1, player.getRailroadOwnedCount());
		assertEquals("check for correct values of player when buying railroad", 1300, player.getMoneyTotal());
		assertEquals("check for correct values of railroad when bought", player, rr.getOwner());
	}
	
	@Test 
	public void testPlayerBuyUtility ()
	{
		Utility u = new Utility("ElectricCompany", 75, 1119);
		player.buyProperty(u);
		
		assertEquals("check for correct values of player when buying utility", 4, player.getUtilitysMultiplyer());
		assertEquals("check for correct values of player when buying utility", 1350, player.getMoneyTotal());
		assertEquals("check for correct values of utlility wwhen bought", player, u.getOwner());
	}
	
	@Test
	public void testPlayerMortgage () 
	{
		Property prop = new Property("MediterraneanAvenue", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 1, 1243, 1425, 0, 2);
		player.buyProperty(prop);
		
		player.mortgage(prop);
		
		assertEquals("check for correct values of player when mortgage property", 1470, player.getMoneyTotal());
		assertTrue("check for correct values of property after mortgage", prop.getMortgageStat());
	}
	
	@Test
	public void testPlayerHouseMaxMin ()
	{
		Property prop = new Property("MediterraneanAvenue", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 1, 1243, 1425, 0, 2);
		Property prop2 =  new Property("BalticAvenue", 60, new int[] {4, 20, 60, 180, 320, 450}, 50, 30, 2, 994, 1425, 0, 2);
		player.buyProperty(prop);
		player.buyProperty(prop2);
		
		prop.buildHouse();
		prop2.buildHouse();
		prop.buildHouse();

		
		assertEquals("Check for correct value of max houses built on properties", 2, player.getMaxBuilt(prop.getMonoColor(), prop.getNumOfParts()));
		assertEquals("Check for correct value of min houses built on properties", 1, player.getMinBuilt(prop.getMonoColor(), prop.getNumOfParts()));
	
		Bank.testHouse(32);
		Bank.testHotel(12);
	}
	
	@Test
	public void testFalseCheckForMonopoly() 
	{
		Player play = new Player("TestPlayerName", "TestToken"); 
		Property prop = new Property("NewYorkAvenue", 200, new int[] {16, 80, 220, 600, 800, 1000}, 100, 100, 3, 75, 262, 3, 3);
		//Property prop =  new Property("BalticAvenue", 60, new int[] {4, 20, 60, 180, 320, 450}, 50, 30, 2, 994, 1425, 0, 2);
		
		play.buyProperty(prop);
		
		assertEquals("check for correct values of player when not having a monopoly", false, play.checkForMonopoly(prop.getMonoColor()));
	}
	
	@Test
	public void testTrueCheckForMonopoly() 
	{
		Property prop = new Property("MediterraneanAvenue", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 1, 1243, 1425, 0, 2);
		Property prop2 =  new Property("BalticAvenue", 60, new int[] {4, 20, 60, 180, 320, 450}, 50, 30, 2, 994, 1425, 0, 2);
		player.buyProperty(prop);
		player.buyProperty(prop2);
		
		assertTrue("check for correct values of player when having a monopoly", player.checkForMonopoly(prop.getMonoColor()));
	}
	
	@Test
	public void testPlayerRailroadCount ()
	{
		Railroad rr1 = new Railroad("ReadingRailroad", 750, 1425);
		Railroad rr2 = new Railroad("PennsylvaniaRailroad", 75, 750);
		
		player.buyProperty(rr1);
		player.buyProperty(rr2);
		
		assertEquals("check for correct values of player when owning multiple railroads", 2, player.getRailroadOwnedCount());
	}
	
	@Test
	public void testPlayerUtilityCount ()
	{
		Utility u1 = new Utility("ElectricCompany", 75, 1119);
		Utility u2 = new Utility("WaterWorks", 1119,75);
		
		player.buyProperty(u1);
		player.buyProperty(u2);
		
		assertEquals("check for correct values of player when owning multiple utilities", 10, player.getUtilitysMultiplyer());
	}
	
	@Test
	public void testPlayerBuildablePropsNone()
	{	
		assertEquals("Check for buildable properties", 0, player.getHouseBuildableProps().size());
	}
	
	@Test
	public void testPlayerBuildablePropsSome()
	{
		Property prop = new Property("MediterraneanAvenue", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 1, 1243, 1425, 0, 2);
		Property prop2 =  new Property("BalticAvenue", 60, new int[] {4, 20, 60, 180, 320, 450}, 50, 30, 2, 994, 1425, 0, 2);
		player.buyProperty(prop);
		player.buyProperty(prop2);
		
		assertEquals("Check for buildable properties", 2, player.getHouseBuildableProps().size());
	
		Bank.testHouse(32);
		Bank.testHotel(12);
	}
	
	@Test
	public void testPlayerPropsWithHotels() 
	{
		Property prop = new Property("MediterraneanAvenue", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 1, 1243, 1425, 0, 2);
		Property prop2 =  new Property("BalticAvenue", 60, new int[] {4, 20, 60, 180, 320, 450}, 50, 30, 2, 994, 1425, 0, 2);
		player.buyProperty(prop);
		player.buyProperty(prop2);
		
		player.buildHouse(prop);//prop.buildHouse();
		player.buildHouse(prop2);//prop2.buildHouse();
		player.buildHouse(prop);//prop.buildHouse();
		player.buildHouse(prop2);//prop2.buildHouse();
		player.buildHouse(prop);//prop.buildHouse();
		player.buildHouse(prop2);//prop2.buildHouse();
		player.buildHouse(prop);//prop.buildHouse();
		player.buildHouse(prop2);//prop2.buildHouse();
		
		player.buildHouse(prop);//prop.buildHotel();
		
		assertEquals("Check for Properties with hotels", 1, player.getPropsWithHotels().size());
	
		Bank.testHouse(32);
		Bank.testHotel(12);
	}
	
	@Test
	public void testTurnsInJailEqual0 ()
	{
		assertEquals("Check turns in jail when not in jail", 0, player.getTurnsInJail());
	}
	
	@Test
	public void testAddingTurnsInJail()
	{
		Player play = new Player("TestPlayerName", "TestToken");
		Player play2 = new Player("TestPlayerName2", "TestToken2");
		Player [] players = new Player [2];
		players[0] = play; players[1] = play2;
		GameDriver.setPlayers(players);
		
		play.setJailedStat(true);
		play.setLocation(10);
		GameDriver.rollDice();
		play.rollToGetOutOfJail(); // turns in jail = 1
		
		if(play.getJailedStat()) //check if still in jail
		{
			assertEquals("Check if turns incremented when still in jail", 1, play.getTurnsInJail());
			GameDriver.rollDice();
			play.rollToGetOutOfJail(); // turns in jail = 2
			if(play.getJailedStat()) //check if still in jail
			{
				assertEquals("Check if turns incremented when still in jail", 2, play.getTurnsInJail());
				GameDriver.rollDice();
				play.rollToGetOutOfJail(); //turns in jail = 3
				if(play.getJailedStat()) //check if still in jail
				{
					assertEquals("Check if turns incremented when still in jail", 3, play.getTurnsInJail());
					play.payToLeaveJail(); //subtract 50 from 1500 for payment
					assertEquals("Check to make sure player left jail", 0, play.getTurnsInJail());
					assertEquals("Check player paid to leave jail", 1450, play.getMoneyTotal());
				}
				else //else assert left jail
				{
					assertEquals("Check if player left jail", 0, play.getTurnsInJail());
				}
			}
			else //else assert left jail
			{
				assertEquals("Check if player left jail", 0, play.getTurnsInJail());
			}
		}
		else //assert left jail
		{
			assertEquals("Check if player left jail", 0, play.getTurnsInJail());
		}
	}
	
	@Test
	public void testgetHotelBuildablePropsWithNone()
	{
		Player play = new Player("TestPlayerName", "TestToken");
		ArrayList<Property> HotelBuildableProperties = new ArrayList<>();
		
		assertEquals("Check to make sure nothing is buidable", HotelBuildableProperties, play.getHotelBuildableProps());
	}
	
	@Test
	public void testgetHotelBuildablePropsWithOneProp()
	{
		Player play = new Player("TestPlayerName", "TestToken");
		Property prop = new Property("MediterraneanAvenue", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 1, 1243, 1425, 0, 2);
		play.buyProperty(prop);
		ArrayList<Property> HotelBuildableProperties = new ArrayList<>();
		
		assertEquals("Check to make sure nothing is buidable", HotelBuildableProperties, play.getHotelBuildableProps());
	}
	
	@Test
	public void testGetTotalWorthInit()
	{
		Player play = new Player("TestPlayerName", "TestToken");
		assertEquals("Check for initial worth of player", 0, play.getTotalWorth());
	}
	
	@Test
	public void testGetTotalWorthPost()
	{
		Player play = new Player("TestPlayerName", "TestToken");
		Property prop = new Property("MediterraneanAvenue", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 1, 1243, 1425, 0, 2);
		play.buyProperty(prop);
		
		Property prop2 =  new Property("BalticAvenue", 60, new int[] {4, 20, 60, 180, 320, 450}, 50, 30, 2, 994, 1425, 0, 2);
		play.buyProperty(prop2);
		play.mortgage(prop2);
		
		play.calculateTotalWorth();
		assertEquals("Check for initial worth of player", 1500, play.getTotalWorth());
	}
	
	@Test
	public void testGetNumHouses()
	{
		Player play = new Player("TestPlayerName", "TestToken");
		assertEquals("Check for num of houses", 0, play.getnumHouses());
	}
	
	@Test
	public void testGetNumHotels()
	{
		Player play = new Player("TestPlayerName", "TestToken");
		assertEquals("Check for num of hotels", 0, play.getnumHotels());
	}
	
	@Test
	public void testPlayerChanceGOOJ()
	{
		Player play = new Player("TestPlayerName", "TestToken");
		
		play.setChanceGetOutOfJailCard();
		
		assertTrue("Check for chance get out of jail card", play.checkForJailCard());
	}
	
	@Test
	public void testPlayerComChestGOOJ()
	{
		Player play = new Player("TestPlayerName", "TestToken");
		
		play.setComChestGetOutOfJailCard();
		
		assertTrue("Check for com chest get out of jail card", play.checkForJailCard());
	}
	
	@Test
	public void testPlayerUseChanceGOOJ()
	{
		Player play = new Player("TestPlayerName", "TestToken");
		
		play.setChanceGetOutOfJailCard();
		play.useJailCard();
		
		assertFalse("Check for us of chance get out of jail card", play.checkForJailCard());
	}
	
	@Test
	public void testPlayerUseComChestGOOJ()
	{
		Player play = new Player("TestPlayerName", "TestToken");
		
		play.setComChestGetOutOfJailCard();
		play.useJailCard();
		
		assertFalse("Check for use of com chest get out of jail card", play.checkForJailCard());
	}
	
	//Die
	
	@Test
	public void testDie ()
	{
		Die die = new Die();
		boolean rolled = false;
		die.rollDie();
		if (die.getDie() >= 1 && die.getDie() <= 6)
			rolled = true;
		assertTrue("Check to make sure rolled values are between 1 and 6, inclusively", rolled);
	}
	
	//Card
	
	Card card = new Card("TestName", 1, 10, 20, 20);
	
	@Test
	public void testCardName()
	{
		assertEquals("Check card name", "TestName", card.getTitle());
	}
	
	@Test
	public void testCardType()
	{
		assertEquals("Check card name", 1, card.getType());
	}
	
	@Test
	public void testCardIndex()
	{
		assertEquals("Check card name", 10, card.getNewIndex());
	}
	@Test
	public void testCardBill()
	{
		assertEquals("Check card name", 20, card.getBillAmount());
	}
	@Test
	public void testCardBonus()
	{
		assertEquals("Check card name", 20, card.getBonusAmount());
	}
	
	//GameDriver
	private Player play;
	private Player play2;
	
	public Player[] createPlayers()
	{
		play = new Player("TestPlayerName", "TestToken");
		play2 = new Player("TestPlayerName2", "TestToken2");
		Player [] players = new Player [2];
		players[0] = play; players[1] = play2;
		GameDriver.setPlayers(players);
		
		return players;
	}
	
	@Test
	public void testGameDriverTurnsTaken()
	{
		assertEquals("Get the num of turns taken", 0, GameDriver.getTurnsTaken());
	}
	
	@Test
	public void testGameDriverPlayers() 
	{
		createPlayers();
		
		assertEquals("Check if game driver returns the list players", "TestPlayerName", GameDriver.getPlayers()[0].getName());
	}
	
	@Test
	public void testGameDriverCurrentPlayer()
	{
		createPlayers();
		
		assertEquals("Check if game driver will return current player", "TestPlayerName", GameDriver.getCurrentPlayer().getName());
	}
	
	@Test
	public void testGameDriverPosition()
	{
		createPlayers();
		
		GameDriver.setBoardandTokens("normal");
		
		assertEquals("Check x coordinate", 1400, GameDriver.getXCoordinate(play));
		assertEquals("Check y coordinate", 1400, GameDriver.getYCoordinate(play));
	}
	
	@Test
	public void testGameDriverSpaceName()
	{
		createPlayers();
		
		GameDriver.setBoardandTokens("normal");
		
		assertEquals("Check space name", "Go", GameDriver.getSpaceName());
	}
	
	@Test
	public void testGameDriverEndTurn()
	{
		createPlayers();
		
		GameDriver.endTurn();
		
		assertEquals("Check for current player to iterate", "TestPlayerName2", GameDriver.getCurrentPlayer().getName());
	}
	
	@Test
	public void testGameDriverNextRound()
	{
		createPlayers();
		
		GameDriver.setTurnLimit(10);
		
		GameDriver.endTurn();
		GameDriver.endTurn();
		
		assertEquals("Check for current player to iterate", 1, GameDriver.getTurnsTaken());
	}
}
