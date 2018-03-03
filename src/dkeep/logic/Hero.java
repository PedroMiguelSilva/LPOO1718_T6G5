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
		int xFinal = this.getX();
		int yFinal = this.getY();
		
		char symbWalkedInto;
		
		
		switch(direction)
		{
		case 'a':
		{
			yFinal -= 1;
			break;
		}
		case 'w':
		{
			xFinal -=  1;
			break;
		}
		case 's':
		{
			xFinal +=  1;
			break;
		}
		case 'd':
		{
			yFinal += 1;
			break;
		}
		}

		symbWalkedInto = map.getChar(xFinal, yFinal);
		
		if(map.canMove(xFinal, yFinal))
		{
			char symb = map.getChar(xFinal, yFinal);
			
			if(symb != ' ')		//moving into an interactable object
			{
				for(Interactive i : interactives)
				{
					if(i.getX() == xFinal && i.getY() == yFinal)//interactable has to be in the same coords as the hero intends to go to
					{
						i.trigger(this,interactives,map);
					}
				}			
			}
			else				//moving into a clear space
			{
				map.setChar(this.getX(), this.getY(), ' ');
				map.setChar(xFinal, yFinal, this.getSymb());
				this.setX(xFinal);
				this.setY(yFinal);
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
		int x = this.getX();
		int y = this.getY();

		//loop through all the enemies, for each one compare their symbols
		for(Enemy e : enemies)
		{
			if(map.isDangerous(x, y, e.getSymb()))
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
