package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Cmd;
import dkeep.logic.Coord;
import dkeep.logic.Drunken;
import dkeep.logic.Game;
import dkeep.logic.GuardType;
import dkeep.logic.Suspicious;
import dkeep.logic.Symbol;


public class TestDungeonGameLogic {
	
	@Test
	public void testMoveHeroIntoFreeCell() {
		Game game = new Game(GuardType.ROOKIE,2,1);
		assertEquals(new Coord(1,1),game.getLevel().getHero().getCoord());
		game.moveHero(Cmd.RIGHT);
		assertEquals(new Coord(1,2),game.getLevel().getHero().getCoord());
	}
	
	@Test
	public void testMoveHeroIntoWall() {
		Game game = new Game(GuardType.ROOKIE,2,1);
		assertEquals(new Coord(1,1),game.getLevel().getHero().getCoord());
		game.moveHero(Cmd.UP);
		assertEquals(new Coord(1,1),game.getLevel().getHero().getCoord());
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
		Coord door1 = new Coord(5,0);
		Coord door2 = new Coord(6,0);
		assertEquals(Symbol.DOOR_CLOSED, game.getLevel().getMap().getEnt(door1).getSymb());
		assertEquals(Symbol.DOOR_CLOSED, game.getLevel().getMap().getEnt(door2).getSymb());
		game.moveHero(Cmd.LEFT);
		assertEquals(Symbol.DOOR_OPEN, game.getLevel().getMap().getEnt(door1).getSymb());
		assertEquals(Symbol.DOOR_OPEN, game.getLevel().getMap().getEnt(door2).getSymb());
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
		assertEquals(1,game.getCurrentLevel());
		game.moveHero(Cmd.LEFT);
		assertEquals(2,game.getCurrentLevel());
		
	}
	
	//testing the drunken guard
	@Test
	public void testDrunkenGuardCreationAndMovement() {
		Game game = new Game(GuardType.DRUNKEN,2,1);
		
		boolean hasSlept = false,hasChangedDirection = false, wokeUp = false;
		
		while(!hasSlept || !hasChangedDirection || !wokeUp) {
			game.moveHero(Cmd.UP);
			Drunken g = (Drunken)game.getLevel().getEnemies().get(0);
			if(g.isSleeping())
				hasSlept = true;
			if(g.hasChangedDirection())
				hasChangedDirection = true;
			if(g.hasWokenUp())
				wokeUp = true;
		}	
	}
	
	@Test(timeout = 1000)
	public void testSuspiciousGuardCreationAndMovement() {
		Game game = new Game(GuardType.SUSPICIOUS,1,1);
		Suspicious s = (Suspicious)game.getLevel().getEnemies().get(0);
		boolean hasDecreased = false, hasIncreased = false;
		
		int currIndex = s.getIndex(), prevIndex = s.getIndex();
		int coordSize = s.getCoordSize();
		
		while(!hasDecreased || !hasIncreased) {
			//update values
			game.moveHero(Cmd.UP);
			currIndex = s.getIndex();
			
			if(isLessThan(currIndex,prevIndex,coordSize)) {
				hasDecreased = true;
			}
			else {
				hasIncreased = true;
			}
			
			prevIndex = currIndex;
		}
	}

	private boolean isLessThan(int currIndex, int prevIndex, int coordSize) {
		if(Math.abs(currIndex-prevIndex) == 1) {
			//two followed values
			return currIndex < prevIndex;
		}
		else if(currIndex == coordSize-1) {
			return true;
		}
		else
			return false;
	}
	
}
