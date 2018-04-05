package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.CustomLevel;
import dkeep.logic.Symbol;

public class TestCustomGame {

	@Test
	public void TestSizeOfDefaultConstructor() {
		CustomLevel lvl = new CustomLevel();
		assertEquals(7,lvl.getMap().length);
	}

	@Test
	public void TestCreationOfWalls() {
		CustomLevel lvl = new CustomLevel();
		assertEquals(Symbol.WALL,lvl.getMap()[0][0]);
		assertEquals(Symbol.WALL,lvl.getMap()[6][6]);
	}
	
	@Test
	public void TestToAddEntityToBorderOfMapAndMiss() {
		CustomLevel lvl = new CustomLevel();
		lvl.editMap(Symbol.HERO, 0, 1);
		lvl.editMap(Symbol.HERO, 1, 0);
		lvl.editMap(Symbol.HERO, 6, 1);
		lvl.editMap(Symbol.DOOR_CLOSED, 1, 6);
		assertEquals(Symbol.WALL,lvl.getMap()[0][0]);
	}
	
	@Test
	public void TestAddEntityInValidPosition() {
		CustomLevel lvl = new CustomLevel();
		lvl.editMap(Symbol.HERO,1, 1);
		assertEquals(Symbol.HERO,lvl.getMap()[1][1]);
	}
	
	@Test
	public void TestCreateMapWithOnlyKey() {
		CustomLevel lvl = new CustomLevel();
		lvl.editMap(Symbol.KEY, 2, 2);
		assertFalse(lvl.isValid());
	}
	
	@Test
	public void TestCreateMapWithOnlyKeyAndHero() {
		CustomLevel lvl = new CustomLevel();
		lvl.editMap(Symbol.KEY, 2, 2);
		lvl.editMap(Symbol.HERO, 2, 1);
		assertFalse(lvl.isValid());
	}
	
	@Test
	public void TestCreateMapWithNoOgre() {
		CustomLevel lvl = new CustomLevel();
		lvl.editMap(Symbol.KEY, 2, 2);
		lvl.editMap(Symbol.HERO, 2, 1);
		lvl.editMap(Symbol.DOOR_CLOSED, 0, 1);
		assertFalse(lvl.isValid());
	}
	
	@Test
	public void TestPutADoorInACornerOfMap() {
		CustomLevel lvl = new CustomLevel();
		
		lvl.editMap(Symbol.DOOR_CLOSED, 0, 0);
		assertEquals(Symbol.WALL,lvl.getMap()[0][0]);
		
		lvl.editMap(Symbol.DOOR_CLOSED,6, 0);
		assertEquals(Symbol.WALL,lvl.getMap()[6][0]);
		
		lvl.editMap(Symbol.DOOR_CLOSED, 0, 6);
		assertEquals(Symbol.WALL,lvl.getMap()[0][6]);
		
		lvl.editMap(Symbol.DOOR_CLOSED, 6,6);
		assertEquals(Symbol.WALL,lvl.getMap()[6][6]);
	}
	
	@Test
	public void TestLevelWithoutOgreOrKey() {
		CustomLevel lvl = new CustomLevel();
		lvl.editMap(Symbol.DOOR_CLOSED, 0, 1);
		lvl.editMap(Symbol.HERO, 2, 1);
		assertFalse(lvl.isValid());
	}
	
	@Test
	public void TestWithTooManyOgres() {
		CustomLevel lvl = new CustomLevel();
		lvl.editMap(Symbol.DOOR_CLOSED, 1,0);
		lvl.editMap(Symbol.HERO, 2, 1);
		lvl.editMap(Symbol.KEY, 2, 2);
		lvl.editMap(Symbol.OGRE, 3, 3);
		lvl.editMap(Symbol.OGRE, 3, 4);
		lvl.editMap(Symbol.OGRE, 2, 3);
		lvl.editMap(Symbol.OGRE, 3, 2);
		lvl.editMap(Symbol.OGRE, 4, 2);
		assertFalse(lvl.isValid());
	}
	
	@Test
	public void TestValidMap() {
		CustomLevel lvl = new CustomLevel();
		lvl.editMap(Symbol.DOOR_CLOSED, 0, 1);
		lvl.editMap(Symbol.HERO, 2, 1);
		lvl.editMap(Symbol.KEY, 2, 2);
		lvl.editMap(Symbol.OGRE, 3, 3);
		lvl.editMap(Symbol.OGRE, 3, 4);
		lvl.editMap(Symbol.OGRE, 3, 2);
		lvl.editMap(Symbol.OGRE, 4, 2);
		assertTrue(lvl.isValid());
	}
}
