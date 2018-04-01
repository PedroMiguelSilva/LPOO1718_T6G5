package dkeep.logic;

import java.util.ArrayList;

/* Represents the first Level known as Dungeon
 * @version 1.0
 * @since 1.0
 */
public class Level1 extends Level
{
	/* Create Hero for Level1
	 */
	private void setHero(ArrayList<Entity> entities) {
		Hero hero = new Hero(1,1,false);
		this.setHero(hero);
		entities.add(hero);
	}

	/* Create Walls for Level1
	 */
	private void setWalls(ArrayList<Entity> entities){
		int wallX[] = {1,2,2,2,2,2,3,4,4,4,4,4,7,7,7,7,7,7,8};
		int wallY[] = {6,1,2,4,5,6,6,1,2,4,5,6,1,2,4,5,6,7,6};
		ArrayList<Entity> walls = createWalls(posToCoords(wallX,wallY));
		entities.addAll(walls);
	}

	/* Create Enemy for Level1
	 */
	private void setGuard(GuardType type,ArrayList<Enemy> enemies,ArrayList<Entity> entities) {
		//SET ENEMIES
		int xPos[] = {1,1,2,3,4,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,5,4,3,2};
		int yPos[] = {8,7,7,7,7,7,6,5,4,3,2,1,1,2,3,4,5,6,7,8,8,8,8,8};

		Enemy ent = new Rookie(1,8,posToCoords(xPos,yPos));

		switch(type) {
		case ROOKIE:
			break;
		case SUSPICIOUS:
			ent = new Suspicious(1,8,posToCoords(xPos,yPos));
			break;
		case DRUNKEN:
			ent = new Drunken(1,8,posToCoords(xPos,yPos));
			break;
		default:
			break;
		}
		
		enemies.add(ent);
		entities.add(ent);
		this.setEnemies(enemies);
	}

	/* Create Doors for Level1
	 */
	private void setDoors(ArrayList<Entity> entities, ArrayList<Interactive> interactives) {
		int doorX[] = {1,3,3,8,8};
		int doorY[] = {4,2,4,2,4};
		ArrayList<Entity> doors = createDoors(posToCoords(doorX,doorY));
		Door door1 = new Door(5,0);
		interactives.add(door1);
		Door door2 = new Door(6,0);
		interactives.add(door2);
		entities.add(door1);
		entities.add(door2);
		entities.addAll(doors);
	}
	
	/* Create Lever for Level1
	 */
	private void setLever(ArrayList<Entity> entities, ArrayList<Interactive> interactives) {
		//SET INTERACTIVES
		int lever1TrigX[] = {5,6};									//coordinates of objects triggered by lever
		int lever1TrigY[] = {0,0};									//coordinates of objects triggered by lever
		Lever lever1 = new Lever(8,7,posToCoords(lever1TrigX,lever1TrigY));

		interactives.add(lever1);
		entities.add(lever1);
	}
	
	/* Create a Dungeon with a specified guard
	 * @param type
	 * 			Type of the Guard of the Dungeon
	 */
	public Level1(GuardType type)
	{
		ArrayList<Entity> entities = new ArrayList<Entity>();
		ArrayList<Enemy> enemiesLevel1 = new ArrayList<Enemy>();
		ArrayList<Interactive> interactives = new ArrayList<Interactive>();

		setWalls(entities);
		setHero(entities);
		setGuard(type,enemiesLevel1,entities);
		setDoors(entities,interactives);
		setLever(entities,interactives);
		
		this.setInteractives(interactives);
		Map mapLvl1 = new Map(10,10,entities);
		this.setMap(mapLvl1);
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

}
