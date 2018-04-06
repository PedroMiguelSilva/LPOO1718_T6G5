package dkeep.logic;

import java.util.ArrayList;

public abstract class Guard extends Enemy{
	
	protected ArrayList<Coord> coords = new ArrayList<Coord>();
	protected int guardIndex;
	
	public Guard(Coord coord, ArrayList<Coord> coords) {
		super(coord,Symbol.GUARD);
		this.coords = coords;
	}
	
	private int getNextIndex(int index, int size, boolean reverse) {
		if(reverse)
			index -= 1;
		else
			index += 1;
		
		if(index == -1)
			return size-1;
		else if(index == size)
			return 0;
		else
			return index;
	}
	
	protected int move_aux(int index, ArrayList<Coord> coords,boolean reverse, Map map) {
		index = getNextIndex(index,coords.size(),reverse);
		Coord fCoord = coords.get(index);
		map.move(this,fCoord);
		return index;
	}
}
