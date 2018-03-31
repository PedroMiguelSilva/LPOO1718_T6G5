package dkeep.logic;

import java.util.ArrayList;

/* Nomiantes a Level
 * @version 1.0
 * @since 1.0
 */
abstract public class Level
{
	private Hero hero;
	private ArrayList<Enemy> enemies;
	private ArrayList<Interactive> interactives;
	private Map map;
	
	/* Checks if the specific level winning condition has been reached
	 * @return Winning condition was met or not
	 */
	abstract public boolean hasWonLevel();
	
	/* Check if the hero dies from current Map
	 * @return Hero is dead
	 */
	public boolean heroDied() {
		if(this.getHero().isDead(this.getMap(), this.getEnemies())) {
			this.getHero().setDead();
			return true;
		}
		return false;
	}

	/* Move Enemies of the level
	 * @param enemies
	 * 			Enemies of the level
	 */
	public void moveEnemies(ArrayList<Enemy> enemies) {
		for(Enemy e: this.getEnemies())
		{
			e.move(this.getMap());
		}
	}
	
	/* Update the logic of the game according to command from User
	 * @param 	cmd
	 * 				Direction in which the user wants to move
	 * @return	0
	 * 				keep playing
	 * 			1
	 * 				hero died
	 * 			2	
	 * 				level up
	 */
	public int update(Cmd cmd) {
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

	/* Create Walls for a Map
	 * @param coord
	 * 			Coordinates of the walls to be created
	 * @return Array of the walls created
	 */
	public ArrayList<Entity> createWalls(ArrayList<Coord> coord){
		ArrayList<Entity> result = new ArrayList<Entity>();
	
		for(Coord c : coord) {
			Wall temp = new Wall(c);
			result.add(temp);
		}
		
		return result;
	}
	
	/* Create Doors for a Map
	 * @param coord
	 * 			Coordinates of the doors to be created
	 * @return Array of Doors created
	 */
	public ArrayList<Entity> createDoors(ArrayList<Coord> coord){
		ArrayList<Entity> result = new ArrayList<Entity>();
		
		for(Coord c : coord) {
			Door temp = new Door(c);
			result.add(temp);
		}
		return result;
	}
	
	/* Transform two arrays of x and y positions and converts into an array of coordinates
	 * @Note : A coordinate is created from values of xPos and yPos of same index
	 * @param posX
	 * 			Array of x values
	 * @param posY
	 * 			Array of y values
	 * @return Array of Coordinates created from array of x and y values
	 */
	public ArrayList<Coord> posToCoords(int[] posX, int[] posY){
		ArrayList<Coord> coords = new ArrayList<Coord>();
		
		for(int i = 0 ; i < posX.length ; i++) {
			Coord temp = new Coord(posX[i],posY[i]);
			coords.add(temp);
		}
		
		return coords;
	}
	
	/* @return Map of the Level
	 */
	public Map getMap()
	{
		return this.map;
	}

	/* @return Hero of the Level
	 */
	public Hero getHero()
	{
		return this.hero;
	}

	/* @return Enemies of the Level
	 */
	public ArrayList<Enemy> getEnemies()
	{
		return this.enemies;
	}

	/* @return Interactive objects of the Level
	 */
	public ArrayList<Interactive> getInteractives()
	{
		return this.interactives;
	}

	/* Search the Map for the Hero and returns it
	 * @return Hero of the Level
	 */
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
	
	/* Search the Map for the Enemies and returns them
	 * @return Array of Enemies of the Level
	 */
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
	
	/* Search the Map for the Interactive objects and returns them
	 * @return Array of Interactive objects of the Level
	 */
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
	
	/* Set new Map to the Level
	 * @param newMap
	 * 			New Map
	 */
	public void setMap(Map newMap)
	{
		this.map = newMap;
	}
	
	/* Set new Enemies to the Level
	 * @param newEnemies
	 * 			New Enemies
	 */
	public void setEnemies(ArrayList<Enemy> newEnemies)
	{
		this.enemies = newEnemies;
	}

	/* Set new Interactive objects to the Level
	 * @param newInteractives
	 * 			New Interactive objects
	 */
	public void setInteractives(ArrayList<Interactive> newInteractives)
	{
		this.interactives = newInteractives;
	}

	/* Set new Hero to the Level
	 * @param newHero
	 * 			New Hero
	 */
	public void setHero(Hero newHero)
	{
		this.hero = newHero;
	}
}
