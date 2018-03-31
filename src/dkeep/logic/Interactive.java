package dkeep.logic;

import java.util.ArrayList;

/* Nominates an Interactive Entity
 * @version 1.0
 * @since 1.0
 */
abstract public class Interactive extends Entity
{
	/* Create an Interactive object with specified position and symbol
	 * @param startX
	 * 			The Interactive's x position
	 * @param startY
	 * 			The Interactive's y position
	 * @param startSymbol
	 * 			The Interactive's start symbol
	 */
	public Interactive(int startX, int startY, Symbol startSymb)
	{
		super(startX,startY,startSymb);
	}
	
	/* Hero moves towards the Door
	 * Hero opens the Door with Key if it's closed, moves Hero on top of Door if it's open
	 * @param hero
	 * 			Hero that is moving against the Door
	 * @param interactives
	 * 			Array of Interactive objects that might be triggered
	 * @param map
	 * 			Map in which the updates should be made
	 */
	abstract public void trigger(Hero hero, ArrayList<Interactive> interactives,Map map);

}
