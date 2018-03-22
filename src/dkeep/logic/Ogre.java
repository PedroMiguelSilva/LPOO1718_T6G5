package dkeep.logic;

import java.util.Random;

public class Ogre extends Enemy
{
	//Attributes
	private boolean onTopOfKey;

	//Constructor
	public Ogre(int startX, int startY, char startSymb)
	{
		super(startX,startY,startSymb);
		onTopOfKey = false;
	}

	//Methods
	public void move(Map map)
	{
		Random  rand = new Random();
		int move = rand.nextInt(4);
		//int xPos = this.getX();
		//int yPos = this.getY();
		
		Coord newCoord = new Coord(this.getCoord());

		do
		{
			newCoord.setCoord(this.getCoord());
			move = rand.nextInt(4);

			switch(move)
			{
			case 0:
			{
				//xPos++;
			newCoord.incX();
				break;
			}
			case 1:
			{
				//yPos++;
				newCoord.incY();
				break;
			}
			case 2:
			{
				//xPos--;
				newCoord.decX();
				break;
			}
			case 3:
			{
				//yPos--;
				newCoord.decY();
				break;
			}
			}
			
			
		}while(map.getChar(newCoord) == 'x' || map.getChar(newCoord) == 'i');

		char symb = map.getChar(newCoord);
		char prevSymb;
		char postSymb;
		
		if(symb == 'k')
		{
			onTopOfKey = true;			
			prevSymb = ' ';
			postSymb = '$';
			this.setSymb('$');
		}
		else
		{
			this.setSymb('o');
			if(onTopOfKey)
			{
				prevSymb = 'k';
				onTopOfKey = false;
			}
			else
			{
				prevSymb = ' ';
			}
			postSymb = this.getSymb();
		}
		
		map.setChar(this.getCoord(), prevSymb);
		map.setChar(newCoord, postSymb);
		this.setCoord(newCoord);
	}
}
