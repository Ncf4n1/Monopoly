package esof322.pa4.team24;

import java.util.Random;

public class Die {

	private int die;

	public Die() {
	}

	//returns the die value
	public int getDie(){
		return die;
	}

	//get a random number between 1 and 6
	public void rollDie()
	{
		Random rand = new Random();
		die = rand.nextInt(6)+1;
	}
}
