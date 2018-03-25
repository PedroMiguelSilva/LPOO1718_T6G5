package dkeep.logic;

import java.util.ArrayList;

public class Hero extends Entity
{
	//Attributes
	private boolean isAlive;
	private Key key;
	private boolean wonLevel;
	private boolean isArmed;

	//Constructor
	public Hero(int startX, int startY, boolean isArmed)
	{
		super(startX , startY , Symbol.HERO);
		isAlive = true;
		wonLevel = false;
		Key key1 = new Key(0,0,0,0);
		this.key = key1;
		this.isArmed = isArmed;

		if(isArmed)
			this.setSymb(Symbol.HERO_WITH_CLUB);
	}

	public void stunNearBy(ArrayList<Enemy> enemies) {
		if(!isArmed)
			return;
		
		Coord temp = this.getCoord();
		Coord c1 = new Coord(temp.getX()+1,temp.getY());
		Coord c2 = new Coord(temp.getX(),temp.getY()+1);
		Coord c3 = new Coord(temp.getX()-1,temp.getY());
		Coord c4 = new Coord(temp.getX(),temp.getY()-1);

		for(Enemy e : enemies) {
			if(		(e.getCoord().equals(c1) ||
					e.getCoord().equals(c2) ||
					e.getCoord().equals(c3)||
					e.getCoord().equals(c4)) && e instanceof Ogre) {
				Ogre o = (Ogre)e;
				o.stun();
			}
		}
	}	

		//Methods
		public void move(Map map, char direction, ArrayList<Interactive> interactives,ArrayList<Enemy> enemies){

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

			
			stunNearBy(enemies);
		}

		public boolean hasWon()
		{
			return wonLevel;
		}

		public boolean isDangerous(Map map, Symbol symb, int xMax, int yMax)
		{
			if(symb == Symbol.GUARD_SLEEP || symb == Symbol.OGRE_STUNED)
				return false;
			

			
			Coord temp = new Coord(this.getCoord());
			
			Coord c1 = new Coord(temp.getX(),temp.getY());
			Coord c2 = new Coord(temp.getX(),temp.getY()+1);
			Coord c3 = new Coord(temp.getX()-1,temp.getY());
			Coord c4 = new Coord(temp.getX(),temp.getY()-1);

			if(!map.outOfBounds(c1,xMax,yMax) && map.getTopEnt(c1).getSymb() == symb)
				return true;
			
			if(!map.outOfBounds(c2,xMax,yMax) && map.getTopEnt(c2).getSymb() == symb)
				return true;
			
			if(!map.outOfBounds(c3,xMax,yMax) && map.getTopEnt(c3).getSymb() == symb)
				return true;
			
			if(!map.outOfBounds(c4,xMax,yMax) && map.getTopEnt(c4).getSymb() == symb)
				return true;
			
			return false;
		}
		
		public boolean isDead(Map map,ArrayList<Enemy> enemies)
		{
			//loop through all the enemies, for each one compare their symbols
			for(Enemy e : enemies)
			{
				if(isDangerous(map, e.getSymb(),map.getHeight(),map.getWidth()))
				{
					isAlive = false;
					return true;
				}
			}

			if(isDangerous(map, Symbol.OGRE_WEAPON,map.getHeight(),map.getWidth()))
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
