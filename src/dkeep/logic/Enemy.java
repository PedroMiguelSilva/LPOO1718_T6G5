package dkeep.logic;

import java.util.ArrayList;

/* Nominates an Enemy
 * @version 1.0
 * @since 1.0
 */
abstract public class Enemy extends Movable implements Move
{
	/* Create an Enemy Entity with specified starting position and symbol
	 * @param startX
	 * 			The Entity's x position
	 * @param startY
	 * 			The Entity's y position
	 * @param startSymb
	 * 			The Entity's start symbol
	 */
	public Enemy(Coord pos, Symbol startSymb)
	{
		super(pos,startSymb);
	}
	
	/* Calculate the next index in the patrol route according
	 * @param index
	 * 			Current index on the patrol route
	 * @param size
	 * 			Size of patrol route
	 * @param reverse
	 * 			Direction of the guard in patrol route
	 * @return Index of next index on the patrol route
	 */
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
	
	/* Move enemy on the map
	 * @param index
	 * 			Current index on the patrol route
	 * @param coords
	 * 			Array of coordinates of the patrol route
	 * @param reverse
	 * 			Direction of the guard in patrol route
	 * @param map
	 * 			Map in which changes should be made
	 */
	protected int move_aux(int index, ArrayList<Coord> coords,boolean reverse, Map map) {
		index = getNextIndex(index,coords.size(),reverse);
		Coord fCoord = coords.get(index);
		map.move(this,fCoord);
		return index;
	}
}
