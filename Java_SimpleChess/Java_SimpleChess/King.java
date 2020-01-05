/* 
 * Haischel Dabian - 10336230
 * Giovanni Kastanja- 10467149
 *
 * The class representing the king and it's behavior on the board
 */

public class King extends Unit {

	private Moves[] moveList = new Moves[8];

	public King(int x, int y, char color) {
		super(x, y, color, 19);
		this.moveList = new Moves[8];
	}

	@Override
	public void show() {
		System.out.println("This is a King");
	}

	// This method gets all possible moves and inserts them into moveList
	// It checks all 9 of the adjacent squares around the King. 
	// if the square is empty, it will add it to moveList.
	// if it finds a unit on the square, it will check the units color
	// if the color doesn't match the king's color it will add it to moveList
	@Override
	public void getLegalMoves() {
		int kingX = this.getX();
		int kingY = this.getY();
		char kingColor = this.getColor();
		Tile[][] chessBoard = SimpleChess.getChessBoard();
		for (int y = (kingY-1) ; y <= (kingY + 1) ; y++) {
			for (int x = (kingX - 1) ; x <= (kingX + 1) ; x++) {
				if (!(kingX == x && kingY == y) && x <= 7 && x >= 0 && y <= 7 && y >= 0 ) {
					Unit unit = chessBoard[x][y].getPiece();
					if (unit == null || unit.getColor() != kingColor){
						Moves move = new Moves(x, y);
						insertMove(move, moveList);
					}
				}
			}
		}
	}
	
	@Override
	public Moves[] getMoveList(){
		return this.moveList;
	}
}