/**
 * @author Alexander Rathke, Arjun Luthra
 * @date April 13, 2014
 * @file Bishop.java
 * @description: Bishop object type representing bishop piece in chess, inherits location fields and setLocation method from ChessPiece 
 * 				 class. This class contains the instance method which is used to determine if a Bishop piece is able to attack a 
 * 				 ChessPiece at location X, and considers pieces blocking the bishops attack path. 
 */

public class Bishop extends ChessPiece{

	/**
	 * This method checks if bishop object is able to attack a ChessPiece 'X' on an 8x8 chess board 'board'. A bishop can attack diagonally
	 * along a chess board (any number of spaces).
	 * @param board, the 8x8 chess board used in the class "ChessAlexanderArjun".
	 * @param X, the ChessPiece object for which the method checks if the bishop can attack.
	 * @return returns true if the bishop piece can attack the chess piece X, false if the bishop cannot attack.
	 */
	public boolean CanAtt(String[][] board, ChessPiece X) {
		boolean can = false;		//Assuming the bishop cannot attack the piece, until it is set to true if the appropriate conditions exist.
		
		//Checking for a piece up and to the right of the bishop.
		for (int i = 1; row + i <= 7 && col + i <= 7; i ++) {	
			if (board[row + i][col + i].equals("X")) 	/*If the value of a position diagonally up and to the right of the bishop contains X, the 
														  target is positioned at this location.*/
				can = true;								//Thus the bishop can attack since it can move diagonally.	
			else if(!board[row + i][col + i].equals(".") && (row + i) > this.row && (row + i) < X.row ) {  /* However, if a piece is in between the bishop and the target
			 																								  the bishop is blocked and cannot attack. This is known if a position diagonally up
			 																								  and to the right of the bishop piece does not contain an "X" or a "." and has a row 
			 																								  value (vertical position) in between the X and the bishop piece.*/
				can = false;							//Bishop's path is blocked so it cannot attack.
				break;									//If this condition exists then there is not point in checking for the other conditions since none of them can exist, breaks and exits the method.
			}
		}
		
		//Checking for a piece up and to the left of the bishop (same as first for statement in method, but different direction).		
		for (int i = 1; row + i <= 7 && col - i >= 0; i ++) {	
			if (board[row + i][col - i].equals("X")) 	//row increases by one each repetition of the for loop (checking 1 vertical row above previous column), and column decreases by one (checking 1 column to the left of previous column).
				can = true;
			else if(!board[row + i][col - i].equals(".") && (row + i) > this.row && (row + i) < X.row ) {
				can = false;
				break;
			}
		}
		
		//Checking for a piece down and to the right of the bishop (same as first for statement in method, but different direction).
		for (int i = 1; row - i >= 0 && col + i <= 7; i ++) {	
			if (board[row - i][col + i].equals("X"))  //Row is now decreasing each repetition (checking below bishop's row), and column increases by one moving to the right one additional column each time a lower row is checked.
				can = true;
			else if(!board[row - i][col + i].equals(".") && (row - i) < this.row && (row - i) > X.row ) {
				can = false;
				break;
			}
		}
		
		//Checking for a piece down and to the left of the bishop (same as first for statement in method, but different direction).
		for (int i = 1; row - i >= 0 && col - i >= 0; i ++) {	
			if (board[row - i][col - i].equals("X")) 
				can = true;
			else if(!board[row - i][col - i].equals(".") && (row - i) < this.row && (row - i) > X.row ) {
				can = false;
				break;	//If this condition is met then the bishop's path is blocked, and the checking can stop as the bishop cannot attack the target.
			}
		}		
		
		return can;		//Returns true if bishop can attack X and is not obstructed, returns false if bishop cannot attack X or is obstructed by another piece.
	}
}
