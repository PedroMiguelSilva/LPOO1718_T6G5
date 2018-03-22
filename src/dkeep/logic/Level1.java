package dkeep.logic;

import java.util.ArrayList;

public class Level1 extends Level
{
	//Attributes

	//Constructor
	public Level1()
	{
		//SET MAP
		char map1 [][] = {
				{'x','x','x','x','x','x','x','x','x','x'},
				{'x','h',' ',' ','i',' ','x',' ','g','x'},
				{'x','x','x',' ','x','x','x',' ',' ','x'},
				{'x',' ','i',' ','i',' ','x',' ',' ','x'},
				{'x','x','x',' ','x','x','x',' ',' ','x'},
				{'i',' ',' ',' ',' ',' ',' ',' ',' ','x'},
				{'i',' ',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x','x','x',' ','x','x','x','x',' ','x'},
				{'x',' ','i',' ','i',' ','x','k',' ','x'},
				{'x','x','x','x','x','x','x','x','x','x'}
		};	
		Map mapLevel1 = new Map(10,10,map1);
		this.setMap(mapLevel1);

		//SET HERO
		Hero heroLevel1 = new Hero(1,1,'h');
		this.setHero(heroLevel1);

		//SET ENEMIES
		int xPos[] = {1,1,2,3,4,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,5,4,3,2};
		int yPos[] = {8,7,7,7,7,7,6,5,4,3,2,1,1,2,3,4,5,6,7,8,8,8,8,8};
		Guard guard = new Guard(1,8,'g',xPos,yPos);
		ArrayList<Enemy> enemiesLevel1 = new ArrayList<Enemy>();
		enemiesLevel1.add(guard);
		this.setEnemies(enemiesLevel1);

		//SET INTERACTIVES
		Door door1 = new Door(5,0,'i','i','S');
		Door door2 = new Door(6,0,'i','i','S');
		int lever1TrigX[] = {5,6};									//coordinates of objects triggered by lever
		int lever1TrigY[] = {0,0};									//coordinates of objects triggered by lever
		Lever lever1 = new Lever(8,7,'k',lever1TrigX,lever1TrigY);
		ArrayList<Interactive> interactives = new ArrayList<Interactive>();
		interactives.add(door1);
		interactives.add(door2);
		interactives.add(lever1);
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
	 */
	public int update(char heroMovement)
	{
		//local variables
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
			this.getHero().setDead();
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
			this.getHero().setDead();
			return 1;
		}


		return status;
	}

}
