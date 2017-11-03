package esof322.a3;

import java.util.Random;

public class Die {
	
	private int die;
	
	public Die() {
		Random rand = new Random();
		die = rand.nextInt(7);
	}
	
	public int getDie(){
		return die;
	}
}
