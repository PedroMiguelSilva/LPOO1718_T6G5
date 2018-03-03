package dkeep.logic;

import java.util.ArrayList;

public class Key extends Interactive
{
	//Attributes
	int xDoor;		//coordinates of door that is opened by this key
	int yDoor;

	//Constructor
	public Key(int startX, int startY, char startSymb, int x, int y)
	{
		super(startX,startY,startSymb);
		this.xDoor = x;
		this.yDoor = y;
	}

	@Override
	public void trigger(Hero hero, ArrayList<Interactive> interactives, Map map)
	{
		int x = 0;
		hero.setKey(this);
		hero.setSymb('K');
		map.setChar(this.getX(), this.getY(), hero.getSymb());
		map.setChar(hero.getX(), hero.getY(), ' ');
		hero.setX(this.getX());
		hero.setY(this.getY());
	}

	//Methods
	public int getDoorX()
	{
		return this.xDoor;
	}
	
	public int getDoorY()
	{
		return this.yDoor;
	}
}
