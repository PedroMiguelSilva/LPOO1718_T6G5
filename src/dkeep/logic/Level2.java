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
		Ogre ogre1 = new Ogre(1,4);
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

	/*
	 * @brief 	updates the logic of the game acording to heroMovement
	 * @param 	heroMovement
	 * 				direction in which the user wants to move
	 * @return	0
	 * 				keep playing
	 * 			1
	 * 				hero died
	 * 			2	
	 * 				level up

	 */
	public int update(char heroMovement)
	{
		//local variales
		int status = 0;
		char symbHeroIsOn;

		//Moving hero
		this.getHero().move(getMap(), heroMovement,this.getInteractives());
		Coord curr = this.getHero().getCoord();
		if(this.getMap().getBotEnt(curr).getSymb() == Symbol.DOOR_OPEN)
			return 2;

		//Checking if hero died from moving
		if(this.getHero().isDead(this.getMap(),this.getEnemies()))
		{
			return 1;
		}

		//Moving enemies
		for(Enemy e: this.getEnemies())
		{
			if(e instanceof Guard || e instanceof Ogre)
			{
				e.move(this.getMap());
			}
		}

		//Checking if hero died from enemy movement
		if(this.getHero().isDead(this.getMap(),this.getEnemies()))
		{
			return 1;
		}


		return status;
	}


}
