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
	
	
	/* Create a Game with default maps but with specified variables
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
	
	/* Create a Game with a customisable Keep
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
	
	/* @return Current Level index
	 */
	public int getCurrentLevel() {
		return currentLevel;
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
	
	/* @return Quit from Game
	 */
	public boolean getQuit()
	{
		return quit;
	}
	
	public boolean gameEnded() {
		return gameOver || wonGame || quit;
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

	/* @return Matrix of Symbols of current Map
	 */
	private Symbol[][] getSymbolMap(){
		return getLevel().getMap().getSymbolMap();
	}
		
}
