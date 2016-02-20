/**
 * @author Alexander Rathke, Arjun Luthra
 * @date April 13, 2014
 * @file ChessAlexanderArjun.java
 * @description: Chess Board In Class Assignment created by Alexander Rathke and Arjun Luthra. The program simulates an 8x8 chess board
 * 				 with King, Queen, Rook, Bishop, and Knight pieces always placed on it. Additionally a target piece is marked as an 'X',
 * 				 the program then determines which pieces are able to attack a piece at this position. The user is not allowed to target
 * 				 their own pieces with an X. Additionally the program takes into account that some pieces may have their attack path 
 * 				 blocked, and thus are not able to attack. 
 * 				 Note: This class contains additional methods at the bottom of the code, and uses code from the following Classes:
 * 				 Bishop.java, King.java, Queen.java, Rook.java, Knight.java (all of which inherit code from ChessPiece.java).
 */


import java.io.*;

public class ChessAlexanderArjun {

	/**
	 * Main method, in which the user is asked for piece locations including a target location, and the board is printed out along with
	 * any pieces which are able to attack the target piece 'X'.
	 * @param args command line argument.
	 * @throws Exception thros when there is an error using BufferedReader.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		String piece, horizontal, s;	/*Piece represents which piece is being placed (only takes first letter of piece, exception: N for Knight).
										  Horizontal stores the user input value for the column of the chess piece (a-h), which is later converted
										  to a value for the column which is stored in Hint. 
										  S is used to store the user's input for a piece's vertical position, before parsing it into a integer 
										  value for the piece's row (stored in vertical).*/
		int vertical, hInt = 0;			/*Vertical stores a piece's row location, hInt stores the piece's horizontal location. 
		 								  Note: vertical, hInt, piece, horizontal, s do not store values permanently, as they are often
		 								  overwritten multiple times inside loops. The permanent values are stored in the variables below.*/
		boolean[] placed = new boolean[5]; //Used to identify which pieces have been placed already to prevent duplicate pieces from being placed.
		//Legend for placed array (true = placed, false = not placed yet): 
		/* 0 (index) = King
		 * 1 = Queen
		 * 2 = Rook
		 * 3 = Bishop
		 * 4 = Knight
		 */
		String[][] board = new String[8][8];	//Simulates an 8x8 chess board, with what is in each position represented by a string ("." is an empty position).
		boolean valid, placetaken, friendly;	/*The variables are used throughout the program as safeguards to check for specific issues. 
		 										  valid: checks to make sure user input is within acceptable ranges.
		 										  placetaken: used to check if a place is already taken by another piece when the user is placing a piece
		 										  friendly: used to check if the user is trying to attack one of their own pieces (already placed pieces being marked as a target)*/
		King K = new King();					//Represents the King piece.
		Queen Q = new Queen();					//Represents the Queen piece.
		Knight N = new Knight();				//Represents the Knight piece.
		Rook R = new Rook();					//Represents the Rook piece.
		Bishop B = new Bishop();				//Represents the Bishop piece.
		
		
	
		//Setting all values of the board to ".", representing an empty location.
		for (int row = 0; row < board.length; row ++) {
			for (int col = 0; col < board[row].length; col ++) {
				board[row][col] = ".";
			}
		}
		
		//Assigning each of the 5 pieces a location.
		for (int i = 0; i < 5; i++) {
			do {
			
				placetaken = false;	//Assumes the place the user is placing their piece is not taken, if it is placetaken is set to true later in code, and the code is looped until it remains false.
				//Assigning the piece letter (which piece is being placed).
				do {
					valid = true;	//Assumes the user input is valid, until proven false.
					System.out.println("Unplaced Pieces: " + piecesLeft(placed));	//Displays pieces not yet placed (piecesLeft() method used for easier readability).
					System.out.print("Enter the first letter of the piece (Except Knight - Enter \"N\"): ");
					piece = buffer.readLine().toUpperCase(); //Converted to upper case to avoid writing multiple if statements for two cases (lower and upper).
					
					//If the piece entered does not equal any of the pieces below the user is showed an error asking them to enter a proper piece, and valid is set to false so the do loop is repeated.
					if (!piece.equals("K") && !piece.equals("Q") && !piece.equals("R") && !piece.equals("B") && !piece.equals("N") ) {
						System.out.println("Please enter a valid piece name (K, Q, R, B, or N).");
						valid = false;
					}
					
					//Correcting for the user selected piece already being placed: if the user selected a piece which is already placed, valid is set to false and the loop repeats.
					if (piece.equals("K") && placed[0] == true || piece.equals("Q") && placed[1] == true || piece.equals("R") && placed[2] == true || piece.equals("B") && placed[3] == true || piece.equals("N") && placed[4] == true) {
						valid = false;
						System.out.println("The " + piece + " piece has already been placed, please place the pieces which have not yet been placed (listed below): ");
					}

					
				} while (valid == false);
	
				
				//Asking user for the horizontal coordinate (column) of the piece.
				do {
					valid = true;
					System.out.print("Enter the column value (a-h): ");	//Column's on a chess board are from a-h.
					horizontal = buffer.readLine().toLowerCase();	//Converted to lower case to avoid mixing cases requiring multiple if statements.
					//If the user's input for horizontal does not match any of the existing column coordinates the loop is repeated and they are presented with an error message.
					if (!horizontal.equals("a") && !horizontal.equals("b") && !horizontal.equals("c") && !horizontal.equals("d") && !horizontal.equals("e") && !horizontal.equals("f") && !horizontal.equals("g") && !horizontal.equals("h")) {
						System.out.println("Please enter either a, b, c, d, e, f, g, or h.");
						valid = false;
						System.out.println();
					}
				} while (valid == false);
				
				
				//Asking the user for the vertical coordinate (row) of the piece being placed.
				do {
					valid = true;
					System.out.print("Enter the row value (1-8): ");
					s = buffer.readLine();
					vertical = Integer.parseInt(s) - 1; //Taking into account that array index starts at 0 so a row of 1 is stored as a row of 0 (row of 8 is stored as 7).
					if ( vertical > 7 || vertical < 0) {	//Checks to assure row is within appropriate range.
						System.out.println("Please enter a row value between 1 and 8 inclusive.");
						valid = false;
						System.out.println();
					}
				} while (valid == false);
 
				//Converts the string value input by the user for the horizontal coordinate of the piece into an integer using method below.
				hInt = horizontalConvertToNum(horizontal);
				
				//If the location in which the user wants the piece to go is empty then it is placed, other wise an error returns as the location must contain another piece already.
				if (board[vertical][hInt].equals("."))
					board[vertical][hInt] = piece;
				else {
					System.out.println("Sorry, there is a piece in this position already. ");
					placetaken = true;		//The position specified by user is already taken, placetaken is set to true and the entire process of placing a piece repeats.
				}
			
				System.out.println();
			} while (placetaken == true);
			
			
			
			//Marking that a specific piece has been placed to prevent the user from placing it twice.
			if (piece.equals("K")) {
				placed[0] = true;
				K.setLocation(vertical, hInt);	//Also permanently stores the location of the piece which has been placed.
			}
				
			else if (piece.equals("Q")) {
				placed[1] = true;
				Q.setLocation(vertical, hInt);
			}
				
			else if (piece.equals("R")) {
				placed[2] = true;
				R.setLocation(vertical, hInt);
			}
				
			else if (piece.equals("B")) {
				placed[3] = true;
				B.setLocation(vertical, hInt);
			}
				
			else if (piece.equals("N")) {
				placed[4] = true;
				N.setLocation(vertical, hInt);
			}
				
	
		} //for loop for placing pieces ends here.
		
		
		
		
		//Marking spot to attack (*similar to inputting location of a piece which is being placed).
		do {
			//User inputs the horizontal coordinate (column) of the spot to attack. 
			do {
				valid = true;
				friendly = false;
				System.out.print("Enter the column value (a - h) of the target (X) to attack: ");
				s = buffer.readLine();
				horizontal = s.toLowerCase();
				if (!horizontal.equals("a") && !horizontal.equals("b") && !horizontal.equals("c") && !horizontal.equals("d") && !horizontal.equals("e") && !horizontal.equals("f") && !horizontal.equals("g") && !horizontal.equals("h")) {
					System.out.println("Please enter either a, b, c, d, e, f, g, or h.");
					valid = false;
				}
			} while (valid == false);
			
			hInt = horizontalConvertToNum(s);
			
			//User inputs the vertical coordinate (row) of the spot to attack.
			do {
				valid = true;
				System.out.print("Enter the row value (1 - 8) of the target (X) to attack: ");
				s = buffer.readLine();
				vertical = Integer.parseInt(s) - 1;
				if ( vertical > 7 || vertical < 0) {
					System.out.println("Please enter a row value between 1 and 8 inclusive.");
					valid = false;
				}
			} while (valid == false);

			//Checks if the location being marked for attack is empty (".") or contains a friendly piece.
			if (board[vertical][hInt].equals("."))
				board[vertical][hInt] = "X";
			else {	//If a friendly piece is present in the location marked for attack the loop for selecting an attack position repeats as we have decided attacking your own piece should not be allowed.
				System.out.println("The target you have marked is occupied by one of your pieces, please mark a different target.");
				friendly = true;
			}
			
		} while (friendly == true);
		
		//Stores the location of the target, which is created as a generic ChessPiece object type.
		ChessPiece X = new ChessPiece();	/*While using a constructor would reduce setting the location of the target to one line, no other 
											  ChessPiece objects (or objects of a type which inherit from the ChessPiece object) use a constructor, 
											  thus the added code would be more than the code used to set the location in this manner.*/
		X.setLocation(vertical, hInt);

		
		
		
		
		
		//Printing out the board starting from the top row, to the bottom row.
		for (int row = 7; row >= 0; row --) {
			System.out.print(row + 1);	/*Prints out the row value on beside the board grid, since the value is stored in memory as one 
										  less than the actual row value (index's go from 0-7) a 1 is added to the row value to get the 
										  appropriate labeling (1-8). */
			for (int col = 0; col < board[row].length; col ++) {
				System.out.print(" " + board[row][col] + " ");
			}
			System.out.println();
		}
		//After printing out the board the labeling for the columns is printed out.
		System.out.print(" "); //extra space to align labeling with columns.
		for (int col = 0; col <= 7; col ++) {
			System.out.print(" " + horizontalConvertToLetter(col) + " ");	//prints out the letter value of each column rather than the number.
		}
		System.out.println();
		
		
		
		//Checks which pieces can attack, in order from the bottom row to the top row, and from left to right across rows.
		System.out.println("Pieces which can attack a piece at position X:");
		/* Checks along each row from left to right to see if a piece is present, then for each piece type present it checks if that
		 * piece can attack, if it can then it prints out the piece and its location. */
		for (int row = 0; row <= 7; row ++) {
			for (int col = 0; col < board[row].length; col ++) {
				if (board[row][col].equals("K") && K.CanAtt(board) == true)
					System.out.println("K" + horizontalConvertToLetter(K.col) + (K.row + 1) );
				
				else if (board[row][col].equals("Q") && Q.CanAtt(board, X) == true)
					System.out.println("Q" + horizontalConvertToLetter(Q.col) + (Q.row + 1) );
				
				else if (board[row][col].equals("B") && B.CanAtt(board, X) == true)
					System.out.println("B" + horizontalConvertToLetter(B.col) + (B.row + 1) );
				
				else if (board[row][col].equals("R") && R.CanAtt(board, X) == true)
					System.out.println("R" + horizontalConvertToLetter(R.col) + (R.row + 1) );
				
				else if (board[row][col].equals("N") && N.CanAtt(board) == true)
					System.out.println("N" + horizontalConvertToLetter(N.col) + (N.row + 1) );
			}
		}
		
		//If no piece can attack, then the following message is printed.
		if(K.CanAtt(board) == false && Q.CanAtt(board, X) == false && B.CanAtt(board, X) == false && R.CanAtt(board, X) == false && N.CanAtt(board) == false)
			System.out.println("No pieces can attack a piece at X.");	
	}
		
	
	/**
	 * This method converts the letter values of the columns to integer values so they can be used for calculations.
	 * @param s, the letter (a-h) input by the user as the column value.
	 * @return The integer value of the column.
	 */
	private static int horizontalConvertToNum(String s) {
		int hInt;	
		
		if (s.equals("a"))	//Matches each letter with the corresponding int value stores for that column.
			hInt = 0;
		else if (s.equals("b"))
			hInt = 1;
		else if (s.equals("c"))
			hInt = 2;	
		else if (s.equals("d"))
			hInt = 3;
		else if (s.equals("e"))
			hInt = 4;
		else if (s.equals("f"))
			hInt = 5;
		else if (s.equals("g"))
			hInt = 6;
		else		/*If none of the above conditions are true the value of hInt must be "h", so it is set to 7. Prevents hInt from 
					  having to be initialized on the first line of method. */
			hInt = 7;
		return hInt;
	}
	
	/**
	 * This method converts a horizontal number value to the corresponding letter value for the column, used when printing out the locations
	 * of pieces which can attack 'X'.
	 * @param hInt, the integer value of a column.
	 * @return The letter value of the column.
	 */
	private static String horizontalConvertToLetter(int hInt) {
		String s;
		
		if (hInt == 0)
			s = "a";
		else if (hInt == 1)
			s = "b";
		else if (hInt == 2)
			s = "c";	
		else if (hInt == 3)
			s = "d";
		else if (hInt == 4)
			s = "e";
		else if (hInt == 5)
			s = "f";
		else if (hInt == 6)
			s = "g";
		else
			s = "h";
		return s;
	}
	
	/**
	 * This method returns a string containing which pieces have not yet been placed (used each time the user is asked to place a new
	 * piece so they know which pieces are remaining).
	 * @param set, the array containing which pieces have already been placed.
	 * @return The string containing which pieces have not been placed.
	 */
	private static String piecesLeft(boolean[] set) {
		String output = "";
		if (set[0] == false) {	//If set[0] == false then the King has not yet been placed, so King is concatenated to the output string.
			output += " King ";
		}
		if (set[1] == false) {
			output += " Queen ";
		}
		if (set[2] == false) {
			output += " Rook ";
		}
		if (set[3] == false) {
			output += " Bishop ";
		}
		if (set[4] == false) {
			output += " Knight ";
		}
		
		
		
		return output;
	}
	
	

}
