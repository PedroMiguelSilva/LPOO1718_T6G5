package dkeep.logic;

import java.util.ArrayList;

public class Rookie extends Enemy
{
	//Attributes
	private ArrayList<Coord> coords = new ArrayList<Coord>();
	int guardIndex;
	
	//Constructor
	public Rookie(int startX, int startY, ArrayList<Coord> coords){
		super(startX,startY,Symbol.GUARD);
		this.coords = coords;
		
		guardIndex = 0;
	}

	@Override
	public void move(Map map)
	{
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
