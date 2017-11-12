package esof322.a3;

public class Space
{
	protected String name;
	protected int x;
	protected int y;
	
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
}
