package dkeep.logic;

import java.util.ArrayList;

public class Lever extends Interactive
{
	//Attributes
	private boolean isActive;
	private ArrayList<Coord> coords = new ArrayList<Coord>();
	
	//Constructor
	public Lever(int startX, int startY, char startSymb,int newXPos[],int newYPos[])
	{
		super(startX,startY,startSymb);
		isActive = false;
		for(int i = 0; i < newXPos.length; i++) {
			Coord coord = new Coord(newXPos[i],newYPos[i]);
			coords.add(coord);
		}
		
	}
	
	//Methods
	public void trigger(Hero hero, ArrayList<Interactive> interactives,Map map)
	{
		//para cada coordenada que a alavanca "funciona"
		for(int i = 0; i < coords.size();i++)
		{
			for(Interactive current : interactives) {
				if(current instanceof Door && current.getCoord().equals(coords.get(i)))
				{
					((Door) current).toggleDoor(map);
				}
			}
		}
		
		//toggle the lever
		isActive = !isActive;
	}
}
