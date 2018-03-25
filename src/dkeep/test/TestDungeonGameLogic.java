package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Cmd;
import dkeep.logic.Coord;
import dkeep.logic.Game;


public class TestDungeonGameLogic {
	
	@Test
	public void testMoveHeroIntoFreeCell() {
		Game game = new Game(1);
		assertEquals(new Coord(1,1),game.getLevel().getHero().getCoord());
		game.moveHero(Cmd.RIGHT);
		assertEquals(new Coord(1,2),game.getLevel().getHero().getCoord());
	}
	
	@Test
	public void testMoveHeroIntoWall() {
		Game game = new Game(1);
		assertEquals(new Coord(1,1),game.getLevel().getHero().getCoord());
		game.moveHero(Cmd.UP);
		assertEquals(new Coord(1,1),game.getLevel().getHero().getCoord());
	}
	
	@Test
	public void testHeroIsCapturedByGuard() {
		Game game = new Game(1);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		assertFalse(game.isGameOver());
		game.moveHero(Cmd.RIGHT);
		assertTrue(game.isGameOver());
	}
	
	@Test
	public void testHeroMoveTowardsCloseDoor() {
		Game game = new Game(1);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.LEFT);
		game.moveHero(Cmd.LEFT);
		assertEquals(1,game.getCurrentLevel());
		game.moveHero(Cmd.LEFT);
		assertEquals(1,game.getCurrentLevel());
	}
	
	

}
