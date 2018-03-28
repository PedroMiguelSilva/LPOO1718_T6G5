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

	public Key(int startX, int startY){
		super(startX,startY,Symbol.KEY);
		doorC = new Coord(0,0);
	}
	
	@Override
	public void trigger(Hero hero, ArrayList<Interactive> interactives, Map map)
	{
		Clear temp = new Clear(hero.getCoord());
		hero.setKey(this);
		hero.setSymb(Symbol.HERO_WITH_KEY);
		map.move(hero, this.getCoord());
		hero.getCoord().setCoord(this.getCoord());
		map.setBotEnt(hero.getCoord(), temp);
	}

	//Methods

	public Coord getDoorCoord() {
		return this.doorC;
	}
}
