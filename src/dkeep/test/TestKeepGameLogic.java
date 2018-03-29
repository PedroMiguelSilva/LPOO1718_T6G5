package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.Cmd;
import dkeep.logic.Game;
import dkeep.logic.Ogre;

public class TestKeepGameLogic {

	char[][] map = {
			{'X','X','X','X','X'},
			{'X','H',' ','o','X'},
			{'X',' ',' ',' ','X'},
			{'i','k',' ',' ','X'},
			{'X','X','X','X','X'}
			
	};
	
	@Test
	public void heroMovesIntoOgreAndStuns() {
		Game game = new Game(map);
		Ogre o = (Ogre)game.getLevel().getEnemies().get(0);
		assertEquals(false,o.getIsStun());
		game.moveHero(Cmd.LEFT);
		assertEquals(true,o.getIsStun());
	}

}
