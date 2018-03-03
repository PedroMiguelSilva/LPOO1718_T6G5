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
		int xPos = this.getX();
		int yPos = this.getY();

		do
		{
			xPos = this.getX();
			yPos = this.getY();
			move = rand.nextInt(4);

			switch(move)
			{
			case 0:
			{
				xPos++;
				break;
			}
			case 1:
			{
				yPos++;
				break;
			}
			case 2:
			{
				xPos--;
				break;
			}
			case 3:
			{
				yPos--;
				break;
			}
			}
		}while(!map.canMove(xPos, yPos));

		char symb = map.getChar(xPos, yPos);
		char prevSymb;
		char postSymb;
		
		if(symb == 'k')
		{
			onTopOfKey = true;			
			prevSymb = ' ';
			postSymb = '$';
		}
		else
		{
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
		
		map.setChar(this.getX(), this.getY(), prevSymb);
		map.setChar(xPos, yPos, postSymb);
		this.setX(xPos);
		this.setY(yPos);
	}
}
