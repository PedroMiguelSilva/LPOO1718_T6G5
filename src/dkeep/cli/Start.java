package dkeep.cli;

import dkeep.logic.Game;
import dkeep.logic.GuardType;
import dkeep.logic.Cmd;
import dkeep.logic.Symbol;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Start
{
	private static Map<Symbol,Character> parseToChar;
	static
	{
		parseToChar = new HashMap<Symbol,Character>();
		parseToChar.put(Symbol.HERO, 'H');
		parseToChar.put(Symbol.HERO_WITH_KEY, 'K');
		parseToChar.put(Symbol.HERO_WITH_CLUB, 'A');
		parseToChar.put(Symbol.GUARD, 'G');
		parseToChar.put(Symbol.GUARD_SLEEP, 'g');
		parseToChar.put(Symbol.WALL, 'X');
		parseToChar.put(Symbol.CLUB_ON_KEY, '$');
		parseToChar.put(Symbol.OGRE, 'o');
		parseToChar.put(Symbol.OGRE_ON_KEY, '$');
		parseToChar.put(Symbol.OGRE_STUNED, '8');
		parseToChar.put(Symbol.OGRE_WEAPON, '*');
		parseToChar.put(Symbol.LEVER, 'k');
		parseToChar.put(Symbol.KEY, 'k');
		parseToChar.put(Symbol.DOOR_CLOSED, 'i');
		parseToChar.put(Symbol.DOOR_OPEN, 'S');
		parseToChar.put(Symbol.CLEAR_SPACE, ' ');
	}
	
	
	public static Cmd parseCharToCmd(char n) {
		switch(n) {
		case 'a':
			return Cmd.LEFT;
		case 's':
			return Cmd.DOWN;
		case 'd':
			return Cmd.RIGHT;
		case 'w':
			return Cmd.UP;
		default:
			return Cmd.QUIT;
		}
	}
	
	private static char minimize(char N) {
		if(N == 'A')return 'a';
		if(N == 'W')return 'w';
		if(N == 'S')return 's';
		if(N == 'D')return 'd';
		if(N == 'Q')return 'q';
		return N;
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
			N = minimize(N);
		} while(N != 'a' &&
				N != 's' &&
				N != 'd' &&
				N != 'w' &&
				N != 'q');

		return parseCharToCmd(N);
	}


	public static void printMap(Symbol[][] map) {
		for(int i = 0 ; i < map.length;i++) {
			for(int j = 0; j < map[0].length; j++) {
				System.out.print(parseToChar.get(map[i][j]) + " ");
			}
			System.out.println();
		}
	}

	
	public static void main(String[] args){
		Game game = new Game(GuardType.ROOKIE,1,2);
		
		Cmd cmd = Cmd.START;						
		Scanner scan = new Scanner(System.in);
		boolean firstCall = true;
		
		do {
			if(!firstCall) {
				cmd = validChar(scan);
			}
			firstCall = false;
			game.moveHero(cmd);
			printMap(game.getSymbolMap());
		}while(!game.gameEnded());
		
		System.out.println(game.endingMessage());
		scan.close();
	}
}
