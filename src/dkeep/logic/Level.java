package dkeep.logic;

import java.util.ArrayList;

abstract public class Level
{
	//Attributes
	private Hero hero;
	private ArrayList<Enemy> enemies;
	private ArrayList<Interactive> interactives;
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
	 * 				level up
	 * 			3
	 * 				won game
	 */
	abstract public int update(char heroMovement);
	
	
	
	
	
	
	
	
	
	
	
	//GET METHODS
	public Map getMap()
	{
		return this.map;
	}

	public Hero getHero()
	{
		return this.hero;
	}

	public ArrayList<Enemy> getEnemies()
	{
		return this.enemies;
	}

	public ArrayList<Interactive> getInteractives()
	{
		return this.interactives;
	}

	
	//SET METHODS
	public void setMap(Map newMap)
	{
		this.map = newMap;
	}
	
	public void setEnemies(ArrayList<Enemy> newEnemies)
	{
		this.enemies = newEnemies;
	}

	public void setInteractives(ArrayList<Interactive> newInteractives)
	{
		this.interactives = newInteractives;
	}

	public void setHero(Hero newHero)
	{
		this.hero = newHero;
	}
}
