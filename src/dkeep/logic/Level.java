package dkeep.logic;

import java.util.ArrayList;

abstract public class Level
{
	//Attributes
	private Hero hero;
	private ArrayList<Enemy> enemies;
	private ArrayList<Interactive> interactives;
	private Map map;
	
	abstract public boolean hasWonLevel();
	
	public boolean heroDied() {
		if(this.getHero().isDead(this.getMap(), this.getEnemies())) {
			this.getHero().setDead();
			return true;
		}
		return false;
	}

	public void moveEnemies(ArrayList<Enemy> enemies) {
		for(Enemy e: this.getEnemies())
		{
			e.move(this.getMap());
		}
	}
	
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
	 */
	public int update(Cmd cmd) {
		
		//move hero		
		this.getHero().move(getMap(), cmd, getInteractives(), getEnemies());
		
		//check if won
		if(hasWonLevel())
			return 2;
		
		//check if hero died from moving
		if(heroDied())
			return 1;
		
		//move enemies
		moveEnemies(this.getEnemies());
		
		//hero attacks
		this.getHero().stunNearBy(enemies);
		
		//check if hero died from moving
		if(heroDied())
			return 1;
		
		return 0;
	}

	
	//Methods
	
	public ArrayList<Entity> createWalls(ArrayList<Coord> coord){
		ArrayList<Entity> result = new ArrayList<Entity>();
	
		for(Coord c : coord) {
			Wall temp = new Wall(c);
			result.add(temp);
		}
		
		return result;
	}
	
	public ArrayList<Entity> createDoors(ArrayList<Coord> coord){
		ArrayList<Entity> result = new ArrayList<Entity>();
		
		for(Coord c : coord) {
			Door temp = new Door(c);
			result.add(temp);
		}
		return result;
	}
	
	public ArrayList<Coord> posToCoords(int[] posX, int[] posY){
		ArrayList<Coord> coords = new ArrayList<Coord>();
		
		for(int i = 0 ; i < posX.length ; i++) {
			Coord temp = new Coord(posX[i],posY[i]);
			coords.add(temp);
		}
		
		return coords;
	}
	
	
	
	
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

	public Hero getHeroFromMap() {
		for(int i = 0 ; i < map.getMap().length; i++) {
			for(int j = 0; j < map.getMap()[0].length; j++) {
				if(map.getMap()[i][j].getTop() instanceof Hero) {
					return (Hero)map.getMap()[i][j].getTop();
				}
			}
		}
		return null;
	}
	
	public ArrayList<Enemy> getEnemiesFromMap(){
		ArrayList<Enemy> result = new ArrayList<Enemy>();
		for(int i = 0 ; i < map.getMap().length; i++) {
			for(int j = 0; j < map.getMap()[0].length; j++) {
				if(map.getMap()[i][j].getTop() instanceof Ogre) {
					result.add((Ogre)map.getMap()[i][j].getTop());
				}
			}
		}
		return result;
	}
	
	public ArrayList<Interactive> getInteractivesFromMap(){
		ArrayList<Interactive> result = new ArrayList<Interactive>();
		
		for(int i = 0 ; i < map.getMap().length; i++) {
			for(int j = 0; j < map.getMap()[0].length; j++) {
				if(map.getMap()[i][j].getBot() instanceof Key) {
					result.add((Key)map.getMap()[i][j].getBot());
				}
				else if(map.getMap()[i][j].getBot() instanceof Door) {
					result.add((Door)map.getMap()[i][j].getBot());
				}
			}
		}
		return result;
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
