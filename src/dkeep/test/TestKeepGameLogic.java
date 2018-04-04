package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.cli.Start;
import dkeep.logic.Cmd;
import dkeep.logic.Coord;
import dkeep.logic.Game;
import dkeep.logic.Ogre;
import dkeep.logic.Symbol;

public class TestKeepGameLogic {
/*
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

	public int searchOgreX(Symbol[][] map) {
		for(int i = 0 ; i < map.length ; i++) {
			for(int j = 0; j < map[0].length ; j++) {
				if(		map[i][j] == Symbol.OGRE ||
						map[i][j] == Symbol.OGRE_ON_KEY ||
						map[i][j] == Symbol.OGRE_STUNED	) {
					return i;
				}
			}
		}
		return 0;
	}

	public int searchOgreY(Symbol[][] map) {
		for(int i = 0 ; i < map.length ; i++) {
			for(int j = 0; j < map[0].length ; j++) {
				if(		map[i][j] == Symbol.OGRE ||
						map[i][j] == Symbol.OGRE_ON_KEY ||
						map[i][j] == Symbol.OGRE_STUNED	) {
					return j;
				}
			}
		}
		return 0;
	}

	public int searchHeroX(Symbol[][] map) {
		for(int i = 0 ; i < map.length ; i++) {
			for(int j = 0; j < map[0].length ; j++) {
				if(		map[i][j] == Symbol.HERO_WITH_CLUB ||
						map[i][j] == Symbol.HERO_WITH_KEY
						) {
					return i;
				}
			}
		}
		return 0;
	}

	public int searchHeroY(Symbol[][] map) {
		for(int i = 0 ; i < map.length ; i++) {
			for(int j = 0; j < map[0].length ; j++) {
				if(		map[i][j] == Symbol.HERO_WITH_CLUB ||
						map[i][j] == Symbol.HERO_WITH_KEY
						) {
					return j;
				}
			}
		}
		return 0;
	}

	@Test(timeout = 1000)
	public void heroMovesIntoOgreAndStuns() {
		Game game = new Game(map1);
		Symbol[][] map = game.moveHero(Cmd.START);
		int x = searchOgreX(map);
		int y = searchOgreY(map);
		assertEquals(Symbol.OGRE,map[x][y]);
		map = game.moveHero(Cmd.RIGHT);
		x = searchOgreX(map);
		y = searchOgreY(map);
		assertEquals(Symbol.OGRE_STUNED,map[x][y]);
	}

	@Test
	public void heroMovesIntoKeyAndChangesRepresentation() {
		Game game = new Game(map1);
		Symbol[][] map = game.moveHero(Cmd.START);
		map = game.moveHero(Cmd.DOWN);
		int x = searchHeroX(map);
		int y = searchHeroY(map);
		assertEquals(Symbol.HERO_WITH_CLUB,map[x][y]);
		map = game.moveHero(Cmd.DOWN);
		x = searchHeroX(map);
		y = searchHeroY(map);
		assertEquals(Symbol.HERO_WITH_KEY,map[x][y]);
	}

	@Test
	public void heroMovesIntoClosedKeepDoorNoKey() {
		Game game = new Game(map1);
		Symbol[][] map = game.moveHero(Cmd.START);

		map = game.moveHero(Cmd.DOWN);
		assertEquals(Symbol.DOOR_CLOSED, map[2][0]);
		map = game.moveHero(Cmd.LEFT);
		assertEquals(Symbol.DOOR_CLOSED, map[2][0]);
	}


	@Test
	public void heroMovesIntoClosedDoorWithKey() {
		Game game = new Game(map1);
		Symbol[][] map = game.moveHero(Cmd.START);

		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		map = game.moveHero(Cmd.UP);
		assertEquals(Symbol.DOOR_CLOSED, map[2][0]);
		map = game.moveHero(Cmd.LEFT);
		assertEquals(Symbol.DOOR_OPEN, map[2][0]);
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

	public Cmd lastMove(int x, int y,int oldx ,int oldy) {
		if(x-oldx > 0)
			return Cmd.DOWN;
		else if(x-oldx < 0)
			return Cmd.UP;
		else if(y-oldy > 0)
			return Cmd.RIGHT;
		else
			return Cmd.LEFT;
	}

	@Test(timeout = 1000)
	public void testOgreMovement(){
		Game game = new Game(map2);
		Symbol[][] map = game.moveHero(Cmd.START);
		int x = searchOgreX(map);
		int y = searchOgreY(map);
		int oldx = x;
		int oldy = y;		

		boolean left = false, right = false, up = false, down = false;

		while(!left || !right || !up || !down) {
			map = game.moveHero(Cmd.UP);
			x = searchOgreX(map);
			y = searchOgreY(map);
			Cmd cmd = lastMove(x,y,oldx,oldy);
			switch(cmd) {
			case UP:
				up = true;break;
			case DOWN:
				down = true; break;
			case LEFT:
				left = true;break;
			case RIGHT:
			default:
				right = true;break;
			}
		}
	}
	
	
}
