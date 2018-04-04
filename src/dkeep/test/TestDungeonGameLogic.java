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
		Game game = new Game(GuardType.ROOKIE,2,2);
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
		assertEquals(1,game.getCurrentLevel());
		game.moveHero(Cmd.LEFT);
		assertEquals(2,game.getCurrentLevel());
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
		int prevX = x;
		int y = searchGuardY(map);
		int prevY = y;
		boolean left = true, down = false,right = false, up = false;
		
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
			
			if(hasReversedDirection(x,prevX,y,prevY,left,down,right,up)) {
				hasChangedDirection = true;
			}
			
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
		int prevX = x;
		int y = searchGuardY(map);
		int prevY = y;
		boolean left = true, down = true,right = false, up = false, hasChangedDirectionOnce = false;
		
		//int currIndex = s.getIndex(), prevIndex = s.getIndex();
		//int coordSize = s.getCoordSize();
		
		while(!hasChangedDirectionOnce) {
			//update values
			game.moveHero(Cmd.UP);
			map = game.getSymbolMap();
			x = searchGuardX(map);
			y = searchGuardY(map);
			
			if(hasReversedDirection(x,prevX,y,prevY,left,down,right,up)) {
				hasChangedDirectionOnce = true;
			}
			
			
			prevX = x;
			prevY = y;
		}
	}
	

	private boolean hasReversedDirection(int x, int prevX, int y, int prevY,boolean left, boolean down, boolean right, boolean up) {
		boolean newMoveLeft = left;
		boolean newMoveDown = down;
		boolean newMoveUp = up;
		boolean newMoveRight = right;
		
		if(x-prevX > 0) {
			up = true;
		}
		else if(x-prevX < 0) {
			down = true;
		}
		else if(y-prevY > 0) {
			left = true;
		}
		else if(y-prevY < 0 ) {
			right = true;
		}
		
		if(newMoveLeft && !left || !newMoveLeft && left)
			return true;
		else if(newMoveDown && !down|| !newMoveDown && down)
			return true;
		else if(newMoveUp && !up|| !newMoveUp && up)
			return true;
		else if(newMoveRight && !right|| !newMoveRight && right)
			return true;
		else
			return false;
		
	}
	
}
