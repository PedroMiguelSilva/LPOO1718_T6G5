package dkeep.logic;

/** Represents a Wall
 * @version 1.0
 * @since 1.0
 */
public class Wall extends Neutral{
	
	/** Creates a Wall Entity with specified x and y position
	 * @param startX
	 * 			The Wall x position
	 * @param startY
	 * 			The Wall y position
	 */
	public Wall(int startX, int startY) {
		super(new Coord(startX,startY),Symbol.WALL);
	}
	
	/** Creates a Wall with specified coordinates
	 * @param coord
	 * 			The coordinate of the Wall
	 */
	public Wall(Coord coord) {
		super(coord,Symbol.WALL);
	}
	

}
