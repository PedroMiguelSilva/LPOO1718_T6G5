package dkeep.logic;

import java.util.ArrayList;

public class Key extends Interactive
{
	//Attributes
	Coord doorC;

	//Constructor
	public Key(int startX, int startY, char startSymb, int x, int y)
	{
		super(startX,startY,startSymb);
		doorC = new Coord(x,y);
	}

	@Override
	public void trigger(Hero hero, ArrayList<Interactive> interactives, Map map)
	{
		hero.setKey(this);
		hero.setSymb('K');
		map.setChar(this.getCoord(), hero.getSymb());
		map.setChar(hero.getCoord(), ' ');
		hero.getCoord().setCoord(this.getCoord());
	}

	//Methods

	public Coord getDoorCoord() {
		return this.doorC;
	}
}
