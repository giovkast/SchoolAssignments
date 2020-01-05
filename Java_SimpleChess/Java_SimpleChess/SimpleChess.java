/* 
 * Haischel Dabian - 10336230
 * Giovanni Kastanja- 10467149
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class SimpleChess{
    // array for storing all the units
    public static Unit[] unitList = new Unit[32];
    public static Unit[] loserList = new Unit[32];
    private static Tile[][] chessBoard;
    private static int turn = 1;
    private static Boolean gameOver=false;

	public static void main(String[] args) {
		chessBoard = initChessBoard(args[0]);
        if(chessBoard!=null){
            Boolean start = false;

            System.out.println("\n\nWelcome to SimpleChess\n");
            instructions();
            Scanner scan = new Scanner(System.in);
            while (!start) {
                while (!scan.hasNextInt()) {
                   System.out.println("\nincorrect input.\n");
                   instructions();
                   scan.nextLine();
                }
                int userInput = scan.nextInt();
                if (userInput == 1 || userInput == 2 || userInput == 3||userInput==4) {
                    playGame(userInput);
                    start=true;
                } else {
                    System.out.println("\n"+Integer.toString(userInput)+" is not a valid entry\n");
                    instructions();
                }
            } 
        }
	}

	// This method will initialize the chessboard by reading a txt file
    public static Tile[][] initChessBoard(String fileName) {
        Tile[][] chessBoard = new Tile[8][8];
        Tile square;
        String line = null;
        int y = 7;
        Unit unit;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                // convert line to charArray
                // iterate over charArray 
                char[]  chars = line.toCharArray();
                for (int x = 0; x < chars.length; x++) {

                    // for every char we create a new unit except in the case of '#' default
                    // we create new unit, init the Tile with the reference to it
                    // init the squaure on which it will be placed
                    // and put the unit in the array of units so it can be modified later
                    switch (chars[x]) {
                        case 'r':
                            
                            unit = new Rook(x,y,'b');
                            square = new Tile(unit, x, y);
                            chessBoard[x][y] = square;
                            insertIntoUnitList(unit);
                            break;
                        case 'k':
                       
                            unit = new King(x,y,'b');
                            square = new Tile(unit, x, y);
                            chessBoard[x][y] = square;
                            insertIntoUnitList(unit);
                            break;
                        case 'p':
                            
                            unit = new Pawn(x,y,'b');
                            square = new Tile(unit, x, y);
                            chessBoard[x][y] = square;
                            insertIntoUnitList(unit);
                            break;
                        case 'R':
                            
                            unit = new Rook(x,y,'w');
                            square = new Tile(unit, x, y);
                            chessBoard[x][y] = square;
                            insertIntoUnitList(unit);
                            break;
                        case 'K':
                           
                            unit = new King(x,y,'w');
                            square = new Tile(unit, x, y);
                            chessBoard[x][y] = square;
                            insertIntoUnitList(unit);
                            break;
                        case 'P':
                        
                            unit = new Pawn(x,y,'w');
                            square = new Tile(unit, x, y);
                            chessBoard[x][y] = square;
                            insertIntoUnitList(unit);
                            break;  
                        default:
                            // in this case there is no unit on the square
                            // so the square is just initialised with its x-y coordinates
                            square = new Tile(x, y);
                            chessBoard[x][y] = square;
                            continue;
                    }
                    
                }
            y--;
            }   
            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        return chessBoard;
    }

    // insert a unit into the next free index of the UnitList
    private static void insertIntoUnitList(Unit unit) {
        // unitList is a global var
        for (int i = 0; i < unitList.length; i++) {
            if (unitList[i] == null) {
                unitList[i] = unit;
                break;
            }
        }
    }

    // This method will start the game mode selected by the user
    private static void playGame(int userInput) {
        if (userInput == 1) {
            startGame('P','M');
        } else if (userInput == 2) {
            startGame('M','P');
        } else if (userInput == 3) {
            startGame('P','P');
        } else {
            startGame('M','M');
        }
    }

    // this method starts Player vs Player mode
    private static void startGame(char p1,char p2) {
        displayBoard();
        while (!gameOver) {
            if (p1 == 'P') {
                playerMove('w');
            } else {
                machineMove('w');
                //correctMachineMove('w', chessBoard, unitList);
            }
            displayBoard();
            if (gameOver) { 
                break;
            }
            if (p2 == 'P') {
                playerMove('b');
            } else {
                machineMove('b');
                //correctMachineMove('b', chessBoard, unitList);
            }
            displayBoard();
        }
        if (turn % 2 == 0) {
            System.out.println("\nWhite has won!\n");
        } else {
            System.out.println("\nBlack has won!\n");
        }
        return;
    }

    // This method will read the users input and check if the move is legal
    // if the move is legal it will call makeMove to execute the move
    // if its not legal it will ask for another input
    private static void playerMove(char player) {
        int oldX;
        int oldY;
        int newX;
        int newY;
        Boolean legalMove = false;
        Scanner scan = new Scanner(System.in);
        
        while (!legalMove) {
            System.out.println("\nPlease enter a move in the form of \"A2-A4\"");
            String play = scan.next();
            char[] playChars = play.toCharArray();
            if (playChars.length == 5) {
                oldX = getLetterValue(playChars[0]);
                oldY = Character.getNumericValue(playChars[1] - 1);
                newX = getLetterValue(playChars[3]);
                newY = Character.getNumericValue(playChars[4] - 1);
                if (oldX >= 0 && oldX <=7 && oldY >= 0 && oldY <= 7 && newX >= 0 && newX <= 7 && newY >= 0 && newY <= 7 ) {
                    Unit unit = chessBoard[oldX][oldY].getPiece();
                    if (unit.getColor() == player) {
                        if (unit != null) {
                            unit.getLegalMoves();
                            Moves[] unitMoveList = unit.getMoveList();
                            if (unitMoveList[0] != null) {
                                legalMove=checkIfPlayerMoveLegal(unitMoveList, newX, newY);
                            } 
                        }
                        if(legalMove) {
                            makeMove(oldX,oldY,newX,newY);
                            turn++;
                            break;
                        } else {
                            System.out.println("this is not a legal move");
                        }
                    } else {
                        System.out.println("This is not your piece!");
                    }
                } else {
                    System.out.println("\nThis is not a valid input");
                    System.out.println("Please try again.");
                }
            } else {
                System.out.println("\nThis is not a valid input");
                System.out.println("Please try again.");
            }
        }
    }

    //This method will ask the machine to make a move
    private static void machineMove(char color) {
        ChessGame cg = new ChessGame(chessBoard, unitList);
        NewMove randomMove = cg.getRandomMove(color);
        int oldX = randomMove.getUnit().getX();
        int oldY = randomMove.getUnit().getY();
        int newX = randomMove.getMove().getX();
        int newY = randomMove.getMove().getY();
        makeMove(oldX,oldY,newX,newY);
        turn++;        
    }

    // this method will check all the moves the machine can make
    // and return the one that result in the best board position
    private static void correctMachineMove(char color, Tile[][] chessBoard, Unit[] unitList) {
        ChessGame cg = new ChessGame(chessBoard, unitList);
        NewMove randomMove = cg.getBestMove(color);
        int oldX = randomMove.getUnit().getX();
        int oldY = randomMove.getUnit().getY();
        System.out.format("%d %d\n", oldX, oldY);
        int newX = randomMove.getMove().getX();
        int newY = randomMove.getMove().getY();
        System.out.format("%d %d\n", newX, newY);
        makeMove(oldX,oldY,newX,newY);
        turn++;     
    }

    // This method will convert the inputed letter by the user 
    // to its integer value inorder to play the game
    private static int getLetterValue(char letter) {
        char upperLetter = Character.toUpperCase(letter);
        int charvalue;
        switch (upperLetter) {
            case 'A':  charvalue = 0;
                     break;
            case 'B':  charvalue = 1;
                     break;
            case 'C':  charvalue = 2;
                     break;
            case 'D':  charvalue = 3;
                     break;
            case 'E':  charvalue = 4;
                     break;
            case 'F':  charvalue = 5;
                     break;
            case 'G':  charvalue = 6;
                     break;
            case 'H':  charvalue = 7;
                     break;
            default: charvalue = 10;
                     break;
        }
        return charvalue;
    }

    // this method will check if users inputed move is legal.
    // if the move is legal it will return true.
    private static Boolean checkIfPlayerMoveLegal(Moves[] moveList, int newX, int newY) {
        Boolean legalMove = false;
        for(Moves move : moveList){
            if(move != null){
                int possibleX = move.getX();
                int possibleY = move.getY();
                if (possibleX == newX && possibleY == newY) {
                    legalMove = true;
                }
            }
        }
        return legalMove;
    }

    public static Tile[][] getChessBoard() {
        return chessBoard;
    }

    // This method makes the move
    // if it finds a unit on the destination square
    // it will first check if its the king, if it is its game over
    // if its not it will add it to loserList and remove him form unitList
    private static void makeMove(int oldX, int oldY, int newX, int newY) {
        if (oldX == newX && oldY == newY) {
            return;
        }
        Tile oldSquare = chessBoard[oldX][oldY];
        Tile newSquare = chessBoard[newX][newY];
        Unit oldUnit = oldSquare.getPiece();
        Unit targetUnit = newSquare.getPiece();
        if (targetUnit != null) {
            if(targetUnit instanceof King) {
                gameOver = true;
            } 
            insertIntoLoserList(targetUnit);
            targetUnit.setCoordinates(10,10);
            for (int i = 0; i < unitList.length; i++) {
                if (unitList[i] == targetUnit) {
                    System.out.println("unit found!");
                    unitList[i] = null;
                }
            }   
        }
        oldUnit.setCoordinates(newX,newY);
        oldSquare.setPiece(null);
        newSquare.setPiece(oldUnit);
    }

    // insert given unit into loserList
    private static void insertIntoLoserList(Unit unit) {
        for (int i = 0; i < loserList.length; i++) {
            if (loserList[i] == null) {
                loserList[i] = unit;
                break;
            }
        }
    }

    // this method will print instructions to the command line
    private static void instructions() {
        System.out.println("Enter '1' for Player vs Machine");
        System.out.println("Enter '2' for Machine vs Player");
        System.out.println("Enter '3' for Player vs Player");
        System.out.println("Enter '4' for Machine vs Machine\n");
        System.out.println("Enter a digit between 1-4:");
    }

    // this method will print the chess board to the command line
    private static void displayBoard() {
        String s = ""; // s is the line with units
        String w = ""; // w is the dead white pieces
        String b = ""; // b is the dead black pieces
        String top = "   _______________________________________________";
        String mid = "  |     |     |     |     |     |     |     |     |";
        String bottom = "  |_____|_____|_____|_____|_____|_____|_____|_____|";
        String newline = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
        int count = 8;
        System.out.println(newline+"This is turn number: "+Integer.toString(turn));
        if (turn % 2 == 1) {
            System.out.println("\nIt's Whites turn:\n");
        } else {
            System.out.println("\nIt's Blacks turn:\n");
        }
        System.out.println(top);
        for (int y = 7; y>=0;y--) {
            System.out.println(mid);
            for (int x = 0; x <= 7;x ++) {
                Unit unit = chessBoard[x][y].getPiece();
                s=getPrintChar(unit, s);
                if (x < 7) {
                    s = s +"|";
                } 
            }
            s = Integer.toString(count) + " |" + s + "|";
            System.out.println(s);
            count--;
            s="";
            System.out.println(bottom);
        }
        System.out.println("     A     B     C     D     E     F     G     H");
        System.out.println("\n");
        System.out.println("The Junkyard:");
        for (Unit loser : loserList) {
            if (loser != null) {
                if (loser.getColor() == 'w') {
                    w = getPrintChar(loser, w);
                } else {
                    b = getPrintChar(loser, b);
                }
                
            }
        }
        System.out.println(w);
        System.out.println(b);
        w = "";
        b = "";
    }

    // this method will identify what type of unit we have 
    // and will add the units unicode chess character to the 
    // string thats about to be printed
    /*
    private static String getPrintChar(Unit unit, String s){
        if(unit instanceof Rook && unit.getColor() =='w'){
            s+="  \u2656  ";
        } else if(unit instanceof Pawn && unit.getColor() =='w'){
            s+="  \u2659  ";
        } else if(unit instanceof King && unit.getColor() =='w'){
            s+="  \u2654  ";
        } else if(unit instanceof Rook && unit.getColor() =='b'){
            s+="  \u265C  ";
        } else if(unit instanceof Pawn && unit.getColor() =='b'){
            s+="  \u265F  ";
        } else if(unit instanceof King && unit.getColor() =='b'){
            s+="  \u265A  ";
        } else{
            s+="     ";
        }
        return s;
    }
    */

    //this method will identify what type of unit we have 
    // and will add the units unicode chess character to the 
    // string thats about to be printed
    private static String getPrintChar(Unit unit, String s) {
        if (unit instanceof Rook && unit.getColor() =='w') {
            s += "  R  ";
        } else if (unit instanceof Pawn && unit.getColor() =='w') {
            s += "  P  ";
        } else if (unit instanceof King && unit.getColor() =='w') {
            s += "  K  ";
        } else if (unit instanceof Rook && unit.getColor() =='b') {
            s += "  r  ";
        } else if (unit instanceof Pawn && unit.getColor() =='b') {
            s += "  p  ";
        } else if (unit instanceof King && unit.getColor() =='b') {
            s += "  k  ";
        } else {
            s += "     ";
        }
        return s;
    }
}
