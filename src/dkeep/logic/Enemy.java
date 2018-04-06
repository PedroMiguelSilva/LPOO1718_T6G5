package dkeep.logic;


/** Nominates an Enemy
 * @version 1.0
 * @since 1.0
 */
abstract public class Enemy extends Movable implements Move
{
	/** Create an Enemy Entity with specified starting position and symbol
	 * @param pos
	 * 			Position of Enemy
	 * @param startSymb
	 * 			The Entity's start symbol
	 */
	public Enemy(Coord pos, Symbol startSymb)
	{
		super(pos,startSymb);
	}
	
}
