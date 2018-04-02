package dkeep.logic;

/* Nominates an Entity
 * @version 1.0
 * @since 1.0
 */
abstract public class Entity
{
	private Coord coord;
	private Symbol symb;
		
	/* Create an Entity with specified starting position and symbol
	 * @param startX
	 * 			The Entity's x position
	 * @param startY
	 * 			The Entity's y position
	 * @param startSymb
	 * 			The Entity's start symbol
	 */
	public Entity(Coord pos, Symbol startSymb){
		this.coord = pos;
		symb = startSymb;
	}
	
	
	/* @return X coordinate of Entity
	 */
	public int getX()
	{
		return coord.getX();
	}
	
	/* @return Y coordinate of Entity
	 */
	public int getY()
	{
		return coord.getY();
	}
	
	/* @return Symbol of Entity
	 */
	public Symbol getSymb()
	{
		return this.symb;
	}
	
	/* Set new Symbol to Entity
	 * @param symb
	 * 			New symbol to be attributed to Entity
	 */
	public void setSymb(Symbol symb)
	{
		this.symb = symb;
	}
	
	/* @return Coordinate of Entity
	 */
	public Coord getCoord() {
		return coord;
	}
	
	/* Set Coordinate to Entity
	 * @param newCoord
	 * 			Coordinate to be attributed to Entity
	 */
	public void setCoord(Coord newCoord) {
		this.coord = newCoord;
	}
}
