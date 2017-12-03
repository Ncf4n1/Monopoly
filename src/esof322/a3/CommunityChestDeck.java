package esof322.a3;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CommunityChestDeck {
	static ArrayList<Card> deck = new ArrayList<Card>();
	static final int deckSize = 16;
	static boolean getOutOfJailCard = true;
	private final int PASS_GO_BONUS = 200;
	private final int HOUSE_REPAIR = 40;
	private final int HOTEL_REPAIR = 115;
	private final int OPERA_SEAT = 50;
	private Player currentPlayer;
	private Card card;
	
	
	public CommunityChestDeck(){
		//(String title, int type, int newIndex, int billAmount, int bonusAmount)
		deck.add(new Card ("Advance to go. Collect $200", 1, 0, 0, PASS_GO_BONUS));
		deck.add(new Card ("Bank error in your favor – Collect $200", 1, 0, 0, PASS_GO_BONUS));
		deck.add(new Card ("Doctor's fees. Pay $50", 3, 0, 50, 0));
		deck.add(new Card ("From sale of stock you get $50", 2, 0, 0, 50));
		deck.add(new Card ("Go to Jail. Do not pass Go, do not collect $200", 1, 10, 0, 0));
		deck.add(new Card ("Grand Opera Night  – Collect $50 from every player for opening night seats", 5, 0, 0, 50));
		deck.add(new Card ("Holiday Fund matures - Receive $100", 2, 0, 0, 100));	
		deck.add(new Card ("Income tax refund – Collect $20", 2, 0, 0, 20));	
		deck.add(new Card ("Life insurance matures – Collect $100", 2, 0, 0, 100));	
		deck.add(new Card ("Pay hospital fees of $100", 3, 0, 100, 0));
		deck.add(new Card ("Pay school fees of $150", 3, 0, 150, 0));
		deck.add(new Card ("You are assessed for street repairs – $40 per house – $115 per hotel", 4, 0, 0, 0));
		deck.add(new Card ("You have won second prize in a beauty contest – Collect $10", 2, 0, 0, 10));	
		deck.add(new Card ("You inherit $100", 2, 0, 0, 100));	
		deck.add(new Card ("Receive $25 consultancy fee", 2, 0, 0, 25));
		deck.add(new Card ("Get out of Jail Free. This card may be kept until needed.", 0, 0, 0, 0));
		}
	
	public void reShuffleCards(){
		if (getOutOfJailCard = true){	//check if get out of jail card is in the discard pile
			for (Card x : deck){
				deck.add(x);
			}
		}
		else{
			for (Card x : deck){
				deck.add(x);
			}
			deck.remove(15);
		}
	}
	
	public void drawCard(Player player){
		currentPlayer = player;
		if (deck.size()==0){
			reShuffleCards();
		}
		Random rand = new Random();
		int  randIndex = rand.nextInt(16);
		card = deck.get(randIndex);
		deck.remove(randIndex);
		JOptionPane.showMessageDialog(null, card.getTitle());
		cardResolution();
	}	
	
	public void displayCard(Card card){
		JOptionPane.showMessageDialog(null, card.getTitle());
	}

	public void cardResolution(){
		int funcType = card.getType();
		switch(funcType){
			case 1: 	//move
				currentPlayer.setLocation(card.getNewIndex());
				giveBonus();
				break;
				
			case 2:		//bonus
				giveBonus();
				
			case 3:		//bill
				payBill();
				
			case 4:		//street repair
				int houses = currentPlayer.getnumHouses();
				int hotels = currentPlayer.getnumHotels();
				currentPlayer.makePayment((houses * HOUSE_REPAIR) + (hotels * HOTEL_REPAIR));
				
			case 5:
				currentPlayer.takePayment(OPERA_SEAT * GameDriver.getPlayers().length);
				for(Player x: GameDriver.getPlayers()){
					x.makePayment(OPERA_SEAT);
				}		
			default: 			
				currentPlayer.setChanceGetOutOfJailCard();
				getOutOfJailCard =false;
		}
	}
	
	public void returnGetOutOfJailCard(){
		getOutOfJailCard = true;
	}

	public void payBill(){
		currentPlayer.makePayment(card.getBonusAmount());
	}
	
	public void giveBonus(){
		currentPlayer.takePayment(card.getBonusAmount());
	}
}
