import java.util.Scanner;

public class Task1
{
	public static void main(String args[]) {
		char map1 [][] = {
				{'x','x','x','x','x','x','x','x','x','x'},
				{'x','h',' ',' ','i',' ','x',' ','g','x'},
				{'x','x','x',' ','x','x','x',' ',' ','x'},
				{'x',' ','i',' ','i',' ','x',' ',' ','x'},
				{'x','x','x',' ','x','x','x',' ',' ','x'},
				{'i',' ',' ',' ',' ',' ',' ',' ',' ','x'},
				{'i',' ',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x','x','x',' ','x','x','x','x',' ','x'},
				{'x',' ','i',' ','i',' ','x','k',' ','x'},
				{'x','x','x','x','x','x','x','x','x','x'}
		};

		char map2 [][] = {
				{'x','x','x','x','x','x','x','x','x'},
				{'i',' ',' ',' ','o',' ',' ','k','x'},
				{'x',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x',' ',' ',' ',' ',' ',' ',' ','x'},
				{'x','h',' ',' ',' ',' ',' ',' ','x'},
				{'x','x','x','x','x','x','x','x','x'}
		};
			
		//initial settings
		char matrix[][] = map1;
		printMatrix(matrix);
		char quit = 'q';
		char current;
		int xHold = 1;
		int yHold = 1;
		int xHnew = 1;
		int yHnew = 1;
		int map = 1;
 
		//guard movement
		char xPos[] = {1,1,2,3,4,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,5,4,3,2};
		char yPos[] = {8,7,7,7,7,7,6,5,4,3,2,1,1,2,3,4,5,6,7,8,8,8,8,8};
		int guardMovIndex = 0;

		do 
		{
			current = validChar();

			switch(current) {
			case 'w' : xHnew = xHold - 1;
			break;

			case 'a' : yHnew = yHold - 1;
			break;

			case 's' : xHnew = xHold + 1;
			break;

			case 'd' : yHnew = yHold + 1;
			break;
			}

			//verificar se o passo é valido. Se for move. Depois de mover verifica se 
			//é um k, um S ou se o Guarda esta perto e trata de cada uma das situaçoes
			//Se nao for nenhuma destas mantem-se
			if (validMove(matrix, xHnew, yHnew) )
			{
				if(matrix[xHnew][yHnew] == 'k') 
				{
					//open gates
					matrix[5][0]= 'S';
					matrix[6][0]= 'S';	
				}
				else if(matrix[xHnew][yHnew] == 'S')
				{
					//goes to the next level
					matrix = map2;
					xHold = 7;
					yHold = 1;
					xHnew = 7;
					yHnew = 1;
					map = 2;
					
				}
				else
				{
					//normal movement
					move(matrix,xHold, yHold, xHnew,yHnew);
					xHold = xHnew;
					yHold = yHnew;
				}
			}
			else {
				xHnew = xHold;
				yHnew = yHold;
			}
			
			//guard movement
			if(map == 1)
			{
				moveGuard(xPos,yPos,guardMovIndex,matrix);
				if(guardMovIndex == xPos.length -1)
				{
					guardMovIndex = 0;
				}
				else
					guardMovIndex++;
			}
			
			printMatrix(matrix);
			
			//movimento do heroi acaba, testar guarda
			if(guardNearBy(xHold,yHnew,matrix))
			{
				current = quit;
				System.out.println("Game Over");
			}
				

		}while(current != quit);
	}

	public static void moveGuard(char xPos[],char yPos[],int i,char matrix[][])
	{
		//delete previous position
		int x = xPos[i];
		int y = yPos[i];
		
		matrix[x][y] = ' ';
		
		//update postion
		
		if(i == xPos.length -1)
		{
			i = 0;
		}
		else
			i++;
		
		//print new position
		matrix[xPos[i]][yPos[i]] = 'g';
		
	}
	
	public static boolean guardNearBy(int xHero, int yHero, char matrix[][])
	{
		if(matrix[xHero+1][yHero] == 'g' ||
				matrix[xHero][yHero+1] == 'g' ||
				matrix[xHero-1][yHero] == 'g' ||
				matrix[xHero][yHero-1] == 'g')
			return true;
		else
			return false;
	}
	
	public static char move(char matrix[][], int xHold, int yHold, int xHnew, int yHnew)
	{
		//regular movement
		char nextChar = matrix[xHnew][yHnew];
		matrix[xHold][yHold] = ' ';
		matrix[xHnew][yHnew] = 'h';
		xHold = xHnew;
		yHold = yHnew;
		//printMatrix(matrix);

		return nextChar;
	}

	public static void printMatrix(char matrix[][] )
	{
		for(int i=0; i< matrix.length;i++)
		{
			for(int j=0; j<matrix[i].length;j++)
			{
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}		
	}

	public static char validChar() {
		System.out.println("Use W,A,S,D to move! \n");
		Scanner scan = new Scanner(System.in);
		char N;
		do {
			N = scan.next().charAt(0);
		} while(N != 'a' && N != 'w'&& N != 's'&& N != 'd'&& N!= 'q');

		return N;
	}

	public static boolean validMove(char matrix [][], int x, int y) {
		if( matrix[x][y] == 'x' || matrix [x][y] == 'i')
			return false;
		return true;

	}

	public static char findGuard(char matrix[][]) {
		for (int i=0; i<10 ; i++) {
			for (int j=0; j<10; j++) {
				if(matrix[i][j] == 'g') 
					return matrix[i][j];
			}
		}
	return 0;
	}
}