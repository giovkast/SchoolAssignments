/* 
 * Haischel Dabian - 10336230
 * Giovanni Kastanja- 10467149
 *
 * this class will get a board state
 * and the boardstate will perform the miniMax algorithm for finding the 
 * best move to play right now, for the current player
 * These are the heuristics for deciding what moves are better then another
 * taking an enemy pawn is +1, an rook is +5, a king + 19

 * losing a pawn is -1, a rook - 5 and a king - 19
 * The score of the boardstate is then the sum of the value of the pieces of a player
 * still on the board
*/

public class ChessGameNode{
	private ChessGameNode[] childrenNodes;
	private BoardState boardState;
	private int boardValue = 0;
	private char player;
	private NewMove originalMove;

	private Moves[] moveList;


	public ChessGameNode(BoardState boardState, char player, NewMove originalMove) {
		this.boardState = boardState;
		this.player = player;
		this.originalMove = originalMove;
	}

	public void setValue() {
		for (Unit unit : this.boardState.getUnitList()) {
			if (unit != null) {
				this.boardValue += unit.getValue();
			}
		}
	}

	public BoardState getBoardState() {
		return this.boardState;
	}

	public int getValue() {
		return this.boardValue;
	}

	public void allocateSpaceForChildren(int numMoves) {
		childrenNodes = new ChessGameNode[numMoves];
	}

	public char getPlayer() {
		return this.player;
	}

	public void setChildrenNode(int index, ChessGameNode newNode) {
		this.childrenNodes[index] = newNode;
	}

	public ChessGameNode[] getChildren() {
		return this.childrenNodes;
	}

	public NewMove getOriginalMove() {
		return this.originalMove;
	}
}