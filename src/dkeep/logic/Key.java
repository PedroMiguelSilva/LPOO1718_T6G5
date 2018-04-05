package dkeep.logic;

import java.util.ArrayList;

/* Represents a Key
 * @Note : A Key with doorC coordinates of x = 0 and y = 0 is universal and will open any door
 * @version 1.0
 * @since 1.0
 */
public class Key extends Interactive
{
	Coord doorC;

	/* Creates a key with specified start position and coordinates of the door it opens
	 * @param startX
	 * 			The Key's x positon
	 * @param startY
	 * 			The Key's y position
	 * @param doorX
	 * 			The x coordinate of the door opened by this Key
	 * @param doorY
	 * 			The y coordinate of the door opened by this Key
	 */
	public Key(Coord pos, Coord doorC)
	{
		super(pos,Symbol.KEY);
		this.doorC = doorC;
	}

	/* Creates an universal Key with specified position
	 * @param startX
	 * 			The Key's x position
	 * @param startY
	 * 			The Key's y position 
	 */
	public Key(int startX, int startY){
		super(new Coord(startX,startY),Symbol.KEY);
		doorC = new Coord(0,0);
	}
	
	public Key(Coord coord) {
		this(coord.getX(),coord.getY());
	}
	
	/* Hero walks into the key and picks it up
	 * @param hero
	 * 			Hero that picks the Key
	 * @param interactives
	 * 			Array of interactive objects that might be interacted with
	 * @param map
	 * 			Map in which the changes should be made
	 */
	@Override
	public void trigger(Hero hero, ArrayList<Interactive> interactives, Map map)
	{
		Clear temp = new Clear(hero.getCoord());
		hero.setKey(this);
		hero.setSymb(Symbol.HERO_WITH_KEY);
		map.move(hero, this.getCoord());
		//hero.getCoord().setCoord(this.getCoord());
		map.setBotEnt(hero.getCoord(), temp);
	}	

	/* @return Coordinates of the door opened by this Key
	 */
	public Coord getDoorCoord() {
		return this.doorC;
	}
}
