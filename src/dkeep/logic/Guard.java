package dkeep.logic;

import java.util.ArrayList;

public class Guard extends Enemy
{
	//Attributes
	private ArrayList<Coord> coords = new ArrayList<Coord>();
	int guardIndex;
	
	//Constructor
	public Guard(int startX, int startY, ArrayList<Coord> coords){
		super(startX,startY,Symbol.GUARD);
		this.coords = coords;
		
		guardIndex = 0;
	}

	//Methodo
	public void move(Map map)
	{
		//local variables
		Coord coord = coords.get(guardIndex);
		
		//update index
		if(guardIndex == coords.size() - 1)
		{
			guardIndex = 0;
		}
		else
		{
			guardIndex++;
		}
		
		Coord fCoord = coords.get(guardIndex);
		
		map.move(this, fCoord);
	}
}
