package dkeep.logic;

import java.util.ArrayList;

abstract public class Interactive extends Entity
{
	//Attributes
	
	//Constructor
	public Interactive(int startX, int startY, char startSymb)
	{
		super(startX,startY,startSymb);
	}
	
	//Methods
	abstract public void trigger(Hero hero, ArrayList<Interactive> interactives,Map map);

}
