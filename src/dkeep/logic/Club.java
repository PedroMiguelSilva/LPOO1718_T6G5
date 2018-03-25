package dkeep.logic;

import java.util.Random;

public class Club extends Entity{

	public Club(int startX, int startY) {
		super(startX, startY, Symbol.OGRE_WEAPON);
	}

	public void swing(Map map, Entity ent) {
		Random  rand = new Random();
		int move = rand.nextInt(4);
		Coord newCoord = new Coord(this.getCoord());

		do
		{
			newCoord.setCoord(ent.getCoord());
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
				map.getBotEnt(newCoord).getSymb() == Symbol.DOOR_OPEN ||
				map.getBotEnt(newCoord).getSymb() == Symbol.OGRE_WEAPON);
		
		if(map.getBotEnt(newCoord).getSymb() == Symbol.KEY) {
			this.setSymb(Symbol.CLUB_ON_KEY);
		}
		else {
			this.setSymb(Symbol.OGRE_WEAPON);
		}
		
		map.move(this, newCoord);
	}
}
