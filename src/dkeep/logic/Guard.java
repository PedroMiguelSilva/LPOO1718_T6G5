package dkeep.logic;

import java.util.ArrayList;

public class Guard extends Enemy
{
	//Attributes
	private ArrayList<Coord> coords = new ArrayList<Coord>();
	int guardIndex;
	
	//Constructor
	public Guard(int startX, int startY, char startSymb, int xPos[], int yPos[])
	{
		super(startX,startY,startSymb);
		
		for(int i = 0; i < xPos.length;i++) {
			Coord coord = new Coord(xPos[i],yPos[i]);
			coords.add(coord);
		}
		
		guardIndex = 0;
	}

	//Methods
	public void move(Map map)
	{
		//local variables
		Coord coord = coords.get(guardIndex);
		
		//delete previous location
		map.setChar(coord, ' ');
		
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
				
		//print new location
		map.setChar(fCoord, this.getSymb());
	}
}
