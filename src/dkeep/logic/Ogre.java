package dkeep.logic;

import java.util.Random;

public class Ogre extends Enemy
{
	private boolean hasClub;
	private Club weapon;
	
	//Constructor
	public Ogre(int startX, int startY,boolean hasClub){
		super(startX,startY,Symbol.OGRE);
		if(hasClub) {
			Club temp = new Club(startX,startY+1);
			this.weapon = temp;
		}
	}

	//Methods
	public void move(Map map)
	{
		Random  rand = new Random();
		int move = rand.nextInt(4);
		
		Coord newCoord = new Coord(this.getCoord());

		do
		{
			newCoord.setCoord(this.getCoord());
			move = rand.nextInt(4);

			switch(move)
			{
			case 0:
			{
				newCoord.incX();
				break;
			}
			case 1:
			{
				newCoord.incY();
				break;
			}
			case 2:
			{
				newCoord.decX();
				break;
			}
			case 3:
			{
				newCoord.decY();
				break;
			}
			}
			
			
		}while(map.getBotEnt(newCoord).getSymb() == Symbol.WALL || 
				map.getBotEnt(newCoord).getSymb() == Symbol.DOOR_CLOSED ||
				map.getBotEnt(newCoord).getSymb() == Symbol.DOOR_OPEN);

		//might move on top of the wall
		if(map.getBotEnt(newCoord).getSymb() == Symbol.KEY) {
			this.setSymb(Symbol.OGRE_ON_KEY);
		}
		else {
			this.setSymb(Symbol.OGRE);
		}
		
		map.move(this, newCoord);
		
		weapon.swing(map,this);
	}
}
