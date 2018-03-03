package dkeep.logic;

import java.util.Vector;

abstract public class Level
{
	//Attributes
	private Hero hero;
	private Vector<Enemy> enemies;
	private Vector<Interactive> interactives;
	private Map map;
	

	//Constructor

	
	//Methods

	
	/*
	 * @brief 	updates the logic of the game acording to heroMovement
	 * @param 	heroMovement
	 * 				direction in which the user wants to move
	 * @return	0
	 * 				keep playing
	 * 			1
	 * 				hero died
	 * 			2	
	 * 				hero won level
	 */
	abstract public int update(char heroMovement);
	
	/*
	 * @brief updates the values of Level with values of current level
	 */
	abstract public void loadLevel();
	
	
	
	
	
	
	
	
	
	
	
	//GET METHODS
	public Map getMap()
	{
		return this.map;
	}

	public Hero getHero()
	{
		return this.hero;
	}

	public Vector<Enemy> getEnemies()
	{
		return this.enemies;
	}

	public Vector<Interactive> getInteractives()
	{
		return this.interactives;
	}
}
