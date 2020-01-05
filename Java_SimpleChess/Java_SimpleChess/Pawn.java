/* 
 * Haischel Dabian - 10336230
 * Giovanni Kastanja- 10467149
 * 
 * The class representing Pawn and the behavior of it
 */
public class Pawn extends Unit {

	private Moves[] moveList = new Moves[4];

	public Pawn(int x, int y, char color) {
		super(x, y, color, 1);
	}

	@Override
	public void show() {
		System.out.println("This is a Pawn");
	}

	// This method gets all possible moves and inserts them into moveList
	@Override
	public void getLegalMoves() {
		int pawnX = this.getX();
		int pawnY = this.getY();
		char pawnColor = getColor();
		Tile[][] chessBoard = SimpleChess.getChessBoard();
		checkInFront(chessBoard,pawnX,pawnY,pawnColor);
		checkDiagonal(chessBoard,pawnX,pawnY,pawnColor);
	}
	
	// This method checks in front of the Pawn 
	// If the square is empty add it to moveList
	// If the pawn is in start position also check 2 squares infront
	private void checkInFront(Tile[][] chessBoard,int pawnX,int pawnY, char pawnColor) {
		int direction;
		Boolean extraStep = false;
		if (pawnColor =='w') {
			direction = 1;
			if (pawnY == 1){
				extraStep = true;
			}
		} else {
			direction = -1;
			if (pawnY == 6) {
				extraStep = true;
			}
		}
		if (extraStep) {
			checkMove(chessBoard, pawnX, pawnY, direction);
			checkMove(chessBoard, pawnX, pawnY, 2*direction);
		} else {
			checkMove(chessBoard, pawnX, pawnY,direction);
		}
	}

	// This method checks if the square is empty and then adds it to moveList
	private void checkMove(Tile[][] chessBoard, int pawnX, int pawnY, int direction) {
		Unit unit = chessBoard[pawnX][pawnY+direction].getPiece();
		if (unit == null) {
			Moves move = new Moves(pawnX, pawnY+direction);
			insertMove(move, moveList);
		}
	}

	// This method checks the two forward diagonal squares of the pawn
	// if it finds a unit on the square it will check the units color
	// if the unit's color doesnt match it will add it to moveList
	private void checkDiagonal(Tile[][] chessBoard,int pawnX,int pawnY, char pawnColor) {
		int[] diagXList = {pawnX - 1, pawnX + 1};
		int direction;
		if (pawnColor == 'w') {
			direction = 1;
		} else {
			direction = -1;
		}
		for (int diagX : diagXList) {
			if (diagX >= 0 && diagX <= 7) {
				int y = pawnY + direction;
				if (y >= 0 && y <= 7) {
					Unit unit = chessBoard[diagX][y].getPiece();
					if (unit != null) {
						char color = unit.getColor();
						if (pawnColor != color){
							System.out.println(Integer.toString(diagX));
							Moves move = new Moves(diagX, y);
							insertMove(move, moveList);
						}
					}
				}
			}
		}
	}

	// return Pawn's moveList
	@Override
	public Moves[] getMoveList(){
		return this.moveList;
	}
}