/**
 * @author Alexander Rathke, Arjun Luthra
 * @date April 11, 2014
 * @file Rook.java
 * @description: Rook object type, representing a Rook chess piece on an 8x8 chess board. This class inherits the location properties of a
 * 				 chess piece from the ChessPiece super class, and contains a method which checks if a Rook chess piece is able to attack a
 * 				 chess piece 'X' on an 8x8 chess board.
 */
public class Rook extends ChessPiece{
	
	/**
	 * This method checks if a Rook chess piece is able to attack a chess piece 'X' on an 8x8 chess board, taking into consideration that
	 * the Rook's attack movement may be blocked by another piece. Rooks may move any number of spaces horizontally or vertically from their start position.
	 * @param board, an 8x8 chess board with other pieces placed on it including a target piece 'X', initialized in the "ChessAlexanderArjun" class.
	 * @param X, the ChessPiece object for which the method checks if the Rook can attack.
	 * @return returns true if the Rook piece is able to attack the Chess Piece 'X', false if it cannot.
	 */
	public boolean CanAtt(String[][] board, ChessPiece X) {
		boolean can = false;	//Assumes the rook piece cannot attack the piece 'X' unless the proper conditions exist (checked for below).
		
		//Checks the rook's column vertically to determine if the rook is able to attack the target, and if the target is in the same column as the rook.
		for (int i = 0; i <= 7; i ++) {		//Checks all locations from top to bottom of the column in which the rook is placed.	
			if (board[i][col].equals("X")) { 	//If a value of a location in the column matches the target ("X") then the rook is able to attack that piece.
				can = true;
			}
			else if (!board[i][col].equals(".") && ((this.row > i && i > X.row) || (this.row < i && i < X.row)) ) {	/* However, if the value of a location in the rook piece's column does not contain
			 																										   a dot or a target ("X") then another piece must be present at this position. If 
			 																										   the row value of the rook is larger than the row value of that piece and if the row value
			 																										   of that piece is greater than the row value of the target the other piece must be in between
			 																										   the rook and the target, thus the rook is unable to attack. The same occurs in the rook is below another piece, 
			 																										   which is below the target - the other piece blocks the rook from attacking the target.*/
				can = false;	//Sets can to false in case it was originally set to true before finding this condition is true.
				break;		//If this condition is met there is no way the rook can attack the target, and the method is exited.
			}	

		}
		
		//Checks the rook's row horizontally to determine if the rook can attack the target, and if the target is in the same row as the rook.
		for (int i = 0; i <= 7; i ++) {	
				if (board[row][i].equals("X")) {	//If the target is in the rook's row than the rook can attack that target, granted the condition below does not turn out to be true (rook is blocked).
					can = true;	
				}
				else if (!board[row][i].equals(".") && ((this.col > i && i > X.col) || (this.col < i && i < X.col)) ) {	//If a piece is in between the rook and the target the rook's attack path is blocked and it cannot attack the target.
					can = false;
					break;					
				}	
		}
	
		return can;
	}
}
