package dkeep.logic;

import java.util.Random;

/* Represents the Club of the an Ogre
 * @version 1.0
 * @since 1.0 
 */
public class Club extends Entity{

	/* Creates a Club with specified x and y position
	 * @param startX
	 * 			The Club's x position
	 * @param startY
	 * 			The Club's y position
	 */
	public Club(int startX, int startY) {
		super(startX, startY, Symbol.OGRE_WEAPON);
	}

	/* Swings the Club
	 * @param map
	 * 			The map in which the Club is updated in
	 * @param ent
	 * 			Entity equipped with the Club
	 */
	public void swing(Map map, Entity ent) {
		Random  rand = new Random();
		int move = rand.nextInt(4);
		Coord newCoord = new Coord(this.getCoord());

		do
		{
			newCoord.setCoord(ent.getCoord());
			move = rand.nextInt(4);

			switch(move)
			{
			case 0:
			{
				newCoord.incX();
				break;
			}
			case 1:
			{
				newCoord.incY();
				break;
			}
			case 2:
			{
				newCoord.decX();
				break;
			}
			case 3:
			{
				newCoord.decY();
				break;
			}
			}


		}while(map.getEnt(newCoord).getSymb() == Symbol.WALL || 
				map.getEnt(newCoord).getSymb() == Symbol.DOOR_CLOSED ||
				map.getEnt(newCoord).getSymb() == Symbol.DOOR_OPEN ||
				map.getEnt(newCoord).getSymb() == Symbol.OGRE ||
				map.getEnt(newCoord).getSymb() == Symbol.OGRE_STUNED ||
				map.getEnt(newCoord).getSymb() == Symbol.OGRE_WEAPON);
		
		if(map.getBotEnt(newCoord).getSymb() == Symbol.KEY) {
			this.setSymb(Symbol.CLUB_ON_KEY);
		}
		else {
			this.setSymb(Symbol.OGRE_WEAPON);
		}
		
		map.move(this, newCoord);
	}
}
