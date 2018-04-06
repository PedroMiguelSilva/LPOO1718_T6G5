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
	
	
	/* Creates a Drunken guard with specified starting coordinate and patrol route
	 * @param pos
	 * 			Starting coordinate
	 * @param coords
	 * 			Array with coordinates of the patrol route of the guard
	 */
	public Drunken(Coord pos, ArrayList<Coord> coords) {
		super(pos, Symbol.GUARD);
		this.coords = coords;
		guardIndex = 0;
		oddSleep = 0.2;
		sleeping = false;
		roundsSleeping = 0;
		reverse = false;
	}
	
	/* Move Drunken guard along his patrol route
	 * @param map
	 * 			Map in which the changes should be made
	 */
	@Override
	public void move(Map map) {
		updateSleepStatus();
		
		if(!sleeping) {
			guardIndex = move_aux(guardIndex,coords,reverse,map);
		}
	}

	
	/* Wake up the Drunken guard
	 */
	private void wakeUp() {
		Random rand = new Random();
		double changeDirection = rand.nextDouble();
		sleeping = false;
		this.setSymb(Symbol.GUARD);
		if(changeDirection > 0.5) {	//change direction
			reverse = !reverse;
		}
	}
	
	/* Update the sleeping status of Drunken guard
	 */
	private void updateSleepStatus() {
		Random rand = new Random();
		double keepMoving = rand.nextDouble();
		if(sleeping) {					//sleeping
			roundsSleeping -= 1;
			if(roundsSleeping == 0) {
				wakeUp();
			}
		}
		else if(keepMoving < oddSleep) {//starts sleeping
			 sleeping = true;
			 roundsSleeping = 3;
			 this.setSymb(Symbol.GUARD_SLEEP);
		}
	}

}
