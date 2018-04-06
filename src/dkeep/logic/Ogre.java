package dkeep.logic;

/* Represents an Ogre
 * @version 1.0
 * @since 1.0
 */
public class Ogre extends Enemy
{
	private boolean hasClub;
	private boolean isStun;
	private int roundsStun;
	private Club weapon;
	
	/* Creates an Ogre with specified coordinates and weapon
	 * @param pos
	 * 			Coordinate of the Ogre
	 * @param hasClub	
	 * 			If the Ogre has a club or not
	 */
	public Ogre(Coord pos,boolean hasClub){
		super(pos,Symbol.OGRE);
		if(hasClub) {
			Coord club = new Coord(pos);
			Club temp = new Club(club);
			this.weapon = temp;
		}
		isStun = false;
		roundsStun = 0;
		this.hasClub = hasClub;
		Symbol[] temp = {Symbol.WALL,Symbol.DOOR_CLOSED,Symbol.DOOR_OPEN};
		Symbol[] canMove = {
				Symbol.CLEAR_SPACE,
				Symbol.OGRE,
				Symbol.OGRE_ON_KEY,
				Symbol.KEY,
				Symbol.OGRE_WEAPON,
				Symbol.OGRE_STUNED
				};
		canWalkInto = canMove;
		cantWalkInto = temp;
	}
	
	/* Stun the Ogre
	 */
	public void stun() {
		isStun = true;
		roundsStun = 3;
		this.setSymb(Symbol.OGRE_STUNED);
	}
	
	/* Move the Ogre in the map
	 * @param map
	 * 			Map in which the changes should be made
	 */
	public void move(Map map){
		Coord newCoord;
		if(noPossibleMove(map,this.getCoord()))
			return;
		
		newCoord = getValidCoord(map,this.getCoord());

		if(map.getBotEnt(newCoord).getSymb() == Symbol.KEY) {
			this.setSymb(Symbol.OGRE_ON_KEY);
		}		
		
		updateStunStatus();
		moveOgre(map,newCoord);
		if(hasClub)
			weapon.swing(map,this);
	}
	
	private void updateStunStatus() {
		if(roundsStun > 1) {
			roundsStun -=1;
		}			
		else{
			roundsStun = 0;
			isStun = false;
		}
	}
	
	private void moveOgre(Map map,Coord newCoord) {
		if(!isStun) {
			this.setSymb(Symbol.OGRE);
			map.move(this, newCoord);
		}
		if(map.getBotEnt(newCoord).getSymb() == Symbol.KEY)
			this.setSymb(Symbol.OGRE_ON_KEY);
	}
}
