package dkeep.logic;

import java.util.ArrayList;

/* Represents an Hero
 * @version 1.0
 * @since 1.0
 */
public class Hero extends Entity
{
	private boolean isAlive;
	private Key key;
	private boolean wonLevel;
	private boolean isArmed;

	/* Create an Hero with specified starting position and weapon
	 * @param startX
	 * 			The Hero's start x position
	 * @param startY
	 * 			The Hero's start y position
	 * @param isArmed
	 * 			Hero is armed or not
	 */
	public Hero(int startX, int startY, boolean isArmed)
	{
		super(startX , startY , Symbol.HERO);
		isAlive = true;
		wonLevel = false;
		Key key1 = new Key(0,0,100,100);
		this.key = key1;
		this.isArmed = isArmed;

		if(isArmed)
			this.setSymb(Symbol.HERO_WITH_CLUB);
	}

	
	//NOTE: CAN BE CHANGED TO CHECK ELEMENTS AROUND HERO AND STUN THEM
	/* Stun ogres around Hero if Hero is Armed
	 * @param enemies
	 * 			Array of enemies to be stunned
	 */
	public void stunNearBy(ArrayList<Enemy> enemies) {
		if(!isArmed)
			return;

		Coord temp = this.getCoord();
		Coord c1 = new Coord(temp.getX()+1,temp.getY());
		Coord c2 = new Coord(temp.getX(),temp.getY()+1);
		Coord c3 = new Coord(temp.getX()-1,temp.getY());
		Coord c4 = new Coord(temp.getX(),temp.getY()-1);

		for(Enemy e : enemies) {
			if(		(e.getCoord().equals(c1) ||
					e.getCoord().equals(c2) ||
					e.getCoord().equals(c3)||
					e.getCoord().equals(c4)) && e instanceof Ogre) {
				Ogre o = (Ogre)e;
				o.stun();
			}
		}
	}	

	/* Move Hero
	 * @param map
	 * 			Map in which the changes should be made
	 * @param interactives
	 * 			Array of objects that might be interacted with by Hero's movement
	 * @param enemies
	 * 			Array of enemies that might be interacted with by Hero's movement
	 */
	public void move(Map map, Cmd cmd, ArrayList<Interactive> interactives,ArrayList<Enemy> enemies){

		Coord newCoord = new Coord(this.getCoord());

		switch(cmd)
		{
		case LEFT:
		{
			newCoord.decY();
			break;
		}
		case UP:
		{
			newCoord.decX();
			break;
		}
		case DOWN:
		{
			newCoord.incX();
			break;
		}
		case RIGHT:
		{
			newCoord.incY();
			break;
		}
		}

		if(!map.canMove(newCoord))
			return;

		Symbol symb = map.getEnt(newCoord).getSymb();//quer saber qual é o elemento que esta onde ele quer ir

		if(symb == Symbol.CLEAR_SPACE)				//moving into an empty space
		{
			map.move(this, newCoord);
		}
		else										//moving into interactable
		{
			//TODO MAKE THIS ANOTHER FUNCTION TO MAKE CODE CLEAR
			for(Interactive i : interactives)
			{
				if(i.getCoord().equals(newCoord))//interactable has to be in the same coords as the hero intends to go to
				{
					i.trigger(this,interactives,map);
				}
			}	
		}


		stunNearBy(enemies);
	}

	/* @return Hero has won level
	 */
	public boolean hasWon()
	{
		return wonLevel;
	}

	//NOTE: xMax and yMax can be deleted since that information can be retrieved from map class
	
	/* Check if there is Danger near Hero with symbol of symb
	 * @param map
	 * 			Map in which changes should be made
	 * @param symb
	 * 			Symbol to be careful with
	 * @param xMax
	 * 			Maximum value that x can have
	 * @param yMax
	 * 			Maximum value that y can have
	 * @return Hero died from an Enemy of symbol symb
	 */
	public boolean isDangerous(Map map, Symbol symb, int xMax, int yMax)
	{
		if(symb == Symbol.GUARD_SLEEP || symb == Symbol.OGRE_STUNED)
			return false;

		Coord temp = new Coord(this.getCoord());

		Coord c1 = new Coord(temp.getX(),temp.getY());
		Coord c2 = new Coord(temp.getX(),temp.getY()+1);
		Coord c3 = new Coord(temp.getX()-1,temp.getY());
		Coord c4 = new Coord(temp.getX(),temp.getY()-1);

		if(!map.outOfBounds(c1,xMax,yMax) && map.getTopEnt(c1).getSymb() == symb)
			return true;

		if(!map.outOfBounds(c2,xMax,yMax) && map.getTopEnt(c2).getSymb() == symb)
			return true;

		if(!map.outOfBounds(c3,xMax,yMax) && map.getTopEnt(c3).getSymb() == symb)
			return true;

		if(!map.outOfBounds(c4,xMax,yMax) && map.getTopEnt(c4).getSymb() == symb)
			return true;

		return false;
	}

	/* Check if Hero dies in the current Map situation
	 * @param map
	 * 			Map in which changes should be made
	 * @param enemies
	 * 			Array of enemies that endanger the Hero
	 * @return Hero died from an Enemy in enemies
	 */
	public boolean isDead(Map map,ArrayList<Enemy> enemies)
	{
		//loop through all the enemies, for each one compare their symbols
		for(Enemy e : enemies)
		{
			if(isDangerous(map, e.getSymb(),map.getHeight(),map.getWidth()))
			{
				isAlive = false;
				return true;
			}
		}

		if(isDangerous(map, Symbol.OGRE_WEAPON,map.getHeight(),map.getWidth()))
		{
			isAlive = false;
			return true;
		}

		//no enemy was next to the hero
		return false;
	}

	
	/* Set Hero as dead
	 */
	public void setDead()
	{
		isAlive = false;
	}
	
	/* Set Key to Hero
	 * @param newKey
	 * 			Key to be given to Hero
	 */

	public void setKey(Key newKey)
	{
		this.key = newKey;
	}

	
	/* @return Hero's Key
	 */
	public Key getKey()
	{
		return this.key;
	}

}
