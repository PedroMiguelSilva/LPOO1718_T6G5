package dkeep.logic;

import java.util.ArrayList;

/* Represents the second level known as Keep
 * @version 1.0
 * @since 1.0
 */
public class Level2 extends Level
{
	/* Create a Keep with a specified number of Ogres
	 * @param nOgre
	 * 			Number of Ogres in the Keep
	 */
	public Level2(int nOgre){
		ArrayList<Entity> entities = new ArrayList<Entity>();
		ArrayList<Enemy> enemiesLevel2 = new ArrayList<Enemy>();
		ArrayList<Interactive> interactives = new ArrayList<Interactive>();
		
		setHero(entities);
		setOgres(entities,enemiesLevel2,nOgre);
		setDoors(entities,interactives);
		setKey(entities,interactives);
		
		this.setInteractives(interactives);
		Map mapLvl2 = new Map(9,9,entities);
		this.setMap(mapLvl2);
	}
	
	/* Create a Keep from information of a matrix of chars
	 * @param map
	 * 			Matrix of chars with information for	 the Level
	 */
	public Level2(char[][] map) {
		Map lvl2 = new Map(map);
		this.setMap(lvl2);
		
		//set hero from map
		this.setHero(getHeroFromMap());
		
		//set enemies from map
		this.setEnemies(getEnemiesFromMap());
		
		//set interactives from map
		this.setInteractives(getInteractivesFromMap());
	}
	
	/* Checks if the specific level winning condition has been reached
	 * @return Winning condition was met or not
	 */
	public boolean hasWonLevel() {
		Coord curr = this.getHero().getCoord();
		if(this.getMap().getBotEnt(curr).getSymb() == Symbol.DOOR_OPEN)
			return true;
		else
			return false;
	}
	
	private void setKey(ArrayList<Entity> entities, ArrayList<Interactive> interactives) {
		Coord pos = new Coord(1,7), door = new Coord(1,0);
		Key key1 = new Key(pos,door);
		interactives.add(key1);
		entities.add(key1);
	}
	
	private void setDoors(ArrayList<Entity> entities, ArrayList<Interactive> interactives) {
		Door door1 = new Door(1,0);
		interactives.add(door1);
		entities.add(door1);
	}
	
	private void setOgres(ArrayList<Entity> entities,ArrayList<Enemy> enemiesLevel2,int nOgre) {
		for(int i = 0; i < nOgre ; i++) {
			Coord pos = new Coord(1,4);
			Ogre ogre1 = new Ogre(pos,true);
			enemiesLevel2.add(ogre1);
			entities.add(ogre1);
		}
		this.setEnemies(enemiesLevel2);
	}
	
	private void setHero(ArrayList<Entity> entities) {
		Hero hero = new Hero(7,1,true);
		this.setHero(hero);
		entities.add(hero);
	}
}
