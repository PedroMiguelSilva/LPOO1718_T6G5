package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.Cmd;
import dkeep.logic.Coord;
import dkeep.logic.Game;
import dkeep.logic.Ogre;
import dkeep.logic.Symbol;

public class TestKeepGameLogic {

	char[][] map1 = {
			{'X','X','X','X','X'},
			{'X','H',' ','o','X'},
			{'i',' ',' ',' ','X'},
			{'X','k',' ',' ','X'},
			{'X','X','X','X','X'}
			
	};
	
	char[][] map2 = {
			{'X','X','X','X','X','X','X'},
			{'X','H',' ',' ',' ',' ','X'},
			{'i',' ',' ',' ',' ',' ','X'},
			{'X','k',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ','o',' ','X'},
			{'X',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X'}
			
	};
	
	@Test
	public void heroMovesIntoOgreAndStuns() {
		Game game = new Game(map1);
		Ogre o = (Ogre)game.getLevel().getEnemies().get(0);
		assertEquals(false,o.getIsStun());
		game.moveHero(Cmd.RIGHT);
		assertEquals(true,o.getIsStun());
	}

	@Test
	public void heroMovesIntoKeyAndChangesRepresentation() {
		Game game = new Game(map1);
		game.moveHero(Cmd.DOWN);
		assertEquals(Symbol.HERO_WITH_CLUB,game.getLevel().getHero().getSymb());
		game.moveHero(Cmd.DOWN);
		assertEquals(Symbol.HERO_WITH_KEY,game.getLevel().getHero().getSymb());
	}
	
	@Test
	public void heroMovesIntoClosedKeepDoorNoKey() {
		Game game = new Game(map1);
		game.moveHero(Cmd.DOWN);
		Coord coord = new Coord(2,0);
		assertEquals(Symbol.DOOR_CLOSED, game.getLevel().getMap().getBotEnt(coord).getSymb());
		game.moveHero(Cmd.LEFT);
		assertEquals(Symbol.DOOR_CLOSED, game.getLevel().getMap().getBotEnt(coord).getSymb());
	}
	

	@Test
	public void heroMovesIntoClosedDoorWithKey() {
		Game game = new Game(map1);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.UP);
		Coord coord = new Coord(2,0);
		assertEquals(Symbol.DOOR_CLOSED, game.getLevel().getMap().getBotEnt(coord).getSymb());
		game.moveHero(Cmd.LEFT);
		assertEquals(Symbol.DOOR_OPEN, game.getLevel().getMap().getBotEnt(coord).getSymb());
	}
	
	@Test
	public void heroMoveIntoOpenDoorAndWins() {
		Game game = new Game(map1);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.UP);
		game.moveHero(Cmd.LEFT);
		assertEquals(false,game.getWonGame());
		game.moveHero(Cmd.LEFT);
		assertEquals(true,game.getWonGame());
	}
	
	@Test(timeout = 1000)
	public void testOgreMovement(){
		Game game = new Game(map2);
		boolean left = false, right = false, up = false, down = false;
		while(!left || !right || !up || !down) {
			game.moveHero(Cmd.UP);
			Ogre o = (Ogre)game.getLevel().getEnemies().get(0);
			if(o.getLastCmd() == Cmd.DOWN)
				down = true;
			else if(o.getLastCmd() == Cmd.UP)
				up = true;
			else if(o.getLastCmd() == Cmd.LEFT)
				left = true;
			else if(o.getLastCmd() == Cmd.RIGHT)
				right = true;
		}
	}
}
