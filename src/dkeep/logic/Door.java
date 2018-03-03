package dkeep.logic;

import java.util.ArrayList;

public class Door extends Interactive
{
	//Attributes
	private char closeDoorSymb;
	private char openDoorSymb;
	private boolean open;
	
	
	//Constructor
	public Door(int startX, int startY, char startSymb, char closed, char open)
	{
		super(startX,startY,startSymb);
		this.closeDoorSymb = closed;
		this.openDoorSymb = open;
		this.open = false;
	}
	
	//Methods
	public void trigger(Hero hero, ArrayList<Interactive> interactives,Map map)
	{
		if(open)
		{
			map.setChar(this.getX(), this.getY(), hero.getSymb());
			map.setChar(hero.getX(), hero.getY(), ' ');
		}
	}
	
	public void toggleDoor(Map map)
	{
		char finalSymb;
		
		if(this.open)		//close door
		{
			finalSymb = this.closeDoorSymb;
		}
		else				//open door
		{
			finalSymb = this.openDoorSymb;
		}
		
		map.setChar(this.getX(), this.getY(), finalSymb);
		this.open = !this.open;
	}
}
