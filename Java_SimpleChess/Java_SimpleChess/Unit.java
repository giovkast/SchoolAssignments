/* 
 * Haischel Dabian - 10336230
 * Giovanni Kastanja- 10467149
 * This is the parent class for the units, pawn rook and king
 * 
 */
public class Unit {
	private int xCoordinate;
	private int yCoordinate;
	private char color;
	private int value;
	private Moves[] moveList;

	// if a unit is black it has the negative value of a white unit
	public Unit(int x, int y, char color, int value) {
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.color = color;
		if (color == 'w') {
			this.value = value;
		} else {
			this.value = -value;
		}
	}

	public void show() {
		System.out.println("This is a Unit");
	}

	public char getColor() {
		return this.color;
	}

	public int getValue() {
		return this.value;
	}

	public void setCoordinates(int x, int y) {
		this.xCoordinate = x;
		this.yCoordinate = y;
	}

	public void insertMove(Moves move, Moves[] list) {
		for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                list[i] = move;   
                break;
            }
        }
	}

	// checks the move a piece can make
	public void getLegalMoves(){}
	// moves a piece
	public void makeMove(){}

	public void printMoveList(Moves[] moveList) {
		for(int i = 0; i < moveList.length; i++) {
			if (moveList[i] != null) {
				System.out.format(" %d %d \n ", moveList[i].getX(), moveList[i].getY());
			}
		}
	}

	public Moves[] getMoveList() {
		return this.moveList;
	}

	public int getX() {
		return this.xCoordinate;
	}

	public int getY() {
		return this.yCoordinate;
	}
}