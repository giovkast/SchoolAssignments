/* 
 * Haischel Dabian - 10336230
 * Giovanni Kastanja- 10467149
 *
 *
 * this is the Tile object 
 * the chessBoard consists of Tile objects, it is essentially a
 * matrix of tiles
 *
*/
public class Tile{
	// x, y coordinates
	// reference to a unit
	private int xCoordinate,yCoordinate;
	private Unit piece = null;

	public Tile(Unit piece, int xCoordinate, int yCoordinate){
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.piece = piece;
	}

	// for when a Tile is instantiated without a piece
	public Tile(int xCoordinate, int yCoordinate){
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public Unit getPiece() {
		return this.piece;
	}

	public void setPiece(Unit newPiece){
		this.piece=newPiece;
	}
}