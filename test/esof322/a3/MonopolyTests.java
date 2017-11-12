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
		
		assertEquals(31, bank.getHouses());
	}
	
	@Test 
	public void testBankBuyBackHouse()
	{
		Bank bank = new Bank();
		bank.sellHouse();
		bank.sellHouse();
		bank.sellHouse();
		
		bank.buybackHouse();
		
		assertEquals(30, bank.getHouses());
	}
	
	@Test
	public void testBankSellHotel()
	{
		Bank bank = new Bank();
		bank.sellHotel();
		
		assertEquals(11, bank.getHotels());
	}
	
	@Test
	public void testBankBuyBackHotel()
	{
		Bank bank = new Bank();
		bank.sellHotel();
		bank.sellHotel();
		bank.sellHotel();
		
		bank.buybackHotel();
		
		assertEquals(10, bank.getHotels());
	}
	
	@Test
	public void testGiveMoney()
	{
		Player player = new Player ("Test", "Test");
		Bank bank = new Bank();
		
		bank.giveMoney(player, 500);
		
		assertEquals(2000, player.getMoneyTotal());
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
		assertEquals("TestName", deed.getName());
	}
	
	@Test
	public void testDeedPrice ()
	{
		assertEquals(500, deed.getPrice());
	}
	
	@Test
	public void testDeedOwnerInitial ()
	{
		assertNull(deed.getOwner());
	}
	
	@Test
	public void testDeedOwnerChanged ()
	{
		Player player = new Player("Test", "Test");
		
		deed.setOwner(player);
		
		assertEquals("Test", deed.getOwner().getName());
	}
	
	@Test
	public void testDeedMortVal ()
	{
		assertEquals( 250, deed.getMortgageVal());
	}
	
	@Test
	public void testDeedSetMortgage ()
	{
		assertFalse(deed.getMortgageStat());
		
		deed.mortgage();
		
		assertTrue(deed.getMortgageStat());
		
		deed.unMortgage();
		
		assertFalse(deed.getMortgageStat());
	}
	
	//GO
		
	@Test
	public void testGo ()
	{
		Player player = new Player("Test", "Test");
		Go go = new Go (0, 0);
		
		go.collectPassingGo(player);
		
		assertEquals(1700, player.getMoneyTotal());
	}
	
	//PROPERTY
	
	Property prop = new Property ("MediterraneanAvenue", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 2, 1243, 1425, 0);
	
	@Test
	public void testPropertyInitOwner ()
	{
		assertNull(prop.getOwner());
	}
	
	@Test
	public void testPropertyOwnerChange ()
	{
		Player play = new Player ("NewPlayer", "TestToken");
		prop.setOwner(play);
		
		assertEquals(play, prop.getOwner());
	}
	
	@Test
	public void testPropertyInitRent ()
	{
		assertEquals(2, prop.getRent());
	}
	
	@Test
	public void testPropertyInitHouses ()
	{
		assertNull(prop.getOwner());
	}
	
	@Test
	public void testPropertyAddHouses ()
	{
		//prop.buildHouse();
		
		//assertEquals(1, prop.getNumberOfHouses());
	}
	
	@Test
	public void testPropertySellHouse ()
	{
		//prop.sellHouse();
		
		//assertEquals(0, prop.getNumberOfHouses());
	}
	
	@Test
	public void testPropertyHouseRent ()
	{
		//prop.buildHouse();
		//prop.buildHouse();
		
		//assertEquals(30, prop.getRent());
	}
	
	@Test
	public void testPropertyNumofMonopolyParts ()
	{
		assertEquals(2, prop.getNumberOfMonopolyParts());
	}
	
	//RAILROAD
	
	@Test
	public void testRailroad ()
	{
		Railroad railroad = new Railroad("Test", 0, 0);
		Player player = new Player("Test", "Test");
		
		player.buyProperty(railroad);
		
		assertEquals(25, railroad.getRent(player.getRailroadOwnedCount()));
	}
	
	//SPACE
	
	@Test
	public void testSpace ()
	{
		Space space = new Space ("Test", 0, 0);
		
		assertEquals("Test" , space.getName());
	}
	
	//UTILITY
	
	@Test
	public void testUtility ()
	{
		Utility util = new Utility ("Test", 0, 0);
		assertEquals("Test", util.getName());
	}
	
	//PLAYER
	Player player = new Player("TestPlayerName", "TestToken");
	
	@Test 
	public void testPlayerName ()
	{
		assertEquals("TestPlayerName", player.getName());
	}
	
	@Test 
	public void testPlayerInitLocat ()
	{
		assertEquals(0, player.getLocation());
	}
	
	@Test 
	public void testPlayerInitJailStat ()
	{
		assertFalse(player.getJailedStat());
	}
	
	@Test 
	public void testPlayerInitMoney ()
	{
		assertEquals(1500, player.getMoneyTotal());
	}
	
	@Test 
	public void testPlayerChangeJailStat ()
	{
		player.setJailedStat(true);
		
		assertTrue(player.getJailedStat());
		
		player.setJailedStat(false);
		
		assertFalse(player.getJailedStat());
	}
	
	@Test 
	public void testPlayerPayments ()
	{
		player.makePayment(500);
		
		assertEquals(1000, player.getMoneyTotal());
		
		player.takePayment(1000);
		
		assertEquals(2000, player.getMoneyTotal());
	}
	
	@Test 
	public void testPlayerLocationChange ()
	{
		player.moveToken(10);
		
		assertEquals(10, player.getLocation());
	}
	
	@Test 
	public void testPlayerLocationPast39 ()
	{
		player.moveToken(56);
		
		assertEquals(16, player.getLocation());
	}
	
	@Test 
	public void testPlayerCheckDoublesTrue ()
	{
		assertTrue(player.checkIfDoubles(3, 3));
	}	
	
	@Test 
	public void testPlayerCheckDoublesFalse ()
	{
		assertFalse(player.checkIfDoubles(1, 5));
	}
	
	@Test 
	public void testPlayerBuyProperty ()
	{
		Property prop = new Property ("MediterraneanAvenue", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 2, 1243, 1425, 0);
		player.buyProperty(prop);
		
		prop.setOwner(player);
		
		assertEquals(1, player.getPropertiesOwned());
		assertEquals(player, prop.getOwner());
	}
	
	@Test
	public void testFalseCheckForMonopoly() {
		Property prop = new Property("MediterraneanAvenue", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 2, 1243, 1425, 0);
		
		player.buyProperty(prop);
		
		assertFalse(player.checkForMonopoly(prop));
	}
	
	@Test
	public void testTrueCheckForMonopoly() {
		Property prop = new Property("MediterraneanAvenue", 60, new int[] {2, 10, 30, 90, 160, 250}, 50, 30, 2, 1243, 1425, 0);
		Property prop2 = new Property("BalticAvenue", 60, new int[] {4, 20, 60, 180, 320, 450}, 50, 30, 2, 994, 1425, 0);
		player.buyProperty(prop);
		player.buyProperty(prop2);
		
		assertEquals(1380, player.getMoneyTotal());
	}

}
