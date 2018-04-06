package dkeep.logic;

/** Nominate a Neutral Entity
 * @version 1.0
 * @since 1.0
 */
public abstract class Neutral extends Entity{

	/** Creates a Neutral Entity with specified coordinate and symbol
	 * @param pos
	 * 			Coordinate of Entity
	 * @param startSymb
	 * 			Symbol of Entity
	 */
	public Neutral(Coord pos, Symbol startSymb) {
		super(pos, startSymb);
	}

}
