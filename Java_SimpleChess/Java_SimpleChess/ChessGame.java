/* 
 * Haischel Dabian - 10336230
 * Giovanni Kastanja- 10467149
 *
 * The chessGame is the overarching class for the minimax tree.
 * We get the chessboard and unitlist, and 
 * Doesn't store  correctly, 
 * 
 */

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class ChessGame {
	
	private ChessGameNode rootNode;
	private Tile[][] chessBoard;
	private Unit[] unitList;
	private NewMove[] moveList; 

	public ChessGame(Tile[][] chessBoard, Unit[] unitList) {
		this.chessBoard = chessBoard;
		this.unitList = unitList;
		this.moveList = new NewMove[30];

	}

	/*
	 * This function will find the best move for the player
	 * by analyzing all the boardStates of all the possible moves
	 * and returns the one with highest score for the player
	 */
	public NewMove getBestMove(char player) {
		initRootNode(player);
		createChildrenNodes(this.rootNode);
		NewMove bestMove = findBestMove(this.rootNode, player);
		bestMove.getUnit().show();
		System.out.println(bestMove.getUnit().getColor());
		System.out.println(bestMove.getUnit().getX());
		return bestMove;
	}

	// initialises the rootnode
	private void initRootNode(char player) {
		BoardState currentState = new BoardState(this.chessBoard, this.unitList);
		this.rootNode = new ChessGameNode(currentState, player, null);
	}

	// gets the value of the unitlist by summing over all the values of the units
	private int getUnitListValue() {
		int value = 0;
		for (Unit unit : this.unitList) {
			value += unit.getValue();
		}
		return value;
	}

	/* initialises all the children of rootnode
	 * the number of children is equal to the number of moves there can be made
	 * from the boardstate in the rootnode
	 */ 
	private void createChildrenNodes(ChessGameNode rootNode) {
		BoardState nextState;
		ChessGameNode newNode;
		char currentPlayer = rootNode.getPlayer();

		generateLegalMovesPlayer(currentPlayer);
		rootNode.allocateSpaceForChildren(this.moveList.length);

		for (int i = 0; i < this.moveList.length; i++) {
			if (this.moveList[i] != null) {
				nextState = makeMove(rootNode, this.moveList[i]);
				if (currentPlayer == 'w') {
					newNode = new ChessGameNode(nextState, 'b', this.moveList[i]);
					newNode.setValue();
				} else {
					newNode = new ChessGameNode(nextState, 'w', this.moveList[i]);	
					newNode.setValue();
				}
				rootNode.setChildrenNode(i, newNode);
			}
		}
	}

	// Returns the move that will result in the best boardState for the player
	private NewMove findBestMove(ChessGameNode node, char player ) {
		ChessGameNode[] childrenNodes = node.getChildren();
		int currentValue = this.rootNode.getValue();
		int bestValue = 0;
		NewMove bestMove = null;
		for( ChessGameNode childNode : childrenNodes) {
			if (childNode != null) {
				if (player == 'w') {
					if (childNode.getValue() >= currentValue) {
						bestValue = childNode.getValue();
						bestMove = childNode.getOriginalMove();
					}
				} else if (player == 'b') {
					if (childNode.getValue() <= currentValue) {
						bestValue = childNode.getValue();
						bestMove = childNode.getOriginalMove();
					}
				} 
			}
		}
		return bestMove;
	}

	/*
	 * Returns a Board State with  a new BoardState and a new Unitlist
	 * after performing a move on the chessBoard
	 */
    private BoardState makeMove(ChessGameNode rootNodeCurr, NewMove nextMoveCurr) {
    	Tile[][] currentBoard = rootNodeCurr.getBoardState().getBoard();
    	Unit[] unitListcB = rootNodeCurr.getBoardState().getUnitList();

    	Unit unitThatMoves = nextMoveCurr.getUnit();
    	Moves moveToMake = nextMoveCurr.getMove();

    	Tile oldSquare = currentBoard[unitThatMoves.getX()][unitThatMoves.getY()];
        Tile newSquare = chessBoard[moveToMake.getX()][moveToMake.getY()];

        Unit oldUnit = oldSquare.getPiece();
        Unit targetUnit = newSquare.getPiece();

        BoardState newBoard;

        if (targetUnit != null) {
            for (int i = 0; i < unitListcB.length; i++) {
                if (unitListcB[i] == targetUnit) {
                    unitListcB[i] = null;
                }
            }   
        }

        // update the coordinates of the piece
		oldUnit.setCoordinates(moveToMake.getX(),moveToMake.getY());
		// doesn;t update the unit in the unitlist i think
        oldSquare.setPiece(null);
        newSquare.setPiece(oldUnit);
        newBoard = new BoardState(currentBoard, unitListcB);

    	return newBoard;
    }

    // check all the legal moves a unit can make for all the units in unitlist
    // and puts those move in the movelist with all the moves player can make
	private void generateLegalMovesPlayer(char player) {

		// loop through the unitLIst and generate all the moves a player can make
		for (Unit unit : this.unitList) {
			if (unit != null && unit.getColor() == player) {
				unit.getLegalMoves();
				insertMovesIntoMoveList(unit, unit.getMoveList());
			}
		}
	}


	// for inserting a list of moves into moveList
	private void insertMovesIntoMoveList(Unit unit, Moves[] moves) {
		for (int i = 0; i < moves.length; i++) {
			for (int j = 0; j < this.moveList.length; j++) {
				if (moves[i] != null && this.moveList[j] == null) {
					this.moveList[j] = new NewMove(unit, moves[i]);
					break;
				}
			}
		}
	}

	// generate all the moves a random move player can make and return a random one from that list
	public NewMove getRandomMove(char player) {
		int randomIndex;
		Random random = new Random();
		generateLegalMovesPlayer(player);
		NewMove randomMove=null;
		while(randomMove == null) {
			randomIndex = random.nextInt(this.moveList.length);
			randomMove = moveList[randomIndex];
		}
		return randomMove;
	}

}