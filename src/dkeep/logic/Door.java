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
			map.setChar(getCoord(), hero.getSymb());
			map.setChar(getCoord(), ' ');
		}
		else
		{
			if(hero.getKey().getDoorCoord().equals(this.getCoord()))
			{
				open = true;
				map.setChar(getCoord(), 'S');
			}
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
		
		map.setChar(getCoord(), finalSymb);
		this.open = !this.open;
	}
}
