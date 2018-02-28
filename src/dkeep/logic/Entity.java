package dkeep.logic;

abstract public class Entity
{
	//Attributes
	private int x;
	private int y;
	private char symb;
		
	
	//Constructor
	public Entity(int startX, int startY, char startSymb)
	{
		x = startX;
		y = startY;
		symb = startSymb;
	}
	
	
	//Methods
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public char getSymb()
	{
		return this.symb;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public void setSymb(char symb)
	{
		this.symb = symb;
	}
}
