package dkeep.logic;

abstract public class Enemy extends Entity
{
	//Attributes
	
	
	//Constructor
	public Enemy(int startX, int startY, Symbol startSymb)
	{
		super(startX,startY,startSymb);
	}
	
	
	//Methods
	abstract public void move(Map map);
}
