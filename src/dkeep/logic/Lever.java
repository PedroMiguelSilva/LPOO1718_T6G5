package dkeep.logic;

public class Lever extends Interactive
{
	//Attributes
	private boolean isActive;
	private char xPos[];
	private char yPos[];
	
	//Constructor
	public Lever(int startX, int startY, char startSymb,char newXPos[],char newYPos[])
	{
		super(startX,startY,startSymb);
		isActive = false;
		this.xPos = newXPos;
		this.yPos = newYPos;
	}
	
	//Methods
	public void activate()
	{
		//trigger objects in xPos and yPos
		
		//toggle the lever
		isActive = !isActive;
	}
	
}
