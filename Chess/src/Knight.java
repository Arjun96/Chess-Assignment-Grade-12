/**
 * @author Alexander Rathke, Arjun Luthra
 * @date April 13, 2014
 * @file Knight.java
 * @description: Knight object type representing a Knight piece on a chess board. This class contains a method which checks if a Knight
 *               piece can attack another Chess Piece 'X' on an 8x8 chess board. Inherits location properties from the Chess Piece 
 *               super class.
 */
public class Knight extends ChessPiece{

	/**
	 * This method checks if a Knight piece is able to attack a Chess Piece 'X' on an 8x8 chess board. Knights are able to move two spaces
	 * vertically followed by one space horizontally, or two spaces horizontally followed by one space vertically (an L-shape).
	 * @param board, an 8x8 chess board with other pieces placed on it including a target piece 'X', initialized in the "ChessAlexanderArjun" class.
	 * @return returns true if the Knight piece is able to attack the Chess Piece 'X', false if it cannot.
	 */
	public boolean CanAtt(String[][] board) {
		boolean can = false;	//Assumes the Knight is unable to attack the piece 'X', unless the appropriate conditions are found below.
		
		//Checks if the target is two above and one to the right of the Knight piece.
		if(row <= 5 && col <= 6 && board[row + 2][col + 1].equals("X")) /* Conditions placed on row and col values assure that no index 
		 																   outside of the board array's index range are checked (which 
		 																   would cause an error to occur). */
			can = true;	//Knight piece can attack since target is in a position the Knight piece can attack.
		
		//Checks if the target is two above and one to the left of the Knight piece.
		else if(row <= 5 && col >= 1 && board[row + 2][col - 1].equals("X")) 
			can = true;
		
		//Checks if the target is two below, and one to the right of the Knight piece.
		else if( row >= 2 && col <= 6 && board[row - 2][col + 1].equals("X"))
			can = true;
		
		//Checks if the target is two below, and one to the left of the Knight piece.
		else if(row >=2 && col >= 1 && board[row - 2][col - 1].equals("X"))
			can = true;
		
		//Checks if the target is one above, and two to the right of the Knight piece.
		else if(row <= 6 && col <= 5 && board[row + 1][col + 2].equals("X"))
			can = true;
		
		//Checks if the target is one below, and two to the right of the Knight piece.
		else if(row >= 1 && col <= 5 && board[row - 1][col + 2].equals("X"))
			can = true;
		
		//Checks if the target is one above, and two to the left of the Knight piece.
		else if(row <= 6 && col >= 2 && board[row + 1][col - 2].equals("X"))
			can = true;
		
		//Checks if the target is one below, and two to the left of the Knight piece.
		else if(row >= 1 && col >=2 && board[row - 1][col - 2].equals("X"))
			can = true;
				
		return can;
	}
}
