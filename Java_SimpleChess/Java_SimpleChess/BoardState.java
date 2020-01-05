	/* 
	 * This class acts as a tuple so that we could return both the 
	 * Tile[][] and Unit[] form a function
	 */
public class BoardState {
	private final Tile[][] chessBoard;
	private final Unit[] unitList;

	public BoardState(Tile[][] chessBoard, Unit[] unitList) {
		this.chessBoard = chessBoard;
		this.unitList = unitList;
	}

	public Unit[] getUnitList() {
		return this.unitList;
	}

	public Tile[][] getBoard() {
		return this.chessBoard;
	}
}