package dkeep.logic;

import java.util.ArrayList;

public class Level1 extends Level
{
	//Attributes

	//Constructor
	public Level1()
	{
		ArrayList<Entity> entities = new ArrayList<Entity>();
		ArrayList<Enemy> enemiesLevel1 = new ArrayList<Enemy>();
		ArrayList<Interactive> interactives = new ArrayList<Interactive>();
		
		//walls
		int wallX[] = {1,2,2,2,2,2,3,4,4,4,4,4,7,7,7,7,7,7,8};
		int wallY[] = {6,1,2,4,5,6,6,1,2,4,5,6,1,2,4,5,6,7,6};
		ArrayList<Entity> walls = createWalls(posToCoords(wallX,wallY));
		entities.addAll(walls);
		
		//this.setMap(mapLevel1);

		//SET HERO
		Hero heroLevel1 = new Hero(1,1);
		this.setHero(heroLevel1);
		entities.add(heroLevel1);

		//SET ENEMIES
		int xPos[] = {1,1,2,3,4,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,5,4,3,2};
		int yPos[] = {8,7,7,7,7,7,6,5,4,3,2,1,1,2,3,4,5,6,7,8,8,8,8,8};
		Guard guard = new Guard(1,8,posToCoords(xPos,yPos));
		enemiesLevel1.add(guard);
		this.setEnemies(enemiesLevel1);
		entities.add(guard);

		//SET INTERACTIVES
		int doorX[] = {1,3,3,8,8};
		int doorY[] = {4,2,4,2,4};
		ArrayList<Entity> doors = createDoors(posToCoords(doorX,doorY));
		int lever1TrigX[] = {5,6};									//coordinates of objects triggered by lever
		int lever1TrigY[] = {0,0};									//coordinates of objects triggered by lever
		Lever lever1 = new Lever(8,7,posToCoords(lever1TrigX,lever1TrigY));
		
		interactives.add(lever1);
		
		Door door1 = new Door(5,0);
		interactives.add(door1);
		Door door2 = new Door(6,0);
		interactives.add(door2);
		entities.add(lever1);
		entities.add(door1);
		entities.add(door2);
		entities.addAll(doors);
		this.setInteractives(interactives);
		
		Map mapLvl1 = new Map(10,10,entities);
		this.setMap(mapLvl1);
		
		/*
		char map1 [][] = {
				{'x','x','x','x','x','x','x','x','x','x'},
				{'x','h',' ',' ','3',' ','x',' ','g','x'},
				{'x','x','x',' ','x','x','x',' ',' ','x'},
				{'x',' ','5',' ','4',' ','x',' ',' ','x'},
				{'x','x','x',' ','x','x','x',' ',' ','x'},
				{'1',' ',' ',' ',' ',' ',' ',' ',' ','x'},
				{'2',' ',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x','x','x',' ','x','x','x','x',' ','x'},
				{'x',' ','6',' ','7',' ','x','k',' ','x'},
				{'x','x','x','x','x','x','x','x','x','x'}
		};
		*/
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
	public int update(char heroMovement) //TODO CHECK IF HERO IS ON TOP OF 'S'
	{
		//local variables
		int status = 0;

		//Moving hero		
		this.getHero().move(getMap(), heroMovement, getInteractives());
		
		//check if he won
		Coord curr = this.getHero().getCoord();
		if(this.getMap().getBotEnt(curr).getSymb() == Symbol.DOOR_OPEN)
			return 2;
		
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
