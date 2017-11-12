package esof322.a3;

import static org.junit.Assert.*;

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
		Bank bank = new Bank();
		bank.sellHouse();
		
		assertEquals("'sell' a house from bank", 31, bank.getHouses());
	}
	
	@Test 
	public void testBankBuyBackHouse()
	{
		Bank bank = new Bank();
		bank.sellHouse();
		bank.sellHouse();
		bank.sellHouse();
		
		bank.buybackHouse();
		
		assertEquals("'buy' back a house from property", 30, bank.getHouses());
	}
	
	@Test
	public void testBankSellHotel()
	{
		Bank bank = new Bank();
		bank.sellHotel();
		
		assertEquals("'sell' hotel from bank", 11, bank.getHotels());
	}
	
	@Test
	public void testBankBuyBackHotel()
	{
		Bank bank = new Bank();
		bank.sellHotel();
		bank.sellHotel();
		bank.sellHotel();
		
		bank.buybackHotel();
		
		assertEquals("'buy' back  hotel from property", 10, bank.getHotels());
	}
	
	@Test
	public void testGiveMoney()
	{
		Player player = new Player ("Test", "Test");
		Bank bank = new Bank();
		
		bank.giveMoney(player, 500);
		
		assertEquals("test giving player money", 2000, player.getMoneyTotal());
	}
	
	//BOARD
	
	@Test
	public void testBoard ()
	{
		Board board = new Board();
		
		assertEquals("Check for initialization of properties", "TennesseeAvenue", board.getSpace(18).getName());
		assertEquals("Check for initialization of Railroads", "PennsylvaniaRailroad", board.getSpace(15).getName());
		assertEquals("Check for initialization of utilities", "ElectricCompany", board.getSpace(12).getName());
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
		prop.buildHouse();
		
		assertEquals("check for building a house on a property", 1, prop.getNumHouses());
	}
	
	@Test
	public void testPropertySellHouse ()
	{
		prop.buildHouse();
		prop.sellHouse();
		
		assertEquals("check for selling a house", 0, prop.getNumHouses());
	}
	
	@Test
	public void testPropertyHouseRent ()
	{
		prop.buildHouse();
		prop.buildHouse();
		
		assertEquals("check for change in rent", 30, prop.getRent());
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
	
	@Test
	public void testSpace ()
	{
		Space space = new Space ("Test", 0, 0);
		
		assertEquals("check for correct initialization of space", "Test" , space.getName());
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
		
		assertEquals("check for correct values of player when buying utility", 1, player.getUtilitysOwned());
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
	public void testPlayerHouseMax ()
	{
		Property prop = new Property("MediterraneanAvenue", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 1, 1243, 1425, 0, 2);
		Property prop2 =  new Property("BalticAvenue", 60, new int[] {4, 20, 60, 180, 320, 450}, 50, 30, 2, 994, 1425, 0, 2);
		player.buyProperty(prop);
		player.buyProperty(prop2);
		
		prop.buildHouse();
		prop2.buildHouse();
		prop.buildHouse();

		
		assertEquals("Check for correct value of max houses built on properties", 1, Player.getMaxBuilt(prop.getMonoColor(), prop.getNumOfParts()));
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
		
		assertEquals("check for correct values of player when owning multiple utilities", 2, player.getUtilitysOwned());
	}
}
