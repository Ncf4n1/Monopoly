package esof322.pa4.team24;

public class Card {
	String title;
	int type;
	int newIndex;
	int billAmount;
	int bonusAmount;
	
	public Card(String title, int type, int newIndex, int billAmount, int bonusAmount){
		this.title = title;
		this.type = type;
		this.newIndex = newIndex;
		this.billAmount = billAmount;
		this.bonusAmount = bonusAmount;
	}
	
	public String getTitle(){
		return title;
	}
	
	public int getType(){
		return type;
	}
	
	public int getNewIndex(){
		return newIndex;
	}
	
	public int getBillAmount(){
		return billAmount;
	}
	
	public int getBonusAmount(){
		return bonusAmount;
	}
	
}
