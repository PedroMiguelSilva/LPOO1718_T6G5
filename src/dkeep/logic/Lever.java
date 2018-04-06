package dkeep.logic;

import java.util.ArrayList;

public class Lever extends Interactive
{
	private boolean isActive;
	private ArrayList<Coord> coords = new ArrayList<Coord>();
	
	/* Creates a Lever with specified starting position and consequences
	 * @param startX
	 * 				Starting x value
	 * @param startY
	 * 				Starting y value
	 * @param coords
	 * 				Array with coordinates of elements the Lever activates
	 */
	public Lever(int startX, int startY,ArrayList<Coord> coords)
	{
		super(new Coord(startX,startY),Symbol.LEVER);
		isActive = false;
		this.coords = coords;
	}
	
	/* Trigger the elements associated with the Key
	 * @param hero
	 * 			The hero that might trigguer an interactive
	 */
	public void trigger(Hero hero, ArrayList<Interactive> interactives,Map map)
	{
		map.move(hero, getCoord());
		for(int i = 0; i < coords.size();i++)
		{
			for(Interactive current : interactives) {
				if(current instanceof Door && current.getCoord().equals(coords.get(i)))
				{
					((Door) current).toggleDoor();
				}
			}
		}
		isActive = !isActive;
	}
}
