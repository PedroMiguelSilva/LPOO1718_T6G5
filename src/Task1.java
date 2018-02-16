import java.util.Scanner;

public class Task1
{
	public static void main(String args[]) {
		char matrix [][] = {
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

		printMatrix(matrix);
		char quit = 'q';
		char current;
		int xHold = 1;
		int yHold = 1;
		int xHnew = 1;
		int yHnew = 1;


		do {
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
			if (validMove(matrix, xHnew, yHnew) ) {
				move(matrix,xHold, yHold, xHnew,yHnew);
				if(matrix[xHnew][yHnew] == 'k') 
				{
					matrix[5][0]= 'S';
					matrix[6][0]= 'S';	
				}
				else if(matrix[xHnew][yHnew] == 'S')
					System.out.println("Success! You Escaped");
				
				else if() 
			}
			else {
				xHnew = xHold;
				yHnew = yHold;
			}




		}while(current != quit);
	}

	public static char move(char matrix[][], int xHold, int yHold, int xHnew, int yHnew)
	{
		//regular movement
		char nextChar = matrix[xHnew][yHnew];
		matrix[xHold][yHold] = ' ';
		matrix[xHnew][yHnew] = 'h';
		xHold = xHnew;
		yHold = yHnew;
		printMatrix(matrix);

		return nextChar;
	}

	public static void printMatrix(char matrix[][] ) {
		for(int i=0; i<10;i++) 
			for(int j=0; j<10;j++) {
				System.out.print(matrix[i][j] + " ");
				if(j== 9) {
					System.out.println();
				}
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