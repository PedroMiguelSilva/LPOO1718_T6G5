package dkeep.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** Represents the game
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
	private static Map<Symbol,Character> parseToChar;
	static
	{
		parseToChar = new HashMap<Symbol,Character>();
		parseToChar.put(Symbol.HERO, 'H');
		parseToChar.put(Symbol.HERO_WITH_KEY, 'K');
		parseToChar.put(Symbol.HERO_WITH_CLUB, 'A');
		parseToChar.put(Symbol.GUARD, 'G');
		parseToChar.put(Symbol.GUARD_SLEEP, 'g');
		parseToChar.put(Symbol.WALL, 'X');
		parseToChar.put(Symbol.CLUB_ON_KEY, '$');
		parseToChar.put(Symbol.OGRE, 'o');
		parseToChar.put(Symbol.OGRE_ON_KEY, '$');
		parseToChar.put(Symbol.OGRE_STUNED, '8');
		parseToChar.put(Symbol.OGRE_WEAPON, '*');
		parseToChar.put(Symbol.LEVER, 'k');
		parseToChar.put(Symbol.KEY, 'k');
		parseToChar.put(Symbol.DOOR_CLOSED, 'i');
		parseToChar.put(Symbol.DOOR_OPEN, 'S');
		parseToChar.put(Symbol.CLEAR_SPACE, ' ');
	}
	
	
	/** Create a Game with default maps but with specified variables
	 * @param type
	 * 			Type of guard found in Dungeon (level 1)
	 * @param nOgre
	 * 			Number of ogres found in Keep (level 2)
	 * @param maxLevel
	 * 			Max level allowed
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
	
	/** Create a Game with a customisable Keep
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
	
	/**
	 * Create a Game with a customisable Keep
	 * @param map
	 * 			Symbol matrix that sets initial game information
	 */
	public Game(Symbol[][] map) {
		this(symbMapToCharMap(map));
	}

	/** Move Hero of the Game
	 * @param cmd
	 * 			Command of the user
	 */
	public void moveHero(Cmd cmd) {
		int status = 0;
		status = this.getLevel().update(cmd);
		this.updateGameVariables(status, cmd);
	}
	
	/** @return Current Level index
	 */
	public int getCurrentLevel() {
		return currentLevel;
	}
		
	/** @return Game is over
	 */
	public boolean isGameOver()
	{
		return this.gameOver;
	}
	
	/** @return Game was won
	 */
	public boolean getWonGame()
	{
		return this.wonGame;
	}
	
	/** @return Quit from Game
	 */
	public boolean getQuit()
	{
		return quit;
	}
	
	public boolean gameEnded() {
		return gameOver || wonGame || quit;
	}
	
	public String endingMessage() {
		if(isGameOver())
			return "Game Over";
		else if(getWonGame())
			return "Congratulations";
		else
			return "Quit Game";
	}
	
	/** @return Matrix of Symbols of current Map
	 */
	public Symbol[][] getSymbolMap(){
		return getLevel().getMap().getSymbolMap();
	}
	
	
	/** @return the current level
	 */
	private Level getLevel()
	{
		return levels.get(currentLevel-1);
	}
	
	/** Set array of levels to the game
	 * @param lvl
	 * 			Array of levels to be set
	 */
	private void setLevels(ArrayList<Level> lvl)
	{
		this.levels = lvl;
	}

	/** Set Game over
	 */
	private void setGameOVer()
	{
		this.gameOver = true;
	}
	
	/** Set Game won
	 */
	private void setWonGame()
	{
		this.wonGame = true;
	}

	/** Set Quit from Game
	 */
	private void setQuit()
	{
		this.quit = true;
	}
	
	/** Update values of Game according to game logic and user command
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
	
	private static char[][] symbMapToCharMap(Symbol[][] map) {
		int h = map.length, w = map[0].length; 
		char[][] result = new char[h][w];
		for(int x = 0; x < h; x++) {
			for(int y = 0 ; y < w ; y++) {
				result[x][y] = parseToChar.get(map[x][y]);
			}
		}
		return result;
	}
		
}
