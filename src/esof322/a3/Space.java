package esof322.a3;

public class Space
{
	private String name;	// Name of the space
	private int x;			// x-coordinate of the space on the board for the GUI
	private int y;			// y-coordinate of the space on the board for the GUI

	public Space(String name, int x, int y)
	{
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public String getName()
	{
		return name;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
}
