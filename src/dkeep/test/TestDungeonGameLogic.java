package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Cmd;
import dkeep.logic.Game;
import dkeep.logic.GuardType;
import dkeep.logic.Symbol;


public class TestDungeonGameLogic {

	@Test
	public void testMoveHeroIntoFreeCell() {
		Game game = new Game(GuardType.ROOKIE,2,1);
		game.moveHero(Cmd.START);
		Symbol[][] map = game.getSymbolMap();
		assertEquals(Symbol.HERO, map[1][1]);
		game.moveHero(Cmd.RIGHT);
		map = game.getSymbolMap();
		assertEquals(Symbol.HERO,map[1][2]);
	}

	@Test
	public void testMoveHeroIntoWall() {
		Game game = new Game(GuardType.ROOKIE,2,1);
		game.moveHero(Cmd.START);
		Symbol[][] map = game.getSymbolMap();
		assertEquals(Symbol.HERO, map[1][1]);
		game.moveHero(Cmd.UP);
		map = game.getSymbolMap();
		assertEquals(Symbol.HERO,map[1][1]);
	}


	@Test
	public void testHeroIsCapturedByGuard() {
		Game game = new Game(GuardType.ROOKIE,2,1);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		assertFalse(game.isGameOver());
		game.moveHero(Cmd.RIGHT);
		assertTrue(game.isGameOver());
		assertTrue(game.gameEnded());
		assertEquals("Game Over",game.endingMessage());
	}

	@Test
	public void testHeroMoveTowardsCloseDoor() {
		Game game = new Game(GuardType.ROOKIE,2,1);
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

	@Test
	public void testHeroOpenLeverAndDoorsOpen() {
		Game game = new Game(GuardType.ROOKIE,2,1);
		game.moveHero(Cmd.START);
		Symbol[][] map = game.getSymbolMap();
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.UP);
		game.moveHero(Cmd.UP);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		map = game.getSymbolMap();
		assertEquals(Symbol.DOOR_CLOSED, map[5][0]);
		assertEquals(Symbol.DOOR_CLOSED, map[6][0]);
		game.moveHero(Cmd.LEFT);
		map = game.getSymbolMap();
		assertEquals(Symbol.DOOR_OPEN, map[5][0]);
		assertEquals(Symbol.DOOR_OPEN, map[6][0]);
	}

	@Test
	public void testHeroOpenLeverAndWinLevel() {
		Game game = new Game(GuardType.ROOKIE,2,1);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.UP);
		game.moveHero(Cmd.UP);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.LEFT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.UP);
		game.moveHero(Cmd.UP);
		game.moveHero(Cmd.LEFT);
		game.moveHero(Cmd.LEFT);
		game.moveHero(Cmd.LEFT);
		game.moveHero(Cmd.LEFT);
		game.moveHero(Cmd.LEFT);
		game.moveHero(Cmd.LEFT);
		game.moveHero(Cmd.LEFT);
		assertFalse(game.gameEnded());
		game.moveHero(Cmd.LEFT);
		assertTrue(game.gameEnded());
		assertTrue(game.getWonGame());
		assertEquals("Congratulations",game.endingMessage());
	}


	public int searchGuardX(Symbol[][] map) {
		for(int i = 0 ; i < map.length ; i++) {
			for(int j = 0; j < map[0].length ; j++) {
				if(map[i][j] == Symbol.GUARD_SLEEP || map[i][j] == Symbol.GUARD) {
					return i;
				}
			}
		}
		return 0;
	}

	public int searchGuardY(Symbol[][] map) {
		for(int i = 0 ; i < map.length ; i++) {
			for(int j = 0; j < map[0].length ; j++) {
				if(map[i][j] == Symbol.GUARD_SLEEP || map[i][j] == Symbol.GUARD) {
					return j;
				}
			}
		}
		return 0;
	}

	@Test
	public void testDrunkenGuardCreationAndMovement() {
		Game game = new Game(GuardType.DRUNKEN,2,1);
		game.moveHero(Cmd.START);
		Symbol[][] map = game.getSymbolMap();
		int x = searchGuardX(map);
		int prevX = 99999;
		int oldX = 99999;
		int y = searchGuardY(map);
		int prevY = 99999;
		int oldY = 99999;

		boolean hasSlept = false,hasChangedDirection = false, wokeUp = false;

		while(!hasSlept || !hasChangedDirection || !wokeUp) {
			game.moveHero(Cmd.UP);
			map = game.getSymbolMap();
			x = searchGuardX(map);
			y = searchGuardY(map);

			if(map[x][y] == Symbol.GUARD_SLEEP)
				hasSlept = true;

			if(map[x][y] == Symbol.GUARD && hasSlept)
				wokeUp = true;

			map = game.getSymbolMap();
			oldX = prevX;
			oldY = prevY;
			prevX = x;
			prevY = y;
			x = searchGuardX(map);
			y = searchGuardY(map);

			if(x == oldX && y == oldY)
				hasChangedDirection = true;


			prevX = x;
			prevY = y;
		}	
	}

	@Test(timeout = 1000)
	public void testSuspiciousGuardCreationAndMovement() {
		Game game = new Game(GuardType.SUSPICIOUS,1,1);
		game.moveHero(Cmd.START);
		Symbol[][] map = game.getSymbolMap();
		int x = searchGuardX(map);
		int prevX = 999999;
		int oldX = 999999;
		int y = searchGuardY(map);
		int prevY = 999999;
		int oldY = 999999;
		boolean hasChangedDirection = false;

		while(!hasChangedDirection) {
			map = game.getSymbolMap();
			oldX = prevX;
			oldY = prevY;
			prevX = x;
			prevY = y;
			x = searchGuardX(map);
			y = searchGuardY(map);

			if(x == oldX && y == oldY)
				hasChangedDirection = true;

			game.moveHero(Cmd.UP);			
		}
	}
	
	@Test
	public void TestToggleDoor() {
		Game game = new Game(GuardType.ROOKIE,2,1);
		Symbol[][] map = game.getSymbolMap();
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.UP);
		game.moveHero(Cmd.UP);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.RIGHT);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.LEFT);
		map = game.getSymbolMap();
		assertEquals(Symbol.DOOR_OPEN, map[5][0]);
		assertEquals(Symbol.DOOR_OPEN, map[6][0]);
		game.moveHero(Cmd.LEFT);
		map = game.getSymbolMap();
		assertEquals(Symbol.DOOR_CLOSED, map[5][0]);
		assertEquals(Symbol.DOOR_CLOSED, map[6][0]);
	}
}

