/**
 * @author Alexander Rathke, Arjun Luthra
 * @date April 11, 2014
 * @file King.java
 * @description: King object type representing the King piece in chess, inherits location properties and method from ChessPiece class. 
 * 				 This class contains the method which checks if a King piece on an 8x8 chess board is able to attack a chess piece 'X' on
 * 				 the same board.
 */
public class King extends ChessPiece{

	/**
	 * This method checks if a King object type (King chess piece) would be able to attack a chess object 'X' on an 8x8 chess board. A King
	 * can attack only one step beside it in all directions.
	 * @param board, 8x8 chess board used in "ChessAlexanderArjun" class, has pieces placed on it already, including target 'X'.
	 * @return true if the King can attack a piece X, false if the King cannot.
	 */
	public boolean CanAtt(String[][] board) {
		boolean can = false;	//Assumes the King cannot attack the piece unless the proper conditions exist below - can is then set to true.
		
		//Checking if the target is to the right of the King piece.
		if (col <= 6 && board[row][col + 1].equals("X")) 	/* col <= 6 or any conditions restricting the value of column or row are always 
		 													   placed before other conditions separated by &&, so if the condition 
		 													   restricting the value of col or row is not met then the second condition is not 
		 													   checked thus not resulting in a crash due to an array index being checked 
		 													   which is out of the index range. */
			can = true; //If the target is to the right of the King piece, the King piece can attack the target.
		
		//Checking if the target is to the left of the King piece.
		else if(col >= 1 && board[row][col - 1].equals("X"))
			can = true;	//King piece can attack.
		//Checking if the target is above the King piece.
		else if(row <= 6 && board[row + 1][col].equals("X"))
			can = true;
		//Checking if the target is below the King piece.
		else if(row >= 1 && board[row - 1][col].equals("X"))
			can = true;
		//Checking if the target is one up, and one to the right of the King piece.
		else if(row <= 6 && col <= 6 && board[row + 1][col + 1].equals("X"))
			can = true;
		//Checking if the target is one up, and one to the left of the King piece.
		else if(row <=6 && col >= 1 && board[row + 1][col - 1].equals("X"))
			can = true;
		//Checking if the target is one below, and one to the right of the King piece.
		else if(row >= 1 && col <= 6 && board[row - 1][col + 1].equals("X"))
			can = true;
		//Checking if the target is one below, and one to the left of the King piece.
		else if(row >= 1 && col >= 1 && board[row - 1][col - 1].equals("X"))
			can = true;

		return can;
	}
}
