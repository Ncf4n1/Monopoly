package esof322.a3;

import java.util.Random;

public class Die {
	
	private int die;
	
	public Die() {
	}
	
	public int getDie(){
		return die+1;
	}
	
	public void rollDie()
	{
		Random rand = new Random();
		die = rand.nextInt(6);
	}
}
