package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

/** Represents a Suspicious guard
 * @version 1.0
 * @since 1.0
 */
public class Suspicious extends Enemy{

	private ArrayList<Coord> coords = new ArrayList<Coord>();
	private int guardIndex;
	private boolean reverse;
	
	/** Creates a Suspicious guard with specified starting coordinate and patrol route
	 * @param pos
	 * 			Starting coordinate
	 * @param coords
	 * 			Array with coordinates of the patrol route of the guard
	 */
	public Suspicious(Coord pos, ArrayList<Coord> coords) {
		super(pos, Symbol.GUARD);
		this.coords = coords;
		guardIndex = 0;
	}

	/** Move Suspicious guard along his patrol route
	 * @param map
	 * 			Map in which the changes should be made
	 */
	@Override
	public void move(Map map) {
		Random rand = new Random();
		double change = rand.nextDouble();
		
		if(change > 0.8) {
			reverse = !reverse;
		}

		guardIndex = move_aux(guardIndex,coords,reverse,map);
	}
}
