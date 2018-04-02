package dkeep.logic;

import java.util.ArrayList;

/* Represents the game
 * @version 1.0
 * @since 1.0
 * 
 */
public class Game
{
	private ArrayList<Level> levels;
	private int currentLevel;
	private int MAX_LEVEL;
	private boolean gameOver;
	private boolean wonGame;
	private boolean quit;
	
	/* @return Array with levels of the game
	 */
	private ArrayList<Level> getLevelArray(){
		return this.levels;
	}
	
	/* @return Max level reachable in this game
	 */
	private int getMaxLevel() {
		return this.MAX_LEVEL;
	}
	
	/* Create a Game with default maps but with specified variables
	 * @param type
	 * 			Type of guard found in Dungeon (level 1)
	 * @param nOgre
	 * 			Number of ogres found in Keep (level 2)
	 * @param maxLevel
	 * 			Max level alowed
	 */
	public Game(GuardType type, int nOgre, int maxLevel) {
		super();
		currentLevel = 1;
		MAX_LEVEL = maxLevel;
		wonGame = false;
		quit = false;
		ArrayList<Level> temp = new ArrayList<Level>();
		Level lvl1 = new Level1(type);
		temp.add(lvl1);
		
		Level lvl2 = new Level2(nOgre);
		temp.add(lvl2);
		
		this.setLevels(temp);
	}
	
	/* Create a Game with a costumizable Keep
	 * @param map
	 * 			Char map that sets initial game information
	 */
	public Game(char[][] map) {
		super();
		currentLevel = 1;
		gameOver = false;
		MAX_LEVEL = 1;
		wonGame = false;
		quit = false;
		ArrayList<Level> temp = new ArrayList<Level>();
		Level lvl2 = new Level2(map);
		temp.add(lvl2);
		this.setLevels(temp);
	}
	/*
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
		
		Level lvl1 = new Level1(OgreType.ROOKIE);
		temp.add(lvl1);
		
		if(MAX_LEVEL == 2) {
			char[][] map2 = {
					{'X','X','X','X','X','X','X'},
					{'X','H',' ',' ',' ',' ','X'},
					{'i',' ',' ',' ',' ',' ','X'},
					{'X','k',' ',' ',' ',' ','X'},
					{'X',' ',' ',' ','o',' ','X'},
					{'X',' ',' ',' ',' ',' ','X'},
					{'X','X','X','X','X','X','X'}
					
			};
			
			Level lvl2 = new Level2(map2);
			temp.add(lvl2);
		}

		this.setLevels(temp);
	}
	
	*/
	/*
	public Game(Game game) {
		super();
		this.levels = game.getLevelArray();
		this.currentLevel = game.getCurrentLevel();
		this.MAX_LEVEL = game.getMaxLevel();
		this.gameOver = game.isGameOver();
		this.wonGame = game.getWonGame();
		this.quit = game.isGameOver();
	}
	*/

	
	/* Move Hero of the Game
	 * @param cmd
	 * 			Command of the user
	 */
	public Symbol[][] moveHero(Cmd cmd) {
		int status = 0;
		status = this.getLevel().update(cmd);
		this.updateGameVariables(status, cmd);
		return getSymbolMap();
	}
	
	/* @return the current level
	 */
	private Level getLevel()
	{
		return levels.get(currentLevel-1);
	}
	
	/* Set array of levels to the game
	 * @param lvl
	 * 			Array of levels to be set
	 */
	private void setLevels(ArrayList<Level> lvl)
	{
		this.levels = lvl;
	}

	/* @return Game is over
	 */
	public boolean isGameOver()
	{
		return this.gameOver;
	}
	
	/* @return Game was won
	 */
	public boolean getWonGame()
	{
		return this.wonGame;
	}
	
	/* Set Game over
	 */
	private void setGameOVer()
	{
		this.gameOver = true;
	}
	
	/* Set Game won
	 */
	private void setWonGame()
	{
		this.wonGame = true;
	}

	/* Set Quit from Game
	 */
	private void setQuit()
	{
		this.quit = true;
	}
	
	/* @return Quit from Game
	 */
	public boolean getQuit()
	{
		return quit;
	}
	
	/* Update values of Game according to game logic and user command
	 * @param status
	 * 			Status returned from game logic
	 * @param cmd
	 * 			Command from user
	 */
	private void updateGameVariables(int status, Cmd cmd){
		if(cmd == Cmd.QUIT){
			this.setQuit();
		}
		switch(status){
		case 1:		
			this.setGameOVer();break;
		case 2:
			this.currentLevel += 1;
			if(currentLevel > MAX_LEVEL) {
				this.setWonGame();
				currentLevel = MAX_LEVEL;
			}
		}
	}

	/* @return Current Level index
	 */
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	public static char symbolToChar(Symbol s) {
		switch(s) {
		case HERO: return 'H';
		case HERO_WITH_KEY:return 'K';	case HERO_WITH_CLUB:return 'A';
		case GUARD:return 'G';			case GUARD_SLEEP:return 'g';
		case WALL:return 'X';			case CLUB_ON_KEY:return '$';
		case OGRE:return 'o';			case OGRE_ON_KEY:return '$';
		case OGRE_WEAPON:return '*';	case OGRE_STUNED:return '8';
		case LEVER:return 'k';			case KEY:return 'k';
		case DOOR_CLOSED:return 'i'; 	case DOOR_OPEN:return 'S';
		case CLEAR_SPACE:
		default:return ' ';
		}
	}
	
	private Symbol[][] getSymbolMap(){
		return getLevel().getMap().getSymbolMap();
	}
	
	public String mapString(Map map) {
		String mapa = new String();
	
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
	
}
