/**
 * @author Alexander Rathke, Arjun Luthra
 * @date April 13, 2014
 * @file Queen.java
 * @description: Queen object type, representing a Queen chess piece on an 8x8 board. This class contains a method which checks to see if 
 * 				 a Queen object is able to attack a Chess piece 'X' on an 8x8 board. Inherits location properties from the ChessPiece 
 * 				 super class.
 */
public class Queen extends ChessPiece{

	/**
	 * This method checks if a Queen piece is able to attack a Chess Piece 'X' on an 8x8 chess board, taking into consideration that 
	 * pieces may be blocking the Queen piece's attack movement. The Queen piece can move any number of spaces in a straight line in any
	 * direction.
	 * @param board, an 8x8 chess board with other pieces placed on it including a target piece 'X', initialized in the "ChessAlexanderArjun" class.
	 * @param X, the ChessPiece object for which the method checks if the Queen can attack.
	 * @return returns true if the Queen piece is able to attack the Chess Piece 'X', false if it cannot.
	 */
	//This method is a combination of the CanAtt methods for the Bishop and the Rook object types (since the Queen's movements are a combination of both pieces' movement patterns).
	public boolean CanAtt(String[][] board, ChessPiece X) {
		boolean can = false;		
		
		for (int i = 0; i <= 7; i ++) {		//Checks all locations from top to bottom of the column in which the Queen is placed.	
			if (board[i][col].equals("X")) { 	//If a value of a location in the column matches the target ("X") then the Queen is able to attack that piece.
				can = true;
			}
			else if (!board[i][col].equals(".") && ((this.row > i && i > X.row) || (this.row < i && i < X.row)) ) {	/* However, if the value of a location in the Queen piece's column does not contain
			 																										   a dot or a target ("X") then another piece must be present at this position. If 
			 																										   the row value of the Queen is larger than the row value of that piece and if the row value
			 																										   of that piece is greater than the row value of the target the other piece must be in between
			 																										   the Queen and the target, thus the Queen is unable to attack. The same occurs in the Queen is below another piece, 
			 																										   which is below the target - the other piece blocks the Queen from attacking the target.*/
				can = false;	//Sets can to false in case it was originally set to true before finding this condition is true.
				break;		//If this condition is met there is no way the Queen can attack the target, and the method is exited.
			}	

		}
		
		//Checks the Queen's row horizontally to determine if the Queen can attack the target, and if the target is in the same row as the Queen.
		for (int i = 0; i <= 7; i ++) {	
				if (board[row][i].equals("X")) {	//If the target is in the Queen's row than the Queen can attack that target, granted the condition below does not turn out to be true (Queen is blocked).
					can = true;	
				}
				else if (!board[row][i].equals(".") && ((this.col > i && i > X.col) || (this.col < i && i < X.col)) ) {	//If a piece is in between the Queen and the target the Queen's attack path is blocked and it cannot attack the target.
					can = false;
					break;					
				}	
		}
		
		//Checking for a piece up and to the right of the Queen.
				for (int i = 1; row + i <= 7 && col + i <= 7; i ++) {	
					if (board[row + i][col + i].equals("X")) 	/*If the value of a position diagonally up and to the right of the Queen contains X, the 
																  target is positioned at this location.*/
						can = true;								//Thus the Queen can attack since it can move diagonally.	
					else if(!board[row + i][col + i].equals(".") && (row + i) > this.row && (row + i) < X.row ) {  /* However, if a piece is in between the Queen and the target
					 																								  the Queen is blocked and cannot attack. This is known if a position diagonally up
					 																								  and to the right of the Queen piece does not contain an "X" or a "." and has a row 
					 																								  value (vertical position) in between the X and the Queen piece.*/
						can = false;							//Queen's path is blocked so it cannot attack.
						break;									//If this condition exists then there is not point in checking for the other conditions since none of them can exist, breaks and exits the method.
					}
				}
				
				//Checking for a piece up and to the left of the Queen (same as first for statement in method, but different direction).		
				for (int i = 1; row + i <= 7 && col - i >= 0; i ++) {	
					if (board[row + i][col - i].equals("X")) 	//row increases by one each repetition of the for loop (checking 1 vertical row above previous column), and column decreases by one (checking 1 column to the left of previous column).
						can = true;
					else if(!board[row + i][col - i].equals(".") && (row + i) > this.row && (row + i) < X.row ) {
						can = false;
						break;
					}
				}
				
				//Checking for a piece down and to the right of the Queen (same as first for statement in method, but different direction).
				for (int i = 1; row - i >= 0 && col + i <= 7; i ++) {	
					if (board[row - i][col + i].equals("X"))  //Row is now decreasing each repetition (checking below Queen's row), and column increases by one moving to the right one additional column each time a lower row is checked.
						can = true;
					else if(!board[row - i][col + i].equals(".") && (row - i) < this.row && (row - i) > X.row ) {
						can = false;
						break;
					}
				}
				
				//Checking for a piece down and to the left of the Queen (same as first for statement in method, but different direction).
				for (int i = 1; row - i >= 0 && col - i >= 0; i ++) {	
					if (board[row - i][col - i].equals("X")) 
						can = true;
					else if(!board[row - i][col - i].equals(".") && (row - i) < this.row && (row - i) > X.row ) {
						can = false;
						break;	//If this condition is met then the Queen's path is blocked, and the checking can stop as the Queen cannot attack the target.
					}
				}		
				
		return can;
	}
}
