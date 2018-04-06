package dkeep.logic;

import java.util.ArrayList;

public class Lever extends Interactive
{
	//Attributes
	private boolean isActive;
	private ArrayList<Coord> coords = new ArrayList<Coord>();
	
	//Constructor
	public Lever(int startX, int startY,ArrayList<Coord> coords)
	{
		super(new Coord(startX,startY),Symbol.LEVER);
		isActive = false;
		this.coords = coords;
	}
	
	//Methods
	public void trigger(Hero hero, ArrayList<Interactive> interactives,Map map)
	{
		map.move(hero, getCoord());
		//para cada coordenada que a alavanca "funciona"
		for(int i = 0; i < coords.size();i++)
		{
			for(Interactive current : interactives) {
				if(current instanceof Door && current.getCoord().equals(coords.get(i)))
				{
					((Door) current).toggleDoor();
				}
			}
		}
		
		//toggle the lever
		isActive = !isActive;
	}
}
