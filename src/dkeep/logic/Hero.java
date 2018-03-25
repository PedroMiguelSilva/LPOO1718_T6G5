package dkeep.logic;

import java.util.ArrayList;

public class Hero extends Entity
{
	//Attributes
	private boolean isAlive;
	private Key key;
	private boolean wonLevel;

	//Constructor
	public Hero(int startX, int startY)
	{
		super(startX , startY , Symbol.HERO);
		isAlive = true;
		wonLevel = false;
		Key key1 = new Key(0,0,0,0);
		this.key = key1;

	}

	//Methods
	public void move(Map map, char direction, ArrayList<Interactive> interactives){

		Coord newCoord = new Coord(this.getCoord());

		switch(direction)
		{
		case 'a':
		{
			newCoord.decY();
			break;
		}
		case 'w':
		{
			newCoord.decX();
			break;
		}
		case 's':
		{
			newCoord.incX();
			break;
		}
		case 'd':
		{
			newCoord.incY();
			break;
		}
		}

		if(!map.canMove(newCoord))
			return;

		Symbol symb = map.getEnt(newCoord).getSymb();//quer saber qual é o elemento que esta onde ele quer ir

		if(symb == Symbol.CLEAR_SPACE)				//moving into an empty space
		{
			//actually call the move
			//map.setChar(this.getCoord(), ' ');
			//map.setChar(newCoord, this.getSymb());
			//this.setCoord(newCoord);
			map.move(this, newCoord);
		}
		else										//moving into interactable
		{
			//TODO MAKE THIS ANOTHER FUNCTION TO MAKE CODE CLEAR
			for(Interactive i : interactives)
			{
				if(i.getCoord().equals(newCoord))//interactable has to be in the same coords as the hero intends to go to
				{
					i.trigger(this,interactives,map);
				}
			}	
		}
		

	}

	public boolean hasWon()
	{
		return wonLevel;
	}

	public boolean isDead(Map map,ArrayList<Enemy> enemies)
	{
		//loop through all the enemies, for each one compare their symbols
		for(Enemy e : enemies)
		{
			if(map.isDangerous(this.getCoord(), e.getSymb(),map.getHeight(),map.getWidth()))
			{
				isAlive = false;
				return true;
			}
		}

		if(map.isDangerous(this.getCoord(), Symbol.OGRE_WEAPON,map.getHeight(),map.getWidth()))
		{
			isAlive = false;
			return true;
		}
		
		//no enemy was next to the hero
		return false;
	}

	public void setDead()
	{
		isAlive = false;
	}

	public void setKey(Key newKey)
	{
		this.key = newKey;
	}

	public Key getKey()
	{
		return this.key;
	}

}
