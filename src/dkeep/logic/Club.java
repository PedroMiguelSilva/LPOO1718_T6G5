package dkeep.logic;

/* Represents the Club of the an Ogre
 * @version 1.0
 * @since 1.0 
 */
public class Club extends Entity{

	Symbol[] symbolClubDoesntSwingTo;
	
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
				Symbol.HERO
				};
		
		symbolClubDoesntSwingTo = temp;
	}

	
	/* Swings the Club
	 * @param map
	 * 			The map in which the Club is updated in
	 * @param ent
	 * 			Entity equipped with the Club
	 */
	public void swing(Map map, Entity ent) {
		Coord newCoord;

		do
		{
			newCoord = ent.getCoord().getRandomAdjacentCoord();
		}while(map.isSymbolInCoord(newCoord,symbolClubDoesntSwingTo));
		
		if(map.getBotEnt(newCoord).getSymb() == Symbol.KEY) {
			this.setSymb(Symbol.CLUB_ON_KEY);
		}
		else {
			this.setSymb(Symbol.OGRE_WEAPON);
		}
		
		map.move(this, newCoord);
	}
}
