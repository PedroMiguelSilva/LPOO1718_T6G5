package dkeep.logic;

import java.util.ArrayList;

public class Hero extends Entity
{
	//Attributes
	private boolean isAlive;
	private Key key;
	private boolean wonLevel;

	//Constructor
	public Hero(int startX, int startY, char startSymb)
	{
		super(startX,startY,startSymb);
		isAlive = true;
		wonLevel = false;
		Key key1 = new Key(0,0,'k',0,0);
		this.key = key1;
		
	}

	//Methods
	public char move(Map map, char direction, ArrayList<Interactive> interactives)
	{
		//int xFinal = this.getX();
		//int yFinal = this.getY();
		Coord newCoord = new Coord(this.getCoord());
				
		char symbWalkedInto;
		
		
		switch(direction)
		{
		case 'a':
		{
			//yFinal -= 1;
			newCoord.decY();
			break;
		}
		case 'w':
		{
			//xFinal -=  1;
			newCoord.decX();
			break;
		}
		case 's':
		{
			//xFinal +=  1;
			newCoord.incX();
			break;
		}
		case 'd':
		{
			//yFinal += 1;
			newCoord.incY();
			break;
		}
		}

		symbWalkedInto = map.getChar(newCoord);
		
		if(map.canMove(newCoord))
		{
			char symb = map.getChar(newCoord);
			
			if(symb != ' ')		//moving into an interactable object
			{
				for(Interactive i : interactives)
				{
					if(i.getCoord().equals(newCoord))//interactable has to be in the same coords as the hero intends to go to
					{
						i.trigger(this,interactives,map);
					}
				}			
			}
			else				//moving into a clear space
			{
				map.setChar(this.getCoord(), ' ');
				map.setChar(newCoord, this.getSymb());
				this.setCoord(newCoord);
			}
		}
		else
		{
			//walking into a door
		}
		
		return symbWalkedInto;
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
			if(map.isDangerous(this.getCoord(), e.getSymb()))
			{
				return true;
			}
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
