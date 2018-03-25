package dkeep.logic;

import java.util.ArrayList;

public class Level2 extends Level
{
	//Attributes

	//Constructor
	public Level2()
	{
		/*
		char map2 [][] = {
				{'x','x','x','x','x','x','x','x','x'},
				{'i',' ',' ',' ','o',' ',' ','k','x'},
				{'x',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x','h',' ',' ',' ',' ',' ',' ','x'},
				{'x','x','x','x','x','x','x','x','x'}
		};
		*/
		//Map mapLevel2 = new Map(9,9,map2);
		//this.setMap(mapLevel2);
		
		ArrayList<Entity> entities = new ArrayList<Entity>();
		ArrayList<Enemy> enemiesLevel2 = new ArrayList<Enemy>();
		ArrayList<Interactive> interactives = new ArrayList<Interactive>();
		
		//walls
		//no walls :)
		
		//SET HERO
		Hero heroLevel2 = new Hero(7,1);
		this.setHero(heroLevel2);
		entities.add(heroLevel2);
		
		//SET ENEMIES
		Ogre ogre1 = new Ogre(1,4,true);
		enemiesLevel2.add(ogre1);
		entities.add(ogre1);
		this.setEnemies(enemiesLevel2);
		
		//SET INTERACTIVES
		Door door1 = new Door(1,0);
		Key key1 = new Key(1,7,1,0);
		interactives.add(door1);
		interactives.add(key1);
		entities.add(door1);
		entities.add(key1);
		this.setInteractives(interactives);
		
		Map mapLvl2 = new Map(9,9,entities);
		this.setMap(mapLvl2);
	}
	
	public boolean hasWonLevel() {
		Coord curr = this.getHero().getCoord();
		if(this.getMap().getBotEnt(curr).getSymb() == Symbol.DOOR_OPEN)
			return true;
		else
			return false;
	}

}
