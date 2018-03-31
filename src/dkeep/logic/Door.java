package dkeep.logic;

import java.util.ArrayList;

/*
 * Represents a Door
 * @version 1.0
 * @since 1.0
 */
public class Door extends Interactive
{
	private boolean open;
	
	/* Creates a Door with specified x and y position
	 * @param startX
	 * 			The Door's x position
	 * @param startY
	 * 			The Door's y position
	 */
	public Door(int startX, int startY){
		super(startX,startY,Symbol.DOOR_CLOSED);
		this.open = false;
	}
	
	/* Creates a Door with specified coordinates
	 * @param coord
	 * 			Object with values of x and y
	 */
	public Door(Coord coord) {
		this(coord.getX(), coord.getY());
	}

	/* Hero moves towards the Door
	 * Hero opens the Door with Key if it's closed, moves Hero on top of Door if it's open
	 * @param hero
	 * 			Hero that is moving against the Door
	 * @param interactives
	 * 			Array of Interactive objects that might be triggered
	 * @param map
	 * 			Map in which the updates should be made
	 */
	public void trigger(Hero hero, ArrayList<Interactive> interactives,Map map)
	{
		if(open)
		{
			map.move(hero, this.getCoord());
		}
		else if(hero.getKey().getDoorCoord().equals(this.getCoord()))
		{
			//just open the door by changing the boolean and the symbol of the door
			open = true;
			this.setSymb(Symbol.DOOR_OPEN);
		}
	}
	
	/* Open or close door
	 */
	public void toggleDoor(){
		if(open)						    //close door
			setSymb(Symbol.DOOR_CLOSED);
		else								//open door
			setSymb(Symbol.DOOR_OPEN);
		
		this.open = !open;
	}
}
