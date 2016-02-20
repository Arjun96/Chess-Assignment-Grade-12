/**
 * @author Alexander Rathke, Arjun Luthra
 * @date April 13, 2014
 * @file ChessPiece.java
 * @description: Superclass for chess pieces, contains properties which all chess pieces contain; row and column values to identify location
 * 				 and a setLocation method to set these fields from outside the method.
 */
public class ChessPiece {
	public int row;		//Value of the row the piece is in (vertical coordinate).
	public int col;		//Value of the column the piece is in (horizontal coordinate), stored as a number not a letter (a - h), converted by method in "ChessAlexanderArjun" class.
	

	/*Note - Constructor with row and column as parameters is not used because in the "ChessAlexanderArjun" class the objects were unable
	 * 		 to be declared at the same time as their values could be assigned, because the values were assigned inside an if statement. 
	 * 		 The objects could not be declared inside the if statement and used in code outside the if statement, so they were
	 * 		 declared outside the if statement, and their values were assigned inside the if statement.
	 */
	/**
	 * Method to set the values for the row and column location of the chess piece.
	 * @param r, row the chess piece is in (0-7).
	 * @param c, column the chess piece is in (0-7).
	 */
	public void setLocation(int r, int c) {		//Used for ease of understanding in code - since fields are public "mutator" (not really mutator since not setting private fields) method is not necessary, but helps readability.
		this.row = r;
		this.col = c;
	}
	
}
