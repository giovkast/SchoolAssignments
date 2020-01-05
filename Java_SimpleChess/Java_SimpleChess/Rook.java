/* 
 * Haischel Dabian - 10336230
 * Giovanni Kastanja- 10467149
 *
 * The class representing the rook and it's behavior on the board
 */
public class Rook extends Unit {

	private Moves[] moveList = new Moves[14];
  
	public Rook(int x, int y, char color) {
		super(x, y, color, 5);
	}

	@Override
	public void show() {
		System.out.println("This is a Rook");
	}

	// This method gets all possible moves and inserts them into moveList
	@Override
	public void getLegalMoves() {
		int rookX = this.getX();
		int rookY = this.getY();
		char rookColor = this.getColor();
		Tile[][] chessBoard = SimpleChess.getChessBoard();
		checkLanes(chessBoard,rookX,rookY,rookColor);
		return;
	}

	// This method will check all directions of the Rook and add
	// all possible moves to moveList
	private void checkLanes(Tile[][] chessBoard, int rookX, int rookY,char rookColor) {
		int[] directionList = {1, -1};
		for (int direction : directionList){
			checkX(chessBoard, rookX, rookY, rookColor, direction);
			checkY(chessBoard, rookX, rookY, rookColor, direction);
		}
	}

	// search all squares in the X axis of the rook
	// if the square is empty it will add it to moveList
	// if it finds a unit on a square it will check the units color
	// if the color doesnt match it will add it to moveList and stop searching
	// if the color matches it will just stop searching
	// If direction=1 it checks RIGHT, if firection=-1 checks LEFT
	private void checkX(Tile[][] chessBoard, int rookX, int rookY,char rookColor, int direction) {
		for (int x = rookX + direction ; x <=7 && x >= 0 ; x = x + direction) {
			Unit unit = chessBoard[x][rookY].getPiece();
			if (unit != null) {
				char color = unit.getColor();
				if (rookColor != color) {
					Moves move = new Moves(x, rookY);
					insertMove(move, moveList);
				}
				break;
			} else {
				Moves move = new Moves(x, rookY);
				insertMove(move, moveList);
			}
		}
	}

	// search all squares in the Y axis of the rook
	// if the square is empty it will add it to moveList
	// if it finds a unit on a square it will check the units color
	// if the color doesnt match it will add it to moveList and stop searching
	// if the color matches it will just stop searching
	// If direction=1 it checks UP, if firection=-1 checks DOWN
	private void checkY(Tile[][] chessBoard, int rookX, int rookY,char rookColor, int direction) {
		for(int y = rookY + direction ; y <= 7 && y >= 0 ; y = y + direction) {
			Unit unit = chessBoard[rookX][y].getPiece();
			if(unit != null) {
				char color = unit.getColor();
				if(rookColor != color) {
					Moves move = new Moves(rookX, y);
					insertMove(move, moveList);
				}
				break;
			} else  {
				Moves move = new Moves(rookX, y);
				insertMove(move, moveList);
			}
		}
	}

	// returns the Rooks moveList
	public Moves[] getMoveList(){
		return this.moveList;
	}
}