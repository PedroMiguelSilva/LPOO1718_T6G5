package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

public class Drunken extends Enemy{

	private ArrayList<Coord> coords = new ArrayList<Coord>();
	private int guardIndex;
	private double oddSleep;
	private boolean sleeping;
	private int roundsSleeping;
	private boolean reverse;
	
	public Drunken(int startX, int startY, ArrayList<Coord> coords) {
		super(startX, startY, Symbol.GUARD);
		this.coords = coords;
		guardIndex = 0;
		oddSleep = 0.2;
		sleeping = false;
		roundsSleeping = 0;
		reverse = false;
	}

	@Override
	public void move(Map map) {
		
		Random rand = new Random();
		double keepMoving = rand.nextDouble();
		double changeDirection = rand.nextDouble();
		
		
		if(sleeping) {					//sleeping
			roundsSleeping -= 1;
			if(roundsSleeping == 0) {
				sleeping = false;
				this.setSymb(Symbol.GUARD);
			}
			if(changeDirection > 0.5) {	//change direction
				reverse = !reverse;				
			}
		}
		else if(keepMoving < oddSleep) {//starts sleeping
			 sleeping = true;
			 roundsSleeping = 3;
			 this.setSymb(Symbol.GUARD_SLEEP);
		}
		
		if(!sleeping) {
			guardIndex = getNextIndex(guardIndex,coords.size(),this.reverse);
			Coord fCoord = coords.get(guardIndex);
			map.move(this, fCoord);
		}
	}


}
