package dkeep.logic;

import java.util.ArrayList;

public class Key extends Interactive
{
	//Attributes
	Coord doorC;

	//Constructor
	public Key(int startX, int startY, int x, int y)
	{
		super(startX,startY,Symbol.KEY);
		doorC = new Coord(x,y);
	}

	@Override
	public void trigger(Hero hero, ArrayList<Interactive> interactives, Map map)
	{
		Clear temp = new Clear(hero.getCoord());
		hero.setKey(this);
		hero.setSymb(Symbol.HERO_WITH_KEY);
		//map.setChar(this.getCoord(), hero.getSymb());
		//map.setChar(hero.getCoord(), ' ');
		map.move(hero, this.getCoord());
		hero.getCoord().setCoord(this.getCoord());
		map.setBotEnt(hero.getCoord(), temp);
	}

	//Methods

	public Coord getDoorCoord() {
		return this.doorC;
	}
}
