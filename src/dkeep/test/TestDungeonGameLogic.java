package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Coord;
import dkeep.logic.Game;


public class TestDungeonGameLogic {
	
	@Test
	public void testMoveHeroIntoFreeCell() {
		Game game = new Game(1);
		assertEquals(new Coord(1,1),game.getLevel().getHero().getCoord());
		game.getLevel().update('d');
		assertEquals(new Coord(1,2),game.getLevel().getHero().getCoord());
	}

}
