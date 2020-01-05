/* 
 * Haischel Dabian - 10336230
 * Giovanni Kastanja- 10467149
 *
 * this class represents the coordinates for the moves a unit can make.
 * So all the moves a unit can make will be stored in an array of Moves.
 */

public class Moves {
	private int x;
	private int y;

	public Moves(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// get the x coordinate of the move
	public int getX() {
		return this.x;
	}

	// get the y coordinate of a move
	public int getY() {
		return this.y;
	}
}