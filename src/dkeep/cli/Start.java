package dkeep.cli;

import dkeep.logic.Game;
import dkeep.logic.Map;
import dkeep.logic.OgreType;
import dkeep.logic.Entity;
import dkeep.logic.Coord;
import dkeep.logic.Clear;
import dkeep.logic.Cmd;
import dkeep.logic.Symbol;

import java.util.Scanner;

public class Start
{

	public static Cmd parseCharToCmd(char n) {
		switch(n) {
		case 'a':
		case 'A':
			return Cmd.LEFT;
		case 's':
		case 'S':
			return Cmd.DOWN;
		case 'd':
		case 'D':
			return Cmd.RIGHT;
		case 'w':
		case 'W':
			return Cmd.UP;
		default:
			return Cmd.QUIT;
		}
	}

	public static void sendFinalMessage(Game game)
	{
		if(game.isGameOver())
		{
			System.out.println("Game Over");
		}
		else if(game.getWonGame())
		{
			System.out.println("Congratz, you won game");
		}
		else if(game.getQuit())
		{
			System.out.println("Quit Game");
		}
	}
	
	/*
	 * @brief Returns one of the chars that are used for movement (WASD)
	 *
	 * @return either W, A, S or D
	 */
	public static Cmd validChar(Scanner scan)
	{
		System.out.println("Use W,A,S,D to move! \n");
		char N;
		do
		{
			N = scan.next().charAt(0);
		} while(N != 'a' &&
				N != 'A' &&
				N != 's' &&
				N != 'S' &&
				N != 'd' &&
				N != 'D' &&
				N != 'w' &&
				N != 'W' &&
				N != 'q' &&
				N != 'Q');

		return parseCharToCmd(N);
	}

	public static Entity entToPrint(Map map, int i, int j) {
		Coord coord = new Coord(i,j);
		Entity entTop = map.getTopEnt(coord);
		Entity entBot = map.getBotEnt(coord);

		if(entTop instanceof Clear)
			return entBot;
		else
			return entTop;
	}

	public static char symbolToChar(Symbol s) {
		switch(s) {
		case HERO:
			return 'H';
		case HERO_WITH_KEY:
			return 'K';
		case HERO_WITH_CLUB:
			return 'A';
		case GUARD:
			return 'G';
		case GUARD_SLEEP:
			return 'g';
		case WALL:
			return 'X';
		case CLEAR_SPACE:
			return ' ';
		case OGRE:
			return 'o';
		case OGRE_ON_KEY:
			return '$';
		case OGRE_WEAPON:
			return '*';
		case OGRE_STUNED:
			return '8';
		case CLUB_ON_KEY:
			return '$';
		case LEVER:
			return 'k';
		case KEY:
			return 'k';
		case DOOR_CLOSED:
			return 'i';
		case DOOR_OPEN:
			return 'S';
		default:
			return ' ';
		}
	}

	public static void printMap(Map map) {
		for(int i = 0 ; i < map.getHeight();i++) {
			for(int j = 0; j < map.getWidth(); j++) {
				Entity ent = entToPrint(map,i,j);
				char charToPrint = symbolToChar(ent.getSymb());
				System.out.print(charToPrint + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args)
	{
		//initiate level with level = 1
		int NUMBER_OF_OGRES = 2;
		int MAX_LEVEL = 2;
		Game game = new Game(OgreType.ROOKIE,NUMBER_OF_OGRES,MAX_LEVEL);

		/*
		 * STATUS
		 * 0 - continue playing
		 * 1 - hero died
		 * 2 - level up
		 */

		Cmd cmd = Cmd.START;						//command given by user
		Cmd quit = Cmd.QUIT;						//command quit
		Scanner scan = new Scanner(System.in);	//initiate scanner

		printMap(game.getLevel().getMap());

		//GAME CYCLE
		while(!(game.isGameOver() || game.getWonGame()) && cmd != quit)
		{
			//read user input
			cmd = validChar(scan);

			//update the level
			game.moveHero(cmd);

			//print the current state of the game to console
			if(!game.getWonGame())
				printMap(game.getLevel().getMap());
		}

		//send end-game message to user
		sendFinalMessage(game);

		//close scanner
		scan.close();
	}
}
