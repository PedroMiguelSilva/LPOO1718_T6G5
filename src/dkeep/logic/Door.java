package dkeep.logic;

import java.util.ArrayList;

public class Door extends Interactive
{
	//Attributes
	private boolean open;
	
	
	//Constructor
	public Door(int startX, int startY){
		super(startX,startY,Symbol.DOOR_CLOSED);
		this.open = false;
	}
	
	public Door(Coord c) {
		this(c.getX(), c.getY());
	}

	/*
	 * @brief if the door is open then move the hero on top of the door, if the door is closed then open the door
	 * 
	 */
	public void trigger(Hero hero, ArrayList<Interactive> interactives,Map map)
	{
		if(open)
		{
			//since this means the door is open then just move the hero to the cell on top of the door
			map.move(hero, this.getCoord());
			
			//map.setChar(getCoord(), hero.getSymb());
			//map.setChar(getCoord(), ' ');
		}
		else if(hero.getKey().getDoorCoord().equals(this.getCoord()))
		{
			//just open the door by changing the boolean and the symbol of the door
			
			open = true;
			this.setSymb(Symbol.DOOR_OPEN);
			//map.setChar(getCoord(), 'S');
		}
	}
	
	/*
	 * @brief just need to change the symbol of the door and its boolean
	 */
	public void toggleDoor(Map map){
		if(open)						//close door
			setSymb(Symbol.DOOR_CLOSED);
		else								//open door
			setSymb(Symbol.DOOR_OPEN);
		
		this.open = !open;
	}
}
