package tictactoe;

import java.util.Scanner;

import java.util.Random;

public class tictactoe {

	// CMD color codes
	public static String RES = "\u001B[0m";
	public static String R = "\u001B[31m";
	public static String G = "\u001B[32m";
	public static String B = "\u001B[36m";
	public static String Y = "\u001B[33m";
	public static String P = "\u001B[35m";
	public static String ERROR = "Geçersiz konum. Lütfen tekrar girin.";

	// Method for CMD color
	public static String c(String color, String text) {
		return color + text + RES;

	}

	// Method for println
	public static void println(String text) {
		System.out.println(text);
	}

	// Method for print
	public static void print(String text) {
		System.out.print(text);
	}
	
	
	// Method for writeBoard
	public static void writeBoard(char[][] board) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 'X') {
					System.out.print(B + board[i][j] + RES);
				} else if (board[i][j] == 'O') {
					System.out.print(P + board[i][j] + RES);
				} else {
					System.out.print(board[i][j]);
				}
			}
			System.out.println();
		}

	}
	
	
	// Method for checkWinner
	public static boolean checkWinner(char[][] board, char player) {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player) { // line control
				return true;
			}
			if (board[0][i] == player && board[1][i] == player && board[2][i] == player) { // column control
				return true;
			}
		}

		if ((board[0][0] == player && board[1][1] == player && board[2][2] == player)
				|| (board[0][2] == player && board[1][1] == player && board[2][0] == player)) { // cross check
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		@SuppressWarnings("resource") // system error removal
		Scanner scanner = new Scanner(System.in); // for inputs to work
		Random random = new Random(); // for random number generation 

		char[][] board = new char[3][3]; // creates a 2-dimensional 3x3 length array

		writeBoard(board); // prints the starting board

		int emptyCells = 9; // we set the empty box value
		boolean gameOver = false; // check to see if the game is over

		while (emptyCells > 0 && !gameOver) { // loop so that the game runs out of empty boxes and the game continues until it wins.

			println(c(Y, "X'i koyacağınız konumu seçin: "));

			print(c(Y, "Satır seçin: "));
			short row = scanner.nextShort();

			System.out.print(c(Y, "Sutün seçin: "));
			short col = scanner.nextShort();
			
			// we determined our own character as x. Since we did this using arrays, we set the selections as rows and columns.

			
			// repeat control of incorrectly entered entries
			if (row < 0 || row >= 3 || col < 0 || col >= 3) {
				println(c(R, ERROR));
				continue;
			}

			// if there is no character in the row and column we selected, put x and reduce the number of empty cells.
			if (board[row][col] == '\u0000') {
				board[row][col] = 'X';
				emptyCells--;
			} else {
				println(c(R, "Bu hücre zaten dolu. Lütfen başka bir hücre seçin."));
				continue;
			}
			

			// checking whether player x wins or not with the function
			if (checkWinner(board, 'X')) {
				writeBoard(board);
				println(c(G, "X kazandı!"));
				break;
			}


			// if the number of empty cells is 0 and there is no winner in the controls, the draw status notification control
			if (emptyCells == 0) {
				writeBoard(board);
				println("Berabere!");
				break;
			}

			// if the number of empty cells is greater than 1, it selects the random row and column in which that character will play. If there is a character in the randomly filled place on the board or if there is a conflict, assign a random row and column value until there is no such thing and place that character and decrease the number of empty cells.
			if (emptyCells > 1) {
				int randomRow = random.nextInt(3);
				int randomCol = random.nextInt(3);
				while (board[randomRow][randomCol] != '\u0000' || (row == randomRow && col == randomCol)) {
					randomRow = random.nextInt(3);
					randomCol = random.nextInt(3);
				}
				board[randomRow][randomCol] = 'O';
				emptyCells--;
			}
			
			// checking whether player o wins or not with the function
			if (checkWinner(board, 'O')) {
				writeBoard(board);
				println(c(G, "O kazandı!"));
				break;
			}

			writeBoard(board); // board's printing function

		}

	}

}
