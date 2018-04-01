package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

/* Represents a Drunken guard
 * @version 1.0
 * @since 1.0
 */
public class Drunken extends Enemy{

	private ArrayList<Coord> coords = new ArrayList<Coord>();
	private int guardIndex;
	private double oddSleep;
	private boolean sleeping;
	private int roundsSleeping;
	private boolean reverse;
	
	
	/* Creates a Drunken guard with specified starting coordinates and patrol route
	 * @param startX
	 * 			The Drunken's start x position
	 * @param startY
	 * 			The Drunken's start y position
	 * @param coords
	 * 			Array with coordinates of the patrol route of the guard
	 */ 
	public Drunken(int startX, int startY, ArrayList<Coord> coords) {
		super(startX, startY, Symbol.GUARD);
		this.coords = coords;
		guardIndex = 0;
		oddSleep = 0.2;
		sleeping = false;
		roundsSleeping = 0;
		reverse = false;
	}

	/* @return Returns if the Drunken guard is sleeping or not
	 */
	public boolean isSleeping() {
		return this.sleeping;
	}
	
	/* Move Drunken guard along his patrol route
	 * @param map
	 * 			Map in which the changes should be made
	 */
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
			guardIndex = move_aux(guardIndex,coords,reverse,map);
		}
	}

}
