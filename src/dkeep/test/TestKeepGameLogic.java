package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.Cmd;
import dkeep.logic.Game;
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
			{'X','H','X',' ',' ',' ','X'},
			{'i','X','X',' ',' ',' ','X'},
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
		game.moveHero(Cmd.START);
		Symbol[][] map = game.getSymbolMap();
		int x = searchOgreX(map);
		int y = searchOgreY(map);
		assertEquals(Symbol.OGRE,map[x][y]);
		game.moveHero(Cmd.RIGHT);
		map = game.getSymbolMap();
		x = searchOgreX(map);
		y = searchOgreY(map);
		assertEquals(Symbol.OGRE_STUNED,map[x][y]);
	}

	@Test
	public void heroMovesIntoKeyAndChangesRepresentation() {
		Game game = new Game(map1);
		game.moveHero(Cmd.START);
		Symbol[][] map = game.getSymbolMap();
		game.moveHero(Cmd.DOWN);
		map = game.getSymbolMap();
		int x = searchHeroX(map);
		int y = searchHeroY(map);
		assertEquals(Symbol.HERO_WITH_CLUB,map[x][y]);
		game.moveHero(Cmd.DOWN);
		map = game.getSymbolMap();
		x = searchHeroX(map);
		y = searchHeroY(map);
		assertEquals(Symbol.HERO_WITH_KEY,map[x][y]);
	}

	@Test
	public void heroMovesIntoClosedKeepDoorNoKey() {
		Game game = new Game(map1);
		game.moveHero(Cmd.START);
		Symbol[][] map = game.getSymbolMap();

		game.moveHero(Cmd.DOWN);
		map = game.getSymbolMap();
		assertEquals(Symbol.DOOR_CLOSED, map[2][0]);
		game.moveHero(Cmd.LEFT);
		map = game.getSymbolMap();
		assertEquals(Symbol.DOOR_CLOSED, map[2][0]);
	}


	@Test
	public void heroMovesIntoClosedDoorWithKey() {
		Game game = new Game(map1);
		game.moveHero(Cmd.START);
		Symbol[][] map = game.getSymbolMap();
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.UP);
		map = game.getSymbolMap();
		assertEquals(Symbol.DOOR_CLOSED, map[2][0]);
		game.moveHero(Cmd.LEFT);
		map = game.getSymbolMap();
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



	@Test
	public void testOgreMovement(){
		Game game = new Game(map2);
		game.moveHero(Cmd.START);
		Symbol[][] map = game.getSymbolMap();
		int x = searchOgreX(map);
		int y = searchOgreY(map);
		int oldx = x;
		int oldy = y;		

		boolean left = false, right = false, up = false, down = false;

		while(!left || !right || !up || !down) {
			game.moveHero(Cmd.UP);
			map = game.getSymbolMap();
			x = searchOgreX(map);
			y = searchOgreY(map);
			Cmd cmd = lastMove(x,y,oldx,oldy);
			switch(cmd) {
			case UP:
				up = true;break;
			case DOWN:
				down = true; break;
			case RIGHT:
				right = true;break;
			case LEFT:
			default:
				left = true;break;
			}
			
			oldx = x;
			oldy = y;
			
		}
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

}
