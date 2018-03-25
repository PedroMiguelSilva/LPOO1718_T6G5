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
	private boolean quit;
	
	private ArrayList<Level> getLevelArray(){
		return this.levels;
	}
	
	private int getMaxLevel() {
		return this.MAX_LEVEL;
	}
	
	//CONSTRUCTOR
	public Game(int maxLevel)
	{
		super();
		currentLevel = 1;
		gameOver = false;
		MAX_LEVEL = maxLevel;
		wonGame = false;
		quit = false;
		
		ArrayList<Level> temp = new ArrayList<Level>();
		
		Level lvl1 = new Level1();
		temp.add(lvl1);
		
		if(MAX_LEVEL == 2) {
			Level lvl2 = new Level2();
			temp.add(lvl2);
		}

		this.setLevels(temp);
	}
	
	public Game(Game game) {
		super();
		this.levels = game.getLevelArray();
		this.currentLevel = game.getCurrentLevel();
		this.MAX_LEVEL = game.getMaxLevel();
		this.gameOver = game.getGameOver();
		this.wonGame = game.getWonGame();
		this.quit = game.getGameOver();
	}
	
	//METHODS
	
	/*
	 * @return the current level
	 */
	public Level getLevel()
	{
		return levels.get(currentLevel-1);
	}
	
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
		else if(quit)
		{
			System.out.println("Quit Game");
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

	public void setQuit()
	{
		this.quit = true;
	}
	
	public boolean getQuit()
	{
		return quit;
	}
	
	public void updateGameVariables(int status, char quit)
	{
		if(quit == 'q')
		{
			this.setQuit();
		}
		
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
			
			if(currentLevel > MAX_LEVEL)
				this.setWonGame();
			
			break;
		}
		default:
		{
			//do nothing
		}
		
		}
	}
	
	public String mapString(Map map) {
		String mapa = new String();
		Character space = ' ';
	
		for(int i=0; i< map.getMap().length;i++)
		{
			for(int j=0; j<map.getMap()[i].length;j++)
			{
				/*if(map[i][j] == space)
					mapa+= map[i][j];*/
				mapa += map.getMap()[i][j] + " ";
			}
			mapa += "\n";
		}		
		
		return mapa;
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}
}
