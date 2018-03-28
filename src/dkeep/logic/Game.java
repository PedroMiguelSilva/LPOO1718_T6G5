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
			char[][] map = {
					{'X','X','X','X','X'},
					{'X','H',' ','o','X'},
					{'X',' ',' ',' ','X'},
					{'i','k',' ',' ','X'},
					{'X','X','X','X','X'}
					
			};
			
			Level lvl2 = new Level2(map);
			temp.add(lvl2);
		}

		this.setLevels(temp);
	}
	
	public Game(Game game) {
		super();
		this.levels = game.getLevelArray();
		this.currentLevel = game.getCurrentLevel();
		this.MAX_LEVEL = game.getMaxLevel();
		this.gameOver = game.isGameOver();
		this.wonGame = game.getWonGame();
		this.quit = game.isGameOver();
	}
	
	//METHODS
	
	public void moveHero(Cmd cmd) {
		int status = 0;
		status = this.getLevel().update(cmd);
		this.updateGameVariables(status, cmd);
	}
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
	
	public boolean isGameOver()
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
	
	public void updateGameVariables(int status, Cmd cmd)
	{
		if(cmd == Cmd.QUIT)
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
	public static char symbolToChar(Symbol s) {
		switch(s) {
		case HERO:
			return 'H';
		case HERO_WITH_KEY:
			return 'K';
		case HERO_WITH_CLUB:
			return 'A';
		case GUARD:
			return 'G';
		case GUARD_SLEEP:
			return 'g';
		case WALL:
			return 'X';
		case CLEAR_SPACE:
			return ' ';
		case OGRE:
			return 'o';
		case OGRE_ON_KEY:
			return '$';
		case OGRE_WEAPON:
			return '*';
		case OGRE_STUNED:
			return '8';
		case CLUB_ON_KEY:
			return '$';
		case LEVER:
			return 'k';
		case KEY:
			return 'k';
		case DOOR_CLOSED:
			return 'i';
		case DOOR_OPEN:
			return 'S';
		default:
			return ' ';
		}
	}
	
	public String mapString(Map map) {
		String mapa = new String();
		Character space = ' ';
	
		for(int i=0; i< map.getMap().length;i++)
		{
			for(int j=0; j<map.getMap()[i].length;j++)
			{
				Coord coord = new Coord(i,j);
				mapa += symbolToChar(map.getEnt(coord).getSymb())+ " ";
				
			}
			mapa += "\n";
		}		
		
		return mapa;
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}
}
