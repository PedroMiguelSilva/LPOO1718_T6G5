package dkeep.logic;

import java.util.ArrayList;

/** Represents a Key
 * @version 1.0
 * @since 1.0
 */
public class Key extends Interactive
{
	Coord doorC;

	/** Creates a key with specified start position and coordinates of the door it opens
	 * A Key with doorC 100 100 is a master Key and will open any door
	 * @param pos
	 * 			Position of Key
	 * @param doorC
	 * 			Position of Door that Key opens
	 */
	public Key(Coord pos, Coord doorC)
	{
		super(pos,Symbol.KEY);
		this.doorC = doorC;
	}
	
	/** Hero walks into the key and picks it up
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

	/** @return Coordinates of the door opened by this Key
	 */
	public Coord getDoorCoord() {
		return this.doorC;
	}
}
