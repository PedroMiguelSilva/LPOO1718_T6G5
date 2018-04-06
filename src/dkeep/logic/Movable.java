package dkeep.logic;

/** Nominates a Movable Entity
 * @version 1.0
 * @since 1.0
 */
public abstract class Movable extends Entity{

	protected Symbol[] cantWalkInto;
	protected Symbol[] canWalkInto;

	/** Create a movable object with specified coordinates and symbol
	 * @param pos 
	 * 			Coordinate of Movable Entity
	 * @param symb
	 * 			Symbol of Entity
	 */
	public Movable(Coord pos,Symbol symb) {
		super(pos,symb);
	}

	protected boolean noPossibleMove(Map map, Coord coord) {
		for(int i = 0; i < canWalkInto.length;i++) {
			if(map.isNearBy(coord, canWalkInto[i]))
				return false;
		}

		return true;
	}

	protected Coord getValidCoord(Map map, Coord coord) {

		Coord newCoord;
		do
		{
			newCoord = coord.getRandomAdjacentCoord();
		}while(map.isSymbolInCoord(newCoord, cantWalkInto));
		return newCoord;
	}
}
