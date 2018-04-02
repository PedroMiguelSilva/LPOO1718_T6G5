package dkeep.logic;

public class GameState {
	private Symbol[][] map;
	private int currentLevel;
	private boolean gameOver;
	private boolean wonGame;
	private boolean quit;
	
	public GameState(Symbol[][] map, Game game) {
		this.map = map;
		this.currentLevel = game.getCurrentLevel();
		this.gameOver = game.isGameOver();
		this.wonGame = game.getWonGame();
		this.quit = game.getQuit();
	}

	public boolean gameEnded() {
		return gameOver || wonGame || quit;
	}
	
	public Symbol[][] getMap(){
		return this.map;
	}
	
	public int getLevel() {
		return this.currentLevel;
	}
	
	public boolean getGameOver() {
		return this.gameOver;
	}
	
	public boolean getWonGame() {
		return this.wonGame;
	}
	
	public boolean getQuit() {
		return this.quit;
	}
}
