package dkeep.logic;

import java.util.Random;

/* Represents a Coordinate
 * @version 1.0
 * @since 1.0
 */
public class Coord {

	private int x;
	private int y;

	/* Creates a Coord with specified x and y position
	 * @param x
	 *			The Coord's x value
	 * @param y
	 * 			The Coord's y value 
	 */
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/* Creates a Coord with the values of another Coord
	 * @param coord
	 * 			The Coord of which values should be copied to create a new Coord
	 */
	public Coord(Coord coord) {
		this.x = coord.getX();
		this.y = coord.getY();
	}

	/* Gets the Coord's x value
	 * @return x value of Coord
	 */
	public int getX() {
		return this.x;
	}

	/* Gets the Coord's y value
	 * @return y value of Coord
	 */
	public int getY() {
		return this.y;
	}

	/* Increments the x value of Coord
	 */
	public void incX() {
		this.x +=1;
	}

	/* Increments the y value of Coord
	 */
	public void incY() {
		this.y += 1;
	}

	/* Decrements the x value of Coord
	 */
	public void decX() {
		this.x -= 1;
	}

	/* Decrements the y value of Coord
	 */
	public void decY() {
		this.y -= 1;
	}

	/* Sets values of Coord with those given in coord
	 * @param coord
	 * 			Coordinates with values of x and y to be copied
	 */
	public void setCoord(Coord coord) {
		this.x = coord.getX();
		this.y = coord.getY();
	}

	/* Return a random Coord Adjacent to this Coord
	 * @return Random adjacent coordinate
	 */
	public Coord getRandomAdjacentCoord() {
		Random rand = new Random();
		int move = rand.nextInt(4);
		Coord newCoord = new Coord(this);

		switch(move) {
		case 0:
			newCoord.incX();break;
		case 1:
			newCoord.incY();break;
		case 2:
			newCoord.decX();break;
		case 3:
			newCoord.decY();break;
		}
		return newCoord;
	}

	/* Return a Coord Adjacent to this Coord according to the command given
	 * @param cmd
	 * 			Command given by user
	 * @return Adjacent coordinate
	 */
	public Coord getAdjacentCoord(Cmd cmd) {
		Coord newCoord = new Coord(this);
		switch(cmd) {
		case LEFT:
			newCoord.decY();break;
		case UP:
			newCoord.decX();break;
		case DOWN:
			newCoord.incX();break;
		case RIGHT:
			newCoord.incY();break;
		case START:case QUIT:
		default:
		}
		return newCoord;
	}

	/* Returns if two Coord objects are equal based on its x and y values
	 * @Note: if the values of x and y are both 0 then its considered true
	 * @return if Coords are equal or not
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;

		if(getX() == 0 && getY() == 0)
			return true;
		Coord coord = (Coord) obj;
		if(coord.getX() == 0 && coord.getY() == 0)
			return true;

		if(this.getX() == coord.getX() && this.getY() == coord.getY())
			return true;
		else
			return false;
	}
}
