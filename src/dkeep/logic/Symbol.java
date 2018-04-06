package dkeep.logic;

/* Types of Symbols Entities can have
 * <li>{@link #HERO}</li>
 * <li>{@link #HERO_WITH_KEY}</li>
 * <li>{@link #HERO_WITH_CLUB}</li>
 * <li>{@link #GUARD}</li>
 * <li>{@link #GUARD_SLEEP}</li>
 * <li>{@link #WALL}</li>
 * <li>{@link #CLEAR_SPACE}</li>
 * <li>{@link #OGRE}</li>
 * <li>{@link #OGRE_ON_KEY}</li>
 * <li>{@link #OGRE_WEAPON}</li>
 * <li>{@link #OGRE_STUNED}</li>
 * <li>{@link #CLUB_ON_KEY}</li>
 * <li>{@link #LEVER}</li>
 * <li>{@link #KEY}</li>
 * <li>{@link #DOOR_CLOSED}</li>
 * <li>{@link #DOOR_OPEN}</li>
 */
public enum Symbol {
	/*
	 * Hero
	 */
	HERO,
	/*
	 * Hero with Key
	 */
	HERO_WITH_KEY,
	/*
	 * Hero with Club
	 */
	HERO_WITH_CLUB,
	/*
	 * Guard
	 */
	GUARD,	
	/*
	 * Guard Sleep
	 */
	GUARD_SLEEP,
	/*
	 * Wall
	 */
	WALL,	
	/*
	 * Clear Space
	 */
	CLEAR_SPACE,
	/*
	 * Ogre
	 */
	OGRE,	
	/*
	 * Ogre on Key
	 */
	OGRE_ON_KEY,
	/*
	 * Ogre weapon
	 */
	OGRE_WEAPON,
	/*
	 * Ogre stuned
	 */
	OGRE_STUNED,
	/*
	 * Club on Key
	 */
	CLUB_ON_KEY,	
	/*
	 * Lever
	 */
	LEVER,
	/*
	 * Key
	 */
	KEY,
	/*
	 * Door Closed
	 */
	DOOR_CLOSED,
	/*
	 * Door Open
	 */
	DOOR_OPEN;			
}