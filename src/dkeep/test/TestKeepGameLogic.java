package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.Cmd;
import dkeep.logic.Coord;
import dkeep.logic.Game;
import dkeep.logic.Ogre;
import dkeep.logic.Symbol;

public class TestKeepGameLogic {

	char[][] map = {
			{'X','X','X','X','X'},
			{'X','H',' ','o','X'},
			{'i',' ',' ',' ','X'},
			{'X','k',' ',' ','X'},
			{'X','X','X','X','X'}
			
	};
	
	@Test
	public void heroMovesIntoOgreAndStuns() {
		Game game = new Game(map);
		Ogre o = (Ogre)game.getLevel().getEnemies().get(0);
		assertEquals(false,o.getIsStun());
		game.moveHero(Cmd.RIGHT);
		assertEquals(true,o.getIsStun());
	}

	@Test
	public void heroMovesIntoKeyAndChangesRepresentation() {
		Game game = new Game(map);
		game.moveHero(Cmd.DOWN);
		assertEquals(Symbol.HERO_WITH_CLUB,game.getLevel().getHero().getSymb());
		game.moveHero(Cmd.DOWN);
		assertEquals(Symbol.HERO_WITH_KEY,game.getLevel().getHero().getSymb());
	}
	
	@Test
	public void heroMovesIntoClosedKeepDoorNoKey() {
		Game game = new Game(map);
		game.moveHero(Cmd.DOWN);
		Coord coord = new Coord(2,0);
		assertEquals(Symbol.DOOR_CLOSED, game.getLevel().getMap().getBotEnt(coord).getSymb());
		game.moveHero(Cmd.LEFT);
		assertEquals(Symbol.DOOR_CLOSED, game.getLevel().getMap().getBotEnt(coord).getSymb());
	}
	

}
