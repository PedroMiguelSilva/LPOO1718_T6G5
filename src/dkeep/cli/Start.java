package dkeep.cli;

import dkeep.logic.Game;
import java.util.Scanner;

public class Start
{
	/*
	 * @brief Returns one of the chars that are used for movement (WASD)
	 *
	 * @return either W, A, S or D
	 */
	public static char validChar(Scanner scan)
	{
		System.out.println("Use W,A,S,D to move! \n");
		char N;
		do
		{
			N = scan.next().charAt(0);
		} while(N != 'a' && N != 'w'&& N != 's'&& N != 'd'&& N!= 'q');

		return N;
	}

	public static void main(String[] args)
	{
		//initiate level with level = 1
		Game game = new Game();

		/*
		 * STATUS
		 * 0 - continue playing
		 * 1 - hero died
		 * 2 - hero won level
		 */
		int status = 0;							//state of the game
		char cmd;								//command given by user
		Scanner scan = new Scanner(System.in);	//initiate scanner

		game.getLevel().getMap().printMap();

		//GAME CYCLE
		while(status != 1)
		{
			//read user input
			cmd = validChar(scan);

			//update the game
			status = game.getLevel().update(cmd);
			
			//print the current state of the game to console
			game.getLevel().getMap().printMap();
		}

		//send end-game message to user
		game.sendFinalMessage();
		
		//close scanner
		scan.close();
	}
}
