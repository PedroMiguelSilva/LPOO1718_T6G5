package dkeep.logic;

import java.util.ArrayList;

public class Game
{
	//ATRIBUTES
	private ArrayList<Level> levels;
	private int currentLevel;
	private int MAX_LEVEL;
	private boolean gameOver;
	private boolean wonGame;
	
	
	
	//CONSTRUCTOR
	public Game()
	{
		super();
		currentLevel = 0;
		gameOver = false;
		MAX_LEVEL = 1;
		wonGame = false;
		
		ArrayList<Level> temp = new ArrayList<Level>();
		Level lvl1 = new Level1();
		
		temp.add(lvl1);
		this.setLevels(temp);
	}
	
	//METHODS
	
	/*
	 * @return the current level
	 */
	public Level getLevel()
	{
		return levels.get(currentLevel);
	}
	
	/*
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
	
	*/
	
	public void setLevels(ArrayList<Level> lvl)
	{
		this.levels = lvl;
	}
	
	public void sendFinalMessage()
	{
		if(gameOver)
		{
			System.out.println("Game Over");
		}
		else if(wonGame)
		{
			System.out.println("Congratz, you won game");
		}
	}
	
	public boolean getGameOver()
	{
		return this.gameOver;
	}
	
	public boolean getWonGame()
	{
		return this.wonGame;
	}
	
	public void setGameOVer()
	{
		this.gameOver = true;
	}
	
	public void setWonGame()
	{
		this.wonGame = true;
	}

	public void updateVariables(int status)
	{
		switch(status)
		{
		case 1:		//lost game
		{
			this.setGameOVer();
			break;
		}
		case 2:		//leveled up
		{
			this.currentLevel += 1;
			break;
		}
		case 3:		//won game
		{
			this.setWonGame();
			break;
		}
		default:
		{
			//do nothing
		}
		
		}
	}
}
