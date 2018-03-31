package dkeep.logic;

/* Represents an Empty Entity. Used to fill and help manage the Map
 * @version 1.0
 * @since 1.0  
 */
public class Clear extends Neutral{

	/* Creates a Clear Entity with specified x and y position
	 * @param startX
	 * 			The Clear Entity x position
	 * @param startY
	 * 			The Clear Entity y position 
	 */
	public Clear(int startX, int startY) {
		super(startX, startY, Symbol.CLEAR_SPACE);
	}
	
	/* Creates a Clear Entity with specified coordinates
	 * @param coord
	 * 			The coordinate of the Clear Entity
	 */
	public Clear(Coord coord) {
		super(coord.getX(),coord.getY(),Symbol.CLEAR_SPACE);
	}

}
