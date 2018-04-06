package dkeep.test;
/*
import static org.junit.Assert.*;

import org.junit.Test;
*/
import dkeep.logic.Cmd;
import dkeep.logic.Game;
import dkeep.logic.GuardType;
import dkeep.logic.Symbol;

public class TestKeepGameLogic {
/*
	

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

	public int searchClubX(Symbol[][] map) {
		for(int i = 0 ; i < map.length ; i++) {
			for(int j = 0; j < map[0].length ; j++) {
				if(		map[i][j] == Symbol.OGRE_WEAPON ||
						map[i][j] == Symbol.CLUB_ON_KEY
						) {
					return i;
				}
			}
		}
		return 0;
	}

	public int searchClubY(Symbol[][] map) {
		for(int i = 0 ; i < map.length ; i++) {
			for(int j = 0; j < map[0].length ; j++) {
				if(		map[i][j] == Symbol.OGRE_WEAPON ||
						map[i][j] == Symbol.CLUB_ON_KEY
						) {
					return j;
				}
			}
		}
		return 0;
	}

	@Test(timeout = 1000)
	public void heroMovesIntoOgreAndStuns() {
		char[][] mapC = {
				{'X','X','X','X','X'},
				{'X','H',' ','o','X'},
				{'i','X','X','X','X'},
				{'X','k',' ',' ','X'},
				{'X','X','X','X','X'}

		};

		Game game = new Game(mapC);
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
		char[][] mapC = {
				{'X','X','X','X','X'},
				{'X','H','X','o','X'},
				{'i',' ','X',' ','X'},
				{'X','k','X',' ','X'},
				{'X','X','X','X','X'}

		};
		
		Game game = new Game(mapC);
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
	public void TestHeroPicksKey() {
		char[][] mapC = {
				{'X','X','X','X','X'},
				{'X','H','X','o','X'},
				{'i',' ','X',' ','X'},
				{'X','k','X','X','X'},
				{'X','X','X','X','X'}

		};
		
		Game game = new Game(mapC);
		Symbol[][] map = game.getSymbolMap();
		game.moveHero(Cmd.START);
		game.moveHero(Cmd.DOWN);
		map = game.getSymbolMap();
		assertEquals(Symbol.KEY,map[3][1]);
		game.moveHero(Cmd.DOWN);
		map = game.getSymbolMap();
		assertEquals(Symbol.HERO_WITH_KEY,map[3][1]);
		game.moveHero(Cmd.UP);
		game.moveHero(Cmd.RIGHT);
		map = game.getSymbolMap();
		assertEquals(Symbol.CLEAR_SPACE,map[3][1]);
	}

	@Test
	public void heroMovesIntoClosedKeepDoorNoKey() {
		char[][] mapC = {
				{'X','X','X','X','X'},
				{'X','H','X','o','X'},
				{'i',' ','X','X','X'},
				{'X','k','X',' ','X'},
				{'X','X','X','X','X'}

		};
		
		Game game = new Game(mapC);
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
		char[][] mapC = {
				{'X','X','X','X','X'},
				{'X','H','X',' ','X'},
				{'i',' ','X','o','X'},
				{'X','k','X',' ','X'},
				{'X','X','X','X','X'}

		};
		
		Game game = new Game(mapC);
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
		char[][] mapC = {
				{'X','X','X','X','X'},
				{'X','H','X',' ','X'},
				{'i',' ','X',' ','X'},
				{'X','k','X','o','X'},
				{'X','X','X','X','X'}

		};
		Game game = new Game(mapC);

		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.DOWN);
		game.moveHero(Cmd.UP);
		game.moveHero(Cmd.LEFT);
		assertEquals(false,game.getWonGame());
		game.moveHero(Cmd.LEFT);
		assertEquals(true,game.getWonGame());
	}

	@Test
	public void TestPlayerQuitGame() {
		char[][] mapC = {
				{'X','X','X','X','X'},
				{'X','H',' ','o','X'},
				{'i',' ','X',' ','X'},
				{'X','k',' ',' ','X'},
				{'X','X','X','X','X'}

		};

		Game game = new Game(mapC);
		game.moveHero(Cmd.QUIT);
		assertTrue(game.getQuit());
		assertTrue(game.gameEnded());
		assertEquals("Quit Game",game.endingMessage());
	}

	@Test
	public void testOgreMovement(){

		char[][] mapC = {
				{'X','X','X','X','X','X','X'},
				{'X','H','X',' ',' ',' ','X'},
				{'i','X','X',' ',' ',' ','X'},
				{'X','k',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ','o',' ','X'},
				{'X',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X'}

		};

		Game game = new Game(mapC);
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

	@Test
	public void TestOgreOverKey() {
		
		char[][] mapC = {
				{'X','X','X','X','X','X','X'},
				{'X','H','X',' ',' ',' ','X'},
				{'i','X','X',' ',' ',' ','X'},
				{'X','k',' ',' ','k',' ','X'},
				{'X',' ',' ','k','o','k','X'},
				{'X',' ',' ',' ','k',' ','X'},
				{'X','X','X','X','X','X','X'}

		};
		
		Game game = new Game(mapC);
		Symbol[][] map = game.getSymbolMap();
		game.moveHero(Cmd.UP);
		map = game.getSymbolMap();
		int x = this.searchOgreX(map);
		int y = this.searchOgreY(map);
		assertEquals(Symbol.OGRE_ON_KEY,map[x][y]);
	}

	@Test(timeout = 1000)
	public void TestOgreBlocked() {
		char[][] mapC = {
				{'X','X','X','X','X','X','X'},
				{'X','H','X',' ',' ',' ','X'},
				{'i','X','X',' ',' ',' ','X'},
				{'X','k',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ','X','X'},
				{'X',' ',' ',' ','X','o','X'},
				{'X','X','X','X','X','X','X'}

		};
		Game game = new Game(mapC);
		Symbol[][] map = game.getSymbolMap();
		game.moveHero(Cmd.UP);
		game.moveHero(Cmd.UP);
		map = game.getSymbolMap();
		assertEquals(Symbol.OGRE,map[5][5]);
		game.moveHero(Cmd.UP);
		assertEquals(Symbol.OGRE,map[5][5]);
	}

	@Test(timeout = 1000)
	public void TestOgreAndClubBlocked() {
		
		char[][] mapC = {
				{'X','X','X','X','X','X','X'},
				{'X','H','X',' ',' ',' ','X'},
				{'i','X','X',' ',' ',' ','X'},
				{'X','k',' ',' ',' ','X','X'},
				{'X',' ',' ',' ','X',' ','X'},
				{'X',' ',' ',' ','X','o','X'},
				{'X','X','X','X','X','X','X'}

		};
		Game game = new Game(mapC);
		Symbol[][] map = game.getSymbolMap();
		game.moveHero(Cmd.UP);
		game.moveHero(Cmd.UP);
		map = game.getSymbolMap();
		game.moveHero(Cmd.UP);
		map = game.getSymbolMap();
		game.moveHero(Cmd.UP);
		map = game.getSymbolMap();
		game.moveHero(Cmd.UP);
		map = game.getSymbolMap();
		game.moveHero(Cmd.UP);
		map = game.getSymbolMap();
		int x = this.searchOgreX(map);
		int y = this.searchOgreY(map);
		assertEquals(Symbol.OGRE,map[x][y]);
	}


	@Test(timeout = 2000)
	public void TestRandomnessClubSwing() {
		Game game = new Game(GuardType.ROOKIE,1,2);
		finishFirstLevel(game);
		Symbol[][] map = game.getSymbolMap();

		//variables
		int xOgre = searchOgreX(map);
		int yOgre = searchOgreY(map);
		int xClub = searchClubX(map);
		int yClub = searchClubY(map);
		boolean left = false, right = false, up = false, down = false;

		while(!left || !right || !up || !down) {
			game.moveHero(Cmd.DOWN);
			map = game.getSymbolMap();
			xOgre = searchOgreX(map);
			yOgre = searchOgreY(map);
			xClub = searchClubX(map);
			yClub = searchClubY(map);

			if(xOgre-xClub == 1) {
				up = true;
			}
			else if(xOgre-xClub == -1) {
				down = true;
			}
			else if(yOgre-yClub == 1) {
				left = true;
			}
			else {
				right = true;
			}
		}

	}

	private void finishFirstLevel(Game game) {
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
		game.moveHero(Cmd.LEFT);
	}

	private Cmd lastMove(int x, int y,int oldx ,int oldy) {
		if(x-oldx > 0)
			return Cmd.DOWN;
		else if(x-oldx < 0)
			return Cmd.UP;
		else if(y-oldy > 0)
			return Cmd.RIGHT;
		else
			return Cmd.LEFT;
	}
	*/

}
