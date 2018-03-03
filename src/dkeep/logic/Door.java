package dkeep.logic;

public class Door extends Interactive
{
	//Attributes
	char closeDoorSymb;
	char openDoorSymb;
	
	
	//Constructor
	public Door(int startX, int startY, char startSymb, char closed, char open)
	{
		super(startX,startY,startSymb);
		this.closeDoorSymb = closed;
		this.openDoorSymb = open;
	}
	
	//Methods
}
