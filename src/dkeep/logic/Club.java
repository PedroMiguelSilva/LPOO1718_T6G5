package dkeep.logic;

/* Represents the Club of the an Ogre
 * @version 1.0
 * @since 1.0 
 */
public class Club extends Movable{

	
	/* Creates a Club with specified x and y position
	 * @param startX
	 * 			The Club's x position
	 * @param startY
	 * 			The Club's y position
	 */
	public Club(Coord pos) {
		super(pos, Symbol.OGRE_WEAPON);
		Symbol[] temp = {
				Symbol.OGRE,
				Symbol.OGRE_STUNED,
				Symbol.WALL,
				Symbol.DOOR_CLOSED,
				Symbol.DOOR_OPEN,
				Symbol.OGRE_WEAPON,
				Symbol.HERO_WITH_CLUB,
				Symbol.HERO_WITH_KEY
				};
		
		Symbol[] temp2 = {
				Symbol.CLEAR_SPACE
				};
		canWalkInto = temp2;
		cantWalkInto = temp;
	}

	
	/* Swings the Club
	 * @param map
	 * 			The map in which the Club is updated in
	 * @param ent
	 * 			Entity equipped with the Club
	 */
	public void swing(Map map, Entity ent) {
		Coord newCoord;

		if(noPossibleMove(map,ent.getCoord()))
			return;
		
		newCoord = getValidCoord(map,ent.getCoord());
		
		if(map.getBotEnt(newCoord).getSymb() == Symbol.KEY) {
			this.setSymb(Symbol.CLUB_ON_KEY);
		}
		else {
			this.setSymb(Symbol.OGRE_WEAPON);
		}
		
		map.move(this, newCoord);
	}
	
}
