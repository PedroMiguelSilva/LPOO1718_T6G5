package dkeep.logic;

import java.util.ArrayList;

/** Create a Rookie guard
 * @version 1.0
 * @since 1.0
 */
public class Rookie extends Guard{
	/** Creates a Rookie guard with specified starting coordinate and patrol route
	 * @param pos
	 * 			Starting coordinate
	 * @param coords
	 * 			Array with coordinates of the patrol route of the guard
	 */
	public Rookie(Coord pos, ArrayList<Coord> coords){
		super(pos,coords);
		this.coords = coords;
		
		guardIndex = 0;
	}

	/** Move rookie guard along his patrol route
	 * @param map
	 * 			Map in which the changes should be made
	 */
	@Override
	public void move(Map map){
		guardIndex = move_aux(guardIndex,coords,false,map);
	}
}
