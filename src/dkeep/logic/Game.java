package dkeep.logic;

import java.util.ArrayList;

public class Game
{
	//ATRIBUTES
	private ArrayList<Level> levels;
	private int currentLevel;
	private int MAX_LEVEL;
	private boolean gameOver;
	
	
	
	//CONSTRUCTOR
	public Game()
	{
		super();
		currentLevel = 1;
		gameOver = false;
		MAX_LEVEL = 2;
		//iniciar niveis
	}
	
	//METHODS
	
	/*
	 * @return the current level
	 */
	public Level getLevel()
	{
		return levels.get(currentLevel);
	}
	
	//returns if the game has ended
	public boolean update(char heroMovement)
	{
		boolean end = false;
		
		//moving hero
		hero.move(map,heroMovement,level);

		//checking if hero died
		if(hero.isDead(map))
		{
			System.out.println("Hero Died");
			return true;
		}
		
		//moving other creatures
		if(level == 1)
		{
			 guard.move(map);
		}
		else if(level == 2)
		{
			//not yet on lvl 2
		}

		//checking if hero died
		if(hero.isDead(map))
		{
			System.out.println("Hero Died");
			return true;
		}
		
		//checks if hero won level
		if(hero.hasWon())
		{
			level++;
			this.loadLevel();
			System.out.println("Acabas te o primeiro nivel, parabens");
		}
		
		//if it reaches here then the game has not ended and the player is free to proceed
		return false;
	}
	
	public void sendFinalMessage()
	{
		//according to variables of the game, send message
	}
	
	public void loadLevel()
	{
		switch(level)
		{
		case 1:
		{
			//initiate map
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
			this.map = new Map(10,10,map1);
			
			//initiate hero
			this.hero = new Hero(1,1,'h');
			
			//initiate guard
			char xPos[] = {1,1,2,3,4,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,5,4,3,2};
			char yPos[] = {8,7,7,7,7,7,6,5,4,3,2,1,1,2,3,4,5,6,7,8,8,8,8,8};
			this.guard = new Guard(1,8,'g',xPos,yPos);
			
			//dont initiate ogre
			this.ogre = null;
		}
		case 2:
		{
			//attributes for level 2
		}
		}
	}
}
