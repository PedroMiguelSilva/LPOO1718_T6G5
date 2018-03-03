package dkeep.logic;

import java.util.ArrayList;

public class Hero extends Entity
{
	//Attributes
	private boolean isAlive;
	private boolean hasKey;
	private boolean wonLevel;

	//Constructor
	public Hero(int startX, int startY, char startSymb)
	{
		super(startX,startY,startSymb);
		isAlive = true;
		hasKey = false;
		wonLevel = false;
	}

	//Methods
	public void move(Map map, char direction, ArrayList<Interactive> interactives)
	{
		int xFinal = this.getX();
		int yFinal = this.getY();
		
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
		
		
		// ===================
		
		/*
		if(map.getChar(xEnd, yEnd) == 'k' && level == 2)						//if moving into key
		{
			this.hasKey = true;
			this.setSymb('K');
			//delete previous location
			map.setChar(this.getX(), this.getY(), ' ');
			//add him in current location
			map.setChar(xEnd, yEnd, this.getSymb());
			//update coordinates
			this.setX(xEnd);
			this.setY(yEnd);
		}
		else if(map.getChar(xEnd, yEnd) == 'k' && level == 1)					//if moving into lever
		{
			map.setChar(5, 0, 'S');
			map.setChar(6, 0, 'S');
		}
		else if(map.getChar(xEnd, yEnd) == 'S' && level == 1)
		{
			//won the game pretty much
			this.wonLevel = true;

		}
		else if(map.getChar(xEnd, yEnd) == 'i' && this.hasKey&& level == 2)		//if moving into door
		{
			map.setChar(xEnd, yEnd, 'S');
		}
		else if(map.getChar(xEnd, yEnd) == 'i' && level == 1)
		{
			//dont do anything
		}
		else
		{
			//delete previous location
			map.setChar(this.getX(), this.getY(), ' ');
			//add him in current location
			map.setChar(xEnd, yEnd, this.getSymb());
			//update coordinates
			this.setX(xEnd);
			this.setY(yEnd);
		}
		*/

	}

	public boolean hasWon()
	{
		return wonLevel;
	}

	public boolean isDead(Map map)
	{
		int x = this.getX();
		int y = this.getY();

		if(map.isDangerous(x,y,'g') || map.isDangerous(x, y, 'o') || map.isDangerous(x, y, '*'))
		{
			this.isAlive = false;
			return true;
		}
		else
		{
			return false;
		}		
	}
}
