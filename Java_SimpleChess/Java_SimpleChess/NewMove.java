/* 
 * Haischel Dabian - 10336230
 * Giovanni Kastanja- 10467149
 *
 * This object will acts as a tuple, so that when we generate all the moves
 * a player can make, those moves can be stored with the piece that will
 * perform the move. If we have just the move( which contain the coordinates
 * a piece should travel to) there would be to little information conveyed
 * to resolve ambiguity in a move.
*/

public class NewMove{
	private Unit unit;
	private Moves move;

	public NewMove(Unit unit, Moves move) {
		this.unit = unit;
		this.move = move;
	}

	public Unit getUnit() {
		return unit;
	}

	public Moves getMove() {
		return move;
	}
}