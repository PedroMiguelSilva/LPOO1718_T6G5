package dkeep.logic;

import java.util.ArrayList;

public class Rookie extends Enemy
{
	//Attributes
	private ArrayList<Coord> coords = new ArrayList<Coord>();
	int guardIndex;
	
	//Constructor
	public Rookie(Coord pos, ArrayList<Coord> coords){
		super(pos,Symbol.GUARD);
		this.coords = coords;
		
		guardIndex = 0;
	}

	@Override
	public void move(Map map)
	{
		guardIndex = move_aux(guardIndex,coords,false,map);
	}
}
