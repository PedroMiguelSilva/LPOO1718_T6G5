package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

public class Suspicious extends Enemy{

	private ArrayList<Coord> coords = new ArrayList<Coord>();
	private int guardIndex;
	private boolean reverse;
	
	public Suspicious(Coord pos, ArrayList<Coord> coords) {
		super(pos, Symbol.GUARD);
		this.coords = coords;
		guardIndex = 0;
	}

	@Override
	public void move(Map map) {
		Random rand = new Random();
		double change = rand.nextDouble();
		
		if(change > 0.9) {
			reverse = !reverse;
		}

		guardIndex = move_aux(guardIndex,coords,reverse,map);
	}

	public int getIndex() {
		return this.guardIndex;
	}
	
	public int getCoordSize() {
		return coords.size();
	}
}
