package dkeep.logic;

import java.util.ArrayList;

public class Level2 extends Level
{
	//Attributes

	//Constructor
	public Level2()
	{
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
		Map mapLevel2 = new Map(9,9,map2);
		this.setMap(mapLevel2);
		
		//SET HERO
		Hero heroLevel2 = new Hero(7,1,'h');
		this.setHero(heroLevel2);
		
		//SET ENEMIES
		Ogre ogre1 = new Ogre(1,4,'o');
		ArrayList<Enemy> enemiesLevel2 = new ArrayList<Enemy>();
		enemiesLevel2.add(ogre1);
		this.setEnemies(enemiesLevel2);
		
		//SET INTERACTIVES
		Key key1 = new Key(1,7,'k');
		ArrayList<Interactive> interactives = new ArrayList<Interactive>();
		interactives.add(key1);
		this.setInteractives(interactives);
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
	 * 			3
	 * 				won game
	 */
	public int update(char heroMovement)
	{
		//local variales
		int status = 0;
		char symbHeroIsOn;

		//Moving hero
		symbHeroIsOn = this.getHero().move(getMap(), heroMovement,this.getInteractives());
		if(symbHeroIsOn == 'S')
		{
			return 2;
		}

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
