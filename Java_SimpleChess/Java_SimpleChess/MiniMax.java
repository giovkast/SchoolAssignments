/* 
 * Haischel Dabian - 10336230
 * Giovanni Kastanja- 10467149
 * this class will get a board state
 * and the boardstate will perform the miniMax algorithm for finding the 
 * best move to play right now, for the current player
 * These are the heuristics for deciding what moves are better then another
 * taking an enemy pawn is +1, an rook is +5, a king + 19

 * losing a pawn is -1, a rook - 5 and a king - 19
 * The score of the boardstate is then the sum of the value of the pieces of a player
 * still on the board
*/

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
public class ChessGameNode{
	private Tile[][] chessBoard;
	private Moves[] moveList;
	private Unit[] unitList;
	private int value;

	// change this files name to chesssgamenode 
	// and move all the methods to the overarching class of the nodes "ChessGame"

	public ChessGameNode(Tile[][] chessBoard, Unit[] unitList) {
		this.chessBoard = chessBoard;
		this.unitList = unitList;
	}

	/*
	 * mode can be:
	 * 		- random: in which can the legal moves for the player are generated
	 * 				  and one of those moves is chosen randomly
	 *  	- ab: in which three layers deep, or 5 seconds long the best move is search
	 * 				in a collection of array  
	 */
	public Moves getBestMove(String mode, char player) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override 
			public void run() {
				if (mode.equals("random")) {

					int randomIndex;
					generateLegalMovesPlayer(player);

					Random random = new Random();
					randomIndex = random.nextInt(moveList.length);
					Moves randomMove = moveList[randomIndex];

					return randomMove;
				} 
					// when we generated the legal moves of a player
					// we have to make the move at least in the tree

					// perform a move in the moveList
					// check the value of the board for the player after performing the move
					// after performing the move call getBestMove for the new player
					// check the 
				/* 
				 *  Solution Words
				 * Check the score of the boardState, generate all the legal moves of the current player save that in an array
				 * Do a move in the array, check the new scoreboardState, generate all the legale move of the other player 
				 * save them in an array, (do that recursively)
				 * three layers deep maximum
				 * 
				 */
				/*
				 * So for all the pieces in the unitList we find the correct piece on the chessBoard
				 * 
				 */
				// go through the unitList and get the moves of the units

				// do a random move and check the boardstate after the move 3 plies deep

				// save the move that most improves the (blacks) board state
				/*
				 * The first boardState is the root
				 * we check all the moves the current player can make, these are the children
				*/

				/* 
				 * So now we have to check how to check the board state
				 * Answer: The board state is the sum of the value of the pieces of a player
				 * still on the board
				 */

				/* 
				 * so essentially get board state, check the possible moves in an array that's the first level
				 * at the first level we have an array with all the moves that are possible.
				 * When we have that list we just go through the list sequentially, we perform the move at array index 1
				 * Once we have performed that move, we check the new boardstate and check the response the enemy player can make
				 * this will also be an array of moves. When we got a list of all the response moves, we perform the moves
				 * in this array sequentially again, and check the boardstate after these moves
				 * 
				*/ 
			}
		}, 5000);	
		// get the boardstate

		// calculate 3/4 plies deep, or 5 seconds

		// during calculations 

		// calculate the best moves based on the value of the pieces
	}

	private int getBoardStateValue(Tile[][] chessBoard, char player) {
		// check the unitList for all the pieces of a player still on the board
		// and returns the sum of their value
		return 0;
	}

	private void findPiecesOnChessBoard() {
		// you get the unitLIst, find a piece with specific coordinates
		// find a piece with the same coordinates on the chessboard
	}

	private void generateLegalMovesPlayer(char player) {
		// init with random length
		this.moveList = new Moves[4];

		// loop through the unitLIst and generate all the moves a player can make
		for(Unit unit : SimpleChess.unitList) {
			unit.getLegalMoves();

			insertMovesIntoMoveList(unit.getMoveList());
			insertPotentialTargetsIntoMoveList(unit.getTargetList(), player);
		}

		// return an array with all the legal moves a player can make
	}

	// for inserting a list of moves into moveList
	private void insertMovesIntoMoveList(Moves[] moves) {
		if (this.moveList == null) {
			this.moveList = moves;
		} else {
			int oldArraySize = this.moveList.length;

			Moves[] newMoveList = new Moves[oldArraySize + moves.length];
			System.arraycopy(this.moveList, 0, newMoveList, 0, oldArraySize);

			this.moveList = newMoveList;
		}
	}

	// for inserting a single move into move list
	private void insertMoveIntoMoveList(Moves move) {
		if (this.moveList == null) {
			this.moveList = new Moves[1];
			this.moveList[0] = move;
		} else {
			int oldArraySize = this.moveList.length;

			Moves[] newMoveList = new Moves[oldArraySize + 1];
			System.arraycopy(this.moveList, 0, newMoveList, 0, oldArraySize);

			this.moveList = newMoveList;
		}
	}	

	// check if potential target is an enemy piece, if so 
	// it is possible to move to the tile of the target
	private void insertPotentialTargetsIntoMoveList(Moves[] targets, char playerColor) {
		for(Moves target : targets) {
				Moves newMove = new Moves(target.getX(), target.getY());
				insertMoveIntoMoveList(newMove);
			}
		}
	}
}